package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Domain.*;

public class SeleccionItems extends JFrame {

    private final Color COLOR_FONDO = new Color(40, 120, 200);
    private final Color COLOR_PLAYER1 = new Color(255, 50, 50);
    private final Color COLOR_PLAYER2 = new Color(50, 50, 255);


    private final int MAX_POCION = 2;       // Máximo 2 Pociones normales
    private final int MAX_SUPERPOCION = 2;  // Máximo 2 Superpociones
    private final int MAX_HIPERPOCION = 2;  // Máximo 2 Hiperpociones
    private final int MAX_REVIVIR = 1;      // Máximo 1 Revivir


    private String modoJuego;
    private boolean modoSupervivencia;
    private Entrenador entrenadorP1;
    private Entrenador entrenadorP2;
    private int currentPlayer = 1;


    private ArrayList<Item> itemsDisponibles;
    private List<Item> itemsSeleccionadosP1;
    private List<Item> itemsSeleccionadosP2;


    private JLabel fondoGif;
    private JPanel mainPanel, itemsPanel, detallePanel;
    private JLabel headerLabel, instructionLabel;
    private JLabel imagenGrande, nombreLabel, descripcionLabel;
    private JButton btnAgregar, btnContinuar, btnVolver;
    private JLabel selectedItemsLabel;
    private Item itemSeleccionado;

    private ArrayList<Pokemon> pokemonesP1;
    private ArrayList<Pokemon> pokemonesP2;

    public SeleccionItems(JFrame parentFrame, String modoJuego, boolean modoSupervivencia,
                          Entrenador entrenadorP1, Entrenador entrenadorP2,
                          ArrayList<Pokemon> pokemonesP1, ArrayList<Pokemon> pokemonesP2) {
        super("Selección de Items - POOBkemon");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);

        this.modoJuego = modoJuego;
        this.modoSupervivencia = modoSupervivencia;
        this.entrenadorP1 = entrenadorP1;
        this.entrenadorP2 = entrenadorP2;

        this.pokemonesP1 = pokemonesP1;
        this.pokemonesP2 = pokemonesP2;

        this.itemsSeleccionadosP1 = new ArrayList<>();
        this.itemsSeleccionadosP2 = new ArrayList<>();

