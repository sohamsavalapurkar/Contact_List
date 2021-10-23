package standAloneApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import standAloneApp.backend.entity.Address;

public interface AddressRepository extends CrudRepository<Address, String> {
}
