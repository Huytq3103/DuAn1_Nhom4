/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import com.poly.pro_1041.it17322.group4.service.ViewHoaDonService;
import com.poly.pro_1041.it17322.group4.service.impl.ViewHoaDonServiceImpl;
import com.poly.pro_1041.it17322.group4.service.impl.ViewSanPhamServiceImpl;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author qcuong
 */
public class ViewHoaDon extends javax.swing.JPanel implements Runnable, ThreadFactory {

    /**
     * Creates new form ViewBanHang
     */
    private ViewHoaDonService vhds = new ViewHoaDonServiceImpl();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm1 = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private DefaultTableModel dtmHD = new DefaultTableModel();
    private DefaultTableModel dtmHDCTTH = new DefaultTableModel();
    private DefaultTableModel dtmCTTH = new DefaultTableModel();
    private DefaultTableModel dtmKhachHang = new DefaultTableModel();
    private List<ViewCTSPResponse> listVCTSP = new ArrayList<>();
    private List<ViewHoaDonResponse> listHD = new ArrayList<>();
    private List<ViewHoaDonResponse> listHDTH = new ArrayList<>();
    private List<ViewHDCTResponse> listHDCT = new ArrayList<>();
    private List<ViewHDCTResponse> listHDCTTraHang = new ArrayList<>();
    private List<TrangThaiOrder> listTTO = new ArrayList<>();
    private List<ViewKhachHangRepose> listKH = new ArrayList<>();
    private ChiTietSanPhamRepository ctspR = new ChiTietSanPhamRepository();
    private ViewSanPhamServiceImpl vspService = new ViewSanPhamServiceImpl();
    private DefaultComboBoxModel cbbModelKhachHang = new DefaultComboBoxModel();

    private String regexInt = "[0-9]+";
    private Account a = new Account();

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private String resultMa;

