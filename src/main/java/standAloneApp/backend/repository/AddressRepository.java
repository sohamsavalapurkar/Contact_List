package standAloneApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import standAloneApp.backend.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
