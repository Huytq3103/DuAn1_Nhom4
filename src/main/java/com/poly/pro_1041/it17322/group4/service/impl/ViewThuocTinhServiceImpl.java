/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import com.poly.pro_1041.it17322.group4.repository.ChatLieuRepository;
import com.poly.pro_1041.it17322.group4.repository.HangSPRepository;
import com.poly.pro_1041.it17322.group4.repository.KichCoRepository;
import com.poly.pro_1041.it17322.group4.repository.LoaiSPRepository;
import com.poly.pro_1041.it17322.group4.repository.MauSacRepository;
import com.poly.pro_1041.it17322.group4.service.ViewThuocTinhService;
import java.util.List;

/**
 *
 * @author Acer
 */
public class ViewThuocTinhServiceImpl implements ViewThuocTinhService {


    private MauSacRepository msr = new MauSacRepository();
    private LoaiSPRepository lspr = new LoaiSPRepository();
    private KichCoRepository kcr = new KichCoRepository();
    private HangSPRepository hspr = new HangSPRepository();
    private ChatLieuRepository clr = new ChatLieuRepository();


    @Override
    public List<MauSac> getAllMauSac() {
        return msr.getAll();
    }

    @Override
    public List<Loai> getAllLoai() {
        return lspr.getAll();
    }

    @Override
    public List<KichCo> getAllKichCo() {
        return kcr.getAll();
    }

    @Override
    public List<Hang> getAllHang() {
        return hspr.getAll();
    }

    @Override
    public List<ChatLieu> getAllChatLieu() {
        return clr.getAll();
    }

    @Override
    public String AddMauSac(MauSac mauSac) {
        MauSac spMa = null;
        try {
            spMa = msr.getOneMa(mauSac.getMa());
        } catch (Exception e) {

        }
        if (mauSac.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (mauSac.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (msr.add(mauSac)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddLoai(Loai loai) {
        Loai spMa = null;
        try {
            spMa = lspr.getOneMa(loai.getMa());
        } catch (Exception e) {

        }

        if (loai.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (loai.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (lspr.add(loai)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddKichCo(KichCo kichCo) {
        KichCo spMa = null;
        try {
            spMa = kcr.getOneMa(kichCo.getMa());
        } catch (Exception e) {

        }
        if (kichCo.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (kichCo.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (kcr.add(kichCo)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddHang(Hang hang) {
        Hang spMa = null;
        try {
            spMa = hspr.getOneMa(hang.getMa());
        } catch (Exception e) {

        }
        if (hang.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (hang.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (hspr.add(hang)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddChatLieu(ChatLieu chatLieu) {
        if (chatLieu.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (chatLieu.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (clr.add(chatLieu)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateMauSac(MauSac mauSac) {

        if (mauSac.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (mauSac.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (msr.update(mauSac)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }


    @Override
    public String UpdateLoai(Loai loai) {

        if (loai.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (loai.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (lspr.update(loai)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateKichCo(KichCo kichCo) {

        if (kichCo.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (kichCo.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (kcr.update(kichCo)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateHang(Hang hang) {

        if (hang.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (hang.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (hspr.update(hang)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateChatLieu(ChatLieu chatLieu) {

        if (chatLieu.getMa().isEmpty()) {
            return "Mã không được trống";
        }
        if (chatLieu.getTen().isEmpty()) {
            return "Tên không được trống";
        }
        if (clr.update(chatLieu)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

}
