/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.view;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.service.ViewSanPhamService;
import com.poly.pro_1041.it17322.group4.service.ViewThuocTinhService;
import com.poly.pro_1041.it17322.group4.service.impl.ViewSanPhamServiceImpl;
import com.poly.pro_1041.it17322.group4.service.impl.ViewThuocTinhServiceImpl;
import com.poly.pro_1041.it17322.group4.util.ImportExcelCTSP;
import com.sun.mail.imap.ACL;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Acer
 */
public class ViewSanPham extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm1 = new DefaultTableModel();
    private ViewSanPhamService vsp = new ViewSanPhamServiceImpl();
    private List<ViewCTSPResponse> listVCTSP = new ArrayList<>();
    private DefaultComboBoxModel cbb2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb3 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb4 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb5 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb6 = new DefaultComboBoxModel();
    private List<KichCo> listkc = new ArrayList<>();
    private List<MauSac> listms = new ArrayList<>();
    private List<Hang> listh = new ArrayList<>();
    private List<Loai> listl = new ArrayList<>();
    private List<ChatLieu> listcl = new ArrayList<>();
    private ViewThuocTinhService vtts = new ViewThuocTinhServiceImpl();
    public int index = -1;

    /**
     * Creates new form ViewSanPham
     */
    public ViewSanPham(Account a) {
        initComponents();
        cbbMauSac.setModel(cbb2);
        cbbLoaiSP.setModel(cbb3);
        cbbKichCo.setModel(cbb4);
        cbbHang.setModel(cbb5);
        cbbChatLieu.setModel(cbb6);
        listkc = vtts.getAllKichCo();
        listms = vtts.getAllMauSac();
        listh = vtts.getAllHang();
        listl = vtts.getAllLoai();
        listcl = vtts.getAllChatLieu();
        cbbHang(listh);
        cbbChatLieu(listcl);
        cbbMS(listms);
        cbbKC(listkc);
        cbbLoai(listl);
        txtNgayNhap.disable();
        tbThuocTinh.setModel(dtm1);
        tbSanPham.setModel(dtm);
        String[] headerSanPham = {"Mã", "Tên", "Màu sắc", "Hãng", "Kích cỡ", "Chất liệu", "Loại", "Ngày nhập", "Số Lượng tồn", "Giá", "Khuyến mại"};
        String[] headerThuocTinh = {"STT", "Mã", "Tên"};
        String[] headerSanPhamAn = {"Mã", "Tên", "Màu sắc", "Hãng", "Kích cỡ", "Chất liệu", "Loại", "Ngày nhập", "Số Lượng tồn", "Giá"};
        dtm1.setColumnIdentifiers(headerThuocTinh);
        dtm.setColumnIdentifiers(headerSanPham);
        listVCTSP = vsp.getAllSP();
        radioChatLieu.setSelected(true);
        showDataTableCTSanPham(listVCTSP);
        txtMa.setEnabled(false);
        txtMaThuocTinh.setEnabled(false);
        thuocTinh();
    }

    private void cbbMS(List<MauSac> list) {
        cbb2.removeAllElements();
        for (MauSac ms : list) {
            cbb2.addElement(ms.getTen());
        }
    }

    private void cbbLoai(List<Loai> list) {
        cbb3.removeAllElements();
        for (Loai l : list) {
            cbb3.addElement(l.getTen());
        }
    }

    private void cbbChatLieu(List<ChatLieu> list) {
        cbb6.removeAllElements();
        for (ChatLieu cl : list) {
            cbb6.addElement(cl.getTen());
        }
    }

    private void cbbHang(List<Hang> list) {
        cbb5.removeAllElements();
        for (Hang h : list) {
            cbb5.addElement(h.getTen());
        }
    }

    private void cbbKC(List<KichCo> list) {
        cbb4.removeAllElements();
        for (KichCo kc : list) {
            cbb4.addElement(kc.getTen());
        }
    }

    private void showDataTableCTSanPham(List<ViewCTSPResponse> list) {
        dtm.setRowCount(0);
        for (ViewCTSPResponse vctspr : list) {
            dtm.addRow(vctspr.toDataRow1());
        }
    }

    private void showDataTableMauSac(List<MauSac> list) {
        dtm1.setRowCount(0);
        int i = 1;
        for (MauSac sp : list) {
            dtm1.addRow(new Object[]{i, "MS" + sp.getMa(), sp.getTen()});
            i++;
        }
    }

    private void showDataTableKichCo(List<KichCo> list) {
        dtm1.setRowCount(0);
        int i = 1;
        for (KichCo sp : list) {
            dtm1.addRow(new Object[]{i, "KC" + sp.getMa(), sp.getTen()});
            i++;
        }
    }

    private void showDataTableHang(List<Hang> list) {
        dtm1.setRowCount(0);
        int i = 1;
        for (Hang sp : list) {
            dtm1.addRow(new Object[]{i, "H" + sp.getMa(), sp.getTen()});
            i++;
        }
    }

    private void showDataTableLoai(List<Loai> list) {
        dtm1.setRowCount(0);
        int i = 1;
        for (Loai sp : list) {
            dtm1.addRow(new Object[]{i, "L" + sp.getMa(), sp.getTen()});
            i++;
        }
    }

    private void showDataTableChatLieu(List<ChatLieu> list) {
        dtm1.setRowCount(0);
        int i = 1;
        for (ChatLieu sp : list) {
            dtm1.addRow(new Object[]{i, "CL" + sp.getMa(), sp.getTen()});
            i++;
        }
    }

    private void thuocTinh() {
        if (radioChatLieu.isSelected() == true) {
            listcl = vtts.getAllChatLieu();
            showDataTableChatLieu(listcl);
        } else if (radioLoaiSanPham.isSelected() == true) {
            listl = vtts.getAllLoai();
            showDataTableLoai(listl);
        } else if (radioKichCo.isSelected() == true) {
            listkc = vtts.getAllKichCo();
            showDataTableKichCo(listkc);
        } else if (radioHang.isSelected() == true) {
            listh = vtts.getAllHang();
            showDataTableHang(listh);
        } else if (radioMauSac.isSelected() == true) {
            listms = vtts.getAllMauSac();
            showDataTableMauSac(listms);
        }

    }

    private void fill(int row) {
        txtMa.setText("SP" + listVCTSP.get(row).getMa());
        txtTenSP.setText(listVCTSP.get(row).getTen());
        cbbMauSac.setSelectedItem(listVCTSP.get(row).getMauSac().getTen());
        cbbLoaiSP.setSelectedItem(listVCTSP.get(row).getLoai().getTen());
        cbbKichCo.setSelectedItem(listVCTSP.get(row).getKichCo().getTen());
        cbbHang.setSelectedItem(listVCTSP.get(row).getHang().getTen());
        cbbChatLieu.setSelectedItem(listVCTSP.get(row).getChatLieu().getTen());
        txtGiaBan.setText(String.valueOf(listVCTSP.get(row).getGia()));
        txtNgayNhap.setText(String.valueOf(listVCTSP.get(row).getNgayNhap()));
        txtSoLuong.setText(String.valueOf(listVCTSP.get(row).getSoLuongTon()));
        tbSanPham.setRowSelectionAllowed(true);

    }

    private void fill1(int row) {
        if (radioMauSac.isSelected()) {
            txtMaThuocTinh.setText("MS" + listms.get(row).getMa());
            txtThuocTinh.setText(listms.get(row).getTen());
        } else if (radioLoaiSanPham.isSelected()) {
            txtMaThuocTinh.setText("L" + listl.get(row).getMa());
            txtThuocTinh.setText(listl.get(row).getTen());
        } else if (radioKichCo.isSelected()) {
            txtMaThuocTinh.setText("KC" + listkc.get(row).getMa());
            txtThuocTinh.setText(listkc.get(row).getTen());
        } else if (radioHang.isSelected()) {
            txtMaThuocTinh.setText("H" + listh.get(row).getMa());
            txtThuocTinh.setText(listh.get(row).getTen());
        } else {
            txtMaThuocTinh.setText("CL" + listcl.get(row).getMa());
            txtThuocTinh.setText(listcl.get(row).getTen());
        }
    }

    private String getDate() {
        java.util.Date d = new java.util.Date();
        int day = d.getDate();
        int month = d.getMonth() + 1;
        int year = d.getYear() + 1900;
        String ngayThanhToan = month + "/" + day + "/" + year;
        return ngayThanhToan;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        ViewMS = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenMS = new javax.swing.JTextField();
        btnThemMS = new javax.swing.JButton();
        ViewHang = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTenHang = new javax.swing.JTextField();
        btnThemHang = new javax.swing.JButton();
        ViewLoai = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTenLoai = new javax.swing.JTextField();
        btnThemLoai = new javax.swing.JButton();
        ViewKichCo = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTenKichCo = new javax.swing.JTextField();
        btnThemKC = new javax.swing.JButton();
        ViewChatLieu = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTenChatLieu = new javax.swing.JTextField();
        btnThemChatLieu = new javax.swing.JButton();
        TBPaneSP = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbThuocTinh = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtThuocTinh = new javax.swing.JTextField();
        radioMauSac = new javax.swing.JRadioButton();
        radioHang = new javax.swing.JRadioButton();
        radioKichCo = new javax.swing.JRadioButton();
        radioChatLieu = new javax.swing.JRadioButton();
        radioLoaiSanPham = new javax.swing.JRadioButton();
        btnSuaThuocTinhSP = new javax.swing.JButton();
        btnThemThuocTinhSP = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        btnLamMoiThuocTinh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        PanelSP = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        cbbLoaiSP = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbHang = new javax.swing.JComboBox<>();
        cbbChatLieu = new javax.swing.JComboBox<>();
        cbbKichCo = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        btnThemChiTietSanPham = new javax.swing.JButton();
        btnSuaChiTietSanPham = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnLamMoi1 = new javax.swing.JButton();
        btnDocFile = new javax.swing.JButton();
        txtTenSP = new javax.swing.JTextField();
        btnMauSac = new javax.swing.JButton();
        btnLoaiSP = new javax.swing.JButton();
        btnHang = new javax.swing.JButton();
        btnKichCo = new javax.swing.JButton();
        btnChatLieu = new javax.swing.JButton();
        PanelDSSP = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        txtSeach = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btndau = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btncuoi = new javax.swing.JButton();

        ViewMS.setMaximumSize(new java.awt.Dimension(400, 167));
        ViewMS.setMinimumSize(new java.awt.Dimension(400, 167));
        ViewMS.setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(400, 167));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 167));

        jLabel1.setText("Tên màu sắc");

        btnThemMS.setText("Thêm");
        btnThemMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(txtTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btnThemMS)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtTenMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemMS)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewMSLayout = new javax.swing.GroupLayout(ViewMS.getContentPane());
        ViewMS.getContentPane().setLayout(ViewMSLayout);
        ViewMSLayout.setHorizontalGroup(
            ViewMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ViewMSLayout.setVerticalGroup(
            ViewMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewMSLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ViewHang.setMaximumSize(new java.awt.Dimension(400, 167));
        ViewHang.setMinimumSize(new java.awt.Dimension(400, 167));
        ViewHang.setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMaximumSize(new java.awt.Dimension(400, 167));
        jPanel5.setMinimumSize(new java.awt.Dimension(400, 167));

        jLabel4.setText("Tên hãng");

        btnThemHang.setText("Thêm");
        btnThemHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btnThemHang)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemHang)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewHangLayout = new javax.swing.GroupLayout(ViewHang.getContentPane());
        ViewHang.getContentPane().setLayout(ViewHangLayout);
        ViewHangLayout.setHorizontalGroup(
            ViewHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ViewHangLayout.setVerticalGroup(
            ViewHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewHangLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ViewLoai.setMaximumSize(new java.awt.Dimension(400, 167));
        ViewLoai.setMinimumSize(new java.awt.Dimension(400, 167));
        ViewLoai.setResizable(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(400, 167));
        jPanel6.setMinimumSize(new java.awt.Dimension(400, 167));

        jLabel5.setText("Tên loại");

        btnThemLoai.setText("Thêm");
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(30, 30, 30)
                        .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btnThemLoai)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemLoai)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewLoaiLayout = new javax.swing.GroupLayout(ViewLoai.getContentPane());
        ViewLoai.getContentPane().setLayout(ViewLoaiLayout);
        ViewLoaiLayout.setHorizontalGroup(
            ViewLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ViewLoaiLayout.setVerticalGroup(
            ViewLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewLoaiLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ViewKichCo.setMaximumSize(new java.awt.Dimension(400, 167));
        ViewKichCo.setMinimumSize(new java.awt.Dimension(400, 167));
        ViewKichCo.setResizable(false);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setMaximumSize(new java.awt.Dimension(400, 167));
        jPanel7.setMinimumSize(new java.awt.Dimension(400, 167));

        jLabel6.setText("Tên kích cỡ");

        btnThemKC.setText("Thêm");
        btnThemKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(30, 30, 30)
                        .addComponent(txtTenKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btnThemKC)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTenKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemKC)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewKichCoLayout = new javax.swing.GroupLayout(ViewKichCo.getContentPane());
        ViewKichCo.getContentPane().setLayout(ViewKichCoLayout);
        ViewKichCoLayout.setHorizontalGroup(
            ViewKichCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ViewKichCoLayout.setVerticalGroup(
            ViewKichCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewKichCoLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ViewChatLieu.setMaximumSize(new java.awt.Dimension(400, 167));
        ViewChatLieu.setMinimumSize(new java.awt.Dimension(400, 167));
        ViewChatLieu.setResizable(false);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setMaximumSize(new java.awt.Dimension(400, 167));
        jPanel8.setMinimumSize(new java.awt.Dimension(400, 167));

        jLabel7.setText("Tên chất liệu");

        btnThemChatLieu.setText("Thêm");
        btnThemChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30)
                        .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btnThemChatLieu)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemChatLieu)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewChatLieuLayout = new javax.swing.GroupLayout(ViewChatLieu.getContentPane());
        ViewChatLieu.getContentPane().setLayout(ViewChatLieuLayout);
        ViewChatLieuLayout.setHorizontalGroup(
            ViewChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ViewChatLieuLayout.setVerticalGroup(
            ViewChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewChatLieuLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1086, 642));
        setMinimumSize(new java.awt.Dimension(1086, 642));
        setPreferredSize(new java.awt.Dimension(1086, 642));

        TBPaneSP.setBackground(new java.awt.Color(255, 255, 255));
        TBPaneSP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TBPaneSP.setMaximumSize(new java.awt.Dimension(1086, 642));
        TBPaneSP.setMinimumSize(new java.awt.Dimension(1086, 642));
        TBPaneSP.setPreferredSize(new java.awt.Dimension(1086, 642));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1086, 622));
        jPanel2.setMinimumSize(new java.awt.Dimension(1086, 622));
        jPanel2.setPreferredSize(new java.awt.Dimension(1086, 622));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách thuộc tính"));

        tbThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbThuocTinh);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin thuộc tính"));

        jLabel2.setText("Tên thuộc tính");

        buttonGroup1.add(radioMauSac);
        radioMauSac.setText("Màu sắc");
        radioMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMauSacActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioHang);
        radioHang.setText("Hãng");
        radioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioHangActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioKichCo);
        radioKichCo.setText("Kích cỡ");
        radioKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioKichCoActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioChatLieu);
        radioChatLieu.setText("Chất liệu");
        radioChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioChatLieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioLoaiSanPham);
        radioLoaiSanPham.setText("Loại sản phẩm");
        radioLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioLoaiSanPhamActionPerformed(evt);
            }
        });

        btnSuaThuocTinhSP.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaThuocTinhSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaThuocTinhSP.setText("Sửa");
        btnSuaThuocTinhSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhSPActionPerformed(evt);
            }
        });

        btnThemThuocTinhSP.setBackground(new java.awt.Color(0, 204, 204));
        btnThemThuocTinhSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemThuocTinhSP.setText("Thêm");
        btnThemThuocTinhSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhSPActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã");

        btnLamMoiThuocTinh.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoiThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiThuocTinh.setText("Làm mới");
        btnLamMoiThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThuocTinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThemThuocTinhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(btnSuaThuocTinhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(btnLamMoiThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(radioMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(radioKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(radioChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(radioLoaiSanPham)
                                .addGap(151, 151, 151))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioMauSac)
                    .addComponent(radioHang)
                    .addComponent(radioKichCo)
                    .addComponent(radioChatLieu)
                    .addComponent(radioLoaiSanPham)
                    .addComponent(jLabel3)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaThuocTinhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemThuocTinhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        TBPaneSP.addTab("Thuộc tính", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(1086, 622));
        jPanel4.setMinimumSize(new java.awt.Dimension(1086, 622));
        jPanel4.setPreferredSize(new java.awt.Dimension(1086, 622));

        PanelSP.setBackground(new java.awt.Color(255, 255, 255));
        PanelSP.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        PanelSP.setName("hbhjvhjvbj"); // NOI18N

        jLabel19.setText("Tên sản phẩm");

        jLabel20.setText("Loại sản phẩm");

        jLabel21.setText("Giá bán");

        jLabel22.setText("Màu sắc");

        jLabel23.setText("Hãng");

        jLabel24.setText("Chất liệu");

        jLabel25.setText("Kích cỡ");

        jLabel26.setText("Số lượng");

        jLabel27.setText("Ngày nhập");

        cbbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setText("Mã");

        btnThemChiTietSanPham.setBackground(new java.awt.Color(0, 204, 204));
        btnThemChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemChiTietSanPham.setText("Thêm");
        btnThemChiTietSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChiTietSanPhamActionPerformed(evt);
            }
        });

        btnSuaChiTietSanPham.setBackground(new java.awt.Color(0, 204, 204));
        btnSuaChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaChiTietSanPham.setText("Sửa");
        btnSuaChiTietSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaChiTietSanPhamActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnLamMoi1.setBackground(new java.awt.Color(255, 102, 255));
        btnLamMoi1.setIcon(new ImageIcon
            ("src/main/icon/In.png"));
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        btnDocFile.setBackground(new java.awt.Color(0, 204, 204));
        btnDocFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDocFile.setText("Đọc file");
        btnDocFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocFileActionPerformed(evt);
            }
        });

        btnMauSac.setBackground(new java.awt.Color(0, 204, 204));
        btnMauSac.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnMauSac.setText("+");
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        btnLoaiSP.setBackground(new java.awt.Color(0, 204, 204));
        btnLoaiSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLoaiSP.setText("+");
        btnLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoaiSPActionPerformed(evt);
            }
        });

        btnHang.setBackground(new java.awt.Color(0, 204, 204));
        btnHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHang.setText("+");
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        btnKichCo.setBackground(new java.awt.Color(0, 204, 204));
        btnKichCo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKichCo.setText("+");
        btnKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichCoActionPerformed(evt);
            }
        });

        btnChatLieu.setBackground(new java.awt.Color(0, 204, 204));
        btnChatLieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChatLieu.setText("+");
        btnChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSPLayout = new javax.swing.GroupLayout(PanelSP);
        PanelSP.setLayout(PanelSPLayout);
        PanelSPLayout.setHorizontalGroup(
            PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSPLayout.createSequentialGroup()
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19))
                                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelSPLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelSPLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(cbbKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSPLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PanelSPLayout.createSequentialGroup()
                                        .addComponent(btnKichCo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelSPLayout.createSequentialGroup()
                                        .addComponent(btnLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelSPLayout.createSequentialGroup()
                                        .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnHang))
                                    .addGroup(PanelSPLayout.createSequentialGroup()
                                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                        .addComponent(btnMauSac)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChatLieu)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelSPLayout.createSequentialGroup()
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PanelSPLayout.createSequentialGroup()
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThemChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnDocFile, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        PanelSPLayout.setVerticalGroup(
            PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSPLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel28)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27)
                                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSPLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)
                                    .addComponent(btnKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDocFile, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        PanelDSSP.setBackground(new java.awt.Color(255, 255, 255));
        PanelDSSP.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbSanPham.setGridColor(new java.awt.Color(0, 102, 102));
        tbSanPham.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbSanPham);

        btnSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnSearch.setIcon(new ImageIcon
            ("src/main/icon/Search.png"));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btndau.setText("<<");
        btndau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndauActionPerformed(evt);
            }
        });

        btnback.setText("<");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        btnnext.setText(">");
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        btncuoi.setText(">>");
        btncuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncuoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDSSPLayout = new javax.swing.GroupLayout(PanelDSSP);
        PanelDSSP.setLayout(PanelDSSPLayout);
        PanelDSSPLayout.setHorizontalGroup(
            PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDSSPLayout.createSequentialGroup()
                .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDSSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btndau, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSeach, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelDSSPLayout.setVerticalGroup(
            PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDSSPLayout.createSequentialGroup()
                .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtSeach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndau)
                    .addComponent(btnback)
                    .addComponent(btnnext)
                    .addComponent(btncuoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDSSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PanelSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TBPaneSP.addTab("Sản phẩm", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TBPaneSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TBPaneSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncuoiActionPerformed
        listVCTSP = vsp.getAllSP();
        index = listVCTSP.size() - 1;
        fill(index);
    }//GEN-LAST:event_btncuoiActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        listVCTSP = vsp.getAllSP();
        if (index < listVCTSP.size() - 1) {
            index++;
            fill(index);
        } else {
            JOptionPane.showMessageDialog(this, "Không tiến được nữa");
        }
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        listVCTSP = vsp.getAllSP();
        if (index > 0) {
            index--;
            fill(index);
        } else {
            JOptionPane.showMessageDialog(this, "Không lùi được nữa");
        }
    }//GEN-LAST:event_btnbackActionPerformed

    private void btndauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndauActionPerformed
        listVCTSP = vsp.getAllSP();
        index = 0;
        fill(0);
    }//GEN-LAST:event_btndauActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String search = txtSeach.getText();
        List<ViewCTSPResponse> listSearch = new ViewSanPhamServiceImpl().Search(listVCTSP, search);
        showDataTableCTSanPham(listSearch);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        int row = tbSanPham.getSelectedRow();
        txtMa.setEditable(false);
        fill(row);
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("danhsach");
            XSSFRow rows = null;
            Cell cell = null;
            rows = sheet.createRow(3);
            //            "Mã", "Tên", "Màu sắc", "Hãng", "Kích cỡ", "Chất liệu", "Loại", "Ngày nhập", "Số Lượng tồn", "Giá"
            cell = rows.createCell(0, CellType.STRING);
            cell.setCellValue("Mã");
            cell = rows.createCell(1, CellType.STRING);
            cell.setCellValue("Tên");
            cell = rows.createCell(2, CellType.STRING);
            cell.setCellValue("Màu sắc");
            cell = rows.createCell(3, CellType.STRING);
            cell.setCellValue("Hãng");
            cell = rows.createCell(4, CellType.STRING);
            cell.setCellValue("Kích cỡ");
            cell = rows.createCell(5, CellType.STRING);
            cell.setCellValue("Chất liệu");
            cell = rows.createCell(6, CellType.STRING);
            cell.setCellValue("Loại");
            cell = rows.createCell(7, CellType.STRING);
            cell.setCellValue("Ngày nhập");
            cell = rows.createCell(8, CellType.STRING);
            cell.setCellValue("Số Lượng tồn");
            cell = rows.createCell(9, CellType.STRING);
            cell.setCellValue("Giá");
            for (int i = 0; i < listVCTSP.size(); i++) {
                rows = sheet.createRow(4 + i);
                cell = rows.createCell(0, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getMa());
                cell = rows.createCell(1, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getTen());
                cell = rows.createCell(2, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getMauSac().getTen());
                cell = rows.createCell(3, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getHang().getTen());
                cell = rows.createCell(4, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getKichCo().getTen());
                cell = rows.createCell(5, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getChatLieu().getTen());
                cell = rows.createCell(6, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getLoai().getTen());
                cell = rows.createCell(7, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getNgayNhap());
                cell = rows.createCell(8, CellType.NUMERIC);
                cell.setCellValue(listVCTSP.get(i).getSoLuongTon());
                cell = rows.createCell(9, CellType.STRING);
                cell.setCellValue(listVCTSP.get(i).getGia().toPlainString());
            }
            File file = new java.io.File("C:\\Users\\Huy PC\\Desktop\\anh\\danhsachxuat.xlsx");
            try {
                file.getParentFile().mkdir();
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fis = new FileOutputStream(file);
                workbook.write(fis);
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "In thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi mở file");
        }
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtTenSP.setText("");
        cbbMauSac.setSelectedIndex(0);
        cbbLoaiSP.setSelectedIndex(0);
        cbbKichCo.setSelectedIndex(0);
        cbbHang.setSelectedIndex(0);
        cbbChatLieu.setSelectedIndex(0);
        txtGiaBan.setText("");
        txtNgayNhap.setText("");
        txtSeach.setText("");
        txtSoLuong.setText("");
        txtMa.setText("");
        listVCTSP = vsp.getAllSP();
        showDataTableCTSanPham(listVCTSP);
        txtMa.setEditable(true);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaChiTietSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaChiTietSanPhamActionPerformed
        int row = tbSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng để sửa!");
            return;
        } else if (txtGiaBan.getText().isEmpty() || txtSoLuong.getText().isEmpty() || txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(PanelDSSP, "không được để trống");

        } else if (Double.valueOf(String.valueOf(txtGiaBan.getText())) <= 0) {
            JOptionPane.showMessageDialog(PanelDSSP, "Giá bán phải lớn hơn 0");

        } else if (Integer.valueOf(txtSoLuong.getText()) <= 0) {
            JOptionPane.showMessageDialog(PanelDSSP, "Số lượng phải lớn hơn 0");
        } else {
            ViewCTSPResponse vctspr = listVCTSP.get(tbSanPham.getSelectedRow());
            UUID id = vctspr.getId();
            double gia = Double.valueOf(txtGiaBan.getText());
            String ma = txtMa.getText();
            String ngayNhap = txtNgayNhap.getText();
            int soLuong = Integer.valueOf(txtSoLuong.getText());
            BigDecimal giaBan = BigDecimal.valueOf(gia);
            String ten = txtTenSP.getText();
            MauSac mauSac = listms.get(cbbMauSac.getSelectedIndex());
            Hang hang = listh.get(cbbHang.getSelectedIndex());
            Loai loai = listl.get(cbbLoaiSP.getSelectedIndex());
            ChatLieu chatLieu = listcl.get(cbbChatLieu.getSelectedIndex());
            KichCo kichCo = listkc.get(cbbKichCo.getSelectedIndex());
            int trangThai = 1;
            ViewCTSPResponse viewCTSP = new ViewCTSPResponse(vctspr.getMa(), hang, loai, kichCo, mauSac, chatLieu, ngayNhap, soLuong, giaBan, ten, trangThai);
            JOptionPane.showMessageDialog(PanelDSSP, new ViewSanPhamServiceImpl().update(viewCTSP, id));
            listVCTSP = vsp.getAllSP();
            showDataTableCTSanPham(listVCTSP);
        }
    }//GEN-LAST:event_btnSuaChiTietSanPhamActionPerformed

    private void btnThemChiTietSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChiTietSanPhamActionPerformed
        if (txtGiaBan.getText().isEmpty() || txtSoLuong.getText().isEmpty() || txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(PanelDSSP, "không được để trống");

        } else if (Double.valueOf(String.valueOf(txtGiaBan.getText())) <= 0) {
            JOptionPane.showMessageDialog(PanelDSSP, "Giá bán phải lớn hơn 0");

        } else if (Integer.valueOf(txtSoLuong.getText()) <= 0) {
            JOptionPane.showMessageDialog(PanelDSSP, "Số lượng phải lớn hơn 0");

        } else {
            double gia = Double.valueOf(txtGiaBan.getText());
            String ma = txtMa.getText();
            String ngayNhap = getDate();
            int soLuong = Integer.valueOf(txtSoLuong.getText());
            BigDecimal giaBan = BigDecimal.valueOf(gia);
            String ten = txtTenSP.getText();
            MauSac mauSac = listms.get(cbbMauSac.getSelectedIndex());
            Hang hang = listh.get(cbbHang.getSelectedIndex());
            Loai loai = listl.get(cbbLoaiSP.getSelectedIndex());
            ChatLieu chatLieu = listcl.get(cbbChatLieu.getSelectedIndex());
            KichCo kichCo = listkc.get(cbbKichCo.getSelectedIndex());
            int trangThai = 1;
            ViewCTSPResponse viewCTSP = new ViewCTSPResponse(null, hang, loai, kichCo, mauSac, chatLieu, ngayNhap, soLuong, giaBan, ten, trangThai);
            JOptionPane.showMessageDialog(PanelDSSP, new ViewSanPhamServiceImpl().add(viewCTSP));
            listVCTSP = vsp.getAllSP();
            showDataTableCTSanPham(listVCTSP);
            tbSanPham.setRowSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_btnThemChiTietSanPhamActionPerformed

    private void btnLamMoiThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThuocTinhActionPerformed
        txtMaThuocTinh.setText("");
        txtThuocTinh.setText("");
        txtMaThuocTinh.setEditable(true);
    }//GEN-LAST:event_btnLamMoiThuocTinhActionPerformed

    private void btnThemThuocTinhSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhSPActionPerformed

        String tenThuocTinh = txtThuocTinh.getText();
        if (radioMauSac.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddMauSac(new MauSac(0, null, tenThuocTinh)));
            listms = vtts.getAllMauSac();
            showDataTableMauSac(listms);
            cbbMS(listms);
        } else if (radioLoaiSanPham.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddLoai(new Loai(0, null, tenThuocTinh)));
            listl = vtts.getAllLoai();
            showDataTableLoai(listl);
            cbbLoai(listl);
        } else if (radioKichCo.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddKichCo(new KichCo(0, null, tenThuocTinh)));
            listkc = vtts.getAllKichCo();
            showDataTableKichCo(listkc);
            cbbKC(listkc);
        } else if (radioChatLieu.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddChatLieu(new ChatLieu(0, null, tenThuocTinh)));
            listcl = vtts.getAllChatLieu();
            showDataTableChatLieu(listcl);
            cbbChatLieu(listcl);
        } else {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddHang(new Hang(0, null, tenThuocTinh)));
            listh = vtts.getAllHang();
            showDataTableHang(listh);
            cbbHang(listh);
        }
    }//GEN-LAST:event_btnThemThuocTinhSPActionPerformed

    private void btnSuaThuocTinhSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhSPActionPerformed
        int row = tbThuocTinh.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng để sửa!");
            return;
        } else {
            String maThuocTinh = txtMaThuocTinh.getText();
            String tenThuocTinh = txtThuocTinh.getText();
            if (radioMauSac.isSelected()) {
                MauSac ms = listms.get(tbThuocTinh.getSelectedRow());
                int idMauSac = ms.getId();
                JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateMauSac(new MauSac(idMauSac, ms.getMa(), tenThuocTinh)));
                listms = vtts.getAllMauSac();
                showDataTableMauSac(listms);
                cbb2.removeAllElements();
            } else if (radioLoaiSanPham.isSelected()) {
                Loai l = listl.get(tbThuocTinh.getSelectedRow());
                int idLoai = l.getId();
                JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateLoai(new Loai(idLoai, l.getMa(), tenThuocTinh)));
                listl = vtts.getAllLoai();
                showDataTableLoai(listl);
                cbb3.removeAllElements();
            } else if (radioKichCo.isSelected()) {
                KichCo kc = listkc.get(tbThuocTinh.getSelectedRow());
                int idKichCo = kc.getId();
                JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateKichCo(new KichCo(idKichCo, kc.getMa(), tenThuocTinh)));
                listkc = vtts.getAllKichCo();
                cbb4.removeAllElements();
                showDataTableKichCo(listkc);
            } else if (radioChatLieu.isSelected()) {
                ChatLieu cl = listcl.get(tbThuocTinh.getSelectedRow());
                int idChatLieu = cl.getId();
                JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateChatLieu(new ChatLieu(idChatLieu, cl.getMa(), tenThuocTinh)));
                listcl = vtts.getAllChatLieu();
                showDataTableChatLieu(listcl);
                cbb6.removeAllElements();
            } else {
                Hang h = listh.get(tbThuocTinh.getSelectedRow());
                int idHang = h.getId();
                JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateHang(new Hang(idHang, h.getMa(), tenThuocTinh)));
                listh = vtts.getAllHang();
                showDataTableHang(listh);
                cbb5.removeAllElements();
            }
        }
        if (radioMauSac.isSelected()) {
            for (MauSac ms : listms) {
                cbb2.addElement(ms.getTen());
            }
        } else if (radioKichCo.isSelected()) {
            for (KichCo kc : listkc) {
                cbb4.addElement(kc.getTen());
            }
        } else if (radioHang.isSelected()) {
            for (Hang h : listh) {
                cbb5.addElement(h.getTen());
            }
        } else if (radioLoaiSanPham.isSelected()) {
            for (Loai l : listl) {
                cbb3.addElement(l.getTen());
            }
        } else {
            for (ChatLieu cl : listcl) {
                cbb6.addElement(cl.getTen());
            }
        }
    }//GEN-LAST:event_btnSuaThuocTinhSPActionPerformed

    private void radioLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioLoaiSanPhamActionPerformed
        listl = vtts.getAllLoai();
        showDataTableLoai(listl);
    }//GEN-LAST:event_radioLoaiSanPhamActionPerformed

    private void radioChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioChatLieuActionPerformed
        listcl = vtts.getAllChatLieu();
        showDataTableChatLieu(listcl);
    }//GEN-LAST:event_radioChatLieuActionPerformed

    private void radioKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioKichCoActionPerformed
        listkc = vtts.getAllKichCo();
        showDataTableKichCo(listkc);
    }//GEN-LAST:event_radioKichCoActionPerformed

    private void radioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHangActionPerformed
        listh = vtts.getAllHang();
        showDataTableHang(listh);
    }//GEN-LAST:event_radioHangActionPerformed

    private void radioMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMauSacActionPerformed
        listms = vtts.getAllMauSac();
        showDataTableMauSac(listms);
    }//GEN-LAST:event_radioMauSacActionPerformed

    private void tbThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuocTinhMouseClicked
        int row = tbThuocTinh.getSelectedRow();
        txtMaThuocTinh.setEditable(false);
        fill1(row);
    }//GEN-LAST:event_tbThuocTinhMouseClicked

    private void btnDocFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocFileActionPerformed

        JFileChooser fc = new JFileChooser();
        int check = fc.showOpenDialog(null);
        File file = null;
        if (check == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        ImportExcelCTSP excelCTSP = new ImportExcelCTSP();
        try {
            excelCTSP.ImportFile(file.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(ViewSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        listVCTSP = vsp.getAllSP();
        showDataTableCTSanPham(listVCTSP);


    }//GEN-LAST:event_btnDocFileActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        ViewMS.setVisible(true);
        ViewMS.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiSPActionPerformed

        ViewLoai.setVisible(true);
        ViewLoai.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnLoaiSPActionPerformed

    private void btnKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichCoActionPerformed

        ViewKichCo.setVisible(true);
        ViewKichCo.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnKichCoActionPerformed

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed

        ViewHang.setVisible(true);
        ViewHang.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieuActionPerformed

        ViewChatLieu.setVisible(true);
        ViewChatLieu.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnChatLieuActionPerformed

    private void btnThemMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMSActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(ViewMS, vtts.AddMauSac(new MauSac(0, null, txtTenMS.getText())));
        listms = vtts.getAllMauSac();
        cbbMS(listms);
        ViewMS.setVisible(false);
    }//GEN-LAST:event_btnThemMSActionPerformed

    private void btnThemHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHangActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(ViewHang, vtts.AddHang(new Hang(0, null, txtTenHang.getText())));
        listh = vtts.getAllHang();
        cbbHang(listh);
        ViewHang.setVisible(false);
    }//GEN-LAST:event_btnThemHangActionPerformed

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(ViewLoai, vtts.AddLoai(new Loai(0, null, txtTenLoai.getText())));
        listl = vtts.getAllLoai();
        cbbLoai(listl);
        ViewLoai.setVisible(false);
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void btnThemKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKCActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(ViewKichCo, vtts.AddKichCo(new KichCo(0, null, txtTenKichCo.getText())));
        listkc = vtts.getAllKichCo();
        cbbKC(listkc);
        ViewKichCo.setVisible(false);
    }//GEN-LAST:event_btnThemKCActionPerformed

    private void btnThemChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(ViewChatLieu, vtts.AddChatLieu(new ChatLieu(0, null, txtTenKichCo.getText())));
        listcl = vtts.getAllChatLieu();
        cbbChatLieu(listcl);
        ViewChatLieu.setVisible(false);
    }//GEN-LAST:event_btnThemChatLieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDSSP;
    private javax.swing.JPanel PanelSP;
    private javax.swing.JTabbedPane TBPaneSP;
    private javax.swing.JFrame ViewChatLieu;
    private javax.swing.JFrame ViewHang;
    private javax.swing.JFrame ViewKichCo;
    private javax.swing.JFrame ViewLoai;
    private javax.swing.JFrame ViewMS;
    private javax.swing.JButton btnChatLieu;
    private javax.swing.JButton btnDocFile;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnKichCo;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoiThuocTinh;
    private javax.swing.JButton btnLoaiSP;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSuaChiTietSanPham;
    private javax.swing.JButton btnSuaThuocTinhSP;
    private javax.swing.JButton btnThemChatLieu;
    private javax.swing.JButton btnThemChiTietSanPham;
    private javax.swing.JButton btnThemHang;
    private javax.swing.JButton btnThemKC;
    private javax.swing.JButton btnThemLoai;
    private javax.swing.JButton btnThemMS;
    private javax.swing.JButton btnThemThuocTinhSP;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btncuoi;
    private javax.swing.JButton btndau;
    private javax.swing.JButton btnnext;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbKichCo;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JRadioButton radioChatLieu;
    private javax.swing.JRadioButton radioHang;
    private javax.swing.JRadioButton radioKichCo;
    private javax.swing.JRadioButton radioLoaiSanPham;
    private javax.swing.JRadioButton radioMauSac;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbThuocTinh;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtSeach;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenChatLieu;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtTenKichCo;
    private javax.swing.JTextField txtTenLoai;
    private javax.swing.JTextField txtTenMS;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThuocTinh;
    // End of variables declaration//GEN-END:variables

}
