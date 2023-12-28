package me.ulearn.springboot;

import com.google.gson.Gson;
import me.ulearn.springboot.test.TestRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {
    public static void main(String[] args) {
        System.out.print(new Gson().toJson(TestRunner.run()));
    }
}