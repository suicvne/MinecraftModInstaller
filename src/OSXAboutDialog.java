import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;


public class OSXAboutDialog extends JFrame 
{

	private JPanel contentPane;

	
	public static void main(String[] args)
	{
		OSXAboutDialog osx = new OSXAboutDialog();
		osx.setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public OSXAboutDialog() 
	{
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.UTILITY);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setBounds(311, 268, 117, 25);
		contentPane.add(btnClose);
		
		JTextPane txtpnMinecraftModInstaller = new JTextPane();
		txtpnMinecraftModInstaller.setText("Minecraft Mod Installer is a simple, cross platform, Java based application for quickly installing Minecraft mods from zip files utilising custom 'versions' for use inside of the new Minecraft Launcher.\n\nMinecraft Mod Installer utilises the following libraries to make the magic happen\n\n- commons-io-2.4.jar (Apache)\n- orange-extensions-1.3.0.jar \n- zip4j_1.3.2.jar (Zip4j Team)\n- json.org JSON file (Source bundled in application)");
		txtpnMinecraftModInstaller.setBounds(12, 176, 416, 80);
		contentPane.add(txtpnMinecraftModInstaller);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(OSXAboutDialog.class.getResource("/res/icon_128.png")));
		lblNewLabel.setBounds(155, 12, 128, 128);
		contentPane.add(lblNewLabel);
		
		JLabel lblMinecraftModInstaller = new JLabel("Minecraft Mod Installer");
		lblMinecraftModInstaller.setBounds(165, 151, 117, 15);
		contentPane.add(lblMinecraftModInstaller);
		//centres the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/icon.png")));
	}
}
