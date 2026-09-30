package com.library.service;

import com.library.dao.LoanDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LoanService {
    private final LoanDAO dao=new LoanDAO();
    public void issue(int bookId,int memberId,LocalDate due)throws SQLException{dao.issue(bookId,memberId,due);}
    public List<String[]> activeLoans()throws SQLException{return dao.activeLoans();}
    public double returnBook(int loanId)throws SQLException{return dao.returnBook(loanId);}
}
