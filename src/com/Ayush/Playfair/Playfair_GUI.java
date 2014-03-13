package com.Ayush.Playfair;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Playfair_GUI {

	private JFrame frame;
	private JTextField key;
	private JTextField inputText;
	private JLabel lblText;
	private JTextArea outputText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Playfair_GUI window = new Playfair_GUI();
					window.frame.setVisible(true);
					window.frame.setTitle("Playfair Cipher");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Playfair_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		key = new JTextField();
		key.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		key.setBounds(20, 40, 253, 45);
		frame.getContentPane().add(key);
		key.setColumns(1);
		key.setDocument(new JTextFieldLimit(15));
		key.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblKey.setBounds(20, 6, 61, 35);
		frame.getContentPane().add(lblKey);
		
		inputText = new JTextField();
		inputText.setBounds(69, 119, 362, 28);
		frame.getContentPane().add(inputText);
		inputText.setColumns(10);
		
		lblText = new JLabel("Text:");
		lblText.setBounds(20, 125, 39, 16);
		frame.getContentPane().add(lblText);
		
		final JCheckBox chckbxTextIsEncrypted = new JCheckBox("Text is encrypted");
		chckbxTextIsEncrypted.setBounds(69, 155, 141, 23);
		frame.getContentPane().add(chckbxTextIsEncrypted);
		
		JButton btnEncryptDecrypt = new JButton("Encrypt / Decrypt");
		btnEncryptDecrypt.addMouseListener(new MouseAdapter() 
			{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(validate())
				{
					CipherBrain brain = new CipherBrain();
					outputText.setText(brain.encryptOrDecrypt(inputText.getText(), key.getText(), chckbxTextIsEncrypted.isSelected()));
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Please check the text boxes. Only Letters are permitted.","Inane error", JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		btnEncryptDecrypt.setBounds(139, 190, 163, 58);
		frame.getContentPane().add(btnEncryptDecrypt);
		
		outputText = new JTextArea();
		outputText.setEditable(false);
		outputText.setBounds(20, 268, 411, 78);
		frame.getContentPane().add(outputText);
	}
	
	private boolean validate()
	{
		inputText.setText(inputText.getText().replaceAll("[^a-zA-Z]", ""));
		key.setText(key.getText().replaceAll("[^a-zA-Z]", ""));
		
		if(key.getText().length()>0 && inputText.getText().length()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
