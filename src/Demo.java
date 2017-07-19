
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Demo extends JFrame implements ActionListener{
	
	Entrada entrada;

	 JPanel JP= new JPanel();
	 
    private RedBlack tree = new RedBlack();
    private Demo() {
   	
        super("Red Black Tree");
       
        JP.add(new JLabel(tree));
        this.setSize(700, 720);
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

    static public void main(String[] arg) {
        new Demo();
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