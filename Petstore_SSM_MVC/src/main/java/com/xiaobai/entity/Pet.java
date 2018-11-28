package com.xiaobai.entity;

import java.math.BigDecimal;

public class Pet {
    private Integer petId;

    private Category categoryInfo;

    private String petName;

    private BigDecimal petPrice;

    private String petPhoto;

    private String petTag;

    private String petStatus;

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Category getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(Category categoryInfo) {
        this.categoryInfo = categoryInfo;
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

    public String getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(String petPhoto) {
        this.petPhoto = petPhoto == null ? null : petPhoto.trim();
    }

    public String getPetTag() {
        return petTag;
    }

    public void setPetTag(String petTag) {
        this.petTag = petTag == null ? null : petTag.trim();
    }

    public String getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(String petStatus) {
        this.petStatus = petStatus == null ? null : petStatus.trim();
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", categoryInfo=" + categoryInfo +
                ", petName='" + petName + '\'' +
                ", petPrice=" + petPrice +
                ", petPhoto='" + petPhoto + '\'' +
                ", petTag='" + petTag + '\'' +
                ", petStatus='" + petStatus + '\'' +
                '}';
    }

}