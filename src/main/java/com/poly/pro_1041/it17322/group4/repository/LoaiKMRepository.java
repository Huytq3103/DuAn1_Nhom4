/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.LoaiKM;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lenovo
 */
public class LoaiKMRepository {
    private String fromTable = "FROM LoaiKM";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<LoaiKM> getAll() {
        Query query = session.createQuery(fromTable, LoaiKM.class);
        List<LoaiKM> listLoaiKM = query.getResultList(); // List category
        return listLoaiKM;
    }

    public LoaiKM getOne(Long id) {
        String sql = fromTable + "WHERE id =: id";
        Query query = session.createQuery(sql, LoaiKM.class);
        query.setParameter("id", id);
        LoaiKM loaiKM =  (LoaiKM) query.getSingleResult();
        return loaiKM;
    }
    
    public Boolean add(LoaiKM loaiKM){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = (Transaction) session.beginTransaction();
            session.save(loaiKM); //add
            transaction.commit();
            return true; 
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public Boolean update(LoaiKM loaiKM){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(loaiKM); //add
            transaction.commit();
            return true; 
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public Boolean delete(LoaiKM loaiKM){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getFACTORY().openSession()){
            transaction = (Transaction) session.beginTransaction();
            session.delete(loaiKM); 
            transaction.commit();
            return true; 
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<LoaiKM> lists = new LoaiKMRepository().getAll();
        for (LoaiKM x : lists) {
            System.out.println(x.toString());
        }
        
        

    }
}
