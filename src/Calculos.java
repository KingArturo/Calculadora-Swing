import javax.swing.JButton;

public class Calculos {
	
    private String texto;
    private String operacion;
    
    private float resultado;
    private float num = 0;
    
    private static enum Operadores {SUMA, RESTA, DIVISION, MULTI, NADA, OTRO};
    private Operadores operador;
    
    public Calculos() {

    	resultado = 0;
    	operacion = "";
    	texto = "";
    	operador = Operadores.NADA;
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
     *	Realiza la operacion pertinente con el numero que le pasas
     *	por parametro.
     */
    
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

    /**
     * Borrar un caracter 
     */
    
    public String borrarUnCaracter(String cadena) {
        String aDevolver = "0";
        if (cadena.length() > 1) {
            aDevolver = cadena.substring(0, cadena.length() - 1);
        }
        return aDevolver;
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
    
    public String limpiar() {
        texto = "0";
        resultado = 0;
        operacion = "";
        return texto;
    }
    
    public String borrar() {
    	texto = borrarUnCaracter(texto);
        operacion = borrarUnCaracter(operacion);
    	Integer fl = new Integer(Math.round(num));
        num = Float.parseFloat(borrarUnCaracter(fl.toString()));
    	return texto;
    }
    
    public void setTexto(String tx) {
    	texto = tx;
    }
    
    public void concatenarTexto(String tx) {
    	texto += tx;
    }
    
    public void setOperacion(String tx) {
    	operacion = tx;
    }
    
    public void concatenarOperacion(String tx) {
    	operacion += tx;
    }
    
    public void setResultado(float fl) {
    	resultado = fl;
    }
    
    public void setNum(float fl) {
    	num = fl;
    }
    
    public String getTexto() {
    	return texto;
    }
    
    public String getOperacion() {
    	return operacion;
    }
    
    public float getResultado() {
    	return resultado;
    }
    
    public float getNum() {
    	return num;
    }
    
    public String getOperador() {
    	return operador.name();
    }
}
