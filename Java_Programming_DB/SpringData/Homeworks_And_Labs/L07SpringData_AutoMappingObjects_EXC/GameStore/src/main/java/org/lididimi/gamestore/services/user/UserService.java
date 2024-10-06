package org.lididimi.gamestore.services.user;


import org.lididimi.gamestore.domain.entities.User;

public interface UserService {

    String registerUser(String[] args);

    String loginUser(String[] userData);

    String logoutUser();

    User getLoggedInUser();

    String getUserOwnedGames();

    String addItemToShoppingCart(String[] data);

    String removeItemFromShoppingCart(String[] data);

    String buyItemsFromShoppingCart();
}