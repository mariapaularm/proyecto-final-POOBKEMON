package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.event.*;


public class PokemonGUI {
    private static final Logger LOGGER = Logger.getLogger(PokemonGUI.class.getName());
    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al iniciar la aplicación", e);
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        });
    }


    static class AlphaJPanel extends JPanel {
        private float alpha = 0f;
        private final Image backgroundImage;

        public AlphaJPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
            setOpaque(false);
        }

        public void setAlpha(float alpha) {
            this.alpha = alpha;
            repaint();
        }

        public float getAlpha() {
            return alpha;
        }


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            // Dibuja el fondo con opacidad controlada
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            if (backgroundImage != null) {
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }


            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f * alpha));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.dispose();
        }
    }

    private static void createAndShowGUI() throws Exception {

        frame = new JFrame("POOBkemon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setMinimumSize(new Dimension(600, 500));
        frame.setLocationRelativeTo(null);


        String gifPath = "src/Presentation/Recursos/Iniciostar.gif";
        String titlePath = "src/Presentation/Recursos/POOBKEMON-23-4-2025.png";

        ImageIcon gifIcon = new ImageIcon(gifPath);
        if (gifIcon.getImage() == null) throw new Exception("No se pudo cargar el GIF: " + gifPath);

        ImageIcon titleIcon = new ImageIcon(titlePath);
        if (titleIcon.getImage() == null) throw new Exception("No se pudo cargar la imagen: " + titlePath);


        AlphaJPanel mainPanel = new AlphaJPanel(gifIcon.getImage());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel del título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));

        JLabel titleLabel = new JLabel(new ImageIcon(titleIcon.getImage()
                .getScaledInstance(400, -1, Image.SCALE_SMOOTH)));
        titlePanel.add(titleLabel);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));

        // Botones
        JButton startButton = createStyledButton("Iniciar Juego", new Color(50, 205, 50, 180));
        JButton instructionsButton = createStyledButton("Instrucciones", new Color(30, 144, 255, 180));
        JButton exitButton = createStyledButton("Salir", new Color(220, 20, 60, 180));

        // Añadir acción al botón Iniciar Juego
        startButton.addActionListener(e -> mostrarProfesorAbedul());

        instructionsButton.addActionListener(e -> mostrarInstrucciones());
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(instructionsButton);
        buttonPanel.add(exitButton);

        // Organizar componentes
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalGlue(), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Mostrar ventana
        frame.add(mainPanel);
        frame.setVisible(true);

        // Animación de entrada (ahora usando AlphaJPanel)
        Timer fadeIn = new Timer(30, e -> {
            float currentAlpha = mainPanel.getAlpha() + 0.05f;
            if (currentAlpha >= 1.0f) {
                currentAlpha = 1.0f;
                ((Timer) e.getSource()).stop();
            }
            mainPanel.setAlpha(currentAlpha);
        });
        fadeIn.start();
    }

    private static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 150), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), 230));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    private static void mostrarInstrucciones() {
        JDialog dialog = new JDialog(frame, "Instrucciones del Juego", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(frame);

        JTextArea instructions = new JTextArea();
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setFont(new Font("Arial", Font.PLAIN, 16));
        instructions.setText(
                "INSTRUCCIONES DE POOBKEMON:\n\n" +
                        "1. Elige tu Pokémon inicial.\n" +
                        "2. Explora el mapa para encontrar Pokémon.\n" +
                        "3. Batalla contra entrenadores.\n" +
                        "4. Captura Pokémon salvajes.\n" +
                        "5. Sube de nivel y fortalece tu equipo.\n\n" +
                        "CONTROLES:\n" +
                        "- Teclas de dirección o WASD para moverte.\n" +
                        "- ESPACIO para interactuar.\n" +
                        "- Mouse en batallas.\n"
        );

        dialog.add(new JScrollPane(instructions));
        dialog.setVisible(true);
    }

    private static void mostrarProfesorAbedul() {
        // Ocultar la ventana principal
        frame.setVisible(false);

        // Crear y mostrar la ventana del Profesor Abedul
        try {
            new ProfesorAbedul(frame);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al mostrar la pantalla del Profesor Abedul", e);
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
            frame.setVisible(true); // Volver a mostrar la ventana principal en caso de error
        }
    }
}




