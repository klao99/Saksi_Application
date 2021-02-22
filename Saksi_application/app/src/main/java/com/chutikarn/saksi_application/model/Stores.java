package com.chutikarn.saksi_application.model;

public class Stores {
    public String id;
    public String location;
    public String storeName;
    public String banner;
    public String profile;
    //public ArrayList<String> type;
    public String storeDetail;
    public String type;

    public Stores() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(String storeDetail) {
        this.storeDetail = storeDetail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Stores(String id, String location, String storeName, String banner, String profile, String storeDetail, String type) {
        this.id = id;
        this.location = location;
        this.storeName = storeName;
        this.banner = banner;
        this.profile = profile;
        this.storeDetail = storeDetail;
        this.type = type;
    }
}
