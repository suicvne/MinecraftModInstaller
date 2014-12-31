import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JButton;

import org.apache.commons.io.FileUtils;

import javax.swing.border.BevelBorder;


public class MainApplicationWindow {

	private JFrame frmMinecraftModInstaller;
	private HashMap componentMap;
	public String selectedProfile;
	public String MINECRAFTVERSIONSPATH;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		//Try and set the look and feel
		///com.sun.java.swing.plaf.gtk.GTKLookAndFeel - generally looks acceptable on Linux
		///com.sun.java.swing.plaf.motif.MotifLookAndFeel - ugly
		///com.sun.java.swing.plaf.windows.WindowsLookAndFeel - looks almost like a real windows application
		
		try
		{
			switch(OsCheck.getOperatingSystemType())
			{
			case Windows:
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				break;
			case Linux:
				//gtk theme is ugly
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				break;
			case MacOS:
				//os x sets its own laf
				break;
			case Other:
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				break;
			default:
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				break;
			}
		}
		catch(Exception ex) //not catching a specific exception
		{
			System.out.println("system look and feel not supported, cool");
			try
			{
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			}
			catch(Exception xxx)
			{
				System.out.println("what the hell dude, i've tried everything and it looks like you don't even have the cross platform look and feel");
			}
		}
		//Run the form
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					//These properties seem to only work for Java 6, but I'm keeping them anyway
					System.setProperty("apple.laf.useScreenMenuBar", "true");
					System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Minecraft Mod Installer");
					