    public ViewHoaDon(Account account) {
        initComponents();
        this.a = account;
        tbHoaDon.setModel(dtm);
        tbHDCT.setModel(dtm1);
        tbSanPham.setModel(dtm2);
        cbbKhachHang.setModel(cbbModelKhachHang);
        String[] headerSanPham = {"Tên", "Màu sắc", "Hãng", "Kích cỡ", "Chất liệu", "Loại", "Số Lượng tồn", "Đơn Giá"};
        String[] headerGioHang = {"Tên", "Màu sắc", "Hãng", "Kích cỡ", "Chất liệu", "Loại", "Số Lượng", "Đơn Giá", "Tổng tiền"};
        String[] headerHoaDonTH = {"Nhân viên", "Khách Hàng", "Mã hóa đơn", "Trạng thái", "Ngày tạo", "Ngày thanh toán"};
        String[] headerHoaDon = {"Nhân viên", "Khách Hàng", "Mã hóa đơn", "Trạng thái", "Ngày tạo"};
        dtmHD.setColumnIdentifiers(headerHoaDonTH);
        dtmHDCTTH.setColumnIdentifiers(headerGioHang);
        dtmCTTH.setColumnIdentifiers(headerSanPham);
        cbbKhachHang.setModel(cbbModelKhachHang);
        cbbKHShip.setModel(cbbModelKhachHang);
        dtm.setColumnIdentifiers(headerHoaDon);
        dtm1.setColumnIdentifiers(headerGioHang);
        dtm2.setColumnIdentifiers(headerSanPham);

        listKH = vhds.getAllKH();
        listTTO = vhds.getAllTTO();
        listVCTSP = vhds.getAllSP();
        listHD = vhds.getAllHD();
        listHDTH = vhds.getAllHD();
        listHDCTTraHang = vhds.getAllHDCT();
        for (ViewKhachHangRepose vkhr : listKH) {
            cbbModelKhachHang.addElement(vkhr.getHoTen() + " " + "KH" + vkhr.getMa());
        }
        cbbModelKhachHang.addElement(" ");
        cbbModelKhachHang.setSelectedItem(" ");
        txtTienKhachDua.setText("0");
        txtTienTaiKhoan.setText("0");
        txtTienKhachDuaShip.setText("0");
        txtTienTaiKhoanShip.setText("0");
        showDataTableHoaDon(listHD);
        showDataTableSanPham(listVCTSP);
        txtTienTaiKhoan.setEnabled(false);
        txtTienTaiKhoanShip.setEnabled(false);
        radioAll.setSelected(true);
        initWebcam();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        if (webcam.close()) {
            webcam.setViewSize(size);
        }

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        panelWebCam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 159));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            try {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }

            if (result != null) {
                resultMa = result.getText();
                ViewCTSPResponse vctspr = vhds.getOneSP(resultMa);
                themHDCT(vctspr);
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    private void showDataTableSanPham(List<ViewCTSPResponse> list) {
        dtm2.setRowCount(0);
        for (ViewCTSPResponse vctspr : list) {
            dtm2.addRow(vctspr.toDataRow());
        }
    }

    private void showDataTableHoaDon(List<ViewHoaDonResponse> list) {
        dtm.setRowCount(0);
        for (ViewHoaDonResponse vhdr : list) {
            dtm.addRow(vhdr.toDataRow());
        }
    }

    private void showDataTableHDCT(List<ViewHDCTResponse> list) {
        dtm1.setRowCount(0);
        for (ViewHDCTResponse vhdctr : list) {
            dtm1.addRow(vhdctr.toDataRow());
        }
    }

    private void showDataTableKH(List<ViewKhachHangRepose> list) {
        dtmKhachHang.setRowCount(0);
        for (ViewKhachHangRepose vkhr : list) {
            dtmKhachHang.addRow(vkhr.toDataRowHoaDon());
        }
    }

    private void fillData(ViewHoaDonResponse vhdr) {
        btnGiaoHang.setEnabled(true);
        btnPDF.setEnabled(true);
        btnThanhToan.setEnabled(true);
        btnPDF.setEnabled(true);
        txtTienKhachDua.setEnabled(true);
        btnRemoveAll.setEnabled(true);
        btnGiaoHang.setEnabled(true);
        btnDaGiao.setEnabled(true);
        btnHuy.setEnabled(true);
        btnHuyDonShip.setEnabled(true);
        tbHDCT.setEnabled(true);
        tbSanPham.setEnabled(true);

        lbTongTien.setText(tongTienHoaDon());
        if (vhdr.getTto().getId() == 2) {
            lbTongTienDonShip.setText(tongTienHoaDon());
        } else {
            lbTongTienDonShip.setText(String.valueOf(vhdr.getTongTien()));
            txtTienShip.setText(String.valueOf(Double.valueOf(String.valueOf(vhdr.getTongTien())) - Double.valueOf(tongTienHoaDon())));
        }

        if (vhdr.getKhachHang() != null) {
            cbbModelKhachHang.setSelectedItem(vhdr.getKhachHang().getHoTen() + " KH" + vhdr.getKhachHang().getMaKH());
            txtSDT.setText(vhdr.getKhachHang().getSdt());
            txtTenKhachHang.setText(vhdr.getKhachHang().getHoTen());
        } else {
            cbbModelKhachHang.setSelectedItem(" ");
        }
        if (vhdr.getTto().getId() == 1 || vhdr.getTto().getId() == 3 || vhdr.getTto().getId() == 5) {
            btnThanhToan.setEnabled(false);
            txtTienKhachDua.setEnabled(false);
            btnHuy.setEnabled(false);
            btnRemoveAll.setEnabled(false);
            btnGiaoHang.setEnabled(false);
            txtTienKhachDuaShip.setEnabled(false);
            btnHuy.setEnabled(false);
            btnRemoveAll.setEnabled(false);
            btnDaGiao.setEnabled(false);
            tbHDCT.setEnabled(false);
            tbSanPham.setEnabled(false);
            btnGiaoHang.setEnabled(false);
        }
        if (vhdr.getTto().getId() == 3) {
            btnHuy.setEnabled(false);
            btnHuyDonShip.setEnabled(false);
        }
    }

    private Date getDate() {
        return new Date();
    }

    private String tongTienHoaDon() {
        Double tongTien = 0.00;
        for (ViewHDCTResponse vhdctr : listHDCT) {
            tongTien += Double.valueOf(String.valueOf(vhdctr.getGia())) * vhdctr.getSoLuong();
        }
        return String.valueOf(tongTien);
    }

    private void themHDCT(ViewCTSPResponse vctspr) {
        if (tbHoaDon.getSelectedRow() > -1) {
            String soLuong = JOptionPane.showInputDialog(TBPaneHD, "Nhập số lượng");
            if (soLuong != null && !soLuong.equals("")) {
                if (soLuong.matches(regexInt) || Integer.valueOf(soLuong) > 0) {
                    ChiTietSanPham ctsp = new ChiTietSanPham();
                    ctsp.setId(vctspr.getId());
                    ViewHDCTResponse vhdctr = new ViewHDCTResponse(ctsp, vctspr.getHang().getTen(), vctspr.getTen(), vctspr.getMauSac().getTen(), vctspr.getLoai().getTen(), vctspr.getKichCo().getTen(), vctspr.getChatLieu().getTen(), Integer.valueOf(soLuong), vctspr.getGia());
                    if (vhds.checkSoLuongTonVoiSoLuong(vhdctr)) {
                        UUID id = listHD.get(tbHoaDon.getSelectedRow()).getId();
                        HoaDon hd = new HoaDon();
                        hd.setId(id);
                        vhdctr.setHd(hd);
                        ViewHDCTResponse vhdctr1 = new ViewHDCTResponse();
                        vhds.updateSoLuongTonKhiThem(vhdctr);
                        try {
                            vhdctr1 = vhds.getOneHDCT(id, vctspr.getId());
                        } catch (Exception e) {
                        }
                        if (vhdctr1 == null) {
                            vhds.addHDCT(vhdctr, id);
                        } else {
                            vhdctr.setSoLuong(vhdctr.getSoLuong() + vhdctr1.getSoLuong());
                            vhds.updateHDCT(vhdctr);
                        }
                        listVCTSP = vhds.getAllSP();
                        listHDCT = vhds.getOneHD(id);
                        lbTongTienDonShip.setText(tongTienHoaDon());
                        lbTongTien.setText(tongTienHoaDon());
                        listHD = vhds.getAllHD();
                        showDataTableSanPham(listVCTSP);
                        showDataTableHDCT(listHDCT);
                    } else {
                        JOptionPane.showMessageDialog(TBPaneHD, "Số lượng không đủ");
                    }

                } else {
                    JOptionPane.showMessageDialog(TBPaneHD, "Số lượng là số nguyên lớn hơn 0");
                }
            }
        } else {
            JOptionPane.showMessageDialog(TBPaneHD, "Hãy chọn hóa đơn bạn cần thêm sản phẩm");
        }
    }

    private int updateRank(Double tongTien) {
        if (tongTien > 20000000.0) {
            return 5;
        }
        if (tongTien > 10000000.0) {
            return 4;
        }
        if (tongTien > 7000000.0) {
            return 3;
        }
        if (tongTien > 5000000.0) {
            return 2;
        } else {
            return 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ViewKhachHang = new javax.swing.JFrame();
        jPanel13 = new javax.swing.JPanel();
        btnChon = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtTenKhachHangThem = new javax.swing.JTextField();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        txtSDTThem = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        labelDiem = new javax.swing.JLabel();
        labelNguoiTao = new javax.swing.JLabel();
        labelNgayTao = new javax.swing.JLabel();
        labelNgayChinhSua = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtSDTKHSearch = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        TBPaneHD = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();
        btnRemoveAll = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbbKhachHang = new javax.swing.JComboBox<>();
        btnChonKH = new javax.swing.JButton();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnTaoHoaDon = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbbHinhThuc = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        lbTienThua = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienTaiKhoan = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbbKHShip = new javax.swing.JComboBox<>();
        btnChonKHShip = new javax.swing.JButton();
        txtTenKHShip = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSDTKHShip = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lbTongTienDonShip = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnDaGiao = new javax.swing.JButton();
        btnTaoDonShip = new javax.swing.JButton();
        btnGiaoHang = new javax.swing.JButton();
        btnHuyDonShip = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cbbHinhThucThanhToanShip = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTienTaiKhoanShip = new javax.swing.JTextField();
        txtTienShip = new javax.swing.JTextField();
        txtTienKhachDuaShip = new javax.swing.JTextField();
        lbTienThuaShip = new javax.swing.JLabel();
        radioChuaTT = new javax.swing.JRadioButton();
        radioDaTT = new javax.swing.JRadioButton();
        radioDangGiao = new javax.swing.JRadioButton();
        radioDaGiao = new javax.swing.JRadioButton();
        radioAll = new javax.swing.JRadioButton();
        jPanel15 = new javax.swing.JPanel();
        panelWebCam = new javax.swing.JPanel();
        btnCloseWebcam = new javax.swing.JButton();

        ViewKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        ViewKhachHang.setMinimumSize(new java.awt.Dimension(536, 538));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setMaximumSize(new java.awt.Dimension(536, 538));
        jPanel13.setMinimumSize(new java.awt.Dimension(536, 538));

        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        jLabel22.setText("Khách hàng");
        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTabbedPane2.setMaximumSize(new java.awt.Dimension(468, 413));
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(468, 413));

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(tbKhachHang);

        jTabbedPane2.addTab("Danh sách", jScrollPane6);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(463, 383));
        jPanel2.setMinimumSize(new java.awt.Dimension(463, 383));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(1062, 300));
        jPanel3.setMinimumSize(new java.awt.Dimension(1062, 300));
        jPanel3.setToolTipText("Quản lý khách hàng");

        jLabel13.setText("Tên khách hàng");

        jLabel18.setText("Ngày sinh");

        jLabel21.setText("Số điện thoại");

        jLabel23.setText("Email");

        jLabel26.setText("Địa chỉ");

        jLabel29.setText("Giới tính");

        txtTenKhachHangThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        radioNam.setSelected(true);
        radioNam.setText("Nam");

        radioNu.setText("Nữ");

        txtSDTThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jPanel14.setAlignmentY(10.0F);
        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel14.add(btnLamMoi);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel14.add(btnThem);

        txtNgaySinh.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel18)
                    .addComponent(jLabel29)
                    .addComponent(jLabel21)
                    .addComponent(jLabel26)
                    .addComponent(jLabel23))
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(radioNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioNu))
                            .addComponent(txtSDTThem)
                            .addComponent(txtTenKhachHangThem)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(128, 128, 128)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(labelNguoiTao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(labelDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(labelNgayChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(labelNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelNgayChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(labelNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(labelDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtTenKhachHangThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(radioNam)
                                    .addComponent(radioNu))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSDTThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Thêm mới", jPanel2);

        jLabel3.setText("SDT");

        txtSDTKHSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKHSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDTKHSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(btnChon)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSDTKHSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChon)
                .addContainerGap())
        );

        javax.swing.GroupLayout ViewKhachHangLayout = new javax.swing.GroupLayout(ViewKhachHang.getContentPane());
        ViewKhachHang.getContentPane().setLayout(ViewKhachHangLayout);
        ViewKhachHangLayout.setHorizontalGroup(
            ViewKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ViewKhachHangLayout.setVerticalGroup(
            ViewKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1086, 642));
        setMinimumSize(new java.awt.Dimension(1086, 642));
        setPreferredSize(new java.awt.Dimension(1086, 642));

        TBPaneHD.setBackground(new java.awt.Color(255, 255, 255));
        TBPaneHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        TBPaneHD.setPreferredSize(new java.awt.Dimension(1070, 633));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1382, 671));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(759, 158));

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbHoaDon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(759, 158));

        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHDCT.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHDCT);

        btnRemoveAll.setText("Xóa tất cả");
        btnRemoveAll.setBackground(new java.awt.Color(255, 255, 255));
        btnRemoveAll.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRemoveAll)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(759, 227));

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
        tbSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSanPham);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(333, 596));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(333, 596));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Khách hàng");

        cbbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbKhachHangItemStateChanged(evt);
            }
        });

        btnChonKH.setText("Chọn");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên khách hàng :");

        jLabel9.setText("SDT :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(cbbKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChonKH))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT)
                            .addComponent(txtTenKhachHang))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonKH)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jLabel4.setText("Tổng tiền :");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel8.setMaximumSize(new java.awt.Dimension(324, 128));
        jPanel8.setMinimumSize(new java.awt.Dimension(324, 128));

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnPDF.setText("In PDF");
        btnPDF.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jLabel5.setText("Hình thức :");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbHinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Cả hai" }));
        cbbHinhThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbHinhThuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHinhThucItemStateChanged(evt);
            }
        });

        jLabel6.setText("Tiền khách đưa :");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setText("Chuyển khoản :");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setText("Tiền thừa :");
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbTienThua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        txtTienTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienTaiKhoanKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(cbbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTienTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTienTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đơn hàng", jPanel1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setMaximumSize(new java.awt.Dimension(324, 142));
        jPanel9.setMinimumSize(new java.awt.Dimension(324, 142));

        jLabel10.setText("Khách hàng");

        cbbKHShip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbKHShip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbKHShipItemStateChanged(evt);
            }
        });

        btnChonKHShip.setText("Chọn");
        btnChonKHShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHShipActionPerformed(evt);
            }
        });

        jLabel11.setText("Tên khách hàng :");

        jLabel12.setText("SDT :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(0, 92, Short.MAX_VALUE)
                                .addComponent(cbbKHShip, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnChonKHShip))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDTKHShip)
                            .addComponent(txtTenKHShip))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbKHShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonKHShip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenKHShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSDTKHShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jLabel14.setText("Tổng tiền :");
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbTongTienDonShip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnDaGiao.setText("Đã giao");
        btnDaGiao.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnDaGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaGiaoActionPerformed(evt);
            }
        });

        btnTaoDonShip.setText("Tạo hóa đơn");
        btnTaoDonShip.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnTaoDonShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonShipActionPerformed(evt);
            }
        });

        btnGiaoHang.setText("Giao hàng");
        btnGiaoHang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoHangActionPerformed(evt);
            }
        });

        btnHuyDonShip.setText("Hủy");
        btnHuyDonShip.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnHuyDonShip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDonShipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDaGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnTaoDonShip, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHuyDonShip, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDaGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoDonShip, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyDonShip, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jLabel15.setText("Hình thức :");
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbbHinhThucThanhToanShip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Cả hai" }));
        cbbHinhThucThanhToanShip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbHinhThucThanhToanShip.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHinhThucThanhToanShipItemStateChanged(evt);
            }
        });

        jLabel16.setText("Tiền ship :");
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel17.setText("Tiền khách đưa :");
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel19.setText("Chuyển khoản :");
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel20.setText("Tiền thừa :");
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTienTaiKhoanShip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienTaiKhoanShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienTaiKhoanShipKeyReleased(evt);
            }
        });

        txtTienShip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienShipKeyReleased(evt);
            }
        });

        txtTienKhachDuaShip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienKhachDuaShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaShipKeyReleased(evt);
            }
        });

        lbTienThuaShip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTienKhachDuaShip, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienTaiKhoanShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTienThuaShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lbTongTienDonShip, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbHinhThucThanhToanShip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(lbTongTienDonShip, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTienShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbbHinhThucThanhToanShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTienKhachDuaShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtTienTaiKhoanShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTienThuaShip, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(50, 50, 50)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ship hàng", jPanel6);

        buttonGroup1.add(radioChuaTT);
        radioChuaTT.setText("Chưa thanh toán");
        radioChuaTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioChuaTTItemStateChanged(evt);
            }
        });

        buttonGroup1.add(radioDaTT);
        radioDaTT.setText("Đã thanh toán");
        radioDaTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioDaTTItemStateChanged(evt);
            }
        });

        buttonGroup1.add(radioDangGiao);
        radioDangGiao.setText("Đang giao");
        radioDangGiao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioDangGiaoItemStateChanged(evt);
            }
        });

        buttonGroup1.add(radioDaGiao);
        radioDaGiao.setText("Đã giao");
        radioDaGiao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioDaGiaoItemStateChanged(evt);
            }
        });

        buttonGroup1.add(radioAll);
        radioAll.setText("Tất cả");
        radioAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioAllItemStateChanged(evt);
            }
        });

        jPanel15.setMaximumSize(new java.awt.Dimension(180, 160));
        jPanel15.setMinimumSize(new java.awt.Dimension(180, 160));
        jPanel15.setPreferredSize(new java.awt.Dimension(180, 160));

        panelWebCam.setBackground(new java.awt.Color(250, 250, 250));
        panelWebCam.setBorder(new javax.swing.border.MatteBorder(null));
        panelWebCam.setMaximumSize(new java.awt.Dimension(180, 159));
        panelWebCam.setMinimumSize(new java.awt.Dimension(180, 159));
        panelWebCam.setPreferredSize(new java.awt.Dimension(180, 159));
        panelWebCam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(panelWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnCloseWebcam.setText("Close");
        btnCloseWebcam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseWebcamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(radioAll)
                                .addGap(18, 18, 18)
                                .addComponent(radioChuaTT)
                                .addGap(18, 18, 18)
                                .addComponent(radioDaTT)
                                .addGap(18, 18, 18)
                                .addComponent(radioDangGiao)
                                .addGap(18, 18, 18)
                                .addComponent(radioDaGiao)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(btnCloseWebcam)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioChuaTT)
                                    .addComponent(radioAll)
                                    .addComponent(radioDaTT)
                                    .addComponent(radioDangGiao)
                                    .addComponent(radioDaGiao))
                                .addGap(13, 13, 13)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCloseWebcam)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(558, 558, 558))
        );

        TBPaneHD.addTab("Mua hàng", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TBPaneHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(4, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TBPaneHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        if (tbHoaDon.getSelectedRow() > -1) {
            int row = tbHoaDon.getSelectedRow();
            ViewHoaDonResponse vhdr = listHD.get(row);
            listHDCT = vhds.getOneHD(vhdr.getId());
            showDataTableHDCT(listHDCT);
            fillData(vhdr);
            lbTongTien.setText(tongTienHoaDon());
        }
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        if (tbSanPham.getSelectedRow() > -1) {
            ViewCTSPResponse vctspr = listVCTSP.get(tbSanPham.getSelectedRow());
            themHDCT(vctspr);
        }
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void tbHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDCTMouseClicked
        // TODO add your handling code here:
        if (tbHDCT.getSelectedRow() > -1) {
            int row = tbHDCT.getSelectedRow();
            ViewHDCTResponse vhdctr = listHDCT.get(row);
            ViewHoaDonResponse vhdr = listHD.get(tbHoaDon.getSelectedRow());
            String soLuong = JOptionPane.showInputDialog(TBPaneHD, "Nhập số lượng bạn muốn bớt");
            if (soLuong != null && soLuong != "") {
                if (soLuong.matches(regexInt) || Integer.valueOf(soLuong) > 0) {
                    if (Integer.valueOf(soLuong) >= vhdctr.getSoLuong()) {
                        vhds.updateSoLuongTonKhiXoa(vhdctr);
                        vhds.deleteHDCT(vhdctr);
                    } else {
                        int soLuongCu = vhdctr.getSoLuong();
                        vhdctr.setSoLuong(Integer.valueOf(soLuong));
                        vhds.updateSoLuongTonKhiXoa(vhdctr);
                        vhdctr.setSoLuong(soLuongCu - Integer.valueOf(soLuong));
                        vhds.updateHDCT(vhdctr);

                    }

                    listHD = vhds.getAllHD();
                    listHDCT = vhds.getOneHD(vhdr.getId());
                    showDataTableHDCT(vhds.getOneHD(vhdr.getId()));
                    lbTongTienDonShip.setText(tongTienHoaDon());
                    lbTongTien.setText(tongTienHoaDon());
                    listVCTSP = vhds.getAllSP();
                    showDataTableSanPham(listVCTSP);
                }
            }
        }
    }//GEN-LAST:event_tbHDCTMouseClicked

    private void btnRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllActionPerformed
        // TODO add your handling code here:
        if (tbHoaDon.getSelectedRow() > -1) {
            boolean check = true;
            int row = tbHoaDon.getSelectedRow();
            ViewHoaDonResponse vhdr = listHD.get(row);
            for (ViewHDCTResponse vhdctr : vhds.getOneHD(vhdr.getId())) {
                check = vhds.deleteHDCT(vhdctr);
                vhds.updateSoLuongTonKhiXoa(vhdctr);
                if (check == false) {
                    JOptionPane.showMessageDialog(TBPaneHD, "Thất bại");
                    break;
                }
            }
            if (check == true) {
                JOptionPane.showMessageDialog(TBPaneHD, "Thành công");
                showDataTableHDCT(vhds.getOneHD(vhdr.getId()));
                listVCTSP = vhds.getAllSP();
                showDataTableSanPham(listVCTSP);
            }
        }
    }//GEN-LAST:event_btnRemoveAllActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        // TODO add your handling code here:
        int i = JOptionPane.showConfirmDialog(TBPaneHD, "Bạn có muốn xuất hóa đơn không ?");
        if (i == JOptionPane.YES_OPTION) {
            if (tbHoaDon.getSelectedRow() > -1) {
                ViewHoaDonResponse vhdr = listHD.get(tbHoaDon.getSelectedRow());
                vhdr.setTongTien(BigDecimal.valueOf(Double.valueOf(tongTienHoaDon())));
                try {
                    vhds.taoFilePDF(vhdr, vhds.getOneHD(vhdr.getId()), a);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ViewHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(TBPaneHD, "Hãy chọn hóa đơn bạn cần in");
            }
        }

    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10000000 + 1);
        Date d = new Date();
        ViewHoaDonResponse vhdr = new ViewHoaDonResponse(null, a, null, new TrangThaiOrder(2, null, null), String.valueOf(randomNum), d, null, BigDecimal.ZERO);
        if (cbbModelKhachHang.getSelectedItem() != " ") {
            UUID idKH = listKH.get(cbbKhachHang.getSelectedIndex()).getId();
            KhachHang kh = new KhachHang();
            kh.setId(idKH);
            vhdr.setKhachHang(kh);
        }
        cbbModelKhachHang.setSelectedItem(" ");
        JOptionPane.showMessageDialog(TBPaneHD, vhds.addHoaDon(vhdr));
        listHD = vhds.getAllHDByChuaTT();
        showDataTableHoaDon(listHD);
        radioChuaTT.setSelected(true);
        tbHoaDon.setRowSelectionInterval(0, 0);
        fillData(listHD.get(0));
        tbHoaDon.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGiaoActionPerformed
        // TODO add your handling code here:
        if (tbHoaDon.getSelectedRow() > -1) {
            TrangThaiOrder tto = new TrangThaiOrder(5, "", "");
            ViewHoaDonResponse vhdr = listHD.get(tbHoaDon.getSelectedRow());
            vhdr.setNgayNhan(getDate());
            vhdr.setTto(tto);
            if (vhds.updateHD(vhdr)) {
                JOptionPane.showMessageDialog(TBPaneHD, "Thành công");
                cbbModelKhachHang.setSelectedItem(" ");
                listHD = vhds.getAllHDByDaGiao();
                showDataTableHoaDon(listHD);
                txtTienTaiKhoanShip.setText("0.0");
                txtTienKhachDuaShip.setText("0.0");
                lbTienThuaShip.setText("0.0");
                txtTienShip.setText(" ");
                radioDaGiao.setSelected(true);
                tbHoaDon.setRowSelectionInterval(0, 0);
                fillData(listHD.get(0));
            } else {
                JOptionPane.showMessageDialog(TBPaneHD, "Thất bại");
            }
        }
    }//GEN-LAST:event_btnDaGiaoActionPerformed

    private void btnTaoDonShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonShipActionPerformed
        // TODO add your handling code here:
        Date d = new Date();
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10000000 + 1);
        ViewHoaDonResponse vhdr = new ViewHoaDonResponse(null, a, null, new TrangThaiOrder(2, null, null), String.valueOf(randomNum), d, null, BigDecimal.ZERO);
        if (cbbModelKhachHang.getSelectedItem() != " ") {
            UUID idKH = listKH.get(cbbKhachHang.getSelectedIndex()).getId();
            KhachHang kh = new KhachHang();
            kh.setId(idKH);
            vhdr.setKhachHang(kh);
        }
        cbbModelKhachHang.setSelectedItem(" ");
        JOptionPane.showMessageDialog(TBPaneHD, vhds.addHoaDon(vhdr));
        listHD = vhds.getAllHD();
        showDataTableHoaDon(listHD);
        radioChuaTT.setSelected(true);
        listHDCT.clear();
        showDataTableHDCT(listHDCT);
        tbHoaDon.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnTaoDonShipActionPerformed

    private void btnGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoHangActionPerformed
        // TODO add your handling code here:
        if (cbbKHShip.getSelectedItem() != " ") {
            if (tbHoaDon.getSelectedRow() > -1) {
                ViewHoaDonResponse vhdr = listHD.get(tbHoaDon.getSelectedRow());
                vhdr.setNgayThanhToan(getDate());
                vhdr.setTongTien(BigDecimal.valueOf(Double.valueOf(tongTienHoaDon())));
                if (cbbKHShip.getSelectedItem() != " ") {
                    UUID idKH = listKH.get(cbbKhachHang.getSelectedIndex()).getId();
                    KhachHang kh = new KhachHang();
                    kh.setId(idKH);
                    vhdr.setKhachHang(kh);
                }
                if (Double.valueOf(lbTongTienDonShip.getText()) <= Double.valueOf(txtTienKhachDuaShip.getText()) + Double.valueOf(txtTienTaiKhoanShip.getText())) {
                    vhdr.setTongTien(BigDecimal.valueOf(Double.valueOf(lbTongTienDonShip.getText())));
                    TrangThaiOrder tto = new TrangThaiOrder(4, null, null);
                    vhdr.setTto(tto);
                    vhdr.setNgayShip(getDate());
                    if (vhds.updateHD(vhdr)) {
                        JOptionPane.showMessageDialog(TBPaneHD, "Thành công");
                    } else {
                        JOptionPane.showMessageDialog(TBPaneHD, "Thât bại");
                    }
                    cbbModelKhachHang.setSelectedItem(" ");
                    listHD = vhds.getAllHDByDangGiao();
                    showDataTableHoaDon(listHD);
                    txtTienTaiKhoanShip.setText("0.0");
                    txtTienKhachDuaShip.setText("0.0");
                    lbTienThuaShip.setText("0.0");
                    txtTienShip.setText(" ");
                    radioDangGiao.setSelected(true);
                    tbHoaDon.setRowSelectionInterval(0, 0);
                    fillData(listHD.get(0));
                } else {
                    JOptionPane.showMessageDialog(TBPaneHD, "Tiền khách đưa không đủ");
                }
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Hãy chọn khách hàng cần giao");
        }
    }//GEN-LAST:event_btnGiaoHangActionPerformed

    private void btnHuyDonShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDonShipActionPerformed
        // TODO add your handling code here:
        if (tbHoaDon.getSelectedRow() > -1) {
            ViewHoaDonResponse vhdr = listHD.get(tbHoaDon.getSelectedRow());
            vhdr.setTto(new TrangThaiOrder(3, null, null));
            if (vhds.updateHD(vhdr)) {
                listHD = vhds.getAllHD();
                showDataTableHoaDon(listHD);
                JOptionPane.showMessageDialog(TBPaneHD, "Thành công");
            } else {
                JOptionPane.showMessageDialog(TBPaneHD, "Thất bại");
            }
        }
    }//GEN-LAST:event_btnHuyDonShipActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        // TODO add your handling code here:
        ViewKhachHang.setVisible(true);
        ViewKhachHang.setLocationRelativeTo(null);
        String[] khachHangHeader = {"Mã", "Họ tên", "Giới tính", "SDT"};
        tbKhachHang.setModel(dtmKhachHang);
        dtmKhachHang.setColumnIdentifiers(khachHangHeader);
        listKH = vhds.getAllKH();
        showDataTableKH(listKH);
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        // TODO add your handling code here:
        if (tbKhachHang.getSelectedRow() > -1) {
            ViewKhachHangRepose vkhr = listKH.get(tbKhachHang.getSelectedRow());
            cbbKhachHang.setSelectedIndex(tbKhachHang.getSelectedRow());
            ViewKhachHang.setVisible(false);
            txtSDTKHSearch.setText("");
        }
    }//GEN-LAST:event_btnChonActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        if (tbHoaDon.getSelectedRow() > -1) {
            ViewHoaDonResponse vhdr = listHD.get(tbHoaDon.getSelectedRow());
            vhdr.setTto(new TrangThaiOrder(3, null, null));
            if (vhds.updateHD(vhdr)) {
                listHD = vhds.getAllHD();
                showDataTableHoaDon(listHD);
                JOptionPane.showMessageDialog(TBPaneHD, "Thành công");
            } else {
                JOptionPane.showMessageDialog(TBPaneHD, "Thất bại");
            }
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void cbbKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbKhachHangItemStateChanged
        // TODO add your handling code here:
        if (cbbKhachHang.getSelectedIndex() > -1) {
            if (cbbModelKhachHang.getSelectedItem() != " ") {
                ViewKhachHangRepose vkhr = listKH.get(cbbKhachHang.getSelectedIndex());
                txtSDT.setText(vkhr.getSdt());
                txtTenKhachHang.setText(vkhr.getHoTen());
            } else {
                txtSDT.setText("");
                txtTenKhachHang.setText("");
            }
        }
    }//GEN-LAST:event_cbbKhachHangItemStateChanged
     private void cbbHinhThucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHinhThucItemStateChanged
         // TODO add your handling code here:
         if (cbbHinhThuc.getSelectedIndex() == 0) {
             txtTienKhachDua.setEnabled(true);
             txtTienTaiKhoan.setEnabled(false);
             txtTienTaiKhoan.setText("0");
         }
         if (cbbHinhThuc.getSelectedIndex() == 1) {
             txtTienTaiKhoan.setEnabled(true);
             txtTienKhachDua.setEnabled(false);
             txtTienKhachDua.setText("0");
         }
         if (cbbHinhThuc.getSelectedIndex() == 2) {
             txtTienTaiKhoan.setEnabled(true);
             txtTienKhachDua.setEnabled(true);
         }
    }//GEN-LAST:event_cbbHinhThucItemStateChanged

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (tbHoaDon.getSelectedRow() > -1) {
            int rankKH = 0;
            ViewHoaDonResponse vhdr = listHD.get(tbHoaDon.getSelectedRow());
            vhdr.setNgayThanhToan(getDate());
            vhdr.setTongTien(BigDecimal.valueOf(Double.valueOf(tongTienHoaDon())));
            if (cbbModelKhachHang.getSelectedItem() != " ") {
                ViewKhachHangRepose vkhr = listKH.get(cbbKhachHang.getSelectedIndex());
                KhachHang kh = new KhachHang();
                kh.setId(vkhr.getId());
                vhdr.setKhachHang(kh);
//                rankKH = updateRank(Double.valueOf(tongTienHoaDon() + Double.valueOf(String.valueOf(vhds.getTongTien(vkhr.getId())))));
            }
            if (txtTienKhachDua.getText().matches(regexInt) && txtTienTaiKhoan.getText().matches(regexInt)) {
                if (Double.valueOf(tongTienHoaDon()) <= Double.valueOf(txtTienKhachDua.getText()) + Double.valueOf(txtTienTaiKhoan.getText())) {
                    JOptionPane.showMessageDialog(TBPaneHD, vhds.thanhToan(vhdr));
//                    if (cbbModelKhachHang.getSelectedItem() != " ") {
//                        ViewKhachHangRepose vkhr = listKH.get(cbbKhachHang.getSelectedIndex());
//                        vkhr.setDiem(rankKH);
//                        vhds.updateKH(vkhr);
//                    }
                    cbbModelKhachHang.setSelectedItem(" ");
                    listHD = vhds.getAllHD();
                    showDataTableHoaDon(listHD);
                    txtTienTaiKhoan.setText("0");
                    txtTienKhachDua.setText("0");
                    lbTienThua.setText("0");
                    radioDaTT.setSelected(true);
                    tbHoaDon.setRowSelectionInterval(0, 0);
                    fillData(listHD.get(0));
                } else {
                    JOptionPane.showMessageDialog(TBPaneHD, "Tiền khách đưa không đủ");
                }
            } else {
                JOptionPane.showMessageDialog(TBPaneHD, "Tiền đưa phải là số");
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        lbTienThua.setText(vhds.tienThua(BigDecimal.valueOf(Double.valueOf(tongTienHoaDon())), txtTienKhachDua, txtTienTaiKhoan));
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void txtTienTaiKhoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienTaiKhoanKeyReleased
        // TODO add your handling code here:
        lbTienThua.setText(vhds.tienThua(BigDecimal.valueOf(Double.valueOf(tongTienHoaDon())), txtTienKhachDua, txtTienTaiKhoan));
    }//GEN-LAST:event_txtTienTaiKhoanKeyReleased

    private void btnChonKHShipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHShipActionPerformed
        // TODO add your handling code here:
        ViewKhachHang.setVisible(true);
        ViewKhachHang.setLocationRelativeTo(null);
        String[] khachHangHeader = {"Mã", "Họ tên", "Giới tính", "SDT"};
        tbKhachHang.setModel(dtmKhachHang);
        dtmKhachHang.setColumnIdentifiers(khachHangHeader);
        listKH = vhds.getAllKH();
        showDataTableKH(listKH);
    }//GEN-LAST:event_btnChonKHShipActionPerformed

    private void cbbKHShipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbKHShipItemStateChanged
        // TODO add your handling code here:
        if (cbbKHShip.getSelectedIndex() > -1) {
            if (cbbKHShip.getSelectedItem() != " ") {
                ViewKhachHangRepose vkhr = listKH.get(cbbKHShip.getSelectedIndex());
                txtSDTKHShip.setText(vkhr.getSdt());
                txtTenKHShip.setText(vkhr.getHoTen());

            } else {
                txtSDTKHShip.setText("");
                txtTenKHShip.setText("");

            }
        }
    }//GEN-LAST:event_cbbKHShipItemStateChanged

    private void cbbHinhThucThanhToanShipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHinhThucThanhToanShipItemStateChanged
        // TODO add your handling code here:
        if (cbbHinhThucThanhToanShip.getSelectedIndex() == 0) {
            txtTienKhachDuaShip.setEnabled(true);
            txtTienTaiKhoanShip.setEnabled(false);
            txtTienTaiKhoanShip.setText("0");
        }
        if (cbbHinhThucThanhToanShip.getSelectedIndex() == 1) {
            txtTienTaiKhoanShip.setEnabled(true);
            txtTienKhachDuaShip.setEnabled(false);
            txtTienKhachDuaShip.setText("0");
        }
        if (cbbHinhThucThanhToanShip.getSelectedIndex() == 2) {
            txtTienTaiKhoanShip.setEnabled(true);
            txtTienKhachDuaShip.setEnabled(true);
        }
    }//GEN-LAST:event_cbbHinhThucThanhToanShipItemStateChanged

    private void txtTienKhachDuaShipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaShipKeyReleased
        // TODO add your handling code here:
        lbTienThuaShip.setText(vhds.tienThua(BigDecimal.valueOf(Double.valueOf(lbTongTienDonShip.getText())), txtTienKhachDuaShip, txtTienTaiKhoanShip));

    }//GEN-LAST:event_txtTienKhachDuaShipKeyReleased

    private void txtTienTaiKhoanShipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienTaiKhoanShipKeyReleased
        // TODO add your handling code here:
        lbTienThuaShip.setText(vhds.tienThua(BigDecimal.valueOf(Double.valueOf(lbTongTienDonShip.getText())), txtTienKhachDuaShip, txtTienTaiKhoanShip));
    }//GEN-LAST:event_txtTienTaiKhoanShipKeyReleased

    private void txtTienShipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienShipKeyReleased
        // TODO add your handling code here:
        if (!txtTienShip.getText().trim().isEmpty() && txtTienShip.getText().matches(regexInt)) {
            lbTongTienDonShip.setText(String.valueOf(Double.valueOf(tongTienHoaDon()) + Double.valueOf(txtTienShip.getText())));
        }
    }//GEN-LAST:event_txtTienShipKeyReleased

    private void radioChuaTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioChuaTTItemStateChanged
        // TODO add your handling code here:
        listHD = vhds.getAllHDByChuaTT();
        showDataTableHoaDon(listHD);
    }//GEN-LAST:event_radioChuaTTItemStateChanged

    private void radioDaTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioDaTTItemStateChanged
        // TODO add your handling code here:
        listHD = vhds.getAllHDByDaTT();
        showDataTableHoaDon(listHD);
    }//GEN-LAST:event_radioDaTTItemStateChanged

    private void radioAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioAllItemStateChanged
        // TODO add your handling code here:
        listHD = vhds.getAllHD();
        showDataTableHoaDon(listHD);
    }//GEN-LAST:event_radioAllItemStateChanged

    private void radioDangGiaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioDangGiaoItemStateChanged
        // TODO add your handling code here:
        listHD = vhds.getAllHDByDangGiao();
        showDataTableHoaDon(listHD);
    }//GEN-LAST:event_radioDangGiaoItemStateChanged

    private void radioDaGiaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioDaGiaoItemStateChanged
        // TODO add your handling code here:
        listHD = vhds.getAllHDByDaGiao();
        showDataTableHoaDon(listHD);
    }//GEN-LAST:event_radioDaGiaoItemStateChanged

    private void txtSDTKHSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKHSearchKeyReleased
        // TODO add your handling code here:
        if (txtSDTKHSearch.getText() != null) {
            if (txtSDTKHSearch.getText().trim() == "") {
                listKH = vhds.getAllKH();
                showDataTableKH(listKH);
            } else {
                listKH = vhds.searchSDT(txtSDTKHSearch.getText());
                showDataTableKH(listKH);
            }
        }
    }//GEN-LAST:event_txtSDTKHSearchKeyReleased

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (!radioNam.isSelected() && !radioNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Giới tính chưa chọn");
        } else {
            UUID id = null;
            String ma = vhds.genMaHD() + "";
            String ten = txtTenKhachHangThem.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySinh = sdf.format(txtNgaySinh.getDate());
            boolean isGioiTinh = radioNam.isSelected();
            boolean gioiTinh;
            if (isGioiTinh) {
                gioiTinh = true;
            } else {
                gioiTinh = false;
            }
            String sdt = txtSDTThem.getText();
            String email = txtEmail.getText();
            String diaChi = txtDiaChi.getText();
            Calendar c1 = Calendar.getInstance();
            Date date = new Date();
            int day = date.getDate();
            int month = date.getMonth() + 1;
            int year = date.getYear() + 1900;
            String ngayTao = year + "-" + month + "-" + day;
            String ngayChinhSua = null;
            UUID nguoiTao = a.getId();
            ViewKhachHangRepose viewkh = new ViewKhachHangRepose(ma, ten, ngaySinh, gioiTinh, sdt, email, diaChi, ngayTao, ngayChinhSua, nguoiTao, null, 0);
            String add = vhds.add(viewkh);
            JOptionPane.showMessageDialog(this, year);
            listKH = vhds.getAllKH();
            showDataTableKH(listKH);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCloseWebcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseWebcamActionPerformed
        // TODO add your handling code here:
        webcam.close();
    }//GEN-LAST:event_btnCloseWebcamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TBPaneHD;
    private javax.swing.JFrame ViewKhachHang;
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnChonKHShip;
    private javax.swing.JButton btnCloseWebcam;
    private javax.swing.JButton btnDaGiao;
    private javax.swing.JButton btnGiaoHang;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuyDonShip;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnRemoveAll;
    private javax.swing.JButton btnTaoDonShip;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbHinhThuc;
    private javax.swing.JComboBox<String> cbbHinhThucThanhToanShip;
    private javax.swing.JComboBox<String> cbbKHShip;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel labelDiem;
    private javax.swing.JLabel labelNgayChinhSua;
    private javax.swing.JLabel labelNgayTao;
    private javax.swing.JLabel labelNguoiTao;
    private javax.swing.JLabel lbTienThua;
    private javax.swing.JLabel lbTienThuaShip;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbTongTienDonShip;
    private javax.swing.JPanel panelWebCam;
    private javax.swing.JRadioButton radioAll;
    private javax.swing.JRadioButton radioChuaTT;
    private javax.swing.JRadioButton radioDaGiao;
    private javax.swing.JRadioButton radioDaTT;
    private javax.swing.JRadioButton radioDangGiao;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTable tbHDCT;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTKHSearch;
    private javax.swing.JTextField txtSDTKHShip;
    private javax.swing.JTextField txtSDTThem;
    private javax.swing.JTextField txtTenKHShip;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenKhachHangThem;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienKhachDuaShip;
    private javax.swing.JTextField txtTienShip;
    private javax.swing.JTextField txtTienTaiKhoan;
    private javax.swing.JTextField txtTienTaiKhoanShip;
    // End of variables declaration//GEN-END:variables
}
