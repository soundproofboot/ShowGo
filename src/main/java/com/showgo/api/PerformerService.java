package com.showgo.api;

import com.showgo.dto.PerformerDto;
import com.showgo.entity.Performer;
import com.showgo.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Performer endpoints
 */
@Path("/performers")
@Produces(MediaType.APPLICATION_JSON)
public class PerformerService {

    /**
     * Returns list of performers with name "like" name passed, for creating event and adding performers to lineup
     * @param name performer name
     * @return list of performers
     */
    @GET
    public Response getPerformers(@QueryParam("name") String name) {
        GenericDao<Performer> dao = new GenericDao<>(Performer.class);
        List<Performer> performers = dao.getByPropertyLike("name", name);
        List<PerformerDto> performerDtos = performers.stream()
                .map(p -> new PerformerDto(p.getId(), p.getName()))
                .collect(Collectors.toList());
        return Response.status(200).entity(performerDtos).build();
    }
}
