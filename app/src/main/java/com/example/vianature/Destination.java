package com.example.vianature;

public class Destination {
    public String title, description;
    public String region;
    public String type;
    public String hardness;
    public String addedBy;
    public String imageUrl;


    public Destination(String title, String description, String region, String type, String hardness, String addedBy, String imageUrl) {
        this.title = title;
        this.description = description;
        this.region = region;
        this.type = type;
        this.hardness = hardness;
        this.addedBy = addedBy;
        this.imageUrl = imageUrl;
    }

    public Destination(){

    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRegion() {
        return region;
    }

    public String getType() {
        return type;
    }

    public String getHardness() {
        return hardness;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
