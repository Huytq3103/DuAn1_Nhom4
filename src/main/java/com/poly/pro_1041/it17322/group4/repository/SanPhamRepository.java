/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.SanPham;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Acer
 */
public class SanPhamRepository {
    
    private String fromtable = "FROM SanPham";
    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<SanPham> getAll() {
        Query query = session.createQuery(fromtable, SanPham.class);
        List<SanPham> listSP = query.getResultList();
        return listSP;
    }

    public SanPham getOne(Long id) {
        String sql = fromtable + "Where Id= :id";
        Query query = session.createQuery(sql, SanPham.class);
        query.setParameter("Id", id);
        SanPham sanPham = (SanPham) query.getSingleResult();
        return sanPham;
    }

    public Boolean add(SanPham sanPham) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(sanPham); // add
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(SanPham sanPham) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(sanPham); // add
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(SanPham sanPham) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(sanPham); // add
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<SanPham> lists = new SanPhamRepository().getAll();
        for (SanPham c : lists) {
            System.out.println(c.toString());
        }
    }
}
