/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author DELL
 */
public class HoaDonChiTietRepository {

    private Session session = HibernateUtil.getFACTORY().openSession();

    private String fromTable = "FROM HoaDonChiTiet";

    public List<HoaDonChiTiet> getAll() {
        Query query = session.createQuery(fromTable, HoaDonChiTiet.class);
        List<HoaDonChiTiet> hoadonchitiets = query.getResultList();
        return hoadonchitiets;
    }

    public List<HoaDonChiTiet> getOneHD(UUID id) {
        String sql = fromTable + " WHERE IdHoaDon=:id ";
        javax.persistence.Query query = session.createQuery(sql, HoaDonChiTiet.class);
        query.setParameter("id", id);
        List<HoaDonChiTiet> hoadonchitiets = query.getResultList();
        return hoadonchitiets;
    }

    public Boolean add(HoaDonChiTiet hoadonchitiet) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(hoadonchitiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(HoaDonChiTiet hoadonchitiet) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(hoadonchitiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(HoaDonChiTiet hoadonchitiet) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(hoadonchitiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<HoaDonChiTiet> lists = new HoaDonChiTietRepository().getAll();
        for (HoaDonChiTiet x : lists) {
            System.out.println(x.toString());
        }
    }
}
