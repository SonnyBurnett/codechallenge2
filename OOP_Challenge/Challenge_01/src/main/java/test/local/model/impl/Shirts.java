package test.local.model.impl;

import test.local.model.Category;
import test.local.model.Product;

public class Shirts extends Product {
    public Shirts(long productId, String name, String description, int dollarPrice) {
        super(productId, name, description, dollarPrice, Category.SHIRTS);
    }
}
