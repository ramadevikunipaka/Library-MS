package com.library.controller;

import com.library.model.Member;
import com.library.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/members")
public class MemberServlet extends HttpServlet {
    private final MemberService service=new MemberService();

    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            req.setAttribute("members",service.findAll());
            req.getRequestDispatcher("/members.jsp").forward(req,resp);
        }catch(Exception e){throw new ServletException(e);}
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            service.add(new Member(req.getParameter("name"),req.getParameter("email"),
                    req.getParameter("phone"),req.getParameter("address")));
            resp.sendRedirect(req.getContextPath()+"/members");
        }catch(Exception e){throw new ServletException(e);}
    }
}
