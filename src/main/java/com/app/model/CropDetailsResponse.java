package com.app.model;

public class CropDetailsResponse {
    private Long farmerId;
    private String farmerName;
    private int price;
    private int quantity;

    public CropDetailsResponse(Long farmerId, String farmerName, int price, int quantity) {
        this.farmerId = farmerId;
        this.farmerName = farmerName;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
