package com.soonworld.upgrade.router;

import com.soonworld.upgrade.handler.UpgradeItemHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class UpgradeItemRouter {
    @Bean
    public RouterFunction<ServerResponse> route(UpgradeItemHandler handler) {
        return RouterFunctions.route()
                .POST("/upgrade",handler::upgradeItem)
                .build();
    }
}
