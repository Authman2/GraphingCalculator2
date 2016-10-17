package gc2.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;

import cw.components.titlebar.specific.sidebar.SideTitlebar;
import cw.components.windows.specific.SidebarWindow;
import gc2.actions.ButtonActions;

public class GraphingCalculator2 {

	public static JTextField functionInput;
	public static Graph graph;
	
	public static void main(String[] args) {
		SidebarWindow window = new SidebarWindow("Graphing Calculator");
		window.setSize(650,500);
		window.setLocationRelativeTo(null);
		
		// Change the color
		window.setTitlebarColor(new Color(51, 153, 255));
		window.setBodyColor(new Color(81, 193, 255));
		
		// Add a panel that will display the graph
		graph = new Graph();
		graph.setBackground(Color.white);
		window.getWindowBody().add(graph);
		
		// Add a text field where the user can enter a graph
		functionInput = new JTextField("x^2");
		window.getWindowBody().add(functionInput, BorderLayout.SOUTH);
		
		
		// Create the buttons
		((SideTitlebar)window.getTitlebar()).addMenuItem("Graph Function");
		((SideTitlebar)window.getTitlebar()).addMenuItem("Graph Derivative");
		((SideTitlebar)window.getTitlebar()).addMenuItem("Show Roots");
		((SideTitlebar)window.getTitlebar()).addMenuItem("xMin-xMin");
		((SideTitlebar)window.getTitlebar()).addMenuItem("yMin-yMax");
		
		// Give the buttons actions
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(0).addAction(new ButtonActions("GRAPH", functionInput, graph));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(1).addAction(new ButtonActions("DERIVATIVE", functionInput, graph));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(2).addAction(new ButtonActions("ROOTS", functionInput, graph));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(3).addAction(new ButtonActions("X_INTERVAL", functionInput, graph));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(4).addAction(new ButtonActions("Y_INTERVAL", functionInput, graph));
		
		
		
		window.setVisible(true);	
	}

}
