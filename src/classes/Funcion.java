package classes;

import java.util.List;

public class Funcion implements Accion {
    private String nombre;
    private List<String> parametros;
    private List<Accion> secuencia;
    private String retorno;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addParametro(String par) {
        this.parametros.add(par);
    }

    public void setRetorno(String ret) {
        this.retorno = ret;
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
