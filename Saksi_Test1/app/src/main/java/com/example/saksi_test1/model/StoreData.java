package com.example.saksi_test1.model;

public class StoreData {
    private String storeName, imgTattoo, detail, typeTattoo;

    public StoreData(){

    }

    public StoreData(String storeName, String imgTattoo, String detail, String typeTattoo) {
        this.storeName = storeName;
        this.imgTattoo = imgTattoo;
        this.detail = detail;
        this.typeTattoo = typeTattoo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getImgTattoo() {
        return imgTattoo;
    }

    public void setImgTattoo(String imgTattoo) {
        this.imgTattoo = imgTattoo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTypeTattoo() {
        return typeTattoo;
    }

    public void setTypeTattoo(String typeTattoo) {
        this.typeTattoo = typeTattoo;
    }
}