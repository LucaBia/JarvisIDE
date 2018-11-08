package classes;

import java.util.List;

public class While implements Accion {
    private Condicion condicion;
    private List<Accion> secuencia;

    public void setCondicion(Condicion cond) {
        this.condicion = cond;
    }

    public void addAccion(Accion ac) {
        this.secuencia.add(ac);
    }

    public void removeAccion(Accion ac) {
        this.secuencia.remove(ac);
    }

    @Override
    public void escribir() {
        //todo
    }
}
