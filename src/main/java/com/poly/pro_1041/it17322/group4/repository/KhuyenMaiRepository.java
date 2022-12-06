/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.KhuyenMai;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Lenovo
 */
public class KhuyenMaiRepository {
    
    private Session session ;
    private String fromTable = "FROM KhuyenMai";
    public List<KhuyenMai> getAll(){
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, KhuyenMai.class);
        List<KhuyenMai> khuyenMai = query.getResultList();
        return khuyenMai;
    }
    
    public KhuyenMai getOne(String id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + " WHERE Id =: Id";
        javax.persistence.Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("Id", id);
        KhuyenMai khuyenMai =  (KhuyenMai) query.getSingleResult();
        return khuyenMai;
    }
    
    
    
    public KhuyenMai getOneMaKM(String maKM) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + " WHERE Ma =: Ma";
        javax.persistence.Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("Ma", maKM);
        KhuyenMai khuyenMai =  (KhuyenMai) query.getSingleResult();
        return khuyenMai;
    }

    public Boolean add(KhuyenMai khuyenMai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(khuyenMai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public Boolean update(KhuyenMai khuyenMai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(khuyenMai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public Boolean delete(KhuyenMai khuyenMai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(khuyenMai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public static void main(String[] args) {
        List<KhuyenMai> lists = new KhuyenMaiRepository().getAll();
        for (KhuyenMai x : lists) {
            System.out.println(x.toString());
        }
    }
}
