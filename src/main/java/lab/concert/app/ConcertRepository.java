package lab.concert.app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ConcertRepository extends CrudRepository<Concert, Long> {
      
}
