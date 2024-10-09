package com.example.demo.Dao;

import com.example.demo.Entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface CartItemRepository extends JpaRepository <CartItem, Long>
{
}
