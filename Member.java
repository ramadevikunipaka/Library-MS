package com.library.model;

public class Member {
    private int id;
    private String name, email, phone, address;

    public Member() {}
    public Member(String name, String email, String phone, String address) {
        this.name=name; this.email=email; this.phone=phone; this.address=address;
    }
    public int getId(){return id;}
    public void setId(int v){id=v;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getEmail(){return email;}
    public void setEmail(String v){email=v;}
    public String getPhone(){return phone;}
    public void setPhone(String v){phone=v;}
    public String getAddress(){return address;}
    public void setAddress(String v){address=v;}
}
