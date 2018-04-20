package Integracion.Participante;

import Integracion.Participacion.DAOParticipacionImp;
import Negocio.Participante.Tparticipante;
import Negocio.Participante.TparticipanteInternacional;
import Negocio.Participante.TparticipanteNacional;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DAOParticipanteImpTest {

	private static TparticipanteNacional tparticipanteTest1 = new TparticipanteNacional(1,"IBM", 778778778, true, "ALBACETE");
	private static TparticipanteInternacional tparticipanteTest2 = new TparticipanteInternacional(1,"GAMBAS VEGANAS", 887887887, true, "CATALONYA");


	@Before
	public  void setUp() throws Exception {
		// Borra todas las tuplas en la tabla 'participante' de la db
		DAOParticipanteImp dao = new DAOParticipanteImp();
		dao.deleteAll();
	}

	@Test
	public void create() throws Exception {
		DAOParticipanteImp dao = new DAOParticipanteImp();
		int out_id = dao.create(tparticipanteTest1);
		assertNotEquals(-1, out_id);
		assertEquals(1,out_id);
		int out_id2 = dao.create(tparticipanteTest2);
		assertNotEquals(-1, out_id2);
		assertEquals(2,out_id2);
	}

	@Test
	public void readAll() throws Exception {
		ArrayList<Tparticipante> out_list = new ArrayList<Tparticipante>();
		ArrayList<Tparticipante> testList = new ArrayList<Tparticipante>();
		DAOParticipanteImp dao = new DAOParticipanteImp();
		tparticipanteTest1.setActive(true);
		tparticipanteTest2.setActive(true);
		dao.create(tparticipanteTest1);
		dao.create(tparticipanteTest2);
		out_list = (ArrayList<Tparticipante>) dao.readAll();
		testList.add(tparticipanteTest1);
		testList.add(tparticipanteTest2);
		for(int i = 0; i < testList.size();++i)
			tparticipanteEquals(testList.get(i),out_list.get(i));
	}

	@Test
	public void readById() throws Exception {
		DAOParticipanteImp dao = new DAOParticipanteImp();

		// Test participanteNacional
		int out_id = dao.create(tparticipanteTest1);
		Tparticipante read = dao.readById(1);

		assertEquals(tparticipanteTest1.getId(), out_id);
		tparticipanteNacionalEquals(tparticipanteTest1, (TparticipanteNacional) read);

		// Test participanteInternacional
		int out_id2 = dao.create(tparticipanteTest2);
		read = dao.readById(1);

		assertEquals(tparticipanteTest2.getId(), out_id2);
		tparticipanteInternacionalEquals(tparticipanteTest2, (TparticipanteInternacional) read);
	}

	@Test
	public void update() throws Exception {
		DAOParticipanteImp dao = new DAOParticipanteImp();

		int out_id_create = dao.create(tparticipanteTest1);
		tparticipanteTest1.setPhone(890);
		tparticipanteTest1.setId(1);
		int out_id_update = dao.update(tparticipanteTest1);
		assertEquals(out_id_create, out_id_update);

		Tparticipante read = dao.readById(tparticipanteTest1.getId());
		tparticipanteNacionalEquals(tparticipanteTest1, (TparticipanteNacional) read);


	}

	@Test
	public void delete() throws Exception {
		DAOParticipanteImp dao = new DAOParticipanteImp();
		int out_id_create = dao.create(tparticipanteTest1);
		assert dao.delete(out_id_create);
	}

	private void tparticipanteEquals(Tparticipante first, Tparticipante second){
		if(first instanceof  TparticipanteNacional)
			tparticipanteNacionalEquals((TparticipanteNacional) first, (TparticipanteNacional) second);
		else
			tparticipanteInternacionalEquals((TparticipanteInternacional) first, (TparticipanteInternacional) second);

	}

	private void tparticipanteNacionalEquals(TparticipanteNacional first, TparticipanteNacional second){
		assertEquals(first.getId(), second.getId());
		assertEquals(first.getName(),second.getName());
		assertEquals(first.getPhone(),second.getPhone());
		assertEquals(first.getActive(), second.getActive());
		assertEquals(first.getRegion(), second.getRegion());
	}
	private void tparticipanteInternacionalEquals(TparticipanteInternacional first, TparticipanteInternacional second){
		assertEquals(first.getId(), second.getId());
		assertEquals(first.getName(),second.getName());
		assertEquals(first.getPhone(),second.getPhone());
		assertEquals(first.getActive(), second.getActive());
		assertEquals(first.getCountry(), second.getCountry());
	}
/*

	*/
}