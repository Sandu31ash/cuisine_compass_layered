package lk.ijse.cuisineCompass.dto;

import java.sql.Date;

public class InventoryDTO {

    private String ingCode;
    private String des;
    private String unit;
    private double par;
    private double qty;
    private String date;

    public InventoryDTO(String iCode, String des, String unit, double par, double qty, Date date){

    }

    public InventoryDTO(String ingCode, String des, String unit, double par, double qty, String date) {
        this.ingCode = ingCode;
        this.des = des;
        this.unit = unit;
        this.par = par;
        this.qty = qty;
        this.date = date;
    }

    public InventoryDTO(String ingCode) {
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

    public double getPar() {
        return par;
    }

    public void setPar(double par) {
        this.par = par;
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
        return "Inventory{" +
                "ingCode='" + ingCode + '\'' +
                ", des='" + des + '\'' +
                ", unit='" + unit + '\'' +
                ", par=" + par +
                ", qty=" + qty +
                ", date='" + date + '\'' +
                '}';
    }

}
