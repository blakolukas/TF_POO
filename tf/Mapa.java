import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Mapa {
    private List<String> mapa;
    private Map<Character, ElementoMapa> elementos;
    private int x = 50; // Posição inicial X do personagem
    private int y = 50; // Posição inicial Y do personagem
    private final int TAMANHO_CELULA = 10; // Tamanho de cada célula do mapa
    private boolean[][] areaRevelada; // Rastreia quais partes do mapa foram reveladas
    private ElementoMapa[][] areaPersonagem;
    private final Color brickColor = new Color(153, 76, 0); // Cor marrom para tijolos
    private final Color vegetationColor = new Color(34, 139, 34); // Cor verde para vegetação
    private final Color shockColor= new Color(255,210,0);
    private final Color offColor= new Color(255,0,0);
    private final Color onColor= new Color(0,204,0);
    private final Color spikesColor= new Color(200,0,0);
    private final int RAIO_VISAO = 2; // Raio de visão do personagem
    private final int RAIO_INT = 1;

    public Mapa(String arquivoMapa) {
        mapa = new ArrayList<>();
        elementos = new HashMap<>();
        registraElementos();
        carregaMapa(arquivoMapa);
        areaRevelada = new boolean[mapa.size()+1000][mapa.get(0).length()+1000];
        atualizaCelulasReveladas();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTamanhoCelula() {
        return TAMANHO_CELULA;
    }

    public int getNumLinhas() {
        return mapa.size();
    }

    public int getNumColunas() {
        return mapa.get(0).length();
    }

    public ElementoMapa getElemento(int x, int y) {
        Character id = mapa.get(y).charAt(x);
        return elementos.get(id);
    }

    public boolean estaRevelado(int x, int y) {
        return areaRevelada[y][x];
    }

    // Move conforme enum Direcao
    public boolean move(Direcao direcao) {
        int dx = 0, dy = 0;

        switch (direcao) {
            case CIMA:
                dy = -TAMANHO_CELULA;
                break;
            case BAIXO:
                dy = TAMANHO_CELULA;
                break;
            case ESQUERDA:
                dx = -TAMANHO_CELULA;
                break;
            case DIREITA:
                dx = TAMANHO_CELULA;
                break;
            default:
                return false;
        }

        

        if (!podeMover(x + dx, y + dy)) {
            System.out.println("Não pode mover");
            return false;
        }

        if (!morreu(x + dx, y + dy)) {
            System.out.println("morreu");
            tela();
            
            
        }
            
        
        x += dx;
        y += dy;

        // Atualiza as células reveladas
        atualizaCelulasReveladas();
        return true;
    }

    private static void tela(){
        JFrame janela= new JFrame();
        JPanel panel= new JPanel();
        final JLabel label= new JLabel("Morreu!");
        panel.add(label);
        JButton botao= new JButton("\nAceitar a morte");
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){
            System.exit(0);
            }
        });
        panel.add(botao);
        janela.add(panel);
        janela.setSize(150, 100);
        janela.setVisible(true);
    }


    private boolean morreu(int nextX, int nextY) {
        int mapX = nextX / TAMANHO_CELULA;
        int mapY = nextY / TAMANHO_CELULA - 1;

        if (mapa == null)
            return false;

        if (mapX >= 0 && mapX < mapa.get(0).length() && mapY >= 1 && mapY <= mapa.size()) {
            char id;

            try {
               id = mapa.get(mapY).charAt(mapX);
            } catch (StringIndexOutOfBoundsException e) {
                return false;
            }

            if (id == ' ')
                return true;

            ElementoMapa elemento = elementos.get(id);
            if (elemento != null) {
                if(elemento.morre()==false){
                    return true;
                }else{
                    return false;
                }
            }
        }

        return false;
    }

    // Verifica se o personagem pode se mover para a próxima posição
    private boolean podeMover(int nextX, int nextY) {
        int mapX = nextX / TAMANHO_CELULA;
        int mapY = nextY / TAMANHO_CELULA - 1;

        if (mapa == null)
            return false;

        if (mapX >= 0 && mapX < mapa.get(0).length() && mapY >= 1 && mapY <= mapa.size()) {
            char id;

            try {
               id = mapa.get(mapY).charAt(mapX);
            } catch (StringIndexOutOfBoundsException e) {
                return false;
            }

            if (id == ' ')
                return true;

            ElementoMapa elemento = elementos.get(id);
            if (elemento != null) {
                //System.out.println("Elemento: " + elemento.getSimbolo() + " " + elemento.getCor());
                return elemento.podeSerAtravessado();
            }
        }

        return false;
    }


    public String interage() {
        for (int i = Math.max(0, y / TAMANHO_CELULA - RAIO_INT); i < Math.min(mapa.size(), y / TAMANHO_CELULA + RAIO_INT + 1); i++) {
            for (int j = Math.max(0, x / TAMANHO_CELULA - RAIO_INT); j < Math.min(mapa.get(i).length(), x / TAMANHO_CELULA + RAIO_INT + 1); j++) {
                if(elementos.get('G').equals(getElemento(j, i))){
                    elementos.put('G', new Gerador('⌻', offColor));
                    elementos.put('X', new Nada(' ', null));
                    return "Gerador desligado.\nChoques foram desativados.";
                }
            }
        }
        
        return "Nenhum gerador encontrado.";
    }

    public String ataca() {
        for (int i = Math.max(0, y / TAMANHO_CELULA - RAIO_INT); i < Math.min(mapa.size(), y / TAMANHO_CELULA + RAIO_INT + 1); i++) {
            for (int j = Math.max(0, x / TAMANHO_CELULA - RAIO_INT); j < Math.min(mapa.get(i).length(), x / TAMANHO_CELULA + RAIO_INT + 1); j++) {
                if(elementos.get('E').equals(getElemento(j, i))){
                String linha = mapa.get(i);
                mapa.set(i, linha.replace('E', ' ')); 
                }
            }
        }
        return "Ataca!";
    }
    

    private void carregaMapa(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                mapa.add(line);
                // Se character 'P' está contido na linha atual, então define a posição inicial do personagem
                if (line.contains("P")) {
                    x = line.indexOf('P') * TAMANHO_CELULA;
                    y = mapa.size() * TAMANHO_CELULA;
                    // Remove o personagem da linha para evitar que seja desenhado
                    mapa.set(mapa.size() - 1, line.replace('P', ' '));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar as células reveladas
    private void atualizaCelulasReveladas() {
        if (mapa == null)
            return;
        for (int i = Math.max(0, y / TAMANHO_CELULA - RAIO_VISAO); i < Math.min(mapa.size(), y / TAMANHO_CELULA + RAIO_VISAO + 1); i++) {
            for (int j = Math.max(0, x / TAMANHO_CELULA - RAIO_VISAO); j < Math.min(mapa.get(i).length(), x / TAMANHO_CELULA + RAIO_VISAO + 1); j++) {
                areaRevelada[i][j] = true;
            }
        }
    }

    // Registra os elementos do mapa
    private void registraElementos() {
        // Parede
        elementos.put('#', new Parede('▣', brickColor));
        // Vegetação
        elementos.put('V', new Vegetacao('♣', vegetationColor));
        elementos.put('X', new Choque('↯', shockColor ));
        elementos.put('G', new Gerador('⌻', onColor ));
        elementos.put('E', new Espinhos('✸', spikesColor));

        }
}
