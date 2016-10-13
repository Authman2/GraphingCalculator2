package gc2.actions;

import cw.components.titlebar.specific.sidebar.Action;

public class ButtonActions extends Action {

	// This name will be a simple way to perform different actions without having to make a bunch of 
	// different classes. Normally this would be a more secure way of checking but for now it doesn't really
	// matter that much.
	String name = "";
	
	
	public ButtonActions(String n) {
		name = n;
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
		System.out.println("Graph");
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
