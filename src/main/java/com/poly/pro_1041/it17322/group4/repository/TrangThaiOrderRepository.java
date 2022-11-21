package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author qcuong
 */
public class TrangThaiOrderRepository {
    private String fromTable = "FROM TrangThaiOrder";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<TrangThaiOrder> getAll() {
        Query query = session.createQuery(fromTable, TrangThaiOrder.class);
        List<TrangThaiOrder> listTrangThaiOrders = query.getResultList();
        return listTrangThaiOrders;
    }

    public TrangThaiOrder getOne(Long id) {
        String sql = fromTable + "WHERE id=:id";
        Query query = session.createQuery(sql, TrangThaiOrder.class);
        query.setParameter("id", id);
        TrangThaiOrder trangThaiOrder = (TrangThaiOrder) query.getSingleResult();
        return trangThaiOrder;
    }

    public Boolean add(TrangThaiOrder trangThaiOrder) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(trangThaiOrder);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(TrangThaiOrder trangThaiOrder) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession();) {
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(trangThaiOrder);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean delete(TrangThaiOrder trangThaiOrder) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession();) {
            transaction = session.beginTransaction();
            session.delete(trangThaiOrder);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        List<TrangThaiOrder> list = new TrangThaiOrderRepository().getAll();
        for (TrangThaiOrder trangThaiOrder : list) {
            System.out.println(trangThaiOrder.toString());
        }
    } 
}
