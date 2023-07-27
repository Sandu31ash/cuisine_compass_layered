package lk.ijse.cuisineCompass.entity;

public class Recipe {

    private String recipeCode;
    private String des;
    private String cateCode;
    private String method;
    private String course;
    private String addedBy;

    public Recipe() {

    }

    public Recipe(String recipeCode, String des, String cateCode, String method, String course, String addedBy) {
        this.recipeCode = recipeCode;
        this.des = des;
        this.cateCode = cateCode;
        this.method = method;
        this.course = course;
        this.addedBy = addedBy;
    }

    public Recipe(String des, String course) {
        this.des = des;
        this.course = course;
    }

    public Recipe(String des) {
        this.des = des;
    }

    public String getRecipeCode() {
        return recipeCode;
    }

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeCode='" + recipeCode + '\'' +
                ", des='" + des + '\'' +
                ", cateCode='" + cateCode + '\'' +
                ", method='" + method + '\'' +
                ", course='" + course + '\'' +
                ", addedBy='" + addedBy + '\'' +
                '}';
    }
}
