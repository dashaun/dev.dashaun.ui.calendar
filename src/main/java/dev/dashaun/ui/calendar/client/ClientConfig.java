package dev.dashaun.ui.calendar.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
public class ClientConfig {

	private final ConfigProps configProps;

	public ClientConfig(ConfigProps configProps) {
		this.configProps = configProps;
	}

	@Bean
	public HttpServiceProxyFactory httpServiceProxyFactory() {
		HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofSeconds(30));

		WebClient client = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient))
				.baseUrl(configProps.url()).defaultHeader("Content-Type", "application/json").build();
		return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
	}

	@Bean
	public CalendarClient foreFrontClient(HttpServiceProxyFactory factory) {
		return factory.createClient(CalendarClient.class);
	}

}
