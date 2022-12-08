package com.poly.pro_1041.it17322.group4.view;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import com.poly.pro_1041.it17322.group4.service.ViewHoaDonService;
import com.poly.pro_1041.it17322.group4.service.ViewTTOrder;
import com.poly.pro_1041.it17322.group4.service.impl.ViewHoaDonServiceImpl;
import com.poly.pro_1041.it17322.group4.service.impl.ViewTTOrderImpl;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author qcuong
 */
public class ViewLichSu extends javax.swing.JPanel {

    private DefaultTableModel dtmHD;
    private DefaultTableModel dtmSP;
    private List<ViewHoaDonResponse> listHoaDon;
    private List<ViewHDCTResponse> listSanPham;
    private List<TrangThaiOrder> listTTO;
    private ViewTTOrder viewTTOrder;
    private ViewHoaDonService viewHoaDonService;
    private DefaultComboBoxModel dcbm;
    private Account a = new Account();

    public ViewLichSu(Account account) {
        initComponents();
        tbDSHD.setModel(dtmHD = new DefaultTableModel());
        tbDSSP.setModel(dtmSP = new DefaultTableModel());
        cbbTTO.setModel(dcbm = new DefaultComboBoxModel());
        a = account;
        listHoaDon = new ArrayList<>();
        listSanPham = new ArrayList<>();
        listTTO = new ArrayList<>();
        viewHoaDonService = new ViewHoaDonServiceImpl();
        viewTTOrder = new ViewTTOrderImpl();

        String[] headerHD = {"STT", "Tên NV", "Tên KH", "Tên hóa đơn", "Trạng thái",
            "Ngày tạo", "Ngày thanh toán", "Tổng tiền"};
        dtmHD.setColumnIdentifiers(headerHD);
        String[] headerSP = {"STT", "Tên sản phẩm", "Màu sắc", "Hãng",
            "Kích cỡ", "Chất liệu", "Loại", "Số lượng", "Giá"};
        dtmSP.setColumnIdentifiers(headerSP);

        listTTO = viewTTOrder.getAll();
        listHoaDon = viewHoaDonService.getAllHD();
        showDataHD(listHoaDon);

        for (TrangThaiOrder tto : listTTO) {
            dcbm.addElement(tto.getTen());
        }
    }

    public void showDataHD(List<ViewHoaDonResponse> list) {
        dtmHD.setRowCount(0);
        int k = 0;
        for (ViewHoaDonResponse viewHoaDonResponse : list) {
            dtmHD.addRow(viewHoaDonResponse.toDataRowLS(k));
            k++;
        }
    }

    public void showDataSP(List<ViewHDCTResponse> list) {
        dtmSP.setRowCount(0);
        int k = 0;
        for (ViewHDCTResponse viewHDCTResponse : list) {
            dtmSP.addRow(viewHDCTResponse.toDataRowLS(k));
            k++;
        }
    }

    public void fillData(int index) {
        ViewHoaDonResponse viewHoaDonResponse = listHoaDon.get(index);
        if (viewHoaDonResponse.getKhachHang() != null) {
            lblTenKhachHang.setText(viewHoaDonResponse.getKhachHang().getHoTen());
            lblSDT.setText(viewHoaDonResponse.getKhachHang().getSdt());
            lblDiaChi.setText(viewHoaDonResponse.getKhachHang().getDiaChi());
        } else {
            lblTenKhachHang.setText("");
            lblSDT.setText("");
            lblDiaChi.setText("");
        }
        lblTenNV.setText(viewHoaDonResponse.getAccount().getHoTen());
        lblTongTien.setText(String.valueOf(viewHoaDonResponse.getTongTien()));
        lblNgayTao.setText(viewHoaDonResponse.getNgaoTao().toString());
        lblTrangThai.setText(String.valueOf(viewHoaDonResponse.getTto().getTen()));
        UUID id = viewHoaDonResponse.getId();
        listSanPham = viewHoaDonService.getOneHD(id);
        showDataSP(listSanPham);
    }

