package com.example.saksi_test1.model;

public class ImageTattoo {
    private String deposit,detail,pictattoo,price,tattooType;

    ImageTattoo(){

    }

    public ImageTattoo(String deposit, String detail, String pictattoo, String price, String tattooType) {
        this.deposit = deposit;
        this.detail = detail;
        this.pictattoo = pictattoo;
        this.price = price;
        this.tattooType = tattooType;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPictattoo() {
        return pictattoo;
    }

    public void setPictattoo(String pictattoo) {
        this.pictattoo = pictattoo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTattooType() {
        return tattooType;
    }

    public void setTattooType(String tattooType) {
        this.tattooType = tattooType;
    }
}
