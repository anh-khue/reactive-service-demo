package anhkhue.io.reactiveroutefunctiondemo.functions;

import anhkhue.io.reactiveroutefunctiondemo.service.TaskReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
public class TaskReminderHandlerFunctions {

    private final TaskReminderService taskReminderService;

    public Mono<ServerResponse> streamTaskReminders(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");

        return ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(taskReminderService.streamTaskReminders(id), String.class);
    }
}
