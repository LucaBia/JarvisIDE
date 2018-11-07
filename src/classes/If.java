package classes;

import java.util.List;

public class If implements Accion {
    private Condicion condicion;
    private List secuenciaTrue;
    private List secuenciaFalse;

    public void setCondicion(Condicion cond) {
        this.condicion = cond;
    }

    //TRUE
    public void addInstruccionT(Instruccion ins) {
        this.secuenciaTrue.add(ins);
    }

    public void addIfT(If ifNuevo) {
        this.secuenciaTrue.add(ifNuevo);
    }

    public void addInputT(Input input) {
        this.secuenciaTrue.add(input);
    }

    public void addPrintT(Print print) {
        this.secuenciaTrue.add(print);
    }

    public void addVariableT(Variable var) {
        this.secuenciaTrue.add(var);
    }

    public void addWhileT(While whileNuevo) {
        this.secuenciaTrue.add(whileNuevo);
    }

    //FALSE
    public void addInstruccionF(Instruccion ins) {
        this.secuenciaFalse.add(ins);
    }

    public void addIfF(If ifNuevo) {
        this.secuenciaFalse.add(ifNuevo);
    }

    public void addInputF(Input input) {
        this.secuenciaFalse.add(input);
    }

    public void addPrintF(Print print) {
        this.secuenciaFalse.add(print);
    }

    public void addVariableF(Variable var) {
        this.secuenciaFalse.add(var);
    }

    public void addWhileF(While whileNuevo) {
        this.secuenciaFalse.add(whileNuevo);
    }

    @Override
    public void escribir() {
        //todo
    }

}
