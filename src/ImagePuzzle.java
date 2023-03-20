import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImagePuzzle extends JFrame implements ActionListener {

    private JButton btnFacil, btnMedio, btnDificil, btnSair;
    private JLabel lblTitulo;
    private JPanel pnlMenu;
    private int dificuldade;

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
            
        } else if (e.getSource() == btnMedio) {
            dificuldade = 9;
            
        } else if (e.getSource() == btnDificil) {
            dificuldade = 12;
          
        } else if (e.getSource() == btnSair) {
            System.exit(0);
        }
    }

}