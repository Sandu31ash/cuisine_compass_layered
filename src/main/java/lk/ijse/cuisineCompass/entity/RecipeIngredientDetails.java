package lk.ijse.cuisineCompass.entity;

public class RecipeIngredientDetails {

    private String recipeCode;
    private String ingCode;
    private String unit;
    private Double qty;

    public RecipeIngredientDetails() {

    }

    public RecipeIngredientDetails(String recipeCode, String ingCode, String unit, Double qty) {
        this.recipeCode = recipeCode;
        this.ingCode = ingCode;
        this.unit = unit;
        this.qty = qty;
    }

    public RecipeIngredientDetails(String ingCode, String unit, double qty) {
        this.ingCode = ingCode;
        this.unit = unit;
        this.qty = qty;
    }

    public String getRecipeCode() {
        return recipeCode;
    }

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    public String getIngCode() {
        return ingCode;
    }

    public void setIngCode(String ingCode) {
        this.ingCode = ingCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "RecipeIngredientDetails{" +
                "recipeCode='" + recipeCode + '\'' +
                ", ingCode='" + ingCode + '\'' +
                ", unit='" + unit + '\'' +
                ", qty=" + qty +
                '}';
    }

    public Object getDes() {
        return null;
    }
}
