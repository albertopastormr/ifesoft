package Integracion.Stand;

import Negocio.Stand.Tstand;
import org.junit.Before;
import org.junit.Test;

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

	}

	@Test
	public void readByParticipation() throws Exception {

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