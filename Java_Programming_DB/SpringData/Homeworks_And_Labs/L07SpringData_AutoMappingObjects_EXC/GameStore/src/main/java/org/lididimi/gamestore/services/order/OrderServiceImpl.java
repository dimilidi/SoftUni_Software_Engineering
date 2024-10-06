package org.lididimi.gamestore.services.order;

import jakarta.transaction.Transactional;
import org.lididimi.gamestore.domain.entities.Game;
import org.lididimi.gamestore.domain.entities.Order;
import org.lididimi.gamestore.domain.entities.User;
import org.lididimi.gamestore.repositories.OrderRepository;
import org.lididimi.gamestore.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void createOrder(User user, Set<Game> shoppingCart) {

       final Order order = new Order(user, shoppingCart);

       orderRepository.saveAndFlush(order);
    }
}