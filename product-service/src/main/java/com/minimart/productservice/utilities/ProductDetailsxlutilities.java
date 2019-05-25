package com.minimart.productservice.utilities;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minimart.productservice.models.Product;
import com.minimart.productservice.models.ProductDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.jni.Local;
import org.json.JSONObject;


/**
 * Created by dbabu on 5/13/19.
 */
public class ProductDetailsxlutilities extends Commonxlutilities {

    private loggerUtil LOGGER = loggerUtil.getInstance();
    public static List<ProductDetails> productDetailsList= new ArrayList<>();
    final String PRODUCTDETAILSHEETNAME="ProductDetail";

    public static void main(String args[]) {
        ProductDetailsxlutilities xlutilities = new ProductDetailsxlutilities();
        //xlutilities.InsertProductDetails(new JSONObject());
        xlutilities.GetProductDetailsBasedOnBarCode("9535353373");
    }

    public  JSONObject GetProductDetailsBasedOnBarCode(String barcode) {
        String jsonStr = null;
        GetAllProductDetails();
        Optional<ProductDetails> productDetails=productDetailsList.stream().filter(barcodeProduct-> barcodeProduct.getBarCode().equalsIgnoreCase(barcode)).findFirst();
        try {
            ObjectMapper Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(productDetails.get());
            LOGGER.loggerInfo(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Sucessfully Fetched BarCode : "+barcode);
            return new JSONObject(jsonStr.toString());
        }
        catch (JsonProcessingException ex){
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Failed To Fetch BarCode :"+barcode,ex);
        }
        catch (NoSuchElementException ex)
        {
            LOGGER.loggerError(this.getClass().getName(),new Throwable().getStackTrace()[0].getMethodName(),"Failed To Fetch BarCode :"+barcode,ex);
        }
        return null;
    }
    private void GetAllProductDetails(){
        try {
            ReadFromXSLFile(PRODUCTDETAILSHEETNAME);
            if(productDetailsList.size() != CURRENTSHEET.getLastRowNum()-1) {
                Iterator<Row> iteratorRow = CURRENTSHEET.iterator();
                while (iteratorRow.hasNext()) {
                    Row currentRow = iteratorRow.next();
                    if (currentRow.getRowNum() != 0) {
                        ProductDetails productDetails = new ProductDetails();
                        productDetails.setProductId(currentRow.getCell(0).toString());
                        productDetails.setHSNId(currentRow.getCell(1).toString());
                        productDetails.setBarCode(df.formatCellValue(currentRow.getCell(2)));
                        productDetails.setProductName(currentRow.getCell(3).toString());
                        productDetails.setVariant(currentRow.getCell(4).toString());
                        productDetails.setTotalQty(Integer.parseInt(df.formatCellValue(currentRow.getCell(5))));
                        productDetails.setAvailableQty(currentRow.getCell(6).toString());
                        productDetails.setMrpEachWithoutTax(Double.parseDouble(currentRow.getCell(7).toString()));
                        productDetails.setRpEachWithoutTax(Double.parseDouble(currentRow.getCell(8).toString()));
                        productDetails.setMmpEachWithoutTax(Double.parseDouble(currentRow.getCell(9).toString()));
                        productDetails.setCGST(currentRow.getCell(10).toString());
                        productDetails.setSGST(currentRow.getCell(11).toString());
                        productDetails.setCESS(currentRow.getCell(12).toString());
                        productDetails.setPromotionApplied(Boolean.parseBoolean(currentRow.getCell(13).toString()));
                        productDetails.setProductExpiryDate(currentRow.getCell(14).getDateCellValue());
                        productDetails.setCreatedDate(currentRow.getCell(15).getDateCellValue());
                        productDetails.setCreatedBy(currentRow.getCell(16).toString());
                        productDetails.setModifiedDate(LocalDateTime.parse(currentRow.getCell(17).toString()));
                        productDetails.setModifiedBy(currentRow.getCell(18).toString());
                        productDetailsList.add(productDetails);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Exception",ex);
        }

    }

    private void UpdateProductDetailsQuantity(String barcode, String incrementdecrement, int bulkQty)
    {
        try{
            ReadFromXSLFile(PRODUCTDETAILSHEETNAME);
            for (Row productROW : CURRENTSHEET) {
                if(productROW.getRowNum()!=0 && df.formatCellValue(productROW.getCell(2)).equalsIgnoreCase(barcode.trim())) {
                    if(Integer.parseInt(df.formatCellValue(productROW.getCell(6)))!=0) {
                        switch (incrementdecrement.trim().toUpperCase()){
                            case "INCREMENT":
                                productROW.getCell(6).setCellValue(Integer.parseInt(df.formatCellValue(productROW.getCell(6))) + bulkQty);
                                break;
                            case "DECREMENT":
                                productROW.getCell(6).setCellValue(Integer.parseInt(df.formatCellValue(productROW.getCell(6))) - bulkQty);
                                break;
                        }
                        LOGGER.loggerInfo(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Product QTY Updated Succesfully For BarCode : "+barcode);
                        WORKBOOK.write(new FileOutputStream(new File(PATH)));
                    }
                    else
                    {
                        LOGGER.loggerInfo(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Product Has Zero Inventory For BarCode : "+barcode);
                    }
                }
            }

        }
        catch (Exception ex)
        {
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Failed To Update Quantity For The BarCode :"+barcode,ex);
        }
    }

    private void DeleteProductDetailsBasedOnQuantity(String barcode)
    {
        try {

        }
        catch (Exception ex){
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Failed To Delete For The BarCode :"+barcode,ex);
        }
    }

    public void InsertProductDetails(JSONObject productDetailsJSON)
    {
        try {
            productDetailsJSON = new JSONObject("{\"productName\":\"Pepsodent -100g\",\"productId\":\"PROD30005\",\"promotion\":null,\"promotionId\":null,\"mrpEachWithoutTax\":150.0,\"rpEachWithoutTax\":130.0,\"mmpEachWithoutTax\":120.0,\"barCode\":\"9535353373\",\"variant\":\"100g\",\"totalQty\":20,\"availableQty\":\"35.0\",\"productExpiryDate\":{\"year\":2019,\"monthValue\":5,\"nano\":86000000,\"minute\":5,\"hour\":21,\"second\":27,\"dayOfYear\":145,\"dayOfWeek\":\"SATURDAY\",\"month\":\"MAY\",\"dayOfMonth\":25,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"createdDate\":{\"year\":2019,\"monthValue\":5,\"nano\":86000000,\"minute\":5,\"hour\":21,\"second\":27,\"dayOfYear\":145,\"dayOfWeek\":\"SATURDAY\",\"month\":\"MAY\",\"dayOfMonth\":25,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"modifiedDate\":{\"year\":2019,\"monthValue\":5,\"nano\":86000000,\"minute\":5,\"hour\":21,\"second\":27,\"dayOfYear\":145,\"dayOfWeek\":\"SATURDAY\",\"month\":\"MAY\",\"dayOfMonth\":25,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"createdBy\":\"Babu\",\"modifiedBy\":\"Babu\",\"cgst\":\"TAX2007\",\"sgst\":\"TAX2008\",\"cess\":\"TAX2012\",\"hsnid\":\"12343.0\",\"promotionApplied\":true}");
            ReadFromXSLFile(PRODUCTDETAILSHEETNAME);
            Row currentRow = CURRENTSHEET.createRow(CURRENTSHEET.getLastRowNum()+1);
            ObjectMapper jsontoObject = new ObjectMapper();
            ProductDetails newProductDetails= jsontoObject.readValue(productDetailsJSON.toString(),ProductDetails.class);
            if(newProductDetails!= null)
            {
                currentRow.createCell(0).setCellValue(newProductDetails.getProductId());
                currentRow.createCell(1).setCellValue(newProductDetails.getHSNId());
                currentRow.createCell(2).setCellValue(newProductDetails.getBarCode());
                currentRow.createCell(3).setCellValue(newProductDetails.getProductName());
                currentRow.createCell(4).setCellValue(newProductDetails.getVariant());
                currentRow.createCell(5).setCellValue(newProductDetails.getTotalQty());
                currentRow.createCell(6).setCellValue(newProductDetails.getAvailableQty());
                currentRow.createCell(7).setCellValue(newProductDetails.getMrpEachWithoutTax());
                currentRow.createCell(8).setCellValue(newProductDetails.getRpEachWithoutTax());
                currentRow.createCell(9).setCellValue(newProductDetails.getMmpEachWithoutTax());
                currentRow.createCell(10).setCellValue(newProductDetails.getCGST());
                currentRow.createCell(11).setCellValue(newProductDetails.getSGST());
                currentRow.createCell(12).setCellValue(newProductDetails.getCESS());
                currentRow.createCell(13).setCellValue(newProductDetails.isPromotionApplied());
                currentRow.createCell(14).setCellValue(newProductDetails.getProductExpiryDate().toString());
                currentRow.createCell(15).setCellValue(newProductDetails.getCreatedDate().toString());
                currentRow.createCell(16).setCellValue(newProductDetails.getCreatedBy());
                currentRow.createCell(17).setCellValue(newProductDetails.getModifiedDate().toString());
                currentRow.createCell(18).setCellValue(newProductDetails.getModifiedBy());
            }
            WriteToXSLFile(PRODUCTDETAILSHEETNAME);
            LOGGER.loggerInfo(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Sucessfully Inserted Product ");
        }
        catch (Exception ex){
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Failed To Insert The Product:",ex);
        }
    }
}
