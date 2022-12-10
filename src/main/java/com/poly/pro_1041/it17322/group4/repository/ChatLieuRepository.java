/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

/**
 *
 * @author Dell
 */
public class ChatLieuRepository {

    private String fromTable = "FROM ChatLieu";

    private Session session = HibernateUtil.getFACTORY().openSession();

    public List<ChatLieu> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        Query query = session.createQuery(fromTable + " ORDER BY CONVERT(INT,Ma) DESC", ChatLieu.class);
        List<ChatLieu> listChatLieu = query.getResultList();
        return listChatLieu;
    }

    public ChatLieu getOne(int id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + "WHERE id =: id";
        Query query = session.createQuery(sql, ChatLieu.class);
        query.setParameter("id", id);
        ChatLieu chatLieu = (ChatLieu) query.getSingleResult();
        return chatLieu;
    }

    public ChatLieu getOneMa(String ma) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + "WHERE ma =: ma";
        Query query = session.createQuery(sql, ChatLieu.class);
        query.setParameter("ma", ma);
        ChatLieu chatLieu = (ChatLieu) query.getSingleResult();
        return chatLieu;
    }

    public Boolean add(ChatLieu chatLieu) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(chatLieu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Boolean update(ChatLieu chatLieu) {
        Transaction transaction = null;
        session = HibernateUtil.getSession();
        transaction = (Transaction) session.beginTransaction();
        session.saveOrUpdate(chatLieu);
        transaction.commit();
        return true;
    }

    public Boolean delete(ChatLieu chatLieu) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(chatLieu);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int genMaChatLieu() {
        String maCL = "";
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NativeQuery query = session.createNativeQuery("SELECT MAX(CONVERT(INT, ma)) FROM ChatLieu");
            if (query.getSingleResult() == null) {
                return 1;
            }
            maCL = query.getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        int ma = Integer.valueOf(maCL);
        return ++ma;
    }

    public ChatLieu findChatLieuByTen(String ten) {
        ChatLieu cl = new ChatLieu();
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            String sql = fromTable + " Where ten= :ten";
            Query query = session.createQuery(sql);
            query.setParameter("ten", ten);
            cl = (ChatLieu) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return cl;
    }

    public static void main(String[] args) {
//        List<ChatLieu> lists = new ChatLieuRepository().getAll();
//        for (ChatLieu cl : lists) {
//            System.out.println(cl.toString());
//        }

    }
}
