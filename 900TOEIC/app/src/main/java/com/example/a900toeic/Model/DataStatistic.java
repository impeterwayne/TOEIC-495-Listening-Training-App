package com.example.a900toeic.Model;

import java.util.Date;

public class DataStatistic {
    private long num_part1, num_part2, num_part3, num_part4;
    private Date date;

    public DataStatistic(long num_part1, long num_part2, long num_part3, long num_part4, Date date) {
        this.num_part1 = num_part1;
        this.num_part2 = num_part2;
        this.num_part3 = num_part3;
        this.num_part4 = num_part4;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DataStatistic() {
    }


    public long getNum_part1() {
        return num_part1;
    }

    public void setNum_part1(long num_part1) {
        this.num_part1 = num_part1;
    }

    public long getNum_part2() {
        return num_part2;
    }

    public void setNum_part2(long num_part2) {
        this.num_part2 = num_part2;
    }

    public long getNum_part3() {
        return num_part3;
    }

    public void setNum_part3(long num_part3) {
        this.num_part3 = num_part3;
    }

    public long getNum_part4() {
        return num_part4;
    }

    public void setNum_part4(long num_part4) {
        this.num_part4 = num_part4;
    }

    @Override
    public String toString() {
        return "DataStatistic{" +
                "num_part1=" + num_part1 +
                ", num_part2=" + num_part2 +
                ", num_part3=" + num_part3 +
                ", num_part4=" + num_part4 +
                ", date=" + date +
                '}';
    }
}
