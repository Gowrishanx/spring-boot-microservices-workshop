package io.javabrains.moviecatalogservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class MovieCatalogServiceApplicationTests {

    static WireMockServer wireMock;
    @Autowired
    private MockMvc mockMvc;
    @Value("${downstream.service.url.gateway}")
    private String GATEWAY;
    @Value("${downstream.service.url.ratingsDataService}")
    private String RATINGS_DATA_SERVICE;
    @Value("${downstream.service.url.movieInfoService}")
    private String MOVIE_INFO_SERVICE;

    @BeforeAll
    static void setUp() {
        wireMock = new WireMockServer(options()
                .port(8071)
                .httpsPort(8001)
                .bindAddress("localhost")
        );
        wireMock.start();
    }

    @AfterAll
    static void afterAll() {
        wireMock.stop();
    }

    @Test
    public void shouldReturnSuccess() throws Exception {


        wireMock.stubFor(WireMock.get(urlEqualTo(RATINGS_DATA_SERVICE + "1"))
                .willReturn(aResponse()
                        .withBodyFile("ratings_data_service_response.json")
                        .withHeader("content-type", "application/json")
                        .withStatus(HttpStatus.SC_OK)));

        wireMock.stubFor(WireMock.get(urlEqualTo(MOVIE_INFO_SERVICE + "a"))
                .willReturn(aResponse()
                        .withBodyFile("movie_info_service_response.json")
                        .withHeader("content-type", "application/json")
                        .withStatus(HttpStatus.SC_OK)));


        wireMock.listAllStubMappings().getMappings().stream().forEach(m -> System.out.println(m));


        this.mockMvc.perform(get("/catalog/1")).andDo(print())
                .andExpect(status().isOk());
    }


}

