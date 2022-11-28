/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Huy PC
 */
public class AccountRepository {

    private String fromTable = " FROM Account ";


    public List<Account> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        org.hibernate.query.Query query = session.createQuery(fromTable, Account.class);
        List<Account> accs = query.getResultList();
        return accs;
    }


    public Account getOne(String username, String pass) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + " WHERE Username=:User AND Password=:Pass";
        Query query = session.createQuery(sql, Account.class);
        query.setParameter("User", username);
        query.setParameter("Pass", pass);
        Account account = (Account) query.getSingleResult();
        return account;
    }

    public Boolean add(Account acc) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(acc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(Account acc) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(acc);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }


    public static void main(String[] args) {

        System.out.println(new AccountRepository().getOne("nhanvien1", "12345678").toString());
    }
}
