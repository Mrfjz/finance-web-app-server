package mrfjz.application.financewebappserver.repositories;

import mrfjz.application.financewebappserver.models.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long> {
}
