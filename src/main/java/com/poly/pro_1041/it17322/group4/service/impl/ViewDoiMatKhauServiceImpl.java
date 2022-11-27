package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.repository.AccountRepository;
import com.poly.pro_1041.it17322.group4.service.ViewDoiMatKhauService;
import java.util.Random;

/**
 *
 * @author qcuong
 */
public class ViewDoiMatKhauServiceImpl implements ViewDoiMatKhauService {

    private AccountRepository accountRepository = new AccountRepository();

    @Override
    public String getCaptcha() {
        char data[] = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', '9', '8', '7', '6'
        };
        char index[] = new char[6];

        Random r = new Random();

        int i = 0;
        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }
        return new String(index);
    }

    public Boolean update(Account acc) {
        if (accountRepository.update(acc)) {
            return true;
        } else {
            return false;
        }
    }
}
