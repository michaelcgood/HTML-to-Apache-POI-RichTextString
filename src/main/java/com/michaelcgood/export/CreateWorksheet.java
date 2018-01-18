package com.michaelcgood.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CreateWorksheet {

    @Autowired
    HTMLtoExcel htmlToExcel;
    
    private FileOutputStream fileOut;
    

    @Cacheable("createworksheet")
    public void createWorkSheet(Workbook wb, String content, String tabName) {
        StringBuilder sbFileName = new StringBuilder();
        sbFileName.append("/Users/mike/javaSTS/michaelcgood-apache-poi-richtext/");
        sbFileName.append("myfile.xlsx");
        String fileMacTest = sbFileName.toString();
        try {
            this.fileOut = new FileOutputStream(fileMacTest);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateWorksheet.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        Sheet sheet = wb.createSheet(tabName); // Create new sheet w/ Tab name

        sheet.setZoom(85); // Set sheet zoom: 85%
        

        // description rich text
        RichTextString contentRich = null;
        if (content != null) {
            contentRich = htmlToExcel.fromHtmlToCellValue(content, wb);
        }


        // begin insertion of values into cells
        Row dataRow = sheet.createRow(0);
        Cell A = dataRow.createCell(0); // Row Number
        A.setCellValue(contentRich);
        sheet.autoSizeColumn(0);
        
        
        try {
            /////////////////////////////////
            // Write the output to a file
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateWorksheet.class.getName())
                .log(Level.SEVERE, null, ex);
        }


    }

}
