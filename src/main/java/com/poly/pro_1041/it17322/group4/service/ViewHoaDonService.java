/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.swing.JTextField;

/**
 *
 * @author Huy PC
 */
public interface ViewHoaDonService {

    List<ViewCTSPResponse> getAllSP();

    List<ViewHoaDonResponse> getAllHD();

    List<ViewHDCTResponse> getAllHDCT();

    List<ViewHDCTResponse> getOneHD(UUID id);

    List<ViewHDCTResponse> getOneHDVoiHDCT(UUID id);

    List<ViewHoaDonResponse> getOneHDKH(UUID id);

    List<ViewHoaDonResponse> getList(int idTT);

    List<ViewHoaDonResponse> getListByDate(String tuNgay, String denNgay);

    List<TrangThaiOrder> getAllTTO();

    String thanhToan(ViewHoaDonResponse vhdr);

    String tienThua(BigDecimal tongTien, JTextField tienKhachDua, JTextField tienTaiKhoan);

    String addHoaDon(ViewHoaDonResponse vhdr);

    boolean addHDCT(ViewHDCTResponse vhdctr, UUID id);

    ViewHDCTResponse getOneHDCT(UUID idHD, UUID idCTSP);

    boolean updateHDCT(ViewHDCTResponse vhdctr);

    BigDecimal getTongTienHD(UUID id);

    boolean updateHD(ViewHoaDonResponse vhdr);

    boolean deleteHDCT(ViewHDCTResponse vhdctr);

    void taoFilePDF(ViewHoaDonResponse hd, List<ViewHDCTResponse> list, Account a) throws FileNotFoundException;

    ViewHoaDonResponse getOneHDByMa(String ma);

    boolean checkSoLuongTonVoiSoLuong(ViewHDCTResponse vhdctr);

    boolean checkSoLuongGioHangVoiSoLuongSP(ViewHDCTResponse vhdctr);

    boolean updateSoLuongTonKhiThem(ViewHDCTResponse vhdctr);

    boolean updateSoLuongTonKhiXoa(ViewHDCTResponse vhdctr);

    List<ViewKhachHangRepose> getAllKH();

    public List<ViewHoaDonResponse> getAllHDByChuaTT();

    public List<ViewHoaDonResponse> getAllHDByDaTT();

    public List<ViewHoaDonResponse> getAllHDByDangGiao();

    public List<ViewHoaDonResponse> getAllHDByDaGiao();

    List<ViewKhachHangRepose> searchSDT(String SDT);

    int genMaHD();

    String add(ViewKhachHangRepose vkhr);

    ViewCTSPResponse getOneSP(String ma);

    BigDecimal getTongTien(UUID idKH);

    String updateKH(ViewKhachHangRepose vkhr);
}
