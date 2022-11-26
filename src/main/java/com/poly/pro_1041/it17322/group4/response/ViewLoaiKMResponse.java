/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.LoaiKM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Lenovo
 */
@Getter
@Setter
@ToString
public class ViewLoaiKMResponse {
    private int id;
    private String ma;
    private String ten;

    public ViewLoaiKMResponse() {
    }


    public ViewLoaiKMResponse(LoaiKM loaiKM) {
        this.id = loaiKM.getId();
        this.ma = loaiKM.getMa();
        this.ten = loaiKM.getTen();
    }

    
    
}
