import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.util.regex.Pattern;

public class Calculadora extends JFrame {

    private JPanel panelprincipal;
    private JPanel panelAbajo;
    private JPanel panelArriba;
    private JLabel text;
    private JLabel opLabel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem itemOscuro;
    private JMenuItem itemClaro;
    private static final JButton BOTONES[] = new JButton[20];

    private boolean temaOscuro;    
    private Calculos calc;

    public Calculadora() {
    	init();
    	calc = new Calculos();
    	temaOscuro = true;
    	botonPulsado();
    	menuPulsado();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
           javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        }
        catch(Exception e) {
            System.out.println(e);
        }
        Calculadora frame = new Calculadora();

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    
    /**
     *	Inicializa todos los componentes grafico de la
     *	Aplicacion 
     */
    public void init() {
    	opLabel = new JLabel();
    	
    	menuBar = new JMenuBar();
    	menu = new JMenu("Temas");
    	itemOscuro = new JMenuItem("Oscuro");
    	itemClaro = new JMenuItem("Claro");
    	menuBar.add(menu);
    	menu.add(itemOscuro);
    	menu.add(itemClaro);

        panelprincipal = new JPanel(new BorderLayout());
        GridLayout grid = new GridLayout(5, 4);
        panelArriba = new JPanel(new BorderLayout());
        panelAbajo = new JPanel(grid);
        text = new JLabel("0");
        labelOscuro();
        panelprincipal.add(panelArriba, BorderLayout.NORTH);
        panelprincipal.add(panelAbajo, BorderLayout.CENTER);
        panelArriba.add(text, BorderLayout.EAST);
        panelArriba.add(opLabel, BorderLayout.WEST);
        panelArriba.add(menuBar, BorderLayout.NORTH);

        estiloMenuBarOscuro();
        
        this.setTitle("Calculadora");
        this.setSize(416, 448);
        this.add(panelprincipal); 
        this.setResizable(false);
        initBotones();
	}

    /** Crea Los botones y los añade a un Array */
    public void initBotones() {
    	String array[] = new String[] {"CE","C","ANT","*","1","2","3"
    			,"-","4","5","6","+","7","8","9","/",".","0","%","="};
    	for (int i=0; i<array.length; i++) {
			String string = array[i];
			BOTONES[i] = new JButton(string);
		}
        addBotones();
    }
    
    /** AÃ±ade los botones numericos a un array*/
    public void addBotones() {
        for (int i = 0; i < BOTONES.length; i++) {
        	JButton btnButton = BOTONES[i];
        	btnButton.setPreferredSize(new Dimension(100, 40));
            panelAbajo.add(estiloBotonesOscuros(btnButton));
        }
    }

    public void labelClaro() {
        panelArriba.setBackground(Colores.PANEL_ARRIBA_CLARO.getColor());
        panelArriba.setBorder(new LineBorder(Colores.PANEL_ARRIBA_CLARO.getColor()));
        panelAbajo.setBackground(Colores.PANEL_ARRIBA_CLARO.getColor());
        text.setForeground(Color.BLACK);
    	opLabel.setForeground(Color.BLACK);
        text.setFont(new Font("Helvetica", Font.PLAIN, 40));
    }

    /** Establece el estilo del label */    
    public void labelOscuro() {
        panelArriba.setBackground(Colores.PANEL_ARRIBA_OSCURO.getColor());
        panelArriba.setBorder(new LineBorder(Colores.PANEL_ARRIBA_OSCURO.getColor()));
        panelAbajo.setBackground(Colores.PANEL_ARRIBA_OSCURO.getColor());
    	opLabel.setForeground(Color.white);
    	text.setForeground(Color.white);
        text.setFont(new Font("Helvetica", Font.PLAIN, 40));
	}
    
    /**	Establece el estilo de los botones */    
    public JButton estiloBotonesOscuros(JButton btn) {
    	if(btn.getLabel().equals("=")) {
        	btn.setBackground(Colores.AZUL_OSCURO.getColor());
    	} else if(esUnBotonConNumero(btn)) {
	    	btn.setBackground(Colores.GRIS_OSCURO.getColor());
    	}
    	else {
        	btn.setBackground(Colores.GRIS.getColor());
    	}
    	btn.setForeground(Color.white);
    	btn.setBorderPainted(true);
        btn.setFont(new Font("Helvetica", Font.PLAIN, 40));
    	btn.setBorder(new LineBorder(Colores.PANEL_ARRIBA_OSCURO.getColor()));
 
    	return btn;
	}
    
    /**	Establece el estilo de los botones */    
    public JButton estiloBotonesClaros(JButton btn) {
    	if(btn.getLabel().equals("=")) {
        	btn.setBackground(Colores.AZUL_CLARO.getColor());
    	} else if(esUnBotonConNumero(btn)) {
	    	btn.setBackground(Colores.AMARILLO.getColor());
    	}
    	else {
        	btn.setBackground(Colores.AMARILLO_CLARO.getColor());
    	}
    	btn.setForeground(Color.BLACK);
    	btn.setBorderPainted(true);
        btn.setFont(new Font("Helvetica", Font.PLAIN, 40));
    	btn.setBorder(new LineBorder(Colores.PANEL_ARRIBA_CLARO.getColor()));
 
    	return btn;
	}
    
