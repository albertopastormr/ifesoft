package Negocio.Feria;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Feria.DAOFeria;
import Negocio.IFDAO;

import java.sql.SQLException;
import java.util.Collection;

public class ASFeriaImp implements ASferia {
    public Integer create(Tferia feria) throws ASException, SQLException, DAOException {
        int id = -1;
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
            Tferia read = daoFeria.readByName(feria.getName());
            if (read == null)
                id = daoFeria.create(feria);
            else{
                if (!feria.getActive()){
                	feria.setActive(true);
					id = daoFeria.update(feria);
				}
                else
                    throw new ASException("ERROR: Feria Name " + feria.getName() + "ya existente\n");
            }

        }
        return id;
    }

    public Integer drop(Tferia feria) throws ASException, DAOException {
        int id = -1;
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
			feria.setActive(false);
            Tferia read = daoFeria.readByName(feria.getName());
            if (read != null)
            	id = daoFeria.update(feria);
        }
        else
			throw new ASException("ERROR: feria erronea Name: " + feria.getName() + " introducida\n");
        return id;
    }

    public Integer modify(Tferia feria) throws ASException, DAOException {
        int id = -1;
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
            Tferia read = daoFeria.readByName(feria.getName());
            if (read != null) id = daoFeria.update(feria);
        }
		else
			throw new ASException("ERROR: ID Feria " + feria.getName() + "introducida\n");
        return id;
    }

    public Collection<Tferia> list() throws DAOException {
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        return daoFeria.readAll();

    }

    public Tferia show(Tferia feria) throws ASException, DAOException {
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
            Tferia read = daoFeria.readByName(feria.getName());
            if (read != null)
                return read;
        }
		else
			throw new ASException("ERROR: ID Feria " + feria.getName() + "introducida\n");
        return null;
    }
}
