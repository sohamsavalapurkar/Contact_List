package standAloneApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import standAloneApp.backend.entity.Date;

public interface DateRepository extends CrudRepository<Date, String> {
}
