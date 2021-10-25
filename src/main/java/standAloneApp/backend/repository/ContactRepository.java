package standAloneApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import standAloneApp.backend.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
}
