package com.chutikarn.saksi_application.model;

public class Category {
    public String catIcon,catTitle;

    public Category() {
    }

    public Category(String catIcon, String catTitle) {
        this.catIcon = catIcon;
        this.catTitle = catTitle;
    }

    public String getCatIcon() {
        return catIcon;
    }

    public void setCatIcon(String catIcon) {
        this.catIcon = catIcon;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

}
