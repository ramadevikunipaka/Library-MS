package com.library.service;

import com.library.dao.UserDAO;
import java.sql.SQLException;

public class AuthService {
    private final UserDAO dao=new UserDAO();
    public boolean login(String username,String password)throws SQLException{return dao.authenticate(username,password);}
}
