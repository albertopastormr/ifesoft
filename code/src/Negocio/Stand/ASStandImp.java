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
        /*DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        if (stand != null && stand.getTotal_m2() >= 0 && stand.getUtil_m2() >= 0 && stand.getCapacity() >= 0) {
            try {
                Tstand read = daoStand.readById(stand.getId());
                if (read == null) {
                    if (stand.getId() != -1 && stand.getTotal_m2() >= 0 && stand.getTotal_m2() >= stand.getUtil_m2())
                        id = daoStand.create(stand);
                    else
                        throw new ASException("ERROR: Los datos del stand no son correctos.\n");
                } else
                    throw new ASException("ERROR: El stand " + stand.getId() + "ya existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");*/
        return id;
    }

    public Integer drop(Tstand stand) throws ASException, DAOException {
        int id = -1;
        /*DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
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
            throw new ASException("ERROR: No se han introducido los datos del stand.\n");*/
        return id;
    }

    public Integer modify(Tstand stand) throws ASException, DAOException {
        return null;
    }

    public Collection<Tstand> list() throws ASException, DAOException {
        return null;
    }

    public Tstand show(Tstand stand) throws ASException, DAOException {
        return null;
    }
}
