import java.awt.Color;

public class Choque implements ElementoMapa {
    private Color cor;
    private Character simbolo;

    public Choque(Character simbolo, Color cor) {
        this.simbolo= simbolo;
        this.cor=cor;
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
    public boolean podeInteragir() {
        return false;
    }

    @Override
    public boolean podeSerAtravessado() {
        return true;
    }
    @Override
    public boolean morre() {
        return true;
    }
    @Override
    public boolean atacado() {
        return false;
    }
    
}
