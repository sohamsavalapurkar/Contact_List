package standAloneApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import standAloneApp.backend.entity.Date;

@Repository
public interface DateRepository extends CrudRepository<Date, String> {
}
