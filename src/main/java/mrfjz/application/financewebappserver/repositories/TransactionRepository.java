package mrfjz.application.financewebappserver.repositories;

import mrfjz.application.financewebappserver.models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
