package com.ktu.vavadoge;

public class OneDebt {
    private int id;
    private String date;
    private String information;
    private String type;

    public OneDebt(int id, String date, String information, String type)
    {
        this.id = id;
        this.date = date;
        this.information = information;
        this.type = type;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
}
