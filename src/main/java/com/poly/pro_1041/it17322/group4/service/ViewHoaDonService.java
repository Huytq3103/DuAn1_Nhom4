/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Huy PC
 */
public interface ViewHoaDonService {

    List<ViewCTSPResponse> getAllSP();

    List<ViewHoaDonResponse> getAllHD();

    List<ViewHDCTResponse> getAllHDCT();

    List<ViewHDCTResponse> getOneHD(UUID id);
}
