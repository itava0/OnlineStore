package com.itavarez;

public class Product {
    private String SKU;

    private String PRODUCT_NAME;

    private double PRODUCT_PRICE;

    private String PRODUCT_DEPARTMENT;

    public Product(String SKU, String PRODUCT_NAME, double PRODUCT_PRICE, String PRODUCT_DEPARTMENT) {
        this.SKU = SKU;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.PRODUCT_PRICE = PRODUCT_PRICE;
        this.PRODUCT_DEPARTMENT = PRODUCT_DEPARTMENT;
    }

    public String getSKU() {
        return SKU;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public double getPRODUCT_PRICE() {
        return PRODUCT_PRICE;
    }

    public String getPRODUCT_DEPARTMENT() {
        return PRODUCT_DEPARTMENT;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public void setPRODUCT_PRICE(double PRODUCT_PRICE) {
        this.PRODUCT_PRICE = PRODUCT_PRICE;
    }

    public void setPRODUCT_DEPARTMENT(String PRODUCT_DEPARTMENT) {
        this.PRODUCT_DEPARTMENT = PRODUCT_DEPARTMENT;
    }

    @Override
    public String toString() {
        return "Product{" +
                "SKU='" + SKU + '\'' +
                ", PRODUCT_NAME='" + PRODUCT_NAME + '\'' +
                ", PRODUCT_PRICE=" + PRODUCT_PRICE +
                ", PRODUCT_DEPARTMENT='" + PRODUCT_DEPARTMENT + '\'' +
                '}';
    }
}
