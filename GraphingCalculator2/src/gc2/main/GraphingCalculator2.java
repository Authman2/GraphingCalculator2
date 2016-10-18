package gc2.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cw.components.titlebar.specific.sidebar.SideTitlebar;
import cw.components.windows.specific.SidebarWindow;
import gc2.actions.ButtonActions;

public class GraphingCalculator2 {

	public static JTextField functionInput;
	public static JTextField rootsTF;
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
		

		// The panel that holds all the components at the bottom of the page (function entry & roots)
		JPanel bottomPanel = new JPanel(new GridLayout(2,2,1,1));
		
		// Add a text field where the user can enter a graph
		functionInput = new JTextField("x^2");
		bottomPanel.add(new JLabel("Polynomial:"));
		bottomPanel.add(functionInput, BorderLayout.SOUTH);
		
		
		// Add a text field so that the roots can be shown
		bottomPanel.add(new JLabel("Roots:"));
		rootsTF = new JTextField();
		rootsTF.setEditable(false);
		bottomPanel.add(rootsTF);
		window.getWindowBody().add(bottomPanel, BorderLayout.SOUTH);
		
		
		
		// Create the buttons
		((SideTitlebar)window.getTitlebar()).addMenuItem("Graph Function");
		((SideTitlebar)window.getTitlebar()).addMenuItem("Graph Derivative");
		((SideTitlebar)window.getTitlebar()).addMenuItem("Show Roots");
		((SideTitlebar)window.getTitlebar()).addMenuItem("Change Interval");
		
		// Give the buttons actions
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(0).addAction(new ButtonActions("GRAPH", functionInput, rootsTF, graph));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(1).addAction(new ButtonActions("DERIVATIVE", functionInput, rootsTF, graph));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(2).addAction(new ButtonActions("ROOTS", functionInput, rootsTF, graph));
		((SideTitlebar)window.getTitlebar()).getMenuItems().get(3).addAction(new ButtonActions("INTERVAL", functionInput, rootsTF, graph));
		
		
		
		window.setVisible(true);	
	}

}
