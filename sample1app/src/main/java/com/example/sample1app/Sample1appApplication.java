package com.example.sample1app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
@RestController
public class Sample1appApplication {

	String[][] data = {
		{"noname", "no email address", "0"},
		{"taro", "taro@gmail.com", "1"},
		{"sakana", "sakana@ai.com", "2"},
		{"tomato", "no email address", "3"}
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Sample1appApplication.class, args);
	}

	@RequestMapping("/{num}")
	public String index(@PathVariable int num) {
		int n = num < 0 ? 0 : num >= data.length ? 0 :num;
		String s = "Name:" + data[num][0] + ", E-mail add:" + data[num][1];
		return s;
	}

}
