
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
    private JLabel text;
    private JLabel opLabel;
    private static final JButton botonArray[] = new JButton[20];
    private static final Color color = new Color(32, 32, 32);
    
    private Calculos calc;

    public Calculadora() {
    	init();
    	calc = new Calculos();
    	botonPulsado();
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

        panelprincipal = new JPanel(new BorderLayout());
        GridLayout grid = new GridLayout(5, 4);
        JPanel panelArriba = new JPanel(new BorderLayout());
        panelAbajo = new JPanel(grid);
        estiloLabel();
        panelprincipal.add(panelArriba, BorderLayout.NORTH);
        panelprincipal.add(panelAbajo, BorderLayout.CENTER);
        panelArriba.add(text, BorderLayout.EAST);
        panelArriba.add(opLabel, BorderLayout.WEST);

        panelAbajo.setBackground(Color.black);
        panelAbajo.setBackground(Color.black);
        panelArriba.setBackground(new Color(20, 20, 20));
        panelArriba.setBorder(new LineBorder(Color.black));
        
        this.setTitle("Calculadora");
        this.setSize(416, 448);
        this.add(panelprincipal); 
        this.setResizable(false);
        initBotones();
	}

    /**
     * Crea Los botones y los añade a un Array
     */
    public void initBotones() {
    	String array[] = new String[] {"CE","C","()","*","1","2","3"
    			,"-","4","5","6","+","7","8","9","/",".","0","%","="};
    	for (int i=0; i<array.length; i++) {
			String string = array[i];
			botonArray[i] = new JButton(string);
		}
        addBotones();
    }
        
    public boolean operadorPulsado(String tx) {
    	boolean pulsado = false;
    	String[] array = new String[] {"+","-","*","/","="};
    	for (int i=0; i < array.length; i++) {
			String string = array[i];
			if(string.equals(tx)) {
				pulsado = true;
			}
		}
    	return pulsado;
    }
    
    /**
     * AÃ±ade los botones numericos a un array
     */
    public void addBotones() {
        for (int i = 0; i < botonArray.length; i++) {
        	JButton btnButton = botonArray[i];
        	btnButton.setPreferredSize(new Dimension(100, 40));
        	btnButton = estiloBotones(btnButton);
            panelAbajo.add(btnButton);
        }
    }

    /**
     * Establece el estilo del label
     */
    
    public void estiloLabel() {
        text = new JLabel("0");
        text.setBackground(Color.black);
        text.setForeground(Color.white);
        text.setFont(new Font("Helvetica", Font.PLAIN, 40));
	}
    
    /**
     *	Establece el estilo de los botones 
     */
    
    public JButton estiloBotones(JButton btn) {
    	if(btn.getLabel().equals("=")) {
        	btn.setBackground(new Color(0, 14, 46));
    	} else if(esUnBotonConNumero(btn)) {
	    	btn.setBackground(new Color(10, 10, 10));
    	}
    	else {
        	btn.setBackground(color);
    	}
    	btn.setForeground(Color.white);
    	btn.setBorderPainted(true);
        btn.setFont(new Font("Helvetica", Font.PLAIN, 40));
    	btn.setBorder(new LineBorder(Color.BLACK));
 
    	return btn;
	}
    
    /**
     * Establece el estilo de los botones cuando pasas el raton por
     * encima
     */
    
    public void botonHover(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	if(btn.getLabel().equals("=")) {
                	btn.setBackground(new Color(32, 42, 65));
            	} else {
                	btn.setBackground((Color.DARK_GRAY));
            	}
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	
            	if(btn.getLabel().equals("=")) {
                	btn.setBackground(new Color(0, 14, 46));
            	} else if(esUnBotonConNumero(btn)) {
            		btn.setBackground(new Color(10, 10, 10));
            	} else {
            		btn.setBackground(color);
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
        for (int i = 0; i < botonArray.length; i++) {
            JButton btn = botonArray[i];
 
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (btn.getLabel() == "CE") {
                        text.setText(calc.limpiar());
                    } else if (btn.getLabel() == "=") {  
                    	calc.calcular();
                    	calc.isOperador(btn.getLabel());
                    	Float fl = new Float(calc.getResultado());
                    	String string = fl.toString();
                        text.setText(string);
                    } else if(btn.getLabel() == "C") {
                        text.setText(calc.borrar());
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
	    						
	    					}
	    					calc.concatenarOperacion(btn.getLabel());
	    				}
                    }
                	opLabel.setText(calc.getOperacion()+" =");
                }
            });
            botonHover(btn);
        }
    }
}