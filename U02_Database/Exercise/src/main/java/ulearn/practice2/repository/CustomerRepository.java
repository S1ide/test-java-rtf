package ulearn.practice2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulearn.practice2.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
