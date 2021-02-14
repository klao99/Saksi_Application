package com.chutikarn.saksi_application.model;

public class tattooPic {
    private String detail;
    private String imageUrl;

    public tattooPic(String detail, String imageUrl) {
        this.detail = detail;
        this.imageUrl = imageUrl;
    }

    public tattooPic() {
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
}
