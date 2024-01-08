package me.ulearn.springboot_module6;

import com.google.gson.Gson;
import me.ulearn.springboot_module6.test.TestRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootModule6Application {
	public static void main(String[] args) {
		System.out.println(new Gson().toJson(TestRunner.run()));
	}
}
