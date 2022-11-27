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
import com.poly.pro_1041.it17322.group4.domainmodel.SanPham;
import com.poly.pro_1041.it17322.group4.repository.ChatLieuRepository;
import com.poly.pro_1041.it17322.group4.repository.HangSPRepository;
import com.poly.pro_1041.it17322.group4.repository.KichCoRepository;
import com.poly.pro_1041.it17322.group4.repository.LoaiSPRepository;
import com.poly.pro_1041.it17322.group4.repository.MauSacRepository;
import com.poly.pro_1041.it17322.group4.repository.SanPhamRepository;
import com.poly.pro_1041.it17322.group4.service.ViewThuocTinhService;
import java.util.List;

/**
 *
 * @author Acer
 */
public class ViewThuocTinhServiceImpl implements ViewThuocTinhService {

    private SanPhamRepository spr = new SanPhamRepository();
    private MauSacRepository msr = new MauSacRepository();
    private LoaiSPRepository lspr = new LoaiSPRepository();
    private KichCoRepository kcr = new KichCoRepository();
    private HangSPRepository hspr = new HangSPRepository();
    private ChatLieuRepository clr = new ChatLieuRepository();

    @Override
    public List<SanPham> getAllSanPham() {
        return spr.getAll();
    }

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
    public String AddSanPham(SanPham sanPham) {
        if (spr.add(sanPham)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddMauSac(MauSac mauSac) {
        if (msr.add(mauSac)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddLoai(Loai loai) {
        if (lspr.add(loai)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddKichCo(KichCo kichCo) {
        if (kcr.add(kichCo)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddHang(Hang hang) {
        if (hspr.add(hang)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String AddChatLieu(ChatLieu chatLieu) {
        if (clr.add(chatLieu)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateMauSac(MauSac mauSac) {
        if (msr.update(mauSac)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateSanPham(SanPham sanPham) {
        if (spr.update(sanPham)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateLoai(Loai loai) {
        if (lspr.update(loai)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateKichCo(KichCo kichCo) {
        if (kcr.update(kichCo)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateHang(Hang hang) {
        if (hspr.update(hang)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String UpdateChatLieu(ChatLieu chatLieu) {
        if (clr.update(chatLieu)) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

}
