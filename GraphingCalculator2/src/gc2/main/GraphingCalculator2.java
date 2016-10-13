package gc2.main;

import cw.components.titlebar.specific.sidebar.SideTitlebar;
import cw.components.windows.specific.SidebarWindow;
import gc2.actions.ButtonActions;

public class GraphingCalculator2 {

	public static void main(String[] args) {
//		Polynomial p1 = new Polynomial("9x^3+5x^2-4x-8+9");
//		Polynomial p2 = new Polynomial("x+5");
//		Polynomial p3 = new Polynomial("x^3-5x");
//		
//		System.out.println("Answer:" + p1.evaluate(1));
//		System.out.println("Answer:" + p2.evaluate(2));
//		System.out.println("Answer: " + p3.evaluate(5));
		
		
		SidebarWindow window = new SidebarWindow("Graphing Calculator");
		window.setSize(500,500);
		window.setLocationRelativeTo(null);
		
		// Create the buttons
		((SideTitlebar)window.getTitlebar()).addMenuItem("Graph Function");
		((SideTitlebar)window.getTitlebar()).addMenuItem("Graph Derivative");
		((SideTitlebar)window.getTitlebar()).addMenuItem("Show Roots");
		((SideTitlebar)window.getTitlebar()).addMenuItem("xMin-xMin");
		((SideTitlebar)window.getTitlebar()).addMenuItem("yMin-yMax");
		
		// Give the buttons actions
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(0).addAction(new ButtonActions("GRAPH"));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(1).addAction(new ButtonActions("DERIVATIVE"));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(2).addAction(new ButtonActions("ROOTS"));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(3).addAction(new ButtonActions("X_INTERVAL"));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(4).addAction(new ButtonActions("Y_INTERVAL"));
		
		
		window.setVisible(true);	
	}

}
