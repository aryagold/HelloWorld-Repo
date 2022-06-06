/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.co.vzap.Inventory.Repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Inventory.Model.Size;

/**
 *
 * @author macpe
 */
public class SizeRepositoryTest {
    
    private IRepository sizeRepository;
    
    @BeforeEach
    void SizeRepositoryTest() {
        this.sizeRepository = new SizeRepository();
    }

   
    @Test
    public void testAdd() {
       
        assertEquals(Integer.class, sizeRepository.add(new Size("TestSize")));
       
    }

   
    @Test
    public void testUpdate() {
        
        Size size =  new Size("TestSize");
        int ID = sizeRepository.add(size);
        
        size.Id = ID;
        size.setSize("UpdateTestSize");
        
        assertEquals(Boolean.class,sizeRepository.update(size));
        
    }

    
    @Test
    public void testGetById_int() {
        
        Size size =  new Size("TestSize");
        int ID = sizeRepository.add(size);
        
        size.Id = ID;
        
        assertEquals(Size.class, sizeRepository.getById(size.Id));
       
    }
}
