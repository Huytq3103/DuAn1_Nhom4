package com.poly.pro_1041.it17322.group4.view;

/**
 *
 * @author qcuong
 */
import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.service.ViewDoiMatKhauService;
import com.poly.pro_1041.it17322.group4.service.impl.ViewDoiMatKhauServiceImpl;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ViewDoiMatKhau extends javax.swing.JPanel {

    private ViewDoiMatKhauService viewDoiMatKhauService;
    private Account acc = new Account();

    public ViewDoiMatKhau(Account account) {
        initComponents();
        this.acc = account;
        viewDoiMatKhauService = new ViewDoiMatKhauServiceImpl();
        lbCaptcha.setText(viewDoiMatKhauService.getCaptcha());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtXacNhanMKMoi = new javax.swing.JPasswordField();
        txtMKCu = new javax.swing.JPasswordField();
        txtMKMoi = new javax.swing.JPasswordField();
        btnLuuThayDoi = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lbCaptcha = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        txtCaptcha = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1086, 642));
        setMinimumSize(new java.awt.Dimension(1086, 642));
        setPreferredSize(new java.awt.Dimension(1086, 642));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Mật khẩu cũ:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Mật khẩu mới:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Xác nhận mật khẩu mới:");

        txtXacNhanMKMoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtMKCu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtMKMoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnLuuThayDoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLuuThayDoi.setIcon(new ImageIcon
            ("src/main/icon/UpdateP.png"));
        btnLuuThayDoi.setText("Lưu thay đổi");
        btnLuuThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuThayDoiActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThoat.setIcon(new ImageIcon
            ("src/main/icon/Thoat.png"));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Nhập mã captcha:");

        lbCaptcha.setBackground(new java.awt.Color(204, 204, 204));
        lbCaptcha.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lbCaptcha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCaptcha.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Gợi ý: Dùng ít nhất 8 kí tự.");

        jLabel8.setText("Kết hợp các kí tự a-z, số 0-9 và một số kí tự đặc biệt");

        jLabel10.setText("Không nên sử dụng những chuỗi dễ đoán như ngày ");

        jLabel9.setText("sinh trong mật khẩu !!!");

        btnRefresh.setIcon(new ImageIcon
            ("src/main/icon/RefreshC.png"));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        txtCaptcha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(217, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnLuuThayDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(btnThoat)
                                .addGap(61, 61, 61))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtXacNhanMKMoi)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txtCaptcha, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbCaptcha, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtMKMoi)
                                    .addComponent(txtMKCu))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMKCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtXacNhanMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbCaptcha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaptcha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuThayDoi)
                    .addComponent(btnThoat))
                .addGap(90, 90, 90))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        lbCaptcha.setText(viewDoiMatKhauService.getCaptcha());
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnLuuThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuThayDoiActionPerformed
        doiMatKhau();
    }//GEN-LAST:event_btnLuuThayDoiActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed

    }//GEN-LAST:event_btnThoatActionPerformed

    public void doiMatKhau() {
        String mkCu = txtMKCu.getText();
        String mkMoi = txtMKMoi.getText();
        String xnMKMoi = txtXacNhanMKMoi.getText();
        String captcha = txtCaptcha.getText();
        String code = lbCaptcha.getText();
        if (mkCu.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu cũ!");
        } else if (mkMoi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới!");
        } else if (xnMKMoi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng xác nhận lại mật khẩu!");
        } else if (captcha.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập captcha!");
        } else if (captcha.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập captcha!");
        } else if (!mkCu.equalsIgnoreCase(acc.getPassword())) {
            JOptionPane.showMessageDialog(this, "Sai mật khẩu cũ!");
        } else if (!mkMoi.equalsIgnoreCase(xnMKMoi)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới không trùng khớp!");
        } else if (!captcha.equalsIgnoreCase(code)) {
            JOptionPane.showMessageDialog(this, "Captcha không chính xác!");
        } else {
            acc.setPassword(mkMoi);
            viewDoiMatKhauService.update(acc);
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuuThayDoi;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbCaptcha;
    private javax.swing.JTextField txtCaptcha;
    private javax.swing.JPasswordField txtMKCu;
    private javax.swing.JPasswordField txtMKMoi;
    private javax.swing.JPasswordField txtXacNhanMKMoi;
    // End of variables declaration//GEN-END:variables
}
