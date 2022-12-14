/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author DELL
 */
public class HoaDonRepository {

    private Session session;

    private String fromTable = "FROM HoaDon ";

    public List<HoaDon> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, HoaDon.class);
        List<HoaDon> hoadons = query.getResultList();
        return hoadons;
    }

    public List<HoaDon> getAllByChuaTT() {
        String sql = fromTable + " WHERE IdTT = 2 ORDER BY NgayTao DESC";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, HoaDon.class);
        List<HoaDon> hoadons = query.getResultList();
        return hoadons;
    }

    public List<HoaDon> getAllByDaTT() {
        String sql = fromTable + " WHERE IdTT = 1 ORDER BY NgayThanhToan DESC";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, HoaDon.class);
        List<HoaDon> hoadons = query.getResultList();
        return hoadons;
    }

    public List<HoaDon> getAllByDangGiao() {
        String sql = fromTable + " WHERE IdTT = 4 ORDER BY NgayShip DESC";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, HoaDon.class);
        List<HoaDon> hoadons = query.getResultList();
        return hoadons;
    }

    public List<HoaDon> getAllByDaGiao() {
        String sql = fromTable + " WHERE IdTT = 5 ORDER BY NgayKhachNhan DESC";
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, HoaDon.class);
        List<HoaDon> hoadons = query.getResultList();
        return hoadons;
    }

    public List<HoaDon> getListByDate(String tuNgay, String denNgay) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + "WHERE NgayTao BETWEEN :tuNgay AND :denNgay";
        Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("tuNgay", tuNgay);
        query.setParameter("denNgay", denNgay);
        List<HoaDon> list = query.getResultList();
        return list;
    }

    public List<HoaDon> getAllOrderByNgayTao() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + " ORDER BY NgayTao DESC";
        Query query = session.createQuery(sql, HoaDon.class);
        List<HoaDon> hoadons = query.getResultList();
        return hoadons;
    }

    public List<HoaDon> getList(int idTT) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + "WHERE IdTT =: idTT";
        Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("idTT", idTT);
        List<HoaDon> list = query.getResultList();
        return list;
    }

    public HoaDon getOne(UUID id) {
        String sql = fromTable + "WHERE Id =: Id";
        Session session = HibernateUtil.getFACTORY().openSession();
        javax.persistence.Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("Id", id);
        HoaDon hoadon = (HoaDon) query.getSingleResult();
        return hoadon;
    }

    public List<HoaDon> getOneHDKH(UUID id) {
        String sql = fromTable + "WHERE IdKH =: Id";
        Session session = HibernateUtil.getFACTORY().openSession();
        javax.persistence.Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("Id", id);
        List<HoaDon> listHD = query.getResultList();
        return listHD;
    }

    public List<HoaDon> getOneHDKHAndTongTien(UUID id, BigDecimal tongTien) {
        String sql = fromTable + "WHERE IdKH =: Id AND tongTien = :tongTien";
        Session session = HibernateUtil.getFACTORY().openSession();
        javax.persistence.Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("Id", id);
        query.setParameter("tongTien", tongTien);
        List<HoaDon> listHD = query.getResultList();
        return listHD;
    }

    public HoaDon getOne(String Ma) {
        String sql = fromTable + "WHERE Ma =: Ma";
        Session session = HibernateUtil.getFACTORY().openSession();
        javax.persistence.Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("Ma", Ma);
        HoaDon hoadon = (HoaDon) query.getSingleResult();
        return hoadon;

    }

    public Boolean add(HoaDon hoadon) {
        Transaction transaction = null;

        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(hoadon);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(HoaDon hd) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.merge(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(HoaDon hoadon) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(hoadon);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<HoaDon> lists = new HoaDonRepository().getAll();
        for (HoaDon x : lists) {
            System.out.println(x.toString());
        }
    }

    public BigDecimal getTongTien(UUID idKH) {
        BigDecimal tongTien;
        session = HibernateUtil.getSession();
        String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE IDKH=:idkh";
        Query query = session.createNativeQuery(sql);
        query.setParameter("idkh", idKH);
        tongTien = (BigDecimal) query.getSingleResult();
        return tongTien;
    }

}
