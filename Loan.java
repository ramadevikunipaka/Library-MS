package com.library.model;

import java.time.LocalDate;

public class Loan {
    private int id, bookId, memberId;
    private LocalDate issueDate, dueDate, returnDate;
    private String status;
    private double fine;

    public int getId(){return id;}
    public void setId(int v){id=v;}
    public int getBookId(){return bookId;}
    public void setBookId(int v){bookId=v;}
    public int getMemberId(){return memberId;}
    public void setMemberId(int v){memberId=v;}
    public LocalDate getIssueDate(){return issueDate;}
    public void setIssueDate(LocalDate v){issueDate=v;}
    public LocalDate getDueDate(){return dueDate;}
    public void setDueDate(LocalDate v){dueDate=v;}
    public LocalDate getReturnDate(){return returnDate;}
    public void setReturnDate(LocalDate v){returnDate=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
    public double getFine(){return fine;}
    public void setFine(double v){fine=v;}
}
