package Negocio.Feria;

import static org.junit.Assert.*;

import org.junit.Test;

public class TferiaTest {
    @Test
    public void getDescription() throws Exception {
    	Tferia feria = new Tferia();
    	assertTrue(feria.getDescription() != null);
    }

    @Test
    public void getName() throws Exception {
    	Tferia feria = new Tferia();
    	assertTrue(feria.getName() != null);
    }
    @Test
    public void getiniDate() throws Exception {
    	Tferia feria = new Tferia();
    	assertTrue(feria.getIniDate() != null);
    }
    @Test
    public void getEndDate() throws Exception {
    	Tferia feria = new Tferia();
    	assertTrue(feria.getEndDate() != null);
    }
    @Test
    public void getActive() throws Exception {
    	Tferia feria = new Tferia();
    	assertTrue(feria.getActive() != null);
    }
    @Test
    public void getId() throws Exception {
    	Tferia feria = new Tferia();
    	assertTrue(feria.getId() != -1);
    }
    
}
