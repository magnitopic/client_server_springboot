package tienda.discos.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogInAdminController {
    @RequestMapping("logInAdmin")
    private String loginAdmin() {
        return "admin/loginAdmin";
    }
    @RequestMapping("logoutAdmin")
    private String logoutAdmin(HttpServletRequest request) {
        request.getSession().invalidate();
        return "inicio";
    }
    
}