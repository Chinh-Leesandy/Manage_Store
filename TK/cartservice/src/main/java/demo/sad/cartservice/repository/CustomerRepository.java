package demo.sad.cartservice.repository;

import demo.sad.cartservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>{

    Customer findByUsernameAndPassword(String username, String password);

}
