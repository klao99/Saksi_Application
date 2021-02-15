package com.chutikarn.saksi_application.model;

public class tattooPic {
    private String detail;
    private String imageUrl;
    private String id;
    private String typeTattoo;

    public tattooPic() {
    }

    public tattooPic(String detail, String imageUrl, String id, String typeTattoo) {
        this.detail = detail;
        this.imageUrl = imageUrl;
        this.id = id;
        this.typeTattoo = typeTattoo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeTattoo() {
        return typeTattoo;
    }

    public void setTypeTattoo(String typeTattoo) {
        this.typeTattoo = typeTattoo;
    }
}
