package com.morris.base;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class AbstactEditDialog extends JFrame {

	protected JPanel contentPane;
	
	protected int labelX = 10;
	
	protected int labelY = 10;
	
	protected int labelWidth = 50;
	
	protected int labelInputHeight = 25;
	
	protected int inputX = 70;
	
	protected int inputY = 10;
	
	protected int inputWidth = 100;	
	
	protected int padding = 10;
	
	protected int getLableY() {
		return labelY += padding + labelInputHeight;
	}
	
	protected int getInputY() {
		return inputY += padding + labelInputHeight;
	}

	/**
	 * Create the frame.
	 */
	public AbstactEditDialog() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 100, 100);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addCompents();
		
		JButton jButton = new JButton("保存");
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		jButton.setBounds(labelX, getLableY(), labelWidth, labelInputHeight);
		contentPane.add(jButton);
		
		JButton jButton2 = new JButton("取消");
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancer();
			}
		});
		jButton2.setBounds(inputX, getInputY(), labelWidth, labelInputHeight);
		contentPane.add(jButton2);
	}
	
	protected abstract void addCompents();
	
	protected abstract void save();
	
	protected void cancer() {
		this.dispose();
	}
	
	protected void autoSize() {
		Dimension size = contentPane.getSize();
		System.out.println(size);
		double w = size.getWidth();
		double h = size.getHeight();
		
		double borderWidth = (this.getWidth() - w)/2;
		double titileHeight = this.getHeight() - h - borderWidth;
		
		double width = inputX + inputWidth + padding + borderWidth * 2;
		double height = labelY + labelInputHeight + padding  + titileHeight + borderWidth;
		setSize((int)width, (int)height);
		
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
		
	}
}
