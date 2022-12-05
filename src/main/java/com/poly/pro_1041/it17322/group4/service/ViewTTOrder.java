package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import java.util.List;

/**
 *
 * @author qcuong
 */
public interface ViewTTOrder {

    List<TrangThaiOrder> getAll();

    TrangThaiOrder getID(String ten);
}
