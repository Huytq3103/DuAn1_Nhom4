/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.util;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Acer
 */
public class ImportExcelCTSP {
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    
    public ImportExcelCTSP() {
        chiTietSanPhamRepository=new ChiTietSanPhamRepository();
    }
    public void ImportFile(String path) throws FileNotFoundException, IOException {
          List<ChiTietSanPham> listctsp = new ArrayList<>();
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            int mactsp = chiTietSanPhamRepository.genMaCTSP();
            while (iterator.hasNext()) {            
             Row currentRow = iterator.next();
//              String ma=String.valueOf(getCellVal);
        }
    }
}
