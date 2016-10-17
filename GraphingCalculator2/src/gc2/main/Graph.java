package gc2.main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Graph extends JPanel {
private static final long serialVersionUID = 5445026948607143713L;


	Polynomial p;
	double minx,maxx,miny,maxy;
	
	double[] xpoints;
	double[] ypoints;
	
	double[] xpoints2;
	double[] ypoints2;
	int n;




	public Graph() {
		
	}

	public void setPoly(Polynomial p) {
		this.p = p;
	}
	
	
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        redrawAxes(g);
        
        if(ypoints != null) {
            g.setColor(Color.black);
            redrawAxes(g);
            drawCurve(g);
        }
        repaint();
    }
    
    public void redrawAxes(Graphics g) {
        if(xpoints != null) {
            int midpoint = 0;
            int midpointy = 0;
            
            if(minx < 0) {
                midpoint = (int)(maxx+minx);
            } else {
                midpoint = (int)(maxx-minx);
            }
            if(miny < 0) {
                midpointy = (int)(maxy+miny);
            } else {
                midpointy = (int)(maxy-miny);
            }
            
            int newX = (int)((getWidth()/(maxx-minx))*(maxx - midpoint));
            int newY = (int)((getHeight()/(maxy-miny))*(midpointy - miny));
                
            //Y-axis
            g.drawLine(newX,0,newX,getHeight());
            //X-axis
            g.drawLine(0,newY,getWidth(),newY);
        } else {
            //Y-axis
            g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
            //X-axis
            g.drawLine(0,getHeight()/2, getWidth(), getHeight()/2);
        }
    }
    
    /* Draws the actual graph
       */
    public void drawCurve(Graphics g) {
        //Draw the first polynomial
        if(xpoints != null) {
            for(int i = 0; i < xpoints.length - 1; i++) {
                int firstX = (int)((getWidth()/(maxx-minx))*(xpoints[i] - minx));
                int firstY = (int)((getHeight()/(maxy-miny))*(maxy - ypoints[i]));
                
                g.setColor(Color.black);
                g.drawLine(firstX,firstY,firstX,firstY);
            }
        }
        //Draw the second polynomial
        if(xpoints2 != null) {
            for(int i = 0; i < xpoints2.length - 1; i++) {
                int firstX = (int)((getWidth()/(maxx-minx))*(xpoints2[i] - minx));
                int firstY = (int)((getHeight()/(maxy-miny))*(maxy - ypoints2[i]));
                
                g.setColor(Color.gray);
                g.drawLine(firstX,firstY,firstX,firstY);
            }
        }
    }
    
    /*This is responsible for finding all the x-values and y-values that need to be plotted, then adds them 
     * to the respective arrays.
       */
     public void findPointsToPlot(double mina, double maxa, double minya, double maxya) {
         /*
          * Finds the minimum and maximum values for the x- and y-axes. Also gets the scale factor.
          */
         minx = mina;
         maxx = maxa;
         miny = minya;
         maxy = maxya;
         
         int num = 1000;
         
         n = 0;  //Number of terms in each array
         
         //X and Y coordinates for the first polynomial
         xpoints = new double[num*(int)((maxx-minx)+1)];
         ypoints = new double[num*(int)((maxx-minx)+1)];
         
         //X and Y coordinates for the second polynomial
         xpoints2 = new double[num*(int)((maxx-minx)+1)];
         ypoints2 = new double[num*(int)((maxx-minx)+1)];
         
         for(double i = minx; i <= maxx; i += 1.0/num) { 
             // 'i' is the x value and 'cp.p.evaluate(i)' is the y value
             xpoints[n] = i;
             ypoints[n] = p.evaluate(i);
             
             n++;
         }
         
     }
     
}
