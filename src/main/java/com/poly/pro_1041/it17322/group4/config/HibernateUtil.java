/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.config;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.ChucVuAccount;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonTra;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonTraChiTiet;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.domainmodel.KhuyenMai;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.LoaiKM;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import com.poly.pro_1041.it17322.group4.domainmodel.SanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiAccount;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiKM;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Huy PC
 */
public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=QuanAoNam");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "31032003lssh");
        properties.put(Environment.SHOW_SQL, "true");
        conf.addAnnotatedClass(Account.class);
        conf.addAnnotatedClass(ChatLieu.class);
        conf.addAnnotatedClass(ChiTietSanPham.class);
        conf.addAnnotatedClass(ChucVuAccount.class);
        conf.addAnnotatedClass(Hang.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(KhuyenMai.class);
        conf.addAnnotatedClass(KichCo.class);
        conf.addAnnotatedClass(Loai.class);
        conf.addAnnotatedClass(LoaiKM.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(TrangThaiAccount.class);
        conf.addAnnotatedClass(TrangThaiKM.class);
        conf.addAnnotatedClass(TrangThaiOrder.class);
        conf.addAnnotatedClass(HoaDonTra.class);
        conf.addAnnotatedClass(HoaDonTraChiTiet.class);
        conf.setProperties(properties);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }
}
