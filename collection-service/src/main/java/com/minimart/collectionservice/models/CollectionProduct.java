package com.minimart.collectionservice.models;

import java.util.List;

/**
 * Created by dbabu on 4/29/19.
 */
public class CollectionProduct {
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
