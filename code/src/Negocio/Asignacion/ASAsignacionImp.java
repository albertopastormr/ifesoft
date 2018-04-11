package Negocio.Asignacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Negocio.Asignacion.ASAsignacion;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;

import java.util.Collection;

public class ASAsignacionImp implements ASAsignacion {
    public Integer create(Tasignacion asignacion) throws ASException, DAOException {
        int id = -1;
        /*DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (asignacion != null && asignacion.getTotal_m2() >= 0 && asignacion.getUtil_m2() >= 0 && asignacion.getCapacity() >= 0) {
            try {
                Tasignacion read = daoAsignacion.readById(asignacion.getId());
                if (read == null) {
                    if (asignacion.getId() != -1 && asignacion.getTotal_m2() >= 0 && asignacion.getTotal_m2() >= asignacion.getUtil_m2())
                        id = daoAsignacion.create(asignacion);
                    else
                        throw new ASException("ERROR: Los datos del asignacion no son correctos.\n");
                } else
                    throw new ASException("ERROR: El asignacion " + asignacion.getId() + "ya existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion.\n");*/
        return id;
    }

    public Integer drop(Tasignacion asignacion) throws ASException, DAOException {
        int id = -1;
        /*DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (asignacion != null) {
            asignacion.setActive(false);
            try {
                Tasignacion read = daoAsignacion.readById(asignacion.getId());
                if (read != null)
                    id = daoAsignacion.update(asignacion);
                else
                    throw new ASException("ERROR: El asignacion " + asignacion.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion.\n");*/
        return id;
    }

    public Integer modify(Tasignacion asignacion) throws ASException, DAOException {
        return null;
    }

    public Collection<Tasignacion> list() throws ASException, DAOException {
        return null;
    }

    public Tasignacion show(Tasignacion asignacion) throws ASException, DAOException {
        return null;
    }
}
