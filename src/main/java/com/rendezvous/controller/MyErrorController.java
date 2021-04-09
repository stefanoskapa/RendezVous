package com.rendezvous.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        int statusCode = 0;
        if (status != null) {
            statusCode = Integer.valueOf(status.toString());
        }

        String message;
        switch (statusCode) {
            case 400:
                message = "Bad Request";
                break;
            case 401:
                message = "Unauthorized";
                break;
            case 403:
                message = "Forbidden";
                break;
            case 404:
                message = "Page not found";
                break;
            case 405:
                message = "Method Not Allowed";
                break;
            case 406:
                message = "Not Acceptable";
                break;
            case 407:
                message = "Proxy Authentication Required";
                break;
            case 408:
                message = "Request Timeout";
                break;
            case 409:
                message = "Conflict";
                break;
            case 410:
                message = "Gone";
                break;
            case 411:
                message = "Length Required";
                break;
            case 412:
                message = "Precondition Failed";
                break;
            case 413:
                message = "Payload Too Large";
                break;
            case 414:
                message = "URI Too Long";
                break;
            case 415:
                message = "Unsupported Media Type";
                break;
            case 416:
                message = "Range Not Satisfiable";
                break;
            case 417:
                message = "Expectation Failed";
                break;
            case 421:
                message = "Misdirected Request";
                break;
            case 422:
                message = "Unprocessable Entity";
                break;
            case 429:
                message = "Too Many Requests";
                break;
            case 431:
                message = "Request Header Fields Too Large";
                break;
            case 500:
                message = "Internal Server Error";
                break;
            case 501:
                message = "Not Implemented";
                break;
            case 502:
                message = "Bad Gateway";
                break;
            case 503:
                message = "Service Unavailable";
                break;
            case 505:
                message = "HTTP Version Not Supported";
                break;
            default:
                message = "Something went wrong";
                break;
        }

        model.addAttribute("msg", message);
        model.addAttribute("status", statusCode);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
