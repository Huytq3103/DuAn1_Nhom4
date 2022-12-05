/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewThongKeResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Dell
 */
public class ThongKeRepository {

    private Session session = HibernateUtil.getFACTORY().openSession();

    public Integer getTongDonHang() {
        int i = 0;
        Query query = session.createNativeQuery("SELECT COUNT(id) FROM HoaDon WHERE IdTT = 1 OR IdTT = 3");
        i = (Integer) query.getSingleResult();
        return i;
    }

    public Integer getHoaDonThanhCong() {
        int i = 0;
        Query query = session.createNativeQuery("SELECT COUNT(id) FROM HoaDon WHERE IdTT = 1");
        i = (Integer) query.getSingleResult();
        return i;
    }

    public Integer getHoaDonBiHuy() {
        int i = 0;
        Query query = session.createNativeQuery("SELECT COUNT(id) FROM HoaDon WHERE IdTT = 3");
        i = (Integer) query.getSingleResult();
        return i;
    }

    public Integer getTongDonHang_Ngay(String ngay) {
        int i = 0;
        Query query = session.createNativeQuery("SELECT COUNT(id) FROM HoaDon WHERE NgayThanhToan=:ngay AND IdTT = 1 OR IdTT = 3");
        query.setParameter("ngay", ngay);
        i = (Integer) query.getSingleResult();
        return i;
    }

    public Integer getDonHangThanhCong_Ngay(String ngay) {
        int i = 0;
        Query query = session.createNativeQuery("SELECT COUNT(id) FROM HoaDon WHERE NgayThanhToan=:ngay AND IdTT = 1");
        query.setParameter("ngay", ngay);
        i = (Integer) query.getSingleResult();
        return i;
    }

    public Integer getDonHangBiHuy_Ngay(String ngay) {
        int i = 0;
        Query query = session.createNativeQuery("SELECT COUNT(id) FROM HoaDon WHERE NgayTao=:ngay AND IdTT = 3");
        query.setParameter("ngay", ngay);
        i = (Integer) query.getSingleResult();
        return i;
    }

    public List<Integer> getNam() {
        Query query = session.createNativeQuery("SELECT DISTINCT YEAR(NgayThanhToan) FROM HoaDon WHERE IdTT = 1 ORDER BY YEAR(NgayThanhToan) DESC");
        List<Integer> listNam = query.getResultList();
        return listNam;
    }

    public BigDecimal getTongDoanhThuThang() {
        BigDecimal i;
        Query query = session.createNativeQuery("SELECT SUM(TongTien) FROM HoaDon WHERE MONTH(NgayThanhToan) = MONTH(GETDATE()) AND YEAR(NgayThanhToan) = YEAR(GETDATE()) AND IdTT = 1");
        i = (BigDecimal) query.getSingleResult();
        return i;
    }

    public BigDecimal getTongDoanhThuNam() {
        BigDecimal i;
        Query query = session.createNativeQuery("SELECT SUM(TongTien) FROM HoaDon WHERE YEAR(NgayThanhToan) = YEAR(GETDATE()) AND IdTT = 1");
        i = (BigDecimal) query.getSingleResult();
        return i;
    }

    public BigDecimal getTongDoanhThuNgay(String ngay) {
        BigDecimal i;
        Query query = session.createNativeQuery("SELECT SUM(TongTien) FROM HoaDon WHERE NgayThanhToan=:ngay AND IdTT = 1");
        query.setParameter("ngay", ngay);
        i = (BigDecimal) query.getSingleResult();
        return i;
    }

    public List<ViewThongKeResponse> getDoanhThu() {
        Query query = session.createNativeQuery("SELECT MONTH(NgayThanhToan) AS Thang, SUM(TongTien) AS Tong FROM HoaDon GROUP BY MONTH(NgayThanhToan)");
        List<ViewThongKeResponse> lists = query.getResultList();
        return lists;
    }

    public List<BigDecimal> getGiaBan(int nam) {
        Query query = session.createNativeQuery("SELECT SUM(TongTien) AS Tong FROM HoaDon WHERE IdTT = 1 AND YEAR(NgayThanhToan)=:nam GROUP BY MONTH(NgayThanhToan) ORDER BY MONTH(NgayThanhToan) ASC");
        query.setParameter("nam", nam);
        List<BigDecimal> lists = query.getResultList();
        return lists;
    }

    public List<Integer> getThang(int nam) {
        Query query = session.createNativeQuery("SELECT MONTH(NgayThanhToan) AS Thang FROM HoaDon WHERE IdTT = 1 AND YEAR(NgayThanhToan)=:nam GROUP BY MONTH(NgayThanhToan) ORDER BY MONTH(NgayThanhToan) ASC");
        query.setParameter("nam", nam);
        List<Integer> lists = query.getResultList();
        return lists;
    }

    public BigDecimal getTongDoanhThuNgay2(String ngayBatDau, String ngayKetThuc) {
        BigDecimal i;
        Query query = session.createNativeQuery("SELECT  SUM(TongTien) AS Tien FROM HoaDon WHERE NgayThanhToan BETWEEN :ngayBatDau AND :ngayKetThuc");
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        i = (BigDecimal) query.getSingleResult();
        return i;
    }

    public List<BigDecimal> getGiaBan_Nam() {
        Query query = session.createNativeQuery("SELECT SUM(TongTien) AS Tong FROM HoaDon WHERE IdTT = 1 GROUP BY YEAR(NgayThanhToan) ORDER BY YEAR(NgayThanhToan) ASC");
        List<BigDecimal> lists = query.getResultList();
        return lists;
    }

    public List<Integer> getNam_2() {
        Query query = session.createNativeQuery("SELECT YEAR(NgayThanhToan) FROM HoaDon WHERE IdTT = 1 GROUP BY YEAR(NgayThanhToan) ORDER BY YEAR(NgayThanhToan) ASC");
        List<Integer> lists = query.getResultList();
        return lists;
    }
}
