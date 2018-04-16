package Integracion.Pabellon;

import Negocio.Pabellon.Tpabellon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DAOPabellonImpTest {
	private static Tpabellon tpabellonTest1 = new Tpabellon(1, 0, 0, 0,true);
	private static Tpabellon tpabellonTest2 = new Tpabellon(2, 2, 2, 2,true);
	@Before
	public  void setUp() throws Exception {
		DAOPabellonImp dao = new DAOPabellonImp();
		dao.deleteAll();
	}

	@Test
	public void create() throws Exception {
		DAOPabellonImp dao = new DAOPabellonImp();
		int out_id = dao.create(tpabellonTest1);
		assertNotEquals(-1, out_id);
		assertEquals(1,out_id);
		int out_id2 = dao.create(tpabellonTest2);
		assertNotEquals(-1, out_id2);
		assertEquals(2,out_id2);
	}

	@Test
	public void readAll() throws Exception {
		ArrayList<Tpabellon> out_list = new ArrayList<Tpabellon>();
		ArrayList<Tpabellon> testList = new ArrayList<Tpabellon>();
		DAOPabellonImp dao = new DAOPabellonImp();
		dao.create(tpabellonTest1);
		dao.create(tpabellonTest2);
		out_list = (ArrayList<Tpabellon>) dao.readAll();
		testList.add(tpabellonTest1);
		testList.add(tpabellonTest2);
		for(int i = 0; i < testList.size();++i)
			tpabellonEquals(testList.get(i),out_list.get(i));
	}

	@Test
	public void readById() throws Exception {
		DAOPabellonImp dao = new DAOPabellonImp();

		int out_id = dao.create(tpabellonTest1);
		Tpabellon read = dao.readById(1);

		assertEquals(tpabellonTest1.getId(), out_id);
		tpabellonEquals(tpabellonTest1, read);
	}

	@Test
	public void update() throws Exception {
		DAOPabellonImp dao = new DAOPabellonImp();

		int out_id_create = dao.create(tpabellonTest1);
		tpabellonTest1.setCapacity(4);
		tpabellonTest1.setId(1);
		int out_id_update = dao.update(tpabellonTest1);
		assertEquals(out_id_create, out_id_update);

		Tpabellon read = dao.readById(tpabellonTest1.getId());
		tpabellonEquals(tpabellonTest1, read);
	}

	@Test
	public void delete() throws Exception {
		DAOPabellonImp dao = new DAOPabellonImp();
		int out_id_create = dao.create(tpabellonTest1);
		assert dao.delete(out_id_create);
	}

	private void tpabellonEquals(Tpabellon first, Tpabellon second){
		assertEquals(first.getId(), second.getId());
		assertEquals(first.getCapacity(),second.getCapacity());
		assertEquals(first.getTotal_m2(),second.getTotal_m2());
		assertEquals(first.getUtil_m2(),second.getUtil_m2());
		assertEquals(first.getActive(), second.getActive());
	}
}