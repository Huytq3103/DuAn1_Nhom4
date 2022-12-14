/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Huy PC
 */
public class AccountRepository {

    private String fromTable = " FROM Account ";

    private Session session;

    public List<Account> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable + " WHERE IDTT = 1", Account.class);
        List<Account> accs = query.getResultList();
        return accs;
    }

    public List<Account> getAllAn() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable + " WHERE IDTT = 2", Account.class);
        List<Account> accs = query.getResultList();
        return accs;
    }

    public Account getOne(String username, String pass) {
        session = HibernateUtil.getSession();
        String sql = fromTable + " WHERE Username=:User AND Password=:Pass";
        Query query = session.createQuery(sql, Account.class);
        query.setParameter("User", username);
        query.setParameter("Pass", pass);
        Account account = (Account) query.getSingleResult();
        return account;
    }

    public Account checkEmail(String email) {
        session = HibernateUtil.getSession();
        String sql = fromTable + "WHERE Email =: Email";
        Query query = session.createQuery(sql, Account.class);
        query.setParameter("Email", email);
        Account acc = (Account) query.getSingleResult();
        return acc;
    }

    public Account getOneNguoiTao(String id) {
        session = HibernateUtil.getSession();
        String sql = fromTable + " WHERE id=:id";
        Query query = session.createQuery(sql, Account.class);
        query.setParameter("id", UUID.fromString(id));
        Account account = (Account) query.getSingleResult();
        return account;
    }

    public Boolean add(Account acc) {
        Transaction transaction = null;
        session = HibernateUtil.getSession();
        transaction = (Transaction) session.beginTransaction();
        session.save(acc);
        transaction.commit();
        return true;
    }

    public Boolean update(Account account) {
        Transaction transaction = null;
        session = HibernateUtil.getSession();
        transaction = (Transaction) session.beginTransaction();
        session.saveOrUpdate(account);
        transaction.commit();
        return true;
    }

    public Boolean updateDMK(Account account) {
        Transaction transaction = null;
        session = HibernateUtil.getSession();
        transaction = (Transaction) session.beginTransaction();
        session.saveOrUpdate(account);
        transaction.commit();
        return true;

    }

    public static void main(String[] args) {
//        System.out.println(new AccountRepository().getOne("nhanvien2", "abc").toString());
        System.out.println(new AccountRepository().checkEmail("cuongnqph26071@fpt.edu.vn").toString());
    }
}
