/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.domainmodel;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "SanPham")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SanPham {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;
    
    @Column(name = "Ma")
    private String ma;

    @Column(name = "TenSP")
    private String tenSP;
    
    @Column(name = "NgayTao")
    private String ngayTao;
}
