package com.qapitol.jsw.utils;

import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import lombok.Getter;

import java.text.DecimalFormat;

public class CurrencyUtils {
    private SimpleLogger log = SauronLogger.getLogger();

    @Getter
    private final DecimalFormat decimalFormatterDecimal;

    @Getter
    private final DecimalFormat decimalFormatterCommaDecimal;

    private CurrencyUtils() {
        this.decimalFormatterDecimal = new DecimalFormat("#.00");
        this.decimalFormatterCommaDecimal = new DecimalFormat("#,###.00");
    }

    public static CurrencyUtils getNewInstance() {
        return new CurrencyUtils();
    }

    public String convertPaisaToRupees(int paisa) {
        double rupees = paisa / 100.0;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(rupees);
    }

    public Double getTDSAmount(Double baseAmount, int rate) {
        Double tdsAmount = baseAmount * rate / 100;
        tdsAmount = Math.ceil(tdsAmount);
        log.info("TDS Amount --> " + tdsAmount);
//        ExtentCucumberAdapter.addTestStepLog("TDS Amount --> " + tdsAmount);
        return tdsAmount;
    }

    public Double getTDSAmount(Double baseAmount, double rate) {
        Double tdsAmount = baseAmount * rate / 100;
        tdsAmount = Math.ceil(tdsAmount);
        log.info("TDS Amount --> " + tdsAmount);
//        ExtentCucumberAdapter.addTestStepLog("TDS Amount --> " + tdsAmount);
        return tdsAmount;
    }

    public double calculateBaseAmount(double invoiceAmount, double gstPercentage) {
        double gstAmount = 1 + gstPercentage;
        return invoiceAmount / gstAmount;
    }
}
