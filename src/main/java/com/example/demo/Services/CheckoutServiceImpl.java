package com.example.demo.Services;

import com.example.demo.Dao.CartItemRepository;
import com.example.demo.Dao.CartRepository;
import com.example.demo.Dao.CustomerRepository;
import com.example.demo.Entities.Cart;
import com.example.demo.Entities.CartItem;
import com.example.demo.Entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

import static com.example.demo.Entities.Cart.StatusType.ordered;


@Service
public class CheckoutServiceImpl implements CheckoutService{
    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {

        this.customerRepository=customerRepository;
        this.cartItemRepository= cartItemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();
       cartItems.forEach(item -> {
           cart.add(item);
           item.setCart(cart);
           item.getExcursions().forEach(excursion -> {
               excursion.setVacation(item.getVacation());
               excursion.getCartitems().add(item);
           });
       });


        cart.setStatus(ordered);
        cartRepository.save(cart);
       return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {
        //random numb
        return UUID.randomUUID().toString();
    }


}
