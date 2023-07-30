package com.artysh.storeapp.services;

import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public void checkAdminStuff() {
        System.out.println("Only admin here");
    }
}