    public void estiloMenuBarClaro() {
        menuBar.setBackground(Colores.AMARILLO.getColor());
    	menuBar.setBorderPainted(false);
        menu.setBackground(Colores.AMARILLO.getColor());
        menu.setForeground(Color.BLACK);
        itemOscuro.setBackground(Colores.AMARILLO.getColor());
        itemOscuro.setForeground(Color.BLACK);
        itemOscuro.setBorderPainted(false);
        itemClaro.setBackground(Colores.AMARILLO.getColor());
        itemClaro.setForeground(Color.BLACK);
        itemClaro.setBorderPainted(false);
    }
    
    /**Establece el estilo de la barra de menu y su contenido*/
    public void estiloMenuBarOscuro() {
        menuBar.setBackground(Colores.GRIS_OSCURO.getColor());
    	menuBar.setBorderPainted(false);
        menu.setBackground(Colores.GRIS_OSCURO.getColor());
        menu.setForeground(Color.WHITE);
        itemOscuro.setBackground(Colores.GRIS_OSCURO.getColor());
        itemOscuro.setForeground(Color.WHITE);
        itemOscuro.setBorderPainted(false);
        itemClaro.setBackground(Colores.GRIS_OSCURO.getColor());
        itemClaro.setForeground(Color.WHITE);
        itemClaro.setBorderPainted(false);
    }
	
    /**
     * Establece el estilo de los botones cuando pasas el raton por
     * encima
     */    
    public void botonHover(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	if(temaOscuro) {
                	if(btn.getLabel().equals("=")) {
                    	btn.setBackground(Colores.AZUL_HOVER_OSCURO.getColor());
                	} else {
                    	btn.setBackground((Color.DARK_GRAY));
                	}
            	} else {
                	if(btn.getLabel().equals("=")) {
                    	btn.setBackground(Colores.AZUL_HOVER_CLARO.getColor());
                	} else {
                    	btn.setBackground(Colores.AMARILLO_HOVER.getColor());
                	}
            	}

            }
            @Override
            public void mouseExited(MouseEvent e) {
            	if(temaOscuro) {
                	if(btn.getLabel().equals("=")) {
                    	btn.setBackground(Colores.AZUL_OSCURO.getColor());
                	} else if(esUnBotonConNumero(btn)) {
                		btn.setBackground(Colores.GRIS_OSCURO.getColor());
                	} else {
                		btn.setBackground(Colores.GRIS.getColor());
                	}
            	} else {
                	if(btn.getLabel().equals("=")) {
                    	btn.setBackground(Colores.AZUL_CLARO.getColor());
                	} else if(esUnBotonConNumero(btn)) {
            	    	btn.setBackground(Colores.AMARILLO.getColor());
                	} else {
                    	btn.setBackground(Colores.AMARILLO_CLARO.getColor());
                	}
            	}

            }
        });
	}

    /**
     *	Devuelve true si el valor de un boton pasado por 
     *	parametro es un numero.  
     */    
    public boolean esUnBotonConNumero(JButton btn) {
    	boolean esNumero = false;
    	String array[] = new String[] {"1","2","3","4","5","6","7","8","9","0"};   	
    	for(int i=0; i<array.length; i++) {
    		if(array[i].equals(btn.getLabel())) {
    			esNumero = true;
    		}
    	} 
    	return esNumero;
    }
    
    public void botonPulsado() {
        for (int i = 0; i < BOTONES.length; i++) {
            JButton btn = BOTONES[i];
 
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (btn.getLabel() == "CE") {
                    	calc.limpiar();
                        text.setText(calc.getTexto());
                    } else if (btn.getLabel() == "=") {  
                    	calc.calcular();
                    	Float fl = new Float(calc.getResultado());
                    	String string = fl.toString();
                        text.setText(string);
                        calc.limpiar();
                    } else if(btn.getLabel() == "C") {
                        text.setText(calc.borrar());
                    } else if(btn.getLabel().equals("ANT")) {
                    	calc.numAnterior();
                    } else {
                    	calc.concatenarTexto(btn.getLabel());
	    				if(calc.operadorPulsado(btn.getLabel())) {
	    					calc.concatenarOperacion(btn.getLabel());
	        				calc.calcular();
	                        calc.isOperador(btn.getLabel());
	    					text.setText(btn.getLabel());
	    					calc.setTexto("");
	    				} else {
	    					text.setText(calc.getTexto());
	    					try {
	    						calc.setNum();
	    					}
	    					catch(Exception e) {
	    						System.out.println(e);
	    					}
	    					calc.concatenarOperacion(btn.getLabel());
	    				}
                    }
                	opLabel.setText(calc.getOperacion());
                }
            });
            botonHover(btn);
        }
    }
    
    public void menuPulsado() {
   	 itemClaro.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
        	 for(int i=0; i<BOTONES.length; i++) {
        		 estiloBotonesClaros(BOTONES[i]);
        	 }
        	 labelClaro();
        	 estiloMenuBarClaro();
        	 temaOscuro = false;
         }
     });
	 itemOscuro.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
        	 for(int i=0; i<BOTONES.length; i++) {
        		 estiloBotonesOscuros(BOTONES[i]);
        	 }
        	 temaOscuro = true;
        	 labelOscuro();
        	 estiloMenuBarOscuro();
         }
     });
    }
}