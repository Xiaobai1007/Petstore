package com.xiaobai.entity;

public class Order {
    private Integer orderId;

    private Integer petId;

    private Integer userId;

    private Integer orderQuantity;

    private String orderShipdate;

    private String orderStatus;

    private byte[] orderComplete;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderShipdate() {
        return orderShipdate;
    }

    public void setOrderShipdate(String orderShipdate) {
        this.orderShipdate = orderShipdate == null ? null : orderShipdate.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public byte[] getOrderComplete() {
        return orderComplete;
    }

    public void setOrderComplete(byte[] orderComplete) {
        this.orderComplete = orderComplete;
    }
}