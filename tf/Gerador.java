import java.awt.Color;

public class Gerador implements ElementoMapa {
    private Color cor;
    private Character simbolo;

    public Gerador(Character simbolo, Color cor){
        this.simbolo=simbolo;
        this.cor=cor;
    }

    @Override
    public Character getSimbolo() {
        return simbolo;
    }

    @Override
    public Color getCor() {
        return cor;
    }

    @Override
    public boolean podeSerAtravessado() {
        return false;
    }

    @Override
    public boolean podeInteragir() {
        return true;
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
    public boolean atacado() {
        return false;
    }
    
}