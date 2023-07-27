package lk.ijse.cuisineCompass.entity;

import lombok.*;

@Data
//@AllArgsConstructor

public class Category {

    private String code;
    private String type;
    private String des;

    public Category() {

    }

    public Category(String code, String type, String des) {
        this.code = code;
        this.type = type;
        this.des = des;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Category{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
