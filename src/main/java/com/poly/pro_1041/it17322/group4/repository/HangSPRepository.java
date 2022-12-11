/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author DELL
 */
public class HangSPRepository {

    private String fromtable = " FROM Hang";

    public List<Hang> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromtable + " ORDER BY CONVERT(INT,Ma) DESC", Hang.class);
        List<Hang> listHang = query.getResultList();
        return listHang;
    }

    public Hang getOne(int id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " Where id = :id";
        Query query = session.createQuery(sql, Hang.class);
        query.setParameter("id", id);
        Hang loai = (Hang) query.getSingleResult();
        return loai;
    }

    public Hang getOneMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " Where ma = :ma";
        Query query = session.createQuery(sql, Hang.class);
        query.setParameter("ma", ma);
        Hang loai = (Hang) query.getSingleResult();
        return loai;
    }

    public Boolean add(Hang hang) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(hang); // add
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(Hang hang) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(hang); // add
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(Hang hang) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(hang); // add
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int genMaHang() {
        String maH = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, ma)) FROM Hang ");
            if (query.getSingleResult() == null) {
                return 1;
            }
            maH = query.getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        int ma = Integer.valueOf(maH);
        return ++ma;
    }

    public Hang findHangByTen(String ten) {
        Hang hang = new Hang();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            String sql = fromtable + " Where ten= :ten";
            Query query = session.createQuery(sql);
            query.setParameter("ten", ten);
            hang = (Hang) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return hang;
    }
}
