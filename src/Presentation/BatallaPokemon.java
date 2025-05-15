package Presentation;

import Domain.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class BatallaPokemon extends JFrame {

    private Pokemon pokemonActualP1;
    private Pokemon pokemonActualP2;
    private List<Pokemon> equipoP1;
    private List<Pokemon> equipoP2;
    private List<Item> itemsP1;


    private JPanel panelBatalla;
    private JPanel panelMenuPrincipal;
    private JPanel panelInfoEnemigo;
    private JPanel panelInfoAliado;
    private JPanel panelOpciones;

    private JProgressBar barraVidaAliado, barraVidaEnemigo;
    private JTextArea textoDialogo;
    private JLabel spriteAliado, spriteEnemigo;

    private final Color COLOR_FONDO = new Color(184, 248, 144);
    private final Color COLOR_MENU = new Color(248, 248, 248);
    private final Color COLOR_BORDE = new Color(56, 56, 56);
    private final Color COLOR_TEXTO = new Color(72, 72, 72);
    private final Color COLOR_DIALOGO = new Color(240, 240, 240);
    private final Color COLOR_BOTON_HOVER = new Color(255, 216, 80);

    private ImageIcon fondoBatalla;
    private Map<String, ImageIcon> spritesPokemon;

    public BatallaPokemon(List<Pokemon> equipoP1, List<Pokemon> equipoP2, List<Item> itemsP1) {
        super("POOBkemon - Batalla");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);  // Establecer tamaño inicial
        setResizable(false);

        try {
            if (equipoP1 == null || equipoP1.isEmpty() || equipoP2 == null || equipoP2.isEmpty()) {
                throw new IllegalArgumentException("Los equipos no están correctamente inicializados.");
            }

            this.equipoP1 = equipoP1;
            this.equipoP2 = equipoP2;
            this.itemsP1 = itemsP1 != null ? itemsP1 : new ArrayList<>();

            this.pokemonActualP1 = equipoP1.get(0);
            this.pokemonActualP2 = equipoP2.get(0);

            // Asegurarnos que los pokemon tienen ataques
            if (pokemonActualP1.getAtaques() == null || pokemonActualP1.getAtaques().isEmpty()) {
                System.out.println("Cargando ataques por defecto para Pokemon 1");
                pokemonActualP1.cargarAtaquesPorDefecto();
            }
            if (pokemonActualP2.getAtaques() == null || pokemonActualP2.getAtaques().isEmpty()) {
                System.out.println("Cargando ataques por defecto para Pokemon 2");
                pokemonActualP2.cargarAtaquesPorDefecto();
            }

            cargarImagenes();
            inicializarUI();

            // Centrar la ventana antes de hacerla visible
            setLocationRelativeTo(null);

            // Hacer visible la ventana al final
            setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al inicializar batalla: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void cargarImagenes() {
        try {

            fondoBatalla = new ImageIcon(getClass().getResource("/Presentation/Recursos/FondoBatalla.jpg"));
            if (fondoBatalla == null || fondoBatalla.getIconWidth() == -1) {
                System.out.println("No se pudo cargar la imagen de fondo");
                fondoBatalla = null;
            }

            spritesPokemon = new HashMap<>();

            // Fuego
            cargarSpritePokemon("Charizard", "Charizardfron.gif", "Charizardbackk.gif");
            cargarSpritePokemon("Vulpix", "Vulpixfron.gif", "Vulpixback.gif");

            // Agua
            cargarSpritePokemon("Squirtle", "Squirtlefron.gif", "Squirtleback.gif");
            cargarSpritePokemon("Blastoise", "Blastoisefron.gif", "Blastoiseback.gif");

            // Planta
            cargarSpritePokemon("Bulbasaur", "Bulbasaurfron.gif", "Bulbasaurback.gif");
            cargarSpritePokemon("Treecko", "treeckofron.gif", "treeckoback.gif");

            // Eléctrico
            cargarSpritePokemon("Pikachu", "pikachufron.gif", "pikachuback.gif");
            cargarSpritePokemon("Manectric", "Manectricfron.gif", "Manectricback.gif");

            // Bicho
            cargarSpritePokemon("Caterpie", "caterpiefron.gif", "caterpieback.gif");
            cargarSpritePokemon("Chikorita", "chikoritafron.gif", "chikoritaback.gif");

            // Acero
            cargarSpritePokemon("Magnemite", "magnemitefron.gif", "magnemiteback.gif");
            cargarSpritePokemon("Skarmory", "skarmoryfron.gif", "skarmoryback.gif");

            // Psíquico
            cargarSpritePokemon("Mewtwo", "Mewtwofron.gif", "Mewtwoback.gif");
            cargarSpritePokemon("Slowking", "slowkingfron.gif", "slowkingback.gif");

            // Tierra
            cargarSpritePokemon("Sandshrew", "sandshrewfron.gif", "sandshrewback.gif");
            cargarSpritePokemon("Onix", "onixfron.gif", "onixback.gif");

            // Hada
            cargarSpritePokemon("Togepi", "togepifron.gif", "togepiback.gif");
            cargarSpritePokemon("Mawile", "mawilefron.gif", "mawileback.gif");

            // Veneno
            cargarSpritePokemon("Swalot", "swalotfron.gif", "swalotback.gif");
            cargarSpritePokemon("Venusaur", "venusaurfron.gif", "venusaurback.gif");

            // Lucha
            cargarSpritePokemon("Hitmonchan", "hitmonchanfron.gif", "hitmonchanback.gif");
            cargarSpritePokemon("Hariyama", "hariyamafron.gif", "hariyamaback.gif");

            // Dragón
            cargarSpritePokemon("Rayquaza", "rayquazafron.gif", "rayquazaback.gif");
            cargarSpritePokemon("Latios", "latiosfron.gif", "latiosback.gif");

            // Hielo
            cargarSpritePokemon("Regice", "regicefron.gif", "regiceback.gif");
            cargarSpritePokemon("Cloyster", "cloysterfron.gif", "cloysterback.gif");

            // Siniestro
            cargarSpritePokemon("Grimer de Alola", "grimeralolafron.gif", "grimeralola​back.gif");
            cargarSpritePokemon("Meowth de Alola", "meowthalolafront.gif", "meowthalolaback.gif");

            // Volador
            cargarSpritePokemon("Moltres", "moltresfron.gif", "moltresback.gif");
            cargarSpritePokemon("Spearow", "spearowfron.gif", "spearowback.gif");

            // Fantasma
            cargarSpritePokemon("Gengar", "gengarfron.gif", "gengarback.gif");
            cargarSpritePokemon("Gastly", "gastlyfron.gif", "gastlyback.gif");

            // Roca
            cargarSpritePokemon("Rhyhorn", "rhyhornfron.gif", "rhyhornback.gif");
            cargarSpritePokemon("Geodude", "geodudefron.gif", "geodudeback.gif");

            // Normal
            cargarSpritePokemon("Raticate", "raticatefron.gif", "raticateback.gif");
            cargarSpritePokemon("Ursaring", "ursaringfron.gif", "ursaringback.gif");


            if (spritesPokemon.isEmpty()) {
                System.out.println("No se pudo cargar ningún sprite, usando nombres genéricos");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imágenes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     * @param nombrePokemon El nombre del Pokémon para las claves del mapa
     * @param archivoFrontal Nombre del archivo GIF frontal
     * @param archivoTrasero Nombre del archivo GIF trasero
     */
    private void cargarSpritePokemon(String nombrePokemon, String archivoFrontal, String archivoTrasero) {
        try {
            // Cargar sprite frontal
            ImageIcon spriteFrontal = new ImageIcon(getClass().getResource("/Presentation/Recursos/Sprites/" + archivoFrontal));
            if (spriteFrontal != null && spriteFrontal.getIconWidth() != -1) {
                spritesPokemon.put(nombrePokemon + "_front", spriteFrontal);
                System.out.println("Sprite " + nombrePokemon + "_front cargado correctamente");
            } else {
                System.out.println("Error al cargar el sprite frontal de " + nombrePokemon);
            }

            // Cargar sprite trasero
            ImageIcon spriteTrasero = new ImageIcon(getClass().getResource("/Presentation/Recursos/Sprites/" + archivoTrasero));
            if (spriteTrasero != null && spriteTrasero.getIconWidth() != -1) {
                spritesPokemon.put(nombrePokemon + "_back", spriteTrasero);
                System.out.println("Sprite " + nombrePokemon + "_back cargado correctamente");
            } else {
                System.out.println("Error al cargar el sprite trasero de " + nombrePokemon);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar sprites de " + nombrePokemon + ": " + e.getMessage());
        }
    }



    private void inicializarUI() {
        // Configuración básica de la ventana
        setTitle("POOBkemon - Batalla");

        // Crear panel principal con layout null para posicionamiento absoluto
        panelBatalla = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondoBatalla != null) {
                    g.drawImage(fondoBatalla.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(COLOR_FONDO);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        setContentPane(panelBatalla);

        // Crear los componentes de la UI
        crearPanelInfoEnemigo();
        crearPanelInfoAliado();
        crearPanelOpciones();

        spriteEnemigo = new JLabel();
        spriteEnemigo.setBounds(520, 160, 200, 200); // Izquierda centro
        cargarSprite(spriteEnemigo, pokemonActualP2.getNombre(), true);

        spriteAliado = new JLabel();
        spriteAliado.setBounds(80, 300, 200, 200); // Derecha centro
        cargarSprite(spriteAliado, pokemonActualP1.getNombre(), false);

        panelBatalla.add(spriteEnemigo);
        panelBatalla.add(spriteAliado);


        // Panel de opciones - centrado abajo
        panelOpciones.setBounds(470, 480, 280, 80);

        // Mostrar la interfaz
        mostrarMensaje("¿Qué debería hacer " + pokemonActualP1.getNombre() + "?");

        // Asegurar que todos los componentes estén correctamente posicionados
        panelBatalla.revalidate();
        panelBatalla.repaint();
    }



    private void cargarSprite(JLabel label, String nombrePokemon, boolean esEnemigo) {
        String clave = nombrePokemon + (esEnemigo ? "_front" : "_back");
        System.out.println("Intentando cargar sprite con clave: " + clave);

        // Verificar si existe el sprite
        if (spritesPokemon.containsKey(clave)) {
            label.setIcon(spritesPokemon.get(clave));
            System.out.println("Sprite cargado correctamente: " + clave);
        } else {
            System.out.println("Sprite no encontrado: " + clave + ", usando placeholder");
            crearPlaceholderSprite(label, nombrePokemon);
        }
    }

    private void crearPlaceholderSprite(JLabel label, String nombrePokemon) {
        int width = 120;
        int height = 120;
        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagen.createGraphics();

        g.setColor(new Color(60, 60, 60, 180));
        g.fillOval(width/4, height/4, width/2, height/2);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(nombrePokemon);
        g.drawString(nombrePokemon, (width - textWidth) / 2, height * 3/4);

        g.dispose();
        label.setIcon(new ImageIcon(imagen));
    }

    private void crearPanelInfoEnemigo() {
        panelInfoEnemigo = crearPanelInfoPokemon(pokemonActualP2, true);
        panelInfoEnemigo.setBounds(400, 20, 220, 70); // Arriba a la derecha
        panelBatalla.add(panelInfoEnemigo);
    }

    private void crearPanelInfoAliado() {
        panelInfoAliado = crearPanelInfoPokemon(pokemonActualP1, false);
        panelInfoAliado.setBounds(40, 200, 250, 70); // Abajo a la izquierda
        panelBatalla.add(panelInfoAliado);
    }

    private JPanel crearPanelInfoPokemon(Pokemon pokemon, boolean esEnemigo) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(COLOR_MENU);
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D)g).fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

                g.setColor(COLOR_BORDE);
                ((Graphics2D)g).draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 20, 20));
            }
        };
        panel.setOpaque(false);

        JLabel lblNombreNivel = new JLabel(pokemon.getNombre().toUpperCase() + "  Nv" + pokemon.getNivel());
        lblNombreNivel.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombreNivel.setForeground(COLOR_TEXTO);
        lblNombreNivel.setBounds(20, 15, 200, 20);

        JLabel lblGenero = new JLabel(esEnemigo ? "♀" : "♂");
        lblGenero.setFont(new Font("Arial", Font.BOLD, 16));
        lblGenero.setForeground(esEnemigo ? Color.PINK : Color.BLUE);
        lblGenero.setBounds(210, 15, 20, 20);

        JLabel lblPS = new JLabel("PS");
        lblPS.setFont(new Font("Arial", Font.BOLD, 14));
        lblPS.setForeground(COLOR_TEXTO);
        lblPS.setBounds(20, 45, 20, 15);

        JProgressBar barraHP = new JProgressBar(0, pokemon.getHpMax());
        barraHP.setValue(pokemon.getHp());
        barraHP.setForeground(getColorHP(pokemon.getHp(), pokemon.getHpMax()));
        barraHP.setBackground(Color.LIGHT_GRAY);
        barraHP.setBorderPainted(false);
        barraHP.setBounds(45, 45, 150, 12);

        JLabel lblHPValor = new JLabel(pokemon.getHp() + "/" + pokemon.getHpMax());
        lblHPValor.setFont(new Font("Arial", Font.PLAIN, 12));
        lblHPValor.setForeground(COLOR_TEXTO);
        lblHPValor.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHPValor.setBounds(200, 45, 80, 15);
        panel.add(lblHPValor);

        if (esEnemigo) {
            barraVidaEnemigo = barraHP;
        } else {
            barraVidaAliado = barraHP;
        }

        panel.add(lblNombreNivel);
        panel.add(lblGenero);
        panel.add(lblPS);
        panel.add(barraHP);

        return panel;
    }


    private void crearPanelOpciones() {
        panelOpciones = new JPanel(new GridLayout(2, 2, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(COLOR_MENU);
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D)g).fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                g.setColor(COLOR_BORDE);
                ((Graphics2D)g).draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));
            }
        };
        panelOpciones.setBounds(470, 480, 280, 80);
        panelOpciones.setOpaque(false);
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] opciones = {"LUCHA", "MOCHILA", "POKéMON", "HUIDA"};
        for (String opcion : opciones) {
            JButton btn = crearBotonEstiloPokemon(opcion);
            btn.addActionListener(e -> manejarAccion(opcion));
            panelOpciones.add(btn);
        }

        panelBatalla.add(panelOpciones);
    }

    private JButton crearBotonEstiloPokemon(String texto) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(COLOR_BOTON_HOVER);
                } else if (getModel().isRollover()) {
                    g.setColor(COLOR_BOTON_HOVER);
                } else {
                    g.setColor(COLOR_MENU);
                }
                g.fillRect(0, 0, getWidth(), getHeight());

                if (getModel().isRollover() || getModel().isPressed()) {
                    g.setColor(COLOR_BORDE);
                    int[] xPoints = {10, 20, 10};
                    int[] yPoints = {getHeight()/2 - 5, getHeight()/2, getHeight()/2 + 5};
                    g.fillPolygon(xPoints, yPoints, 3);
                }

                super.paintComponent(g);
            }
        };

        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(COLOR_TEXTO);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                boton.repaint();
            }
        });

        return boton;
    }

    private void manejarAccion(String accion) {
        switch (accion) {
            case "LUCHA" -> mostrarMenuAtaques();
            case "MOCHILA" -> mostrarMenuMochila();
            case "POKéMON" -> mostrarMenuPokemon();
            case "HUIDA" -> intentarHuida();
        }
    }

    private void mostrarMenuAtaques() {
        panelOpciones.setVisible(false);

        JPanel panelAtaques = new JPanel(new GridLayout(2, 2, 5, 5)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(COLOR_MENU);
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D)g).fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                g.setColor(COLOR_BORDE);
                ((Graphics2D)g).draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));
            }
        };
        panelAtaques.setBounds(470, 480, 280, 80);
        panelAtaques.setOpaque(false);
        panelAtaques.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        List<Ataque> ataques = pokemonActualP1.getAtaques();
        String[] opcionesAtaque = new String[4];
        for (int i = 0; i < 4; i++) {
            if (i < ataques.size()) {
                opcionesAtaque[i] = ataques.get(i).getNombre();
            } else {
                opcionesAtaque[i] = i < 2 ? "Ataque " + (i+1) : "Defensa " + (i-1);
            }

            final int indice = i;
            JButton btn = crearBotonEstiloPokemon(opcionesAtaque[i]);
            btn.addActionListener(e -> {
                if (indice < 2) {
                    realizarAtaque(indice);
                } else {
                    activarDefensa(indice - 2);
                }
                panelBatalla.remove(panelAtaques);
                panelOpciones.setVisible(true);
                repaint();
            });
            panelAtaques.add(btn);
        }

        JButton btnVolver = crearBotonEstiloPokemon("VOLVER");
        btnVolver.addActionListener(e -> {
            panelBatalla.remove(panelAtaques);
            panelOpciones.setVisible(true);
            repaint();
        });

        panelBatalla.add(panelAtaques);
        panelBatalla.revalidate();
        repaint();
    }

    private void mostrarMenuMochila() {
        if (itemsP1 == null || itemsP1.isEmpty()) {
            mostrarMensaje("¡No tienes objetos en la mochila!");
            return;
        }

        panelOpciones.setVisible(false);

        JPanel panelItems = new JPanel(new GridLayout(0, 1, 5, 5)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(COLOR_MENU);
                ((Graphics2D)g).fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                g.setColor(COLOR_BORDE);
                ((Graphics2D)g).draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));
            }
        };
        panelItems.setBounds(470, 480, 280, 80);
        panelItems.setOpaque(false);
        panelItems.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int i = 0; i < itemsP1.size(); i++) {
            final int indice = i;
            Item item = itemsP1.get(i);
            JButton btnItem = crearBotonEstiloPokemon(item.getNombre());
            btnItem.addActionListener(e -> {
                usarItem(indice);
                panelBatalla.remove(panelItems);
                panelOpciones.setVisible(true);
                repaint();
            });
            panelItems.add(btnItem);
        }

        JButton btnVolver = crearBotonEstiloPokemon("VOLVER");
        btnVolver.addActionListener(e -> {
            panelBatalla.remove(panelItems);
            panelOpciones.setVisible(true);
            repaint();
        });
        panelItems.add(btnVolver);

        panelBatalla.add(panelItems);
        panelBatalla.revalidate();
        repaint();
    }

    private void mostrarMenuPokemon() {
        panelOpciones.setVisible(false);

        JPanel panelEquipo = new JPanel(new GridLayout(0, 1, 5, 5)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(COLOR_MENU);
                ((Graphics2D)g).fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                g.setColor(COLOR_BORDE);
                ((Graphics2D)g).draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));
            }
        };
        panelEquipo.setBounds(470, 480, 280, 80);
        panelEquipo.setOpaque(false);
        panelEquipo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int i = 0; i < equipoP1.size(); i++) {
            final int indice = i;
            Pokemon poke = equipoP1.get(i);
            JButton btnPokemon = crearBotonEstiloPokemon(
                    poke.getNombre() + " (PS: " + poke.getHp() + "/" + poke.getHpMax() + ")"
            );
            btnPokemon.addActionListener(e -> {
                cambiarPokemon(indice);
                panelBatalla.remove(panelEquipo);
                panelOpciones.setVisible(true);
                repaint();
            });
            panelEquipo.add(btnPokemon);
        }

        JButton btnVolver = crearBotonEstiloPokemon("VOLVER");
        btnVolver.addActionListener(e -> {
            panelBatalla.remove(panelEquipo);
            panelOpciones.setVisible(true);
            repaint();
        });
        panelEquipo.add(btnVolver);

        panelBatalla.add(panelEquipo);
        panelBatalla.revalidate();
        repaint();
    }

    private void intentarHuida() {
        Object[] options = {"Sí", "No"};
        int confirmacion = JOptionPane.showOptionDialog(
                this,
                "¿Estás seguro de querer huir?",
                "HUIR",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            mostrarMensaje("¡Escapaste con éxito!");
            Timer timer = new Timer(2000, e -> dispose());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void realizarAtaque(int indiceAtaque) {
        if (pokemonActualP1.getAtaques() == null || pokemonActualP1.getAtaques().isEmpty() ||
                indiceAtaque >= pokemonActualP1.getAtaques().size()) {
            mostrarMensaje("¡Este ataque no está disponible!");
            return;
        }

        Ataque ataque = pokemonActualP1.getAtaques().get(indiceAtaque);
        int danio = pokemonActualP1.atacar(ataque, pokemonActualP2);

        mostrarMensaje(pokemonActualP1.getNombre() + " usó " + ataque.getNombre() + "!");

        Timer timer = new Timer(1000, e -> {
            actualizarBarrasVida();

            if (pokemonActualP2.getHp() <= 0) {
                mostrarMensaje("¡" + pokemonActualP2.getNombre() + " se debilitó!");
                Timer timerFin = new Timer(2000, ev -> {

                    JOptionPane.showMessageDialog(this, "¡Has ganado la batalla!");
                    dispose();
                });
                timerFin.setRepeats(false);
                timerFin.start();
            } else {
                contraataqueEnemigo();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void activarDefensa(int indiceDefensa) {
        if (pokemonActualP1.getAtaques() == null || pokemonActualP1.getAtaques().size() <= indiceDefensa + 2) {
            mostrarMensaje("¡Esta defensa no está disponible!");
            return;
        }

        Ataque defensa = pokemonActualP1.getAtaques().get(indiceDefensa + 2);
        mostrarMensaje(pokemonActualP1.getNombre() + " usó " + defensa.getNombre() + "!");

        switch (defensa.getNombre().toLowerCase()) {
            case "defensa ardiente":
                pokemonActualP1.setDefensa(pokemonActualP1.getDefensa() + 10);
                break;
            case "esquivar":
                break;
            default:
                break;
        }

        Timer timer = new Timer(1000, e -> contraataqueEnemigo());
        timer.setRepeats(false);
        timer.start();
    }

    private void contraataqueEnemigo() {
        if (pokemonActualP2.getAtaques() == null || pokemonActualP2.getAtaques().isEmpty()) {
            mostrarMensaje(pokemonActualP2.getNombre() + " no tiene ataques disponibles.");
            return;
        }

        int indiceAleatorio = (int)(Math.random() * Math.min(pokemonActualP2.getAtaques().size(), 2));
        Ataque ataqueEnemigo = pokemonActualP2.getAtaques().get(indiceAleatorio);

        mostrarMensaje("¡" + pokemonActualP2.getNombre() + " usó " + ataqueEnemigo.getNombre() + "!");

        int danio = pokemonActualP2.atacar(ataqueEnemigo, pokemonActualP1);

        Timer timer = new Timer(1000, e -> {
            actualizarBarrasVida();

            if (pokemonActualP1.getHp() <= 0) {
                mostrarMensaje("¡" + pokemonActualP1.getNombre() + " se debilitó!");

                // Verificar si hay más pokémon disponibles
                boolean tieneMasPokemon = false;
                for (Pokemon p : equipoP1) {
                    if (p != pokemonActualP1 && p.getHp() > 0) {
                        tieneMasPokemon = true;
                        break;
                    }
                }

                if (tieneMasPokemon) {
                    JOptionPane.showMessageDialog(this,
                            "¡" + pokemonActualP1.getNombre() + " se ha debilitado! Debes elegir otro Pokémon.",
                            "Pokémon Debilitado",
                            JOptionPane.INFORMATION_MESSAGE);
                    Timer t = new Timer(500, ev -> mostrarMenuPokemon());
                    t.setRepeats(false);
                    t.start();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "¡Has perdido la batalla! Todos tus Pokémon están debilitados.",
                            "Derrota",
                            JOptionPane.INFORMATION_MESSAGE);
                    Timer t = new Timer(2000, ev -> dispose());
                    t.setRepeats(false);
                    t.start();
                }
            } else {
                mostrarMensaje("¿Qué debería hacer " + pokemonActualP1.getNombre() + "?");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void usarItem(int indice) {
        if (itemsP1 == null || indice >= itemsP1.size()) {
            return;
        }

        Item item = itemsP1.get(indice);
        mostrarMensaje("Usaste " + item.getNombre() + ".");

        item.usar(pokemonActualP1);
        itemsP1.remove(indice);

        actualizarBarrasVida();

        Timer timer = new Timer(1000, e -> {
            mostrarMensaje("¡" + pokemonActualP1.getNombre() + " se siente mejor!");
            contraataqueEnemigo();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void cambiarPokemon(int indice) {
        if (indice >= equipoP1.size()) {
            return;
        }

        Pokemon nuevoPokemon = equipoP1.get(indice);

        if (nuevoPokemon == pokemonActualP1) {
            mostrarMensaje("¡Este Pokémon ya está en combate!");
            return;
        }

        if (nuevoPokemon.getHp() <= 0) {
            mostrarMensaje("¡Este Pokémon está debilitado y no puede luchar!");
            return;
        }

        pokemonActualP1 = nuevoPokemon;
        mostrarMensaje("¡Adelante, " + pokemonActualP1.getNombre() + "!");

        actualizarPokemonAliado();

        Timer timer = new Timer(1000, e -> contraataqueEnemigo());
        timer.setRepeats(false);
        timer.start();
    }

    private void actualizarPokemonAliado() {
        cargarSprite(spriteAliado, pokemonActualP1.getNombre(), false);

        panelBatalla.remove(panelInfoAliado);
        crearPanelInfoAliado();
        panelBatalla.revalidate();
        repaint();
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje); // Solo muestra el mensaje en la consola
    }

    private void actualizarBarrasVida() {
        if (barraVidaAliado != null) {
            int hp = Math.max(0, pokemonActualP1.getHp());
            barraVidaAliado.setValue(hp);
            barraVidaAliado.setString(hp + "/" + pokemonActualP1.getHpMax());
            barraVidaAliado.setForeground(getColorHP(hp, pokemonActualP1.getHpMax()));
        }

        if (barraVidaEnemigo != null) {
            int hp = Math.max(0, pokemonActualP2.getHp());
            barraVidaEnemigo.setValue(hp);
            barraVidaEnemigo.setForeground(getColorHP(hp, pokemonActualP2.getHpMax()));
        }

        repaint();
    }

    private Color getColorHP(int hpActual, int hpMax) {
        double porcentaje = (double) hpActual / hpMax;
        if (porcentaje <= 0.2) return Color.RED;
        if (porcentaje <= 0.5) return Color.ORANGE;
        return Color.GREEN;
    }

}

