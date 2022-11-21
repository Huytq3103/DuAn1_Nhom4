/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiKM;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lenovo
 */
public class TrangThaiKMRepository {
    private String fromTable = "FROM TrangThaiKM";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<TrangThaiKM> getAll() {
        Query query = session.createQuery(fromTable, TrangThaiKM.class);
        List<TrangThaiKM> listLoai = query.getResultList(); // List category
        return listLoai;
    }

    public TrangThaiKM getOne(Long id) {
        String sql = fromTable + "WHERE id =: id";
        Query query = session.createQuery(sql, TrangThaiKM.class);
        query.setParameter("id", id);
        TrangThaiKM trangThaiKM =   (TrangThaiKM) query.getSingleResult();
        return trangThaiKM;
    }
    
    public Boolean add(TrangThaiKM trangThaiKM){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = (Transaction) session.beginTransaction();
            session.save(trangThaiKM); //add
            transaction.commit();
            return true; 
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public Boolean update(TrangThaiKM trangThaiKM){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(trangThaiKM); //add
            transaction.commit();
            return true; 
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public Boolean delete(TrangThaiKM trangThaiKM){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = (Transaction) session.beginTransaction();
            session.delete(trangThaiKM); 
            transaction.commit();
            return true; 
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<TrangThaiKM> lists = new TrangThaiKMRepository().getAll();
        for (TrangThaiKM x : lists) {
            System.out.println(x.toString());
        }
    }
}
