package kz.epam.tcfp.foodordering.command.factory;

import kz.epam.tcfp.foodordering.command.*;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    ADD_FOOD_CATEGORY(new AddFoodCategoryCommand()),
    ADD_FOOD(new AddFoodCommand()),
    SHOW_REGISTRATION(new ShowRegistrationCommand()),
    SHOW_MENU(new ShowMenuCommand()),
    SHOW_ADDING_FOOD(new ShowAddingFoodCommand()),
    SHOW_ADDING_FOOD_CATEGORY(new ShowAddingFoodCategoryCommand()),
    SHOW_FOOD_DETAIL(new ShowFoodDetailCommand()),
    ADD_FOOD_TO_CART(new AddFoodToCartCommand()),
    SHOW_CART(new ShowCartCommand()),
    SHOW_MAIN(new ShowMainCommand()),
    SHOW_LOGIN(new ShowLoginCommand()),
    ORDER_FOOD(new OrderFoodCommand()),
    SHOW_MY_ORDERS(new ShowMyOrdersCommand()),
    SHOW_ALL_ORDERS(new ShowAllOrdersCommand()),
    EDIT_ORDER_STATUS(new EditOrderStatusCommand()),
    SHOW_MY_PROFILE(new ShowMyProfileCommand());

    private ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }

    CommandEnum(ActionCommand command) {
        this.command = command;
    }
}
