import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;


public class SuccessDialog extends JFrame
{

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SuccessDialog()
	{
		setTitle("Minecraft Mod Installer");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SuccessDialog.class.getResource("/res/icon.png")));
		setType(Type.UTILITY);
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourModsWere = new JLabel("Your mods were installed successfully!");
		lblYourModsWere.setBounds(10, 11, 486, 14);
		contentPane.add(lblYourModsWere);
		
		JLabel lblBeforePlayingBe = new JLabel("Before playing, be sure to select the version under \"edit profile\" in the Minecraft Launcher");
		lblBeforePlayingBe.setBounds(10, 259, 685, 14);
		contentPane.add(lblBeforePlayingBe);
		
		JButton btnOk = new JButton("Exit");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnOk.setBounds(558, 309, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblIfYouWish = new JLabel("If you wish to install more, run the Mod Installer again selecting your new version profile");
		lblIfYouWish.setBounds(10, 284, 637, 14);
		contentPane.add(lblIfYouWish);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 36, 649, 212);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SuccessDialog.class.getResource("/res/success_image.png")));
		lblNewLabel.setBounds(54, 7, 554, 200);
		panel.add(lblNewLabel);
		//
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/icon.png")));
	}
}
