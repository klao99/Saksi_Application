package com.chutikarn.saksi_application.model;

public class Request {

    private String dateTime;
    private String position;
    private String storeId;
    private String color;
    private String storeName;
    private String imageUrl;
    private String detail;
    private String size;
    private String userId;

    public Request() {
    }

    public Request(String dateTime, String position, String storeId, String color, String storeName, String imageUrl, String detail, String size, String userId) {
        this.dateTime = dateTime;
        this.position = position;
        this.storeId = storeId;
        this.color = color;
        this.storeName = storeName;
        this.imageUrl = imageUrl;
        this.detail = detail;
        this.size = size;
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
