package com.rendezvous.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        int statusCode=0;
        if (status != null) {
            statusCode = Integer.valueOf(status.toString());
        }       
        model.addAttribute("status", statusCode);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
