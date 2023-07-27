package lk.ijse.cuisineCompass.dao;

import lk.ijse.cuisineCompass.dao.custom.impl.*;

public class DAOFactory {


    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case EMPLOYEE:
                return (SuperDAO) new EmployeeDAOImpl();
            case INGREDIENT:
                return new IngredientDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case CATEGORY:
                return new CategoryDAOImpl();
            case MENU:
                return new MenuDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case RECIPE:
                return new RecipeDAOImpl();
            case RECIPE_ING_DETAILS:
                return new RecipeIngDetailsDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();
            default:
                return null;

        }
    }

    public enum DAOTypes {
        //CUSTOMER, ITEM, ORDER, ORDER_DETAILS, QUERY_DAO

        EMPLOYEE , INGREDIENT , INVENTORY , CATEGORY, MENU, ORDER, ORDER_DETAILS, RECIPE, RECIPE_ING_DETAILS, SUPPLIER, USER, QUERY_DAO
    }

}
