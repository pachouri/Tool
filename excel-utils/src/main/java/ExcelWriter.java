import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 22/12/17.
 */

public class ExcelWriter {

    private static String[] columns = {"Name", "Email", "Date Of Birth", "Salary"};

    private static List<ProductInfo> productinfos =  new ArrayList<>();

   

    public static void main(String[] args) throws IOException, InvalidFormatException {
    	
    	 String csvFile = "C:/Users/shine/Documents/Test.csv";
         String line = "";
         String cvsSplitBy = ",";

         try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

             while ((line = br.readLine()) != null) {

                 // use comma as separator
                 String[] product = line.split(cvsSplitBy);

                 System.out.println("Country [code= " + product[0] + " , name=" + product[1] + "]");
                
                 
                 productinfos.add(new ProductInfo(product[0], Double.parseDouble(product[1]),Integer.parseInt(product[2])) );

             }

         } catch (IOException e) {
             e.printStackTrace();
         }

        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();     // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances for various things like DataFormat,
           Hyperlink, RichTextString etc in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Kinkars");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

       

        // Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(ProductInfo productinfo: productinfos) {
            Row row = sheet.createRow(rowNum++);
            //A
            //row.createCell(0).setCellValue(productinfo.getPrice());
           row.createCell(0).setCellValue("");
            //B
            row.createCell(1).setCellValue(productinfo.getName());
            //C
            row.createCell(2).setCellValue("101");
            //D
            row.createCell(3).setCellValue("");
            
            //E
            row.createCell(4).setCellValue("");
            //F
            row.createCell(5).setCellValue("");
            //G
            row.createCell(6).setCellValue("");
            //H
            row.createCell(7).setCellValue("");
            //I
            row.createCell(8).setCellValue("");
            //J
            row.createCell(9).setCellValue("");
            //K
            row.createCell(10).setCellValue(productinfo.getQuantity());
            //L
            row.createCell(11).setCellValue(productinfo.getName().replaceAll("\\s+",""));
            //M
            row.createCell(12).setCellValue("Patanjali");
            //N
            row.createCell(13).setCellValue("catalog/patanjali/"+productinfo.getName().replaceAll("\\s+","")+".jpg");
            //O
            row.createCell(14).setCellValue("yes");
            //P
            row.createCell(15).setCellValue(productinfo.getPrice());
            //Q
            row.createCell(16).setCellValue("0");
            //R
            row.createCell(17).setCellValue("2018-01-25 04:57:12");
            //S
            row.createCell(18).setCellValue("0000-00-00 00:00:00");
            //T
            row.createCell(19).setCellValue("2018-01-25");
            //U
            row.createCell(20).setCellValue("0.00");
            //V
            row.createCell(21).setCellValue("kg");
            //W
            row.createCell(22).setCellValue("0");
            //X
            row.createCell(23).setCellValue("0");
            //Y
            row.createCell(24).setCellValue("0");
            //Z
            row.createCell(25).setCellValue("cm");
            //AA
            row.createCell(26).setCellValue("true");
            //AB
            row.createCell(27).setCellValue("0");
            //AC
            row.createCell(28).setCellValue(productinfo.getName().replace(" ", "-"));
            //AD
            row.createCell(29).setCellValue(productinfo.getName());
            //AE
            row.createCell(30).setCellValue(productinfo.getName());
            //AF
            row.createCell(31).setCellValue(productinfo.getName());
            //AG
            row.createCell(32).setCellValue(productinfo.getName());
            //AH
            row.createCell(33).setCellValue("6");
            //AI
            row.createCell(34).setCellValue("0");
           
            //AJ
            row.createCell(35).setCellValue("");
            //AK
            row.createCell(36).setCellValue("");
            //AL
            row.createCell(37).setCellValue(productinfo.getName());
            //AM
            row.createCell(38).setCellValue("1");
            //AN
            row.createCell(39).setCellValue("true");
            //AO
            row.createCell(40).setCellValue("1");
            //row.createCell(0).setCellValue(productinfo.getName());

            //row.createCell(1).setCellValue(productinfo.getPrice());

           

            
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }


    // Example to modify an existing excel file
    private static void modifyExistingWorkbook() throws InvalidFormatException, IOException {
        // Obtain a workbook from the excel file
        Workbook workbook = WorkbookFactory.create(new File("existing-spreadsheet.xlsx"));

        // Get Sheet at index 0
        Sheet sheet = workbook.getSheetAt(0);

        // Get Row at index 1
        Row row = sheet.getRow(1);

        // Get the Cell at index 2 from the above row
        Cell cell = row.getCell(2);

        // Create the cell if it doesn't exist
        if (cell == null)
            cell = row.createCell(2);

        // Update the cell's value
        cell.setCellType(CellType.STRING);
        cell.setCellValue("Updated Value");

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("existing-spreadsheet.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }
}



