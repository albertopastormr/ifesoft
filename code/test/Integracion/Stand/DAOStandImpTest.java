package Integracion.Stand;

import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.Tparticipante;
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

	private static Tstand tstandTest1 = new Tstand(1, 0, 0, 0,true);
	private static Tstand tstandTest2 = new Tstand(2, 2, 2, 2,true);

	@Before
	public  void setUp() throws Exception {
		// Borra todas las tuplas en la tabla 'stand' de la db
		DAOStandImp dao = new DAOStandImp();
		dao.deleteAll();
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
		DAOPabellonImp daoP = new DAOPabellonImp();
		DAOFeriaImp daoF = new DAOFeriaImp();
		DAOAsignacionImp daoA = new DAOAsignacionImp();
		Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new Date(117,0,4),new Date(117,0,4),true);
		Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new Date(117,9,28),new Date(117,10,4),true);
		daoF.create(tferiaTest1);
		daoF.create(tferiaTest2);
		Tpabellon tpabellonTest1 = new Tpabellon(2,2,1,true);
		Tpabellon tpabellonTest2 = new Tpabellon(2,2,1,true);
		daoP.create(tpabellonTest1);
		daoP.create(tpabellonTest2);
		daoS.create(tstandTest1);
		daoS.create(tstandTest2);
		Tasignacion tasignacionTest1 = new Tasignacion(1,1,1,1,true);
		Tasignacion tasignacionTest2 = new Tasignacion(1,1,2,1,true);
		daoA.create(tasignacionTest1);
		daoA.create(tasignacionTest2);
		out_StandList = (ArrayList<Tstand>) daoS.readByAssignation(1,1);
		tstandEquals(testList.get(0),out_StandList.get(0));
		tstandEquals(testList.get(1),out_StandList.get(1));
		daoS.deleteAll();
		daoA.deleteAll();
		daoP.deleteAll();
		daoF.deleteAll();
	}

	@Test
	public void readByParticipation() throws Exception {
		ArrayList<Tstand> out_StandList = new ArrayList<Tstand>();
		ArrayList<Tstand> testList = new ArrayList<Tstand>();
		DAOStandImp daoS = new DAOStandImp();
		DAOParticipanteImp daoP = new DAOParticipanteImp();
		DAOFeriaImp daoF = new DAOFeriaImp();
		DAOParticipacionImp daoPt = new DAOParticipacionImp();
		Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new Date(117,0,4),new Date(117,0,4),true);
		Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new Date(117,9,28),new Date(117,10,4),true);
		daoF.create(tferiaTest1);
		daoF.create(tferiaTest2);
		Tparticipante tparticipanteTest1 = new Tparticipante("imperioAlbertino",910000000,true);
		Tparticipante tparticipanteTest2 = new Tparticipante("imperioAlbertino",911111111,true);
		daoP.create(tparticipanteTest1);
		daoP.create(tparticipanteTest2);
		daoS.create(tstandTest1);
		daoS.create(tstandTest2);
		Tparticipacion tparticipacionTest1 = new Tparticipacion(1,1,1,true);
		Tparticipacion tparticipacionTest2 = new Tparticipacion(1,2,1,true);
		daoPt.create(tparticipacionTest1);
		daoPt.create(tparticipacionTest2);
		out_StandList = (ArrayList<Tstand>) daoS.readByAssignation(1,1);
		tstandEquals(testList.get(0),out_StandList.get(0));
		tstandEquals(testList.get(1),out_StandList.get(1));
		daoS.deleteAll();
		daoPt.deleteAll();
		daoP.deleteAll();
		daoF.deleteAll();
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