package com.generali.jwtauthbackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/test")
public class TestAccessController {

    @GetMapping(value = "/all")
    public String getAllAccess(){
        return "public content";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin")
    public String getAdminBoard(){
        return "public content admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF') or hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/staff")
    public String getStaffBoard(){
        return "public content staff";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/manager")
    public String getManagerBoard(){
        return "public content manager";
    }
}
