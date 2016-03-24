package com.gt.bmf.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Ranking implements Serializable {
    private static final long serialVersionUID = -1686320397097241613L;
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String code;

    private String name;
    private Date date1;
    private Date date2;
    private Date date3;
    private Double price1;
    private Double price2;
    private Double price3;
    private Double count1;
    private Double count2;
    private Double count3;

    private Double change1;
    private Double change2;

    public Double getChange1() {
        return change1;
    }

    public void setChange1(Double change1) {
        this.change1 = change1;
    }

    public Double getChange2() {
        return change2;
    }

    public void setChange2(Double change2) {
        this.change2 = change2;
    }

    public Double getCount1() {
        return count1;
    }

    public void setCount1(Double count1) {
        this.count1 = count1;
    }

    public Double getCount2() {
        return count2;
    }

    public void setCount2(Double count2) {
        this.count2 = count2;
    }

    public Double getCount3() {
        return count3;
    }

    public void setCount3(Double count3) {
        this.count3 = count3;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public Double getPrice1() {
        return price1;
    }

    public void setPrice1(Double price1) {
        this.price1 = price1;
    }

    public Double getPrice2() {
        return price2;
    }

    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    public Double getPrice3() {
        return price3;
    }

    public void setPrice3(Double price3) {
        this.price3 = price3;
    }
}
