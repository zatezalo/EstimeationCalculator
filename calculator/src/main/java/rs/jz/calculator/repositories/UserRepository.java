package rs.jz.calculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.jz.calculator.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String username);
}
