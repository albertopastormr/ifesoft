package Integracion.Feria;

import Exceptions.DAOException;
import Negocio.Feria.Tferia;


import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOFeriaImpTest {

	private static Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new Date(117,0,4),new Date(117,0,4),true);
	private static Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new Date(117,9,28),new Date(117,10,4),true);

	/*@Before
	public  void setUp() throws Exception {
		DAOFeriaImp dao = new DAOFeriaImp();
		dao.deleteAll();
	}
*/
	@Test
	public void create() throws Exception {

		DAOFeriaImp dao = new DAOFeriaImp();
		int out_id = dao.create(tferiaTest1);
		assertNotEquals(-1, out_id);
		assertEquals(1,out_id);
		int out_id2 = dao.create(tferiaTest2);
		assertNotEquals(-1, out_id2);
		assertEquals(2,out_id2);
		dao.delete(out_id);
		dao.delete(out_id2);
	}
	
	@Test
	public void readAll() throws Exception {
			ArrayList<Tferia> out_list = new ArrayList<Tferia>();
			ArrayList<Tferia> testList = new ArrayList<Tferia>();
			DAOFeriaImp dao = new DAOFeriaImp();
			dao.create(tferiaTest1);
			dao.create(tferiaTest2);
			out_list = (ArrayList<Tferia>) dao.readAll();
			testList.add(tferiaTest1);
			testList.add(tferiaTest2);
			for(int i = 0; i < testList.size();++i)
				tferiaEquals(testList.get(i),out_list.get(i));
			dao.deleteAll();
	}

	

	@Test
	public void readByName() throws Exception {

		DAOFeriaImp dao = new DAOFeriaImp();

		int out_id = dao.create(tferiaTest1);
		Tferia read = dao.readByName(tferiaTest1.getName());

		tferiaEquals(tferiaTest1, read);

		dao.delete(out_id);
	}

	@Test
	public void update() throws Exception {
		DAOFeriaImp dao = new DAOFeriaImp();

		int out_id_create = dao.create(tferiaTest1);
		tferiaTest1.setName("fit");
		tferiaTest1.setId(1);
		int out_id_update = dao.update(tferiaTest1);

		assertEquals(out_id_create, out_id_update);
		dao.delete(out_id_update);
	}

	@Test
	public void delete() throws Exception {
		DAOFeriaImp dao = new DAOFeriaImp();
		int out_id_create = dao.create(tferiaTest1);
		assert dao.delete(out_id_create);
	}


	private void tferiaEquals(Tferia first, Tferia second){
		assertEquals(first.getName(),second.getName());
		assertEquals(first.getDescription(),second.getDescription());
		assertEquals(first.getIniDate(),second.getIniDate());
		assertEquals(first.getEndDate(),second.getEndDate());
	}

}