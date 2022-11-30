/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.view;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import com.poly.pro_1041.it17322.group4.service.ViewKhachHangService;
import com.poly.pro_1041.it17322.group4.service.impl.ViewKhachHangServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class ViewKhachHang extends javax.swing.JPanel {

    /**
     * Creates new form ViewKhachHang
     */
    private DefaultTableModel dtm = new DefaultTableModel();
    private ViewKhachHangService khService = new ViewKhachHangServiceImpl();
    private DefaultComboBoxModel dcbb = new DefaultComboBoxModel<>();
    private List<ViewKhachHangRepose> listKH = new ArrayList<>();
    private int index;
    private Account account = new Account();

    public ViewKhachHang(Account a) {
        initComponents();
        this.account = a;
        tbHienThi.setModel(dtm);
        String headers[] = {"Mã", "Họ tên", "Ngày sinh", "Giới tính", "Sdt", "Email", "Địa chỉ", "Ngày tạo", "Ngày chỉnh sửa"};
        dtm.setColumnIdentifiers(headers);
        listKH = khService.getAll();
        showDetail(listKH);
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    private void showDetail(List<ViewKhachHangRepose> lists) {
        dtm.setRowCount(0);
        for (ViewKhachHangRepose viewKH : lists) {
            dtm.addRow(viewKH.toDataRow());
        }
    }

    private void clearForm() {
        txtTenKhachHang.setText("");
        txtNgaySinh.setText("");
        buttonGroup1.clearSelection();
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtMa.setText("");
        tbHienThi.setRowSelectionAllowed(false);
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        labelNgayTao.setText("");
        labelNCS.setText("");
        labelNguoiTao.setText("");
    }

    private void fillIndex() {
        ViewKhachHangRepose kh = listKH.get(index);
        txtTenKhachHang.setText(kh.getHoTen());
        txtNgaySinh.setText(kh.getNgaySinh());
        if (kh.isGioiTinh()) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }
        txtSDT.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
        labelNgayTao.setText(kh.getNgayTao());
        labelNCS.setText(kh.getNgayChinhSua());
        labelNguoiTao.setText(kh.getNguoiTao().toString());
        txtMa.setText(kh.getMa());
        labelNguoiTao.setText(khService.getOneNguoiTao(String.valueOf(kh.getNguoiTao())).getHoTen());
        tbHienThi.setRowSelectionInterval(index, index);
        tbHienThi.setRowSelectionAllowed(true);
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        labelNgayTao = new javax.swing.JLabel();
        labelNCS = new javax.swing.JLabel();
        labelNguoiTao = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(1086, 682));
        setMinimumSize(new java.awt.Dimension(1086, 682));
        setPreferredSize(new java.awt.Dimension(1086, 682));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1086, 642));
        jPanel1.setMinimumSize(new java.awt.Dimension(1086, 642));
        jPanel1.setPreferredSize(new java.awt.Dimension(1086, 642));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý khách hàng"));
        jPanel2.setToolTipText("Quản lý khách hàng");
        jPanel2.setMaximumSize(new java.awt.Dimension(1062, 300));
        jPanel2.setMinimumSize(new java.awt.Dimension(1062, 300));
        jPanel2.setPreferredSize(new java.awt.Dimension(1062, 300));

        jLabel2.setText("Tên khách hàng");

        jLabel3.setText("Ngày sinh");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Email");

        jLabel6.setText("Địa chỉ");

        jLabel7.setText("Ngày tạo");

        jLabel8.setText("Ngày chỉnh sửa");

        jLabel9.setText("Giới tính");

        buttonGroup1.add(radioNam);
        radioNam.setSelected(true);
        radioNam.setText("Nam");

        buttonGroup1.add(radioNu);
        radioNu.setText("Nữ");

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel12.setText("Mã khách hàng");

        jLabel13.setText("NguoiTao");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLamMoi)
                        .addGap(36, 36, 36)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua)
                        .addGap(52, 52, 52)
                        .addComponent(btnXoa)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(radioNam)
                                    .addGap(60, 60, 60)
                                    .addComponent(radioNu)
                                    .addGap(121, 121, 121))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(100, 100, 100)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(labelNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNCS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(labelNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addComponent(labelNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioNam)
                            .addComponent(radioNu)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(labelNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(labelNCS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khách hàng"));
        jPanel3.setToolTipText("Quản lý khách hàng");
        jPanel3.setMaximumSize(new java.awt.Dimension(1062, 300));
        jPanel3.setMinimumSize(new java.awt.Dimension(1062, 300));
        jPanel3.setPreferredSize(new java.awt.Dimension(1062, 300));

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHienThi.setMaximumSize(new java.awt.Dimension(1026, 196));
        tbHienThi.setMinimumSize(new java.awt.Dimension(1026, 196));
        tbHienThi.setPreferredSize(new java.awt.Dimension(1026, 196));
        tbHienThi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHienThi);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnTimKiem)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        // TODO add your handling code here:
        if (tbHienThi.getSelectedRow() > -1) {
            index = tbHienThi.getSelectedRow();
            fillIndex();
            btnThem.setEnabled(false);
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
            labelNCS.setVisible(true);
            labelNgayTao.setVisible(true);
        }
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        ViewKhachHangRepose khrepo = listKH.get(tbHienThi.getSelectedRow());
        String delete = khService.delete(khrepo);
        JOptionPane.showMessageDialog(this, delete);
        listKH = khService.getAll();
        clearForm();
        showDetail(listKH);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (!radioNam.isSelected() && !radioNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Giới tính chưa chọn");
        } else if (tbHienThi.getSelectedRow() > -1) {
            String ma = txtMa.getText();
            String ten = txtTenKhachHang.getText();
            String ngaySinh = txtNgaySinh.getText();
            boolean isGioiTinh = radioNam.isSelected();
            boolean gioiTinh;
            if (isGioiTinh) {
                gioiTinh = true;
            } else {
                gioiTinh = false;
            }
            String sdt = txtSDT.getText();
            String email = txtEmail.getText();
            String diaChi = txtDiaChi.getText();
            Date d = new Date();
            int day = d.getDate();
            int month = d.getMonth() + 1;
            int year = d.getYear() + 1900;
            String ngayTao = labelNgayTao.getText();
            String ngayChinhSua = year + "-" + month + "-" + day;
            UUID nguoiChinhSua = account.getId();
            ViewKhachHangRepose view = listKH.get(tbHienThi.getSelectedRow());
            ViewKhachHangRepose viewkh = new ViewKhachHangRepose(ma, ten, ngaySinh, gioiTinh, sdt, email, diaChi, view.getNgayTao(), ngayChinhSua, view.getNguoiTao(), nguoiChinhSua);
            viewkh.setId(view.getId());
            String update = khService.update(viewkh);
            JOptionPane.showMessageDialog(this, update);
            listKH = khService.getAll();
            showDetail(listKH);
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (!radioNam.isSelected() && !radioNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Giới tính chưa chọn");
        } else {
            UUID id = null;
            String ma = txtMa.getText();
            String ten = txtTenKhachHang.getText();
            String ngaySinh = txtNgaySinh.getText();
            boolean isGioiTinh = radioNam.isSelected();
            boolean gioiTinh;
            if (isGioiTinh) {
                gioiTinh = true;
            } else {
                gioiTinh = false;
            }
            String sdt = txtSDT.getText();
            String email = txtEmail.getText();
            String diaChi = txtDiaChi.getText();
            Date d = new Date();
            int day = d.getDate();
            int month = d.getMonth() + 1;
            int year = d.getYear() + 1900;
            String ngayTao = year + "-" + month + "-" + day;
            String ngayChinhSua = null;
            UUID nguoiTao = account.getId();
            String nguoiChinhSua = null;
            ViewKhachHangRepose viewkh = new ViewKhachHangRepose(ma, ten, ngaySinh, gioiTinh, sdt, email, diaChi, ngayTao, ngayChinhSua, nguoiTao, null);
            String add = khService.add(viewkh);
            JOptionPane.showMessageDialog(this, add);
            listKH = khService.getAll();
            showDetail(listKH);

        }
    }//GEN-LAST:event_btnThemActionPerformed
    private String getDate() {
        java.util.Date d = new java.util.Date();
        int day = d.getDate();
        int month = d.getMonth() + 1;
        int year = d.getYear() + 1900;
        String ngayThanhToan = month + "/" + day + "/" + year;
        return ngayThanhToan;
    }
    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String search = txtTimKiem.getText();
        List<ViewKhachHangRepose> listSearchs = new ViewKhachHangServiceImpl().searchByName(listKH, search);
        showDetail(listSearchs);
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNCS;
    private javax.swing.JLabel labelNgayTao;
    private javax.swing.JLabel labelNguoiTao;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
