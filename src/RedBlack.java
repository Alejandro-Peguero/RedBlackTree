
import java.awt.*;
import javax.swing.*;

public class RedBlack extends Entrada implements Icon{

	public void limpiar(){
		if(root!=null){
			root.limpiar();
		}
	}

    /** Node labels. */
    private enum Color {RED, BLACK};

    /** Value returned from a comparator */
    private static final int LESS    = -1;

    /** Value returned from a comparator */
    private static final int EQUAL   =  0;

    /** Value returned from a comparator */
    private static final int GREATER = +1;
    
    /** NULL when the tree is empty */
    private Node root;

    /** All leaf nodes are black empty nodes that share this one instance. */
    final private Node EMPTY = new Empty();

    public RedBlack() {
        root = EMPTY;
    }
    public Node buscarNodo(int value){
    	if(root!=null){return root.getNode(value);}return null;
    }
    public void add(int value) {
        root = root.add(value);
        root.color = Color.BLACK;
    }

    private class Node {
    	int recorrido=0;
        public int  value;
        
        public Color  color;
        public Node   left;
        public Node   right;
        
        public void limpiar(){
        	this.recorrido=0;
        	if(left!=null){
        		left.limpiar();
        	}
        	if(right!=null){
        		right.limpiar();
        	}
        }
        
        /** Used by Empty */
        protected Node() {
            assert EMPTY == null;
        }

        /** Nodes always begin red */
        public Node(int v) {
         
            value = v;
            color = Color.RED;
            left  = EMPTY;
            right = EMPTY; 
        }
 
        private boolean isRed() {
            return color == Color.RED;
        }

        public int Comparar(int v){
            if(v<this.value) return LESS; else if(v>this.value) return GREATER; else return 0;
          }
        
        public Node add(int v) {
        	
            switch (Comparar(v)) {
            case LESS:
                left = left.add(v);
                break;

            case GREATER:
                right = right.add(v);
                break;

            case EQUAL:
                value = v;
                return this;  
            }
        
        
            // Check for two red nodes in a row: Red child and red grandchild
            if (left.isRed() && left.left.isRed()) {

                //       z           y
                //      / \         / \
                //     y   D  ==>  /   \
                //    / \         x     z
                //   x   C       / \   / \
                //  / \         A   B C   D
                // A   B
                return balance(left.left, left, this,              // x,y,z
                               left.left.right, left.right);       // B,C

            } else if (left.isRed() && left.right.isRed()) {

                //       z           y
                //      / \         / \
                //     x   D  ==>  /   \
                //    / \         x     z
                //   A   y       / \   / \
                //      / \     A   B C   D
                //     B   C
                return balance(left, left.right, this,             // x,y,z
                               left.right.left, left.right.right); // B,C
                
            } else if (right.isRed() && right.left.isRed()) {

                //     x             y
                //    / \           / \
                //   A   z    ==>  /   \
                //      / \       x     z
                //     y   D     / \   / \
                //    / \       A   B C   D
                //   B   C
                return balance(this, right.left, right,            // x,y,z
                               right.left.left, right.left.right); // B,C
                
            } else if (right.isRed() && right.right.isRed()) {
                //   x               y
                //  / \             / \
                // A   y      ==>  /   \
                //    / \         x     z
                //   B   z       / \   / \
                //      / \     A   B C   D
                //     C   D
                return balance(this, right, right.right,           // x,y,z
                               right.left, right.right.left);      // B,C
            }

            return this;
        }
        
        
        /** Returns the node for this key, or null. */
        public Node getNode(int v) {

            switch (Comparar(v)) {
            case LESS:
            	recorrido =-1;
                return left.getNode(v);

            case GREATER:
            	recorrido = 1;
                return right.getNode(v);

            default: // EQUAL
            	recorrido = 0;
                return this;
            }
        }


        public void paint(Graphics g, int x, int y, int separation) {
            final int R = 20;
            
            if (left != null) {
                int xx = x - separation;
                int yy = y + R * 3;
                g.setColor(java.awt.Color.BLACK);
                if(recorrido==-1){
                	g.setColor(java.awt.Color.BLUE);
                	
                }
               g.drawLine(x, y, xx, yy);
                left.paint(g, xx, yy, separation / 2);
            }

            if (right != null) {
                int xx = x + separation;
                int yy = y + R * 3;
                g.setColor(java.awt.Color.BLACK);
                if(recorrido==1){
                	g.setColor(java.awt.Color.BLUE);
                
                }
                g.drawLine(x, y, xx, yy);
                right.paint(g, xx, yy, separation / 2);
            }

            if (color == Color.RED) {
                g.setColor(java.awt.Color.RED);
            } else {
                g.setColor(java.awt.Color.BLACK);
            }
            g.fillOval(x - R, y - R, 2 * R, 2 * R);
            
            g.setColor(java.awt.Color.WHITE);
            drawCenteredString(g,String.valueOf(value), x, y);
        }
    }

    /** The empty node used at leaves */
    private class Empty extends Node {

        public Empty() {
            color = Color.BLACK;
            assert EMPTY == null : "Should only make one empty node instance!";
        }

        /** Always make a new node, since this one is empty */
        public Node add(int v) {
            return new Node(v);
        }

        public Node getNode(int value) {
            return null;
        }
    }

    /**
      Rearrange/recolor the tree as

      <pre>
             y      <== red
            / \
           /   \
          x     z   <== both black
         / \   / \
        A   B C   D
      </pre> 
      Note: A and D are not passed in because already in the right place
    */
    private Node balance(Node x, Node y, Node z, Node B, Node C) {
        
        x.right = B;
        y.left  = x;
        y.right = z;
        z.left  = C;
        x.color = Color.BLACK;
        y.color = Color.RED;
        z.color = Color.BLACK;
        return y;
    }

    //////////////////////////////////////////////////////////////
    // Rendering code

    public int getIconWidth() {
        return 600;
    }
    
    public int getIconHeight() {
        return 600;
    }
    
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.translate(x, y);
        
        Rectangle r = c.getBounds();
        if (root != null) {
            root.paint(g, r.width / 2, 40, r.width / 5);
        }
        
        g.translate(-x, -y);
    }

    private  void drawCenteredString(Graphics g, String s, int x, int y) {
        FontMetrics m = g.getFontMetrics();
        
        // Center the label  
        java.awt.geom.Rectangle2D bounds = m.getStringBounds(s, g);
        g.drawString(s, (int)(x - bounds.getWidth() / 2), (int)(y - bounds.getMinY() - bounds.getHeight() / 2));
    }
}