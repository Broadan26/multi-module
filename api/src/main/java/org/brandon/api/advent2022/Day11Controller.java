package org.brandon.api.advent2022;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brandon.api.util.ResponseCreation;
import org.brandon.services.advent2022.Day11Service;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/v1/advent2022/day11")
public class Day11Controller {

    private final Day11Service day11Service;
    private static final Logger LOG = LoggerFactory.getLogger(Day11Controller.class);

    /**
     * Builds the Day11 Controller and injects necessary beans.
     * @param day11Service The injected service related to the Day11 Controller
     */
    public Day11Controller(Day11Service day11Service) {
        this.day11Service = day11Service;
    }

    /**
     * Solves the problem file provided for day 11 part 1 of Advent of Code 2022
     * @param file The problem file
     * @return A response containing the status of the request and the result
     */
    @POST
    @Path("part1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response part1Endpoint(@RestForm("file") FileUpload file) {
        try {
            long day11Answer = day11Service.part1Solve(file.uploadedFile().toFile());
            return ResponseCreation.buildAdventSuccessResponse(day11Answer);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return ResponseCreation.buildAdventFailureResponse(ex.getMessage());
        }
    }

    /**
     * Solves the problem file provided for day 11 part 2 of Advent of Code 2022
     * @param file The problem file
     * @return A response containing the status of the request and the result
     */
    @POST
    @Path("part2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response part2Endpoint(@RestForm("file") FileUpload file) {
        try {
            long day11Answer = day11Service.part2Solve(file.uploadedFile().toFile());
            return ResponseCreation.buildAdventSuccessResponse(day11Answer);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return ResponseCreation.buildAdventFailureResponse(ex.getMessage());
        }
    }

}
