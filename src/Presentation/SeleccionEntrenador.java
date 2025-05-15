package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Domain.*;

public class SeleccionEntrenador extends JFrame {
    private ArrayList<Entrenador> entrenadores;
    private Entrenador entrenadorSeleccionadoP1;  // Entrenador para jugador 1
    private Entrenador entrenadorSeleccionadoP2;  // Entrenador para jugador 2
    private int currentPlayer = 1; // Para rastrear qué jugador está seleccionando (1 o 2)

    private JPanel mainPanel, gridPanel, detallePanel;
    private JLabel imagenGrande, nombreLabel, especialidadLabel, nivelLabel, instructionLabel;
    private JButton btnConfirmar;
    private final Color COLOR_FONDO = new Color(40, 120, 200);
    private final Color COLOR_GRID = new Color(60, 150, 220);
    private final Color COLOR_BORDE_SELECCION = new Color(255, 215, 0);
    private final Color COLOR_PLAYER1 = new Color(255, 50, 50); // Rojo para jugador 1
    private final Color COLOR_PLAYER2 = new Color(50, 50, 255); // Azul para jugador 2


    private String modoJuego;
    private boolean modoSupervivencia;
    private JLabel fondoGif;


    public SeleccionEntrenador(JFrame parentFrame, String modoJuego, boolean modoSupervivencia) {
        super("Selecciona tu Entrenador - POOBkemon");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // parámetros
        this.modoJuego = modoJuego;
        this.modoSupervivencia = modoSupervivencia;


        inicializarEntrenadores();


        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout());


