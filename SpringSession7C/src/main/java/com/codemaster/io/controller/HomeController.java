package com.codemaster.io.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "index"; // point to index.html
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "dashboard"; // point to dashboard.html
    }

    @GetMapping("/admin")
    public String admin(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "admin";
    }

//    @GetMapping("/user")
//    public String user(Principal principal, Model model) {
//        if (principal != null) {
//            model.addAttribute("username", principal.getName());
//        }
//        return "user";
//    }

    @DeleteMapping("file/{id}")
    public String fileDelete(@PathVariable String id) {
        System.out.println("File DeleteRequest = " + id);
        return "Success";
    }

    @GetMapping("/error_403")
    public String accessError() {
        System.out.println("error_403");
        return "error_403";
    }
}
