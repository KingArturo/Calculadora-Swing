
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.regex.Pattern;

public class Calculadora extends JFrame {

    private JPanel panelprincipal;
    private JPanel panelAbajo;
    private JLabel text;
    private JLabel opLabel;
    private static final JButton botonArray[] = new JButton[20];
    private static final Color color = new Color(32, 32, 32);
    
    private String texto;
    private String operacion;
    
    private float resultado;
    private float num = 0;
    
    private static enum Operadores {SUMA, RESTA, DIVISION, MULTI, NADA, OTRO};
    private Operadores operador;

    public Calculadora() {
    	init();
    	resultado = 0;
    	operacion = "";
    	operador = Operadores.NADA;

               
        for (int i = 0; i < botonArray.length; i++) {
            JButton btn = botonArray[i];
 
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (btn.getLabel() == "CE") {
                        texto = "0";
                        resultado = 0;
                        text.setText(texto);
                    } else if (btn.getLabel() == "=") {  
                    	calcular(num);
                    	isOperador(btn.getLabel());
                    	Float fl = new Float(resultado);
                    	String string = fl.toString();
                        text.setText(string);
                    } else if(btn.getLabel() == "C") {
                        texto = borrarUnCaracter(texto);
                        text.setText(texto);
                    } else {
                        texto += btn.getLabel();

	    				if (operadorPulsado(btn.getLabel())) {
	    					operacion += " "+btn.getLabel()+" "; 
	        				calcular(num);
	                        isOperador(btn.getLabel());
	    					text.setText(btn.getLabel());
	    	                texto = "";
	    				}
	    				else {
	    					text.setText(texto);
	        				num = Float.parseFloat(texto);
	                        operacion += btn.getLabel(); 
	    				}

                    }
                	opLabel.setText(operacion+" =");
                }
            });
            botonHover(btn);
        }
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
     * Comprueba si es un operador y le da un valor a
     * la variable operador en caso de que lo sea
     */
    public void isOperador(String str) {
		switch (str) {
		case "+":
			operador = Operadores.SUMA;
			break;
		case "-":
			operador = Operadores.RESTA;
			break;
		case "*":
			operador = Operadores.MULTI;
			break;
		case "/":
			operador = Operadores.DIVISION;
			break;
		case "=":
			operador = Operadores.NADA;
			break;
		default:
			break;
		}
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
    
    public void init() {
    	opLabel = new JLabel();
    	opLabel.setBackground(Color.black);
    	opLabel.setForeground(Color.white);
    	
        texto = "";
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
        panelArriba.setBackground(Color.black);
        
        this.setTitle("Calculadora Mal");
        this.setSize(417, 450);
        this.add(panelprincipal); 
        this.setResizable(false);
        initBotones();
	}

    /**
     * Crea Los botones y los a�ade a un Array
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

    /**
     * Añade los botones numericos a un array
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
        text.setBorder(new LineBorder(Color.BLACK));
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

    public void calcular(float num) {
        if (operador.name().equals("SUMA")) {
        	resultado += num;
        }
        else if (operador.name().equals("RESTA")) {
        	resultado -= num;
        }
        else if (operador.name().equals("DIVISION")) {
        	resultado /= num;
        }
        else if (operador.name().equals("MULTI")) {
        	resultado *= num;
        }
        else {
        	resultado = num;
        }
    }

    public String borrarUnCaracter(String cadena) {
        String aDevolver = "";
        if (cadena.length() > 1) {
            aDevolver = cadena.substring(0, cadena.length() - 1);
        }
        return aDevolver;
    }
    
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
}