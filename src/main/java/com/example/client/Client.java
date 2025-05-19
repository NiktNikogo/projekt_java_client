package com.example.client;

import com.example.client.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class Client {

	private RestTemplate restTemplate;

	@Autowired
	public Client(RestTemplateBuilder builder) {
		restTemplate = builder.rootUri("http://localhost:8080/stats/").build();
	}

	public List<UserDto> getMostActive() {
		try {
			var mostActive = restTemplate.getForEntity("/most-active?limit=100", UserDto[].class);
			return Arrays.asList(Objects.requireNonNull(mostActive.getBody()));
		} catch (Exception e) {
			return List.of();
		}
	}

}
