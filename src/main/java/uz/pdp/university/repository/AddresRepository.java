package uz.pdp.university.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.university.entity.Address;

public interface AddresRepository extends JpaRepository<Address  ,Integer> {
}
