package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.net.URL;

import Domain.*;


public class SeleccionPokemon extends JFrame {

    private final Color COLOR_FONDO = new Color(40, 120, 200);
    private final Color COLOR_GRID = new Color(60, 150, 220);
    private final Color COLOR_BORDE_SELECCION = new Color(255, 215, 0);
    private final Color COLOR_PLAYER1 = new Color(255, 50, 50); // Rojo para jugador 1
    private final Color COLOR_PLAYER2 = new Color(50, 50, 255); // Azul para jugador 2


    private String modoJuego;
    private boolean modoSupervivencia;
    private Entrenador entrenadorP1;
    private Entrenador entrenadorP2;
    private int currentPlayer = 1;
    private int pokemonSeleccionadosP1 = 0;
    private int pokemonSeleccionadosP2 = 0;
    private int maxPokemonPorJugador = 3; // Puede variar según el modo


    private ArrayList<Pokemon> pokemonesDisponibles;
    private ArrayList<Pokemon> pokemonesSeleccionadosP1;
    private ArrayList<Pokemon> pokemonesSeleccionadosP2;


    private JLabel fondoGif;
    private JPanel mainPanel, gridPanel, equipoPanel, detallePanel;
    private JLabel headerLabel, instructionLabel;
    private JLabel imagenGrande, nombreLabel, tipoLabel, nivelLabel, statsLabel;
    private JButton btnAgregar, btnContinuar, btnVolver;

    /**
     * @param parentFrame El JFrame padre
     * @param modoJuego El modo de juego elegido
     * @param modoSupervivencia Si está activado el modo supervivencia
     * @param entrenadorP1 El entrenador elegido por el jugador 1
     * @param entrenadorP2 El entrenador elegido por el jugador 2 (puede ser null en modo vs CPU)
     */
    public SeleccionPokemon(JFrame parentFrame, String modoJuego, boolean modoSupervivencia,
                            Entrenador entrenadorP1, Entrenador entrenadorP2) {
        super("Selección de Pokémon - POOBkemon");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        this.modoJuego = modoJuego;
        this.modoSupervivencia = modoSupervivencia;
        this.entrenadorP1 = entrenadorP1;
        this.entrenadorP2 = entrenadorP2;


        if (modoJuego.equals("Torneo")) {
            maxPokemonPorJugador = 6;
        } else if (modoJuego.equals("1 vs 1")) {
            maxPokemonPorJugador = 3;
        } else if (modoJuego.equals("vs CPU")) {
            maxPokemonPorJugador = 4;
        }


        pokemonesDisponibles = new ArrayList<>();
        pokemonesSeleccionadosP1 = new ArrayList<>();
        pokemonesSeleccionadosP2 = new ArrayList<>();


        cargarPokemonesDisponibles();


        setupUI();


        actualizarUIParaJugadorActual();
    }


