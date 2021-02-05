package com.example.saksi_test1.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageData {

    //public String storeName, detail, imgTat,tattooType;
    //private ArrayList<String> imgtatoo;
    private String banner, profile;

    public ImageData(String banner, String profile) {
        this.banner = banner;
        this.profile = profile;
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
}
