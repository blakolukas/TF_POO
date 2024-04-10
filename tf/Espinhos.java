import java.awt.Color;

public class Espinhos implements ElementoMapa {
    private Character simbolo;
    private Color cor;

    public Espinhos(Character simbolo, Color cor){
        this.cor=cor;
        this.simbolo=simbolo;
    }

    @Override
    public boolean atacado() {
        return true;
    }

    @Override
    public Color getCor() {
        return cor;
    }

    @Override
    public Character getSimbolo() {
        return simbolo;
    }

    @Override
    public String interage() {
        return null;
    }

    @Override
    public boolean morre() {
        return false;
    }

    @Override
    public boolean podeInteragir() {
        return false;
    }

    @Override
    public boolean podeSerAtravessado() {
        return false;
    }
    
}
