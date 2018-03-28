package Negocio.Feria;

import Negocio.IFDAO;

import java.util.Collection;

public class ASFeriaImp implements ASferia {
    public Integer create(Tferia feria) {
        int id = -1;
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
            Tferia read = daoFeria.readByName(feria.getName());
            if (read == null)
                id = daoFeria.create(feria);
            else{
                if (!feria.getActive())
                    id = daoFeria.activate();
                else
                    throw new ASException("ERROR: ID Feria " + feria.getName());
            }

        }
        return id;
    }

    public Integer drop(Tferia feria) {
        int id = -1;
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
            Tferia read = daoFeria.readByName(feria.getName());
            if (read != null) id = daoFeria.drop(feria);
        }
        return id;
    }

    public Integer modify(Tferia feria) {
        int id = -1;
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
            Tferia read = daoFeria.readByName(feria.getName());
            if (read != null) id = daoFeria.update(feria);
        }
        return id;
    }

    public Collection<Tferia> list() {
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        return daoFeria.readAll();

    }

    public Tferia show(Tferia feria) {/*
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
            Tferia read = daoFeria.readByName(feria.getName());
            if (read != null) daoFeria.(feria);*/
        DAOFeria daoFeria = IFDAO.getInstance().generateDAOferia();
        if (feria != null) {
            Tferia read = daoFeria.readByName(feria.getName());
            if (read != null) return read;
        }
        return null;
    }
}
