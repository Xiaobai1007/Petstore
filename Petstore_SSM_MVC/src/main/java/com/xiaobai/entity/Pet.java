package com.xiaobai.entity;

import java.math.BigDecimal;

public class Pet {
    private Integer petId;

    private Integer categoryId;

    private String petName;

    private BigDecimal petPrice;

    private Integer photoId;

    private String petStatus;

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName == null ? null : petName.trim();
    }

    public BigDecimal getPetPrice() {
        return petPrice;
    }

    public void setPetPrice(BigDecimal petPrice) {
        this.petPrice = petPrice;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(String petStatus) {
        this.petStatus = petStatus == null ? null : petStatus.trim();
    }
}