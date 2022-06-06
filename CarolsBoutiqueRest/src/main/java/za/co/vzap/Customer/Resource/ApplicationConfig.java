/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package za.co.vzap.Customer.Resource;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author aryagoldridge
 */
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
        resources.add(za.co.vzap.User.Resource.UserRestController.class);
    }
}
