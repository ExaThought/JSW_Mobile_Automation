package com.qapitol.jsw.pageFactory;


public class CategoryPages {

    public static String mildSteelCategory="//a[contains(., 'Mild Steel')]";
    public static String hotRolledCategory="(//a[contains(text(),'Hot rolled')])[4]";
    public static String hotRolledSheetsSubCategory="(//a[contains(text(),'Hot rolled sheets')])[1]";
    public static String hotRolledCoilsSubCategory="(//a[contains(text(),'Hot rolled coils')])[1]";
    public static String plpFirstProduct="(//span[contains(text(),'Brand:')])[1]";
    public static String length="//div[contains(text(),'Length (mm)')]/parent::div/following-sibling::div/child::div/child::button[1]";
    public static String addToCart="//button[contains(., 'Add to Cart')]";
    public static String coldRolledCategory="(//a[contains(text(),'Cold rolled')])[4]";
    public static String coldRolledCoilsSubCategory ="(//a[contains(text(),'Cold rolled coils')])[1]";
    public static String coldRolledSheetsSubCategory="(//a[contains(text(),'Cold rolled sheets')])[1]";
    public static String coatingGrade="(//div[contains(text(),'Coating grade - AZ (GSM)')]//parent::div//following-sibling::div//child::div//child::button)[1]";
}

