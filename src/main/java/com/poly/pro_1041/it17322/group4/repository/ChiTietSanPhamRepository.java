/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.repository;

import com.poly.pro_1041.it17322.group4.config.HibernateUtil;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import java.util.List;
import java.util.UUID;
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
    
    public List<ViewCTSPResponse> getOneLoai(int idLoai) {
        Session session = HibernateUtil.getFACTORY().openSession();
        String sql = fromTable + " WHERE IdLoai=:idLoai ";
        javax.persistence.Query query = session.createQuery(sql, ChiTietSanPham.class);
        query.setParameter("IdLoai", idLoai);
        List<ViewCTSPResponse> chiTietSP = query.getResultList();
        return chiTietSP;
    }
    
    public static void main(String[] args) {
//        for(ChiTietSanPham ctsp : new ChiTietSanPhamRepository().getAll()){
//            System.out.println(ctsp.toString());
//        }
        List<ViewCTSPResponse> list = new ChiTietSanPhamRepository().getOneLoai(1);
        for (ViewCTSPResponse viewCTSPResponse : list) {
            System.out.println(viewCTSPResponse.toString());
        }
    }
}
