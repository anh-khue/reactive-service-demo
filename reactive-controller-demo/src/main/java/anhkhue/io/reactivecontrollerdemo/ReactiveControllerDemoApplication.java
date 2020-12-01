package anhkhue.io.reactivecontrollerdemo;

import anhkhue.io.reactivecontrollerdemo.model.Technology;
import anhkhue.io.reactivecontrollerdemo.repositories.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@SpringBootApplication
public class ReactiveControllerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveControllerDemoApplication.class, args);
    }

}


@Component
@RequiredArgsConstructor
@Log4j2
class SampleDataInitializer {

    private final TechnologyRepository technologyRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        var savedTechnologies = Flux.just("Java", "JavaScript", "Python", "C#",
                                          "C", "Ruby", "PHP", "Go")
                                    .map(Technology::new)
                                    .flatMap(technologyRepository::save);

        technologyRepository.deleteAll()
                            .thenMany(savedTechnologies)
                            .thenMany(technologyRepository.findAll())
                            .subscribe(log::info);
    }
}
