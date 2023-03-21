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
    
    }
}