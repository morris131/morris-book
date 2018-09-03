package com.morris.base;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class EditDialog extends AbstactEditDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
					EditDialog frame = new EditDialog();
					frame.setVisible(true);
					frame.autoSize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void addCompents() {
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
	}

	@Override
	protected void save() {
		System.out.println("save");

	}

}
