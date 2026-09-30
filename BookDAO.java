package com.library.dao;

import com.library.model.Book;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.*;

public class BookDAO {
    public void add(Book b) throws SQLException {
        String sql="INSERT INTO books(title,author,isbn,category,quantity,available_quantity) VALUES(?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,b.getTitle()); p.setString(2,b.getAuthor()); p.setString(3,b.getIsbn());
            p.setString(4,b.getCategory()); p.setInt(5,b.getQuantity()); p.setInt(6,b.getQuantity());
            p.executeUpdate();
        }
    }

    public List<Book> findAll() throws SQLException {
        List<Book> list=new ArrayList<>();
        try(Connection c=DBConnection.getConnection();
            PreparedStatement p=c.prepareStatement("SELECT * FROM books ORDER BY id DESC");
            ResultSet r=p.executeQuery()){
            while(r.next()){
                Book b=new Book();
                b.setId(r.getInt("id")); b.setTitle(r.getString("title"));
                b.setAuthor(r.getString("author")); b.setIsbn(r.getString("isbn"));
                b.setCategory(r.getString("category")); b.setQuantity(r.getInt("quantity"));
                b.setAvailableQuantity(r.getInt("available_quantity")); list.add(b);
            }
        }
        return list;
    }

    public void delete(int id) throws SQLException {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("DELETE FROM books WHERE id=?")){
            p.setInt(1,id); p.executeUpdate();
        }
    }
}
