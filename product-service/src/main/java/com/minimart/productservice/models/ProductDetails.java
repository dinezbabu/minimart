package com.minimart.productservice.models;

import java.util.Date;
import java.util.List;

/**
 * Created by dbabu on 4/29/19.
 */
public class ProductDetails {
    private String productName;
    private String productId;
    private String promotion;
    private String promotionId;
    private double mrpEachWithoutTax;
    private double rpEachWithoutTax;
    private double mmpEachWithoutTax;
    private String CESS;
    private String CGST;
    private String SGST;
    private String HSNId;
    private String barCode;
    private String variant;
    private Integer totalQty;
    private String availableQty;
    private boolean isPromotionApplied;
    private Date productExpiryDate;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public double getMrpEachWithoutTax() {
        return mrpEachWithoutTax;
    }

    public void setMrpEachWithoutTax(double mrpEachWithoutTax) {
        this.mrpEachWithoutTax = mrpEachWithoutTax;
    }

    public double getRpEachWithoutTax() {
        return rpEachWithoutTax;
    }

    public void setRpEachWithoutTax(double rpEachWithoutTax) {
        this.rpEachWithoutTax = rpEachWithoutTax;
    }

    public double getMmpEachWithoutTax() {
        return mmpEachWithoutTax;
    }

    public void setMmpEachWithoutTax(double mmpEachWithoutTax) {
        this.mmpEachWithoutTax = mmpEachWithoutTax;
    }

    public String getCESS() {
        return CESS;
    }

    public void setCESS(String CESS) {
        this.CESS = CESS;
    }

    public String getCGST() {
        return CGST;
    }

    public void setCGST(String CGST) {
        this.CGST = CGST;
    }

    public String getSGST() {
        return SGST;
    }

    public void setSGST(String SGST) {
        this.SGST = SGST;
    }

    public String getHSNId() {
        return HSNId;
    }

    public void setHSNId(String HSNId) {
        this.HSNId = HSNId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public String getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(String availableQty) {
        this.availableQty = availableQty;
    }

    public boolean isPromotionApplied() {
        return isPromotionApplied;
    }

    public void setPromotionApplied(boolean promotionApplied) {
        isPromotionApplied = promotionApplied;
    }

    public Date getProductExpiryDate() {
        return productExpiryDate;
    }

    public void setProductExpiryDate(Date productExpiryDate) {
        this.productExpiryDate = productExpiryDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
