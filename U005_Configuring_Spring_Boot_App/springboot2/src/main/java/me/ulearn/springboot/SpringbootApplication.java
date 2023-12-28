package me.ulearn.springboot;

import com.google.gson.Gson;
import me.ulearn.springboot.test.TestRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringbootApplication {
	public static void main(String[] args) {
		System.out.println(new Gson().toJson(TestRunner.run()));
	}
}
