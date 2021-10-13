package com.educative.ecommerce.service;

import com.educative.ecommerce.dto.cart.AddToCartDto;
import com.educative.ecommerce.dto.cart.CartDto;
import com.educative.ecommerce.dto.cart.CartItemDto;
import com.educative.ecommerce.exceptions.CartItemNotExistException;
import com.educative.ecommerce.model.Cart;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        // first get all the cart items for user
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        // convert cart to cartItemDto
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }

        // calculate the total price
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity();
        }

        // return cart DTO
        return new CartDto(cartItems,totalCost);
    }

    public void deleteCartItem(int cartItemId, User user) throws CartItemNotExistException {
        //TODO

        // first check if cartItemId is valid else throw an CartItemNotExistException

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (!optionalCart.isPresent()) {
            throw new CartItemNotExistException("cartItemId not valid");
        }

        // next check if the cartItem belongs to the user else throw CartItemNotExistException exception

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw new CartItemNotExistException("cart item does not belong to user");
        }

        cartRepository.deleteById(cartItemId);
        // delete the cart item
    }
}
