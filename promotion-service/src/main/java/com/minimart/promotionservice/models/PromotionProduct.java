package com.minimart.promotionservice.models;

import java.util.Date;

/**
 * Created by dbabu on 4/29/19.
 */
public class PromotionProduct {
    private String promotionId;
    private String promotionName;
    private String productId;
    private Date promotionStartDate;
    private Date promotionEndDate;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getPromotionStartDate() {
        return promotionStartDate;
    }

    public void setPromotionStartDate(Date promotionStartDate) {
        this.promotionStartDate = promotionStartDate;
    }

    public Date getPromotionEndDate() {
        return promotionEndDate;
    }

    public void setPromotionEndDate(Date promotionEndDate) {
        this.promotionEndDate = promotionEndDate;
    }

    public PromotionProduct(String promotionId, String promotionName, String productId, Date promotionStartDate, Date promotionEndDate) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.productId = productId;
        this.promotionStartDate = promotionStartDate;
        this.promotionEndDate = promotionEndDate;
    }
}
