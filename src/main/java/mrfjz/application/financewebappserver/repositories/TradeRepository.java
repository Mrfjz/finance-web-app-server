package mrfjz.application.financewebappserver.repositories;

import mrfjz.application.financewebappserver.models.Trade;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trade, Long> {
}
