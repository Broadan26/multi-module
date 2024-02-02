package org.brandon.api.advent2022;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brandon.api.util.ResponseCreation;
import org.brandon.services.advent2022.Day6Service;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/v1/advent2022/day6")
public class Day6Controller {

    private final Day6Service day6Service;
    private static final Logger LOG = LoggerFactory.getLogger(Day6Controller.class);

    /**
     * Builds the Day6 Controller and injects necessary beans.
     * @param day6Service The injected service related to the Day6 Controller
     */
    public Day6Controller(Day6Service day6Service) {
        this.day6Service = day6Service;
    }

    /**
     * Solves the problem file provided for day 6 part 1 of Advent of Code 2022
     * @param file The problem file
     * @return A response containing the status of the request and the result
     */
    @POST
    @Path("part1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response part1Endpoint(@RestForm("file") FileUpload file) {
        try {
            int smallWindow = 4;
            long day6Answer = day6Service.solve(file.uploadedFile().toFile(), smallWindow);
            return ResponseCreation.buildAdventSuccessResponse(day6Answer);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return ResponseCreation.buildAdventFailureResponse(ex.getMessage());
        }
    }

    /**
     * Solves the problem file provided for day 6 part 2 of Advent of Code 2022
     * @param file The problem file
     * @return A response containing the status of the request and the result
     */
    @POST
    @Path("part2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response part2Endpoint(@RestForm("file") FileUpload file) {
        try {
            int bigWindow = 14;
            long day6Answer = day6Service.solve(file.uploadedFile().toFile(), bigWindow);
            return ResponseCreation.buildAdventSuccessResponse(day6Answer);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return ResponseCreation.buildAdventFailureResponse(ex.getMessage());
        }
    }

}