        try {
            ImageIcon gifIcon = new ImageIcon(getClass().getResource("Recursos/Entreinte.gif"));
            fondoGif = new JLabel(gifIcon);
            fondoGif.setLayout(new BorderLayout());
            setContentPane(fondoGif);

            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    Image resizedImage = gifIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
                    fondoGif.setIcon(new ImageIcon(resizedImage));
                }
            });
        } catch (Exception e) {
            System.err.println("Error al cargar el GIF de fondo: " + e.getMessage());
            setContentPane(mainPanel);
        }


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);


        actualizarHeaderParaJugador(headerPanel);

        if (fondoGif != null) {
            fondoGif.add(headerPanel, BorderLayout.NORTH);
        } else {
            mainPanel.add(headerPanel, BorderLayout.NORTH);
        }


        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setOpaque(false);


        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new GridLayout(2, 3, 15, 15));


        for (Entrenador entrenador : entrenadores) {
            JPanel card = crearMiniCartaEntrenador(entrenador);
            leftPanel.add(card);
        }

        // Panel para botones adicionales
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonesPanel.setOpaque(false);

        JButton randomButton = new JButton("RANDOM");
        randomButton.setFont(new Font("Arial", Font.BOLD, 12));
        randomButton.setBackground(new Color(200, 200, 0));
        randomButton.setForeground(Color.BLACK);
        randomButton.addActionListener(e -> seleccionarEntrenadorAleatorio());
        botonesPanel.add(randomButton);

        // Panel derecho: Detalle del entrenador seleccionado
        detallePanel = new JPanel();
        detallePanel.setOpaque(false);
        detallePanel.setLayout(new BoxLayout(detallePanel, BoxLayout.Y_AXIS));
        detallePanel.setPreferredSize(new Dimension(350, 0));

        imagenGrande = new JLabel();
        imagenGrande.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagenGrande.setPreferredSize(new Dimension(300, 300));
        imagenGrande.setMaximumSize(new Dimension(300, 300));

        nombreLabel = new JLabel("Selecciona un entrenador");
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        nombreLabel.setForeground(getColorForCurrentPlayer());
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        especialidadLabel = new JLabel("");
        especialidadLabel.setFont(new Font("Arial", Font.BOLD, 22));
        especialidadLabel.setForeground(Color.WHITE);
        especialidadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nivelLabel = new JLabel("Nivel: ");
        nivelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nivelLabel.setForeground(Color.WHITE);
        nivelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnConfirmar = new JButton("CONFIRMAR");
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 16));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setBackground(getColorForCurrentPlayer());
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.setMaximumSize(new Dimension(200, 40));
        btnConfirmar.addActionListener(e -> confirmarSeleccion());
        btnConfirmar.setEnabled(false);

        detallePanel.add(Box.createVerticalGlue());
        detallePanel.add(imagenGrande);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detallePanel.add(nombreLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        detallePanel.add(especialidadLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detallePanel.add(nivelLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detallePanel.add(btnConfirmar);
        detallePanel.add(Box.createVerticalGlue());

        instructionLabel = new JLabel("Select Player 1's character", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        instructionLabel.setForeground(getColorForCurrentPlayer());

        JPanel fullLeftPanel = new JPanel(new BorderLayout());
        fullLeftPanel.setOpaque(false);
        fullLeftPanel.add(leftPanel, BorderLayout.CENTER);
        fullLeftPanel.add(botonesPanel, BorderLayout.SOUTH);

        contentPanel.add(fullLeftPanel, BorderLayout.CENTER);
        contentPanel.add(detallePanel, BorderLayout.EAST);
        contentPanel.add(instructionLabel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> volverAModalidades(parentFrame));
        bottomPanel.add(backButton, BorderLayout.WEST);

        if (fondoGif != null) {
            fondoGif.add(contentPanel, BorderLayout.CENTER);
            fondoGif.add(bottomPanel, BorderLayout.SOUTH);
        } else {
            mainPanel.add(contentPanel, BorderLayout.CENTER);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        }
    }

    private void actualizarHeaderParaJugador(JPanel headerPanel) {
        headerPanel.removeAll();

        String playerTitle = "Character Select - Player " + currentPlayer;
        Color playerColor = getColorForCurrentPlayer();

        JLabel titulo = new JLabel(playerTitle, SwingConstants.RIGHT);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(playerColor);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
        headerPanel.add(titulo, BorderLayout.CENTER);

        JLabel infoModo = new JLabel("Modo: " + modoJuego + (modoSupervivencia ? " (Supervivencia)" : " (Normal)"));
        infoModo.setFont(new Font("Arial", Font.BOLD, 14));
        infoModo.setForeground(Color.WHITE);
        infoModo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        headerPanel.add(infoModo, BorderLayout.SOUTH);

        headerPanel.revalidate();
        headerPanel.repaint();
    }

    private Color getColorForCurrentPlayer() {
        return currentPlayer == 1 ? COLOR_PLAYER1 : COLOR_PLAYER2;
    }

    public SeleccionEntrenador(JFrame parentFrame) {
        this(parentFrame, "Normal", false);
    }

    private void inicializarEntrenadores() {
        entrenadores = new ArrayList<>();

        String[] nombres = {"guay.png", "Chiconinja.png", "Damisela.png", "DomaDragon.png", "Fogonero.png", "Dama_parasol.png"};
        String[] nombresEntrenador = {"Guay", "Chico Ninja", "Damisela", "DomaDragón", "Fogonero", "Dama Parasol"};
        String[] especialidades = {"Cooltrainer", "Ninja-Boy", "Lady", "Dragon Tramer", "Kindler", "Parasol lady"};
        int nivelInicial = 5;

        for (int i = 0; i < nombres.length; i++) {
            String imagePath = "Recursos/" + nombres[i];
            entrenadores.add(new Entrenador(nombresEntrenador[i], especialidades[i], imagePath, nivelInicial, especialidades[i]));
        }
    }

    private JPanel crearMiniCartaEntrenador(Entrenador entrenador) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(COLOR_GRID);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                g2d.setColor(getColorForCurrentPlayer());
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 15, 15);

                if ((currentPlayer == 2 && entrenador.equals(entrenadorSeleccionadoP1)) ||
                        (currentPlayer == 1 && entrenador.equals(entrenadorSeleccionadoP2))) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    g2d.setColor(Color.BLACK);
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                }
            }
        };
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(120, 120));
        card.setOpaque(false);
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel imagen = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(entrenador.getImagePath()));
            Image scaled = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(scaled));
            imagen.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {
            imagen.setText(entrenador.getNombre().substring(0, 1));
            imagen.setFont(new Font("Arial", Font.BOLD, 24));
            imagen.setForeground(Color.WHITE);
            imagen.setHorizontalAlignment(SwingConstants.CENTER);
            System.err.println("Error cargando imagen para " + entrenador.getNombre() + ": " + e.getMessage());
        }
        card.add(imagen, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel(entrenador.getNombre(), SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        card.add(nameLabel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPlayer == 2 && entrenador.equals(entrenadorSeleccionadoP1)) {
                    JOptionPane.showMessageDialog(SeleccionEntrenador.this,
                            "Este entrenador ya fue seleccionado por el Jugador 1.\nElija otro entrenador.",
                            "Entrenador no disponible", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (currentPlayer == 1 && entrenador.equals(entrenadorSeleccionadoP2)) {
                    JOptionPane.showMessageDialog(SeleccionEntrenador.this,
                            "Este entrenador ya fue seleccionado por el Jugador 2.\nElija otro entrenador.",
                            "Entrenador no disponible", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                seleccionarEntrenador(card, entrenador);
            }
        });

        return card;
    }

    private void seleccionarEntrenador(JPanel card, Entrenador entrenador) {
        if (currentPlayer == 1) {
            entrenadorSeleccionadoP1 = entrenador;
        } else {
            entrenadorSeleccionadoP2 = entrenador;
        }

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(entrenador.getImagePath()));
            Image scaled = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            imagenGrande.setIcon(new ImageIcon(scaled));
            imagenGrande.setText(null);
        } catch (Exception e) {
            imagenGrande.setIcon(null);
            imagenGrande.setText(entrenador.getNombre());
            imagenGrande.setFont(new Font("Arial", Font.BOLD, 48));
            imagenGrande.setForeground(Color.WHITE);
            imagenGrande.setHorizontalAlignment(SwingConstants.CENTER);
            System.err.println("Error cargando imagen grande para " + entrenador.getNombre() + ": " + e.getMessage());
        }

        nombreLabel.setText(entrenador.getNombre());
        especialidadLabel.setText(entrenador.getEspecialidad());
        nivelLabel.setText("Nivel: " + entrenador.getNivelInicial());

        btnConfirmar.setEnabled(true);

        Toolkit.getDefaultToolkit().beep();
    }

    private void seleccionarEntrenadorAleatorio() {
        ArrayList<Entrenador> entrenadoresDisponibles = new ArrayList<>(entrenadores);

        if (modoJuego.equals("1 vs 1")) {
            if (currentPlayer == 1 && entrenadorSeleccionadoP2 != null) {
                entrenadoresDisponibles.remove(entrenadorSeleccionadoP2);
            } else if (currentPlayer == 2 && entrenadorSeleccionadoP1 != null) {
                entrenadoresDisponibles.remove(entrenadorSeleccionadoP1);
            }
        }

        int randomIndex = (int)(Math.random() * entrenadoresDisponibles.size());
        Entrenador randomEntrenador = entrenadoresDisponibles.get(randomIndex);

        seleccionarEntrenador(null, randomEntrenador);
    }

    private void confirmarSeleccion() {
        if (currentPlayer == 1) {
            if (entrenadorSeleccionadoP1 != null) {
                if (modoJuego.equals("1 vs 1")) {
                    currentPlayer = 2;
                    actualizarInterfazParaNuevoJugador();
                } else {
                    finalizarSeleccionEntrenadores();
                }
            }
        } else {
            if (entrenadorSeleccionadoP2 != null) {
                finalizarSeleccionEntrenadores();
            }
        }
    }

    private void actualizarInterfazParaNuevoJugador() {

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);


        String playerTitle = "Character Select - Player " + currentPlayer;
        Color playerColor = getColorForCurrentPlayer();

        JLabel titulo = new JLabel(playerTitle, SwingConstants.RIGHT);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(playerColor);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
        headerPanel.add(titulo, BorderLayout.CENTER);


        JLabel infoModo = new JLabel("Modo: " + modoJuego + (modoSupervivencia ? " (Supervivencia)" : " (Normal)"));
        infoModo.setFont(new Font("Arial", Font.BOLD, 14));
        infoModo.setForeground(Color.WHITE);
        infoModo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        headerPanel.add(infoModo, BorderLayout.SOUTH);


        if (fondoGif != null) {
            fondoGif.remove(0); // Remover el encabezado antiguo
            fondoGif.add(headerPanel, BorderLayout.NORTH);
        } else {
            mainPanel.remove(0); // Remover el encabezado antiguo
            mainPanel.add(headerPanel, BorderLayout.NORTH);
        }

        imagenGrande.setIcon(null);
        nombreLabel.setText("Selecciona un entrenador");
        nombreLabel.setForeground(getColorForCurrentPlayer());
        especialidadLabel.setText("");
        nivelLabel.setText("Nivel: ");
        btnConfirmar.setEnabled(false);
        btnConfirmar.setBackground(getColorForCurrentPlayer());

        instructionLabel.setText("Select Player 2's character");
        instructionLabel.setForeground(getColorForCurrentPlayer());

        headerPanel.revalidate();
        headerPanel.repaint();


        revalidate();
        repaint();
    }



    private void finalizarSeleccionEntrenadores() {
        String mensaje;
        if (modoJuego.equals("1 vs 1")) {
            mensaje = "¡Selección completa!\n" +
                    "Jugador 1: " + entrenadorSeleccionadoP1.getNombre() + "\n" +
                    "Jugador 2: " + entrenadorSeleccionadoP2.getNombre() + "\n" +
                    "Modo de juego: " + modoJuego + " (" + (modoSupervivencia ? "Supervivencia" : "Normal") + ")";
        } else {
            mensaje = "¡Has seleccionado a " + entrenadorSeleccionadoP1.getNombre() + "!" +
                    "\nModo de juego: " + modoJuego +
                    "\nModo " + (modoSupervivencia ? "Supervivencia" : "Normal");
        }

        JOptionPane.showMessageDialog(this, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);

        dispose();
        new SeleccionPokemon(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                modoJuego,
                modoSupervivencia,
                entrenadorSeleccionadoP1,
                entrenadorSeleccionadoP2
        ).setVisible(true);
    }

    private void volverAModalidades(JFrame parentFrame) {
        dispose();
        try {
            new ModalidadesJuego(parentFrame).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al volver: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            parentFrame.setVisible(true);
        }
    }

    public Entrenador getEntrenadorSeleccionadoP1() {
        return entrenadorSeleccionadoP1;
    }

    public Entrenador getEntrenadorSeleccionadoP2() {
        return entrenadorSeleccionadoP2;
    }

    public String getModoJuego() {
        return modoJuego;
    }

    public boolean isModoSupervivencia() {
        return modoSupervivencia;
    }
}

