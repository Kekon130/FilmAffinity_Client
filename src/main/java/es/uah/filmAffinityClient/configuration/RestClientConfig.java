package es.uah.filmAffinityClient.configuration;

import es.uah.filmAffinityClient.client.IActorClient;
import es.uah.filmAffinityClient.client.IGeneroClient;
import es.uah.filmAffinityClient.client.IPeliculaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
    @Value("${backend.api.url}")
    private String baseUrl;

    @Bean
    public RestClient restClient() {
        return RestClient.builder().baseUrl(baseUrl).build();
    }

    @Bean
    public IActorClient actorRestClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(IActorClient.class);
    }

    @Bean
    public IGeneroClient generoRestClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(IGeneroClient.class);
    }

    @Bean
    public IPeliculaClient peliculaRestClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(IPeliculaClient.class);
    }
}
