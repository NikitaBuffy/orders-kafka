package ru.pominov.processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pominov.processing.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
