package util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class TabuleiroQuebraCabeca extends JPanel {
    private ArrayList<BufferedImage> pecas;
    private ArrayList<BufferedImage> pecasOriginais;
    private int linhas;
    private int colunas;
    private int larguraPreferida;
    private int alturaPreferida;

    public TabuleiroQuebraCabeca(BufferedImage imagem, int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new ArrayList<>();
        pecasOriginais = new ArrayList<>();
        larguraPreferida = imagem.getWidth();
        alturaPreferida = imagem.getHeight();

        // Divide a imagem em peças
        int larguraPeca = imagem.getWidth() / colunas;
        int alturaPeca = imagem.getHeight() / linhas;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                BufferedImage peca = imagem.getSubimage(j * larguraPeca, i * alturaPeca, larguraPeca, alturaPeca);
                pecas.add(peca);
                pecasOriginais.add(peca);
            }
        }

        // Embaralha as peças
        Collections.shuffle(pecas);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        int larguraPeca = getWidth() / colunas;
        int alturaPeca = getHeight() / linhas;
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(Color.WHITE);
    
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                int indice = i * colunas + j;
                g.drawImage(pecas.get(indice), j * larguraPeca, i * alturaPeca, larguraPeca, alturaPeca, null);
                g.drawString(Integer.toString(indice+1), j * larguraPeca + 30, i * alturaPeca + 100);
            }
        }
    }
    
    public void trocarPecas(int indice1, int indice2) {
        Collections.swap(pecas, indice1, indice2);
        repaint();
    }

    public boolean estaResolvido() {
        return pecas.equals(pecasOriginais);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(larguraPreferida, alturaPreferida);
    }
}
