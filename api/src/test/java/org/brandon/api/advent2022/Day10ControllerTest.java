package org.brandon.api.advent2022;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import org.brandon.core.constants.ResponseConstants;
import org.brandon.services.advent2022.Day10Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
class Day10ControllerTest {

    @InjectMock
    Day10Service day10Service;

    @Test
    void testPart1Endpoint() throws IOException {
        Long answer = 123L;
        Mockito.when(day10Service.part1Solve(Mockito.any()))
                .thenReturn(answer);

        given()
                .multiPart(new MultiPartSpecBuilder("Fake Content", ObjectMapperType.JACKSON_2)
                        .fileName("greeting.json")
                        .controlName("file")
                        .mimeType("application/vnd.custom+json").build())
                .when().post("/v1/advent2022/day10/part1")
                .then()
                .statusCode(200)
                .assertThat()
                .body(ResponseConstants.RESPONSE, equalTo(ResponseConstants.SUCCESS))
                .and()
                .body(ResponseConstants.ANSWER, equalTo(answer.intValue()));

        String errorMessage = "Test Case Endpoint Failure";
        Mockito.when(day10Service.part1Solve(Mockito.any()))
                .thenThrow(new IOException(errorMessage));

        given()
                .multiPart(new MultiPartSpecBuilder("Fake Content", ObjectMapperType.JACKSON_2)
                        .fileName("greeting.json")
                        .controlName("file")
                        .mimeType("application/vnd.custom+json").build())
                .when().post("/v1/advent2022/day10/part1")
                .then()
                .statusCode(500)
                .assertThat()
                .body(ResponseConstants.RESPONSE, equalTo(ResponseConstants.FAILURE))
                .and()
                .body(ResponseConstants.MESSAGE, equalTo(errorMessage));
    }

    @Test
    void testPart2Endpoint() throws IOException {
        String answer = "321";
        Mockito.when(day10Service.part2Solve(Mockito.any()))
                .thenReturn(answer);

        given()
                .multiPart(new MultiPartSpecBuilder("Fake Content", ObjectMapperType.JACKSON_2)
                        .fileName("greeting.json")
                        .controlName("file")
                        .mimeType("application/vnd.custom+json").build())
                .when().post("/v1/advent2022/day10/part2")
                .then()
                .statusCode(200)
                .assertThat()
                .body(ResponseConstants.RESPONSE, equalTo(ResponseConstants.SUCCESS))
                .and()
                .body(ResponseConstants.ANSWER, equalTo(answer));

        String errorMessage = "Test Case Endpoint Failure";
        Mockito.when(day10Service.part2Solve(Mockito.any()))
                .thenThrow(new IOException(errorMessage));

        given()
                .multiPart(new MultiPartSpecBuilder("Fake Content", ObjectMapperType.JACKSON_2)
                        .fileName("greeting.json")
                        .controlName("file")
                        .mimeType("application/vnd.custom+json").build())
                .when().post("/v1/advent2022/day10/part2")
                .then()
                .statusCode(500)
                .assertThat()
                .body(ResponseConstants.RESPONSE, equalTo(ResponseConstants.FAILURE))
                .and()
                .body(ResponseConstants.MESSAGE, equalTo(errorMessage));
    }

}
