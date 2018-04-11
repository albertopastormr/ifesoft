package Negocio.Participacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Participacion.DAOParticipacion;
import Negocio.Participacion.ASParticipacion;
import Negocio.Participacion.IFDAOParticipacion;
import Negocio.Participacion.Tparticipacion;

import java.util.Collection;

public class ASParticipacionImp implements ASParticipacion {
    public Integer create(Tparticipacion participacion) throws ASException, DAOException {
        int id = -1;
        /*DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null && participacion.getTotal_m2() >= 0 && participacion.getUtil_m2() >= 0 && participacion.getCapacity() >= 0) {
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getId());
                if (read == null) {
                    if (participacion.getId() != -1 && participacion.getTotal_m2() >= 0 && participacion.getTotal_m2() >= participacion.getUtil_m2())
                        id = daoParticipacion.create(participacion);
                    else
                        throw new ASException("ERROR: Los datos del participacion no son correctos.\n");
                } else
                    throw new ASException("ERROR: El participacion " + participacion.getId() + "ya existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion.\n");*/
        return id;
    }

    public Integer drop(Tparticipacion participacion) throws ASException, DAOException {
        int id = -1;
        /*DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();
        if (participacion != null) {
            participacion.setActive(false);
            try {
                Tparticipacion read = daoParticipacion.readById(participacion.getId());
                if (read != null)
                    id = daoParticipacion.update(participacion);
                else
                    throw new ASException("ERROR: El participacion " + participacion.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participacion.\n");*/
        return id;
    }

    public Integer modify(Tparticipacion participacion) throws ASException, DAOException {
        return null;
    }

    public Collection<Tparticipacion> list() throws ASException, DAOException {
        return null;
    }

    public Tparticipacion show(Tparticipacion participacion) throws ASException, DAOException {
        return null;
    }
}
