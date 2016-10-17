package gc2.actions;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cw.components.titlebar.specific.sidebar.Action;
import gc2.main.Graph;
import gc2.main.Polynomial;

public class ButtonActions extends Action {

	// This name will be a simple way to perform different actions without having to make a bunch of 
	// different classes. Normally this would be a more secure way of checking but for now it doesn't really
	// matter that much.
	String name = "";
	
	// The text field to grab the polynomial from.
	JTextField tf;
	
	// The graph panel to display the graph on.
	Graph graph;
	
	// Where to graph the function.
	String xMinString = "-10", xMaxString = "10", yMinString = "-10", yMaxString = "10";
	
	
	
	
	
	/**@param n -- The name of the operation to perform.
	 * @param tf -- The text field to grab the polynomial from
	 * @param g -- The graph to display the polynomial on. */
	public ButtonActions(String n, JTextField tf, Graph g) {
		name = n;
		this.tf = tf;
		this.graph = g;
	}

	
	
	@Override
	public void execute() {
		if(name.equals("GRAPH")) {
			graphFunction();
		} else if(name.equals("DERIVATIVE")) {
			graphDerivative();
		} else if(name.equals("ROOTS")) {
			showRoots();
		} else if(name.equals("X_INTERVAL")) {
			changeXInterval();
		} else if(name.equals("Y_INTERVAL")) {
			changeYInterval();
		}
	}

	
	
	
	
	/////////// Methods ////////////
	
	private void graphFunction() {
		
		graph.setPoly(new Polynomial(tf.getText()));
		
		//Max and Min x values
        double thisMin = Double.parseDouble(xMinString);
        double thisMax = Double.parseDouble(xMaxString);
        
        //Max and Min y values
        double thisMiny = Double.parseDouble(yMinString);
        double thisMaxy = Double.parseDouble(yMaxString);
        
        graph.findPointsToPlot(thisMin, thisMax, thisMiny, thisMaxy);
	}
	
	
	private void graphDerivative() {
		System.out.println("Derivative");
	}
	
	
	private void showRoots() {
		System.out.println("Roots");
	}
	
	
	private void changeXInterval() {
		JPanel panel = new JPanel(new GridLayout(2,2,5,5));
		panel.add(new JLabel("X-Min:"));
		JTextField f1 = new JTextField();
		panel.add(f1);
		panel.add(new JLabel("X-Max:"));
		JTextField f2 = new JTextField();
		panel.add(f2);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "xMin - xMax", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			if(!f1.getText().equals("") && !f1.getText().equals(null)) {
				xMinString = f1.getText();
			}
			if(!f2.getText().equals("") && !f2.getText().equals(null)) {
				xMaxString = f2.getText();
			}
			
			graphFunction();
		} else {
			xMinString = "-10";
			xMaxString = "10";
		}
	}
	

	private void changeYInterval() {
		System.out.println("Y interval");
	}
	
	
	
}
