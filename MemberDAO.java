package com.library.dao;

import com.library.model.Member;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.*;

public class MemberDAO {
    public void add(Member m) throws SQLException {
        String sql="INSERT INTO members(name,email,phone,address) VALUES(?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,m.getName()); p.setString(2,m.getEmail());
            p.setString(3,m.getPhone()); p.setString(4,m.getAddress()); p.executeUpdate();
        }
    }

    public List<Member> findAll() throws SQLException {
        List<Member> list=new ArrayList<>();
        try(Connection c=DBConnection.getConnection();
            PreparedStatement p=c.prepareStatement("SELECT * FROM members ORDER BY id DESC");
            ResultSet r=p.executeQuery()){
            while(r.next()){
                Member m=new Member();
                m.setId(r.getInt("id")); m.setName(r.getString("name"));
                m.setEmail(r.getString("email")); m.setPhone(r.getString("phone"));
                m.setAddress(r.getString("address")); list.add(m);
            }
        }
        return list;
    }
}
