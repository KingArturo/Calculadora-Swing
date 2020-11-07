public class Calculos {
	
    private String texto;
    private String operacion;
    
    private float resultado;
    private float num;
    private float resulAnt;
    
    private static enum Operadores {SUMA, RESTA, DIVISION, MULTI, PORCENT, NADA};
    private Operadores operador;
    
    public Calculos() {
    	num = 0;
    	resultado = 0;
    	operacion = "";
    	texto = "";
    	resulAnt = 0;
    	operador = Operadores.NADA;
    }
	
    /**
     * Comprueva si el boton pulsado es un operador
     */    
    public boolean operadorPulsado(String tx) {
    	boolean pulsado = false;
    	String[] array = new String[] {"+","-","*","/","=","%"};
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
    public void calcular() {
        if (operador.name().equals("SUMA")) {
        	resultado += num;
        } else if (operador.name().equals("RESTA")) {
        	resultado -= num;
        } else if (operador.name().equals("DIVISION")) {
        	resultado /= num;
        } else if (operador.name().equals("MULTI")) {
        	resultado *= num;
        } else if (operador.name().equals("PORCENT")) {
        	resultado = (resultado/100)*num;
        } else {
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
		case "%":
			operador = Operadores.PORCENT;
			break;
		case "=":
			operador = Operadores.NADA;
			break;
		default:
			break;
		}
	}

    /**
     * Borra un caracter 
     */    
    public String borrarUnCaracter(String cadena) {
        String aDevolver = "0";
        if (cadena.length() > 1) {
            aDevolver = cadena.substring(0, cadena.length() - 1);
        }
        return aDevolver;
    }
        
    public void limpiar() {
        texto = "0";
        resulAnt = resultado;
        resultado = 0;
        operacion = "";
    }
    
    /**
     * Borra un caracter de la variable text, operacion y num
     * y devuelve la variable texto
     */
    
    public String borrar() {
    	texto = borrarUnCaracter(texto);
        operacion = borrarUnCaracter(operacion);
    	Integer fl = new Integer(Math.round(num));
        num = Float.parseFloat(borrarUnCaracter(fl.toString()));
    	return texto;
    }
    
    public void numAnterior() {
    	num = resulAnt;
    	operacion = ""+resulAnt;
    }
    
    /**
     * Metodos Setter 
     */    
    public void setTexto(String tx) {texto = tx;}
    
    public void concatenarTexto(String tx) {texto += tx;}
    
    public void setOperacion(String tx) {operacion = tx;}
    
    public void concatenarOperacion(String tx) {operacion += tx;}
    
    public void setResultado(float fl) {resultado = fl;}
    
    public void setNum() {num = Float.parseFloat(texto);}
    
    public void setResulAnt() {resulAnt = resultado;}
   
    /**
     * Metodos Getter 
     */
    public String getTexto() {return texto;}
    
    public String getOperacion() {return operacion;}
    
    public float getResultado() {return resultado;}
    
    public float getNum() {return num;}
    
    public String getOperador() {return operador.name();}
    
    public float getNumAnt() {return resulAnt;}
}
