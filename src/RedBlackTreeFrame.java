
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RedBlackTreeFrame extends JFrame implements ActionListener{
	
	 Entrada entrada;

	 JPanel JP= new JPanel();
	 
    private RedBlackTree tree = new RedBlackTree();
    public RedBlackTreeFrame() {

        super("Red Black Tree");
        /*ancho,largo*/
        JP.add(new JLabel(tree));
        JP.setBackground(Color.DARK_GRAY);
        this.setSize(800, 600);
        this.setLayout(new FlowLayout());
        this.setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        entrada = new Entrada();
        
        this.add(entrada);
        entrada.agregar.addActionListener(this);
        entrada.buscar.addActionListener(this);
        this.add(JP);
        setVisible(true);
 
    }

	@Override
	public void actionPerformed(ActionEvent tec) {
	 if(tec.getSource()==entrada.agregar){
		try{
			int valor = Integer.parseInt(entrada.entrada.getText());
			tree.add(valor);
			JP.repaint();
		}
		catch(Exception e){
			
		}	
	 }
	 
	 if(tec.getSource()==entrada.buscar){
		try{
			int rec = Integer.parseInt(entrada.entrada.getText());
			tree.limpiar();
			tree.buscarNodo(rec);
			JP.repaint();
			System.out.println(rec);
		}
		catch(Exception e){}
	 } 
   }	
}