    public boolean checkNgay() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date ngayBatDau = java.sql.Date.valueOf(datePicker1.getDate());
        Date ngayKetThuc = java.sql.Date.valueOf(datePicker3.getDate());
        c1.setTime(ngayBatDau);
        c2.setTime(ngayKetThuc);
        long a = (c1.getTime().getTime()) / (24 * 3600 * 1000);
        long b = (c2.getTime().getTime()) / (24 * 3600 * 1000);
        if (a < b) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc!");
            return false;
        }
    }

    public void getListByDate() {
        try {
            String tuNgay = String.valueOf(datePicker1);
            String denNgay = String.valueOf(datePicker3);
            listHoaDon = viewHoaDonService.getListByDate(tuNgay, denNgay);
            showDataHD(listHoaDon);
        } catch (Exception e) {
            listHoaDon = null;
            showDataHD(listHoaDon);
        }
    }

    private String tongTienHoaDon() {
        Double tongTien = 0.00;
        for (ViewHDCTResponse vhdctr : listSanPham) {
            tongTien += Double.valueOf(String.valueOf(vhdctr.getGia())) * vhdctr.getSoLuong();
        }
        return String.valueOf(tongTien);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDSHD = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        datePicker3 = new com.github.lgooddatepicker.components.DatePicker();
        jLabel11 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbbTTO = new javax.swing.JComboBox<>();
        btnXuat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDSSP = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLyDoHuy = new javax.swing.JTextArea();
        lblTenNV = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1086, 642));
        setMinimumSize(new java.awt.Dimension(1086, 642));
        setPreferredSize(new java.awt.Dimension(1086, 642));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(767, 305));
        jPanel1.setMinimumSize(new java.awt.Dimension(767, 305));
        jPanel1.setPreferredSize(new java.awt.Dimension(767, 305));

        tbDSHD.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDSHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDSHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDSHD);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setText("Từ ngày:");

        datePicker1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        datePicker3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setText("Đến ngày:");

        btnLoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLoc.setIcon(new ImageIcon
            ("src/main/icon/setting.png"));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setText("Trạng thái:");

        cbbTTO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbTTO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTTOActionPerformed(evt);
            }
        });

        btnXuat.setBackground(new java.awt.Color(0, 102, 102));
        btnXuat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXuat.setIcon(new ImageIcon
            ("src/main/icon/file.png"));
        btnXuat.setText("Xuất hóa đơn");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(datePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLoc)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(40, 40, 40))
                            .addComponent(cbbTTO, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, 0)
                        .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoc)))
                    .addComponent(btnXuat)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(1, 1, 1)
                        .addComponent(cbbTTO, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(767, 305));
        jPanel2.setMinimumSize(new java.awt.Dimension(767, 305));
        jPanel2.setPreferredSize(new java.awt.Dimension(767, 305));

        tbDSSP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbDSSP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Tên NV:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Tên KH:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("SĐT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Tổng tiền hàng:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Ngày tạo:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Trạng thái:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Lý do hủy:");

        txtLyDoHuy.setColumns(20);
        txtLyDoHuy.setRows(5);
        jScrollPane3.setViewportView(txtLyDoHuy);

        lblTenNV.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblTenNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTenKH.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        lblSDT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblSDT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTenKhachHang.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblTenKhachHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblDiaChi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTongTien.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblTongTien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNgayTao.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblNgayTao.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTrangThai.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblTrangThai.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenKH)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblTenKH)
                .addGap(382, 382, 382))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbDSHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDSHDMouseClicked
        int index = tbDSHD.getSelectedRow();
        fillData(index);
    }//GEN-LAST:event_tbDSHDMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        checkNgay();
        getListByDate();
    }//GEN-LAST:event_btnLocActionPerformed

    private void cbbTTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTTOActionPerformed
        String tto = (String) cbbTTO.getSelectedItem();
        int idTT = viewTTOrder.getID(tto).getId();
        listHoaDon = viewHoaDonService.getList(idTT);
        showDataHD(listHoaDon);
    }//GEN-LAST:event_cbbTTOActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        if (tbDSHD.getSelectedRow() > -1) {
            ViewHoaDonResponse vhdr = listHoaDon.get(tbDSHD.getSelectedRow());
            vhdr.setTongTien(BigDecimal.valueOf(Double.valueOf(tongTienHoaDon())));
            try {
                viewHoaDonService.taoFilePDF(vhdr, viewHoaDonService.getOneHD(vhdr.getId()), this.a);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ViewHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnXuatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnXuat;
    private javax.swing.JComboBox<String> cbbTTO;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private com.github.lgooddatepicker.components.DatePicker datePicker3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JTable tbDSHD;
    private javax.swing.JTable tbDSSP;
    private javax.swing.JTextArea txtLyDoHuy;
    // End of variables declaration//GEN-END:variables
}
