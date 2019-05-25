package com.minimart.productservice.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by dbabu on 5/25/19.
 */
public class Commonxlutilities {

    final String PATH="/Users/dbabu/Desktop/MiniMartGrocery.xlsx";
    Workbook WORKBOOK=null;
    Sheet CURRENTSHEET= null;
    private loggerUtil LOGGER = loggerUtil.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    final DataFormatter df = new DataFormatter();
    CreationHelper CREATEHELPER = null;
    CellStyle DATECELLSTYLE = null;


    public void ReadFromXSLFile(String sheetName)
    {
        try {
            FileInputStream file = new FileInputStream(new File(PATH));
            WORKBOOK = new XSSFWorkbook(file);
            CURRENTSHEET = WORKBOOK.getSheet(sheetName);
            CREATEHELPER=WORKBOOK.getCreationHelper();
            DATECELLSTYLE=WORKBOOK.createCellStyle();
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
    public void WriteToXSLFile(String sheetName)
    {
        try {
            FileOutputStream file = new FileOutputStream(new File(PATH));
            WORKBOOK.write(file);
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
}
