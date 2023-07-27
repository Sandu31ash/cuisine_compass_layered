package lk.ijse.cuisineCompass.dto;

public class RecipeIngredientDetailsDTO {

    private String recipeCode;
    private String ingCode;
    private String unit;
    private Double qty;

    public RecipeIngredientDetailsDTO() {

    }

    public RecipeIngredientDetailsDTO(String recipeCode, String ingCode, String unit, Double qty) {
        this.recipeCode = recipeCode;
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

}
