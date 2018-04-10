package Negocio.Feria;

import org.junit.Test;

import Integracion.Feria.DAOFeria;
import Negocio.IFDAO;

import static org.junit.Assert.*;

import java.util.Collection;

public class ASFeriaImpTest {
	@Test
	public void create() throws Exception {
		Tferia feria = new Tferia();
		System.out.println("Test Create Feria");
		DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
		
		assertTrue(daoFeria.create(feria) > 0);
	}
	@Test
	public void drop() throws Exception {
		Tferia feria = new Tferia();
		Boolean deleted = false;
		System.out.println("Test drop Feria");
		DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
		deleted = daoFeria.delete(feria.getId());
		
		assertTrue(deleted == true);
	}

	@Test
	public void modify() throws Exception {
		Tferia feria = new Tferia();
		Integer id = -1;
		System.out.println("Test Modify Feria");
		DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
		id = daoFeria.update(feria);
		
		assertTrue(id != -1);
	}

	@Test
	public void list() throws Exception {
		System.out.println("Test list Feria");
		DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
		Collection<Tferia> collectionFeria = daoFeria.readAll();
		assertTrue(collectionFeria != null); //Si se cumple assertTrue el test saldrá correcto
	}

	@Test
	public void show() throws Exception {
		Tferia feria = new Tferia();
		System.out.println("Test show Feria");
		DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
		Tferia read = daoFeria.readByName(feria.getName());
		assertTrue(read != null);
	}

}