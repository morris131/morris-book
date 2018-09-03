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

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	private int labelX = 10;
	
	private int labelY = 10;
	
	private int labelWidth = 50;
	
	private int labelInputHeight = 25;
	
	private int inputX = 70;
	
	private int inputY = 10;
	
	private int inputWidth = 100;	
	
	private int padding = 10;
	
	private int getLableY() {
		return labelY += padding + labelInputHeight;
	}
	
	private int getInputY() {
		return inputY += padding + labelInputHeight;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
					UIManager.put("RootPane.setupButtonVisible", false);
					BeautyEyeLNFHelper.launchBeautyEyeLNF();
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.autoSize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 100, 100);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblX = new JLabel("x");
		lblX.setBounds(labelX, labelY, labelWidth, labelInputHeight);
		contentPane.add(lblX);
		
		JTextField textField = new JTextField();
		textField.setBounds(inputX, inputY, inputWidth, labelInputHeight);
		contentPane.add(textField);
		
		JLabel lblX1 = new JLabel("x");
		lblX1.setBounds(labelX, getLableY(), labelWidth, labelInputHeight);
		contentPane.add(lblX1);
		
		JTextField textField1 = new JTextField();
		textField1.setBounds(inputX, getInputY(), inputWidth, labelInputHeight);
		contentPane.add(textField1);
		
		
		JLabel lblX2 = new JLabel("x");
		lblX2.setBounds(labelX, getLableY(), labelWidth, labelInputHeight);
		contentPane.add(lblX2);
		
		JTextField textField2 = new JTextField();
		textField2.setBounds(inputX, getInputY(), inputWidth, labelInputHeight);
		contentPane.add(textField2);
		
		JButton jButton = new JButton("保存");
		jButton.setBounds(labelX, getLableY(), labelWidth, labelInputHeight);
		contentPane.add(jButton);
		
		JButton jButton2 = new JButton("取消");
		jButton2.setBounds(inputX, getInputY(), labelWidth, labelInputHeight);
		contentPane.add(jButton2);
		
	}
	
	public void autoSize() {
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
