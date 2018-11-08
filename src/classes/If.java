package classes;

import java.util.List;

public class If implements Accion {
    private Condicion condicion;
    private List<Accion> secuenciaTrue;
    private List<Accion> secuenciaFalse;

    public void setCondicion(Condicion cond) {
        this.condicion = cond;
    }

    //TRUE
    public void addAccionT(Accion ac) {
        this.secuenciaTrue.add(ac);
    }

    public void removeAccionT(Accion ac) {
        this.secuenciaTrue.remove(ac);
    }

    //FALSE
    public void addAccionF(Accion ac) {
        this.secuenciaFalse.add(ac);
    }

    public void removeAccionF(Accion ac) {
        this.secuenciaFalse.remove(ac);
    }

    @Override
    public void escribir() {
        //todo
    }

}
