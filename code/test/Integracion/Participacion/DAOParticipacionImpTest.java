package Integracion.Participacion;

import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;
import Integracion.Participante.DAOParticipanteImp;
import Integracion.Stand.DAOStandImp;
import Negocio.Feria.Tferia;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.TparticipanteInternacional;
import Negocio.Participante.TparticipanteNacional;
import Negocio.Stand.Tstand;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DAOParticipacionImpTest {
	// Tparticipacion para probar
	private static Tparticipacion tparticipacionTest1 = new Tparticipacion(1, 1, 1,true);
	private static Tparticipacion tparticipacionTest2 = new Tparticipacion(2, 2, 2, true);
	// Tferia para probar
	private static Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new Date(117,0,4),new Date(117,0,4),true);
	private static Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new Date(117,9,28),new Date(117,10,4),true);
	// Tparticipante para probar
	private static TparticipanteNacional tparticipanteTest1 = new TparticipanteNacional("IBM", 778778778, true, "ALBACETE");
	private static TparticipanteInternacional tparticipanteTest2 = new TparticipanteInternacional("GAMBAS VEGANAS", 887887887, true, "CATALONYA");
	// Tstand para probar
	private static Tstand tstandTest1 = new Tstand(1, 0, 0, 0,true);
	private static Tstand tstandTest2 = new Tstand(2, 2, 2, 2,true);

	@Before
	public  void setUp() throws Exception {
		// Borra todas las tuplas en la tabla 'asignacion' de la db
		DAOParticipacionImp dao = new DAOParticipacionImp();
		dao.deleteAll();
		// Creacion de 2 tFeria para soportar el uso de asignaciones
		DAOFeriaImp daoFeriaImp = new DAOFeriaImp();
		daoFeriaImp.create(tferiaTest1);
		daoFeriaImp.create(tferiaTest2);
		// Creacion de 2 tParticipante para soportar el uso de asignaciones
		DAOParticipanteImp daoParticipanteImp = new DAOParticipanteImp();
		daoParticipanteImp.create(tparticipanteTest1);
		daoParticipanteImp.create(tparticipanteTest2);
		// Creacion de 2 tStand para soportar el uso de asignaciones
		DAOStandImp daoStandImp = new DAOStandImp();
		daoStandImp.create(tstandTest1);
		daoStandImp.create(tstandTest2);
	}

	@Test
	public void create() throws Exception {
		DAOParticipacionImp dao = new DAOParticipacionImp();
		assert  dao.create(tparticipacionTest1);
		assert dao.create(tparticipacionTest2);
	}

	@Test
	public void readAll() throws Exception {
		ArrayList<Tparticipacion> out_list = new ArrayList<Tparticipacion>();
		ArrayList<Tparticipacion> testList = new ArrayList<Tparticipacion>();
		DAOParticipacionImp dao = new DAOParticipacionImp();
		assert dao.create(tparticipacionTest1);
		assert dao.create(tparticipacionTest2);
		out_list = (ArrayList<Tparticipacion>) dao.readAll();
		testList.add(tparticipacionTest1);
		testList.add(tparticipacionTest2);
		for(int i = 0; i < testList.size();++i)
			tparticipacionEquals(testList.get(i),out_list.get(i));
	}

	@Test
	public void readByFairId() throws Exception {
		DAOParticipacionImp dao = new DAOParticipacionImp();

		assert  dao.create(tparticipacionTest1);
		ArrayList<Tparticipacion>  read = (ArrayList<Tparticipacion>) dao.readByFairId(tparticipacionTest1.getFair_id());

		tparticipacionEquals(tparticipacionTest1, read.get(0));
	}

	@Test
	public void readByClientId() throws Exception {
		DAOParticipacionImp dao = new DAOParticipacionImp();

		assert  dao.create(tparticipacionTest1);
		ArrayList<Tparticipacion>  read = (ArrayList<Tparticipacion>) dao.readByClientId(tparticipacionTest1.getClient_id());

		tparticipacionEquals(tparticipacionTest1, read.get(0));
	}

	@Test
	public void update() throws Exception {
		DAOParticipacionImp dao = new DAOParticipacionImp();

		assert  dao.create(tparticipacionTest1);
		tparticipacionTest1.setActive(false);
		assert  dao.update(tparticipacionTest1);

		ArrayList<Tparticipacion> read = (ArrayList<Tparticipacion>) dao.readByFairId(tparticipacionTest1.getFair_id());
		tparticipacionEquals(tparticipacionTest1, read.get(0));
	}


	@Test
	public void delete() throws Exception {
		DAOParticipacionImp dao = new DAOParticipacionImp();
		assert  dao.create(tparticipacionTest1);
		assert dao.delete(tparticipacionTest1.getFair_id(), tparticipacionTest1.getClient_id(), tparticipacionTest1.getStand_id());
	}

	private void tparticipacionEquals(Tparticipacion first, Tparticipacion second){
		assertEquals(first.getFair_id(), second.getFair_id());
		assertEquals(first.getClient_id(), second.getClient_id());
		assertEquals(first.getStand_id(), second.getStand_id());
		assertEquals(first.getActive(), second.getActive());
	}
}