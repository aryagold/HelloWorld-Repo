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

    /**
     * Test of add method, of class SizeRepository.
     */
    @Test
    public void testAdd() {
       
        assertEquals(true, sizeRepository.add(new Size("TestSize")));
       
    }

    /**
     * Test of update method, of class SizeRepository.
     */
    @Test
    public void testUpdate() {
        
        Size size =  new Size("TestSize");
        sizeRepository.add(size);
        size.setSize("UpdateTestSize");
        
        assertEquals(true,sizeRepository.update(size));
        
    }

    /**
     * Test of getById method, of class SizeRepository.
     */
    @Test
    public void testGetById_int() {
        
        Size size =  new Size("TestSize");
        sizeRepository.add(size);
        
        assertEquals(Size.class, sizeRepository.getById(size.Id));
       
    }

    /**
     * Test of getById method, of class SizeRepository.
     */
    @Test
    public void testGetById_String() {
        throw new UnsupportedOperationException("This method cannot be tested yet");
    }
    
}
