package com.example.Picnic.model.rest.external;

import java.util.List;

public class Product {
    private String type;
    private String id;
    private String name;
    private List<String> image_ids;
    private String image_id;
    private String unit_quantity;
    private String unit_quantity_sub;
    private int display_price;
    private int price;
    private List<Decorator> decorators;

    public Product() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(List<String> image_ids) {
        this.image_ids = image_ids;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getUnit_quantity() {
        return unit_quantity;
    }

    public void setUnit_quantity(String unit_quantity) {
        this.unit_quantity = unit_quantity;
    }

    public String getUnit_quantity_sub() {
        return unit_quantity_sub;
    }

    public void setUnit_quantity_sub(String unit_quantity_sub) {
        this.unit_quantity_sub = unit_quantity_sub;
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

    public List<Decorator> getDecorators() {
        return decorators;
    }

    public void setDecorators(List<Decorator> decorators) {
        this.decorators = decorators;
    }

    static public class Decorator {
        private String type;
        private int quantity;
        private String text;
        private int display_price;
        private String unit_quantity_text;

        public Decorator() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getDisplay_price() {
            return display_price;
        }

        public void setDisplay_price(int display_price) {
            this.display_price = display_price;
        }

        public String getUnit_quantity_text() {
            return unit_quantity_text;
        }

        public void setUnit_quantity_text(String unit_quantity_text) {
            this.unit_quantity_text = unit_quantity_text;
        }
    }
}
