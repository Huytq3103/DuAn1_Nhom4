/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;


import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiAccount;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DELL
 */
public class TrangThaiAccountRepository {

    private String fromTable = "FROM TrangThaiAccount";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<TrangThaiAccount> getAll() {
        Query query = session.createQuery(fromTable, TrangThaiAccount.class);
        List<TrangThaiAccount> listTrangThaiAccount = query.getResultList();
        return listTrangThaiAccount;
    }

    public TrangThaiAccount getOne(int id) {
        String sql = fromTable + "WHERE Id =: Id";
        Query query = session.createQuery(sql, TrangThaiAccount.class);
        query.setParameter("Id", id);
        TrangThaiAccount trangthai = (TrangThaiAccount) query.getSingleResult();
        return trangthai;
    }

    public Boolean add(TrangThaiAccount trangthai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(trangthai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(TrangThaiAccount trangthai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(trangthai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(TrangThaiAccount trangthai) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(trangthai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<TrangThaiAccount> lists = new TrangThaiAccountRepository().getAll();
        for (TrangThaiAccount x : lists) {
            System.out.println(x.toString());
        }
//            new TrangThaiAccountRepository().add(new TrangThaiAccount(0, "hh", "aa"));
    }
}
