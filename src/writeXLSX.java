
/**
 * File: writeXLSX.java
 * Description: This program writes to a excel file (.xlsx). It requires a filepath (taken as a string) for its
 *              constructor. This program also requires APACHE POI dependencies download link provided below:
 *              https://poi.apache.org/download.html
 * IMPORTANT!   File: poi-bin-4.1.2-20200217.zip (binaries)
 *
 *              * User will need to configure project structure to include APACHE POI dependencies during compile time
 *              by adding all .jar files from the zipped folder.
 *
 * Created by:  Christopher K. Leung
 *              October 4, 2020
 *
 */

// import org.apache.poi.xssf.usermodel.XSSFCell;
// import org.apache.poi.xssf.usermodel.XSSFRow;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import java.io.*;
//
// public class writeXLSX {
//     private static String fileName;
//     private static XSSFWorkbook wb;
//     private static XSSFSheet sheet;
//     private static XSSFRow row;
//     private static XSSFCell cell;
//
//     //constructor requires output file path (string)
//     public writeXLSX(String s) throws IOException {
//         this.fileName = s;
//         wb = new XSSFWorkbook();
//         sheet = wb.createSheet("Sorting Algorithm Runtimes");
//     }
//
//     /**
//      * writeSize needs to be called first since the write method depends on writeSize to create the rows in the excel file
//      * This method will set the value in the excel sheet to the number of elements in the array
//      * @param rowNum - row number to access
//      * @param columnNum - column number to access
//      * @param size - set a cell's value to the size of elements
//      */
//     public void writeSize(int rowNum, int columnNum, int size){
//         if(sheet.getRow(rowNum) == null)
//             row = sheet.createRow(rowNum);
//         row = sheet.getRow(rowNum);
//         cell = row.createCell(columnNum);
//         cell.setCellValue(size);
//     }
//
//     /**
//      * This write method depends on writeSize as the rows need to be created before we can call .getRow(int).
//      * This method will write the algorithm times in selected cell
//      * @param rowNum - row number to access
//      * @param columnNum - column number to create a cell in specified row
//      * @param algorithmTime - writes a long integer to excel cell
//      */
//     public void write(int rowNum, int columnNum, long algorithmTime){
//         row = sheet.getRow(rowNum);
//         cell = row.createCell(columnNum);
//         cell.setCellValue(algorithmTime);
//     }
//
//     /**
//      * close method will need to be called at the end of procedure to write all data to excel file.
//      * If close method isn't called, excel file will not be created.
//      * @throws IOException - error when writing to excel file
//      */
//     public void close() throws IOException {
//         FileOutputStream out = new FileOutputStream(fileName);
//         wb.write(out);
//
//         out.close();
//         wb.close();
//     }
// }