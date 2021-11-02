package com.example.Picnic.model.rest.external;

import java.util.List;

public class Cart {
    private int total_price;
    private int total_count;
    private List<OrderLineItems> items;
    private int checkout_total_price;
    private int total_savings;
    private int total_deposit;

    public Cart() {
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<OrderLineItems> getItems() {
        return items;
    }

    public void setItems(List<OrderLineItems> items) {
        this.items = items;
    }

    public int getCheckout_total_price() {
        return checkout_total_price;
    }

    public void setCheckout_total_price(int checkout_total_price) {
        this.checkout_total_price = checkout_total_price;
    }

    public int getTotal_savings() {
        return total_savings;
    }

    public void setTotal_savings(int total_savings) {
        this.total_savings = total_savings;
    }

    public int getTotal_deposit() {
        return total_deposit;
    }

    public void setTotal_deposit(int total_deposit) {
        this.total_deposit = total_deposit;
    }

    static public class OrderLineItems{
        private String type;
        private String id;
        private List<Product> items;
        private int display_price;
        private int price;

        public OrderLineItems() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Product> getItems() {
            return items;
        }

        public void setItems(List<Product> items) {
            this.items = items;
        }

        public int getDisplay_price() {
            return display_price;
        }

        public void setDisplay_price(int display_price) {
            this.display_price = display_price;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }


}
