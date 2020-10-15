package test.local.model.impl;

import test.local.model.Product;

public class InvalidProduct extends Product {
    public InvalidProduct() {
        super(-1, "", "", 0, null);
    }
}
