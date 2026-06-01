package com.indivaragroup.lambda.exam.property.model;

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

    public PropertyAsset() {

    }

    public PropertyAsset(String id) {
        this.id = id;
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

