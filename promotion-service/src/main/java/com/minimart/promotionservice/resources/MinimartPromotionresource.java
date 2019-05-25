package com.minimart.promotionservice.resources;


import com.minimart.promotionservice.models.PromotionProduct;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbabu on 4/27/19.
 */
@RestController
@RequestMapping("/v1/promotionproduct")
public class MinimartPromotionresource {

    @RequestMapping("/{productId}")
    public PromotionProduct getProductWithPromotion(@PathVariable("productId") String productId)
    {
        PromotionProduct promotionProduct = null;
        try {
            promotionProduct= new PromotionProduct("PROM912345678","Buy1Get4","912345678", Date.valueOf(LocalDate.parse("2019-12-04")),Date.valueOf(LocalDate.parse("2019-12-05")));
        }
        catch (Exception ex){
            System.out.println("Error"+ex);
        }
        return promotionProduct;
    }


}

