package com.chutikarn.saksi_application.model;

public class tattooImg {

    private  String id, name, price, tattoo_type, store_id, image;

    tattooImg(){ }

    public tattooImg(String id, String name, String price, String tattoo_type, String store_id, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tattoo_type = tattoo_type;
        this.store_id = store_id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTattoo_type() {
        return tattoo_type;
    }

    public void setTattoo_type(String tattoo_type) {
        this.tattoo_type = tattoo_type;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}