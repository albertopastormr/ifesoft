package Negocio.Pabellon;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.sql.SQLException;

import Integracion.Pabellon.DAOPabellon;

import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;

public class ASPabellonImp implements ASPabellon {
    public Integer create(Tpabellon pabellon) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null && pabellon.getTotal_m2() >= 0 && pabellon.getCapacity() >= 0) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read == null) {
                    if (pabellon.getTotal_m2() / pabellon.getCapacity() >= 1)
                        return daoPabellon.create(pabellon);
                    else
                        throw new ASException("ERROR: La capacidad del pabellon es demasiado alta respecto a los metros de este.\n");
                } else {
                    if (!read.getActive()) {
                        read.setActive(true);
                        return daoPabellon.update(read);
                    } else
                        throw new ASException("ERROR: El pabellon " + pabellon.getId() + " ya existe.\n");
                }
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
        	// introduzco la excepcion de arriba aqui
            throw new ASException("ERROR: No se han introducido los datos del pabellon o son incorrectos.\n");
    }

    public Integer drop(Tpabellon pabellon) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null && pabellon.getId() != -1) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read != null) {
                    read.setActive(false);
                    return daoPabellon.update(read);
                } else
                    throw new ASException("ERROR: El pabellon " + pabellon.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
    }

    public Integer modify(Tpabellon pabellon) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null && pabellon.getId() != -1) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read != null) {
                    if (pabellon.getTotal_m2() / pabellon.getCapacity() >= 1) {
                       return daoPabellon.update(pabellon);
                    } else
                        throw new ASException("ERROR: La capacidad del pabellon es demasiado alta respecto a los metros de este.\n");
                } else
                    throw new ASException("ERROR: El pabellon " + pabellon.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
    }

    public Collection<Tpabellon> list() throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        Collection<Tpabellon> collection;
        try {
            collection = daoPabellon.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tpabellon showById(Integer id) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (id != -1) {
            try {
                Tpabellon read = daoPabellon.readById(id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: El pabellon " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
    }
}
