package com.el.spring.service;

import com.el.spring.entity.TempUser;
import org.springframework.stereotype.Service;

@Service
public class TempUserServiceImpl implements TempUserService {

    @Override
    public TempUser getUser(String login) {
        TempUser tempuser = new TempUser();
        tempuser.setLogin(login);
        tempuser.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return tempuser;
    }
}