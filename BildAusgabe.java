import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class BildAusgabe extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JButton jBAuswahl;
	private JTextField jTFOrdner;
	private JButton jBDarstellen;
	private JFileChooser jFC;
	private JButton jBEnde;
	private JFileChooser fc = new JFileChooser();

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BildAusgabe inst = new BildAusgabe();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public BildAusgabe() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jBAuswahl = new JButton();
				getContentPane().add(jBAuswahl);
				jBAuswahl.setText("Bild auswählen");
				jBAuswahl.setBounds(244, 12, 129, 23);
				jBAuswahl.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jBAuswahlActionPerformed(evt);
					}
				});
			}
			{
				jTFOrdner = new JTextField();
				getContentPane().add(jTFOrdner);
				jTFOrdner.setBounds(19, 12, 219, 23);
			}
			{
				jBEnde = new JButton();
				getContentPane().add(jBEnde);
				jBEnde.setText("Ende");
				jBEnde.setBounds(265, 83, 90, 23);
				jBEnde.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jBEndeActionPerformed(evt);
					}
				});
			}
			{
			jFC = new JFileChooser();
			getContentPane().add(jFC);
			jFC.setBounds(549, 5, 594, 397);
			}
			{
				jBDarstellen = new JButton();
				getContentPane().add(jBDarstellen);
				jBDarstellen.setText("Bild Darstellen");
				jBDarstellen.setBounds(19, 83, 187, 23);
				jBDarstellen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jBDarstellenActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(400, 155);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	
	private void jBEndeActionPerformed(ActionEvent evt) {
		System.exit(0);
	}
	
	private void jBAuswahlActionPerformed(ActionEvent evt) {
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int status = fc.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			File selFile = fc.getSelectedFile();
			jTFOrdner.setText(selFile.getAbsolutePath());
		}
	}
	
	private void jBDarstellenActionPerformed(ActionEvent evt) {
		try {
			Process p = Runtime.getRuntime().exec("/home/pi/Skripte/BildAusgabe.sh " + jTFOrdner.getText());
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} 
	}

}
