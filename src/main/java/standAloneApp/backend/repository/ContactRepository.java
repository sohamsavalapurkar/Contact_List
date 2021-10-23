package standAloneApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import standAloneApp.backend.entity.Contact;

public interface ContactRepository extends CrudRepository<Contact, String> {
}
