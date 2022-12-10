/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author DELL
 */
public class LoaiSPRepository {

    private String fromtable = " FROM Loai ";
    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<Loai> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromtable + " ORDER BY CONVERT(INT,Ma) DESC", Loai.class);
        List<Loai> listLoai = query.getResultList();
        return listLoai;
    }

    public Loai getOne(int id) {
        Session session = HibernateUtil.getFACTORY().openSession();

        String sql = fromtable + " Where id = :id";
        Query query = session.createQuery(sql, Loai.class);
        query.setParameter("id", id);
        Loai loai = (Loai) query.getSingleResult();
        return loai;
    }

    public Loai getOneMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromtable + " Where ma = :ma";
        Query query = session.createQuery(sql, Loai.class);
        query.setParameter("ma", ma);
        Loai loai = (Loai) query.getSingleResult();
        return loai;
    }

    public Loai getOneTen(String ten) {
        String sql = fromtable + " Where Ten = :ten";
        Query query = session.createQuery(sql, Loai.class);
        query.setParameter("Ten", ten);
        Loai loai = (Loai) query.getSingleResult();
        return loai;
    }

    public Boolean add(Loai loai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(loai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(Loai loai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(loai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(Loai loai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(loai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int genMaLoai() {
        String maLoai = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, ma)) FROM Loai");
            if (query.getSingleResult() == null) {
                return 1;
            }
            maLoai = query.getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        int ma = Integer.valueOf(maLoai);
        return ++ma;
    }

    public Loai findLoaiByTen(String ten) {
        Loai l = new Loai();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            String sql = fromtable + " Where ten= :ten";
            Query query = session.createQuery(sql);
            query.setParameter("ten", ten);
            l = (Loai) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return l;
    }
}
