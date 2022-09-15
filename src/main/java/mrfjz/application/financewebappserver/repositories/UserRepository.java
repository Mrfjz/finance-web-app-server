package mrfjz.application.financewebappserver.repositories;

import mrfjz.application.financewebappserver.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
