/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;


import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.ChucVuAccount;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DELL
 */
public class ChucVuAccountRepository {

    private String fromTable = "FROM ChucVuAccount";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<ChucVuAccount> getAll() {
        Query query = session.createQuery(fromTable, ChucVuAccount.class);
        List<ChucVuAccount> listChucVuAccount = query.getResultList();
        return listChucVuAccount;
    }

    public ChucVuAccount getOne(int id) {
        String sql = fromTable + "WHERE Id =: Id";
        Query query = session.createQuery(sql, ChucVuAccount.class);
        query.setParameter("Id", id);
        ChucVuAccount chucvu = (ChucVuAccount) query.getSingleResult();
        return chucvu;
    }

    public Boolean add(ChucVuAccount chucvu) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(chucvu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(ChucVuAccount chucvu) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(chucvu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(ChucVuAccount chucvu) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(chucvu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<ChucVuAccount> lists = new ChucVuAccountRepository().getAll();
        for (ChucVuAccount x : lists) {
            System.out.println(x.toString());
        }
    }
}
