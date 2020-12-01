package anhkhue.io.reactiveroutefunctiondemo.service;

import anhkhue.io.reactiveroutefunctiondemo.model.TaskReminder;
import anhkhue.io.reactiveroutefunctiondemo.repositories.TaskReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TaskReminderService {

    private final TaskReminderRepository taskReminderRepository;

    public Flux<String> streamTaskReminders(String id) {
        var taskReminderMono = taskReminderRepository.findById(id);

        return taskReminderMono
                .map(taskReminderToStringFlux)
                .flux()
                .flatMap(stringFlux -> stringFlux);
    }

    private final Function<TaskReminder, Flux<String>> taskReminderToStringFlux = taskReminder -> {
        Duration remindDuration = Duration.ofSeconds(taskReminder.getRemindDuration());
        String task = taskReminder.getTask();

        return Flux.interval(remindDuration)
                   .map(sequence -> "Please " + task + " - " + LocalTime.now().toString());
    };

}

