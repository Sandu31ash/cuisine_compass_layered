package lk.ijse.cuisineCompass.view.tdm;

public class MenuTM implements Comparable<MenuTM>{

    private String mCode;
    private String dish;
    private String des;
    private String meal;
    private double price;
    private String cCode;
    private String userName;

    public MenuTM() {

    }

    public MenuTM(String mCode, String dish, String des, String meal, double price, String cCode, String userName) {
        this.mCode = mCode;
        this.dish = dish;
        this.des = des;
        this.meal = meal;
        this.price = price;
        this.cCode = cCode;
        this.userName = userName;
    }

    public MenuTM(String dish, double price) {
        this.dish = dish;
        this.price = price;
    }

    public String getMCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCCode() {
        return cCode;
    }

    public void setCCode(String cCode) {
        this.cCode = cCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "MenuTM{" +
                "mCode='" + mCode + '\'' +
                ", dish='" + dish + '\'' +
                ", des='" + des + '\'' +
                ", meal='" + meal + '\'' +
                ", price=" + price +
                ", cCode='" + cCode + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public int compareTo(MenuTM o) {
        return mCode.compareTo(o.getMCode());
    }
}