        cargarItemsDisponibles();
        setupUI();
        actualizarUIParaJugadorActual();
    }

    private void cargarItemsDisponibles() {
        String basePath = "src/Presentation/Recursos/Items/";
        itemsDisponibles = new ArrayList<>();
        itemsDisponibles.add(new Potion(basePath + "Pocion.png"));
        itemsDisponibles.add(new SuperPotion(basePath + "Superpocion.png"));
        itemsDisponibles.add(new HyperPotion(basePath + "Hiperpocion.png"));
        itemsDisponibles.add(new Revive(basePath + "Revivir.png"));
    }

    private void setupUI() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        try {
            ImageIcon gifIcon = new ImageIcon("src/Presentation/Recursos/fonpoke.gif");
            Image scaledImage = gifIcon.getImage().getScaledInstance(1200, 750, Image.SCALE_DEFAULT);
            fondoGif = new JLabel(new ImageIcon(scaledImage));
            fondoGif.setLayout(new BorderLayout());
            setContentPane(fondoGif);
        } catch (Exception e) {
            setContentPane(mainPanel);
            mainPanel.setBackground(COLOR_FONDO);
        }

        JPanel contentPanel = new JPanel(new BorderLayout(10, 0));
        contentPanel.setOpaque(false);


        itemsPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        itemsPanel.setOpaque(false);
        itemsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2), "Items Disponibles",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16), Color.WHITE));

        for (Item item : itemsDisponibles) {
            itemsPanel.add(crearCartaItem(item));
        }


        detallePanel = new JPanel();
        detallePanel.setOpaque(false);
        detallePanel.setLayout(new BoxLayout(detallePanel, BoxLayout.Y_AXIS));
        detallePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        imagenGrande = new JLabel();
        imagenGrande.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagenGrande.setPreferredSize(new Dimension(200, 200));

        nombreLabel = new JLabel("Selecciona un item");
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 26));
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        descripcionLabel = new JLabel("<html>Selecciona un item para ver su descripción.</html>");
        descripcionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descripcionLabel.setForeground(Color.WHITE);
        descripcionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnAgregar = new JButton("AGREGAR ITEM");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgregar.setBackground(getColorForCurrentPlayer());
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAgregar.addActionListener(e -> agregarItem());

        selectedItemsLabel = new JLabel();
        selectedItemsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedItemsLabel.setForeground(Color.WHITE);
        selectedItemsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 18));
        btnContinuar.setBackground(new Color(50, 180, 50));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.addActionListener(e -> continuarABatalla());

        detallePanel.add(imagenGrande);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detallePanel.add(nombreLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detallePanel.add(descripcionLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        detallePanel.add(btnAgregar);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detallePanel.add(selectedItemsLabel);
        detallePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        detallePanel.add(btnContinuar);


        headerLabel = new JLabel("Selección de Items - Jugador 1", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(getColorForCurrentPlayer());


        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        instructionLabel = new JLabel();
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> volverASeleccionPokemon());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnVolver);
        buttonPanel.add(btnContinuar);

        bottomPanel.add(instructionLabel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);


        JComponent contentPane = fondoGif != null ? fondoGif : mainPanel;
        contentPane.add(headerLabel, BorderLayout.NORTH);
        contentPane.add(contentPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        contentPanel.add(itemsPanel, BorderLayout.CENTER);
        contentPanel.add(detallePanel, BorderLayout.EAST);

        setPreferredSize(new Dimension(1200, 750));
        pack();
    }

    private JPanel crearCartaItem(Item item) {
        JPanel card = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(245, 232, 220));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        card.setPreferredSize(new Dimension(250, 200));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));


        JLabel imagen = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(item.getImagePath());
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imagen.setText(item.getNombre().substring(0, 1));
            imagen.setFont(new Font("Arial", Font.BOLD, 36));
        }
        imagen.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel nameLabel = new JLabel(item.getNombre(), SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(0, 100, 0, 180));
        infoPanel.add(nameLabel, BorderLayout.CENTER);

        card.add(imagen, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarItem(item);
                card.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
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

        return card;
    }

    private void seleccionarItem(Item item) {
        itemSeleccionado = item;

        try {
            ImageIcon icon = new ImageIcon(item.getImagePath());
            Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            imagenGrande.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imagenGrande.setText(item.getNombre().substring(0, 1));
        }

        nombreLabel.setText(item.getNombre());
        descripcionLabel.setText("<html>" + item.getDescripcion() + "</html>");


        List<Item> itemsJugador = getItemsJugadorActual();
        boolean puedeAgregar = false;

        if (item instanceof Potion) {
            long count = itemsJugador.stream().filter(i -> i instanceof Potion).count();
            puedeAgregar = count < MAX_POCION;
        }
        else if (item instanceof SuperPotion) {
            long count = itemsJugador.stream().filter(i -> i instanceof SuperPotion).count();
            puedeAgregar = count < MAX_SUPERPOCION;
        }
        else if (item instanceof HyperPotion) {
            long count = itemsJugador.stream().filter(i -> i instanceof HyperPotion).count();
            puedeAgregar = count < MAX_HIPERPOCION;
        }
        else if (item instanceof Revive) {
            long count = itemsJugador.stream().filter(i -> i instanceof Revive).count();
            puedeAgregar = count < MAX_REVIVIR;
        }

        btnAgregar.setEnabled(puedeAgregar);
    }

    private void agregarItem() {
        if (itemSeleccionado == null) return;

        List<Item> itemsJugador = getItemsJugadorActual();
        Item nuevoItem = crearNuevaInstanciaItem(itemSeleccionado);

        if (nuevoItem != null) {
            itemsJugador.add(nuevoItem);
            actualizarListaItems();
            actualizarContadores();

            JOptionPane.showMessageDialog(this,
                    "¡" + nuevoItem.getNombre() + " agregado!",
                    "Item Agregado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Item crearNuevaInstanciaItem(Item item) {
        if (item instanceof Potion) {
            return new Potion(item.getImagePath());
        } else if (item instanceof SuperPotion) {
            return new SuperPotion(item.getImagePath());
        } else if (item instanceof HyperPotion) {
            return new HyperPotion(item.getImagePath());
        } else if (item instanceof Revive) {
            return new Revive(item.getImagePath());
        }
        return null;
    }

    private void actualizarListaItems() {
        List<Item> itemsJugador = getItemsJugadorActual();
        Map<String, Integer> contador = new HashMap<>();

        for (Item item : itemsJugador) {
            contador.put(item.getNombre(), contador.getOrDefault(item.getNombre(), 0) + 1);
        }

        StringBuilder sb = new StringBuilder("<html><div style='text-align:center;'>Items seleccionados:<br>");
        if (contador.isEmpty()) {
            sb.append("Ninguno");
        } else {
            contador.forEach((nombre, cantidad) ->
                    sb.append("• ").append(nombre).append(": ").append(cantidad).append("<br>"));
        }
        sb.append("</div></html>");

        selectedItemsLabel.setText(sb.toString());
    }

    private void actualizarContadores() {
        List<Item> itemsJugador = getItemsJugadorActual();

        long pociones = itemsJugador.stream().filter(i -> i instanceof Potion).count();
        long superPociones = itemsJugador.stream().filter(i -> i instanceof SuperPotion).count();
        long hiperPociones = itemsJugador.stream().filter(i -> i instanceof HyperPotion).count();
        long revivir = itemsJugador.stream().filter(i -> i instanceof Revive).count();

        instructionLabel.setText("<html>Límites por jugador:<br>" +
                "• Pociones: " + pociones + "/" + MAX_POCION + "<br>" +
                "• Superpociones: " + superPociones + "/" + MAX_SUPERPOCION + "<br>" +
                "• Hiperpociones: " + hiperPociones + "/" + MAX_HIPERPOCION + "<br>" +
                "• Revivir: " + revivir + "/" + MAX_REVIVIR + "</html>");
    }

    private void continuarABatalla() {
        if (modoJuego.equals("1 vs 1") && currentPlayer == 1 && entrenadorP2 != null) {
            currentPlayer = 2;
            actualizarUIParaJugadorActual();
            itemSeleccionado = null;

            JOptionPane.showMessageDialog(this,
                    "¡Turno del Jugador 2 para seleccionar items!",
                    "Cambio de Jugador", JOptionPane.INFORMATION_MESSAGE);
        } else {
            iniciarBatalla();
        }
    }

    private void iniciarBatalla() {
        // Confirmación al usuario
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas iniciar la batalla con los items seleccionados?",
                "Iniciar Batalla",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {

            System.out.println("Iniciando batalla con equipos de tamaño: " +
                    pokemonesP1.size() + " y " + pokemonesP2.size());
            System.out.println("Items seleccionados: " + itemsSeleccionadosP1.size());

            try {

                if (pokemonesP1 == null || pokemonesP1.isEmpty()) {
                    throw new Exception("Equipo Jugador 1 no inicializado correctamente.");
                }
                if (pokemonesP2 == null || pokemonesP2.isEmpty()) {
                    throw new Exception("Equipo Jugador 2 no inicializado correctamente.");
                }


                for (Pokemon p : pokemonesP1) {
                    if (p.getNombre() == null || p.getNombre().isEmpty()) {
                        p.setNombre("Pokemon" + (int)(Math.random() * 100));
                    }


                    if (p.getAtaques() == null || p.getAtaques().isEmpty()) {
                        p.cargarAtaquesPorDefecto();
                    }
                }

                for (Pokemon p : pokemonesP2) {
                    if (p.getNombre() == null || p.getNombre().isEmpty()) {
                        p.setNombre("Pokemon" + (int)(Math.random() * 100));
                    }

                    if (p.getAtaques() == null || p.getAtaques().isEmpty()) {
                        p.cargarAtaquesPorDefecto();
                    }
                }


                SwingUtilities.invokeLater(() -> {
                    try {

                        if (pokemonesP1 == null || pokemonesP1.isEmpty() ||
                                pokemonesP2 == null || pokemonesP2.isEmpty()) {
                            throw new IllegalArgumentException("Los equipos de Pokémon están vacíos");
                        }


                        System.out.println("Equipo P1: " + pokemonesP1.size() + " Pokemon");
                        System.out.println("Primer Pokemon P1: " + pokemonesP1.get(0).getNombre());
                        System.out.println("Equipo P2: " + pokemonesP2.size() + " Pokemon");
                        System.out.println("Primer Pokemon P2: " + pokemonesP2.get(0).getNombre());


                        BatallaPokemon batalla = new BatallaPokemon(
                                pokemonesP1,
                                pokemonesP2,
                                itemsSeleccionadosP1
                        );



                        System.out.println("Ventana de batalla creada y debería ser visible");
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(
                                null,
                                "Error al iniciar la batalla: " + e.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                });



                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error al iniciar la batalla: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void agregarInfoItems(StringBuilder sb, List<Item> items) {
        sb.append("• Items: ");
        if (items.isEmpty()) {
            sb.append("Ninguno\n");
        } else {
            Map<String, Integer> contador = new HashMap<>();
            items.forEach(item ->
                    contador.put(item.getNombre(), contador.getOrDefault(item.getNombre(), 0) + 1));

            contador.forEach((nombre, cantidad) ->
                    sb.append(nombre).append(" (").append(cantidad).append("), "));

            sb.delete(sb.length()-2, sb.length()).append("\n");
        }
    }

    private void actualizarUIParaJugadorActual() {
        String nombreJugador = (currentPlayer == 1) ? entrenadorP1.getNombre() : entrenadorP2.getNombre();
        headerLabel.setText("Selección de Items - " + nombreJugador);
        headerLabel.setForeground(getColorForCurrentPlayer());

        btnAgregar.setBackground(getColorForCurrentPlayer());
        actualizarListaItems();
        actualizarContadores();
    }

    private List<Item> getItemsJugadorActual() {
        return (currentPlayer == 1) ? itemsSeleccionadosP1 : itemsSeleccionadosP2;
    }

    private Color getColorForCurrentPlayer() {
        return (currentPlayer == 1) ? COLOR_PLAYER1 : COLOR_PLAYER2;
    }

    private void volverASeleccionPokemon() {
        int result = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas volver? Se perderán los items seleccionados.",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            dispose();
            new SeleccionPokemon(null, modoJuego, modoSupervivencia, entrenadorP1, entrenadorP2).setVisible(true);
        }
    }
}