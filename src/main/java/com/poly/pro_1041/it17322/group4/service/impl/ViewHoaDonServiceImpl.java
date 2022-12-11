/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import com.poly.pro_1041.it17322.group4.repository.AccountRepository;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonChiTietRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonRepository;
import com.poly.pro_1041.it17322.group4.repository.KhachHangRepository;
import com.poly.pro_1041.it17322.group4.repository.TrangThaiOrderRepository;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import com.poly.pro_1041.it17322.group4.service.ViewHoaDonService;
import com.poly.pro_1041.it17322.group4.view.ViewSanPham;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JTextField;

/**
 *
 * @author Huy PC
 */
public class ViewHoaDonServiceImpl implements ViewHoaDonService {

    private ChiTietSanPhamRepository ctspr = new ChiTietSanPhamRepository();
    private HoaDonRepository hdr = new HoaDonRepository();
    private HoaDonChiTietRepository hdctr = new HoaDonChiTietRepository();
    private TrangThaiOrderRepository ttor = new TrangThaiOrderRepository();
    private AccountRepository ar = new AccountRepository();
    private KhachHangRepository khr = new KhachHangRepository();
    private String regexInt = "[0-9]+";

    @Override
    public List<ViewCTSPResponse> getAllSP() {
        List<ViewCTSPResponse> list = new ArrayList<>();
        for (ChiTietSanPham ctsp : ctspr.getAll()) {
            list.add(new ViewCTSPResponse(ctsp));
        }
        return list;
    }

