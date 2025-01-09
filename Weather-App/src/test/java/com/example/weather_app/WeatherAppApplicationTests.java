package com.example.weather_app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherAppApplicationIntegrationTests {

	@Autowired
	private MockMvc mockMvc; //szimuláló környezet, amivel a tesztet elvégzem

	@Test
	void shouldReturnIndexPage() throws Exception {
		// az API alap weboldalának elérésére szolgáló teszt
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	void shouldHandleWeatherEndpoint() throws Exception {
		// A végpont tesztje, szimulált bemenettel
		mockMvc.perform(get("/weather").param("city", "London"))
				.andExpect(status().isOk())
				.andExpect(view().name("weather"));
	}
}