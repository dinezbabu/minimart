package com.minimart.productservice.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.minimart.productservice.models.Product;
import com.minimart.productservice.models.ProductDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by dbabu on 5/25/19.
 */
public class Productxlutilities extends Commonxlutilities {
    private loggerUtil LOGGER = loggerUtil.getInstance();
    public static List<Product> productList= new ArrayList<>();
    final String PRODUCTSHEETNAME="Product";

    public static void main(String args[]) {
        Productxlutilities xlutilities = new Productxlutilities();
        xlutilities.InsertProduct(new JSONObject());
        xlutilities.GetProductBasedOnBarCode("9535353373");
    }

    public  JSONObject GetProductBasedOnBarCode(String barcode) {
        String jsonStr = null;
        GetAllProducts();
        Optional<Product> product=productList.stream().filter(barcodeProduct-> barcodeProduct.getBarcode().equalsIgnoreCase(barcode)).findFirst();
        try {
            ObjectMapper Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(product.get());
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
    private void InsertProduct(JSONObject productJSON)
    {
        try {
            productJSON = new JSONObject("{\"barcode\":\"9535353373\",\"productId\":\"PROD30003\",\"promotionId\":\"PROMO40003\",\"createdDate\":{\"minute\":5,\"second\":26,\"hour\":21,\"dayOfYear\":145,\"dayOfWeek\":\"SATURDAY\",\"month\":\"MAY\",\"dayOfMonth\":25,\"nano\":892000000,\"year\":2019,\"monthValue\":5,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"modifiedDate\":{\"minute\":5,\"second\":26,\"hour\":21,\"dayOfYear\":145,\"dayOfWeek\":\"SATURDAY\",\"month\":\"MAY\",\"dayOfMonth\":25,\"nano\":894000000,\"year\":2019,\"monthValue\":5,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"createdBy\":\"Babu\",\"modifiedBy\":\"Babu\",\"productDetails\":null}");
            //productDetailsJSON = new JSONObject("{\"productName\":\"Pepsodent -100g\",\"productId\":\"PROD30005\",\"promotion\":null,\"promotionId\":null,\"mrpEachWithoutTax\":150.0,\"rpEachWithoutTax\":130.0,\"mmpEachWithoutTax\":120.0,\"barCode\":\"423423567\",\"variant\":\"100g\",\"totalQty\":20,\"availableQty\":\"35.0\",\"productExpiryDate\":1546292700000,\"createdDate\":1546292700000,\"modifiedDate\":1546281000000,\"createdBy\":\"Babu\",\"modifiedBy\":\"Babu\",\"cess\":\"TAX2012\",\"sgst\":\"TAX2008\",\"cgst\":\"TAX2007\",\"hsnid\":\"12343.0\",\"promotionApplied\":true}");
            ReadFromXSLFile(PRODUCTSHEETNAME);
            Row currentRow = CURRENTSHEET.createRow(CURRENTSHEET.getLastRowNum()+1);
            ObjectMapper jsontoObject = new ObjectMapper();
            //jsontoObject.registerModule(new JavaTimeModule());
            jsontoObject.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            Product newProduct= jsontoObject.readValue(productJSON.toString(),Product.class);
            if(newProduct!= null)
            {
                currentRow.createCell(0).setCellValue(newProduct.getBarcode());
                currentRow.createCell(1).setCellValue(newProduct.getProductId());
                currentRow.createCell(2).setCellValue(newProduct.getPromotionId());
                currentRow.createCell(3).setCellValue(dateFormat.format(new Date()));
                currentRow.createCell(4).setCellValue(newProduct.getCreatedBy());
                currentRow.createCell(5).setCellValue(dateFormat.format(new Date()));
                currentRow.createCell(6).setCellValue(newProduct.getModifiedBy());
            }
            WriteToXSLFile(PRODUCTSHEETNAME);
            ProductDetailsxlutilities productDetailsxlutilities = new ProductDetailsxlutilities();
            productDetailsxlutilities.InsertProductDetails(productJSON);
            LOGGER.loggerInfo(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Sucessfully Inserted Product ");
        }
        catch (Exception ex){
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Failed To Insert The Product:",ex);
        }
    }

    private void GetAllProducts()
    {
        try {
            ReadFromXSLFile(PRODUCTSHEETNAME);
            if(productList.size() != CURRENTSHEET.getLastRowNum()-1) {
                Iterator<Row> iteratorRow = CURRENTSHEET.iterator();
                while (iteratorRow.hasNext()) {
                    Row currentRow = iteratorRow.next();
                    if (currentRow.getRowNum() != 0) {
                        Product product = new Product();
                        product.setBarcode(df.formatCellValue(currentRow.getCell(0)));
                        product.setProductId(currentRow.getCell(1).toString());
                        product.setPromotionId(df.formatCellValue(currentRow.getCell(2)));
                        product.setCreatedDate(currentRow.getCell(3).getDateCellValue());
                        product.setCreatedBy(currentRow.getCell(4).toString());
                        product.setModifiedDate(currentRow.getCell(5).getDateCellValue());
                        product.setModifiedBy(currentRow.getCell(6).toString());
                        productList.add(product);
                    }
                }
            }

        }
        catch (Exception ex){
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Exception",ex);
        }
    }
}
