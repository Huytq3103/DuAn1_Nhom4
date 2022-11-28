package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import java.util.UUID;

/**
 *
 * @author qcuong
 */
public interface ViewDoiMatKhauService {

    String getCaptcha();

    Boolean update(Account account);
}
