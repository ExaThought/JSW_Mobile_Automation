package com.qapitol.jsw.pageFactory;

import org.openqa.selenium.By;

public class CategoryPages {

    public static String mildSteelCategory = "//li//a[contains(@href,'mild-steel')]";
    public static String hotRolledCategory = "//li/a[contains(@href,'hot-rolled-steel')]";
    public static String msHotRolled = "//li/a[contains(@href,'hot-rolled-steel')]";
    public static String hotRolledSheetsSubCategory = "//a[contains(@href,'hot-rolled-sheets')]";
    public static String hotRolledCoilsSubCategory = "//a[contains(@href,'hot-rolled-coils')]";
    public static String length = "//div[contains(text(),'Length (mm)')]/parent::div/following-sibling::div/child::div/child::button[1]";
    public static String addToCart = "//button[text()='Add to cart']";
    public static String coldRolledCategory = "(//li//a[@href=\"/category/cold-rolled-steel\"])[1]";
    public static String coldRolledCoilsSubCategory = "(//li//a[@href=\"/category/cold-rolled-coils\"])[1]";
    public static String coldRolledSheetsSubCategory = "(//li//a[@href=\"/category/cold-rolled-sheets\"])[1]";
    public static String COATING_GRADE = "//span[contains(normalize-space(), 'Coating grade (gsm)')]/../following-sibling::div/button[not(contains(@class, 'disabled'))]";
    public static String SPANGLE_TYPE = "//span[contains(normalize-space(), 'Spangle type')]/../following-sibling::div/button[not(contains(@class, 'disabled'))]";
    public static String wireCategory = "//li//a[contains(@href,'steel-wire-rods')]";
    public static String structuralSteelCategory = "//div[@class='nav-active-link-jsw']/child::a[contains(text(),'Structural steel')]";
    public static String anglesSubCategory = "(//a[contains(text(),'Angles')])[2]";
    public static String channelsSubCategory = "(//a[contains(text(),'Channels')])[3]";
    public static String beamsAndColumnsSubCategory = "(//a[contains(text(),'Beams and columns')])[2]";
    public static String deleteCartLineItem = "(//div[contains(text(),'Delete')])[1]";
    public static String coatedSteel = "//a[contains(@href, 'steel-coated') or contains(@href, '/category/coated-steel')]/span";
    public static By editEnterQuantity = By.xpath("//div[contains(text(),'Enter quantity')]");
    public static By CONFIRM_ORDER_BTN = By.xpath("//button[normalize-space()='Confirm order']");
    public static By ACTIVE_ORDER_NUMBER = By.xpath("//span[normalize-space()='Order number:']/following-sibling::span");
    public static By VIEW_ORDER_DOCUMENTS_BTN = By.xpath("//p[normalize-space()='View order documents']");
    public static By SAVE_ADDRESS = By.xpath("//button[@id='save-change' and normalize-space()='Save changes']");
    public static By Add_TO_CART_BUTTON = By.xpath("//button[text()='Add to cart']");
    public static By BLOG_SECTION = By.xpath("//span[text()='Blogs']");
    public static By BLOG_Youtube = By.xpath("//img[@alt='Youtube']");
    public static By ALL_CATEGORY = By.xpath("(//span[text()=\"All categories\"])[2]");
}

