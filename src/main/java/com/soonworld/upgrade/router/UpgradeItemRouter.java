package com.soonworld.upgrade.router;

import com.soonworld.upgrade.handler.UpgradeItemHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class UpgradeItemRouter {
    @Bean
    public RouterFunction<ServerResponse> route(UpgradeItemHandler handler) {
        return RouterFunctions.route()
                .POST("/upgrade",accept(MediaType.APPLICATION_JSON),handler::upgradeItem)
                .build();
    }
}
