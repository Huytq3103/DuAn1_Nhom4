/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
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

    public List<TrangThaiOrder> getAllTTO();

    String thanhToan(BigDecimal tongTien, JTextField tienKhachDua, ViewHoaDonResponse vhdr);

    public String tienThua(BigDecimal tongTien, JTextField tienKhachDua);

    public String addHoaDon(ViewHoaDonResponse vhdr);

    public boolean addHDCT(ViewHDCTResponse vhdctr, UUID id);

    public ViewHDCTResponse getOneHDCT(UUID idHD, UUID idCTSP);

    boolean updateHDCT(ViewHDCTResponse vhdctr);

    BigDecimal getTongTienHD(UUID id);

    boolean updateHD(ViewHoaDonResponse vhdr);

    boolean deleteHDCT(ViewHDCTResponse vhdctr);
}
