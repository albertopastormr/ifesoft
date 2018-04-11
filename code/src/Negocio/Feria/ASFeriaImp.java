package Negocio.Feria;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Feria.DAOFeria;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class ASFeriaImp implements ASferia { // Try-Catch solo si hay que capturar excepciones del DAO
    public Integer create(Tferia feria) throws ASException, SQLException, DAOException, ClassNotFoundException {
        int id = -1;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null && feria.getName() != null && feria.getDescription() != null && feria.getIniDate() != null && feria.getEndDate() != null) {
            try {
                Tferia read = daoFeria.readByName(feria.getName());
                if (read == null) {
                    Date currentDate = new Date();
                    if (feria.getIniDate().after(currentDate) && feria.getEndDate().after(feria.getIniDate()))
                        id = daoFeria.create(feria);
                    else
                        throw new ASException("ERROR: El intervalo de fechas no es correcto.\n");
                } else {
                    //   if (read.equals(feria)) {
                    //   if (!read.getActive()) {
                    //           read.setActive(true);
                    //           id = daoFeria.update(read);
                    //       } else
                    //           throw new ASException("ERROR: La feria " + feria.getName() + "ya esta activa.\n");
                    //   } else
                    throw new ASException("ERROR: El nombre " + feria.getName() + " ya esta siendo utilizado.\n");
                }
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
        return id;
    }

    public Integer drop(Tferia feria) throws ASException, DAOException {
        int id = -1;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null) {
            feria.setActive(false);
            try {
                Tferia read = daoFeria.readByName(feria.getName());
                if (read != null)
                    id = daoFeria.update(feria);
                else
                    throw new ASException("ERROR: La feria " + feria.getName() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
        return id;
    }

    public Integer modify(Tferia feria) throws ASException, DAOException {
        int id = -1;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null) {
            try {
                Tferia read = daoFeria.readByName(feria.getName());
                if (read != null) {
                    Date currentDate = new Date();
                    if (feria.getIniDate().after(currentDate) && feria.getEndDate().after(feria.getIniDate()))
                        id = daoFeria.update(feria);
                    else
                        throw new ASException("ERROR: El intervalo de fechas no es correcto.\n");
                } else
                    throw new ASException("ERROR: La feria " + feria.getName() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
        return id;
    }

    public Collection<Tferia> list() throws ASException, DAOException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        Collection<Tferia> collection;
        try {
            collection = daoFeria.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tferia show(Tferia feria) throws ASException, DAOException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null) {
            try {
                Tferia read = daoFeria.readByName(feria.getName());
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: La feria " + feria.getName() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
        //return null; /* No se llega a este return */
    }
}