					MainApplicationWindow window = new MainApplicationWindow();
					window.frmMinecraftModInstaller.setVisible(true);
				} 
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplicationWindow() 
	{
		//
		initialize();
		//
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frmMinecraftModInstaller.setLocation(dim.width/2-frmMinecraftModInstaller.getSize().width/2, dim.height/2-frmMinecraftModInstaller.getSize().height/2);
		//do form load events
		if(OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS)
			System.out.println("current operating system is probably " + OsCheck.getOperatingSystemType().name() + ", you lucky bastard");
		else
			System.out.println("current operating system is probably " + OsCheck.getOperatingSystemType().name());
		
		if(OsCheck.getOperatingSystemType() == OsCheck.OSType.Windows)
		{
			LoadFromWindows();
		}
		else if(OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux)
		{
			LoadFromLinux();
		}
		else if(OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS)
		{
			LoadFromOSX();
			/*JOptionPane.showMessageDialog(null, "Minecraft Mod Installer is not yet supported on Mac OS X.", "Minecraft Mod Installer", JOptionPane.ERROR_MESSAGE);
			System.exit(1);*/
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Minecraft Mod Installer is not yet supported on " + System.getProperty("os.name"), "Minecraft Mod Installer", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		//
	}
	
	private void LoadFromWindows()
	{
		File minecraftFolder = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft\\versions");
		MINECRAFTVERSIONSPATH = System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft\\versions";
		System.out.println("searching in '" + minecraftFolder.getAbsolutePath() + "' for jars..");
		File[] filesList = minecraftFolder.listFiles();
		int listCount = 0;
		DefaultListModel listModel = new DefaultListModel();
		JList dlVerList = (JList) getComponentByName(frmMinecraftModInstaller.getContentPane(), "dlVerList");
		
		if(dlVerList != null)
		{
			for(File file : filesList)
			{
				File verJar = new File(file.getAbsolutePath() + "\\*.jar");
				if(verJar.getAbsolutePath().endsWith(".jar"))
				{
					listModel.addElement(file.getName());
					listCount++;
				}
			}
			if(listCount <= 0)
			{
				DefaultListModel empty = new DefaultListModel();
				empty.addElement("No versions available!");
				dlVerList.setModel(empty);
			}
			else
			{
				dlVerList.setModel(listModel);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "dlVerList is null for some reason");
		}
	}
	private void LoadFromLinux()
	{
		File minecraftFolder = new File(System.getProperty("user.home") + "/.minecraft/versions");
		MINECRAFTVERSIONSPATH = System.getProperty("user.home") + "/.minecraft/versions";
		System.out.println("searching in '" + minecraftFolder.getAbsolutePath() + "' for jars..");
		File[] filesList = minecraftFolder.listFiles();
		int listCount = 0;
		DefaultListModel listModel = new DefaultListModel();
		JList dlVerList = (JList) getComponentByName(frmMinecraftModInstaller.getContentPane(), "dlVerList");
		
		if(dlVerList != null)
		{
			for(File file : filesList)
			{
				File verJar = new File(file.getAbsolutePath() + "\\*.jar");
				if(verJar.getAbsolutePath().endsWith(".jar"))
				{
					listModel.addElement(file.getName());
					listCount++;
				}
			}
			if(listCount <= 0)
			{
				DefaultListModel empty = new DefaultListModel();
				empty.addElement("No versions available!");
				dlVerList.setModel(empty);
			}
			else
			{
				dlVerList.setModel(listModel);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "dlVerList is null for some reason");
		}
	}
	private void LoadFromOSX()
	{
		//
		OSXSpecifics();
		//
		File minecraftFolder = new File(System.getProperty("user.home") + "/Library/Application Support/minecraft/versions");
		MINECRAFTVERSIONSPATH = System.getProperty("user.home") + "/Library/Application Support/minecraft/versions";
		System.out.println("searching in '" + minecraftFolder.getAbsolutePath() + "' for jars..");
		File[] filesList = minecraftFolder.listFiles();
		int listCount = 0;
		DefaultListModel listModel = new DefaultListModel();
		JList dlVerList = (JList) getComponentByName(frmMinecraftModInstaller.getContentPane(), "dlVerList");
		
		if(dlVerList != null)
		{
			for(File file : filesList)
			{
				File verJar = new File(file.getAbsolutePath() + "\\*.jar");
				if(verJar.getAbsolutePath().endsWith(".jar"))
				{
					listModel.addElement(file.getName());
					listCount++;
				}
			}
			if(listCount <= 0)
			{
				DefaultListModel empty = new DefaultListModel();
				empty.addElement("No versions available!");
				dlVerList.setModel(empty);
			}
			else
			{
				dlVerList.setModel(listModel);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "dlVerList is null for some reason");
		}
	}
	
	/*
	 * OS X Specific Voids that we don't really need to worry about after setting
	 */
	private void OSXSpecifics()
	{
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Minecraft Mod Installer");
        com.apple.eawt.Application app = new com.apple.eawt.Application();
        app.setDockIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/icon.png")));
        try
        {
            OSXAdapter.setAboutHandler(this, getClass().getDeclaredMethod("showAbout", (Class[])null));
            OSXAdapter.setQuitHandler(this, getClass().getDeclaredMethod("quitApp", (Class[])null));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
	}
	public void showAbout()
	{
		OSXAboutDialog osx = new OSXAboutDialog();
		osx.setVisible(true);
	}
	public Boolean quitApp()
	{
		return true;
	}
	/*
	 * End OS X Specifics
	 */
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() 
	{
		frmMinecraftModInstaller = new JFrame();
		frmMinecraftModInstaller.setResizable(false);
		frmMinecraftModInstaller.setTitle("Minecraft Mod Installer - v1.0");
		frmMinecraftModInstaller.setBounds(100, 100, 453, 399);
		frmMinecraftModInstaller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuItem mnExit = new JMenuItem("Exit");
		mnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		frmMinecraftModInstaller.getContentPane().setLayout(null);
		
		JList dlVerList = new JList();
		dlVerList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dlVerList.setName("dlVerList");
		dlVerList.setModel(new AbstractListModel() {
			String[] values = new String[] {"these", "lists", "are ", "very ugly"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		dlVerList.setBounds(22, 91, 399, 211);
		frmMinecraftModInstaller.getContentPane().add(dlVerList);
		
		JLabel lblToBeginSelect = new JLabel("To begin, select one of the version profiles found below.\n");
		lblToBeginSelect.setBounds(12, 12, 429, 30);
		frmMinecraftModInstaller.getContentPane().add(lblToBeginSelect);
		
		JLabel lblIfNoneAre = new JLabel("If none are found, please run Minecraft once.");
		lblIfNoneAre.setBounds(12, 42, 409, 37);
		frmMinecraftModInstaller.getContentPane().add(lblIfNoneAre);
		
		JButton btnNext = new JButton("Next >");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String selValue = dlVerList.getSelectedValue().toString();
				nextButtonClicked(selValue);
			}
		});
		btnNext.setBounds(325, 314, 98, 26);
		frmMinecraftModInstaller.getContentPane().add(btnNext);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(215, 314, 98, 26);
		frmMinecraftModInstaller.getContentPane().add(btnExit);
		
		JButton btnRemoveSelectedProfile = new JButton("Remove Selected Profile");
		btnRemoveSelectedProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeProfileButtonClicked();
			}
		});
		btnRemoveSelectedProfile.setBounds(23, 313, 173, 26);
		frmMinecraftModInstaller.getContentPane().add(btnRemoveSelectedProfile);
		
		frmMinecraftModInstaller.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/icon.png")));
		createComponentMap();
	}
	//
	private void removeProfileButtonClicked()
	{
		int quesRes = JOptionPane.showConfirmDialog(frmMinecraftModInstaller, 
				"Are you sure you wish to delete the selected version profile?\nThis can't be undone", 
				"Minecraft Mod Installer", 
				JOptionPane.YES_NO_OPTION);
		if(quesRes == 0)
		{
			JList dlVerList = (JList)getComponentByName("dlVerList");
			try
			{
				FileUtils.deleteDirectory(new File(MINECRAFTVERSIONSPATH + File.separator + dlVerList.getSelectedValue()));
				if(OsCheck.getOperatingSystemType() == OsCheck.OSType.Windows)
					LoadFromWindows();
				else if(OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux)
					LoadFromLinux();
				else if(OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS)
					LoadFromOSX();
			} 
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(frmMinecraftModInstaller,
						"An error occurred while trying to delete the version profile!\n\nError Message: " + e.getMessage(),
						"Minecraft Mod Installer",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	//
	private void nextButtonClicked(String selectedItem)
	{
		boolean isDefaultVersion = false;
		for(String i : DefaultVersions.defaultVersions)
		{
			if(selectedItem.equals(i))
				{isDefaultVersion = true; break;}
			if(i == selectedItem)
			{
				isDefaultVersion = true;
				break;
			}
		}
		if(isDefaultVersion)
		{
			//0 is yes, 1 is no, 2 is cancel
			int questionResult = JOptionPane.showConfirmDialog(frmMinecraftModInstaller, "This profile appears to be a standard Minecraft profile. \nIf you choose to continue modding it, Minecraft will automatically re-download the Jar and overwrite any changes. \nTo avoid this, we will create a new profile. \n\nIs this okay?", "Minecraft Mod Installer", JOptionPane.YES_NO_CANCEL_OPTION);
			switch(questionResult)
			{
			case 0:
				if(OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux)
					createNewProfile_unix(selectedItem);
				else if(OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS)
					createNewProfile_osx(selectedItem);
				else if(OsCheck.getOperatingSystemType() == OsCheck.OSType.Windows)
					createNewProfile_win32(selectedItem);
				break;
			case 1:
				//the user is crazy and wants to watch the world burn, but I won't stop them
				ModSelectionWindow msw = new ModSelectionWindow(MINECRAFTVERSIONSPATH + File.separator + selectedItem, selectedItem);
				msw.setVisible(true);
				this.frmMinecraftModInstaller.setVisible(false);
				break;
			case 2:
				//cancel
				break;
			}
		}
		else
		{
			ModSelectionWindow msw = new ModSelectionWindow(MINECRAFTVERSIONSPATH + File.separator + selectedItem, selectedItem);
			msw.setVisible(true);
			this.frmMinecraftModInstaller.setVisible(false);
		}
	}
	private void createNewProfile_win32(String selectedItem)
	{
		String newProfileName = JOptionPane.showInputDialog(frmMinecraftModInstaller, 
				"Enter the name of the new profile you'd like to create", 
				"Minecraft Mod Installer", 
				JOptionPane.QUESTION_MESSAGE);
		int successOrNaw = 0; //0 will mean success, 1 will mean error
		File verFolder = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft\\versions"); //the folder that contains all the versions and such
		File jarFolder_original = new File(verFolder + "\\" + selectedItem); //The original profile to base this one off of
		File jarFolder_new = new File(verFolder + "\\" + newProfileName); //The new one that this will be copied to
		File jar_old = new File(jarFolder_new + "\\" + selectedItem + ".jar"); //The old jar which is renamed to whatever jar is
		File json_old = new File(jarFolder_new + "\\" + selectedItem + ".json"); //The old json which is renamed to whatever json is
		File jar = new File(jarFolder_new + "\\" + newProfileName + ".jar"); //The jar file in the new jarFolder
		File json = new File(jarFolder_new + "\\" + newProfileName + ".json"); //The json file in the new jarFolder
		
		try
		{
			org.apache.commons.io.FileUtils.copyDirectory(jarFolder_original, jarFolder_new);
			System.out.println("copying '" + jarFolder_original + "' to '" + jarFolder_new + "'");
			FileUtils.moveFile(jar_old, jar);
			System.out.println("copying '" + jar_old + "' to '" + jar + "'");
			FileUtils.moveFile(json_old, json);
			System.out.println("copying '" + json_old + "' to '" + json + "'");
			FileUtils.copyFile(json, new File(json.getAbsolutePath() + "_backup"));
			System.out.println("backing up the new json just in case");
			JSONEditor.JSONEditor(json.getAbsolutePath(), "id", newProfileName);
			System.out.println("editing the new json");
			successOrNaw = 0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(frmMinecraftModInstaller, 
					"There was an error while trying to create a new profile!\nException Message: " + ex.getMessage(), 
					"Minecraft Mod Installer", JOptionPane.ERROR_MESSAGE);
			successOrNaw = 1;
		}
		if(successOrNaw == 0)
			JOptionPane.showMessageDialog(frmMinecraftModInstaller,
					"Profile '" + newProfileName + "' was created successfully!",
					"",
					JOptionPane.INFORMATION_MESSAGE);
		
		ModSelectionWindow msw = new ModSelectionWindow(MINECRAFTVERSIONSPATH + File.separator + newProfileName, newProfileName);
		msw.setVisible(true);
		this.frmMinecraftModInstaller.setVisible(false);
	}
	private void createNewProfile_unix(String selectedItem)
	{
		String newProfileName = JOptionPane.showInputDialog(frmMinecraftModInstaller, 
				"Enter the name of the new profile you'd like to create", 
				"Minecraft Mod Installer", 
				JOptionPane.QUESTION_MESSAGE);
		int successOrNaw = 0; //0 will mean success, 1 will mean error
		File verFolder = new File(System.getProperty("user.home") + "/.minecraft/versions"); //the folder that contains all the versions and such
		File jarFolder_original = new File(verFolder + "/" + selectedItem); //The original profile to base this one off of
		File jarFolder_new = new File(verFolder + "/" + newProfileName); //The new one that this will be copied to
		File jar_old = new File(jarFolder_new + "/" + selectedItem + ".jar"); //The old jar which is renamed to whatever jar is
		File json_old = new File(jarFolder_new + "/" + selectedItem + ".json"); //The old json which is renamed to whatever json is
		File jar = new File(jarFolder_new + "/" + newProfileName + ".jar"); //The jar file in the new jarFolder
		File json = new File(jarFolder_new + "/" + newProfileName + ".json"); //The json file in the new jarFolder
		
		try
		{
			org.apache.commons.io.FileUtils.copyDirectory(jarFolder_original, jarFolder_new);
			System.out.println("copying '" + jarFolder_original + "' to '" + jarFolder_new + "'");
			FileUtils.moveFile(jar_old, jar);
			System.out.println("copying '" + jar_old + "' to '" + jar + "'");
			FileUtils.moveFile(json_old, json);
			System.out.println("copying '" + json_old + "' to '" + json + "'");
			FileUtils.copyFile(json, new File(json.getAbsolutePath() + "_backup"));
			System.out.println("backing up the new json just in case");
			JSONEditor.JSONEditor(json.getAbsolutePath(), "id", newProfileName);
			System.out.println("editing the new json");
			successOrNaw = 0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(frmMinecraftModInstaller, 
					"There was an error while trying to create a new profile!\nException Message: " + ex.getMessage(), 
					"Minecraft Mod Installer", JOptionPane.ERROR_MESSAGE);
			successOrNaw = 1;
		}
		if(successOrNaw == 0)
			JOptionPane.showMessageDialog(frmMinecraftModInstaller,
					"Profile '" + newProfileName + "' was created successfully!",
					"",
					JOptionPane.INFORMATION_MESSAGE);
		
		ModSelectionWindow msw = new ModSelectionWindow(MINECRAFTVERSIONSPATH + File.separator + newProfileName, newProfileName);
		msw.setVisible(true);
		this.frmMinecraftModInstaller.setVisible(false);
	}
	//
	private void createNewProfile_osx(String selectedItem)
	{
		String newProfileName = JOptionPane.showInputDialog(frmMinecraftModInstaller, 
				"Enter the name of the new profile you'd like to create", 
				"Minecraft Mod Installer", 
				JOptionPane.QUESTION_MESSAGE);
		int successOrNaw = 0; //0 will mean success, 1 will mean error
		File verFolder = new File(System.getProperty("user.home") + "/Library/Application Support/minecraft/versions"); //the folder that contains all the versions and such
		File jarFolder_original = new File(verFolder + "/" + selectedItem); //The original profile to base this one off of
		File jarFolder_new = new File(verFolder + "/" + newProfileName); //The new one that this will be copied to
		File jar_old = new File(jarFolder_new + "/" + selectedItem + ".jar"); //The old jar which is renamed to whatever jar is
		File json_old = new File(jarFolder_new + "/" + selectedItem + ".json"); //The old json which is renamed to whatever json is
		File jar = new File(jarFolder_new + "/" + newProfileName + ".jar"); //The jar file in the new jarFolder
		File json = new File(jarFolder_new + "/" + newProfileName + ".json"); //The json file in the new jarFolder
		
		try
		{
			org.apache.commons.io.FileUtils.copyDirectory(jarFolder_original, jarFolder_new);
			System.out.println("copying '" + jarFolder_original + "' to '" + jarFolder_new + "'");
			FileUtils.moveFile(jar_old, jar);
			System.out.println("copying '" + jar_old + "' to '" + jar + "'");
			FileUtils.moveFile(json_old, json);
			System.out.println("copying '" + json_old + "' to '" + json + "'");
			FileUtils.copyFile(json, new File(json.getAbsolutePath() + "_backup"));
			System.out.println("backing up the new json just in case");
			JSONEditor.JSONEditor(json.getAbsolutePath(), "id", newProfileName);
			System.out.println("editing the new json");
			successOrNaw = 0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(frmMinecraftModInstaller, 
					"There was an error while trying to create a new profile!\nException Message: " + ex.getMessage(), 
					"Minecraft Mod Installer", JOptionPane.ERROR_MESSAGE);
			successOrNaw = 1;
		}
		if(successOrNaw == 0)
			JOptionPane.showMessageDialog(frmMinecraftModInstaller,
					"Profile '" + newProfileName + "' was created successfully!",
					"",
					JOptionPane.INFORMATION_MESSAGE);
		
		ModSelectionWindow msw = new ModSelectionWindow(MINECRAFTVERSIONSPATH + File.separator + newProfileName, newProfileName);
		msw.setVisible(true);
		this.frmMinecraftModInstaller.setVisible(false);
	}
	//
	private void createComponentMap() 
	{
        componentMap = new HashMap<String,Component>();
        Component[] components = frmMinecraftModInstaller.getContentPane().getComponents();
        for (int i=0; i < components.length; i++) {
                componentMap.put(components[i].getName(), components[i]);
        }
	}
	private Component getComponentByName(String name) 
	{ 
		return getComponentByName(frmMinecraftModInstaller.getRootPane(), name); 
	} 
	private Component getComponentByName(Container root, String name) 
	{ 
		for (Component c : root.getComponents()) 
		{ 
			if (name.equals(c.getName())) 
			{ 
				return c; 
			} 
			if (c instanceof Container) 
			{ 
				Component result = getComponentByName((Container) c, name); 
				if (result != null) 
				{ 
					return result; 
				} 
			} 
		} 
		return null; 
	}
}
