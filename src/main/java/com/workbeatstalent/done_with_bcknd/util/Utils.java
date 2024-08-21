package com.workbeatstalent.done_with_bcknd.util;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public record Utils(Environment env) {

    public String getLocalFilePath() {
        return this.env.getProperty("file.local.path");
    }

}
