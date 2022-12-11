/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.util;

import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import com.poly.pro_1041.it17322.group4.repository.ChatLieuRepository;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.repository.HangSPRepository;
import com.poly.pro_1041.it17322.group4.repository.KichCoRepository;
import com.poly.pro_1041.it17322.group4.repository.LoaiSPRepository;
import com.poly.pro_1041.it17322.group4.repository.MauSacRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
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

    public ImportExcelCTSP() {
        chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    }
    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    private HangSPRepository hangSPRepository = new HangSPRepository();
    private MauSacRepository mauSacRepository = new MauSacRepository();
    private LoaiSPRepository loaiSPRepository = new LoaiSPRepository();
    private KichCoRepository kichCoRepository = new KichCoRepository();
    private ChatLieuRepository chatLieuRepository = new ChatLieuRepository();

    public void ImportFile(String path) throws FileNotFoundException, IOException {
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
            String ma = String.valueOf(getCellValue(currentRow.getCell(0))).trim();
            String tenSP = String.valueOf(getCellValue(currentRow.getCell(1))).trim();
            String mauSac = String.valueOf(getCellValue(currentRow.getCell(2))).trim();
            String hang = String.valueOf(getCellValue(currentRow.getCell(3))).trim();
            String kichCo = String.valueOf(getCellValue(currentRow.getCell(4))).trim();
            String chatLieu = String.valueOf(getCellValue(currentRow.getCell(5))).trim();
            String loai = String.valueOf(getCellValue(currentRow.getCell(6))).trim();
            String ngayNhap = String.valueOf(getCellValue(currentRow.getCell(7))).trim();
            String soLuongTon = String.valueOf(getCellValue(currentRow.getCell(8))).trim();
            String gia = String.valueOf(getCellValue(currentRow.getCell(9))).trim();
            if (ma.isEmpty() || tenSP.isEmpty() || mauSac.isEmpty() || hang.isEmpty() || kichCo.isEmpty() || chatLieu.isEmpty() || loai.isEmpty() || ngayNhap.isEmpty() || soLuongTon.isEmpty() || gia.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không để trống");
                return;
            }
            if (ma.isEmpty() || tenSP.isEmpty() || mauSac.isEmpty() || hang.isEmpty() || kichCo.isEmpty() || chatLieu.isEmpty() || loai.isEmpty() || ngayNhap.isEmpty() || soLuongTon.isEmpty() || gia.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không để trống");
                return;
            }
            System.out.println(hang);
            Hang hang1 = hangSPRepository.findHangByTen(hang);
            System.out.println(hang1.getTen());
            if (hang1 == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy hãng");
                return;
            }
            ChatLieu chatLieu1 = chatLieuRepository.findChatLieuByTen(chatLieu);
            System.out.println(chatLieu1.getTen());
            if (chatLieu1 == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy chất liệu");
                return;
            }
            Loai loai1 = loaiSPRepository.findLoaiByTen(loai);
            System.out.println(loai1.getTen());
            if (loai1 == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy loại sản phẩm");
                return;
            }
            KichCo kichCo1 = kichCoRepository.findKichCoByTen(kichCo);
            System.out.println(kichCo1.getTen());
            if (kichCo1 == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy loại kích cỡ");
                return;
            }
            MauSac mauSac1 = mauSacRepository.findMauSacByTen(mauSac);
            System.out.println(mauSac1.getTen());
            if (mauSac1 == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy loại màu sắc");
                return;
            }
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham(null, mauSac1, hang1, kichCo1, chatLieu1, loai1, null, "" + chiTietSanPhamRepository.genMaCTSP(), tenSP, (int) Double.parseDouble(soLuongTon), new BigDecimal(gia), ngayNhap, null, null, 1);
            chiTietSanPhamRepository.add(chiTietSanPham);

            JOptionPane.showMessageDialog(null, "Import file excel thành công");
            workbook.close();
        }
    }

    private static Object getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue();
                }
                default -> {
                    return cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            return "";
        }
    }
}
