package rs.jz.calculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.jz.calculator.model.Element;

public interface ElementRepository extends JpaRepository<Element, Long> {
    Element findByName(String name);
}
