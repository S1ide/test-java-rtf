package me.ulearn.springboot.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

//#region Task
@Data
@ConfigurationProperties(prefix = "film")
public class Film {
    private String name;
    private String genre;
    private Integer yearOfRelease;

    @Override
    public String toString() {
        return String.format("%s (%s, %d)", this.name, this.genre, this.yearOfRelease);
    }
}
//#endregion Task
