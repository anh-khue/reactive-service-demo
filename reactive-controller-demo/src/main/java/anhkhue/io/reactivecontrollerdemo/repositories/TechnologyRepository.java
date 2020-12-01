package anhkhue.io.reactivecontrollerdemo.repositories;

import anhkhue.io.reactivecontrollerdemo.model.Technology;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TechnologyRepository extends ReactiveCrudRepository<Technology, String> {

}
