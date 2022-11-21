/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Dell
 */
public class KichCoRepository {

    private String fromTable = "FROM KichCo";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<KichCo> getAll() {
        Query query = session.createQuery(fromTable, KichCo.class);
        List<KichCo> listKichCo = query.getResultList();
        return listKichCo;
    }

    public KichCo getOne(int id) {
        String sql = fromTable + " WHERE id =: id";
        Query query = session.createQuery(sql, KichCo.class);
        query.setParameter("id", id);
        KichCo kichCo = (KichCo) query.getSingleResult();
        return kichCo;
    }

    public Boolean add(KichCo kichCo) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(kichCo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(KichCo kichCo) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(kichCo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(KichCo kichCo) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(kichCo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
//        List<KichCo> lists = new KichCoRepository().getAll();
//        for (KichCo kc : lists) {
//            System.out.println(kc.toString());
//        }

//        KichCo kichCo = new KichCoRepository().getOne(2);
//        System.out.println(kichCo.toString());
        Boolean delete = new KichCoRepository().delete(new KichCo(1, "KC1", "31"));
        System.out.println(delete);

    }

}
