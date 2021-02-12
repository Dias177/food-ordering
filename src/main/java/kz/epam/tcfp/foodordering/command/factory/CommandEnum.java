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
    SHOW_MY_PROFILE(new ShowMyProfileCommand()),
    CHANGE_TO_ENGLISH_LOCALE(new ChangeToEnglishLocaleCommand()),
    CHANGE_TO_RUSSIAN_LOCALE(new ChangeToRussianLocaleCommand()),
    REMOVE_FOOD_FROM_CART(new RemoveFoodFromCartCommand()),
    EDIT_FOOD(new EditFoodCommand()),
    SHOW_EDITING_FOOD(new ShowEditingFoodCommand()),
    SHOW_ADDING_ORDER_STATUS(new ShowAddingOrderStatusCommand()),
    ADD_ORDER_STATUS(new AddOrderStatusCommand()),
    EDIT_PROFILE(new EditProfileCommand()),
    EDIT_PASSWORD(new EditPasswordCommand()),
    SHOW_EDITING_PROFILE(new ShowEditingProfileCommand()),
    SHOW_EDITING_PASSWORD(new ShowEditingPasswordCommand()),
    SORT_ALL_ORDERS(new SortAllOrdersCommand()),
    SORT_MENU_ITEMS(new SortMenuItemsCommand()),
    SHOW_MENU_ITEMS_BY_CATEGORY(new ShowMenuItemsByCategoryCommand()),
    DELETE_FOOD(new DeleteFoodCommand()),
    SHOW_DELETE_FOOD_CATEGORY(new ShowDeleteFoodCategoryCommand()),
    DELETE_FOOD_CATEGORY(new DeleteFoodCategoryCommand()),
    SHOW_DELETE_ORDER_STATUS(new ShowDeleteOrderStatusCommand()),
    DELETE_ORDER_STATUS(new DeleteOrderStatusCommand()),
    DELETE_ORDER(new DeleteOrderCommand()),
    SHOW_DELETE_USER(new ShowDeleteUserCommand()),
    DELETE_USER(new DeleteUserCommand());

    private ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }

    CommandEnum(ActionCommand command) {
        this.command = command;
    }
}
