/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiKM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Lenovo
 */
@Setter
@Getter
@ToString
public class ViewTrangThaiKMResponse {
    private int id;
    private String ma;
    private String ten;

    public ViewTrangThaiKMResponse() {
    }

    public ViewTrangThaiKMResponse(TrangThaiKM trangThaiKM) {
        this.id = trangThaiKM.getId();
        this.ma = trangThaiKM.getMa();
        this.ten = trangThaiKM.getTen();
    }
    
    
}