    @Override
    public List<ViewHoaDonResponse> getAllHD() {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getAllOrderByNgayTao()) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public List<ViewHoaDonResponse> getAllHDByChuaTT() {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getAllByChuaTT()) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public List<ViewHoaDonResponse> getAllHDByDaTT() {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getAllByDaTT()) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public List<ViewHoaDonResponse> getAllHDByDangGiao() {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getAllByDangGiao()) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public List<ViewHoaDonResponse> getAllHDByDaGiao() {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getAllByDaGiao()) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public List<ViewHDCTResponse> getAllHDCT() {
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getAll()) {
            list.add(new ViewHDCTResponse(hdct));
        }
        return list;
    }

    @Override
    public List<ViewHDCTResponse> getOneHD(UUID id) {
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getOneHD(id)) {
            list.add(new ViewHDCTResponse(hdct));
        }
        return list;
    }

    @Override
    public ViewHoaDonResponse getOneHDByMa(String ma) {
        return new ViewHoaDonResponse(hdr.getOne(ma));
    }

    @Override
    public List<ViewHoaDonResponse> getList(int idTT) {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getList(idTT)) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public List<TrangThaiOrder> getAllTTO() {
        return ttor.getAll();
    }

    @Override
    public String thanhToan(ViewHoaDonResponse vhdr) {
        if (hdr.update(new HoaDon(vhdr.getId(), vhdr.getAccount(), vhdr.getKhachHang(), new TrangThaiOrder(1, "TTO1", "Đã TT"), vhdr.getMa(), vhdr.getNgaoTao(), vhdr.getNgayThanhToan(), null, null, vhdr.getTongTien()))) {
            return "Thanh toán thành công";
        } else {
            return "Thanh toán thất bại";
        }
    }

    @Override
    public String tienThua(BigDecimal tongTien, JTextField tienKhachDua, JTextField tienTaiKhoan) {
        if (tienKhachDua.getText().trim() == null && tienTaiKhoan.getText().trim() == null) {
            return "0";
        } else if (!tienKhachDua.getText().trim().matches(regexInt) && !tienKhachDua.getText().trim().matches(regexInt)) {
            return "0";
        } else if (tienKhachDua.getText().trim().isEmpty()) {
            return String.valueOf(Double.valueOf(0.0 + Double.valueOf(tienTaiKhoan.getText()) - Double.valueOf(String.valueOf(tongTien))));
        } else if (tienTaiKhoan.getText().trim().isEmpty()) {
            return String.valueOf(Double.valueOf(tienKhachDua.getText()) + 0.0 - Double.valueOf(String.valueOf(tongTien)));
        } else {
            return String.valueOf(Double.valueOf(tienKhachDua.getText()) + Double.valueOf(tienTaiKhoan.getText()) - Double.valueOf(String.valueOf(tongTien)));
        }
    }

    @Override
    public String addHoaDon(ViewHoaDonResponse vhdr) {
        if (hdr.add(new HoaDon(null, vhdr.getAccount(), vhdr.getKhachHang(), vhdr.getTto(), vhdr.getMa(), vhdr.getNgaoTao(), null, null, null, vhdr.getTongTien()))) {
            return "Add thành công";
        } else {
            return "Add thất bại";
        }
    }

    @Override
    public boolean addHDCT(ViewHDCTResponse vhdctr, UUID id) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setId(vhdctr.getCtsp().getId());
        HoaDon hd = new HoaDon();
        hd.setId(id);
        HoaDonChiTiet hdct = new HoaDonChiTiet(ctsp, hd, vhdctr.getTen(), vhdctr.getMauSac(), vhdctr.getHang(), vhdctr.getLoai(), vhdctr.getKichCo(), vhdctr.getChatLieu(), vhdctr.getSoLuong(), vhdctr.getGia());
        if (hdctr.add(hdct)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public ViewHDCTResponse getOneHDCT(UUID idHD, UUID idCTSP) {
        return new ViewHDCTResponse(hdctr.getOneHDCT(idHD, idCTSP));
    }

    @Override
    public boolean updateHDCT(ViewHDCTResponse vhdctr) {
        if (hdctr.update(new HoaDonChiTiet(vhdctr.getCtsp(), vhdctr.getHd(), vhdctr.getTen(), vhdctr.getMauSac(), vhdctr.getHang(), vhdctr.getLoai(), vhdctr.getKichCo(), vhdctr.getChatLieu(), vhdctr.getSoLuong(), vhdctr.getGia()))) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public BigDecimal getTongTienHD(UUID id) {
        Double tongTien = 0.0;
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getOneHD(id)) {
            tongTien += Double.valueOf(String.valueOf(hdct.getDonGia())) * hdct.getSoLuong();
        }
        return BigDecimal.valueOf(tongTien);
    }

    @Override
    public boolean updateHD(ViewHoaDonResponse vhdr) {
        if (hdr.update(new HoaDon(vhdr.getId(), vhdr.getAccount(), vhdr.getKhachHang(), vhdr.getTto(), vhdr.getMa(), vhdr.getNgaoTao(), vhdr.getNgayThanhToan(), vhdr.getNgayShip(), vhdr.getNgayNhan(), vhdr.getTongTien()))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteHDCT(ViewHDCTResponse vhdctr) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setHoaDon(vhdctr.getHd());
        hdct.setChiTietSanPham(vhdctr.getCtsp());
        if (hdctr.delete(hdct)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkSoLuongTonVoiSoLuong(ViewHDCTResponse vhdctr) {
        boolean check = true;
        int SoluongTon = ctspr.getOne(vhdctr.getCtsp().getId()).getSoLuongTon();
        if (vhdctr.getSoLuong() > SoluongTon) {
            check = false;
        }
        return check;
    }

    @Override
    public boolean updateSoLuongTonKhiThem(ViewHDCTResponse vhdctr) {
        boolean check = true;
        ChiTietSanPham ctsp = ctspr.getOneUpdateHoaDon(vhdctr.getCtsp().getId());
        int soLuongMoi = ctsp.getSoLuongTon() - vhdctr.getSoLuong();
        ctsp.setSoLuongTon(soLuongMoi);
        if (ctspr.updateTableHD(ctsp)) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    @Override
    public boolean updateSoLuongTonKhiXoa(ViewHDCTResponse vhdctr) {
        boolean check = true;
        ChiTietSanPham ctsp = ctspr.getOneUpdateHoaDon(vhdctr.getCtsp().getId());
        int soLuongMoi = ctsp.getSoLuongTon() + vhdctr.getSoLuong();
        ctsp.setSoLuongTon(soLuongMoi);
        if (ctspr.updateTableHD(ctsp)) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    @Override
    public List<ViewKhachHangRepose> getAllKH() {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (KhachHang kh : khr.getAll()) {
            list.add(new ViewKhachHangRepose(kh));
        }
        return list;
    }

    @Override
    public void taoFilePDF(ViewHoaDonResponse hd, List<ViewHDCTResponse> list, Account a) throws FileNotFoundException {
        Date d = new Date();
        int day = d.getDate();
        int month = d.getMonth() + 1;
        int year = d.getYear() + 1900;
        String ngayTao = month + "/" + day + "/" + year;
        String path = "HoaDon.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);
        float fourCol = 140f;
        float threeCol = 190f;
        float twoCol = 285f;
        float twoCol150 = twoCol + 150f;
        float twoColumnWidth[] = {twoCol150, twoCol};
        float fourColumnWidth[] = {fourCol + 100f, fourCol - 100f, fourCol, fourCol};
        float fullWidth[] = {threeCol * 3};
        Paragraph oneSp = new Paragraph("\n");

        Table table = new Table(twoColumnWidth);
        table.addCell(new Cell().add("Shelby Company").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
        Table nestedTable = new Table(new float[]{twoCol / 2, twoCol / 2});
        nestedTable.addCell(getHeaderTextCell("Created Date"));
        nestedTable.addCell(getHeaderTextCellValue(ngayTao));
        nestedTable.addCell(getHeaderTextCell("Created by"));
        nestedTable.addCell(getHeaderTextCellValue(a.getHoTen()));
        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
        Border gb = new SolidBorder(Color.GRAY, 0.5f);
        Table divider = new Table(fullWidth);
        divider.setBorder(gb);

        document.add(table);
        document.add(oneSp);
        document.add(divider);
        document.add(oneSp);

        Table twoColTable = new Table(twoColumnWidth);
        twoColTable.addCell(getBillingAndShippingCell("Billing"));
        twoColTable.addCell(getBillingAndShippingCell("Shipping"));
        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2 = new Table(twoColumnWidth);
        twoColTable2.addCell(getCell10fLeft("Company name", true));
        twoColTable2.addCell(getCell10fLeft("Buyer", true));
        twoColTable2.addCell(getCell10fLeft("Shelby Company", false));
        twoColTable2.addCell(getCell10fLeft(hd.getMa().trim() == null ? hd.getKhachHang() == null ? " " : hd.getKhachHang().getHoTen() : " ", false));
        document.add(twoColTable2);

        Table twoColTable3 = new Table(twoColumnWidth);
        twoColTable3.addCell(getCell10fLeft("Seller", true));
        twoColTable3.addCell(getCell10fLeft("Ship date", true));
        twoColTable3.addCell(getCell10fLeft(hd.getAccount().getHoTen(), false));
        twoColTable3.addCell(getCell10fLeft(hd.getNgayShip() != null ? hd.getNgayShip().toString() : " ", false));
        document.add(twoColTable3);

        Table twoColTable4 = new Table(twoColumnWidth);
        twoColTable4.addCell(getCell10fLeft("Address", true));
        twoColTable4.addCell(getCell10fLeft("Address", true));
        twoColTable4.addCell(getCell10fLeft("Lang son", false));
        twoColTable4.addCell(getCell10fLeft(hd.getKhachHang() != null ? hd.getKhachHang().getDiaChi() : " ", false));
        document.add(twoColTable4);

        Table twoColTable5 = new Table(twoColumnWidth);
        twoColTable5.addCell(getCell10fLeft("SDT", true));
        twoColTable5.addCell(getCell10fLeft("SDT", true));
        twoColTable5.addCell(getCell10fLeft(a.getSdt(), false));
        twoColTable5.addCell(getCell10fLeft(hd.getKhachHang() == null ? " " : hd.getKhachHang().getSdt(), false));
        document.add(twoColTable5.setMarginBottom(10f));
        Table tableDivider2 = new Table(fullWidth);
        Border dgb = new DashedBorder(Color.GRAY, 0.5f);
        document.add(tableDivider2.setBorder(dgb));
        Paragraph productPra = new Paragraph("Product").setBold().setFontSize(20f).setMarginTop(20f).setMarginBottom(20f);
        document.add(productPra);

        Table fourColTable = new Table(fourColumnWidth);
        fourColTable.setBackgroundColor(Color.BLACK, 0.7f);
        fourColTable.addCell(new Cell().add("Product").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        fourColTable.addCell(new Cell().add("Quantity").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        fourColTable.addCell(new Cell().add("Product Price").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        fourColTable.addCell(new Cell().add("Price").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        document.add(fourColTable);

        for (ViewHDCTResponse vhdctr : list) {
            Table fourColTable2 = new Table(fourColumnWidth);
            double tongTien = Double.valueOf(String.valueOf(vhdctr.getGia())) * vhdctr.getSoLuong();
            String sanPham = vhdctr.getTen() + " " + vhdctr.getMauSac() + " " + vhdctr.getKichCo() + " " + vhdctr.getChatLieu() + " " + vhdctr.getLoai() + " " + vhdctr.getHang();
            fourColTable2.addCell(new Cell().add(sanPham).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER).setMarginLeft(15f));
            fourColTable2.addCell(new Cell().add(String.valueOf(vhdctr.getSoLuong())).setFontColor(Color.BLACK).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            fourColTable2.addCell(new Cell().add(String.valueOf(vhdctr.getGia())).setFontColor(Color.BLACK).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            fourColTable2.addCell(new Cell().add(String.valueOf(tongTien)).setFontColor(Color.BLACK).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            document.add(fourColTable2.setMarginBottom(5f));
        }

        float oneTwo[] = {threeCol + 125f, threeCol * 2};
        Table fourColTable3 = new Table(oneTwo);
        fourColTable3.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER).setMarginLeft(10f));
        fourColTable3.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
        fourColTable3.addCell(new Cell().add("total").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        fourColTable3.addCell(new Cell().add(String.valueOf(hd.getTongTien())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph("\n"));
        document.add(tableDivider2.setBorder(new SolidBorder(Color.GRAY, 1f)).setMarginLeft(200f));
        document.add(fourColTable3);

        document.close();

    }

    static Cell getHeaderTextCell(String textValue) {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingAndShippingCell(String textValue) {
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String textValue, boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;
    }

    @Override
    public List<ViewHoaDonResponse> getListByDate(String tuNgay, String denNgay) {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getListByDate(tuNgay, denNgay)) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public List<ViewHoaDonResponse> getOneHDKH(UUID id) {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getOneHDKH(id)) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public boolean checkSoLuongGioHangVoiSoLuongSP(ViewHDCTResponse vhdctr) {
        boolean check = true;
        int SoluongTon = ctspr.getOne(vhdctr.getCtsp().getId()).getSoLuongTon();
        if (vhdctr.getSoLuong() < SoluongTon) {
            check = false;
        }
        return check;
    }

    @Override
    public List<ViewHDCTResponse> getOneHDVoiHDCT(UUID id) {
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getOneHDVoiHDCT(id)) {
            list.add(new ViewHDCTResponse(hdct));
        }
        return list;
    }

    public List<ViewKhachHangRepose> searchSDT(String SDT) {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (KhachHang kh : khr.getAll()) {
            if (kh.getSdt().contains(SDT)) {
                list.add(new ViewKhachHangRepose(kh));
            }
        }
        return list;
    }

    @Override
    public int genMaHD() {
        return khr.genMaKH();
    }

    public String add(ViewKhachHangRepose vkhr) {
        if (vkhr.getHoTen().trim().isEmpty()) {
            return "Họ tên đang trống";
        } else if (!vkhr.getHoTen().matches("[a-z A-Z]+")) {
            return "Họ tên là chữ";
        } else if (vkhr.getSdt().trim().isEmpty()) {
            return "Sdt đang trống";
        } else if (!vkhr.getSdt().matches("[0-9]+")) {
            return "Sdt là số";
        } else if (!vkhr.getSdt().startsWith("0")) {
            return "Sdt bắt đầu bằng 0";
        } else if (vkhr.getSdt().length() != 10) {
            return "Sdt có 10 chữ số";
        } else if (vkhr.getEmail().trim().isEmpty()) {
            return "Email đang trống";
        } else if (!vkhr.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return "Email không đúng định dạng";
        } else if (vkhr.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ đang trống";
        } else {

            KhachHang kh = new KhachHang(vkhr.getId(), vkhr.getMa(), vkhr.getHoTen(), vkhr.getNgaySinh(), vkhr.isGioiTinh(), vkhr.getSdt(), vkhr.getDiaChi(), vkhr.getEmail(), vkhr.getNgayTao(), vkhr.getNguoiTao(), vkhr.getNguoiChinhSua(), vkhr.getNgayChinhSua(), vkhr.getDiem());

            boolean add = khr.add(kh);
            if (add) {
                return "Add thanh cong";
            } else {
                return "Add that bai";
            }
        }
    }

    public ViewCTSPResponse getOneSP(String ma) {
        ViewCTSPResponse vctspr = new ViewCTSPResponse(ctspr.getOneMa(ma));
        return vctspr;
    }

    public BigDecimal getTongTien(UUID idKH) {
        return hdr.getTongTien(idKH);
    }

    public String updateKH(ViewKhachHangRepose vkhr) {
        KhachHang kh = new KhachHang(vkhr.getId(), vkhr.getMa(), vkhr.getHoTen(), vkhr.getNgaySinh(), vkhr.isGioiTinh(), vkhr.getSdt(), vkhr.getDiaChi(), vkhr.getEmail(), vkhr.getNgayTao(), vkhr.getNguoiTao(), vkhr.getNguoiChinhSua(), vkhr.getNgayChinhSua(), vkhr.getDiem());
            boolean update = khr.update(kh);
            if (update) {
                return "Update thanh cong";
            } else {
                return "Update that bai";
            }
    }

}
