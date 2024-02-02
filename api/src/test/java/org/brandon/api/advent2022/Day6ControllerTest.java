package org.brandon.api.advent2022;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import org.brandon.core.constants.ResponseConstants;
import org.brandon.services.advent2022.Day6Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
class Day6ControllerTest {

    @InjectMock
    Day6Service day6Service;

    @Test
    void testPart1Endpoint() throws IOException {
        Long answer = 123L;
        Mockito.when(day6Service.solve(Mockito.any(), Mockito.anyInt()))
                .thenReturn(answer);

        given()
                .multiPart(new MultiPartSpecBuilder("Fake Content", ObjectMapperType.JACKSON_2)
                        .fileName("greeting.json")
                        .controlName("file")
                        .mimeType("application/vnd.custom+json").build())
                .when().post("/v1/advent2022/day6/part1")
                .then()
                .statusCode(200)
                .assertThat()
                .body(ResponseConstants.RESPONSE, equalTo(ResponseConstants.SUCCESS))
                .and()
                .body(ResponseConstants.ANSWER, equalTo(answer.intValue()));

        String errorMessage = "Test Case Endpoint Failure";
        Mockito.when(day6Service.solve(Mockito.any(), Mockito.anyInt()))
                .thenThrow(new IOException(errorMessage));

        given()
                .multiPart(new MultiPartSpecBuilder("Fake Content", ObjectMapperType.JACKSON_2)
                        .fileName("greeting.json")
                        .controlName("file")
                        .mimeType("application/vnd.custom+json").build())
                .when().post("/v1/advent2022/day6/part1")
                .then()
                .statusCode(500)
                .assertThat()
                .body(ResponseConstants.RESPONSE, equalTo(ResponseConstants.FAILURE))
                .and()
                .body(ResponseConstants.MESSAGE, equalTo(errorMessage));
    }

    @Test
    void testPart2Endpoint() throws IOException {
        Long answer = 321L;
        Mockito.when(day6Service.solve(Mockito.any(), Mockito.anyInt()))
                .thenReturn(answer);

        given()
                .multiPart(new MultiPartSpecBuilder("Fake Content", ObjectMapperType.JACKSON_2)
                        .fileName("greeting.json")
                        .controlName("file")
                        .mimeType("application/vnd.custom+json").build())
                .when().post("/v1/advent2022/day6/part2")
                .then()
                .statusCode(200)
                .assertThat()
                .body(ResponseConstants.RESPONSE, equalTo(ResponseConstants.SUCCESS))
                .and()
                .body(ResponseConstants.ANSWER, equalTo(answer.intValue()));

        String errorMessage = "Test Case Endpoint Failure";
        Mockito.when(day6Service.solve(Mockito.any(), Mockito.anyInt()))
                .thenThrow(new IOException(errorMessage));

        given()
                .multiPart(new MultiPartSpecBuilder("Fake Content", ObjectMapperType.JACKSON_2)
                        .fileName("greeting.json")
                        .controlName("file")
                        .mimeType("application/vnd.custom+json").build())
                .when().post("/v1/advent2022/day6/part2")
                .then()
                .statusCode(500)
                .assertThat()
                .body(ResponseConstants.RESPONSE, equalTo(ResponseConstants.FAILURE))
                .and()
                .body(ResponseConstants.MESSAGE, equalTo(errorMessage));
    }

}
