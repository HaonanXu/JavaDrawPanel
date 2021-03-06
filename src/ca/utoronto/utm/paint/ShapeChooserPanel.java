package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	
	public ShapeChooserPanel(View view) {	
		this.view=view;
		
		String[] buttonLabels = { "Line","Circle", "Rectangle", "Square", "Squiggle", "Polyline","Eraser","Airbrush","Zoom","Straw","Pencil","Fill" };
		this.setLayout(new GridLayout(buttonLabels.length, 1));
		for (String label : buttonLabels) {
				JButton button = new JButton(label);
				button.setIcon(new ImageIcon(getClass().getResource(label+".png")));
				this.add(button);
				button.addActionListener(this);
				button.setToolTipText("Click and then Press and Grag on right panel to pain.");
		}
	}
	
	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setMode(e.getActionCommand());
		System.out.println(e.getActionCommand());
	}
}
