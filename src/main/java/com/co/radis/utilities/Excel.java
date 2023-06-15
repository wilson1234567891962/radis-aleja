/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.radis.utilities;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    private static final Logger logger = Logger.getLogger(Excel.class);

    public static String readFile(Properties pro) {
        String nombre = pro.getProperty(Constant.FILE_NAME);
        StringBuilder data = new StringBuilder();
        try ( FileInputStream file = new FileInputStream(nombre)) {
            XSSFWorkbook worbook = new XSSFWorkbook(file);
            XSSFSheet sheet = worbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            Row row;

            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell;

                while (cellIterator.hasNext()) {

                    cell = cellIterator.next();
                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_STRING:
                            data.append(cell.getRichStringCellValue());
                            data.append("|");

                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue());
                            data.append("|");
                            break;

                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue());
                            data.append("|");

                            break;

                        case Cell.CELL_TYPE_FORMULA:
                            data.append(cell.getCellFormula());
                            data.append("|");
                            break;
                        default:
                            logger.warn("No hay caso para esta columna");
                    }
                }
                data.append("\n");
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return data.toString();
    }
}
