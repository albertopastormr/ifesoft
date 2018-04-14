package Negocio.Feria;


import Exceptions.ASException;
import Exceptions.DAOException;
import org.junit.Test;
import Integracion.Feria.DAOFeria;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

public class ASFeriaImpTest {
	//-------------------------------------TEST CREATE-------------------------------------------------------------
	
	///intenta crear una feria existente
	@SuppressWarnings("deprecation")
	@Test(expected=ASException.class)//Se pasa el test si se lanza la excepcion
	//Metodo donde probamos que no se puede crear una feria ya existente en la bbdd
	public void createFeriaExistente() throws Exception, DAOException {
			ASFeriaImp asFeria = new ASFeriaImp();
			int id = -1;
			System.out.println("Test Create Feria Existente"); //Probamos que la feria que intentamos crear existe ya en la bbdd
	
			DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
			daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
			
			//Insertamos una feria en la bbdd
			Date dateIni = new Date(2018, 01, 12);
			Date dateEnd = new Date(2018, 01, 18);
	
			Tferia feria = new Tferia(id, "IBM", "IBM prueba existente", dateIni , dateEnd, false); //Generamos un transfer
			daoFeria.create(feria); //Creamos una feria con los datos intoducidos en el transfer anterior
			
			Tferia feria2 = new Tferia(id, "IBM", "IBM prueba existente", dateIni, dateEnd, false);
			asFeria.create(feria2); //A partir de aqui le estamos diciendo al test que si intentamos crear feria2 y se lanza una excepcion se 
									//Pasa el test
	}
	//Metodo donde probamos que no se puede crear una feria con una fecha incorrecta
	@SuppressWarnings("deprecation")
	@Test(expected=ASException.class)//Se pasa el test si se lanza la excepcion
	public void creaFeriaConFechaIncorrecta() throws Exception, DAOException {

		Integer id = -1;
		System.out.println("Test Lee Feria Inexistente"); //Probamos que la feria que intentamos crear existe ya en la bbdd

		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2016, 01, 12);
		Date dateEnd = new Date(2016, 01, 18);
		Tferia feria = new Tferia(id, "IBM", "prueba de fecha", dateIni, dateEnd, false); //Generamos un transfer
		daoFeria.create(feria);
	}
	//Metodo donde probamos que no se puede crear una feria cuando le pasamos algun datos incorrecto, es decir, a null o incompleto
	@SuppressWarnings("deprecation")
	@Test(expected=ASException.class)//Se pasa el test si se lanza la excepcion
	public void creaFeriaConDatosIncorrectos() throws Exception, DAOException {
		Integer id = -1;
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2019, 01, 12);
		Date dateEnd = new Date(2019, 01, 18);
		//En este caso le pasamos la descripcion a null y necesitaria una descripcion para poder crearse correctamente
		Tferia feria = new Tferia(id, "IBM", null, dateIni, dateEnd, false); //Generamos un transfer
		daoFeria.create(feria);
	}
	//Metodo donde probamos que no se puede crear una feria leida incorrectamente
	@SuppressWarnings("deprecation")
	@Test(expected=ASException.class)//Se pasa el test si se lanza la excepcion
	public void creaFeriaLeidaIncorrectamente() throws Exception, DAOException {
		Integer id = -1;
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2016, 01, 12);
		Date dateEnd = new Date(2016, 01, 18);
		Tferia feria = new Tferia(id, "IBM", "prueba de fecha", dateIni, dateEnd, false); //Generamos un transfer
		//Intentara leer IBM en la bbdd y saltara una excepcion en create ya que la feria no existe
		daoFeria.create(feria);
	}
	//Metodo donde probamos que se crea una feria correctamente
	@SuppressWarnings("deprecation")
	@Test
	public void creaFeria() throws Exception, DAOException {
		Integer id = -1;
		System.out.println("Test crea Feria Inexistente"); //Probamos que la feria que intentamos crear existe ya en la bbdd

		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2016, 01, 12);
		Date dateEnd = new Date(2016, 01, 18);
		Tferia feria = new Tferia(id, "IBM", "prueba de fecha", dateIni, dateEnd, false); //Generamos un transfer
		assertTrue(daoFeria.create(feria) > 0);
	}
	
	//---------------------------------------------------------------------------------------------------------------
	
	//---------------------------------------TEST DROP---------------------------------------------------------------------

	public void drop() throws Exception {
		Tferia feria = new Tferia(0, null, null, null, null, null);
		Boolean deleted = false;
		System.out.println("Test drop Feria");
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		deleted = daoFeria.delete(feria.getId());
		
		assertTrue(deleted == true);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------

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