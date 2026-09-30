package com.library.controller;

import com.library.service.LoanService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/loans")
public class LoanServlet extends HttpServlet {
    private final LoanService service=new LoanService();

    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            req.setAttribute("loans",service.activeLoans());
            req.getRequestDispatcher("/loans.jsp").forward(req,resp);
        }catch(Exception e){throw new ServletException(e);}
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            String action=req.getParameter("action");
            if("issue".equals(action)){
                service.issue(Integer.parseInt(req.getParameter("bookId")),
                        Integer.parseInt(req.getParameter("memberId")),
                        LocalDate.parse(req.getParameter("dueDate")));
            } else if("return".equals(action)){
                double fine=service.returnBook(Integer.parseInt(req.getParameter("loanId")));
                req.getSession().setAttribute("message","Book returned. Fine: ₹"+fine);
            }
            resp.sendRedirect(req.getContextPath()+"/loans");
        }catch(Exception e){throw new ServletException(e);}
    }
}
