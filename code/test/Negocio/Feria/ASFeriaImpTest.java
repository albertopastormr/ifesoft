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
		ASFeriaImp asFeria = new ASFeriaImp();
		Integer id = -1;
		System.out.println("Test Lee Feria Inexistente"); //Probamos que la feria que intentamos crear existe ya en la bbdd

		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2016, 01, 12);
		Date dateEnd = new Date(2016, 01, 18);
		Tferia feria = new Tferia(id, "IBM", "prueba de fecha", dateIni, dateEnd, false); //Generamos un transfer
		asFeria.create(feria);
	}
	//Metodo donde probamos que no se puede crear una feria cuando le pasamos algun datos incorrecto, es decir, a null o incompleto
	@SuppressWarnings("deprecation")
	@Test(expected=ASException.class)//Se pasa el test si se lanza la excepcion
	public void creaFeriaConDatosIncorrectos() throws Exception, DAOException {
		ASFeriaImp asFeria = new ASFeriaImp();
		Integer id = -1;
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2019, 01, 12);
		Date dateEnd = new Date(2019, 01, 18);
		//En este caso le pasamos la descripcion a null y necesitaria una descripcion para poder crearse correctamente
		Tferia feria = new Tferia(id, "IBM", null, dateIni, dateEnd, false); //Generamos un transfer
		asFeria.create(feria);
	}
	//Metodo donde probamos que no se puede crear una feria leida incorrectamente
	@SuppressWarnings("deprecation")
	@Test(expected=ASException.class)//Se pasa el test si se lanza la excepcion
	public void creaFeriaLeidaIncorrectamente() throws Exception, DAOException {
		ASFeriaImp asFeria = new ASFeriaImp();
		Integer id = -1;
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2016, 01, 12);
		Date dateEnd = new Date(2016, 01, 18);
		Tferia feria = new Tferia(id, "IBM", "prueba de fecha", dateIni, dateEnd, false); //Generamos un transfer
		//Intentara leer IBM en la bbdd y saltara una excepcion en create ya que la feria no existe
		asFeria.create(feria);
	}
	//Metodo donde probamos que se crea una feria correctamente
	@SuppressWarnings("deprecation")
	@Test
	public void createFeria() throws Exception, DAOException {
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
	@Test(expected=ASException.class)
	//Intentamos borrar una feria pasandole un id incorrecto(-1)
	public void dropIdincorrecto() throws Exception {
		ASFeriaImp asFeria = new ASFeriaImp();

		Tferia feria = new Tferia(-1, "IBM", null, null , null, true); //Generamos un transfer
		asFeria.drop(feria); //Creamos una feria con los datos intoducidos en el transfer anterior
		
	}
	//Intentamos borrar una feria con un id que no se encuentra en la bbdd
	@Test(expected=ASException.class)
	public void dropIdinexistente() throws Exception {
		ASFeriaImp asFeria = new ASFeriaImp();
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd

		
		Tferia feria = new Tferia(223344, "IBM", "The feria IBM", null , null, true);
		asFeria.drop(feria);
		
	}
	//Borramos una feria correctamente, existente en la bbdd
	@SuppressWarnings("deprecation")
	@Test
	public void dropFeria() throws Exception {
		ASFeriaImp asFeria = new ASFeriaImp();		
		Date dateIni = new Date(2016, 01, 12);
		Date dateEnd = new Date(2016, 01, 18);

		Tferia feria = new Tferia(223344, "IBM", "The feria IBM", dateIni , dateEnd, false);
		asFeria.create(feria); //Creamos una feria IBM
		assertTrue(asFeria.drop(feria) > 0); //Si se ha podido borrar la feria correctamente, nos devuelve el id de la feria borrada logicamente
											//Y ademas el test sale correcto
		
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------

	//------------------------------------------TEST MODIFY-------------------------------------------------------------------------
	//Test que comprueba que no se puede modificar una feria con un id incorrecto
	@Test(expected=ASException.class)
	public void testModifyIdIncorrecto() throws Exception {
		Integer id = -1;
		ASFeriaImp asFeria = new ASFeriaImp();
		Tferia feria = new Tferia(id, "IBM", "Desription", null, null, true);
		asFeria.modify(feria);
	}
	
	//Test que comprueba que no se puede modificar una feria con un nombre que no existe  en la bddd
	@SuppressWarnings("deprecation")
	@Test(expected=ASException.class)
	public void testModifyFeriaInexistente() throws Exception {
		Integer id = 223344;
		ASFeriaImp asFeria = new ASFeriaImp();
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2018, 12, 12);
		Date dateEnd = new Date(2018, 12, 18);
		Tferia feria = new Tferia(id, "IBM", "Desription", dateIni, dateEnd, true);
		asFeria.modify(feria);
	}
	
	//Test que intenta modificar una feria introduciendole una fecha no valida
	@SuppressWarnings("deprecation")
	@Test(expected=ASException.class)
	public void testModifyFeriaFechaIncorrecta() throws Exception {
		Integer id = 223344;
		ASFeriaImp asFeria = new ASFeriaImp();
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2018, 12, 12);
		Date dateEnd = new Date(2018, 12, 18);
		Tferia feria = new Tferia(id, "IBM", "Desription", dateIni, dateEnd, true);
		asFeria.create(feria);
		
		Date dateIni2 = new Date(2016, 12, 12);
		Date dateEnd2 = new Date(2016, 12, 18);
		Tferia feria2 = new Tferia(id, "IBM", "Description", dateIni2, dateEnd2, true );
		asFeria.modify(feria2);
	}
	
	//Test que comprueba que se modifica una feria correctamente en la bbdd
	@SuppressWarnings("deprecation")
	@Test
	public void testModify() throws Exception {
		Integer id = 223344;
		ASFeriaImp asFeria = new ASFeriaImp();
		DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
		daoFeria.deleteAll(); //Borramos todos los campos de la bbdd
		
		Date dateIni = new Date(2018, 12, 12);
		Date dateEnd = new Date(2018, 12, 18);
		Tferia feria = new Tferia(id, "IBM", "Desription", dateIni, dateEnd, true);
		asFeria.create(feria);
		
		Date dateIni2 = new Date(2018, 12, 13);
		Date dateEnd2 = new Date(2018, 12, 19);
		Tferia feria2 = new Tferia(id, "IBM", "Description + more things", dateIni2, dateEnd2, true );
		asFeria.modify(feria2);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------
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