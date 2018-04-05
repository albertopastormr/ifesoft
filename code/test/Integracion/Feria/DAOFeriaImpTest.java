package Integracion.Feria;

import Exceptions.DAOException;
import Negocio.Feria.Tferia;


import java.sql.Date;

import org.junit.Test;

import static org.junit.Assert.*;

public class DAOFeriaImpTest {
	@Test
	public void create() throws Exception { //ya conecta con la BD, 1er problema el id es auto incremental 
										    //asiq cada vez que hacemos un test aumenta y para reiniciar es un jaque mate
											// 2º el date sale raro, hay que saber como funciona
		Date ini = new Date(2017,04,04);
		Date fin = new Date(2017,05,04);
		Tferia tf = new Tferia("FITUR","Feria internacional turismo",ini,fin);
		DAOFeriaImp dao = new DAOFeriaImp();
		int a = dao.create(tf);
		assertEquals(1,a);
	}

	@Test
	public void readAll() throws Exception {
	}

	@Test
	public void readByName() throws Exception { //Test correcto, pero hay que revisar los constructores 
												//de Tferia pq sospecho que pueden dar algun error de retorno con el id	
		DAOFeriaImp dao = new DAOFeriaImp();
		Date ini = new Date(2017,4,4);
		Date fin = new Date(2017,5,4);
		Tferia tf = new Tferia("FITUR","Feria internacional turismo",ini,fin,true);
		Tferia out = dao.readByName("FITUR");
		assertEquals(out.getId(),tf.getId());
	}

	@Test
	public void update() throws Exception {
	}

	@Test
	public void delete() throws Exception {
	}

}