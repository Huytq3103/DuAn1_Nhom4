/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author DELL
 */
public class KhachHangRepository {

    private String fromtable = " FROM KhachHang";
    private String fromtableHD = "FROM HoaDon";

    public List<KhachHang> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromtable, KhachHang.class);
        List<KhachHang> lists = query.getResultList();
        return lists;
    }

    public KhachHang getOne(UUID id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " WHERE id = :id";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("id", id);
        KhachHang kh = (KhachHang) query.getSingleResult();
        return kh;
    }

    public KhachHang getOneSdt(String sdt) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " WHERE sdt = :sdt";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("sdt", sdt);
        KhachHang khachHang = (KhachHang) query.getSingleResult();
        return khachHang;
    }

    public KhachHang getOneEmail(String email) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " WHERE email = :email";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("email", email);
        KhachHang khachHang = (KhachHang) query.getSingleResult();
        return khachHang;
    }

    public List<KhachHang> seachKhoangNgay(String ngayBatDau, String ngayKetThuc) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " WHERE NgayTao BETWEEN :ngayBatDau AND :ngayKetThuc";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        List<KhachHang> lists = query.getResultList();
        return lists;
    }

    public List<KhachHang> seachKhoangNgaySinh(String ngaySinh) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " WHERE NgaySinh = :ngaySinh";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("ngaySinh", ngaySinh);
        List<KhachHang> lists = query.getResultList();
        return lists;
    }

    public List<KhachHang> seachByEmail(String email) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " WHERE email = :email";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("email", email);
        List<KhachHang> lists = query.getResultList();
        return lists;
    }

    public int genMaKH() {
        String maKH = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, ma)) FROM KhachHang");
            if (query.getSingleResult() == null) {
                return 1;
            }
            maKH = query.getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        int ma = Integer.valueOf(maKH);
        return ++ma;
    }

    public Boolean add(KhachHang kh) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(KhachHang kh) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(KhachHang kh) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public KhachHang getKhachHangLichSu(String id) {
        String sql = fromtableHD + "WHERE id =:id";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("id", id);
        KhachHang kh = (KhachHang) query.getSingleResult();
        return kh;
    }

    public static void main(String[] args) {
        new KhachHangRepository().getAll().forEach(s -> System.out.println(s.toString()));
    }
}
