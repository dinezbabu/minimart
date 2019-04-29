package com.minimart.collectionservice.resources;


import com.minimart.collectionservice.models.CollectionProduct;
import com.minimart.collectionservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dbabu on 4/27/19.
 */
@RestController
@RequestMapping("/v1/search")
public class MinimartCollectionresources {

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("?q={searchTerm}")
    public CollectionProduct getListProduct(@PathVariable("searchTerm") String searchTerm)
    {

        //Get all the product after search with out Promotion

        // Pass each Product id to the Promotion to check the Promotion


        //Get all the product with Promotion


        List<String> imgURLS= new ArrayList<>();
        imgURLS.add("/img/ashirvad_912345678_1.png");
        imgURLS.add("/img/ashirvad_912345678_2.png");
        imgURLS.add("/img/ashirvad_912345678_3.png");
//        return Collections.singletonList(
//           new Product("Ashirvad Atta","912345678",430,330,380,20,imgURLS,"",2.00,3.00,4.00,"","123")
//        );
        List<Product> productList = Arrays.asList(
                new Product("Ashirvad Atta1","912345678",430,330,380,20,imgURLS,"",2.00,3.00,4.00,"","123"),
                new Product("Ashirvad Atta2","912345678",430,330,380,20,imgURLS,"",2.00,3.00,4.00,"","123"),
                new Product("Ashirvad Atta3","912345678",430,330,380,20,imgURLS,"",2.00,3.00,4.00,"","123"),
                new Product("Ashirvad Atta4","912345678",430,330,380,20,imgURLS,"",2.00,3.00,4.00,"","123"),
                new Product("Ashirvad Atta5","912345678",430,330,380,20,imgURLS,"",2.00,3.00,4.00,"","123")
        );
        CollectionProduct collectionProduct= new CollectionProduct();
        collectionProduct.setProductList(productList);
        return  collectionProduct;
    }
}
