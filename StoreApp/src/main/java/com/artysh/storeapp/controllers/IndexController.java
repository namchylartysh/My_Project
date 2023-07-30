package com.artysh.storeapp.controllers;

import com.artysh.storeapp.security.ClientDetails;
import com.artysh.storeapp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final AdminService adminService;

    @Autowired
    public IndexController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/index")
    public String sayHello() {
        return "index";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDetails clientDetails = (ClientDetails) authentication.getPrincipal();
        System.out.println(clientDetails.getClient());
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.checkAdminStuff();
        return "admin";
    }
}
