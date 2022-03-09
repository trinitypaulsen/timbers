package com.trinity;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class MyResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
      return "Jersey Jetty example.";
  }

  @Path("/{username}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Player hello(@PathParam("username") String name) {

      Player obj = new Player();
      obj.setPlayerNumber(0);
      obj.setLastName(name);

      return obj;

  }

  @Path("/all")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Player> helloList() {

      List<Player> list = new ArrayList<>();

      Player obj1 = new Player();
      obj1.setPlayerNumber(1);
      obj1.setLastName("mkyong");
      list.add(obj1);

      Player obj2 = new Player();
      obj2.setPlayerNumber(2);
      obj2.setLastName("zilap");
      list.add(obj2);

      return list;

  }

}
