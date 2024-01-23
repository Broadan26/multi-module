package org.brandon.api.util;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.core.Response;

import static org.brandon.core.constants.ResponseConstants.*;

public class ResponseCreation {

    private ResponseCreation() {
        throw new UnsupportedOperationException("This class cannot be constructed.");
    }

    /**
     * Creates a response with a success status and an Advent of Code answer
     * @param answer The answer to the current Advent of Code problem
     * @return A response containing the fields of response and answer
     */
    public static Response buildAdventSuccessResponse(long answer) {
        return Response.ok()
                .entity(new JsonObject()
                        .put(RESPONSE, SUCCESS)
                        .put(ANSWER, answer))
                .build();
    }

    /**
     * Creates a response with a failure message and a response of failure
     * @param errorMessage The error message to include in the JSON response
     * @return A response containing the fields of response and message
     */
    public static Response buildAdventFailureResponse(String errorMessage) {
        return Response.serverError()
                .entity(new JsonObject()
                        .put(RESPONSE, FAILURE)
                        .put(MESSAGE, errorMessage))
                .build();
    }

}
