package com.minimart.productservice.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minimart.productservice.models.Product;
import com.minimart.productservice.models.ProductDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Created by dbabu on 5/13/19.
 */
public class xlutilities {

    private loggerUtil LOGGER = loggerUtil.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    final DataFormatter df = new DataFormatter();
    public static List<ProductDetails> productDetailsList= new ArrayList<>();
    final String PATH="/Users/dbabu/Desktop/MiniMartGrocery.xlsx";
    final String SHEETNAME="ProductDetail";

    public static void main(String args[]) {
        xlutilities xlutilities = new xlutilities();
        xlutilities.GetProductBasedOnBarCode("4234235671");
    }

    public  JSONObject GetProductBasedOnBarCode(String barcode) {
        String jsonStr = null;
        GetAllProducts();
        Optional<ProductDetails> productDetails=productDetailsList.stream().filter(barcodeProduct-> barcodeProduct.getBarCode().equalsIgnoreCase(barcode)).findFirst();
        try {
            ObjectMapper Obj = new ObjectMapper();
            jsonStr = Obj.writeValueAsString(productDetails.get());
            new JSONObject(jsonStr.toString());
            LOGGER.loggerInfo(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Sucessfully Fetched BarCode : "+barcode);
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
    private void GetAllProducts(){
        try {
            if(productDetailsList.size()==0) {
                FileInputStream file = new FileInputStream(new File(PATH));
                Workbook workbookRead = new XSSFWorkbook(file);
                Sheet sheetRead = workbookRead.getSheet(SHEETNAME);
                Iterator<Row> iteratorRow = sheetRead.iterator();
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
                        productDetails.setModifiedDate(currentRow.getCell(17).getDateCellValue());
                        productDetails.setModifiedBy(currentRow.getCell(18).toString());
                        productDetailsList.add(productDetails);
                    }
                }
            }
        }
        catch (FileNotFoundException ex )
        {
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"File Not Found : "+PATH,ex);
        }
        catch (IOException ex)
        {
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"IO Exception",ex);
        }
    }

    private void UpdateProductQuantity(String barcode)
    {
        try{

        }
        catch (Exception ex)
        {
            LOGGER.loggerError(this.getClass().getName(),new Object(){}.getClass().getEnclosingMethod().getName(),"Failed To Update BarCode :"+barcode,ex);
        }
    }


}
