package com.example.projekt_sm.API;

public class ImageModel {
    private int id;
    private String mediumUrl;

    public ImageModel() {
    }

    public ImageModel(int id, String mediumUrl) {
        this.id = id;
        this.mediumUrl = mediumUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }
}
