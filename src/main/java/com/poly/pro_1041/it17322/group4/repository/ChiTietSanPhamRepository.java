/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Huy PC
 */
public class ChiTietSanPhamRepository {

    private String fromTable = " FROM ChiTietSanPham ";

    private Session session;

    public List<ChiTietSanPham> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, ChiTietSanPham.class);
        List<ChiTietSanPham> list = query.getResultList();
        return list;
    }

    public List<ViewCTSPResponse> getOneLoai(int idLoai) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + " WHERE IdLoai=:idLoai ";
        javax.persistence.Query query = session.createQuery(sql, ChiTietSanPham.class);
        query.setParameter("IdLoai", idLoai);
        List<ViewCTSPResponse> chiTietSP = query.getResultList();
        return chiTietSP;
    }

    public ChiTietSanPham getOne(UUID id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + "Where id=:id";
        Query query = session.createQuery(sql, ChiTietSanPham.class);
        query.setParameter("id", id);
        ChiTietSanPham ctsp = (ChiTietSanPham) query.getSingleResult();
        return ctsp;
    }

    public ChiTietSanPham getOneUpdateHoaDon(UUID id) {
        session = HibernateUtil.getSession();
        String sql = fromTable + "Where id=:id";
        Query query = session.createQuery(sql, ChiTietSanPham.class);
        query.setParameter("id", id);
        ChiTietSanPham ctsp = (ChiTietSanPham) query.getSingleResult();
        return ctsp;
    }

    public ChiTietSanPham getOneMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + "Where ma=:ma";
        Query query = session.createQuery(sql, ChiTietSanPham.class);
        query.setParameter("ma", ma);
        ChiTietSanPham ctsp = (ChiTietSanPham) query.getSingleResult();
        return ctsp;
    }

    public Boolean add(ChiTietSanPham chitietsanPham) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(chitietsanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(ChiTietSanPham chitietsanPham) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(chitietsanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean updateTableHD(ChiTietSanPham ctsp) {
        Transaction transaction = null;
        session = HibernateUtil.getSession();
        transaction = (Transaction) session.beginTransaction();
        session.saveOrUpdate(ctsp);
        transaction.commit();
        return true;
    }

    public Boolean delete(ChiTietSanPham chitietsanPham) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(chitietsanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
