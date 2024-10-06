package org.lididimi.gamestore.services.order;

import org.lididimi.gamestore.domain.entities.Game;
import org.lididimi.gamestore.domain.entities.User;

import java.util.Set;

public interface OrderService {

    void createOrder(User user, Set<Game> shoppingCart);
}