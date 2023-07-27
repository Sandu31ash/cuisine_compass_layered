package lk.ijse.cuisineCompass.entity;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
//@AllArgsConstructor

public class Ingredient {

    private String ingCode;
    private String des;
    private String unit;
    private double qty;
    private String date;

    public Ingredient() {

    }

    public Ingredient(String ingCode, String des, String unit, double qty, String date) {
        this.ingCode = ingCode;
        this.des = des;
        this.unit = unit;
        this.qty = qty;
        this.date = date;
    }

    public Ingredient(String ingCode, String des) {
        this.ingCode = ingCode;
        this.des = des;
    }

    public Ingredient(String ingCode) {
        this.ingCode = ingCode;
    }

    public String getIngCode() {
        return ingCode;
    }

    public void setIngCode(String ingCode) {
        this.ingCode = ingCode;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingCode='" + ingCode + '\'' +
                ", des='" + des + '\'' +
                ", unit='" + unit + '\'' +
                ", qty=" + qty +
                ", date='" + date + '\'' +
                '}';
    }
}
