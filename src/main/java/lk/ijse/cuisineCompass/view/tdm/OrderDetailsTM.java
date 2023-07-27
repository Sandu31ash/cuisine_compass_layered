package lk.ijse.cuisineCompass.view.tdm;

public class OrderDetailsTM implements Comparable<OrderDetailsTM>{

    private String code;
    private String iCode;
    private double price;
    private double qty;
    private double tot;

    public OrderDetailsTM() {

    }

    public OrderDetailsTM(String code, String iCode, double price, double qty, double tot) {
        this.code = code;
        this.iCode = iCode;
        this.price = price;
        this.qty = qty;
        this.tot = tot;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    @Override
    public String toString() {
        return "OrderDetailsTM{" +
                "code='" + code + '\'' +
                ", iCode='" + iCode + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", tot=" + tot +
                '}';
    }

    @Override
    public int compareTo(OrderDetailsTM o) {
        return 0;
    }
}
