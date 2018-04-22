package Integracion.Stand;

import Integracion.Asignacion.DAOAsignacion;
import Integracion.Participacion.DAOParticipacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.IFDAOParticipacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.Tparticipante;
import Negocio.Participante.TparticipanteInternacional;
import Negocio.Participante.TparticipanteNacional;
import Negocio.Stand.Tstand;
import org.junit.Before;
import org.junit.Test;

import Integracion.Asignacion.DAOAsignacionImp;
import Integracion.Feria.DAOFeriaImp;
import Integracion.Pabellon.DAOPabellonImp;
import Integracion.Participacion.DAOParticipacionImp;
import Integracion.Participante.DAOParticipanteImp;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DAOStandImpTest {

	// Tstand para probar
	private static Tstand tstandTest1 = new Tstand(1,1,1, 0, 0, 0,true);
	private static Tstand tstandTest2 = new Tstand(2,1,1, 2, 2, 2,true);
	// Tferia para probar
	private static Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new Date(117,0,4),new Date(117,0,4),true);
	private static Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new Date(117,9,28),new Date(117,10,4),true);
	// Tparticipante para probar
	private static TparticipanteNacional tparticipanteTest1 = new TparticipanteNacional("IBM", 778778778, true, "ALBACETE");
	private static TparticipanteInternacional tparticipanteTest2 = new TparticipanteInternacional("GAMBAS VEGANAS", 887887887, true, "CATALONYA");
	// Tpabellon para probar
	private static Tpabellon tpabellonTest1 = new Tpabellon(1, 0,  0,true);
	private static Tpabellon tpabellonTest2 = new Tpabellon(2, 2,  2,true);
	// Tasignacion para probar
	private static Tasignacion tasignacionTest1 = new Tasignacion(1, 1, 1, 1,true);
	private static Tasignacion tasignacionTest2 = new Tasignacion(2, 2, 2,2, true);
	// Tparticipacion para probar
	private static Tparticipacion tparticipacionTest1 = new Tparticipacion(1, 1, 1,true);
	private static Tparticipacion tparticipacionTest2 = new Tparticipacion(2, 2, 2, true);


	@Before
	public  void setUp() throws Exception {
		// Borra todas las tuplas en la tabla 'stand' de la db
		DAOStandImp dao = new DAOStandImp();
		dao.deleteAll();
		// Creacion de 2 tFeria para soportar el uso de asignaciones
		DAOFeriaImp daoFeriaImp = new DAOFeriaImp();
		daoFeriaImp.deleteAll();
		daoFeriaImp.create(tferiaTest1);
		daoFeriaImp.create(tferiaTest2);
		// Creacion de 2 tParticipante para soportar el uso de asignaciones
		DAOParticipanteImp daoParticipanteImp = new DAOParticipanteImp();
		daoParticipanteImp.deleteAll();
		daoParticipanteImp.create(tparticipanteTest1);
		daoParticipanteImp.create(tparticipanteTest2);
		// Creacion de 2 tPabellon para soportar el uso de asignaciones
		DAOPabellonImp daoPabellonImp = new DAOPabellonImp();
		daoPabellonImp.deleteAll();
		daoPabellonImp.create(tpabellonTest1);
		daoPabellonImp.create(tpabellonTest2);
		// Creacion de 2 tParticipaciones para soportar el uso de asignaciones
		DAOParticipacion daoParticipacion = new DAOParticipacionImp();
		daoParticipacion.deleteAll();
		daoParticipacion.create(tparticipacionTest1);
		daoParticipacion.create(tparticipacionTest2);
		// Creacion de 2 tAsignaciones para soportar el uso de asignaciones
		DAOAsignacion daoAsignacion = new DAOAsignacionImp();
		daoAsignacion.deleteAll();
		daoAsignacion.create(tasignacionTest1);
		daoAsignacion.create(tasignacionTest2);
	}

	@Test
	public void create() throws Exception {
		DAOStandImp dao = new DAOStandImp();
		int out_id = dao.create(tstandTest1);
		assertNotEquals(-1, out_id);
		assertEquals(1,out_id);
		int out_id2 = dao.create(tstandTest2);
		assertNotEquals(-1, out_id2);
		assertEquals(2,out_id2);
	}

	@Test
	public void readAll() throws Exception {
		ArrayList<Tstand> out_list = new ArrayList<Tstand>();
		ArrayList<Tstand> testList = new ArrayList<Tstand>();
		DAOStandImp dao = new DAOStandImp();
		dao.create(tstandTest1);
		dao.create(tstandTest2);
		out_list = (ArrayList<Tstand>) dao.readAll();
		testList.add(tstandTest1);
		testList.add(tstandTest2);
		for(int i = 0; i < testList.size();++i)
			tstandEquals(testList.get(i),out_list.get(i));
	}

	@Test
	public void readByAssignation() throws Exception {
		ArrayList<Tstand> out_StandList = new ArrayList<Tstand>();
		ArrayList<Tstand> testList = new ArrayList<Tstand>();
		DAOStandImp daoS = new DAOStandImp();
		tstandTest1.setActive(true);
		tstandTest2.setActive(true);
		daoS.create(tstandTest1);
		daoS.create(tstandTest2);
		testList.add(tstandTest1);
		testList.add(tstandTest2);
		out_StandList = (ArrayList<Tstand>) daoS.readByAssignation(1);
		tstandEquals(testList.get(0),out_StandList.get(0));
		tstandEquals(testList.get(1),out_StandList.get(1));
	}

	@Test
	public void readByParticipation() throws Exception {
		ArrayList<Tstand> out_StandList = new ArrayList<Tstand>();
		ArrayList<Tstand> testList = new ArrayList<Tstand>();
		DAOStandImp daoS = new DAOStandImp();
		daoS.create(tstandTest1);
		daoS.create(tstandTest2);
		testList.add(tstandTest1);
		testList.add(tstandTest2);
		out_StandList = (ArrayList<Tstand>) daoS.readByAssignation(1);
		tstandEquals(testList.get(0),out_StandList.get(0));
		tstandEquals(testList.get(1),out_StandList.get(1));
	}

	@Test
	public void readById() throws Exception {
		DAOStandImp dao = new DAOStandImp();

		int out_id = dao.create(tstandTest1);
		Tstand read = dao.readById(1);
		assertEquals(out_id, read.getId());
		tstandEquals(tstandTest1, read);
	}

	@Test
	public void update() throws Exception {
		DAOStandImp dao = new DAOStandImp();

		int out_id_create = dao.create(tstandTest1);
		tstandTest1.setCost(4);
		tstandTest1.setId(1);
		int out_id_update = dao.update(tstandTest1);
		assertEquals(out_id_create, out_id_update);

		Tstand read = dao.readById(tstandTest1.getId());
		tstandEquals(read, tstandTest1);
	}

	@Test
	public void delete() throws Exception {
		DAOStandImp dao = new DAOStandImp();
		int out_id_create = dao.create(tstandTest1);
		assert dao.delete(out_id_create);
	}

	private void tstandEquals(Tstand first, Tstand second){
		assertEquals(first.getId(), second.getId());
		assertEquals(first.getNum_at_fair(),second.getNum_at_fair());
		assertEquals(first.getTotal_m2(),second.getTotal_m2());
		assertEquals(first.getActive(), second.getActive());
	}

}