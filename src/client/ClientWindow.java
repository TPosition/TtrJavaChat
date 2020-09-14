package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ClientWindow {

	private JFrame frmChatProgram;
	private JTextField msgField;
	private static JTextArea textArea = new JTextArea();

	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ClientWindow window = new ClientWindow();
					window.frmChatProgram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
		String name = JOptionPane.showInputDialog("Enter Name");
		client = new Client(name, "localhost", 52864);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatProgram = new JFrame();
		frmChatProgram.setResizable(false);
		frmChatProgram.setTitle("Chat program\r\n");
		frmChatProgram.setBounds(100, 100, 638, 472);
		frmChatProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatProgram.getContentPane().setLayout(new BorderLayout(0, 0));

		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		frmChatProgram.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		frmChatProgram.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		msgField = new JTextField();
		panel.add(msgField);
		msgField.setColumns(50);

		JButton sendBtn = new JButton("Send");
		sendBtn.addActionListener(e -> {
			if (!msgField.getText().equals("")) {
				client.send(msgField.getText());
				msgField.setText("");
			}
		});
		panel.add(sendBtn);

		// make window shows at center
		frmChatProgram.setLocationRelativeTo(null);

	}

	public static void printToConsole(String message) {
		textArea.setText(textArea.getText() + message + "\n");
	}

}
