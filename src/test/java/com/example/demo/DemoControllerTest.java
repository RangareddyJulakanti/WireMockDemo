package com.example.demo;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTest {
    @Autowired
    DemoController demoController;
    @Autowired
    DemoService demoService;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);
   @Test
    public void testFetchResponse() throws IOException {
       wireMockRule.stubFor(get(urlPathEqualTo("/some-test-api-key"))
               .willReturn(aResponse()
                       .withBody(FileLoader.read("classpath:demoResponse.json"))
                       .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                       .withStatus(200)));
       wireMockRule.stubFor(get(urlPathEqualTo("/fetchResponse"))
               .willReturn(aResponse()
                       .withBody("Arun")
                       .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                       .withStatus(200)));
       String expectedResponse=demoController.fetchResponse();
       Assert.assertEquals(expectedResponse,"Arun");

   }
}
