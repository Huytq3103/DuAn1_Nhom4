/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author Acer
 */
public class MauSacRepository {

    private String fromtable = " FROM MauSac";

    public List<MauSac> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromtable + " ORDER BY CONVERT(INT,Ma) DESC", MauSac.class);
        List<MauSac> listMauSac = query.getResultList();
        return listMauSac;
    }

    public MauSac getOne(int id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " Where id = :id";
        Query query = session.createQuery(sql, MauSac.class);
        query.setParameter("id", id);
        MauSac mauSac = (MauSac) query.getSingleResult();
        return mauSac;
    }

    public MauSac getOneMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " Where ma = :ma";
        Query query = session.createQuery(sql, MauSac.class);
        query.setParameter("ma", ma);
        MauSac mauSac = (MauSac) query.getSingleResult();
        return mauSac;
    }

    public Boolean add(MauSac mauSac) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(MauSac mauSac) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(MauSac mauSac) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(mauSac);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int genMaMauSac() {
        String maMS = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, ma)) FROM MauSac");
            if (query.getSingleResult() == null) {
                return 1;
            }
            maMS = query.getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        int ma = Integer.valueOf(maMS);
        return ++ma;
    }
}
