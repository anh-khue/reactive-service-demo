package anhkhue.io.reactiveroutefunctiondemo;

import anhkhue.io.reactiveroutefunctiondemo.model.TaskReminder;
import anhkhue.io.reactiveroutefunctiondemo.model.Technology;
import anhkhue.io.reactiveroutefunctiondemo.repositories.TaskReminderRepository;
import anhkhue.io.reactiveroutefunctiondemo.repositories.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class ReactiveRouteFunctionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRouteFunctionDemoApplication.class, args);
    }

}


@Log4j2
@Component
@RequiredArgsConstructor
class SampleDataInitializer {

    private final TechnologyRepository technologyRepository;
    private final TaskReminderRepository taskReminderRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        initializeTechnologies();
        initializeTaskReminders();
    }

    private void initializeTechnologies() {
        var savedTechnologies = Flux.just("Java", "JavaScript", "Python",
                                          "Go", "Ruby", "PHP")
                                    .map(Technology::new)
                                    .flatMap(technologyRepository::save);

        technologyRepository.deleteAll()
                            .thenMany(savedTechnologies)
                            .thenMany(technologyRepository.findAll())
                            .subscribe(log::info);
    }

    private void initializeTaskReminders() {
        var taskFlux = Flux.just("Check Emails", "Create Report", "Reply Tickets");
        var durationFlux = Flux.just(1, 2, 3);

        var savedTaskReminder = Flux.zip(taskFlux, durationFlux, TaskReminder::new)
                                    .flatMap(taskReminderRepository::save);

        taskReminderRepository.deleteAll()
                              .thenMany(savedTaskReminder)
                              .thenMany(taskReminderRepository.findAll())
                              .subscribe(log::info);
    }
}
