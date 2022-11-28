/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.view;

import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.LoaiKM;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiKM;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import com.poly.pro_1041.it17322.group4.response.ViewKhuyenMaiResponse;
import com.poly.pro_1041.it17322.group4.response.ViewLoaiKMResponse;
import com.poly.pro_1041.it17322.group4.response.ViewTrangThaiKMResponse;
import com.poly.pro_1041.it17322.group4.service.ViewHoaDonService;
import com.poly.pro_1041.it17322.group4.service.ViewKhuyenMaiService;
import com.poly.pro_1041.it17322.group4.service.ViewLoaiService;
import com.poly.pro_1041.it17322.group4.service.impl.ViewHoaDonServiceImpl;
import com.poly.pro_1041.it17322.group4.service.impl.ViewKhuyenMaiServiceImpl;
import com.poly.pro_1041.it17322.group4.service.impl.ViewLoaiServiceImpl;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ViewKhuyenMai extends javax.swing.JPanel {

    /**
     * Creates new form ViewKhuyenMai
     */
    private ViewHoaDonService vhds = new ViewHoaDonServiceImpl();
    private ViewKhuyenMaiService vkms = new ViewKhuyenMaiServiceImpl();
    private ViewLoaiService vls = new ViewLoaiServiceImpl();
    private DefaultTableModel tbsp = new DefaultTableModel();
    private DefaultTableModel tbkm = new DefaultTableModel();
    private DefaultComboBoxModel dcbmLoaiSP = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmLoaiKM = new DefaultComboBoxModel();
    private List<ViewKhuyenMaiResponse> listVKM = new ArrayList<>();
    private List<ViewLoaiKMResponse> listVLKM = new ArrayList<>();
    private List<ViewTrangThaiKMResponse> listVTTKM = new ArrayList<>();
    private List<ViewCTSPResponse> listSanPham = new ArrayList<>();
    private List<Loai> listLoaiSP = new ArrayList<>();
    
    int index;
    
    public ViewKhuyenMai() {
        initComponents();
        
        tbSanPham.setModel(tbsp);
        tbKhuyenMai.setModel(tbkm);
        cbbApDung.setModel(dcbmLoaiSP);
        cbbHinhThuc.setModel(dcbmLoaiKM);
        
        String[] headerSanPham = {"Tên SP", "Màu sắc", "Hãng", "Kích cỡ", "Chất liệu", "Loại", "Còn tồn"};
        String[] headerKhuyenMai = {"STT", "Tên KM", "Ngày bắt đầu", "Ngày kết thúc", "% Khuyến mãi", "Loại khuyến mãi"};
        tbsp.setColumnIdentifiers(headerSanPham);
        tbkm.setColumnIdentifiers(headerKhuyenMai);
        
        listVKM = vkms.getAllKM();
        listVLKM = vkms.getAllLKM();
        listVTTKM = vkms.getAllTTKM();
        listSanPham = vhds.getAllSP();
        listLoaiSP = vls.getAll();
        
        showDataTableKhuyenMai(listVKM);
        showDataTableSanPham(listSanPham);
        
        for (Loai loaiSP : listLoaiSP) {
            dcbmLoaiSP.addElement(loaiSP.getTen());
        }
        for (ViewLoaiKMResponse viewLoaiKMResponse : listVLKM) {
            dcbmLoaiKM.addElement(viewLoaiKMResponse.getTen());
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbKhuyenMai = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cbbApDung = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbbHinhThuc = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        txtTenCT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBatDau = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtKetThuc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rdoHoatDong = new javax.swing.JRadioButton();
        rdoNgungHoatDong = new javax.swing.JRadioButton();
        btnLuu = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbbLoaiKM = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtLoaiSP = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Danh sách khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(759, 158));

        tbKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbKhuyenMai);

        txtSearch.setText("Search...");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(759, 158));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSanPham);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Áp dụng cho");

        cbbApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbApDungActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Hình thức giảm giá");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cbbHinhThuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbbApDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Danh sách khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(759, 158));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("Hình thức giảm giá");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Tên chương trình");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Mức giảm giá(%)");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("TG bắt đầu");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setText("TG kết thúc");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setText("Trạng thái");

        buttonGroup1.add(rdoHoatDong);
        rdoHoatDong.setSelected(true);
        rdoHoatDong.setText("Đang hoạt động");

        buttonGroup1.add(rdoNgungHoatDong);
        rdoNgungHoatDong.setText("Ngừng hoạt dộng");

        btnLuu.setBackground(new java.awt.Color(0, 102, 102));
        btnLuu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 102, 102));
        btnXoa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(0, 102, 102));
        btnMoi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnMoi.setText("Tạo mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Loại KM");

        cbbLoaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo %", "Theo số tiền" }));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Mã KM");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Loại SP");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(59, 59, 59)
                                .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(23, 23, 23)
                                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(57, 57, 57)
                                .addComponent(txtKetThuc))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenCT, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(txtMaKM))))
                        .addGap(84, 84, 84)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox6, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(rdoHoatDong)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rdoNgungHoatDong))))
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTenCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbLoaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoHoatDong)
                    .addComponent(rdoNgungHoatDong))
                .addGap(27, 27, 27)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhuyenMaiMouseClicked
        index = tbKhuyenMai.getSelectedRow();
        if (index == -1) {
            return;
        }
        txtMaKM.setText(listVKM.get(index).getMa().toString());
        txtTenCT.setText(tbKhuyenMai.getValueAt(index, 1).toString());
        txtGiamGia.setText(tbKhuyenMai.getValueAt(index, 4).toString());
        txtBatDau.setText(tbKhuyenMai.getValueAt(index, 2).toString());
        txtKetThuc.setText(tbKhuyenMai.getValueAt(index, 3).toString());
        
        if(tbKhuyenMai.getValueAt(index,5).toString().equalsIgnoreCase("Giảm theo %")){
            cbbLoaiKM.setSelectedIndex(0);
            
        }else{
            cbbLoaiKM.setSelectedIndex(1);
        }
        if (listVKM.get(index).getTrangThaiKM().getTen().equalsIgnoreCase("Able")) {
            rdoHoatDong.setSelected(true);
        } else {
            rdoNgungHoatDong.setSelected(true);
        }

    }//GEN-LAST:event_tbKhuyenMaiMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            int ttkm ;
            if(rdoHoatDong.isSelected()){
                ttkm = 9;
            }else{
                ttkm = 10;
            }
            int lkm;
            if(cbbLoaiKM.getSelectedIndex()==0){
                lkm = 10;
            }else{
                lkm = 11;
            }
            TrangThaiKM trangThaiKM = new TrangThaiKM(ttkm,null,null);
            LoaiKM loaiKM = new LoaiKM(lkm,null,null);
            
            ViewKhuyenMaiResponse vkmr = new ViewKhuyenMaiResponse(null,trangThaiKM,loaiKM,txtMaKM.getText(),txtTenCT.getText(),txtBatDau.getText(),txtKetThuc.getText(),Float.valueOf(txtGiamGia.getText()),Integer.valueOf(txtLoaiSP.getText()));
            JOptionPane.showMessageDialog(this, vkms.addKhuyenMai(vkmr));
            listVKM = vkms.getAllKM();
            
            showDataTableKhuyenMai(listVKM);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        try {
            txtTenCT.setText(null);
            txtGiamGia.setText(null);
            txtBatDau.setText(null);
            txtKetThuc.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnMoiActionPerformed

    private void cbbApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbApDungActionPerformed
        
    }//GEN-LAST:event_cbbApDungActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        index = tbKhuyenMai.getSelectedRow();
        boolean check = true;
        ViewKhuyenMaiResponse vkmr = listVKM.get(index);
        check = vkms.deleteKhuyenMai(vkmr);
        
        JOptionPane.showMessageDialog(this,"Xóa thành công");
        
        listVKM = vkms.getAllKM();
        showDataTableKhuyenMai(listVKM);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        index = tbKhuyenMai.getSelectedRow();
        
        boolean check = true;
        ViewKhuyenMaiResponse vkmr = listVKM.get(index);
        
        int ttkm ;
            if(rdoHoatDong.isSelected()){
                ttkm = 9;
            }else{
                ttkm = 10;
            }
            int lkm;
            if(cbbLoaiKM.getSelectedIndex()==0){
                lkm = 10;
            }else{
                lkm = 11;
            }
            TrangThaiKM trangThaiKM = new TrangThaiKM(ttkm,null,null);
            LoaiKM loaiKM = new LoaiKM(lkm,null,null);
            
        vkmr = new ViewKhuyenMaiResponse(vkmr.getId(),trangThaiKM,loaiKM,txtMaKM.getText(),txtTenCT.getText(),txtBatDau.getText(),txtKetThuc.getText(),Float.valueOf(txtGiamGia.getText()),Integer.valueOf(txtLoaiSP.getText()));
            
        
        check = vkms.updateKhuyenMai(vkmr);
        
        JOptionPane.showMessageDialog(this,"Update thành công");
        
        
        listVKM = vkms.getAllKM();
        showDataTableKhuyenMai(listVKM);
    }//GEN-LAST:event_btnCapNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbApDung;
    private javax.swing.JComboBox<String> cbbHinhThuc;
    private javax.swing.JComboBox<String> cbbLoaiKM;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoNgungHoatDong;
    private javax.swing.JTable tbKhuyenMai;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtBatDau;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtKetThuc;
    private javax.swing.JTextField txtLoaiSP;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenCT;
    // End of variables declaration//GEN-END:variables

    private void showDataTableKhuyenMai(List<ViewKhuyenMaiResponse> list) {
        tbkm.setRowCount(0);
        int i = 1;
        for (ViewKhuyenMaiResponse viewKhuyenMaiResponse : list) {
            tbkm.addRow(viewKhuyenMaiResponse.toDataRow(i));
            i++;
        }
    }
    
    private void showDataTableSanPham(List<ViewCTSPResponse> list) {
        tbsp.setRowCount(0);
        
        for (ViewCTSPResponse view : list) {
            tbsp.addRow(view.toDataRow());
            
        }
    }
    
}