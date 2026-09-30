package com.library.controller;

import com.library.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService service=new AuthService();

    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            if(service.login(req.getParameter("username"),req.getParameter("password"))){
                req.getSession().setAttribute("username",req.getParameter("username"));
                resp.sendRedirect(req.getContextPath()+"/dashboard");
            } else {
                req.setAttribute("error","Invalid username or password.");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }catch(Exception e){throw new ServletException(e);}
    }
}
