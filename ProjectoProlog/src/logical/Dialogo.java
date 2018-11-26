package logical;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;

public class Dialogo extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dialogo dialog = new Dialogo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialogo() {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dialogo.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		setBounds(100, 100, 288, 191);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Courier New", Font.PLAIN, 33));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(93, 120, 110, 44);
		contentPanel.add(btnOk);
		
		JLabel lblLaAiLo = new JLabel("La AI  lo resolvio en "+GUI.getPasos() +" pasos.");
		lblLaAiLo.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lblLaAiLo.setBounds(21, 75, 254, 33);
		contentPanel.add(lblLaAiLo);
	}
}
