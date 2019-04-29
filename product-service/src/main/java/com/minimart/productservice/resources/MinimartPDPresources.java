package com.minimart.productservice.resources;

import com.minimart.productservice.models.Product;
import com.minimart.productservice.models.PromotionProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbabu on 4/27/19.
 */
@RestController
@RequestMapping("/v1/product")
public class MinimartPDPresources {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webclientBuilder;

    @RequestMapping("/{productId}")
    public Product getProduct(@PathVariable ("productId") String productId)
    {
        PromotionProduct promotionProduct = restTemplate.getForObject("http://minimart-Promotion/v1/promotionproduct/"+productId, PromotionProduct.class);

//        PromotionProduct promotionProduct = webclientBuilder.build()
//                        .get()
//                        .uri("http://localhost:9997/v1/promotionproduct/"+productId)
//                        .retrieve()
//                        .bodyToMono(PromotionProduct.class)
//                        .block();

        List<String> imgURLS= new ArrayList<>();
        imgURLS.add("/img/ashirvad_912345678_1.png");
        imgURLS.add("/img/ashirvad_912345678_2.png");
        imgURLS.add("/img/ashirvad_912345678_3.png");
        return new Product("Ashirvad Atta","912345678",430.00,330.00,380,20,imgURLS,"",2.00,3.00,4.00,promotionProduct.getPromotionName(),promotionProduct.getPromotionId());
    }
}