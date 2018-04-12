package Negocio.Feria;

import Exceptions.DAOException;
import org.junit.Test;
import Integracion.Feria.DAOFeria;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class ASFeriaImpTest {
	//HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	@Test
	//Metodo donde probamos que no se puede crear una feria ya existente en la bbdd
	public void createFeriaExistente() throws Exception, DAOException {
		int id = -1;
		System.out.println("Test Create Feria Existente"); //Probamos que la feria que intentamos crear existe ya en la bbdd

		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd

		Tferia feria = new Tferia(id, null, null, null, null, null); //Generamos un transfer
		daoFeria.create(feria); //Creamos una feria con los datos intoducidos en el transfer anterior

		assertTrue(daoFeria.create(feria) < 0); //Intentamos volver a crear una feria que ya existe en la bbdd y si nos devuelve -1
		//Es porque ya existia en la bbdd por lo tanto es correcto el test

	}
	public void main() throws DAOException, Exception{
		createFeriaExistente();
	}
	
	@Test
	//Metodo donde probamos que no se puede leer una feria que no existe en la bbdd
	public void leeFeriaInexistente() throws Exception, DAOException {
		int id = -1;
		System.out.println("Test Lee Feria Inexistente"); //Probamos que la feria que intentamos crear existe ya en la bbdd

		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd

		Tferia feria = new Tferia(id, null, null, null, null, null); //Generamos un transfer
		daoFeria.create(feria); //Creamos una feria con los datos intoducidos en el transfer anterior

		assertTrue(daoFeria.create(feria) < 0); //Intentamos volver a crear una feria que ya existe en la bbdd y si nos devuelve -1
		//Es porque ya existia en la bbdd por lo tanto es correcto el test

	}
	@Test
	public void drop() throws Exception {
		Tferia feria = new Tferia(0, null, null, null, null, null);
		Boolean deleted = false;
		System.out.println("Test drop Feria");
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		deleted = daoFeria.delete(feria.getId());
		
		assertTrue(deleted == true);
	}

	@Test
	public void modify() throws Exception {
		Tferia feria = new Tferia(0, null, null, null, null, null);
		Integer id = -1;
		System.out.println("Test Modify Feria");
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		id = daoFeria.update(feria);
		
		assertTrue(id != -1);
	}

	@Test
	public void list() throws Exception, DAOException {
		System.out.println("Test list Feria");
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		Collection<Tferia> collectionFeria = daoFeria.readAll();
		assertTrue(collectionFeria != null); //Si se cumple assertTrue el test saldr� correcto
	}

	@Test
	public void show() throws Exception {
		Tferia feria = new Tferia(0, null, null, null, null, null);
		System.out.println("Test show Feria");
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		Tferia read = daoFeria.readByName(feria.getName());
		assertTrue(read != null);
	}

}