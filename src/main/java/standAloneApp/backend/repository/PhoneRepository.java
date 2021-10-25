package standAloneApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import standAloneApp.backend.entity.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, String> {
}
