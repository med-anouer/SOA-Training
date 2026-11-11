package utilities;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Active JAX-RS : toutes les ressources sont exposées sous /rest
 * Exemple : http://localhost:8080/Gestion_Options_Etudiants_war_exploded/rest/options
 */
@ApplicationPath("rest")
public class RestActivator extends Application {
}
