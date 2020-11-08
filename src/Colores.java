import java.awt.Color;

public enum Colores {
	PANEL_ARRIBA_OSCURO(new Color(20, 20, 20)),
	GRIS(new Color(32, 32, 32)),
	GRIS_OSCURO(new Color(10, 10, 10)),
	AZUL_OSCURO(new Color(0, 14, 46)),
	AZUL_HOVER_OSCURO(new Color(32, 42, 65)),
	PANEL_ARRIBA_CLARO(new Color(255, 252, 239)),
	AMARILLO_CLARO(new Color(255, 243, 185)),
	AMARILLO(new Color(255	, 217, 41)),
	AMARILLO_HOVER(new Color(255, 226, 93)),
	AZUL_CLARO(new Color(101, 148, 255)),
	AZUL_HOVER_CLARO(new Color(152, 179, 243));
	
	private Color color;
	
	Colores(Color cl) {
		color = cl;
	}
	
	public Color getColor() {
		return color;
	}
}
