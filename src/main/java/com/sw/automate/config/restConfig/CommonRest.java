package com.sw.automate.config.restConfig;

import com.sw.automate.annotation.ServiceMethod;
import com.sw.automate.service.LaunchTimedMouse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags = "Optimisation")
@Consumes(MediaType.APPLICATION_JSON)
@Service
@Path("/sw")
public class CommonRest {

    @Resource
    private LaunchTimedMouse launchTimedMouse;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRest.class);



    @GET
    @Path("/automate/journalier")
    @Produces(MediaType.APPLICATION_JSON)
    @ServiceMethod
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    public Response launchOptimizer() {

        launchTimedMouse.launchJourna();
        return Response.ok().build();

    }


    /**
     * Retourne des données
     *
     * @param object
     * @return
     */
    private Response ok(final Object object) {
        return Response.ok(object).build();
    }

    /**
     * Retourne des données
     *
     * @return
     */
    private Response ok() {
        return Response.ok().build();
    }

}
