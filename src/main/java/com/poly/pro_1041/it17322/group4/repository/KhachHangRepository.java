/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DELL
 */
public class KhachHangRepository {

    private Session session = HibernateUtil.getFACTORY().openSession();
    private String fromtable = " FROM KhachHang ";

    public List<KhachHang> getAll() {
        Query query = session.createQuery(fromtable, KhachHang.class);
        List<KhachHang> lists = query.getResultList();
        return lists;
    }

    public KhachHang getOne(UUID id) {
        String sql = fromtable + " WHERE id = :id";
        Query query = session.createQuery(sql, KhachHang.class);
        query.setParameter("id", id);
        KhachHang kh = (KhachHang) query.getSingleResult();
        return kh;
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

    public static void main(String[] args) {
        new KhachHangRepository().getAll().forEach(s -> System.out.println(s.toString()));
    }
}
