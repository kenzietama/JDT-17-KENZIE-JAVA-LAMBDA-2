package com.indivaragroup.services.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class PropertyAsset {
    private String id;
    private String propertyName;
    private String propertyType;
    private String location;
    private double price;
    private int landArea;
    private int buildingArea;
    private boolean sold;
    private int buildYear;

    public PropertyAsset(String id, String propertyName, String propertyType, String location, double price, int landArea, int buildingArea, boolean sold, int buildYear) {
        this.id = id;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.location = location;
        this.price = price;
        this.landArea = landArea;
        this.buildingArea = buildingArea;
        this.sold = sold;
        this.buildYear = buildYear;
    }

    public void print() {
        String statusTerjual = this.sold ? "Terjual" : "Belum Terjual";

        NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
        String hargaFormat = "Rp " + nf.format(this.price);

        System.out.printf("[%s] %s | %s | %s | %s | %dm² | %s%n",
                this.id,
                this.propertyName,
                this.propertyType,
                this.location,
                hargaFormat,
                this.landArea,
                statusTerjual
        );
    }

    public static String formatRupiah(double harga) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
        return "Rp " + nf.format(harga);
    }

    public String getId() {
        return id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public int getLandArea() {
        return landArea;
    }

    public int getBuildingArea() {
        return buildingArea;
    }

    public boolean isSold() {
        return sold;
    }

    public int getBuildYear() {
        return buildYear;
    }
}

