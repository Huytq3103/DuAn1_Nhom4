/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonTra;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DELL
 */
public class HoaDonTraRepository {

    private String fromTable = " FROM HoaDonTra";
    private Session session;

    public List<HoaDonTra> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createNativeQuery(fromTable, HoaDonTra.class);
        List<HoaDonTra> lists = query.getResultList();
        return lists;
    }

    public Boolean add(HoaDonTra hdt) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(hdt);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(HoaDonTra hdt) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hdt);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
