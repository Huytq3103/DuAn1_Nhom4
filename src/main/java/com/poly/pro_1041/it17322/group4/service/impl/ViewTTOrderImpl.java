package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import com.poly.pro_1041.it17322.group4.repository.TrangThaiOrderRepository;
import java.util.List;
import com.poly.pro_1041.it17322.group4.service.ViewTTOrder;

/**
 *
 * @author qcuong
 */
public class ViewTTOrderImpl implements ViewTTOrder {

    private TrangThaiOrderRepository trangThaiOrderRepository = new TrangThaiOrderRepository();

    @Override
    public List<TrangThaiOrder> getAll() {
        return trangThaiOrderRepository.getAll();
    }

    @Override
    public TrangThaiOrder getID(String ten) {
        return trangThaiOrderRepository.getID(ten);
    }

}