    private void cargarPokemonesDisponibles() {
        String basePath = "/Presentation/Recursos/Pokemons/";

        // Crear Pokémon de ejemplo de diferentes tipos
        // Fuego
        pokemonesDisponibles.add(new PokemonFuego("Charizard", basePath + "Charizard2.gif", 100, 297, 224, 210, 259));
        pokemonesDisponibles.add(new PokemonFuego("Vulpix", basePath + "Vulpix.gif", 100, 217, 129, 182, 134));

        // Agua
        pokemonesDisponibles.add(new PokemonAgua("Squirtle", basePath + "Squirtle.gif", 100, 229, 145, 182, 134));
        pokemonesDisponibles.add(new PokemonAgua("Blastoise", basePath + "Blastoise.gif", 100, 299, 222, 259, 211));

        // Planta
        pokemonesDisponibles.add(new PokemonPlanta("Bulbasaur", basePath + "Bulbasau.gif", 100, 231, 147, 147, 138));
        pokemonesDisponibles.add(new PokemonPlanta("Treecko", basePath + "Treecko.gif", 100, 221, 138, 116, 193));

        // Eléctrico
        pokemonesDisponibles.add(new PokemonElectrico("Pikachu", basePath + "Pikachu3.gif", 100, 211, 160, 127, 237));
        pokemonesDisponibles.add(new PokemonElectrico("Manectric", basePath + "Macnetric2.gif", 100, 281, 204, 171, 270));

        // Bicho
        pokemonesDisponibles.add(new PokemonBicho("Caterpie", basePath + "Caterpie.gif", 100, 231, 105, 116, 138));
        pokemonesDisponibles.add(new PokemonBicho("Chikorita", basePath + "Chikorita.gif", 100, 231, 147, 182, 138));

        // Acero
        pokemonesDisponibles.add(new PokemonAcero("Magnemite", basePath + "Magnemite.gif", 100, 191, 116, 193, 138));
        pokemonesDisponibles.add(new PokemonAcero("Skarmory", basePath + "Skarmory.gif", 100, 271, 215, 347, 193));

        // Psíquico
        pokemonesDisponibles.add(new PokemonPsiquico("Mewtwo", basePath + "Mewtwo.gif", 100, 353, 281, 237, 325));
        pokemonesDisponibles.add(new PokemonPsiquico("Slowking", basePath + "Slowking.gif", 100, 331, 204, 215, 105));

        // Tierra
        pokemonesDisponibles.add(new PokemonTierra("Sandshrew", basePath + "Sandshrew.gif", 100, 241, 204, 226, 127));
        pokemonesDisponibles.add(new PokemonTierra("Onix", basePath + "Onix.gif", 100, 211, 138, 391, 193));

        // Hada
        pokemonesDisponibles.add(new PokemonHada("Togepi", basePath + "Togepi.gif", 100, 211, 83, 182, 83));
        pokemonesDisponibles.add(new PokemonHada("Mawile", basePath + "Mawile.gif", 100, 241, 226, 226, 149));

        // Veneno
        pokemonesDisponibles.add(new PokemonVeneno("Swalot", basePath + "Swalot.gif", 100, 341, 260, 221, 160));
        pokemonesDisponibles.add(new PokemonVeneno("Venusaur", basePath + "Venusaur.gif", 100, 301, 220, 222, 215));

        // Lucha
        pokemonesDisponibles.add(new PokemonLucha("Hitmonchan", basePath + "Hitmonchan.gif", 100, 241, 270, 213, 206));
        pokemonesDisponibles.add(new PokemonLucha("Hariyama", basePath + "Hariyama.gif", 100, 429, 303, 171, 149));

        // Dragón
        pokemonesDisponibles.add(new PokemonDragon("Rayquaza", basePath + "Rayquaza2.gif", 100, 351, 369, 237, 248));
        pokemonesDisponibles.add(new PokemonDragon("Latios", basePath + "Latios3.gif", 100, 301, 149, 259, 149));

        // Hielo
        pokemonesDisponibles.add(new PokemonHielo("Regice", basePath + "Regice.gif", 100, 301, 149, 259, 149));
        pokemonesDisponibles.add(new PokemonHielo("Cloyster", basePath + "Cloyster.gif", 100, 241, 248, 435, 193));

        // Siniestro
        pokemonesDisponibles.add(new PokemonSiniestro("Grimer de Alola", basePath + "Grimer de Alola.gif", 100, 301, 215, 149, 94));
        pokemonesDisponibles.add(new PokemonSiniestro("Meowth de Alola", basePath + "Meowth de Alola.gif", 100, 221, 138, 116, 237));

        // Volador
        pokemonesDisponibles.add(new PokemonVolador("Moltres", basePath + "Moltres.gif", 100, 321, 259, 237, 237));
        pokemonesDisponibles.add(new PokemonVolador("Spearow", basePath + "Spearow.gif", 100, 221, 171, 105, 193));

        // Fantasma
        pokemonesDisponibles.add(new PokemonFantasma("Gengar", basePath + "Gengar.gif", 100, 261, 182, 171, 281));
        pokemonesDisponibles.add(new PokemonFantasma("Gastly", basePath + "Gastly.gif", 100, 201, 116, 105, 215));

        //Roca
        pokemonesDisponibles.add(new PokemonRoca("Rhyhorn", basePath + "Rhyhorn.gif", 100, 301, 226, 248, 94));
        pokemonesDisponibles.add(new PokemonRoca("Geodude", basePath + "Geodude.gif", 100, 221, 215, 259, 83));

        //Normal
        pokemonesDisponibles.add(new PokemonNormal("Raticate", basePath + "Raticate.gif", 100, 251, 217, 171, 253));
        pokemonesDisponibles.add(new PokemonNormal("Ursaring", basePath + "Ursaring.gif", 100, 321, 325, 204, 160));



    }

