/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.view;

import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import com.poly.pro_1041.it17322.group4.domainmodel.SanPham;
import com.poly.pro_1041.it17322.group4.repository.SanPhamRepository;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.service.ViewSanPhamService;
import com.poly.pro_1041.it17322.group4.service.ViewThuocTinhService;
import com.poly.pro_1041.it17322.group4.service.impl.ViewSanPhamServiceImpl;
import com.poly.pro_1041.it17322.group4.service.impl.ViewThuocTinhServiceImpl;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class ViewSanPham extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm1 = new DefaultTableModel();
    private ViewSanPhamService vsp = new ViewSanPhamServiceImpl();
    private List<ViewCTSPResponse> listVCTSP = new ArrayList<>();
    private DefaultComboBoxModel cbb1 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb3 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb4 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb5 = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbb6 = new DefaultComboBoxModel();
    private List<SanPham> listSP = new ArrayList<>();
    private List<KichCo> listkc = new ArrayList<>();
    private List<MauSac> listms = new ArrayList<>();
    private List<Hang> listh = new ArrayList<>();
    private List<Loai> listl = new ArrayList<>();
    private List<ChatLieu> listcl = new ArrayList<>();
    private ViewThuocTinhService vtts = new ViewThuocTinhServiceImpl();

    /**
     * Creates new form ViewSanPham
     */
    public ViewSanPham() {
        initComponents();
        cbbTenSP.setModel(cbb1);
        cbbMauSac.setModel(cbb2);
        cbbLoaiSP.setModel(cbb3);
        cbbKichCo.setModel(cbb4);
        cbbHang.setModel(cbb5);
        cbbChatLieu.setModel(cbb6);
        listSP = vtts.getAllSanPham();
        listkc = vtts.getAllKichCo();
        listms = vtts.getAllMauSac();
        listh = vtts.getAllHang();
        listl = vtts.getAllLoai();
        listcl = vtts.getAllChatLieu();
        for (SanPham sp : listSP) {
            cbb1.addElement(sp.getTenSP());
        }
        for (KichCo kc : listkc) {
            cbb4.addElement(kc.getTen());
        }
        for (MauSac ms : listms) {
            cbb2.addElement(ms.getTen());
        }
        for (Hang h : listh) {
            cbb5.addElement(h.getTen());
        }
        for (Loai l : listl) {
            cbb3.addElement(l.getTen());
        }
        for (ChatLieu cl : listcl) {
            cbb6.addElement(cl.getTen());
        }
        tbThuocTinh.setModel(dtm1);
        tbSanPham.setModel(dtm);
        String[] headerSanPham = {"Mã", "Tên", "Màu sắc", "Hãng", "Kích cỡ", "Chất liệu", "Loại", "Ngày nhập", "Số Lượng tồn", "Giá"};
        String[] headerThuocTinh = {"ID", "Mã", "Tên"};
        dtm1.setColumnIdentifiers(headerThuocTinh);
        dtm.setColumnIdentifiers(headerSanPham);
        listVCTSP = vsp.getAllSP();
        radioChatLieu.setSelected(true);
        showDataTableCTSanPham(listVCTSP);
        thuocTinh();
    }

    private void showDataTableCTSanPham(List<ViewCTSPResponse> list) {
        dtm.setRowCount(0);
        for (ViewCTSPResponse vctspr : list) {
            dtm.addRow(vctspr.toDataRow1());
        }
    }

    private void showDataTableSanPham(List<SanPham> list) {
        dtm1.setRowCount(0);
        for (SanPham sp : list) {
            dtm1.addRow(new Object[]{sp.getId(), sp.getMa(), sp.getTenSP()});
        }
    }

    private void showDataTableMauSac(List<MauSac> list) {
        dtm1.setRowCount(0);
        for (MauSac sp : list) {
            dtm1.addRow(new Object[]{sp.getId(), sp.getMa(), sp.getTen()});
        }
    }

    private void showDataTableKichCo(List<KichCo> list) {
        dtm1.setRowCount(0);
        for (KichCo sp : list) {
            dtm1.addRow(new Object[]{sp.getId(), sp.getMa(), sp.getTen()});
        }
    }

    private void showDataTableHang(List<Hang> list) {
        dtm1.setRowCount(0);
        for (Hang sp : list) {
            dtm1.addRow(new Object[]{sp.getId(), sp.getMa(), sp.getTen()});
        }
    }

    private void showDataTableLoai(List<Loai> list) {
        dtm1.setRowCount(0);
        for (Loai sp : list) {
            dtm1.addRow(new Object[]{sp.getId(), sp.getMa(), sp.getTen()});
        }
    }

    private void showDataTableChatLieu(List<ChatLieu> list) {
        dtm1.setRowCount(0);
        for (ChatLieu sp : list) {
            dtm1.addRow(new Object[]{sp.getId(), sp.getMa(), sp.getTen()});
        }
    }

    private void thuocTinh() {
        if (radioTenSP.isSelected() == true) {
            listSP = vtts.getAllSanPham();
            showDataTableSanPham(listSP);
        } else if (radioChatLieu.isSelected() == true) {
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
        txtMa.setText(listVCTSP.get(row).getMa());
        cbbTenSP.setSelectedIndex(row);
        cbbMauSac.setSelectedIndex(row);
        cbbLoaiSP.setSelectedIndex(row);
        cbbKichCo.setSelectedIndex(row);
        cbbHang.setSelectedIndex(row);
        cbbChatLieu.setSelectedIndex(row);
        txtGiaBan.setText(String.valueOf(listVCTSP.get(row).getGia()));
        txtNgayNhap.setText(String.valueOf(listVCTSP.get(row).getNgayNhap()));
        txtSoLuong.setText(String.valueOf(listVCTSP.get(row).getSoLuongTon()));
    }

    private void fill1(int row) {
        if (radioTenSP.isSelected()) {
            txtMaThuocTinh.setText(listSP.get(row).getMa());
            txtThuocTinh.setText(listSP.get(row).getTenSP());
        } else if (radioMauSac.isSelected()) {
            txtMaThuocTinh.setText(listms.get(row).getMa());
            txtThuocTinh.setText(listms.get(row).getTen());
        } else if (radioLoaiSanPham.isSelected()) {
            txtMaThuocTinh.setText(listl.get(row).getMa());
            txtThuocTinh.setText(listl.get(row).getTen());
        } else if (radioKichCo.isSelected()) {
            txtMaThuocTinh.setText(listkc.get(row).getMa());
            txtThuocTinh.setText(listkc.get(row).getTen());
        } else if (radioHang.isSelected()) {
            txtMaThuocTinh.setText(listh.get(row).getMa());
            txtThuocTinh.setText(listh.get(row).getTen());
        } else {
            txtMaThuocTinh.setText(listcl.get(row).getMa());
            txtThuocTinh.setText(listcl.get(row).getTen());
        }
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
        TBPaneSP = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
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
        cbbTenSP = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        PanelDSSP = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        txtSeach = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
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
        btnSua2 = new javax.swing.JButton();
        btnThem2 = new javax.swing.JButton();
        radioTenSP = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(1086, 642));
        setMinimumSize(new java.awt.Dimension(1086, 642));

        TBPaneSP.setBackground(new java.awt.Color(255, 255, 255));
        TBPaneSP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TBPaneSP.setMaximumSize(new java.awt.Dimension(1070, 633));
        TBPaneSP.setMinimumSize(new java.awt.Dimension(1070, 633));
        TBPaneSP.setPreferredSize(new java.awt.Dimension(1070, 633));

        btnLamMoi.setBackground(new java.awt.Color(153, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

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

        cbbTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setText("Mã");

        javax.swing.GroupLayout PanelSPLayout = new javax.swing.GroupLayout(PanelSP);
        PanelSP.setLayout(PanelSPLayout);
        PanelSPLayout.setHorizontalGroup(
            PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSPLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSPLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSPLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSPLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(30, 30, 30)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSPLayout.createSequentialGroup()
                        .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelSPLayout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        PanelSPLayout.setVerticalGroup(
            PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSPLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel26)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cbbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(PanelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
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
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbSanPham);

        btnSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDSSPLayout = new javax.swing.GroupLayout(PanelDSSP);
        PanelDSSP.setLayout(PanelDSSPLayout);
        PanelDSSPLayout.setHorizontalGroup(
            PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDSSPLayout.createSequentialGroup()
                .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDSSPLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSeach, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelDSSPLayout.setVerticalGroup(
            PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDSSPLayout.createSequentialGroup()
                .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtSeach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnThem.setBackground(new java.awt.Color(102, 255, 0));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 102));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDSSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(PanelSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

        TBPaneSP.addTab("Sản phẩm", jPanel4);

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
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnSua2.setBackground(new java.awt.Color(255, 255, 102));
        btnSua2.setText("Sửa");
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        btnThem2.setBackground(new java.awt.Color(153, 255, 0));
        btnThem2.setText("Thêm");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioTenSP);
        radioTenSP.setText("Tên sản phẩm");
        radioTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTenSPActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnSua2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(646, 646, 646))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioLoaiSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(radioTenSP)
                        .addGap(23, 23, 23))))
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
                    .addComponent(radioTenSP)
                    .addComponent(jLabel3)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
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
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TBPaneSP.addTab("Thuộc tính", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TBPaneSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TBPaneSP, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        int row = tbSanPham.getSelectedRow();
        fill(row);

    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        cbbTenSP.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        cbbLoaiSP.setSelectedIndex(0);
        cbbKichCo.setSelectedIndex(0);
        cbbHang.setSelectedIndex(0);
        cbbChatLieu.setSelectedIndex(0);
        txtGiaBan.setText("");
        txtNgayNhap.setText("");
        txtSeach.setText("");
        txtSoLuong.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tbThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuocTinhMouseClicked
        int row = tbThuocTinh.getSelectedRow();
        fill1(row);
    }//GEN-LAST:event_tbThuocTinhMouseClicked

    private void radioMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMauSacActionPerformed
        listms = vtts.getAllMauSac();
        showDataTableMauSac(listms);
    }//GEN-LAST:event_radioMauSacActionPerformed

    private void radioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHangActionPerformed
        listh = vtts.getAllHang();
        showDataTableHang(listh);
    }//GEN-LAST:event_radioHangActionPerformed

    private void radioKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioKichCoActionPerformed
        listkc = vtts.getAllKichCo();
        showDataTableKichCo(listkc);
    }//GEN-LAST:event_radioKichCoActionPerformed

    private void radioChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioChatLieuActionPerformed
        listcl = vtts.getAllChatLieu();
        showDataTableChatLieu(listcl);
    }//GEN-LAST:event_radioChatLieuActionPerformed

    private void radioLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioLoaiSanPhamActionPerformed
        listl = vtts.getAllLoai();
        showDataTableLoai(listl);
    }//GEN-LAST:event_radioLoaiSanPhamActionPerformed

    private void radioTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTenSPActionPerformed
        listSP = vtts.getAllSanPham();
        showDataTableSanPham(listSP);
    }//GEN-LAST:event_radioTenSPActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        double gia = Double.valueOf(txtGiaBan.getText());
        String ma = txtMa.getText();
        String ngayNhap =(txtNgayNhap.getText());
        int soLuong = Integer.valueOf(txtSoLuong.getText());
        BigDecimal giaBan = BigDecimal.valueOf(gia);
        SanPham sp = listSP.get(cbbTenSP.getSelectedIndex());
        MauSac mauSac = listms.get(cbbMauSac.getSelectedIndex());
        Hang hang = listh.get(cbbMauSac.getSelectedIndex());
        Loai loai = listl.get(cbbLoaiSP.getSelectedIndex());
        ChatLieu chatLieu = listcl.get(cbbChatLieu.getSelectedIndex());
        KichCo kichCo = listkc.get(cbbKichCo.getSelectedIndex());
        ViewCTSPResponse viewCTSP = new ViewCTSPResponse(ma, sp, hang, loai, kichCo, mauSac, chatLieu, ngayNhap, soLuong, giaBan);
        JOptionPane.showMessageDialog(PanelDSSP, new ViewSanPhamServiceImpl().add(viewCTSP));
        listVCTSP = vsp.getAllSP();
        showDataTableCTSanPham(listVCTSP);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ViewCTSPResponse vctspr = listVCTSP.get(tbSanPham.getSelectedRow());
        UUID id = vctspr.getId();
        double gia = Double.valueOf(txtGiaBan.getText());
        String ma = txtMa.getText();
        String ngayNhap = txtNgayNhap.getText();
        int soLuong = Integer.valueOf(txtSoLuong.getText());
        BigDecimal giaBan = BigDecimal.valueOf(gia);
        SanPham sp = listSP.get(cbbTenSP.getSelectedIndex());
        MauSac mauSac = listms.get(cbbMauSac.getSelectedIndex());
        Hang hang = listh.get(cbbMauSac.getSelectedIndex());
        Loai loai = listl.get(cbbLoaiSP.getSelectedIndex());
        ChatLieu chatLieu = listcl.get(cbbChatLieu.getSelectedIndex());
        KichCo kichCo = listkc.get(cbbKichCo.getSelectedIndex());
        ViewCTSPResponse viewCTSP = new ViewCTSPResponse(ma, sp, hang, loai, kichCo, mauSac, chatLieu, ngayNhap, soLuong, giaBan);
        JOptionPane.showMessageDialog(PanelDSSP, new ViewSanPhamServiceImpl().update(viewCTSP, id));
        listVCTSP = vsp.getAllSP();
        showDataTableCTSanPham(listVCTSP);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        String maThuocTinh = txtMaThuocTinh.getText();
        String tenThuocTinh = txtThuocTinh.getText();
        if (radioTenSP.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddSanPham(new SanPham(null, maThuocTinh, tenThuocTinh)));
            listSP = vtts.getAllSanPham();
            showDataTableSanPham(listSP);
        } else if (radioMauSac.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddMauSac(new MauSac(0, maThuocTinh, tenThuocTinh)));
            listms = vtts.getAllMauSac();
            showDataTableMauSac(listms);
        } else if (radioLoaiSanPham.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddLoai(new Loai(0, maThuocTinh, tenThuocTinh)));
            listl = vtts.getAllLoai();
            showDataTableLoai(listl);
        } else if (radioKichCo.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddKichCo(new KichCo(0, maThuocTinh, tenThuocTinh)));
            listkc = vtts.getAllKichCo();
            showDataTableKichCo(listkc);
        } else if (radioChatLieu.isSelected()) {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddChatLieu(new ChatLieu(0, maThuocTinh, tenThuocTinh)));
            listcl = vtts.getAllChatLieu();
            showDataTableChatLieu(listcl);
        } else {
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().AddHang(new Hang(0, maThuocTinh, tenThuocTinh)));
            listh = vtts.getAllHang();
            showDataTableHang(listh);
        }
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String search = txtSeach.getText();
        List<ViewCTSPResponse> listSearch = new ViewSanPhamServiceImpl().Search(listVCTSP, search);
        showDataTableCTSanPham(listSearch);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        String maThuocTinh = txtMaThuocTinh.getText();
        String tenThuocTinh = txtThuocTinh.getText();
        if (radioTenSP.isSelected()) {
            SanPham sp = listSP.get(tbThuocTinh.getSelectedRow());
            UUID idSanPham = sp.getId();
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateSanPham(new SanPham(idSanPham, maThuocTinh, tenThuocTinh)));
            listSP = vtts.getAllSanPham();
            showDataTableSanPham(listSP);
        } else if (radioMauSac.isSelected()) {
            MauSac ms = listms.get(tbThuocTinh.getSelectedRow());
            int idMauSac = ms.getId();
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateMauSac(new MauSac(idMauSac, maThuocTinh, tenThuocTinh)));
            listms = vtts.getAllMauSac();
            showDataTableMauSac(listms);
        } else if (radioLoaiSanPham.isSelected()) {
            Loai l = listl.get(tbThuocTinh.getSelectedRow());
            int idLoai = l.getId();
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateLoai(new Loai(idLoai, maThuocTinh, tenThuocTinh)));
            listl = vtts.getAllLoai();
            showDataTableLoai(listl);
        } else if (radioKichCo.isSelected()) {
            KichCo kc = listkc.get(tbThuocTinh.getSelectedRow());
            int idKichCo = kc.getId();
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateKichCo(new KichCo(idKichCo, maThuocTinh, tenThuocTinh)));
            listkc = vtts.getAllKichCo();
            showDataTableKichCo(listkc);
        } else if (radioChatLieu.isSelected()) {
            ChatLieu cl = listcl.get(tbThuocTinh.getSelectedRow());
            int idChatLieu = cl.getId();
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateChatLieu(new ChatLieu(idChatLieu, maThuocTinh, tenThuocTinh)));
            listcl = vtts.getAllChatLieu();
            showDataTableChatLieu(listcl);
        } else {
            Hang h = listh.get(tbThuocTinh.getSelectedRow());
            int idHang = h.getId();
            JOptionPane.showMessageDialog(PanelDSSP, new ViewThuocTinhServiceImpl().UpdateHang(new Hang(idHang, maThuocTinh, tenThuocTinh)));
            listh = vtts.getAllHang();
            showDataTableHang(listh);
        }
    }//GEN-LAST:event_btnSua2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDSSP;
    private javax.swing.JPanel PanelSP;
    private javax.swing.JTabbedPane TBPaneSP;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbKichCo;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbTenSP;
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
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JRadioButton radioChatLieu;
    private javax.swing.JRadioButton radioHang;
    private javax.swing.JRadioButton radioKichCo;
    private javax.swing.JRadioButton radioLoaiSanPham;
    private javax.swing.JRadioButton radioMauSac;
    private javax.swing.JRadioButton radioTenSP;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbThuocTinh;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtSeach;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThuocTinh;
    // End of variables declaration//GEN-END:variables
}
