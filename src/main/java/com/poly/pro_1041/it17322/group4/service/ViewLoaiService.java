/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface ViewLoaiService {

    List<Loai> getAll();
    
    Loai getOneTen(String ten);
}
