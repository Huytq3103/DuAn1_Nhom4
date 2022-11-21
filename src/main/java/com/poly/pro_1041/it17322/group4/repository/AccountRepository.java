/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Huy PC
 */
public class AccountRepository {
    private Session session = HibernateUtil.getFACTORY().openSession();
    
    private String fromTable = " FROM Account ";
    public Account getOne(String username,String pass){
        String sql = fromTable + " WHERE Username=:User AND Password=:Pass";
        Query query = session.createQuery(sql, Account.class);
        query.setParameter("User", username);
        query.setParameter("Pass", pass);
        Account account = (Account) query.getSingleResult();
        return account;
    }
    public static void main(String[] args) {
        System.out.println(new AccountRepository().getOne("nhanvien1", "12345678"));
    }
}
