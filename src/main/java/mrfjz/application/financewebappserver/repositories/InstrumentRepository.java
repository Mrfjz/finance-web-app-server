package mrfjz.application.financewebappserver.repositories;

import mrfjz.application.financewebappserver.models.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface InstrumentRepository extends CrudRepository<Instrument, Long> {
    java.util.Optional<Instrument> findBySymbol(String symbol);
}
