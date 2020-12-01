package anhkhue.io.reactiveroutefunctiondemo.functions;

import anhkhue.io.reactiveroutefunctiondemo.model.Technology;
import anhkhue.io.reactiveroutefunctiondemo.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
public class TechnologyHandlerFunctions {

    private final TechnologyService technologyService;

    public Mono<ServerResponse> getAllTechnologies(ServerRequest serverRequest) {
        return ok()
                .body(technologyService.getAllTechnologies(), Technology.class);
    }

    public Mono<ServerResponse> getTechnologyById(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");

        return ok()
                .body(technologyService.getTechnologyById(id), Technology.class);
    }

}
