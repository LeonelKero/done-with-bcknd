package com.workbeatstalent.done_with_bcknd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppOpenAPIConfig {

    public OpenAPI postApi() {
        return new OpenAPI().info(
                new Info()
                        .title("Post API Specification")
                        .description("This specification shows the Post API operate under the wood")
                        .version("1.0")
        );
    }

}
