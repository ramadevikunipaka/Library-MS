package com.library.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException, jakarta.servlet.ServletException{
        if(req.getSession().getAttribute("username")==null){resp.sendRedirect(req.getContextPath()+"/login.jsp");return;}
        req.getRequestDispatcher("/dashboard.jsp").forward(req,resp);
    }
}
