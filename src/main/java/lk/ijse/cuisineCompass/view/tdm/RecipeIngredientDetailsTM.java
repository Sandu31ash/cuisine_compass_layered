package lk.ijse.cuisineCompass.view.tdm;

public class RecipeIngredientDetailsTM implements Comparable<RecipeIngredientDetailsTM>{

    private String recipeCode;
    private String ingCode;
    private String unit;
    private Double qty;

    public RecipeIngredientDetailsTM() {

    }

    public RecipeIngredientDetailsTM(String recipeCode, String ingCode, String unit, Double qty) {
        this.recipeCode = recipeCode;
        this.ingCode = ingCode;
        this.unit = unit;
        this.qty = qty;
    }

    public RecipeIngredientDetailsTM(String ingCode, String unit, Double qty) {
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

    @Override
    public int compareTo(RecipeIngredientDetailsTM o) {
        return recipeCode.compareTo(o.getRecipeCode());
    }

}
