package com.example.TestJoinSpan;

import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.tracing.TracingProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TestJoinSpanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJoinSpanApplication.class, args);
	}



	@RestController
	static class TestJoinRestController {

		@Autowired
		Tracer tracer;

		@Autowired
		TracingProperties tracingProperties;

		@GetMapping(path = "/hello")
		public TraceData hello() {
			TraceContext traceContext = tracer.currentSpan().context();
			System.out.println(tracingProperties.getBrave().isSpanJoiningSupported());
			return new TraceData(traceContext.traceId(), traceContext.spanId(), traceContext.parentId());
		}

	}

	record TraceData(String traceId, String spanId, String parentSpanId) {}
}
