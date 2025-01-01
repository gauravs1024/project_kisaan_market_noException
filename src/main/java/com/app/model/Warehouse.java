package com.app.model;

public class Warehouse {
    private int id;
    private String location;
    private int maximumCapacity;
    private int currentCapacity;
    private String placeName;
    private String ownerName;
    private double ratePerQuintalPurchase;
    private double ratePerQuintalSelling;
    private String cropsAvailable;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getRatePerQuintalPurchase() {
        return ratePerQuintalPurchase;
    }

    public void setRatePerQuintalPurchase(double ratePerQuintalPurchase) {
        this.ratePerQuintalPurchase = ratePerQuintalPurchase;
    }

    public double getRatePerQuintalSelling() {
        return ratePerQuintalSelling;
    }

    public void setRatePerQuintalSelling(double ratePerQuintalSelling) {
        this.ratePerQuintalSelling = ratePerQuintalSelling;
    }

    public String getCropsAvailable() {
        return cropsAvailable;
    }

    public void setCropsAvailable(String cropsAvailable) {
        this.cropsAvailable = cropsAvailable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
