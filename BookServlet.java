package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private final BookService service=new BookService();

    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            req.setAttribute("books",service.findAll());
            req.getRequestDispatcher("/books.jsp").forward(req,resp);
        }catch(Exception e){throw new ServletException(e);}
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            if("delete".equals(req.getParameter("action"))){
                service.delete(Integer.parseInt(req.getParameter("id")));
            }else{
                Book b=new Book(req.getParameter("title"),req.getParameter("author"),
                        req.getParameter("isbn"),req.getParameter("category"),
                        Integer.parseInt(req.getParameter("quantity")));
                service.add(b);
            }
            resp.sendRedirect(req.getContextPath()+"/books");
        }catch(Exception e){throw new ServletException(e);}
    }
}
