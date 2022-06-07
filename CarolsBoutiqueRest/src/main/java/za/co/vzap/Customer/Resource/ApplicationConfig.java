package za.co.vzap.Customer.Resource;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(za.co.vzap.Customer.Resource.CustomerRestController.class);
        resources.add(za.co.vzap.Inventory.Resource.InventoryRestController.class);
        resources.add(za.co.vzap.POS.Resource.POSRestController.class);
        resources.add(za.co.vzap.Report.Resource.ReportRestController.class);
        resources.add(za.co.vzap.User.Resource.UserRestController.class);
    }
}
