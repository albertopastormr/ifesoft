package Negocio.Pabellon;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.sql.SQLException;

import Integracion.Pabellon.DAOPabellon;

import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;

public class ASPabellonImp implements ASPabellon {
    public Integer create(Tpabellon pabellon) throws ASException {
        int id = -1;
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null && pabellon.getTotal_m2() >= 0 && pabellon.getUtil_m2() >= 0 && pabellon.getCapacity() >= 0) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read == null) {
                    if (pabellon.getTotal_m2() >= 0 && pabellon.getTotal_m2() >= pabellon.getUtil_m2())
                        id = daoPabellon.create(pabellon);
                    else
                        throw new ASException("ERROR: Los datos del pabellon no son correctos.\n");
                } else {
                    if (!read.getActive()) {
                        read.setActive(true);
                        id = daoPabellon.update(read);
                    } else
                        throw new ASException("ERROR: El pabellon " + pabellon.getId() + "ya existe.\n");
                }
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
        return id;
    }

    public Integer drop(Tpabellon pabellon) throws ASException {
        int id = -1;
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null && pabellon.getId() != -1) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read != null) {
                    read.setActive(false);
                    id = daoPabellon.update(read);
                } else
                    throw new ASException("ERROR: El pabellon " + pabellon.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
        return id;
    }

    public Integer modify(Tpabellon pabellon) throws ASException {
        int id = -1;
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null && pabellon.getId() != -1) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read != null) {
                    if (pabellon.getTotal_m2() >= pabellon.getUtil_m2()) {
                        id = daoPabellon.update(pabellon);
                    } else
                        throw new ASException("Error: Los metros cuadrados totales son menores a los utiles.\n");
                } else
                    throw new ASException("ERROR: El pabellon " + pabellon.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
        return id;
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

    public Tpabellon showById(Tpabellon pabellon) throws ASException {
        DAOPabellon daoPabellon = IFDAOPabellon.getInstance().generateDAOpabellon();
        if (pabellon != null) {
            try {
                Tpabellon read = daoPabellon.readById(pabellon.getId());
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: El pabellon " + pabellon.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
        //return null;
    }
}
