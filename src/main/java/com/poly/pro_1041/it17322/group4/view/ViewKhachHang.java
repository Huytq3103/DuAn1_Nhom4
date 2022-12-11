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
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.Common;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import com.poly.pro_1041.it17322.group4.service.ViewKhachHangService;
import com.poly.pro_1041.it17322.group4.service.impl.ViewHoaDonServiceImpl;
import com.poly.pro_1041.it17322.group4.service.impl.ViewKhachHangServiceImpl;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class ViewKhachHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    /**
     * Creates new form ViewKhachHang
     */
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultComboBoxModel dcbbRank = new DefaultComboBoxModel();
    private List<String> listCbbRank = new ArrayList<>();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private ViewKhachHangService khService = new ViewKhachHangServiceImpl();
    private DefaultComboBoxModel dcbb = new DefaultComboBoxModel<>();
    private List<ViewKhachHangRepose> listKH = new ArrayList<>();
    private int index;
    private Account account = new Account();
    private ViewHDCTResponse hdRepose = new ViewHDCTResponse();
    private HoaDon hd = new HoaDon();
    private ViewHoaDonServiceImpl hdService = new ViewHoaDonServiceImpl();
    private List<ViewHoaDonResponse> listHD = new ArrayList<>();
    private List<String> listCombobox = new ArrayList<>();
    private Webcam webcam = null;
    private Thread t1;

    public ViewKhachHang(Account a) {
        initComponents();
        this.account = a;
        tbHienThi.setModel(dtm);
        String headers[] = {"STT", "Mã", "Họ tên", "Ngày sinh", "Giới tính", "Sdt", "Email", "Địa chỉ", "Ngày tạo", "Ngày chỉnh sửa"};
        dtm.setColumnIdentifiers(headers);
        listKH = khService.getAll();
        // add dữ liệu vô cbb search
        cbbSearch.setModel(dcbb);
        listCombobox.add("Số điện thoại");
        listCombobox.add("Họ tên");
        listCombobox.add("Địa chỉ");
        listCombobox.add("Ngày tạo");
        listCombobox.add("Ngày sinh");
        listCombobox.add("Email");
        // add dữ liệu cbb rank
        listCbbRank.add("Thường");
        listCbbRank.add("Quen");
        listCbbRank.add("VIP");
        listCbbRank.add("VVIP");
        listCbbRank.add("S-VIP");
        dcbbRank.setSelectedItem("Thường");
        dcbbRank.addAll(listCbbRank);
        dcbb.addAll(listCombobox);
        dcbb.setSelectedItem("Họ tên");
        labelNgayBatDau.setVisible(false);
        labelNgayKetThuc.setVisible(false);
        txtNgayBatDau.setVisible(false);
        txtNgayKetThuc.setVisible(false);
        btnTimKiem.setVisible(false);
        initWebcam();
        FillToTable(listKH);
    }

    private void initWebcam() {
        WebcamPanel panel = null;
        Executor executor = Executors.newSingleThreadExecutor(this);
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        if (webcam.close()) {
            webcam.setViewSize(size);
        }
        Common.webcam = webcam;
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        panelWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 189, 137));
        executor.execute(this);
    }

    private void FillToTable(List<ViewKhachHangRepose> lists) {
        dtm.setRowCount(0);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        int i = 1;
        for (ViewKhachHangRepose x : lists) {
            dtm.addRow(new Object[]{i, "KH" + x.getMa(), x.getHoTen(), (x.getNgaySinh()), x.isGioiTinh() == true ? "Nam" : "Nữ", x.getSdt(), x.getEmail(), x.getDiaChi(), (x.getNgayTao()), (x.getNgayChinhSua())});
            i++;
        }
    }

    private void clearForm() {
        txtTenKhachHang.setText("");
        txtNgaySinh.setCalendar(null);
        buttonGroup1.clearSelection();
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        tbHienThi.setRowSelectionAllowed(false);
        labelNgayTao.setText("");
        labelNgayChinhSua.setText("");
        labelNguoiTao.setText("");
    }

    public Date getDate(String ngay) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        Date date = null;
        try {
            if (ngay != "") {
                date = formatter.parse(ngay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public String showDate(Date date) {
        String ngay = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM dd yyyy");
            ngay = sdf2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ngay;
    }

    private boolean checkNgay() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date ngayBatDau = java.sql.Date.valueOf(txtNgayBatDau.getDate());
        Date ngayKetThuc = java.sql.Date.valueOf(txtNgayKetThuc.getDate());
        c1.setTime(ngayBatDau);
        c2.setTime(ngayKetThuc);
        long a = (c1.getTime().getTime()) / (24 * 3600 * 1000);
        long b = (c2.getTime().getTime()) / (24 * 3600 * 1000);
        if (a <= b) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
            return false;
        }
    }

    public boolean checkNgaySinh() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date ngaySinhCheck = (txtNgaySinh.getDate());
        String ngayHienTaiCheck = LocalDate.now().toString();
        c1.setTime(ngaySinhCheck);
        c2.setTime(java.sql.Date.valueOf(ngayHienTaiCheck));
        long a = (c1.getTime().getTime()) / (24 * 3600 * 1000);
        long b = (c2.getTime().getTime()) / (24 * 3600 * 1000);
        if (a > b) {
            return true;
        } else {
//            JOptionPane.showMessageDialog(this,"Ngày sinh không được lớn hơn ngày hiện tại");
            return false;
        }
    }

    private boolean checkSdt() {
        for (ViewKhachHangRepose x : listKH) {
            if (x.getSdt().equalsIgnoreCase(txtSDT.getText())) {
                JOptionPane.showMessageDialog(this, "SDT đã có trong dữ liệu");
                return false;
            }
        }
        return true;
    }

    private boolean checkEmail() {
        for (ViewKhachHangRepose x : listKH) {
            if (x.getEmail().equalsIgnoreCase(txtEmail.getText())) {
                JOptionPane.showMessageDialog(this, "Email đã có trong dữ liệu");
                return false;
            }
        }
        return true;
    }

    private void fillIndex() {
        Calendar c = Calendar.getInstance();
        ViewKhachHangRepose kh = listKH.get(index);
        txtTenKhachHang.setText(kh.getHoTen());
        try {
            int row = tbHienThi.getSelectedRow();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(kh.getNgaySinh());
            txtNgaySinh.setDate(date);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        if (kh.isGioiTinh()) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }
        txtSDT.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
        labelNgayTao.setText(kh.getNgayTao().toString());
        labelNgayChinhSua.setText(kh.getNgayChinhSua());
        labelNguoiTao.setText(khService.getOneNguoiTao(String.valueOf(kh.getNguoiTao())).getHoTen());
        tbHienThi.setRowSelectionAllowed(true);
        tbHienThi.setRowSelectionInterval(index, index);
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
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        labelNguoiTao = new javax.swing.JLabel();
        labelNgayChinhSua = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        labelNgayTao = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        cbbSearch = new javax.swing.JComboBox<>();
        txtNgayBatDau = new com.github.lgooddatepicker.components.DatePicker();
        labelNgayBatDau = new javax.swing.JLabel();
        labelNgayKetThuc = new javax.swing.JLabel();
        txtNgayKetThuc = new com.github.lgooddatepicker.components.DatePicker();
        btnTimKiem = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        panelWebcam = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1086, 682));
        setMinimumSize(new java.awt.Dimension(1086, 682));
        setPreferredSize(new java.awt.Dimension(1086, 682));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1086, 642));
        jPanel1.setMinimumSize(new java.awt.Dimension(1086, 642));
        jPanel1.setPreferredSize(new java.awt.Dimension(1086, 642));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(1062, 300));
        jPanel2.setMinimumSize(new java.awt.Dimension(1062, 300));
        jPanel2.setPreferredSize(new java.awt.Dimension(1062, 300));
        jPanel2.setToolTipText("Quản lý khách hàng");

        jLabel2.setText("Tên khách hàng");

        jLabel3.setText("Ngày sinh");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Email");

        jLabel6.setText("Địa chỉ");

        jLabel7.setText("Ngày tạo");

        jLabel8.setText("Ngày chỉnh sửa");

        jLabel9.setText("Giới tính");

        txtTenKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(radioNam);
        radioNam.setSelected(true);
        radioNam.setText("Nam");

        buttonGroup1.add(radioNu);
        radioNu.setText("Nữ");

        txtSDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel13.setText("NguoiTao");

        jPanel5.setAlignmentY(10.0F);
        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel5.add(btnLamMoi);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem);

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel5.add(btnSua);

        jButton1.setText("Xuất Exel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        txtNgaySinh.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(radioNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioNu))
                    .addComponent(txtSDT)
                    .addComponent(txtTenKhachHang)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(labelNgayChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(449, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                            .addGap(30, 30, 30)
                            .addComponent(jLabel13)
                            .addGap(18, 18, 18)
                            .addComponent(labelNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel6)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioNam)
                                    .addComponent(jLabel9)
                                    .addComponent(radioNu)
                                    .addComponent(jLabel7))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelNgayChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

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
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHienThi);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Tìm kiếm");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        cbbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSearchItemStateChanged(evt);
            }
        });

        labelNgayBatDau.setText("Ngày bắt đầu");

        labelNgayKetThuc.setText("Ngày kết thúc");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(267, 267, 267))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelNgayBatDau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelNgayKetThuc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNgayBatDau)
                        .addComponent(labelNgayKetThuc)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKiem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jTabbedPane1.addTab("Thông tin cá nhân", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane3.setViewportView(tbHoaDon);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lịch sử hóa đơn", jPanel7);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panelWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("QR Code Scan");
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel11)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(panelWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(13, 13, 13)
                .addComponent(panelWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnClose)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
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

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (!radioNam.isSelected() && !radioNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Giới tính chưa chọn");
        } else if (checkNgaySinh()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh nhỏ hơn ngày hiện tại");
        } else {
            UUID id = listKH.get(tbHienThi.getSelectedRow()).getId();
            String ma = listKH.get(tbHienThi.getSelectedRow()).getMa();
            String ten = txtTenKhachHang.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySinh = sdf.format(txtNgaySinh.getDate());
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
            Date date = new Date();
            int day = date.getDate();
            int month = date.getMonth() + 1;
            int year = date.getYear() + 1900;
            String ngayChinhSua = year + "-" + month + "-" + day;
            String ngayTao = labelNgayTao.getText();
            UUID nguoiTao = account.getId();
            ViewKhachHangRepose viewkh = new ViewKhachHangRepose(ma, ten, ngaySinh, gioiTinh, sdt, email, diaChi, ngayTao, ngayChinhSua, nguoiTao, null, 0);
            viewkh.setId(id);
            String update = khService.update(viewkh);
            JOptionPane.showMessageDialog(this, update);
            listKH = khService.getAll();
            FillToTable(listKH);
            fillIndex();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateHT = new Date();
        if (!radioNam.isSelected() && !radioNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Giới tính chưa chọn");
        } else if (checkNgaySinh()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh nhỏ hơn ngày hiện tại");
        } else if (!checkSdt()) {
        } else if (!checkEmail()) {
        } else {
            UUID id = null;
            String ma = khService.genMaHD() + "";
            String ten = txtTenKhachHang.getText();
            String ngaySinh = sdf.format(txtNgaySinh.getDate());
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
            Calendar c1 = Calendar.getInstance();
            Date date = new Date();
            int day = date.getDate();
            int month = date.getMonth() + 1;
            int year = date.getYear() + 1900;
            String ngayTao = year + "-" + month + "-" + day;
            String ngayChinhSua = null;
            UUID nguoiTao = account.getId();
            ViewKhachHangRepose viewkh = new ViewKhachHangRepose(ma, ten, ngaySinh, gioiTinh, sdt, email, diaChi, ngayTao, ngayChinhSua, nguoiTao, null, 0);
            String add = khService.add(viewkh);
            JOptionPane.showMessageDialog(this, add);
            listKH = khService.getAll();
            FillToTable(listKH);
            fillIndex();
    }//GEN-LAST:event_btnThemActionPerformed

    }
    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        // TODO add your handling code here:
        String headerhd[] = {"STT", "Mã", "Ngày tạo", "Ngày thanh toán", "Ngày ship", "Ngày nhận", "Tổng tiền"};
        tbHoaDon.setModel(dtm2);
        dtm2.setColumnIdentifiers(headerhd);
        index = tbHienThi.getSelectedRow();
        fillIndex();
        UUID id = listKH.get(index).getId();
        listHD = hdService.getOneHDKH(id);
        dtm2 = (DefaultTableModel) tbHoaDon.getModel();
        dtm2.setRowCount(0);
        int stt = 1;
        for (ViewHoaDonResponse x : listHD) {
            dtm2.addRow(x.toDataRowHD(stt));
        }
        System.out.println(listHD);
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void cbbSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSearchItemStateChanged
        // TODO add your handling code here:
        for (ViewKhachHangRepose x : listKH) {
            if (cbbSearch.getSelectedItem().equals("Họ tên")) {
                txtTimKiem.setVisible(true);
                labelNgayBatDau.setVisible(false);
                labelNgayKetThuc.setVisible(false);
                txtNgayBatDau.setVisible(false);
                txtNgayKetThuc.setVisible(false);
                btnTimKiem.setVisible(false);
            }
            if (cbbSearch.getSelectedItem().equals("Số điện thoại")) {
                txtTimKiem.setVisible(true);
                labelNgayBatDau.setVisible(false);
                labelNgayKetThuc.setVisible(false);
                txtNgayBatDau.setVisible(false);
                txtNgayKetThuc.setVisible(false);
                btnTimKiem.setVisible(false);
            }
            if (cbbSearch.getSelectedItem().equals("Địa chỉ")) {
                txtTimKiem.setVisible(true);
                labelNgayBatDau.setVisible(false);
                labelNgayKetThuc.setVisible(false);
                txtNgayBatDau.setVisible(false);
                txtNgayKetThuc.setVisible(false);
                btnTimKiem.setVisible(false);
            }
            if (cbbSearch.getSelectedItem().equals("Ngày tạo")) {
                txtTimKiem.setVisible(false);
                labelNgayBatDau.setVisible(true);
                labelNgayKetThuc.setVisible(true);
                txtNgayBatDau.setVisible(true);
                txtNgayKetThuc.setVisible(true);
                btnTimKiem.setVisible(true);
                labelNgayBatDau.setText("Ngày bắt đầu");
            }
            if (cbbSearch.getSelectedItem().equals("Ngày sinh")) {
                txtTimKiem.setVisible(false);
                labelNgayBatDau.setVisible(true);
                labelNgayBatDau.setText("Ngày sinh");
                labelNgayKetThuc.setVisible(false);
                txtNgayBatDau.setVisible(true);
                txtNgayKetThuc.setVisible(false);
                btnTimKiem.setVisible(true);
            }
            if (cbbSearch.getSelectedItem().equals("Email")) {
                txtTimKiem.setVisible(true);
                labelNgayBatDau.setVisible(false);
                labelNgayKetThuc.setVisible(false);
                txtNgayBatDau.setVisible(false);
                txtNgayKetThuc.setVisible(false);
                btnTimKiem.setVisible(false);
            }
        }
    }//GEN-LAST:event_cbbSearchItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        listKH = khService.getAll();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách khách hàng");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Họ tên");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày sinh");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giới tính");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số điện thoại");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Email");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("địa chỉ");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Ngày tạo");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Điểm");

            DecimalFormat formatter = new DecimalFormat("###,###,###");
            for (int i = 0; i <= listKH.size() - 1; i++) {
                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(listKH.get(i).getHoTen());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(listKH.get(i).getNgaySinh());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(listKH.get(i).isGioiTinh() == true ? "Nam" : "Nữ");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(listKH.get(i).getSdt());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(listKH.get(i).getEmail());

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(listKH.get(i).getDiaChi());

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(listKH.get(i).getNgayTao());

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(listKH.get(i).getDiem());
            }

            File file = new File("C:\\Users\\Huy PC\\Desktop\\anh\\DanhsachKhachHang.xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();
                JOptionPane.showMessageDialog(this, "Xuất file thành công");
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        if (cbbSearch.getSelectedItem().equals("Họ tên")) {
            String search = txtTimKiem.getText();
            List<ViewKhachHangRepose> listSearch = khService.searchByName(listKH, search);
            FillToTable(listSearch);
        }
        if (cbbSearch.getSelectedItem().equals("Số điện thoại")) {
            String searchPhone = txtTimKiem.getText();
            List<ViewKhachHangRepose> listSearch = khService.searchByPhone(listKH, searchPhone);
            FillToTable(listSearch);
        }
        if (cbbSearch.getSelectedItem().equals("Địa chỉ")) {
            String search = txtTimKiem.getText();
            List<ViewKhachHangRepose> listSearch = khService.searchByDiaChi(listKH, search);
            FillToTable(listSearch);
        }
        if (cbbSearch.getSelectedItem().equals("Email")) {
            String search = txtTimKiem.getText();
//            List<ViewKhachHangRepose> listSearch = khService.seachByEmail(search);
//            FillToTable(listSearch);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        if (cbbSearch.getSelectedItem().equals("Ngày tạo")) {
            DecimalFormat formarter = new DecimalFormat();
            String ngayBatDau = txtNgayBatDau.getDateStringOrEmptyString();
            String ngayKetThuc = txtNgayKetThuc.getDateStringOrEmptyString();
            if (checkNgay()) {
                listKH = khService.seachKhoangNgay(ngayBatDau, ngayKetThuc);
                FillToTable(listKH);
            }
        } else if (cbbSearch.getSelectedItem().equals("Ngày sinh")) {
            DecimalFormat formarter = new DecimalFormat();
            String ngaySinh = txtNgayBatDau.getDateStringOrEmptyString();
            listKH = khService.seachKhoangNgaySinh(ngaySinh);
            FillToTable(listKH);
        } else {
            JOptionPane.showMessageDialog(this, "Tìm thất bại");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        webcam.close();
    }//GEN-LAST:event_btnCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelNgayBatDau;
    private javax.swing.JLabel labelNgayChinhSua;
    private javax.swing.JLabel labelNgayKetThuc;
    private javax.swing.JLabel labelNgayTao;
    private javax.swing.JLabel labelNguoiTao;
    private javax.swing.JPanel panelWebcam;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private com.github.lgooddatepicker.components.DatePicker txtNgayBatDau;
    private com.github.lgooddatepicker.components.DatePicker txtNgayKetThuc;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
//                e.printStackTrace();
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
            } catch (Exception e) {
            }
            if (result != null) {
                try {
                    List<String> listt = new ArrayList<>();
                    System.out.println(result);
                    String chuoiCCCd = result.toString();
                    if (chuoiCCCd.contains("||")) {
                        chuoiCCCd = chuoiCCCd.replace("||", " ");
                        chuoiCCCd = chuoiCCCd.replace("|", " ");
                        String[] b = chuoiCCCd.split(" ");
                        String ngaySinh = b[4].substring(0, 2) + "-" + b[4].substring(2, 4) + "-" + b[4].substring(4, 8);
                        txtTenKhachHang.setText(b[1] + " " + b[2] + " " + b[3]);
                        txtDiaChi.setText(b[13] + " " + b[14]);
                        if (b[5].equalsIgnoreCase("Nam")) {
                            radioNam.setSelected(true);
                        } else {
                            radioNu.setSelected(true);
                        }
                        try {
                            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(ngaySinh);
                            txtNgaySinh.setDate(date);
                        } catch (Exception e) {
                            e.printStackTrace(System.out);
                        }
                        JOptionPane.showMessageDialog(this, "Quét qr thành công");
                    } else {
                        chuoiCCCd = chuoiCCCd.replace("|", " ");
                        String[] chuoi = chuoiCCCd.split(" ");
                        String ngaySinh = chuoi[5].substring(0, 2) + "-" + chuoi[5].substring(2, 4) + "-" + chuoi[5].substring(4, 8);
                        txtTenKhachHang.setText(chuoi[2] + " " + chuoi[3] + " " + chuoi[4]);
                        txtDiaChi.setText(chuoi[16] + " " + chuoi[17]);
                        if (chuoi[6].equalsIgnoreCase("Nam")) {
                            radioNam.setSelected(true);
                        } else {
                            radioNu.setSelected(true);
                        }
                        try {
                            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(ngaySinh);
                            txtNgaySinh.setDate(date);
                        } catch (Exception e) {
                            e.printStackTrace(System.out);
                        }
                        JOptionPane.showMessageDialog(this, "Quét qr thành công");
                    }
                } catch (Exception e) {
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
