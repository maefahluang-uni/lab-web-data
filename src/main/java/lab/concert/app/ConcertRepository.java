package lab.concert.app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lab.concert.app.domain.Concert;

public interface ConcertRepository extends CrudRepository<Concert, Long> {
      
}
