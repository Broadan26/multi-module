package org.brandon.api.advent2022;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brandon.api.util.ResponseCreation;
import org.brandon.services.advent2022.Day9Service;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/v1/advent2022/day9")
public class Day9Controller {

    private final Day9Service day9Service;
    private static final Logger LOG = LoggerFactory.getLogger(Day9Controller.class);

    /**
     * Builds the Day9 Controller and injects necessary beans.
     * @param day9Service The injected service related to the Day9 Controller
     */
    public Day9Controller(Day9Service day9Service) {
        this.day9Service = day9Service;
    }

    /**
     * Solves the problem file provided for day 9 part 1 of Advent of Code 2022
     * @param file The problem file
     * @return A response containing the status of the request and the result
     */
    @POST
    @Path("part1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response part1Endpoint(@RestForm("file") FileUpload file) {
        try {
            long day9Answer = day9Service.part1Solve(file.uploadedFile().toFile());
            return ResponseCreation.buildAdventSuccessResponse(day9Answer);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return ResponseCreation.buildAdventFailureResponse(ex.getMessage());
        }
    }

    /**
     * Solves the problem file provided for day 9 part 2 of Advent of Code 2022
     * @param file The problem file
     * @return A response containing the status of the request and the result
     */
    @POST
    @Path("part2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response part2Endpoint(@RestForm("file") FileUpload file) {
        try {
            long day9Answer = day9Service.part2Solve(file.uploadedFile().toFile());
            return ResponseCreation.buildAdventSuccessResponse(day9Answer);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return ResponseCreation.buildAdventFailureResponse(ex.getMessage());
        }
    }

}
