package Negocio.Asignacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Negocio.Asignacion.ASAsignacion;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Asignacion.Tasignacion;

import java.util.Collection;

public class ASAsignacionImp implements ASAsignacion {
    public boolean create(Tasignacion asignacion) throws ASException {
        boolean create = false;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (asignacion != null && asignacion.getFair_id() != -1 && asignacion.getPavilion_id() != -1 && asignacion.getStand_id() != -1 && asignacion.getUsed_m2() != 0) {
            try {
                Tasignacion read = daoAsignacion.readById(asignacion.getFair_id(), asignacion.getPavilion_id(), asignacion.getStand_id());
                if (read == null) {
                    if (asignacion.getUsed_m2() >= 0)
                        create = daoAsignacion.create(asignacion);
                    else
                        throw new ASException("ERROR: Los datos de la asignacion no son correctos.\n");
                } else
                    throw new ASException("ERROR: La asignacion Feria(" + asignacion.getFair_id() + ") Pabellon("+ asignacion.getPavilion_id() + ") Stand("+ asignacion.getStand_id() +") ya existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion.\n");
        return create;
    }

    public boolean drop(Tasignacion asignacion) throws ASException {
        boolean drop = false;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (asignacion != null) {
            asignacion.setActive(false);
            try {
                Tasignacion read = daoAsignacion.readById(asignacion.getFair_id(), asignacion.getPavilion_id(), asignacion.getStand_id());
                if (read != null)
                    drop = daoAsignacion.update(asignacion);
                else
                    throw new ASException("ERROR: La asignacion Feria(" + asignacion.getFair_id() + ") Pabellon("+ asignacion.getPavilion_id() + ") Stand("+ asignacion.getStand_id() +") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion.\n");
        return drop;
    }

    public boolean modify(Tasignacion asignacion) throws ASException {
        boolean modify = false;
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        if (asignacion != null && asignacion.getFair_id() != -1 && asignacion.getPavilion_id() != -1 && asignacion.getStand_id() != -1 && asignacion.getUsed_m2() >= 0) {
            try {
                Tasignacion read = daoAsignacion.readById(asignacion.getFair_id(), asignacion.getPavilion_id(), asignacion.getStand_id());
                if (read != null) {
                    modify =  daoAsignacion.update(asignacion);
                } else
                    throw new ASException("ERROR: La asignacion Feria(" + asignacion.getFair_id() + ") Pabellon("+ asignacion.getPavilion_id() + ") Stand("+ asignacion.getStand_id() +") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del asignacion.\n");
        return modify;
    }

    public Collection<Tasignacion> list() throws ASException {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        Collection<Tasignacion> collection;
        try {
            collection = daoAsignacion.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tasignacion show(Integer fair_id, Integer pavilion_id, Integer stand_id) throws ASException {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        Tasignacion read = null;
        if (fair_id != -1 && pavilion_id != -1 && stand_id != -1) {
            try {
                read = daoAsignacion.readById(fair_id, pavilion_id, stand_id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: La asignacion Feria(" + fair_id + ") Pabellon("+ pavilion_id + ") Stand("+ stand_id +") no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del pabellon.\n");
    }

    public Collection<Tasignacion> showByIdPavilion(Integer pavilion_id) throws ASException {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        Collection<Tasignacion> collection;
        if(pavilion_id  != 0) {
            try {
                collection = daoAsignacion.readByPavilionId(pavilion_id);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        }else
            throw new ASException("ERROR: No se ha introducido el id del pabellon a leer.\n");
        return collection;
    }

    public Collection<Tasignacion> showByIdFair(Integer fair_id) throws ASException {
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        Collection<Tasignacion> collection;
        if(fair_id  != 0) {
            try {
                collection = daoAsignacion.readByFairId(fair_id);
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        }else
            throw new ASException("ERROR: No se ha introducido el id de la feria a leer.\n");
        return collection;
    }
}
