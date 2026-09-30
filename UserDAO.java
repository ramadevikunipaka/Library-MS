package com.library.dao;

import com.library.util.DBConnection;
import java.sql.*;

public class UserDAO {
    public boolean authenticate(String username, String password) throws SQLException {
        String sql="SELECT id FROM users WHERE username=? AND password=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,username); p.setString(2,password);
            try(ResultSet r=p.executeQuery()){ return r.next(); }
        }
    }
}
