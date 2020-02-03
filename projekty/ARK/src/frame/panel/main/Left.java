package frame.panel.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class Left extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Left INSTANCE = new Left();public static Left getLeft() {return INSTANCE;}
	
	private JTextField spiciesSearch;
	private JButton bredable;
	private JButton tamable;
	private JButton rideable;
	
	private Left() {
		super();
		JScrollPane scrollFrameServer=new JScrollPane(frame.panel.main.left.Server.getServer());
		JScrollPane scrollFrameSpicies=new JScrollPane(frame.panel.main.left.Spicies.getSpicies());
		scrollFrameServer.setPreferredSize(new Dimension(230,250));
		scrollFrameSpicies.setPreferredSize(new Dimension(230,275));
		scrollFrameServer.getVerticalScrollBar().setUnitIncrement(16);
		scrollFrameSpicies.getVerticalScrollBar().setUnitIncrement(16);
		scrollFrameServer.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		scrollFrameSpicies.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		scrollFrameServer.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		scrollFrameSpicies.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		bredable = new JButton("B");
		bredable.setPreferredSize(new Dimension(70,20));
		bredable.setBackground(Color.WHITE);
		bredable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (bredable.getBackground()==Color.WHITE)
				{bredable.setBackground(Color.RED);}
				else if(bredable.getBackground()==Color.RED)
				{bredable.setBackground(Color.GREEN);}
				else{bredable.setBackground(Color.WHITE);}
				updateSpicies();
			}});
		tamable = new JButton("T");
		tamable.setPreferredSize(new Dimension(70,20));
		tamable.setBackground(Color.WHITE);
		tamable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tamable.getBackground()==Color.WHITE)
				{tamable.setBackground(Color.RED);}
				else if(tamable.getBackground()==Color.RED)
				{tamable.setBackground(Color.GREEN);}
				else{tamable.setBackground(Color.WHITE);}
				updateSpicies();
			}});
		rideable = new JButton("R");
		rideable.setPreferredSize(new Dimension(70,20));
		rideable.setBackground(Color.WHITE);
		rideable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rideable.getBackground()==Color.WHITE)
				{rideable.setBackground(Color.RED);}
				else if(rideable.getBackground()==Color.RED)
				{rideable.setBackground(Color.GREEN);}
				else{rideable.setBackground(Color.WHITE);}
				updateSpicies();
			}});
		
		spiciesSearch = new JTextField(20);
		spiciesSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updateSpicies();
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				updateSpicies();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				updateSpicies();
			}
			
		});
		
		add(spiciesSearch);
		add(tamable);
		add(rideable);
		add(bredable);
		
		add(scrollFrameSpicies);
		JTextField serverSearch = new JTextField(20);
		serverSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				frame.panel.main.left.Server.getServer().updateList(serverSearch.getText());
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				frame.panel.main.left.Server.getServer().updateList(serverSearch.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				frame.panel.main.left.Server.getServer().updateList(serverSearch.getText());
			}
			
		});
		
		add(serverSearch);
		add(scrollFrameServer);
		setPreferredSize(new Dimension(240,615));
		setBackground(Color.BLACK);
	}
	
	private void updateSpicies()
	{
		Boolean b = false,t = false,r = false;
		if(bredable.getBackground() ==Color.WHITE) {b =null;}
		else if(bredable.getBackground() ==Color.GREEN) {b =true;}
		if(tamable.getBackground() ==Color.WHITE) {t =null;}
		else if(tamable.getBackground() ==Color.GREEN) {t =true;}
		if(rideable.getBackground() ==Color.WHITE) {r =null;}
		else if(rideable.getBackground() ==Color.GREEN) {r =true;}
		
		frame.panel.main.left.Spicies.getSpicies().updateList(spiciesSearch.getText(),t,r,b);
	}

}
