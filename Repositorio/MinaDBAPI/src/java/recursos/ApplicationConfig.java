/**
 * AplicationConfig.java
 * 
 * Creado el 20/04/2020 a las 08:54PM
 */
package recursos;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * 
 * @author Equipo Mineria.
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(recursos.REST_Congestion.class);
        resources.add(recursos.REST_Materiales.class);
        resources.add(recursos.REST_Transporte.class);
    }
    
}
