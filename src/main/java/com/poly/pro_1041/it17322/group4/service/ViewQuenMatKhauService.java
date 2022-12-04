package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;

/**
 *
 * @author qcuong
 */
public interface ViewQuenMatKhauService {

    Account checkEmail(String email);

    Boolean update(Account account);
}
