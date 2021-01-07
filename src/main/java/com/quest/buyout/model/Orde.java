package com.quest.buyout.model;

public class Orde {
    private CustomerShow customerShow;
    private Order order;
    private AddAddress addAddress;
    private ProductShow productShow;

    public Orde() {}
    public Orde(CustomerShow customerShow,
                Order order,
                AddAddress addAddress,
                ProductShow productShow) {
        this.customerShow = customerShow;
        this.order = order;
        this.addAddress = addAddress;
        this.productShow = productShow;
    }

    public CustomerShow getCustomerShow() {
        return customerShow;
    }

    public void setCustomerShow(CustomerShow customerShow) {
        this.customerShow = customerShow;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public AddAddress getAddAddress() {
        return addAddress;
    }

    public void setAddAddress(AddAddress addAddress) {
        this.addAddress = addAddress;
    }

    public ProductShow getProductShow() {
        return productShow;
    }

    public void setProductShow(ProductShow productShow) {
        this.productShow = productShow;
    }

    @Override
    public String toString() {
        return "Orde{" +
                "customerShow=" + customerShow +
                ", order=" + order +
                ", addAddress=" + addAddress +
                ", productShow=" + productShow +
                '}';
    }
}
