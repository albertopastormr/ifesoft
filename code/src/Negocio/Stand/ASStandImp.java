package Negocio.Stand;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Stand.DAOStand;
import Negocio.Stand.ASStand;
import Negocio.Stand.IFDAOStand;
import Negocio.Stand.Tstand;

import java.util.Collection;

public class ASStandImp implements ASStand {
    public Integer create(Tstand stand) throws ASException, DAOException {
        int id = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null) {
            try {
                if (stand.getTotal_m2() >= 0 && stand.getCost() >= 0 && stand.getNum_at_fair() >= 0)
                    id = daoStand.create(stand);
                else
                    throw new ASException("ERROR: Los datos del stand no son correctos.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
        return id;
    }

    public Integer drop(Tstand stand) throws ASException, DAOException {
        int id = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null) {
            stand.setActive(false);
            try {
                Tstand read = daoStand.readById(stand.getId());
                if (read != null)
                    id = daoStand.update(stand);
                else
                    throw new ASException("ERROR: El stand " + stand.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
        return id;
    }

    public Integer modify(Tstand stand) throws ASException, DAOException {
        int id = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null) {
            try {
                Tstand read = daoStand.readById(stand.getId());
                if (read != null) {
                    if (stand.getTotal_m2() >= 0 && stand.getCost() >= 0 && stand.getNum_at_fair() >= 0) {
                        id = daoStand.update(stand);
                    }
                    else
                        throw new ASException("ERROR: No se han introducido los datos del stand.\n");
                } else
                    throw new ASException("ERROR: El stand " + stand.getId() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
        return id;
    }

    public Collection<Tstand> list() throws ASException, DAOException {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        Collection<Tstand> collection;
        try {
            collection = daoStand.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tstand showById(Integer id) throws ASException, DAOException {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (id != -1) {
            try {
                Tstand read = daoStand.readById(id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: El stand " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
    }


    public Collection<Tstand> showByAssignation(Integer fair_id, Integer pavilion_id) throws ASException, DAOException {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (fair_id != -1 && pavilion_id != -1) {
            try {
                Collection<Tstand> read = daoStand.readByAssignation(fair_id, pavilion_id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: No existe un stand en la feria " + fair_id + " y el pabellon " + pavilion_id + ".\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
    }




    public Collection<Tstand> showByParticipation(Integer fair_id, Integer client_id) throws ASException, DAOException {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (fair_id != -1 && client_id != -1) {
            try {
                Collection<Tstand> read = daoStand.readByAssignation(fair_id, client_id);
                if (read != null)
                    return read;
                else
                    throw new ASException("ERROR: No existe un stand en la feria " + fair_id + " con el participante " + client_id + ".\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");
    }


}
