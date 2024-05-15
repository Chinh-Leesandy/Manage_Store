package demo.sad.discoveryservice.repository;

import demo.sad.discoveryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

    List<Product> findAllByNameContains(String keyword);
}
