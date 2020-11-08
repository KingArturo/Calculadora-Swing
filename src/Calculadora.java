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
    //Tema Oscuro
    private static final Color GRIS = new Color(32, 32, 32);
    private static final Color GRIS_OSCURO = new Color(10, 10, 10);
    //Tema Claro
    private static final Color AMARILLO_CLARO = new Color(255, 247, 210);
    private static final Color AMARILLO = new Color(255	, 217, 41);
    private boolean temaOscuro;
    
    private Calculos calc;

    public Calculadora() {
    	init();
    	calc = new Calculos();
    	temaOscuro = true;
    	botonPulsado();
    	 itemClaro.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
            	 for(int i=0; i<BOTONES.length; i++) {
            		 estiloBotonesClaros(BOTONES[i]);
            	 }
            	 labelClaro();
            	 temaOscuro = false;
             }
         });
    	 itemOscuro.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
            	 for(int i=0; i<BOTONES.length; i++) {
            		 estiloBotones(BOTONES[i]);
            	 }
            	 temaOscuro = true;
            	 estiloLabel();
             }
         });
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
    	opLabel.setBackground(Color.black);
    	opLabel.setForeground(Color.white);
    	
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
        estiloLabel();
        panelprincipal.add(panelArriba, BorderLayout.NORTH);
        panelprincipal.add(panelAbajo, BorderLayout.CENTER);
        panelArriba.add(text, BorderLayout.EAST);
        panelArriba.add(opLabel, BorderLayout.WEST);
        panelArriba.add(menuBar, BorderLayout.NORTH);

        panelAbajo.setBackground(Color.black);
        panelAbajo.setBackground(Color.black);
    	estiloMenuBar();
        
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
            panelAbajo.add(estiloBotones(btnButton));
        }
    }

    public void labelClaro() {
        panelArriba.setBackground(new Color(255, 252, 239));
        //text.setBackground(AMARILLO_CLARO);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Helvetica", Font.PLAIN, 40));
    }

    /** Establece el estilo del label */    
    public void estiloLabel() {
        text.setForeground(Color.white);
        panelArriba.setBackground(new Color(20, 20, 20));
        panelArriba.setBorder(new LineBorder(Color.black));
        text.setFont(new Font("Helvetica", Font.PLAIN, 40));
	}
    
    /**	Establece el estilo de los botones */    
    public JButton estiloBotones(JButton btn) {
    	if(btn.getLabel().equals("=")) {
        	btn.setBackground(new Color(0, 14, 46));
    	} else if(esUnBotonConNumero(btn)) {
	    	btn.setBackground(GRIS_OSCURO);
    	}
    	else {
        	btn.setBackground(GRIS);
    	}
    	btn.setForeground(Color.white);
    	btn.setBorderPainted(true);
        btn.setFont(new Font("Helvetica", Font.PLAIN, 40));
    	btn.setBorder(new LineBorder(Color.BLACK));
 
    	return btn;
	}
    
    /**	Establece el estilo de los botones */    
    public JButton estiloBotonesClaros(JButton btn) {
    	if(btn.getLabel().equals("=")) {
        	btn.setBackground(new Color(101, 148, 255));
    	} else if(esUnBotonConNumero(btn)) {
	    	btn.setBackground(AMARILLO);
    	}
    	else {
        	btn.setBackground(AMARILLO_CLARO);
    	}
    	btn.setForeground(Color.BLACK);
    	btn.setBorderPainted(true);
        btn.setFont(new Font("Helvetica", Font.PLAIN, 40));
    	btn.setBorder(new LineBorder(Color.BLACK));
 
    	return btn;
	}
    
    /**Establece el estilo de la barra de menu y su contenido*/
    public void estiloMenuBar() {
        menuBar.setBackground(GRIS_OSCURO);
    	menuBar.setBorderPainted(true);
    	menuBar.setBorder(new LineBorder(Color.BLACK));
        menu.setBackground(GRIS_OSCURO);
        menu.setForeground(Color.WHITE);
        itemOscuro.setBackground(GRIS_OSCURO);
        itemOscuro.setForeground(Color.WHITE);
        itemClaro.setBackground(GRIS_OSCURO);
        itemClaro.setForeground(Color.WHITE);
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
                    	btn.setBackground(new Color(32, 42, 65));
                	} else {
                    	btn.setBackground((Color.DARK_GRAY));
                	}
            	} else {
                	if(btn.getLabel().equals("=")) {
                    	btn.setBackground(new Color(152, 179, 243));
                	} else {
                    	btn.setBackground(new Color(255, 226, 93));
                	}
            	}

            }
            @Override
            public void mouseExited(MouseEvent e) {
            	if(temaOscuro) {
                	if(btn.getLabel().equals("=")) {
                    	btn.setBackground(new Color(0, 14, 46));
                	} else if(esUnBotonConNumero(btn)) {
                		btn.setBackground(GRIS_OSCURO);
                	} else {
                		btn.setBackground(GRIS);
                	}
            	} else {
                	if(btn.getLabel().equals("=")) {
                    	btn.setBackground(new Color(101, 148, 255));
                	} else if(esUnBotonConNumero(btn)) {
            	    	btn.setBackground(AMARILLO);
                	} else {
                    	btn.setBackground(AMARILLO_CLARO);
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
}