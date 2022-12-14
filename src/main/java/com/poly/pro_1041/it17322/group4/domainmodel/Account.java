/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.domainmodel;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Huy PC
 */
@Entity
@Table(name = "Account")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCV", referencedColumnName = "Id")
    private ChucVuAccount chucVuAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTT", referencedColumnName = "Id")
    private TrangThaiAccount trangThaiAccount;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "NgaySinh")
    private String ngaySinh;

    @Column(name = "GioiTinh")
    private boolean gioiTinh;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Email")
    private String email;

    @Column(name = "Username")
    private String username;

    @Column(name = "[Password]")
    private String password;

    @Column(name = "NgayTao")
    private String ngayTao;

    @Column(name = "NguoiTao")
    private UUID nguoiTao;

    @Column(name = "NguoiChinhSua")
    private UUID nguoiChinhSua;

    @Column(name = "NgayChinhSua")
    private String ngayChinhSua;
}
