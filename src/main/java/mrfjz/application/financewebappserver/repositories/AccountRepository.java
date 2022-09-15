package mrfjz.application.financewebappserver.repositories;

import mrfjz.application.financewebappserver.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
