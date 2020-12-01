package anhkhue.io.reactiveroutefunctiondemo.routes;

import anhkhue.io.reactiveroutefunctiondemo.functions.TaskReminderHandlerFunctions;
import anhkhue.io.reactiveroutefunctiondemo.functions.TechnologyHandlerFunctions;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class ApplicationRouter {

    private final TechnologyHandlerFunctions technologyHandlerFunctions;
    private final TaskReminderHandlerFunctions taskReminderHandlerFunctions;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route()
                .path("/technologies", builder -> builder
                        .GET(technologyHandlerFunctions::getAllTechnologies)
                        .GET("/{id}", technologyHandlerFunctions::getTechnologyById)
                     )
                .path("/task-reminders", builder -> builder
                        .GET("/{id}/sse", taskReminderHandlerFunctions::streamTaskReminders)
                     )
                .build();
    }
}
