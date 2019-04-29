package com.minimart.collectionservice.models;

import java.util.List;

/**
 * Created by dbabu on 4/29/19.
 */
public class Product {

    private String productName;
    private String productId;
    private String promotion;
    private String promotionId;
    private double mrp;
    private double rp;
    private double mmp;
    private int threshold;
    private List<String> imgURLs;
    private String productDescription;
    private double GST;
    private double CGST;
    private double SGST;


    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName=productName;
    }

    public String getproductId() {
        return productId;
    }

    public void setproductId(String productId) {
        this.productId=productId;
    }

    public String getpromotion() {
        return promotion;
    }

    public void setpromotion(String promotion) {
        this.promotion=promotion;
    }

    public double getmrp() {
        return mrp;
    }

    public void setmrp(double mrp) {
        this.mrp=mrp;
    }

    public double getrp() {
        return rp;
    }

    public void setrp(double rp) {
        this.rp=rp;
    }

    public double getmmp() {
        return mmp;
    }

    public void setmmp(double mmp) {
        this.mmp=mmp;
    }

    public int getthreshold() {
        return threshold;
    }

    public void sethreshold(int threshold) {
        this.threshold=threshold;
    }

    public List<String> getimgURLs() {
        return imgURLs;
    }


    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public double getGST() {
        return GST;
    }

    public void setGST(double GST) {
        this.GST = GST;
    }

    public double getCGST() {
        return CGST;
    }

    public void setCGST(double CGST) {
        this.CGST = CGST;
    }

    public double getSGST() {
        return SGST;
    }

    public void setSGST(double SGST) {
        this.SGST = SGST;
    }

    public void setimgURL(String[] imgURLs) {
        for ( String imgURL:imgURLs) {
            this.imgURLs.add(imgURL);
        }
    }

    public Product(String productName, String productId, double mrp, double rp, double mmp, int threshold, List<String> imgURLs, String productDescription, double GST, double CGST, double SGST, String promotion, String promotionId) {
        this.productName = productName;
        this.productId = productId;
        this.promotion = promotion;
        this.promotionId = promotionId;
        this.mrp = mrp;
        this.rp = rp;
        this.mmp = mmp;
        this.threshold = threshold;
        this.imgURLs = imgURLs;
        this.productDescription = productDescription;
        this.GST = GST;
        this.CGST = CGST;
        this.SGST = SGST;
    }
}
