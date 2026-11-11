package graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import repository.EtudiantBusiness;
import repository.OptionBusiness;

import javax.servlet.annotation.WebServlet;

/**
 * Point d'entrée unique de l'API : POST http://localhost:8080/<nomProjet>/graphql
 *
 * @WebServlet déclare ce composant servlet (sans web.xml). urlPatterns = chemin d'accès.
 */
@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndPoint extends SimpleGraphQLServlet {

    public GraphQLEndPoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        OptionBusiness optionBusiness = new OptionBusiness();
        EtudiantBusiness etudiantBusiness = new EtudiantBusiness();

        return SchemaParser.newParser()
                .file("schema.graphqls")                                    // src/main/resources
                .resolvers(new Query(optionBusiness, etudiantBusiness),
                           new Mutations(optionBusiness, etudiantBusiness))
                .build()
                .makeExecutableSchema();
    }
}
