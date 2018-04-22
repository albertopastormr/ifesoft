package Negocio.Feria;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Feria.DAOFeria;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class ASFeriaImp implements ASFeria { // Try-Catch solo si hay que capturar excepciones del DAO
    public Integer create(Tferia feria) throws ASException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null && feria.getName() != null && feria.getDescription() != null && feria.getIniDate() != null && feria.getEndDate() != null) {
            try {
                Tferia read = daoFeria.readByName(feria.getName());
                if (read == null) {
                    Date currentDate = new Date();
                    if (feria.getIniDate().after(currentDate) && feria.getEndDate().after(feria.getIniDate()))
                        return daoFeria.create(feria);
                    else
                        throw new ASException("ERROR: El intervalo de fechas no es correcto.\n");
                } else {
                    if (read.equals(feria)) {
                        if (!read.getActive()) {
                            read.setActive(true);
                            return daoFeria.update(read);
                        } else
                            throw new ASException("ERROR: La feria " + feria.getName() + " ya esta activa.\n");
                    } else
                        throw new ASException("ERROR: La feria " + feria.getName() + " ya esta siendo utilizada con id " + read.getId() + ".\n");
                }
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
    }

    public Integer drop(Tferia feria) throws ASException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null && feria.getId() != -1) {
            try {
                Tferia read = daoFeria.readById(feria.getId());
                if (read != null) {
                    read.setActive(false);
                    return daoFeria.update(read);
                } else
                    throw new ASException("ERROR: La feria " + feria.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
    }

    public Integer modify(Tferia feria) throws ASException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria != null && feria.getName() != null && feria.getId() != -1) {
            try {
                Tferia read = daoFeria.readById(feria.getId());
                if (read != null) {
                    Tferia nameOK = daoFeria.readByName(feria.getName());
                    if ((nameOK == null) || nameOK.getName().equals(read.getName())) {
                        Date currentDate = new Date();
                        if (feria.getIniDate().after(currentDate) && feria.getEndDate().after(feria.getIniDate()))
                            return daoFeria.update(feria);
                        else
                            throw new ASException("ERROR: El intervalo de fechas no es correcto.\n");
                    } else
                        throw new ASException("ERROR: El nuevo nombre para la feria " + feria.getName() + " ya esta siendo usado.\n");
                } else
                    throw new ASException("ERROR: La feria " + feria.getName() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
    }

    public Collection<Tferia> list() throws ASException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        Collection<Tferia> collection;
        try {
            collection = daoFeria.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    @Override
    @SuppressWarnings("deprecation")
    public Collection<Tferia> listDates(Tferia feria) throws ASException {
        Date ini, end;
        Collection<Tferia> collection;
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (feria == null || feria.getIniDate() == null || feria.getEndDate() == null) {
            ini = new Date();
            ini.setMonth(0);
            ini.setDate(1);
            end = new Date();
            end.setMonth(11);
            end.setDate(31);
        } else {
            ini = feria.getIniDate();
            end = feria.getEndDate();
        }
        try {
            collection = daoFeria.readByDates(ini, end);
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tferia showByName(String name) throws ASException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (name != null) {
            try {
                Tferia read = daoFeria.readByName(name);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: La feria " + name + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
    }

    public Tferia showById(Integer id) throws ASException {
        DAOFeria daoFeria = IFDAOFeria.getInstance().generateDAOferia();
        if (id != -1) {
            try {
                Tferia read = daoFeria.readById(id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: La feria " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos de la feria.\n");
    }
}
