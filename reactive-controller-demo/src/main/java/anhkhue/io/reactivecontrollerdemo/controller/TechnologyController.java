package anhkhue.io.reactivecontrollerdemo.controller;

import anhkhue.io.reactivecontrollerdemo.model.Technology;
import anhkhue.io.reactivecontrollerdemo.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;
import static reactor.core.publisher.Mono.just;

@RequiredArgsConstructor
@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    @GetMapping
    public Mono<ResponseEntity<?>> getAllTechnologies() {
        return just(technologyService.getAllTechnologies())
                .map(technologies -> status(OK)
                        .body(technologies));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<?>> getTechnologyById(@PathVariable String id) {
        return technologyService.getTechnologyById(id)
                                .map(Technology -> status(OK)
                                        .body(Technology));
    }

    @PostMapping
    public Mono<ResponseEntity<?>> createTechnology(Technology newTechnology) {
        return technologyService.createTechnology(newTechnology)
                                .map(createdTechnology -> status(CREATED)
                                        .body("New technology created with ID " + createdTechnology.getId()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deleteTechnologyById(@PathVariable String id) {
        return technologyService.deleteTechnologyById(id)
                                .map(unused -> status(OK)
                                        .body("Technology with ID " + id + " has been removed"));
    }
}
