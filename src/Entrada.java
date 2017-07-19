import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Entrada extends JPanel {
	
	JTextField entrada;
	JButton buscar,agregar;

	Entrada(){
		
		entrada = new JTextField(10);
		buscar = new JButton("buscar");
		agregar = new JButton("agregar");

		this.add(buscar);
		this.add(agregar);
        this.add(entrada);
	}
}
