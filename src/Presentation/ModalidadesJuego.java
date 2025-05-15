package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ModalidadesJuego extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(ModalidadesJuego.class.getName());
    private final JFrame parentFrame;

    public ModalidadesJuego(JFrame parentFrame) throws Exception {
        super("POOBkemon - Modalidades de Juego");
        this.parentFrame = parentFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));


        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            private ImageIcon backgroundIcon;

            {
                try {
                    java.net.URL gifUrl = getClass().getResource("Recursos/Fondmod.gif");
                    if (gifUrl == null) {
                        throw new Exception("No se pudo encontrar el GIF de fondo en Recursos/Fondmod.gif");
                    }
                    backgroundIcon = new ImageIcon(gifUrl);
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "No se pudo cargar el GIF de fondo", e);
                    backgroundIcon = null;
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundIcon != null) {
                    // Dibuja el GIF animado escalado al tamaño del panel
                    Image img = backgroundIcon.getImage();
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } else {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    RadialGradientPaint radialGradient = new RadialGradientPaint(
                            new Point(getWidth() / 2, getHeight() / 2),
                            getWidth(),
                            new float[]{0.0f, 0.5f, 1.0f},
                            new Color[]{
                                    new Color(255, 215, 0),
                                    new Color(255, 165, 0),
                                    new Color(255, 140, 0)
                            }
                    );

                    g2d.setPaint(radialGradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());

                    g2d.setColor(new Color(255, 255, 255, 70));
                    int numRays = 20;
                    int centerX = getWidth() / 2;
                    int centerY = getHeight() / 2;

                    for (int i = 0; i < numRays; i++) {
                        double angle = Math.toRadians(i * (360.0 / numRays));
                        int endX = (int) (centerX + Math.cos(angle) * getWidth());
                        int endY = (int) (centerY + Math.sin(angle) * getHeight());
                        g2d.drawLine(centerX, centerY, endX, endY);
                    }
                }
            }
        };
        backgroundPanel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundPanel, Integer.valueOf(0));


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setOpaque(false);
        contentPanel.setBounds(0, 0, 800, 600);
        layeredPane.add(contentPanel, Integer.valueOf(1));


        try {
            ImageIcon logoIcon = loadImageIcon("Recursos/POOBKEMON-23-4-2025.png");
            if (logoIcon.getImage() != null) {
                Image scaledLogo = logoIcon.getImage().getScaledInstance(400, -1, Image.SCALE_SMOOTH);
                JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
                logoLabel.setBounds(200, 20, 400, 130);
                contentPanel.add(logoLabel);
            } else {
                JLabel titleLabel = new JLabel("POOBkemon");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                titleLabel.setBounds(150, 20, 500, 100);
                contentPanel.add(titleLabel);
            }
        } catch (Exception e) {
            JLabel titleLabel = new JLabel("POOBkemon");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setBounds(150, 20, 500, 100);
            contentPanel.add(titleLabel);
        }

        int buttonSize = 130;
        int buttonSpacing = 150;
        int startX = (800 - (3 * buttonSize + 2 * (buttonSpacing - buttonSize))) / 2;
        int bottomY = 420;

        createModeButton(contentPanel, "1 vs 1", "Juega contra otro jugador", startX, bottomY, "Recursos/1vs1.png", buttonSize);
        createModeButton(contentPanel, "IA vs 1", "Enfrenta a la máquina", startX + buttonSpacing, bottomY, "Recursos/1vsia.png", buttonSize);
        createModeButton(contentPanel, "IA vs IA", "Observa una batalla entre IAs", startX + 2 * buttonSpacing, bottomY, "Recursos/iavsia.png", buttonSize);

        setContentPane(layeredPane);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parentFrame.setVisible(true);
            }
        });

        setVisible(true);
    }

    private void createModeButton(JPanel panel, String title, String description, int x, int y, String imagePath, int buttonSize) {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        buttonContainer.setOpaque(false);
        buttonContainer.setBounds(x, y, buttonSize, buttonSize + 50);

        try {
            ImageIcon modeIcon = loadImageIcon(imagePath);
            if (modeIcon.getImage() != null) {
                Image scaledImage = modeIcon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
                JButton button = new JButton(new ImageIcon(scaledImage));
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                final Image originalImage = scaledImage;
                final Image largerImage = modeIcon.getImage().getScaledInstance(buttonSize + 10, buttonSize + 10, Image.SCALE_SMOOTH);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        button.setIcon(new ImageIcon(largerImage));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.setIcon(new ImageIcon(originalImage));
                    }
                });

                button.addActionListener(e -> launchGameMode(title));

                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttonContainer.add(button);
            } else {
                createFallbackButton(buttonContainer, title, description, buttonSize);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "No se pudo cargar la imagen para la modalidad " + title, e);
            createFallbackButton(buttonContainer, title, description, buttonSize);
        }

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonContainer.add(titleLabel);

        panel.add(buttonContainer);
    }

    private void createFallbackButton(JPanel container, String title, String description, int buttonSize) {
        JButton fallbackButton = new JButton();
        fallbackButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
        fallbackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        fallbackButton.setBackground(new Color(200, 0, 0));
        fallbackButton.setForeground(Color.WHITE);

        fallbackButton.setLayout(new BorderLayout());
        JPanel circlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(200, 0, 0));
                g2d.fillArc(5, 5, getWidth()-10, getHeight()-10, 0, 180);

                g2d.setColor(Color.WHITE);
                g2d.fillArc(5, 5, getWidth()-10, getHeight()-10, 180, 180);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(5, getHeight()/2, getWidth()-5, getHeight()/2);
                g2d.drawOval(5, 5, getWidth()-10, getHeight()-10);

                g2d.setColor(Color.WHITE);
                g2d.fillOval(getWidth()/3, getHeight()/3, getWidth()/3, getHeight()/3);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(getWidth()/3, getHeight()/3, getWidth()/3, getHeight()/3);
            }
        };
        circlePanel.setOpaque(false);
        fallbackButton.add(circlePanel, BorderLayout.CENTER);

        fallbackButton.setContentAreaFilled(false);
        fallbackButton.setFocusPainted(false);
        fallbackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        fallbackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                circlePanel.setBackground(new Color(230, 0, 0, 50));
                circlePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                circlePanel.setBackground(new Color(0, 0, 0, 0));
                circlePanel.repaint();
            }
        });

        fallbackButton.addActionListener(e -> launchGameMode(title));

        fallbackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(fallbackButton);
    }

    private void launchGameMode(String mode) {
        setVisible(false);

        Object[] options = {"Modo Normal", "Modo Supervivencia"};

        UIManager.put("OptionPane.background", new Color(255, 140, 0));
        UIManager.put("Panel.background", new Color(255, 165, 0));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        JOptionPane optionPane = new JOptionPane(
                "Selecciona el tipo de juego:",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                options[0]
        );

        JDialog dialog = optionPane.createDialog(this, "POOBkemon - Tipo de Juego");
        dialog.setVisible(true);

        Object selectedValue = optionPane.getValue();

        if (selectedValue == null) {
            setVisible(true);
            return;
        }

        String gameType = selectedValue.toString();
        boolean isSurvivalMode = gameType.equals("Modo Supervivencia");

        dispose();

        try {
            SeleccionEntrenador seleccionEntrenador = new SeleccionEntrenador(parentFrame, mode, isSurvivalMode);
            seleccionEntrenador.setVisible(true);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar el modo " + mode, e);
            JOptionPane.showMessageDialog(parentFrame, "Error al iniciar el modo de juego: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            parentFrame.setVisible(true);
        }
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
}


