package mrfjz.application.financewebappserver.repositories;

import mrfjz.application.financewebappserver.models.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Long> {
}
