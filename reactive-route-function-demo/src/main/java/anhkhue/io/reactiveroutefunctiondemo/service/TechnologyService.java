package anhkhue.io.reactiveroutefunctiondemo.service;

import anhkhue.io.reactiveroutefunctiondemo.model.Technology;
import anhkhue.io.reactiveroutefunctiondemo.repositories.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public Flux<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    public Mono<Technology> getTechnologyById(String id) {
        return technologyRepository.findById(id);
    }

    public Mono<Technology> createTechnology(Technology newTechnology) {
        return technologyRepository.save(newTechnology);
    }

    public Mono<Void> deleteTechnologyById(String id) {
        return technologyRepository.deleteById(id);
    }
}

