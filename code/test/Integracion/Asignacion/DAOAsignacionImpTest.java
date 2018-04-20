package Integracion.Asignacion;

import Integracion.Feria.DAOFeriaImp;
import Integracion.Pabellon.DAOPabellonImp;
import Integracion.Stand.DAOStand;
import Integracion.Stand.DAOStandImp;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Stand.Tstand;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class DAOAsignacionImpTest {

	// Tasignacion para probar
	private static Tasignacion tasignacionTest1 = new Tasignacion(1,1, 1, 1, 1,true);
	private static Tasignacion tasignacionTest2 = new Tasignacion(2,2, 2, 2,2, true);
	// Tferia para probar
	private static Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new Date(117,0,4),new Date(117,0,4),true);
	private static Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new Date(117,9,28),new Date(117,10,4),true);
	// Tpabellon para probar
	private static Tpabellon tpabellonTest1 = new Tpabellon(1, 0,  0,true);
	private static Tpabellon tpabellonTest2 = new Tpabellon(2, 2,  2,true);


	@Before
	public  void setUp() throws Exception {
		// Borra todas las tuplas en la tabla 'asignacion' de la db
		DAOAsignacionImp dao = new DAOAsignacionImp();
		dao.deleteAll();
		// Creacion de 2 tFeria para soportar el uso de asignaciones
		DAOFeriaImp daoFeriaImp = new DAOFeriaImp();
		daoFeriaImp.deleteAll();
		daoFeriaImp.create(tferiaTest1);
		daoFeriaImp.create(tferiaTest2);
		// Creacion de 2 tPabellon para soportar el uso de asignaciones
		DAOPabellonImp daoPabellonImp = new DAOPabellonImp();
		daoPabellonImp.deleteAll();
		daoPabellonImp.create(tpabellonTest1);
		daoPabellonImp.create(tpabellonTest2);
	}


	@Test
	public void create() throws Exception {
		DAOAsignacionImp dao = new DAOAsignacionImp();
		assert  (dao.create(tasignacionTest1) != -1 );
		assert (dao.create(tasignacionTest2) != -1);
	}

	@Test
	public void readAll() throws Exception {
		ArrayList<Tasignacion> out_list = new ArrayList<Tasignacion>();
		ArrayList<Tasignacion> testList = new ArrayList<Tasignacion>();
		DAOAsignacionImp dao = new DAOAsignacionImp();
		assert (dao.create(tasignacionTest1) != -1);
		assert (dao.create(tasignacionTest2) != -1);
		out_list = (ArrayList<Tasignacion>) dao.readAll();
		testList.add(tasignacionTest1);
		testList.add(tasignacionTest2);
		for(int i = 0; i < testList.size();++i)
			tasignacionEquals(testList.get(i),out_list.get(i));
	}

	@Test
	public void readByFairId() throws Exception {
		DAOAsignacionImp dao = new DAOAsignacionImp();

		assert  (dao.create(tasignacionTest1) != -1);
		ArrayList<Tasignacion>  read = (ArrayList<Tasignacion>) dao.readByFairId(tasignacionTest1.getFair_id());

		tasignacionEquals(tasignacionTest1, read.get(0));
	}

	@Test
	public void readByPavilionId() throws Exception {
		DAOAsignacionImp dao = new DAOAsignacionImp();

		assert  (dao.create(tasignacionTest1) != -1);
		ArrayList<Tasignacion>  read = (ArrayList<Tasignacion>) dao.readByPavilionId(tasignacionTest1.getPavilion_id());

		tasignacionEquals(tasignacionTest1, read.get(0));
	}

	@Test
	public void update() throws Exception {
		DAOAsignacionImp dao = new DAOAsignacionImp();

		assert  (dao.create(tasignacionTest1) != -1);
		tasignacionTest1.setActive(false);
		assert  (dao.update(tasignacionTest1) != -1);

		ArrayList<Tasignacion> read = (ArrayList<Tasignacion>) dao.readByFairId(tasignacionTest1.getFair_id());
		tasignacionEquals(tasignacionTest1, read.get(0));
	}

	@Test
	public void delete() throws Exception {
		DAOAsignacionImp dao = new DAOAsignacionImp();
		assert  (dao.create(tasignacionTest1) != -1);
		assert dao.delete(tasignacionTest1.getId());
	}

	private void tasignacionEquals(Tasignacion first, Tasignacion second){
		assertEquals(first.getId(), first.getId());
		assertEquals(first.getFair_id(), second.getFair_id());
		assertEquals(first.getPavilion_id(), second.getPavilion_id());
		assertEquals(first.getTotal_m2(), second.getUsed_m2());
		assertEquals(first.getActive(), second.getActive());
	}
}