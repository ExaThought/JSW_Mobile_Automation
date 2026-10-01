package com.qapitol.jsw.utils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

/**
 * Ownership rules of {@link TagsOverrideGuard}: which {@code <test>} block runs a scenario under a
 * Jenkins TAGS override. A wrong answer runs the scenario under the wrong account.
 *
 * <p>Pure logic, no browser and no Sauron runtime - messages are captured instead of logged.
 */
public class TagsOverrideGuardTest {

    private final List<String> logged = new ArrayList<>();

    @BeforeMethod
    public void captureLogs() {
        logged.clear();
        TagsOverrideGuard.INFO = logged::add;
        TagsOverrideGuard.WARN = logged::add;
    }

    // ---- pathCovers: path-boundary matching -------------------------------------------------

    @Test
    public void fileEntryDoesNotClaimAFileWhoseNameEndsTheSame() {
        String social = "/ws/src/test/resources/features/login/SocialLogin.feature";
        assertFalse(TagsOverrideGuard.pathCovers("src/test/resources/features/login/Login.feature", social));
        assertFalse(TagsOverrideGuard.pathCovers("Login.feature", social));
    }

    @Test
    public void fileEntryClaimsItsOwnFile() {
        String login = "/ws/src/test/resources/features/login/Login.feature";
        assertTrue(TagsOverrideGuard.pathCovers("src/test/resources/features/login/Login.feature", login));
        assertTrue(TagsOverrideGuard.pathCovers("Login.feature", login));
    }

    @Test
    public void directoryEntryNeedsAWholeDirectoryMatch() {
        assertTrue(TagsOverrideGuard.pathCovers("src/test/resources/features/Homepage",
                "/ws/src/test/resources/features/Homepage/Ledger.feature"));
        assertFalse(TagsOverrideGuard.pathCovers("src/test/resources/features/Homepage",
                "/ws/src/test/resources/features/HomepageExtra/Ledger.feature"));
        assertFalse(TagsOverrideGuard.pathCovers("features", "/ws/src/test/resources/myfeatures/A.feature"));
    }

    @Test
    public void lineSelectorIsIgnoredForTheFileMatch() {
        String cart = "/ws/src/test/resources/features/cart/AddToCart.feature";
        assertTrue(TagsOverrideGuard.pathCovers("src/test/resources/features/cart/AddToCart.feature:12", cart));
        assertFalse(TagsOverrideGuard.pathCovers("src/test/resources/features/cart/Add.feature:12", cart));
    }

    @Test
    public void classpathAndDotSlashAndBackslashEntriesAreNormalised() {
        String ledger = "/ws/src/test/resources/features/Homepage/Ledger.feature";
        assertTrue(TagsOverrideGuard.pathCovers("classpath:features", "classpath:features/Homepage/Ledger.feature"));
        assertTrue(TagsOverrideGuard.pathCovers("classpath:features/Homepage", "classpath:features/Homepage/Ledger.feature"));
        assertTrue(TagsOverrideGuard.pathCovers("./src/test/resources/features", ledger));
        assertTrue(TagsOverrideGuard.pathCovers(".\\src\\test\\resources\\features\\", ledger));
    }

    @Test
    public void absoluteEntriesMatchAbsoluteScenarioPaths() {
        assertTrue(TagsOverrideGuard.pathCovers("/var/lib/jenkins/ws/src/test/resources/features",
                "/var/lib/jenkins/ws/src/test/resources/features/Homepage/Ledger.feature"));
        assertTrue(TagsOverrideGuard.pathCovers("D:/repo/src/test/resources/features",
                "/D:/repo/src/test/resources/features/Homepage/Ledger.feature"));
    }

    // ---- contains: comma-separated cucumber.features and URI forms ---------------------------

    @Test
    public void containsChecksEveryCommaSeparatedEntryAndBothUriForms() {
        XmlTest block = block(new XmlSuite(), "Mixed", null, "src/test/resources/features/cart, src/test/resources/features/Homepage");
        assertTrue(TagsOverrideGuard.contains(block, URI.create("file:///ws/src/test/resources/features/Homepage/Ledger.feature")));
        assertFalse(TagsOverrideGuard.contains(block, URI.create("file:///ws/src/test/resources/features/profile/Customer.feature")));
        XmlTest classpathBlock = block(new XmlSuite(), "Cp", null, "classpath:features/Homepage");
        assertTrue(TagsOverrideGuard.contains(classpathBlock, URI.create("classpath:features/Homepage/Ledger.feature")));
    }

