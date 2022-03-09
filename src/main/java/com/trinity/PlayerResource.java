package com.trinity;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Path("/players")
public class PlayerResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlayer(Player p) {
        Dynamo.getINSTANCE().savePlayer(p);
        return Response.status(201).entity(p).build();
    }

    @Path("/{last}/{first}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Player getPlayer(@PathParam("last") String last, @PathParam("first") String first) {
        return Dynamo.getINSTANCE().getPlayer(last, first);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAllPlayers() {
        return Dynamo.getINSTANCE().getAllPlayers();
    }
}
