package ressources;

import entities.Option;
import filter.Secured;
import metiers.OptionBusiness;
import utilities.Metiers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Secured                      // Atelier n°5 : toutes les méthodes exigent un access token valide
@Path("options")
public class OptionRessources {

    public static OptionBusiness optionMetier = Metiers.OPTIONS;

    // 1) POST /options -> 201 Created
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOption(Option option) {
        if (option == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (optionMetier.getOptionByCode(option.getCodeOption()) != null) {
            return Response.status(Response.Status.CONFLICT).build();   // code déjà utilisé
        }
        if (optionMetier.addOption(option)) {
            return Response.status(Response.Status.CREATED).entity(option).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    // 2) GET /options                      -> toutes les options
    // 3) GET /options?domaine=Mathématiques -> options d'un domaine
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptions(@QueryParam("domaine") String domaine) {
        List<Option> liste;
        if (domaine != null && !domaine.trim().isEmpty()) {
            liste = optionMetier.getOptionsByDomaine(domaine);
        } else {
            liste = optionMetier.getListeOptions();
        }
        if (liste.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(liste).build();
    }

    // 6) GET /options/{codeOption}
    @GET
    @Path("/{codeOption}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptionByCode(@PathParam("codeOption") int codeOption) {
        Option option = optionMetier.getOptionByCode(codeOption);
        if (option != null) {
            return Response.status(Response.Status.OK).entity(option).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // 5) PUT /options/{codeOption}
    @PUT
    @Path("/{codeOption}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam("codeOption") int codeOption, Option updatedOption) {
        if (updatedOption == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        // Le code de l'URL fait foi : on évite d'avoir deux options avec le même code
        updatedOption.setCodeOption(codeOption);
        if (optionMetier.updateOption(codeOption, updatedOption)) {
            return Response.status(Response.Status.OK).entity(updatedOption).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // 4) DELETE /options/{codeOption} -> 200 | 404
    @DELETE
    @Path("/{codeOption}")
    public Response deleteOption(@PathParam("codeOption") int codeOption) {
        if (optionMetier.deleteOption(codeOption)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
