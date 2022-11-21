/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Huy PC
 */
public class ChiTietSanPhamRepository {

    private Session session = HibernateUtil.getFACTORY().openSession();

    private String fromTable = " FROM ChiTietSanPham ";

    public List<ChiTietSanPham> getAll() {
        Query query = session.createQuery(fromTable, ChiTietSanPham.class);
        List<ChiTietSanPham> list = query.getResultList();
        return list;
    }
    public static void main(String[] args) {
        for(ChiTietSanPham ctsp : new ChiTietSanPhamRepository().getAll()){
            System.out.println(ctsp.toString());
        }
    }
}
