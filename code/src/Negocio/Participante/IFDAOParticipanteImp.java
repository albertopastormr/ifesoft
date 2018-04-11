package Negocio.Participante;

import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;
import Integracion.Participante.DAOParticipante;
import Integracion.Participante.DAOParticipanteImp;
import Negocio.Feria.IFDAOFeria;

public class IFDAOParticipanteImp extends IFDAOParticipante {
    public DAOParticipante generateDAOparticipante(){
        return new DAOParticipanteImp();
    }
}
