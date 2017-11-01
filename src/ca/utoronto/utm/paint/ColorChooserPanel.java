package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the Panel for choose the color.
 * @author michael
 *
 */
public class ColorChooserPanel extends JPanel implements ActionListener {
	private View view;
	private ArrayList<JButton> buttonlist = new ArrayList<>();
	private JButton[] stylelist = new JButton[2];
	public ColorChooserPanel(View a) {
		this.view = a;
		JPanel style = new JPanel();
		JButton outline = new JButton("outline");
		outline.addActionListener(this);
		outline.setBorder(BorderFactory.createLoweredBevelBorder());
		
		JButton fill = new JButton("fill");
		fill.addActionListener(this);
		fill.setBorder(BorderFactory.createRaisedBevelBorder());
		style.setLayout(new GridLayout(2,1));
		
		this.stylelist[0] = outline;
		this.stylelist[1] = fill;
		style.add(outline);
		style.add(fill);
		
		String [] ColorButton = {"blue","red","black","green"};
		JLabel jl = new JLabel("Color:",JLabel.CENTER);
		jl.setFont(new   java.awt.Font("Dialog",   1,   15));
		this.setLayout(new GridLayout(1,ColorButton.length+2));
		this.add(style);
		this.add(jl);
		for(String o:ColorButton) {
			JButton jb = new JButton(o);
			jb.setBorder(BorderFactory.createRaisedBevelBorder()); 
			jb.setIcon(new ImageIcon(getClass().getResource(o+".png"))); 
			jb.addActionListener(this);
			jb.setPreferredSize(new Dimension(10,10)); 
			this.buttonlist.add(jb);
		}
		for(JButton b: this.buttonlist) {
			this.add(b);
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "fill"){
			this.view.getPaintPanel().setstyle("fill");
			this.stylelist[1].setBorder(BorderFactory.createLoweredBevelBorder());
			this.stylelist[0].setBorder(BorderFactory.createRaisedBevelBorder());
		}
		
		if(e.getActionCommand() == "outline"){
			this.view.getPaintPanel().setstyle("outline");
			this.stylelist[0].setBorder(BorderFactory.createLoweredBevelBorder());
			this.stylelist[1].setBorder(BorderFactory.createRaisedBevelBorder());
		}
		
		if(e.getActionCommand() == "blue") {this.view.getPaintPanel().setdrawColor(Color.BLUE);
			for(JButton x: this.buttonlist) {x.setBorder(BorderFactory.createRaisedBevelBorder());}
			this.buttonlist.get(0).setBorder(BorderFactory.createLoweredBevelBorder());
		
		}
		
		if(e.getActionCommand() == "red") {this.view.getPaintPanel().setdrawColor(Color.RED);
			for(JButton x: this.buttonlist) {x.setBorder(BorderFactory.createRaisedBevelBorder());}
			this.buttonlist.get(1).setBorder(BorderFactory.createLoweredBevelBorder());
		}
		
		if(e.getActionCommand() == "black") {this.view.getPaintPanel().setdrawColor(Color.BLACK);
			for(JButton x: this.buttonlist) {x.setBorder(BorderFactory.createRaisedBevelBorder());}
			this.buttonlist.get(2).setBorder(BorderFactory.createLoweredBevelBorder());
		}
		
		if(e.getActionCommand() == "green") {this.view.getPaintPanel().setdrawColor(Color.GREEN);
			for(JButton x: this.buttonlist) {x.setBorder(BorderFactory.createRaisedBevelBorder());}
			this.buttonlist.get(3).setBorder(BorderFactory.createLoweredBevelBorder());
		}
		
		
	}

}
