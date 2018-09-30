package com.qworldr.mmorpg.logic.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class AccountService {

    @Autowired
    private JpaRepository jpa;
}
