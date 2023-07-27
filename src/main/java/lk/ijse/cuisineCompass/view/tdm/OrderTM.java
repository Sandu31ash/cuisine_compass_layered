package lk.ijse.cuisineCompass.view.tdm;

public class OrderTM implements Comparable<OrderTM>{

    private String code;
    private String id;
    private String date;
    private String orderBy;
    /*private String supplier;
    private String iCode;
    private double price;
    private double qty;
    private double tot;*/

    public OrderTM() {

    }

    public OrderTM(String code, String id, String date, String orderBy/*, String supplier, String iCode, double price, double qty, double tot*/) {
        this.code = code;
        this.id = id;
        this.date = date;
        this.orderBy = orderBy;
        /*this.supplier = supplier;
        this.iCode = iCode;
        this.price = price;
        this.qty = qty;
        this.tot = tot;*/
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /*public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
    }*/

    /*@Override
    public String toString() {
        return "Order{" +
                "code='" + code + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", supplier='" + supplier + '\'' +
                ", iCode='" + iCode + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", tot=" + tot +
                '}';
    }*/

    @Override
    public String toString() {
        return "OrderTM{" +
                "code='" + code + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }

    @Override
    public int compareTo(OrderTM o) {
        return code.compareTo(o.getCode());
    }
}
