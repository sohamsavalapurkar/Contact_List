package standAloneApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import standAloneApp.backend.entity.Phone;

public interface PhoneRepository extends CrudRepository<Phone, String> {
}
