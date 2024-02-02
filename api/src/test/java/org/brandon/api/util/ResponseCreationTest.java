package org.brandon.api.util;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.core.Response;
import org.brandon.core.constants.ResponseConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class ResponseCreationTest {

    @Test
    void testBuildAdventSuccessResponseLong() {
        long answer = 123L;

        try (Response testResponse = ResponseCreation.buildAdventSuccessResponse(answer)) {
            assertEquals(200, testResponse.getStatus());
            assertTrue(testResponse.hasEntity());
            JsonObject jsonMap = (JsonObject) testResponse.getEntity();
            assertEquals(ResponseConstants.SUCCESS, jsonMap.getString(ResponseConstants.RESPONSE));
            assertEquals(answer, jsonMap.getLong(ResponseConstants.ANSWER));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AssertionError("Build Advent Success Response Test has failed");
        }
    }

    @Test
    void testBuildAdventSuccessResponseString() {
        String answer = "Hello World!";

        try (Response testResponse = ResponseCreation.buildAdventSuccessResponse(answer)) {
            assertEquals(200, testResponse.getStatus());
            assertTrue(testResponse.hasEntity());
            JsonObject jsonMap = (JsonObject) testResponse.getEntity();
            assertEquals(ResponseConstants.SUCCESS, jsonMap.getString(ResponseConstants.RESPONSE));
            assertEquals(answer, jsonMap.getString(ResponseConstants.ANSWER));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AssertionError("Build Advent Success Response Test has failed");
        }
    }

    @Test
    void testBuildAdventFailureResponse() {
        String errorMessage = "Test error message";

        try (Response testResponse = ResponseCreation.buildAdventFailureResponse(errorMessage)) {
            assertEquals(500, testResponse.getStatus());
            assertTrue(testResponse.hasEntity());
            JsonObject jsonMap = (JsonObject) testResponse.getEntity();
            assertEquals(ResponseConstants.FAILURE, jsonMap.getString(ResponseConstants.RESPONSE));
            assertEquals(errorMessage, jsonMap.getString(ResponseConstants.MESSAGE));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AssertionError("Build Advent Failure Response Test has failed");
        }
    }

}
