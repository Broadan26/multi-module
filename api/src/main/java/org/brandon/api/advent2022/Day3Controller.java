package org.brandon.api.advent2022;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brandon.api.util.ResponseCreation;
import org.brandon.services.advent2022.Day3Service;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/v1/advent2022/day3")
public class Day3Controller {

    private final Day3Service day3Service;
    private static final Logger LOG = LoggerFactory.getLogger(Day3Controller.class);

    /**
     * Builds the Day3 Controller and injects necessary beans.
     * @param day3Service The injected service related to the Day3 Controller
     */
    public Day3Controller(Day3Service day3Service) {
        this.day3Service = day3Service;
    }

    /**
     * Solves the problem file provided for day 3 part 1 of Advent of Code 2022
     * @param file The problem file
     * @return A response containing the status of the request and the result
     */
    @POST
    @Path("part1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response part1Endpoint(@RestForm("file") FileUpload file) {
        try {
            long day2Answer = day3Service.part1Solve(file.uploadedFile().toFile());
            return ResponseCreation.buildAdventSuccessResponse(day2Answer);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return ResponseCreation.buildAdventFailureResponse(ex.getMessage());
        }
    }

    /**
     * Solves the problem file provided for day 3 part 2 of Advent of Code 2022
     * @param file The problem file
     * @return A response containing the status of the request and the result
     */
    @POST
    @Path("part2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response part2Endpoint(@RestForm("file") FileUpload file) {
        try {
            long day2Answer = day3Service.part2Solve(file.uploadedFile().toFile());
            return ResponseCreation.buildAdventSuccessResponse(day2Answer);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return ResponseCreation.buildAdventFailureResponse(ex.getMessage());
        }
    }

}
