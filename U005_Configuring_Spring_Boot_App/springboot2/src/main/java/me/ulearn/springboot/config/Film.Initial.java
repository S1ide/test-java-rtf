package me.ulearn.springboot.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

//#region Task
public class Film {
    private String name;
    private String genre;
    private Integer yearOfRelease;
}
//#endregion Task
