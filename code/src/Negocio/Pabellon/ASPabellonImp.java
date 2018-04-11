package Negocio.Pabellon;

import Exceptions.ASException;
import Exceptions.DAOException;
import java.sql.SQLException;

import Integracion.Pabellon.DAOPabellon;
import java.util.Collection;

public class ASPabellonImp implements ASPabellon {

    public Integer create(Tpabellon pabellon) throws ASException, DAOException {
        int id = -1;
        return id;
    }

    public Integer drop(Tpabellon pabellon) throws ASException, DAOException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if(pabellon != null){
            Tpabellon read = DAO
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
        return null;
    }

    public Integer modify(Tpabellon pabellon) throws ASException, DAOException {
        return null;
    }

    public Collection<Tpabellon> list() throws DAOException {
        return null;
    }

    public Tpabellon show(Tpabellon pabellon) throws ASException, DAOException {
        return null;
    }
}
