/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.view;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewThongKeResponse;
import com.poly.pro_1041.it17322.group4.service.ViewHoaDonService;
import com.poly.pro_1041.it17322.group4.service.ViewThongKeService;
import com.poly.pro_1041.it17322.group4.service.impl.ViewHoaDonServiceImpl;
import com.poly.pro_1041.it17322.group4.service.impl.ViewThongKeServiceImpl;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Dell
 */
public class ViewThongKe extends javax.swing.JPanel {

//    private DefaultTableModel dtm;
    private DefaultTableModel dtm2;
    private DefaultComboBoxModel dcbm;
    private DefaultComboBoxModel dcbm2;
    private List<String> listCbb;
    private List<Integer> listNam;
    private List<ViewThongKeResponse> listCTSP;
    private ViewThongKeService viewThongKeService;
    private ViewThongKeResponse viewThongKeResponse;
    private List<BigDecimal> listGiaBan;
    private List<Integer> listThang;
    private Account account = new Account();
    private ViewHDCTResponse viewHDCTResponse;
    private List<Integer> listNam2;
    private List<BigDecimal> listGiaBan_Nam;
    private ViewHoaDonService viewHoaDonService;

    /**
     * Creates new form ViewThongKe
     */
    public ViewThongKe(Account account) {
        initComponents();
//        dtm = new DefaultTableModel();
        dtm2 = new DefaultTableModel();
        dcbm = new DefaultComboBoxModel();
        dcbm2 = new DefaultComboBoxModel<>();
        listCbb = new ArrayList<>();
        listCTSP = new ArrayList<>();
        listNam = new ArrayList<>();
        listGiaBan = new ArrayList<>();
        listThang = new ArrayList<>();
        listNam2 = new ArrayList<>();
        listGiaBan_Nam = new ArrayList<>();
        this.account = account;

        viewHDCTResponse = new ViewHDCTResponse();
        viewThongKeResponse = new ViewThongKeResponse();
        viewThongKeService = new ViewThongKeServiceImpl();
        viewHoaDonService = new ViewHoaDonServiceImpl();

//        dtm = (DefaultTableModel) tbChiTietDoanhThu.getModel();
        dtm2 = (DefaultTableModel) tbChiTietSanPham.getModel();
        cbbLoaiThoiGian.setModel(dcbm);
        cbbNam.setModel(dcbm2);

        listCbb.add("Hôm nay");
        listCbb.add("Theo ngày");
        for (String i : listCbb) {
            dcbm.addElement(i);
        }
        cbbLoaiThoiGian.setSelectedIndex(0);

        listCTSP = viewThongKeService.getChiTietSP();

//        Calendar c1 = Calendar.getInstance();
//        int nam = c1.get(Calendar.YEAR);
//        listThang = viewThongKeService.getThang(nam);
//        listGiaBan = viewThongKeService.getGiaBan(nam);
        listNam = viewThongKeService.getNam();
        for (Integer i : listNam) {
            dcbm2.addElement(i);
        }
        radioNam.setSelected(true);
        showDataTableCTSP();
        bieuDo_Nam();
        phanQuyen();
    }

