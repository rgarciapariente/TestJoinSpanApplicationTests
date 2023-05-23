package com.example.TestJoinSpan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureObservability
@SpringBootTest
class TestJoinSpanApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	void textJoinSpan() throws Exception {
		mockMvc.perform(get("/hello")
						.header("b3","646ce89e9fa17f1ed7e8b40fdaad443b-d7e8b40fdaad443b"))
				.andExpect(status().isOk())
				.andExpect(content().json("""
      {"traceId":"646ce89e9fa17f1ed7e8b40fdaad443b","spanId":"d7e8b40fdaad443b","parentSpanId":null}
						"""));
	}

}

