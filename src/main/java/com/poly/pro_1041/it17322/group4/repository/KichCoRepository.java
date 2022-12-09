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
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 *
 * @author Dell
 */
public class KichCoRepository {

    private String fromTable = "FROM KichCo";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<KichCo> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable + " ORDER BY CONVERT(INT,Ma) DESC", KichCo.class);
        List<KichCo> listKichCo = query.getResultList();
        return listKichCo;
    }

    public KichCo getOne(int id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + " WHERE id =: id";
        Query query = session.createQuery(sql, KichCo.class);
        query.setParameter("id", id);
        KichCo kichCo = (KichCo) query.getSingleResult();
        return kichCo;
    }

    public KichCo getOneMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + " WHERE ma =: ma";
        Query query = session.createQuery(sql, KichCo.class);
        query.setParameter("ma", ma);
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

    public int genMaKichCo() {
        String maKC = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, ma)) FROM KichCo");
            if (query.getSingleResult() == null) {
                return 1;
            }
            maKC = query.getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        int ma = Integer.valueOf(maKC);
        return ++ma;
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
