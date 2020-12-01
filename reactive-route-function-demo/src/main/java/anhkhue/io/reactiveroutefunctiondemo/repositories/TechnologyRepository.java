package anhkhue.io.reactiveroutefunctiondemo.repositories;

import anhkhue.io.reactiveroutefunctiondemo.model.Technology;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TechnologyRepository extends ReactiveCrudRepository<Technology, String> {

}