class ProfesorAbedul extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(ProfesorAbedul.class.getName());
    private final JFrame parentFrame;
    private int currentMessageIndex = 0;
    private final String[] mensajes = {
            "¡Hola! ¡Bienvenido al mundo de POOBkemon!",
            "Yo soy el Profesor ABEDUL.",
            "Este mundo está habitado por criaturas llamadas Pokemones.",
            "La gente y los Pokemones conviven y se ayudan mutuamente.",
            "Algunos juegan con los Pokemones, otros luchan con ellos.",
            "Pero aún hay muchas cosas que no sabemos sobre ellos.",
            "Por eso estudio a diario los Pokemones.",
            "Me gustaría verte en acción.",
            "Comencemos con esta nueva aventura Pokemon"
    };
    private Timer textAnimationTimer;
    private StringBuilder currentText;
    private String fullText;
    private final JTextArea dialogText;
    private final JLabel arrowLabel;
    private boolean isAnimatingText = false;

    public ProfesorAbedul(JFrame parentFrame) throws Exception {
        super("POOBkemon");
        this.parentFrame = parentFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);


        ImageIcon profesorIcon = loadImageIcon("Recursos/abedul2.png");
        ImageIcon pokemonIcon = loadImageIcon("Recursos/pokemonabe.png");


        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                RadialGradientPaint gp = new RadialGradientPaint(
                        new Point(getWidth() / 2, getHeight() / 3),
                        getWidth() / 3,
                        new float[]{0.0f, 1.0f},
                        new Color[]{new Color(255, 255, 150), new Color(0, 0, 0, 0)}
                );
                g2d.setPaint(gp);
                g2d.fillOval(
                        getWidth() / 2 - getWidth() / 3,
                        getHeight() / 3 - getHeight() / 4,
                        getWidth() / 3 * 2,
                        getHeight() / 2
                );
            }
        };
        mainPanel.setLayout(null);


        Image scaledProfesor = profesorIcon.getImage().getScaledInstance(150, -1, Image.SCALE_SMOOTH);
        JLabel profesorLabel = new JLabel(new ImageIcon(scaledProfesor));
        profesorLabel.setBounds(280, 100, 150, 200);


        Image scaledPokemon = pokemonIcon.getImage().getScaledInstance(100, -1, Image.SCALE_SMOOTH);
        JLabel pokemonLabel = new JLabel(new ImageIcon(scaledPokemon));
        pokemonLabel.setBounds(200, 200, 100, 100);


        JPanel dialogPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
            }
        };
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setBounds(20, 320, 600, 120);


        dialogText = new JTextArea();
        dialogText.setEditable(false);
        dialogText.setLineWrap(true);
        dialogText.setWrapStyleWord(true);
        dialogText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        dialogText.setMargin(new Insets(10, 15, 10, 15));
        dialogText.setBackground(Color.WHITE);
        dialogText.setForeground(Color.BLACK);


        arrowLabel = new JLabel("▼");
        arrowLabel.setFont(new Font("Arial", Font.BOLD, 16));
        arrowLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        arrowLabel.setBounds(580, 410, 20, 20);
        arrowLabel.setVisible(false);


        mainPanel.add(dialogPanel);
        mainPanel.add(profesorLabel);
        mainPanel.add(pokemonLabel);
        mainPanel.add(arrowLabel);

        dialogPanel.add(dialogText, BorderLayout.CENTER);
        setContentPane(mainPanel);

        showNextMessage();


        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isAnimatingText) {
                    completeTextAnimation();
                } else {
                    showNextMessage();
                }
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (textAnimationTimer != null) {
                    textAnimationTimer.stop();
                }
                parentFrame.setVisible(true);
            }
        });

        setVisible(true);
    }

    private ImageIcon loadImageIcon(String path) throws Exception {
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            throw new Exception("No se pudo cargar la imagen: " + path);
        }
        try {
            return new ImageIcon(ImageIO.read(stream));
        } catch (Exception e) {
            throw new Exception("Error leyendo la imagen: " + path, e);
        }
    }

    private void showNextMessage() {
        if (currentMessageIndex < mensajes.length) {
            fullText = mensajes[currentMessageIndex];
            currentText = new StringBuilder();
            isAnimatingText = true;
            arrowLabel.setVisible(false);

            if (textAnimationTimer != null) {
                textAnimationTimer.stop();
            }

            textAnimationTimer = new Timer(40, e -> {
                if (currentText.length() < fullText.length()) {
                    currentText.append(fullText.charAt(currentText.length()));
                    dialogText.setText(currentText.toString());
                } else {
                    ((Timer) e.getSource()).stop();
                    isAnimatingText = false;
                    arrowLabel.setVisible(true);
                }
            });
            textAnimationTimer.start();

            currentMessageIndex++;
        } else {
            if (textAnimationTimer != null) {
                textAnimationTimer.stop();
            }
            dispose();

            try {
                new ModalidadesJuego(parentFrame);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error al mostrar la pantalla de modalidades", ex);
                JOptionPane.showMessageDialog(parentFrame,
                        "Error al cargar las modalidades de juego: " + ex.getMessage());
                parentFrame.setVisible(true);
            }
        }
    }

    private void completeTextAnimation() {
        if (isAnimatingText) {
            textAnimationTimer.stop();
            dialogText.setText(fullText);
            isAnimatingText = false;
            arrowLabel.setVisible(true);
        }
    }
}
