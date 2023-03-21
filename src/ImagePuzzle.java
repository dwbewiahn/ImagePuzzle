
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import util.ConversorImagemAED;
import util.TabuleiroQuebraCabeca;

public class ImagePuzzle extends JFrame implements ActionListener {

    private JButton btnFacil, btnMedio, btnDificil, btnSair;
    private JLabel lblTitulo;
    private JPanel pnlMenu;
    private int dificuldade;
    private TabuleiroQuebraCabeca tabuleiro;
    public ImagePuzzle() {
        setTitle("ImagePuzzle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        // Inicializa componentes da interface gráfica
        pnlMenu = new JPanel();
        pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.Y_AXIS));
    
        lblTitulo = new JLabel("Quebra-Cabeça de Imagem");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        btnFacil = new JButton("Fácil");
        btnFacil.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFacil.addActionListener(this);
    
        btnMedio = new JButton("Médio");
        btnMedio.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMedio.addActionListener(this);
    
        btnDificil = new JButton("Difícil");
        btnDificil.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDificil.addActionListener(this);
    
        btnSair = new JButton("Sair");
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSair.addActionListener(this);
    
        pnlMenu.add(lblTitulo);
        pnlMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        pnlMenu.add(btnFacil);
        pnlMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlMenu.add(btnMedio);
        pnlMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlMenu.add(btnDificil);
        pnlMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        pnlMenu.add(btnSair);
    
        getContentPane().add(pnlMenu);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImagePuzzle quebraCabeca = new ImagePuzzle();
            quebraCabeca.setVisible(true);
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFacil) {
            dificuldade = 6;
            iniciarJogo();
        } else if (e.getSource() == btnMedio) {
            dificuldade = 9;
            iniciarJogo();
        } else if (e.getSource() == btnDificil) {
            dificuldade = 12;
            iniciarJogo();
        } else if (e.getSource() == btnSair) {
            System.exit(0);
        }
    }
    
    private void iniciarJogo() {
        // 1. Carregar uma imagem aleatória
        Random random = new Random();
        int numeroImagem = random.nextInt(5) + 1;
        String caminhoImagem = "src\\imagens\\foto_"+ numeroImagem + ".png";
    
        // 2. Converter a imagem para preto e branco
        String caminhoImagemPB = "src\\imagens\\foto_pb_" + numeroImagem + ".png";
        ConversorImagemAED conversor = new ConversorImagemAED(caminhoImagem);
        conversor.converteBW(caminhoImagemPB);
    
        // 3. Adicionar uma borda à imagem
        String caminhoImagemPBBorda = "src\\imagens\\foto_pb_borda_" + numeroImagem + ".png";
        int tamanhoBorda = 20;
        ConversorImagemAED conversorBorda = new ConversorImagemAED(caminhoImagemPB);
        conversorBorda.criarMargem(tamanhoBorda, caminhoImagemPBBorda);
    
        // 4. Dividir a imagem de acordo com a dificuldade e embaralhar as peças
        BufferedImage imagemPBBorda = null;
       
        try {
            imagemPBBorda = ImageIO.read(new File(caminhoImagemPBBorda));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    
        int linhas;
        if (dificuldade == 6) {
            linhas = 2;
        } else if (dificuldade == 9) {
            linhas = 3;
        } else {
            linhas = 4;
        }
    
        int colunas = linhas;
        tabuleiro = new TabuleiroQuebraCabeca(imagemPBBorda, linhas, colunas);
        this.getContentPane().add(tabuleiro);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    
        this.getContentPane().remove(pnlMenu);
    
        SwingUtilities.invokeLater(this::loopJogo);
    
    }
    
    private void loopJogo() {
        while (true) {
            // Obter entrada do usuário para os índices das peças a serem trocadas
            int indice1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o índice da primeira peça a ser trocada:"));
            int indice2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o índice da segunda peça a ser trocada:"));
    
            // Trocar as peças e atualizar o tabuleiro do jogo
            tabuleiro.trocarPecas(indice1-1, indice2-1);
    
            // Verificar se o jogador resolveu o quebra-cabeça
            if (tabuleiro.estaResolvido()) {
                JOptionPane.showMessageDialog(this, "Parabéns! Você resolveu o quebra-cabeça!", "Vitória",
                        JOptionPane.INFORMATION_MESSAGE);
    
                // Oferecer ao jogador opções para jogar novamente ou retornar ao menu principal
                Object[] opcoes = { "Jogar novamente", "Retornar ao menu" };
                int opcao = JOptionPane.showOptionDialog(this, "O que você gostaria de fazer em seguida?",
                        "Jogar novamente ou retornar ao menu",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
    
                if (opcao == 0) {
                    // Jogar novamente
                    this.getContentPane().removeAll();
                    iniciarJogo();
                    break;
                } else {
                    // Retornar ao menu principal
                    this.getContentPane().removeAll();
                    getContentPane().add(pnlMenu);
                    this.pack();
                    this.setLocationRelativeTo(null);
                    this.setVisible(true);
                    setSize(800, 600);
                    break;
                }
            }
        }
    }
}
      