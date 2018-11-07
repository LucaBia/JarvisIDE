package classes;

import java.util.List;

public class Funcion implements Accion {
    private String nombre;
    private List<String> parametros;
    private List secuencia;
    private String retorno;

    public void addInstruccion(Instruccion ins) {
        this.secuencia.add(ins);
    }

    public void addIf(If ifNuevo) {
        this.secuencia.add(ifNuevo);
    }

    public void addInput(Input input) {
        this.secuencia.add(input);
    }

    public void addPrint(Print print) {
        this.secuencia.add(print);
    }

    public void addVariable(Variable var) {
        this.secuencia.add(var);
    }

    public void addWhile(While whileNuevo) {
        this.secuencia.add(whileNuevo);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addParametro(String par) {
        this.parametros.add(par);
    }

    public void setRetorno(String ret) {
        this.retorno = ret;
    }

    @Override
    public void escribir() {
        //todo
    }
}