    private void showThongKe() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        try {
            lblThanhCong.setText(viewThongKeService.getHoaDonThanhCong().toString());
            lblBiHuy.setText(viewThongKeService.getHoaDonBiHuy().toString());

            if (viewThongKeService.getTongDonHang() == 0) {
                lblTongDonHang.setText("0" + " Đơn hàng");
            } else {
                lblTongDonHang.setText(viewThongKeService.getTongDonHang().toString() + " Đơn hàng");
            }

            if (viewThongKeService.getTongDoanhThuThang() == null) {
                lblTongDoanhThuThang.setText("0" + " VNĐ");
            } else {
                lblTongDoanhThuThang.setText((formatter.format(viewThongKeService.getTongDoanhThuThang()) + " VNĐ"));
            }

            if (viewThongKeService.getTongDoanhThuNam() == null) {
                lblTongDoanhThuNam.setText("0" + " VNĐ");
            } else {
                lblTongDoanhThuNam.setText((formatter.format(viewThongKeService.getTongDoanhThuNam()) + " VNĐ"));
            }

            if (cbbLoaiThoiGian.getSelectedItem() != null) {
                if (cbbLoaiThoiGian.getSelectedItem().equals("Hôm nay")) {
                    hide_();

                    if (viewThongKeService.getTongDoanhThuNgay(LocalDate.now().toString()) == null) {
                        lblTongDoanhThuNgay.setText("0" + " VNĐ");

                    } else {
                        lblTongDoanhThuNgay.setText((formatter.format(viewThongKeService.getTongDoanhThuNgay(LocalDate.now().toString())) + " VNĐ"));
                    }

                } else {
                    show_();
                    lblTongDoanhThuNgay.setText("0" + " VNĐ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void showDataTableCTSP() {
        dtm2.setRowCount(0);
        int stt = 1;
        for (ViewThongKeResponse x : thongKeDTSP()) {
            dtm2.addRow(x.toDataRow(stt));
            stt++;
        }

    }

    private List<ViewThongKeResponse> thongKeDTSP() {
        List<ViewThongKeResponse> list = new ArrayList<>();
        List<ViewCTSPResponse> listSP = viewThongKeService.getAllSP();
        for (ViewThongKeResponse vtkr : viewThongKeService.getChiTietSP()) {
            for (ViewCTSPResponse vctspr : listSP) {
                if (vtkr.getCtsp().getId().compareTo(vctspr.getId()) == 0) {
                    vctspr.setSoLuongTon(vctspr.getSoLuongTon() + vtkr.getSoLuong());
                }
            }
        }
        for (ViewCTSPResponse vctspr : listSP) {
            list.add(new ViewThongKeResponse(vctspr));
        }
        Comparator<ViewThongKeResponse> com = new Comparator<ViewThongKeResponse>() {
            @Override
            public int compare(ViewThongKeResponse vtkr1, ViewThongKeResponse vtkr2) {
                return vtkr2.getSoLuong() - vtkr1.getSoLuong();
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        Collections.sort(list, com);
        return list;
    }

//    private void showDataTableDoanhThu() {
//        jpBang.removeAll();
//        jpBang.add(tbChiTietDoanhThu);
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        dtm.setRowCount(0);
//        for (int i = 0; i <= listGiaBan.size() - 1; i++) {
//            dtm.addRow(new Object[]{listThang.get(i), formatter.format(listGiaBan.get(i))});
//        }
//
//    }
    private void show_() {
        lblNgayBatDau.setVisible(true);
        lblNgayKetThuc.setVisible(true);
        dpNgayBatDau.setVisible(true);
        dpNgayKetThuc.setVisible(true);
        btTimKiem.setVisible(true);
    }

    private void hide_() {
        lblNgayBatDau.setVisible(false);
        lblNgayKetThuc.setVisible(false);
        dpNgayBatDau.setVisible(false);
        dpNgayKetThuc.setVisible(false);
        btTimKiem.setVisible(false);
    }

    private void sendEmail() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<Object> listSP_Het = new ArrayList<>();
        String nhanVien = account.getHoTen();
        int donThanhCong = viewThongKeService.getDonHangThanhCong_Ngay(LocalDate.now().toString());
        int donBiHuy = viewThongKeService.getDonHangBiHuy_Ngay(LocalDate.now().toString());
        int tongDonHang = viewThongKeService.getDonHangThanhCong_Ngay(LocalDate.now().toString()) + viewThongKeService.getDonHangBiHuy_Ngay(LocalDate.now().toString());
        String s = "";
        String tongDoanhThuNgay = "";
        if (viewThongKeService.getTongDoanhThuNgay(LocalDate.now().toString()) == null) {
            tongDoanhThuNgay = "0" + " VNĐ";

        } else {
            tongDoanhThuNgay = formatter.format(viewThongKeService.getTongDoanhThuNgay(LocalDate.now().toString())) + " VNĐ";
        }
        for (ViewCTSPResponse i : viewHoaDonService.getAllSP()) {
            if (i.getSoLuongTon() <= 5) {
                listSP_Het.add("\n" + "+ Mã sản phẩm: " + i.getId() + " - Tên sản phẩm: " + i.getTen() + " - Số lượng còn lại: " + i.getSoLuongTon());
            }
        }
        if (listSP_Het.isEmpty()) {
            s = "Không có sản phẩm nào sắp hết hàng";
        }
        System.out.println("" + s + listSP_Het);
        String title = "Báo cáo doanh thu ngày" + "(" + java.time.LocalDate.now() + ")";
        String body
                = "Tổng doanh thu của ngày hôm nay: " + tongDoanhThuNgay + "\n"
                + "Tổng đơn hàng của ngày hôm nay: " + tongDonHang + "\n"
                + "Đơn thành công: " + donThanhCong + "\n"
                + "Đơn bị hủy: " + donBiHuy + "\n"
                + "\nSản phẩm gần hết hàng: " + s + listSP_Het + "\n"
                + "\nNhân viên bán hàng: " + nhanVien;
        try {
            final String fromEmail = "shelbycompany.nd@gmail.com";
            // Mat khai email cua ban
            final String password = "sljrvxfsbibpntqe";
            // dia chi email nguoi nhan
            final String toEmail = "tranquanghuy3103@gmail.com";
            final String subject = "Java Example Test";
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            message.setSubject(subject);
            message.setSubject(title);
            message.setSubject(title, "UTF-8");
            message.setText(body, "UTF-8");
//            String htmlContent = "<h3>" + body + "</h3>";
//            message.setContent(htmlContent, "text/html");
            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Báo cáo doanh thu thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bieuDo_Thang() {
        int nam = (int) cbbNam.getSelectedItem();
        listThang = viewThongKeService.getThang(nam);
        listGiaBan = viewThongKeService.getGiaBan(nam);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i <= listGiaBan.size() - 1; i++) {
            dataset.addValue(Double.parseDouble((listGiaBan.get(i)).toString()), "Tổng giá bán", listThang.get(i));
        }

        JFreeChart chart = ChartFactory.createBarChart("Doanh thu", "Tháng", "Tổng giá bán", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpBang.getWidth(), 500));

        jpBang.removeAll();
        jpBang.setLayout(new CardLayout());
        jpBang.add(chartPanel);
        jpBang.validate();
        jpBang.repaint();
    }

    private void bieuDo_Nam() {
        listGiaBan_Nam = viewThongKeService.getGiaBan_Nam();
        listNam2 = viewThongKeService.getNam_2();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        for (int i = 0; i < listGiaBan_Nam.size(); i++) {
            dataset2.addValue(Double.parseDouble((listGiaBan_Nam.get(i)).toString()), "Tổng giá bán", listNam2.get(i));
        }

        JFreeChart chart2 = ChartFactory.createBarChart("Doanh thu", "Năm", "Tổng giá bán", dataset2);
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        chartPanel2.setPreferredSize(new Dimension(jpBang.getWidth(), 500));

        jpBang.removeAll();
        jpBang.setLayout(new CardLayout());
        jpBang.add(chartPanel2);
        jpBang.validate();
        jpBang.repaint();
    }

    private void xuatExcel_DoanhThuThang() {
        try {
            int nam = (int) cbbNam.getSelectedItem();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Chi tiết doanh thu năm " + nam);
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tháng");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tổng giá bán");

            DecimalFormat formatter = new DecimalFormat("###,###,###");
            for (int i = 0; i <= listGiaBan.size() - 1; i++) {
                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(listThang.get(i));

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(formatter.format(listGiaBan.get(i)));
            }

            File file = new File("C:\\Users\\Huy PC\\Desktop\\anh\\CT_DoanhThu.xlsx");
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
    }

    private void xuatExcel_DoanhThuNam() {
        listGiaBan_Nam = viewThongKeService.getGiaBan_Nam();
        listNam2 = viewThongKeService.getNam_2();
        try {
            int nam = (int) cbbNam.getSelectedItem();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Chi tiết doanh thu các năm");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Năm");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tổng giá bán");

            DecimalFormat formatter = new DecimalFormat("###,###,###");
            for (int i = 0; i <= listGiaBan_Nam.size() - 1; i++) {
                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(listNam2.get(i));

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(formatter.format(listGiaBan_Nam.get(i)));
            }

            File file = new File("D:\\CT_DoanhThu.xlsx");
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
    }

    private boolean checkNgay() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date ngayBatDau = java.sql.Date.valueOf(dpNgayBatDau.getDate());
        Date ngayKetThuc = java.sql.Date.valueOf(dpNgayKetThuc.getDate());
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

    private void phanQuyen() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        if (account.getChucVuAccount().getId() == 2) {
            lblTongDoanhThuNam.setText("0" + " VNĐ");
            lblTongDoanhThuThang.setText("0" + " VNĐ");
            jTabbedPane1.setSelectedComponent(jPanel7);
            jTabbedPane1.remove(jPanel6);
        } else {
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
        barRenderer1 = new org.jfree.chart.renderer.category.BarRenderer();
        barRenderer2 = new org.jfree.chart.renderer.category.BarRenderer();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTongDonHang = new javax.swing.JLabel();
        lblThanhCong = new javax.swing.JLabel();
        lblBiHuy = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblTongDoanhThuNgay = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblTongDoanhThuThang = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblTongDoanhThuNam = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNgayBatDau = new javax.swing.JLabel();
        lblNgayKetThuc = new javax.swing.JLabel();
        btTimKiem = new javax.swing.JButton();
        cbbLoaiThoiGian = new javax.swing.JComboBox<>();
        dpNgayBatDau = new com.github.lgooddatepicker.components.DatePicker();
        dpNgayKetThuc = new com.github.lgooddatepicker.components.DatePicker();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jpBang = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbbNam = new javax.swing.JComboBox<>();
        btXuatExcel = new javax.swing.JButton();
        radioNam = new javax.swing.JRadioButton();
        radioThang = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        tbCTSP = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbChiTietSanPham = new javax.swing.JTable();
        btBaoCaoDoanhThu = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1074, 624));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1086, 642));
        setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1074, 219));

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(220, 120));

        jLabel1.setText("Tổng đơn hàng");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        lbl.setText("Thành công:");
        lbl.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Bị hủy:");
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        lblTongDonHang.setText("0");
        lblTongDonHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongDonHang.setForeground(new java.awt.Color(255, 255, 255));

        lblThanhCong.setText("0");
        lblThanhCong.setForeground(new java.awt.Color(255, 255, 255));

        lblBiHuy.setText("0");
        lblBiHuy.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBiHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThanhCong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTongDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addGap(70, 70, 70)))
                .addComponent(jLabel17)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTongDonHang)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl)
                            .addComponent(lblThanhCong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblBiHuy))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));
        jPanel3.setPreferredSize(new java.awt.Dimension(220, 120));

        jLabel7.setText("Tổng doanh thu ngày");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        lblTongDoanhThuNgay.setText("0");
        lblTongDoanhThuNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongDoanhThuNgay.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setIcon(new javax.swing.ImageIcon("D:\\PRO1041\\DuAn1_Nhom4\\src\\main\\icon\\money.png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTongDoanhThuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addGap(106, 106, 106)
                        .addComponent(jLabel20)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongDoanhThuNgay)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel4.setBackground(new java.awt.Color(255, 153, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(220, 120));

        jLabel10.setText("Tổng doanh thu tháng");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        lblTongDoanhThuThang.setText("0");
        lblTongDoanhThuThang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongDoanhThuThang.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setIcon(new javax.swing.ImageIcon("D:\\PRO1041\\DuAn1_Nhom4\\src\\main\\icon\\1month.png")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jLabel21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTongDoanhThuThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(55, 55, 55)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongDoanhThuThang)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 153, 153));
        jPanel5.setPreferredSize(new java.awt.Dimension(220, 120));

        jLabel13.setText("Tổng doanh thu năm");
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));

        lblTongDoanhThuNam.setText("0");
        lblTongDoanhThuNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongDoanhThuNam.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\PRO1041\\DuAn1_Nhom4\\src\\main\\icon\\1year.png")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblTongDoanhThuNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel22)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(65, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongDoanhThuNam)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Loại thời gian");

        lblNgayBatDau.setText("Ngày bắt đầu");

        lblNgayKetThuc.setText("Ngày kết thúc");

        btTimKiem.setText("Tìm kiếm");
        btTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemActionPerformed(evt);
            }
        });

        cbbLoaiThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLoaiThoiGian.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiThoiGianItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cbbLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblNgayBatDau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dpNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblNgayKetThuc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dpNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btTimKiem)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblNgayKetThuc)
                        .addComponent(cbbLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dpNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btTimKiem))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dpNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNgayBatDau)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jpBang.setBackground(new java.awt.Color(255, 255, 255));
        jpBang.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        jpBang.setPreferredSize(new java.awt.Dimension(861, 71));

        javax.swing.GroupLayout jpBangLayout = new javax.swing.GroupLayout(jpBang);
        jpBang.setLayout(jpBangLayout);
        jpBangLayout.setHorizontalGroup(
            jpBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        jpBangLayout.setVerticalGroup(
            jpBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jLabel16.setText("Năm");

        cbbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNamItemStateChanged(evt);
            }
        });

        btXuatExcel.setIcon(new javax.swing.ImageIcon("D:\\PRO1041\\DuAn1_Nhom4\\src\\main\\icon\\icons8-export-excel-24.png")); // NOI18N
        btXuatExcel.setText("Xuất excel");
        btXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXuatExcelActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioNam);
        radioNam.setSelected(true);
        radioNam.setText("Năm");
        radioNam.setBackground(new java.awt.Color(255, 255, 255));
        radioNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioThang);
        radioThang.setText("Tháng");
        radioThang.setBackground(new java.awt.Color(255, 255, 255));
        radioThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioThangActionPerformed(evt);
            }
        });

        jLabel3.setText("Thống kê theo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbbNam, 0, 170, Short.MAX_VALUE)
                        .addComponent(btXuatExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(radioNam)
                        .addGap(18, 18, 18)
                        .addComponent(radioThang))
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(jpBang, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBang, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioNam)
                    .addComponent(radioThang))
                .addGap(29, 29, 29)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Doanh thu", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tbCTSP.setBackground(new java.awt.Color(255, 255, 255));
        tbCTSP.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));

        jLabel18.setText("Thông tin chi tiết sản phẩm");
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        tbChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Màu sắc", "Hãng", "Chất liệu", "Kích cỡ", "Loại SP", "Số lượng bán"
            }
        ));
        jScrollPane2.setViewportView(tbChiTietSanPham);

        javax.swing.GroupLayout tbCTSPLayout = new javax.swing.GroupLayout(tbCTSP);
        tbCTSP.setLayout(tbCTSPLayout);
        tbCTSPLayout.setHorizontalGroup(
            tbCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbCTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbCTSPLayout.createSequentialGroup()
                .addContainerGap(365, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(294, 294, 294))
        );
        tbCTSPLayout.setVerticalGroup(
            tbCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbCTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );

        btBaoCaoDoanhThu.setIcon(new javax.swing.ImageIcon("D:\\PRO1041\\DuAn1_Nhom4\\src\\main\\icon\\thongke.png")); // NOI18N
        btBaoCaoDoanhThu.setText("Báo cáo doanh thu");
        btBaoCaoDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBaoCaoDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btBaoCaoDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tbCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btBaoCaoDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamItemStateChanged
        // TODO add your handling code here:
        if (radioThang.isSelected()) {
            if (cbbNam.getSelectedItem() != null) {

                bieuDo_Thang();
            }
        }
    }//GEN-LAST:event_cbbNamItemStateChanged

    private void btTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemActionPerformed
        // TODO add your handling code here:
        if (cbbLoaiThoiGian.getSelectedItem().equals("Theo ngày")) {
            DecimalFormat formatter = new DecimalFormat();
            String ngayBatDau = dpNgayBatDau.getDateStringOrEmptyString();
            String ngayKetThuc = dpNgayKetThuc.getDateStringOrEmptyString();
            if (checkNgay()) {
                if (viewThongKeService.getTongDoanhThuNgay2(ngayBatDau, ngayKetThuc) == null) {
                    lblTongDoanhThuNgay.setText("0" + " VNĐ");
                } else {
                    lblTongDoanhThuNgay.setText((formatter.format(viewThongKeService.getTongDoanhThuNgay2(ngayBatDau, ngayKetThuc)) + " VNĐ"));
                }
            }
        }
    }//GEN-LAST:event_btTimKiemActionPerformed

    private void cbbLoaiThoiGianItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiThoiGianItemStateChanged
        // TODO add your handling code here:
        showThongKe();
        phanQuyen();
    }//GEN-LAST:event_cbbLoaiThoiGianItemStateChanged

    private void btBaoCaoDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBaoCaoDoanhThuActionPerformed
        // TODO add your handling code here:
        sendEmail();
    }//GEN-LAST:event_btBaoCaoDoanhThuActionPerformed

    private void btXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXuatExcelActionPerformed
        // TODO add your handling code here:
        if (radioNam.isSelected()) {
            xuatExcel_DoanhThuNam();
        } else {
            xuatExcel_DoanhThuThang();
        }
    }//GEN-LAST:event_btXuatExcelActionPerformed

    private void radioNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNamActionPerformed
        // TODO add your handling code here:
        bieuDo_Nam();
    }//GEN-LAST:event_radioNamActionPerformed

    private void radioThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioThangActionPerformed
        // TODO add your handling code here:
        bieuDo_Thang();
    }//GEN-LAST:event_radioThangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jfree.chart.renderer.category.BarRenderer barRenderer1;
    private org.jfree.chart.renderer.category.BarRenderer barRenderer2;
    private javax.swing.JButton btBaoCaoDoanhThu;
    private javax.swing.JButton btTimKiem;
    private javax.swing.JButton btXuatExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLoaiThoiGian;
    private javax.swing.JComboBox<String> cbbNam;
    private com.github.lgooddatepicker.components.DatePicker dpNgayBatDau;
    private com.github.lgooddatepicker.components.DatePicker dpNgayKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpBang;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblBiHuy;
    private javax.swing.JLabel lblNgayBatDau;
    private javax.swing.JLabel lblNgayKetThuc;
    private javax.swing.JLabel lblThanhCong;
    private javax.swing.JLabel lblTongDoanhThuNam;
    private javax.swing.JLabel lblTongDoanhThuNgay;
    private javax.swing.JLabel lblTongDoanhThuThang;
    private javax.swing.JLabel lblTongDonHang;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioThang;
    private javax.swing.JPanel tbCTSP;
    private javax.swing.JTable tbChiTietSanPham;
    // End of variables declaration//GEN-END:variables
}
