package gc2.actions;

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
        double thisMin = Double.parseDouble("-10");
        double thisMax = Double.parseDouble("10");
        
        //Max and Min y values
        double thisMiny = Double.parseDouble("-10");
        double thisMaxy = Double.parseDouble("10");
        
        graph.findPointsToPlot(thisMin, thisMax, thisMiny, thisMaxy);
	}
	
	
	private void graphDerivative() {
		System.out.println("Derivative");
	}
	
	
	private void showRoots() {
		System.out.println("Roots");
	}
	
	
	private void changeXInterval() {
		System.out.println("X interval");
	}
	

	private void changeYInterval() {
		System.out.println("Y interval");
	}
	
	
	
}
