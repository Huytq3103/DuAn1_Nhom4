/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
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

    public List<HoaDon> getAllOrderByNgayTao() {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + "ORDER BY NgayTao DESC";
        Query query = session.createQuery(sql, HoaDon.class);
        List<HoaDon> hoadons = query.getResultList();
        return hoadons;
    }

    public HoaDon getOne(UUID id) {
        String sql = fromTable + "WHERE Id =: Id";
        Session session = HibernateUtil.getFACTORY().openSession();
        javax.persistence.Query query = session.createQuery(sql, HoaDon.class);
        query.setParameter("Id", id);
        HoaDon hoadon = (HoaDon) query.getSingleResult();
        return hoadon;
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
            session.saveOrUpdate(hd);
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

}
