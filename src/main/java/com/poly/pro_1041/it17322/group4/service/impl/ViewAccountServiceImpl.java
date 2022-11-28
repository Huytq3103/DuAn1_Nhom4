/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.repository.AccountRepository;
import com.poly.pro_1041.it17322.group4.response.ViewAccountReponse;
import com.poly.pro_1041.it17322.group4.service.ViewAccountService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ViewAccountServiceImpl implements ViewAccountService {

    private AccountRepository accrp = new AccountRepository();

    @Override
    public List<ViewAccountReponse> getAll() {
        List<ViewAccountReponse> listAcc = new ArrayList<>();
        List<Account> lists = accrp.getAll();
        for (Account x : lists) {
            listAcc.add(new ViewAccountReponse(x));
        }
        return listAcc;
    }

    public static void main(String[] args) {
        List<Account> lists = new AccountRepository().getAll();
        for (Account x : lists) {
            System.out.println(x.toString());
        }
    }

    @Override
    public String add(ViewAccountReponse vaccr) {
        Account acc = new Account(null, vaccr.getCvac(), vaccr.getTta(), vaccr.getHoTen(), vaccr.getNgaySinh(), vaccr.isGioiTinh(), vaccr.getSdt(), vaccr.getDiaChi(), vaccr.getEmail(), vaccr.getUsername(), vaccr.getPassword(), vaccr.getNgayTao(), vaccr.getNguoiTao(), null, null);
        boolean add = accrp.add(acc);
        if (add) {
            return "Thêm thành công!";
        } else {
            return "Thêm lỗi!";
        }
    }

    @Override
    public String update(ViewAccountReponse vaccr) {
        Account acc = new Account(vaccr.getId(), vaccr.getCvac(), vaccr.getTta(), vaccr.getHoTen(), vaccr.getNgaySinh(), vaccr.isGioiTinh(), vaccr.getSdt(), vaccr.getDiaChi(), vaccr.getEmail(), vaccr.getUsername(), vaccr.getPassword(), vaccr.getNgayTao(), vaccr.getNguoiTao(), vaccr.getNguoiChinhSua(), vaccr.getNgayChinhSua());
        boolean update = accrp.update(acc);
        if (update) {
            return "Sửa thành công!";
        } else {
            return "Sửa lỗi!";
        }
    }

}
