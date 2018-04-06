package Integracion.Feria;

import Exceptions.DAOException;
import Negocio.Feria.Tferia;


import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOFeriaImpTest {

	private static Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new Date(2017,4,4),new Date(2017,5,4),true);
	private static Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new Date(2017,9,28),new Date(2017,10,4),true);

	@Before
	public  void setUp() throws Exception {
		DAOFeriaImp dao = new DAOFeriaImp();
		dao.deleteAll();
	}

	@Test
	public void create() throws Exception {

		DAOFeriaImp dao = new DAOFeriaImp();
		int out_id = dao.create(tferiaTest1);
		assertNotEquals(-1, out_id);

		Tferia read = dao.readByName(tferiaTest1.getName());

		tferiaEquals(tferiaTest1, read);

		dao.delete(out_id);
	}

	@Test
	public void readAll() throws Exception {
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
		int save_id = tferiaTest2.getId();
		tferiaTest2.setId(out_id_create);
		int out_id_update = dao.update(tferiaTest2);
		tferiaTest2.setId(save_id);

		assertEquals(out_id_create, out_id_update);

		Tferia read = dao.readByName(tferiaTest1.getName());

		tferiaEquals(tferiaTest2, read);


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