    // ---- ownFilterMatches / ownerOf ---------------------------------------------------------

    @Test
    public void ownerIsTheBlockWhoseOwnFilterMatches() {
        XmlSuite suite = new XmlSuite();
        XmlTest login = block(suite, "Login", "@login or @registration", "src/test/resources/features");
        XmlTest ledger = block(suite, "Ledger", "@ledger", "src/test/resources/features");
        XmlTest homepage = block(suite, "Homepage", "@header or @footer", "src/test/resources/features");
        URI ledgerFile = URI.create("file:///ws/src/test/resources/features/Homepage/Ledger.feature");

        assertEquals(TagsOverrideGuard.ownerOf(suite.getTests(), tags("@ledger", "@regression"), ledgerFile), ledger);
        assertEquals(TagsOverrideGuard.ownerOf(suite.getTests(), tags("@footer", "@regression"), ledgerFile), homepage);
        assertEquals(TagsOverrideGuard.ownerOf(suite.getTests(), tags("@login"), ledgerFile), login);
        assertNull(TagsOverrideGuard.ownerOf(suite.getTests(), tags("@tokenCheckLogin"), ledgerFile),
                "a tag only a commented-out block selects has no owner");
    }

    @Test
    public void featurePathDecidesOwnershipBeforeTags() {
        XmlSuite suite = new XmlSuite();
        XmlTest cart = block(suite, "Cart", "@regression", "src/test/resources/features/cart");
        XmlTest ledger = block(suite, "Ledger", "@regression", "src/test/resources/features/Homepage");
        URI ledgerFile = URI.create("file:///ws/src/test/resources/features/Homepage/Ledger.feature");
        XmlTest owner = TagsOverrideGuard.ownerOf(suite.getTests(), tags("@regression"), ledgerFile);
        assertNotEquals(owner, cart, "Cart comes first and its filter matches, but its path does not contain Ledger.feature");
        assertEquals(owner, ledger);
        URI cartFile = URI.create("file:///ws/src/test/resources/features/cart/AddToCart.feature");
        assertEquals(TagsOverrideGuard.ownerOf(suite.getTests(), tags("@regression"), cartFile), cart);
    }

    @Test
    public void unfilteredBlockMatchesEveryScenarioAndFeaturelessCatchAllIsLogged() {
        XmlSuite suite = new XmlSuite();
        XmlTest catchAll = block(suite, "Everything", null, null);
        block(suite, "Ledger", "@ledger", "src/test/resources/features");
        URI ledgerFile = URI.create("file:///ws/src/test/resources/features/Homepage/Ledger.feature");

        assertTrue(TagsOverrideGuard.ownFilterMatches(catchAll, tags("@anything")));
        assertEquals(TagsOverrideGuard.ownerOf(suite.getTests(), tags("@ledger"), ledgerFile), catchAll,
                "first in run order wins");
        assertEquals(logged.size(), 1, "the catch-all claim is logged once: " + logged);
        assertTrue(logged.get(0).contains("'Everything' has no cucumber.features"), logged.get(0));
    }

    @Test
    public void malformedFilterNamesTheBlockAndTheFilterText() {
        XmlTest broken = block(new XmlSuite(), "Broken", "(@ledger and", "src/test/resources/features");
        IllegalStateException e = expectThrows(IllegalStateException.class,
                () -> TagsOverrideGuard.ownFilterMatches(broken, tags("@ledger")));
        assertTrue(e.getMessage().contains("'Broken'"), e.getMessage());
        assertTrue(e.getMessage().contains("(@ledger and"), e.getMessage());
    }

    @Test
    public void tagExpressionsBehaveAsInCucumber() {
        XmlTest notWip = block(new XmlSuite(), "NotWip", "@regression and not @wip", null);
        assertTrue(TagsOverrideGuard.ownFilterMatches(notWip, tags("@regression")));
        assertFalse(TagsOverrideGuard.ownFilterMatches(notWip, tags("@regression", "@wip")));
    }

    // ---- helpers ----------------------------------------------------------------------------

    private static XmlTest block(XmlSuite suite, String name, String filter, String features) {
        XmlTest test = new XmlTest(suite);
        test.setName(name);
        Map<String, String> parameters = new HashMap<>();
        if (filter != null) {
            parameters.put("cucumber.filter.tags", filter);
        }
        if (features != null) {
            parameters.put("cucumber.features", features);
        }
        test.setParameters(parameters);
        return test;
    }

    private static List<String> tags(String... tags) {
        return Arrays.asList(tags);
    }
}
