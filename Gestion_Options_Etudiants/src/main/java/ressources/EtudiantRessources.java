package ressources;

import entities.Etudiant;
import entities.EtudiantList;
import entities.Option;
import filter.Secured;
import metiers.EtudiantBusiness;
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
@Path("etudiants")
public class EtudiantRessources {

    public static EtudiantBusiness etudiantMetier = Metiers.ETUDIANTS;
    private static final OptionBusiness optionMetier = Metiers.OPTIONS;

    // 1) POST /etudiants -> 201 Created | 404 Not Found (option inexistante)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etudiant) {
        if (etudiant == null || etudiant.getIdentifiant() == null || etudiant.getOption() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (etudiantMetier.getEtudiantByIdentifiant(etudiant.getIdentifiant()) != null) {
            return Response.status(Response.Status.CONFLICT).build();   // identifiant déjà utilisé
        }
        if (etudiantMetier.addEtudiant(etudiant)) {
            // addEtudiant() remplace l'option reçue par l'option "officielle" de la liste
            return Response.status(Response.Status.CREATED).entity(etudiant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();      // l'option n'existe pas
    }

    // 2) GET /etudiants            -> tous les étudiants
    //    GET /etudiants?option=1   -> (bonus) étudiants d'une option
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiants(@QueryParam("option") Integer codeOption) {
        List<Etudiant> etudiants;
        if (codeOption != null) {
            Option option = new Option();
            option.setCodeOption(codeOption);
            etudiants = etudiantMetier.getEtudiantsByOption(option);
        } else {
            etudiants = etudiantMetier.getAllEtudiants();
        }
        if (etudiants.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(etudiants).build();
    }

    // 6) GET /etudiants/option?codeOption=1 -> XML (sans l'option dans chaque étudiant)
    //    Chemin littéral : prioritaire sur /{identifiant} pour JAX-RS
    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(@QueryParam("codeOption") int codeOption) {
        Option option = new Option();
        option.setCodeOption(codeOption);
        List<Etudiant> etudiants = etudiantMetier.getEtudiantsByOption(option);
        if (etudiants.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(new EtudiantList(etudiants)).build();
    }

    // 3) GET /etudiants/{identifiant} -> 200 | 404
    @GET
    @Path("/{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantById(@PathParam("identifiant") String identifiant) {
        Etudiant etudiant = etudiantMetier.getEtudiantByIdentifiant(identifiant);
        if (etudiant != null) {
            return Response.status(Response.Status.OK).entity(etudiant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // 5) PUT /etudiants/{identifiant} -> 200 | 404
    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(@PathParam("identifiant") String identifiant, Etudiant updatedEtudiant) {
        if (updatedEtudiant == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Etudiant existant = etudiantMetier.getEtudiantByIdentifiant(identifiant);
        if (existant == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        updatedEtudiant.setIdentifiant(identifiant);          // l'identifiant de l'URL fait foi
        if (updatedEtudiant.getOption() == null) {
            updatedEtudiant.setOption(existant.getOption());  // option non fournie : on garde l'ancienne
        } else {
            Option option = optionMetier.getOptionByCode(updatedEtudiant.getOption().getCodeOption());
            if (option == null) {
                return Response.status(Response.Status.NOT_FOUND).build();   // option inexistante
            }
            updatedEtudiant.setOption(option);
        }
        etudiantMetier.updateEtudiant(identifiant, updatedEtudiant);
        return Response.status(Response.Status.OK).entity(updatedEtudiant).build();
    }

    // 4) DELETE /etudiants/{identifiant} -> 200 | 404
    @DELETE
    @Path("/{identifiant}")
    public Response deleteEtudiant(@PathParam("identifiant") String identifiant) {
        if (etudiantMetier.deleteEtudiant(identifiant)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
