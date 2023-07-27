package lk.ijse.cuisineCompass.bo;

import lk.ijse.cuisineCompass.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBOFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes) {
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case INGREDIENT:
                return new IngredientBOImpl();
            case INVENTORY:
                return new InventoryBOImpl();
            case CATEGORY:
                return new CategoryBOImpl();
            case MENU:
                return new MenuBOImpl();
            case RECIPE:
                return new RecipeBOImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBOImpl();
            case RECIPE_ING_DETAILS:
                return new RecipeIngDetailsBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes{
        EMPLOYEE , INGREDIENT , INVENTORY , CATEGORY , MENU , RECIPE , RECIPE_ING_DETAILS , PURCHASE_ORDER , SUPPLIER, USER
    }

}