    private boolean estaPokemonSeleccionado(Pokemon pokemon) {
        return pokemonesSeleccionadosP1.contains(pokemon) ||
                pokemonesSeleccionadosP2.contains(pokemon);
    }


    private void seleccionarPokemon(Pokemon pokemon) {
        // Limpiar el panel de detalles
        detallePanel.removeAll();


        JPanel imagenPanel = new JPanel(new BorderLayout());
        imagenPanel.setOpaque(false);
        imagenPanel.setPreferredSize(new Dimension(300, 300));

        try {

            URL imgURL = getClass().getResource(pokemon.getImagePath());
            if (imgURL != null) {

                ImageIcon originalIcon = new ImageIcon(imgURL);
                imagenGrande = new JLabel(originalIcon);
                imagenGrande.setHorizontalAlignment(SwingConstants.CENTER);
                imagenGrande.setBorder(BorderFactory.createLineBorder(getColorForCurrentPlayer(), 3));
            } else {

                System.err.println("No se pudo encontrar la imagen: " + pokemon.getImagePath());
                imagenGrande = new JLabel("Imagen no disponible");
                imagenGrande.setFont(new Font("Arial", Font.BOLD, 16));
            }
            imagenPanel.add(imagenGrande, BorderLayout.CENTER);
        } catch (Exception e) {

            System.err.println("Error cargando imagen para " + pokemon.getNombre() + ": " + e.getMessage());
            imagenGrande = new JLabel("Error de imagen");
            imagenGrande.setFont(new Font("Arial", Font.BOLD, 16));
            imagenPanel.add(imagenGrande, BorderLayout.CENTER);
        }


        nombreLabel = new JLabel(pokemon.getNombre());
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 26));
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tipoLabel = new JLabel("Tipo: " + pokemon.getTipo());
        tipoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tipoLabel.setForeground(Color.WHITE);
        tipoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nivelLabel = new JLabel("Nivel: " + pokemon.getNivel());
        nivelLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nivelLabel.setForeground(Color.WHITE);
        nivelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsLabel = new JLabel("<html><div style='text-align:center;'>" +
                "HP: " + pokemon.getHp() + "<br>" +
                "Ataque: " + pokemon.getAtaque() + "<br>" +
                "Defensa: " + pokemon.getDefensa() + "<br>" +
                "Velocidad: " + pokemon.getVelocidad() +
                "</div></html>");
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statsLabel.setForeground(Color.WHITE);
        statsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 3. Botones
        btnAgregar = new JButton("AGREGAR AL EQUIPO");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgregar.setBackground(getColorForCurrentPlayer());
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.setMaximumSize(new Dimension(250, 50));
        btnAgregar.setPreferredSize(new Dimension(250, 50));
        btnAgregar.setEnabled(!estaPokemonSeleccionado(pokemon));
        btnAgregar.addActionListener(e -> agregarPokemonAlEquipo());

        btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 18));
        btnContinuar.setBackground(new Color(50, 180, 50));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setMaximumSize(new Dimension(250, 50));
        btnContinuar.setPreferredSize(new Dimension(250, 50));
        btnContinuar.setEnabled(false);
        btnContinuar.addActionListener(e -> continuarABatalla());


        detallePanel.setLayout(new BoxLayout(detallePanel, BoxLayout.Y_AXIS));
        detallePanel.add(imagenPanel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detallePanel.add(nombreLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detallePanel.add(tipoLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detallePanel.add(nivelLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detallePanel.add(statsLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        detallePanel.add(btnAgregar);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detallePanel.add(btnContinuar);
        detallePanel.add(Box.createVerticalGlue());


        detallePanel.revalidate();
        detallePanel.repaint();
    }


    private JPanel crearCartaPokemon(Pokemon pokemon) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                g2d.setColor(new Color(245, 232, 220));
                g2d.fillRect(0, 0, getWidth(), getHeight());


                boolean yaSeleccionado = pokemonesSeleccionadosP1.contains(pokemon) ||
                        pokemonesSeleccionadosP2.contains(pokemon);

                if (yaSeleccionado) {
                    // Aplicar efecto de deshabilitado si ya fue seleccionado
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                }
            }
        };
        card.setLayout(new BorderLayout());

        card.setPreferredSize(new Dimension(150, 130));
        card.setMinimumSize(new Dimension(150, 130));
        card.setMaximumSize(new Dimension(150, 130));


        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)
        ));


        JLabel imagen = new JLabel();
        imagen.setPreferredSize(new Dimension(140, 120));
        imagen.setHorizontalAlignment(SwingConstants.CENTER);
        imagen.setVerticalAlignment(SwingConstants.CENTER);

        try {

            URL imgURL = getClass().getResource(pokemon.getImagePath());
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);


                if (!pokemon.getImagePath().toLowerCase().endsWith(".gif")) {
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(120, 100, java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newImg);
                }

                imagen.setIcon(icon);
            } else {
                System.err.println("No se pudo encontrar la imagen: " + pokemon.getImagePath());
                imagen.setText(pokemon.getNombre().substring(0, 1));
                imagen.setFont(new Font("Arial", Font.BOLD, 36));
                imagen.setForeground(Color.BLACK);
            }
        } catch (Exception e) {
            imagen.setText(pokemon.getNombre().substring(0, 1));
            imagen.setFont(new Font("Arial", Font.BOLD, 36));
            imagen.setForeground(Color.BLACK);
            System.err.println("Error cargando imagen para " + pokemon.getNombre() + ": " + e.getMessage());
        }


        imagen.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        imagePanel.add(imagen, BorderLayout.CENTER);

        card.add(imagePanel, BorderLayout.CENTER);


        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(true);
        infoPanel.setBackground(new Color(0, 100, 0, 180)); // Fondo verde semitransparente

        JLabel nameLabel = new JLabel(pokemon.getNombre(), SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE); // Texto blanco para mejor contraste
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente ajustada al nuevo tamaño
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Espacio para el texto

        infoPanel.add(nameLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);


        if (!estaPokemonSeleccionado(pokemon)) {
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionarPokemon(pokemon);
                }


                @Override
                public void mouseEntered(MouseEvent e) {
                    card.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    card.setBorder(BorderFactory.createLineBorder(new Color(50, 150, 50), 3));
                }
            });
        }

        return card;
    }


    private void agregarPokemonAlEquipo() {
        // Obtener el Pokémon seleccionado actualmente
        Pokemon pokemonSeleccionado = null;
        String nombrePokemon = nombreLabel.getText();


        for (Pokemon p : pokemonesDisponibles) {
            if (p.getNombre().equals(nombrePokemon)) {
                pokemonSeleccionado = p;
                break;
            }
        }

        // Verificar si se encontró un Pokémon y no está ya seleccionado
        if (pokemonSeleccionado != null && !estaPokemonSeleccionado(pokemonSeleccionado)) {
            // Agregar al equipo según el jugador actual
            if (currentPlayer == 1) {
                if (pokemonSeleccionadosP1 < maxPokemonPorJugador) {
                    pokemonesSeleccionadosP1.add(pokemonSeleccionado);
                    pokemonSeleccionadosP1++;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Ya has seleccionado el máximo de Pokémon permitidos.",
                            "Equipo completo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                if (pokemonSeleccionadosP2 < maxPokemonPorJugador) {
                    pokemonesSeleccionadosP2.add(pokemonSeleccionado);
                    pokemonSeleccionadosP2++;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Ya has seleccionado el máximo de Pokémon permitidos.",
                            "Equipo completo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            btnAgregar.setEnabled(false);


            // Refrescar la grid para mostrar el Pokémon como seleccionado
            gridPanel.revalidate();
            gridPanel.repaint();

            // Verificar si el equipo está completo
            verificarEquipoCompleto();
        }
    }


    /**
     * Configura la interfaz gráfica con la nueva organización
     * y mejoras para mostrar los 36 Pokémon disponibles
     */
    private void setupUI() {
        // Panel principal
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        try {
            URL gifURL = getClass().getResource("/Presentation/Recursos/fonpoke.gif");
            if (gifURL != null) {
                ImageIcon gifIcon = new ImageIcon(gifURL);
                Image scaledImage = gifIcon.getImage().getScaledInstance(1200, 700, Image.SCALE_DEFAULT);
                ImageIcon scaledGifIcon = new ImageIcon(scaledImage);
                fondoGif = new JLabel(scaledGifIcon);
                fondoGif.setLayout(new BorderLayout());
                setContentPane(fondoGif);


                addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        Image resizedImage = gifIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
                        fondoGif.setIcon(new ImageIcon(resizedImage));
                    }
                });
            } else {
                System.err.println("No se pudo encontrar el GIF de fondo");
                setContentPane(mainPanel);
                mainPanel.setBackground(COLOR_FONDO);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar el GIF de fondo: " + e.getMessage());
            setContentPane(mainPanel);
            mainPanel.setBackground(COLOR_FONDO);
        }


        JPanel contentPanel = new JPanel(new BorderLayout(10, 0));
        contentPanel.setOpaque(false);



        gridPanel = new JPanel(new GridLayout(0, 6, 10, 10));

        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2), "Pokémon Disponibles",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16), Color.WHITE
        ));


        for (Pokemon pokemon : pokemonesDisponibles) {
            JPanel card = crearCartaPokemon(pokemon);
            gridPanel.add(card);
        }


        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setPreferredSize(new Dimension(950, 600));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);



        detallePanel = new JPanel();
        detallePanel.setOpaque(false);
        detallePanel.setLayout(new BoxLayout(detallePanel, BoxLayout.Y_AXIS));
        detallePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        detallePanel.setPreferredSize(new Dimension(280, 0));


        imagenGrande = new JLabel();
        imagenGrande.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagenGrande.setPreferredSize(new Dimension(250, 250)); // Mucho más grande
        imagenGrande.setMaximumSize(new Dimension(250, 250)); // Mucho más grande


        nombreLabel = new JLabel("Selecciona un Pokémon");
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 26)); // Más grande
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tipoLabel = new JLabel("");
        tipoLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Más grande
        tipoLabel.setForeground(Color.WHITE);
        tipoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nivelLabel = new JLabel("Nivel: ");
        nivelLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Más grande
        nivelLabel.setForeground(Color.WHITE);
        nivelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsLabel = new JLabel("<html>HP: <br>Ataque: <br>Defensa: <br>Velocidad: </html>");
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Más grande
        statsLabel.setForeground(Color.WHITE);
        statsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        btnAgregar = new JButton("AGREGAR AL EQUIPO");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 18)); // Más grande
        btnAgregar.setBackground(getColorForCurrentPlayer());
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.setMaximumSize(new Dimension(250, 50)); // Botón más grande
        btnAgregar.setPreferredSize(new Dimension(250, 50)); // Botón más grande
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(e -> agregarPokemonAlEquipo());


        btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 18)); // Más grande
        btnContinuar.setBackground(new Color(50, 180, 50));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setMaximumSize(new Dimension(250, 50)); // Botón más grande
        btnContinuar.setPreferredSize(new Dimension(250, 50)); // Botón más grande
        btnContinuar.setEnabled(false);
        btnContinuar.addActionListener(e -> continuarABatalla());


        detallePanel.add(Box.createVerticalGlue());
        detallePanel.add(imagenGrande);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detallePanel.add(nombreLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        detallePanel.add(tipoLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detallePanel.add(nivelLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        detallePanel.add(statsLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        detallePanel.add(btnAgregar);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detallePanel.add(btnContinuar);
        detallePanel.add(Box.createVerticalGlue());


        headerLabel = new JLabel("Selección de Pokémon - Jugador 1", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Más grande
        headerLabel.setForeground(getColorForCurrentPlayer());
        headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 25, 0));


        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        instructionLabel = new JLabel("Selecciona tu equipo de " + maxPokemonPorJugador + " Pokémon", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Más grande
        instructionLabel.setForeground(Color.WHITE);

        btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16)); // Más grande
        btnVolver.setPreferredSize(new Dimension(120, 40)); // Botón más grande
        btnVolver.addActionListener(e -> volverASeleccionEntrenador());

        bottomPanel.add(instructionLabel, BorderLayout.CENTER);
        bottomPanel.add(btnVolver, BorderLayout.WEST);


        JComponent contentPane = fondoGif != null ? fondoGif : mainPanel;
        contentPane.add(headerLabel, BorderLayout.NORTH);
        contentPane.add(contentPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);


        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(detallePanel, BorderLayout.EAST);


        setPreferredSize(new Dimension(1400, 850));
        pack();
        setLocationRelativeTo(null);
    }

    private void verificarEquipoCompleto() {
        int cantidadActual = currentPlayer == 1 ? pokemonSeleccionadosP1 : pokemonSeleccionadosP2;

        if (cantidadActual == maxPokemonPorJugador) {

            btnContinuar.setEnabled(true);


            JOptionPane.showMessageDialog(this,
                    "¡Has completado tu equipo de " + maxPokemonPorJugador + " Pokémon!",
                    "Equipo Completo", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private Color getColorForCurrentPlayer() {
        return currentPlayer == 1 ? COLOR_PLAYER1 : COLOR_PLAYER2;
    }


    private void actualizarUIParaJugadorActual() {
        // Actualizar título
        headerLabel.setText("Selección de Pokémon - " +
                (currentPlayer == 1 ? "Entrenador " + entrenadorP1.getNombre() :
                        "Entrenador " + entrenadorP2.getNombre()));
        headerLabel.setForeground(getColorForCurrentPlayer());


        imagenGrande.setIcon(null);
        nombreLabel.setText("Selecciona un Pokémon");
        tipoLabel.setText("");
        nivelLabel.setText("Nivel: ");
        statsLabel.setText("<html>HP: <br>Ataque: <br>Defensa: <br>Velocidad: </html>");
        btnAgregar.setEnabled(false);
        btnAgregar.setBackground(getColorForCurrentPlayer());
        btnContinuar.setEnabled(false);



        instructionLabel.setText("Selecciona tu equipo de " + maxPokemonPorJugador + " Pokémon");


        revalidate();
        repaint();
    }


    private void continuarABatalla() {
        if (modoJuego.equals("1 vs 1") && currentPlayer == 1) {
            // Si estamos en modo 1vs1 y es el jugador 1, pasar al jugador 2
            currentPlayer = 2;
            actualizarUIParaJugadorActual();
        } else {
            // Transición a la pantalla de batalla
            iniciarBatalla();
        }
    }


    private void iniciarBatalla() {
        // Mensaje de confirmación
        StringBuilder mensaje = new StringBuilder("¡Equipos seleccionados!\n\n");

        mensaje.append("Entrenador ").append(entrenadorP1.getNombre()).append(":\n");
        for (Pokemon p : pokemonesSeleccionadosP1) {
            mensaje.append("- ").append(p.getNombre()).append(" (").append(p.getTipo()).append(")\n");
        }

        if (modoJuego.equals("1 vs 1")) {
            mensaje.append("\nEntrenador ").append(entrenadorP2.getNombre()).append(":\n");
            for (Pokemon p : pokemonesSeleccionadosP2) {
                mensaje.append("- ").append(p.getNombre()).append(" (").append(p.getTipo()).append(")\n");
            }
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "¡Equipos completos!", JOptionPane.INFORMATION_MESSAGE);

        // Abrir la ventana de selección de items
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame == null) parentFrame = new JFrame();

        new SeleccionItems(
                parentFrame,
                modoJuego,
                modoSupervivencia,
                entrenadorP1,
                entrenadorP2,
                pokemonesSeleccionadosP1,
                pokemonesSeleccionadosP2
        ).setVisible(true);

        dispose();
    }


    private void volverASeleccionEntrenador() {
        dispose();
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame == null) parentFrame = new JFrame();
        new SeleccionEntrenador(parentFrame, modoJuego, modoSupervivencia).setVisible(true);
    }

}