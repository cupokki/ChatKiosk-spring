package dev.cupokki.ChatKiosk.repository;

import dev.cupokki.ChatKiosk.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
