package com.library.dao;

import com.library.model.Loan;
import com.library.util.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LoanDAO {
    private static final double FINE_PER_DAY = 5.0;

    public void issue(int bookId, int memberId, LocalDate dueDate) throws SQLException {
        try(Connection c=DBConnection.getConnection()){
            c.setAutoCommit(false);
            try {
                try(PreparedStatement p=c.prepareStatement(
                        "UPDATE books SET available_quantity=available_quantity-1 WHERE id=? AND available_quantity>0")){
                    p.setInt(1,bookId);
                    if(p.executeUpdate()!=1) throw new SQLException("Book is not available.");
                }
                try(PreparedStatement p=c.prepareStatement(
                        "INSERT INTO loans(book_id,member_id,issue_date,due_date,status,fine) VALUES(?,?,?,?,?,0)")){
                    p.setInt(1,bookId); p.setInt(2,memberId);
                    p.setDate(3,Date.valueOf(LocalDate.now()));
                    p.setDate(4,Date.valueOf(dueDate)); p.setString(5,"ISSUED"); p.executeUpdate();
                }
                c.commit();
            } catch(SQLException e){ c.rollback(); throw e; }
        }
    }

    public List<String[]> activeLoans() throws SQLException {
        List<String[]> list=new ArrayList<>();
        String sql="SELECT l.id,b.title,m.name,l.issue_date,l.due_date,l.status,l.fine " +
                   "FROM loans l JOIN books b ON l.book_id=b.id JOIN members m ON l.member_id=m.id " +
                   "WHERE l.status='ISSUED' ORDER BY l.id DESC";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql);
            ResultSet r=p.executeQuery()){
            while(r.next()) list.add(new String[]{
                String.valueOf(r.getInt("id")),r.getString("title"),r.getString("name"),
                r.getString("issue_date"),r.getString("due_date"),r.getString("status"),
                String.valueOf(r.getDouble("fine"))});
        }
        return list;
    }

    public double returnBook(int loanId) throws SQLException {
        try(Connection c=DBConnection.getConnection()){
            c.setAutoCommit(false);
            try {
                int bookId;
                LocalDate due;
                try(PreparedStatement p=c.prepareStatement(
                        "SELECT book_id,due_date FROM loans WHERE id=? AND status='ISSUED' FOR UPDATE")){
                    p.setInt(1,loanId);
                    try(ResultSet r=p.executeQuery()){
                        if(!r.next()) throw new SQLException("Active loan not found.");
                        bookId=r.getInt("book_id"); due=r.getDate("due_date").toLocalDate();
                    }
                }
                LocalDate today=LocalDate.now();
                long late=Math.max(0,ChronoUnit.DAYS.between(due,today));
                double fine=late*FINE_PER_DAY;
                try(PreparedStatement p=c.prepareStatement(
                        "UPDATE loans SET return_date=?,status='RETURNED',fine=? WHERE id=?")){
                    p.setDate(1,Date.valueOf(today)); p.setDouble(2,fine); p.setInt(3,loanId); p.executeUpdate();
                }
                try(PreparedStatement p=c.prepareStatement(
                        "UPDATE books SET available_quantity=available_quantity+1 WHERE id=?")){
                    p.setInt(1,bookId); p.executeUpdate();
                }
                c.commit(); return fine;
            } catch(SQLException e){ c.rollback(); throw e; }
        }
    }
}
