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
	
	// The text field that displays the roots.
	JTextField rootsTF;
	
	// The graph panel to display the graph on.
	Graph graph;
	
	// Where to graph the function.
	String xMinString = "-10", xMaxString = "10", yMinString = "-10", yMaxString = "10";
	
	
	
	
	
	/**@param n -- The name of the operation to perform.
	 * @param tf -- The text field to grab the polynomial from
	 * @param g -- The graph to display the polynomial on. */
	public ButtonActions(String n, JTextField tf, JTextField rts, Graph g) {
		name = n;
		this.tf = tf;
		this.rootsTF = rts;
		this.graph = g;
	}

	
	/** The overriden execute method. */
	@Override
	public void execute() {
		if(name.equals("GRAPH")) {
			graphFunction();
		} else if(name.equals("DERIVATIVE")) {
			graphDerivative();
		} else if(name.equals("ROOTS")) {
			showRoots();
		} else if(name.equals("INTERVAL")) {
			changeXInterval();
		}
	}

	
	
	
	
	/////////// Methods ////////////
	
	/** Shows the graph of the polynomial on the screen. */
	private void graphFunction() {
		
		try {
			graph.setPoly(new Polynomial(tf.getText()));
			
			//Max and Min x values
	        double thisMin = Double.parseDouble(xMinString);
	        double thisMax = Double.parseDouble(xMaxString);
	        
	        //Max and Min y values
	        double thisMiny = Double.parseDouble(yMinString);
	        double thisMaxy = Double.parseDouble(yMaxString);
	        
	        graph.findPointsToPlot(thisMin, thisMax, thisMiny, thisMaxy);
	        
		} catch(StringIndexOutOfBoundsException err) {
        	JOptionPane.showMessageDialog(graph, "You must enter a polynomial to graph.");
        }
	}
	
	
	/** Graphs the derivative of the function on the screen. */
	private void graphDerivative() {
		try {
			graph.setPoly(new Polynomial(tf.getText()).getDerivative());
			tf.setText(graph.getPolynomial().toString());
			
			//Max and Min x values
	        double thisMin = Double.parseDouble(xMinString);
	        double thisMax = Double.parseDouble(xMaxString);
	        
	        //Max and Min y values
	        double thisMiny = Double.parseDouble(yMinString);
	        double thisMaxy = Double.parseDouble(yMaxString);
	        
	        graph.findPointsToPlot(thisMin, thisMax, thisMiny, thisMaxy);
	        
		} catch(StringIndexOutOfBoundsException err) {
        	JOptionPane.showMessageDialog(graph, "You must enter a polynomial to graph.");
        
		} catch(NumberFormatException err) {
			JOptionPane.showMessageDialog(graph, "There is no derivative for this polynomial.");
        }
	}
	
	
	/** Displays the roots of the polynomial. */
	private void showRoots() {
		try {
			Polynomial p = graph.getPolynomial();
			double[] roots = p.getRoots((int)Double.parseDouble(xMinString), (int)Double.parseDouble(xMaxString));
			
			
			// Create the roots string. Only takes it to 3 decimal places.
			String s = "";
			for(Double d : roots) {
				String str = "" + d;
				if(str.substring(str.indexOf(".")).length() > 3) {
					s += str.substring(0, str.indexOf(".")+3) + ", ";
				} else {
					s += str + ", ";
				}
			}
			rootsTF.setText(s);
		} catch(NullPointerException err) {
			JOptionPane.showMessageDialog(graph, "You must enter a polynomial to find its roots.");
		}
	}
	
	
	/** Brings up a window to change the x interval. */
	private void changeXInterval() {
		JPanel panel = new JPanel(new GridLayout(2,2,5,5));
		panel.add(new JLabel("X-Min:"));
		JTextField f1 = new JTextField(xMinString);
		panel.add(f1);
		panel.add(new JLabel("X-Max:"));
		JTextField f2 = new JTextField(xMaxString);
		panel.add(f2);
		panel.add(new JLabel("Y-Min"));
		JTextField f3 = new JTextField(yMinString);
		panel.add(f3);
		panel.add(new JLabel("Y-Max"));
		JTextField f4 = new JTextField(yMaxString);
		panel.add(f4);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Min - Max", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			if(!f1.getText().equals("") && !f1.getText().equals(null)) {
				xMinString = f1.getText();
			} else {
				xMinString = "-10";
			}
			if(!f2.getText().equals("") && !f2.getText().equals(null)) {
				xMaxString = f2.getText();
			} else {
				xMaxString = "10";
			}
			if(!f3.getText().equals("") && !f3.getText().equals(null)) {
				yMinString = f3.getText();
			} else {
				yMinString = "-10";
			}
			if(!f4.getText().equals("") && !f4.getText().equals(null)) {
				yMaxString = f4.getText();
			} else {
				yMaxString = "10";
			}
			
			graphFunction();
		} else {
			xMinString = "-10";
			xMaxString = "10";
			yMinString = "-10";
			yMaxString = "10";
		}
	}
	
	
	
}
