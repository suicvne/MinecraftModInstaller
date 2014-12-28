import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.commons.io.FileUtils;


public class ModSelectionWindow extends JFrame
{

	private JPanel contentPane;
	private HashMap componentMap;
	private String SELECTEDPROFILEPATH;
	private String SELECTEDPROFILENAME;

	/**
	 * Create the frame.
	 */
	public ModSelectionWindow(String _SELECTEDPROFILEPATH, String _SELECTEDPROFILENAME)
	{
		setResizable(false);
		SELECTEDPROFILEPATH = _SELECTEDPROFILEPATH;
		SELECTEDPROFILENAME = _SELECTEDPROFILENAME;
		
		setTitle("Minecraft Mod Installer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNextWeWill = new JLabel("Next, we will select the mods we wish to install");
		lblNextWeWill.setBounds(10, 11, 411, 23);
		contentPane.add(lblNextWeWill);
		
		JLabel lblPleaseAddThe = new JLabel("Please add the mods you wish to add (in zip format) to the list");
		lblPleaseAddThe.setBounds(10, 42, 411, 23);
		contentPane.add(lblPleaseAddThe);
		
		
		JButton btnNext = new JButton("Next >");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextButtonClick();
			}
		});
		btnNext.setBounds(332, 371, 89, 23);
		contentPane.add(btnNext);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		btnExit.setBounds(233, 371, 89, 23);
		contentPane.add(btnExit);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				addModButtonClick();
			}
		});
		button.setBounds(10, 303, 41, 23);
		contentPane.add(button);
		JButton button_1 = new JButton("-");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				removeModButtonClick();
			}
		});
		button_1.setBounds(61, 303, 41, 23);
		contentPane.add(button_1);
		
		JList list = new JList();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setName("modZipList");
		list.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				if(list.getComponentCount() >= 0)
				{	
					button_1.setEnabled(true);
					btnNext.setEnabled(true);
				}
				else
				{	
					button_1.setEnabled(false);
					btnNext.setEnabled(false);
				}
			}
			@Override
			public void componentRemoved(ContainerEvent e) {
				if(list.getComponentCount() >= 0)
				{
					button_1.setEnabled(true);
					btnNext.setEnabled(false);
				}
				else
				{	
					button_1.setEnabled(false);
					btnNext.setEnabled(false);
				}
			}
		});
		list.setBounds(10, 100, 411, 193);
		contentPane.add(list);
		
		JLabel lblNoteTheBottom = new JLabel("NOTE: the bottom most mods will override the top most");
		lblNoteTheBottom.setBounds(10, 66, 411, 23);
		contentPane.add(lblNoteTheBottom);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setName("mainProgressBar");
		progressBar.setVisible(false);
		progressBar.setBounds(10, 337, 411, 23);
		contentPane.add(progressBar);
		
		JLabel lblProgress = new JLabel("progress");
		lblProgress.setVisible(false);
		lblProgress.setName("mainProgressLabel");
		lblProgress.setBounds(10, 375, 213, 14);
		contentPane.add(lblProgress);
	}
	
	private void removeModButtonClick()
	{
		JList modZipList = (JList)getComponentByName("modZipList");
		try
		{
			if(modZipList.getComponentCount() > 0)
			{
				int sel = modZipList.getSelectedIndex();
				DefaultListModel model = (DefaultListModel) modZipList.getModel();
				model.remove(sel);
				modZipList.setModel(model);
			}
			else
			{
				System.out.println("user is trying to remove a null item apparently");
			}
		}
		catch(Exception ex)
		{
			System.out.println("user is trying to remove a null item, stop this madness");
		}
	}
	
	private void nextButtonClick()
	{
		JList modZipList = (JList)getComponentByName("modZipList");
		ListModel model = modZipList.getModel();
		if(model.getSize() == 0)
		{
			JOptionPane.showMessageDialog(contentPane, 
					"Please add some mods before trying to continue!", 
					"Minecraft Mod Installer", 
					JOptionPane.ERROR_MESSAGE);
		}
		else if(model.getSize() >= 1)
		{
			///TODO: begin the mod installation process
			///1. rename jar to zip for extraction to a temp folder
			///2. extract individual mods to one temp folder
			///3. recompress jar to zip
			///4. backup old jar
			///5. rename the recompressed zip to jar, deleting the old jar if necessary
			JProgressBar progBar = (JProgressBar)getComponentByName("mainProgressBar");
			JLabel progLabel = (JLabel)getComponentByName("mainProgressLabel");
			progBar.setVisible(true);
			progLabel.setVisible(true);
			progLabel.setText("Preparing to install mods..");
			installMods(progLabel, progBar);
		}
	}
	
	private void installMods(JLabel progLabel, JProgressBar progBar)
	{
		JList modZipList = (JList)getComponentByName("modZipList");
		File versionJar = new File(SELECTEDPROFILEPATH + File.separator + SELECTEDPROFILENAME + ".jar");
		File versionJarAsZip = new File(SELECTEDPROFILEPATH + File.separator + SELECTEDPROFILENAME + ".zip");
		File versionJar_backup = new File(versionJar + "_backup");
		File dirJarExtraction = new File(SELECTEDPROFILEPATH + File.separator + "._tempJarFolder");
		File dirTempModExtraction = new File(SELECTEDPROFILEPATH + File.separator + "._tempModFolder");
		
		int SUCCESS = 1; //0 meaning success, 1 meaning error
		
		try
		{
			//1. Backup
			progLabel.setText("Backing up the current jar..");
			System.out.println("backing up current jar..");
			FileUtils.copyFile(versionJar, versionJar_backup);
			
			//2. rename and extract
			progLabel.setText("Extracting jar to temp directory..");
			System.out.println("extracting jar to temp directory..");
			if(versionJarAsZip.exists())
				versionJarAsZip.delete();
			FileUtils.moveFile(versionJar, versionJarAsZip);
			if(!dirJarExtraction.exists())
				dirJarExtraction.mkdir();
			ZipFile verJarZip = new ZipFile(versionJarAsZip.getAbsolutePath());
			verJarZip.extractAll(dirJarExtraction.getAbsolutePath());
			
			//3. Extract mods
			progLabel.setText("Extracting mods to temp directory..");
			System.out.println("extracting mods to temp directory..");
			if(!dirTempModExtraction.exists())
				dirTempModExtraction.mkdir();
			ZipFile curModZip;
			ListModel model = modZipList.getModel();
			for(int i = 0; i < model.getSize(); i++)
			{
				progLabel.setText("Extracting mods to temp directory " + "(" + i + "/" + model.getSize() + ")..");
				System.out.println("extracting mod " + i + " of " + model.getSize());
				String path = model.getElementAt(i).toString();
				curModZip = new ZipFile(path);
				curModZip.extractAll(dirTempModExtraction.getAbsolutePath());
			}
			
			//4. Move files from temp mod dir into the temp jar one
			progLabel.setText("Moving files into jar..");
			System.out.println("moving files into jar..");
			File[] tempModDirFiles = new File(dirTempModExtraction.getAbsolutePath()).listFiles();
			for(File i : tempModDirFiles)
			{
				progLabel.setText("Moving files into jar (" + i.getName() + ")..");
				System.out.println("moving file " + i.getName());
				if(new File(dirJarExtraction + File.separator + i.getName()).exists())
				{
					System.out.println("!!! overriding base class " + i.getName());
					File sysClassOverride = new File(dirJarExtraction + File.separator + i.getName());
					sysClassOverride.delete();
				}
				FileUtils.moveFile(i, new File(dirJarExtraction + File.separator + i.getName()));
			}
			tempModDirFiles = null;
			progLabel.setText("Deleting META-INF..");
			System.out.println("deleting meta-inf..");
			File METAINF = new File(dirJarExtraction.getAbsolutePath() + File.separator + "META-INF");
			FileUtils.deleteDirectory(METAINF);
			
			//5. Recompress jar into zip
			progLabel.setText("Recompressing jar..");
			System.out.println("recompressing jar..");
			File[] tempJarFiles = new File(dirJarExtraction.getAbsolutePath()).listFiles();
			ZipFile tempZip = new ZipFile(SELECTEDPROFILEPATH + File.separator + "._TEMP.ZIP");
			ZipParameters p = new ZipParameters();
			p.setCompressionMethod(Zip4jConstants.COMP_STORE);
			for(File i : tempJarFiles)
			{
				if(i.isDirectory())
				{
					tempZip.addFolder(i, p);
				}
				tempZip.addFile(i, p);
			}
			
			//6. Move into place
			System.out.println("moving everything into place..");
			versionJar.delete();
			File tempZipAsFile = tempZip.getFile();
			FileUtils.moveFile(tempZipAsFile, versionJar);
			
			//7. Delete temp dirs, but keep backups
			FileUtils.deleteDirectory(dirJarExtraction);
			FileUtils.deleteDirectory(dirTempModExtraction);
			versionJarAsZip.delete();
			
			SUCCESS = 0;
			System.out.println("looks like it was a success!");
		} 
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(contentPane, 
					"There was an error trying to install the mods!\n\nException Message: " + e.getMessage(), 
					"Minecraft Mod Installer", 
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			SUCCESS = 1;
			progLabel.setVisible(false);
			progBar.setVisible(false);
		} 
		catch (ZipException e)
		{
			JOptionPane.showMessageDialog(contentPane, 
					"There was an error trying to extract the jar!\n\nException Message: " + e.getMessage(), 
					"Minecraft Mod Installer", 
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			SUCCESS = 1;
			progLabel.setVisible(false);
			progBar.setVisible(false);
		}
		
		if(SUCCESS == 0)
		{
			JOptionPane.showMessageDialog(contentPane, 
					"Installed " + modZipList.getModel().getSize() + " mods successfully! Enjoy!", 
					"Minecraft Mod Installer", 
					JOptionPane.INFORMATION_MESSAGE);
			progLabel.setVisible(false);
			progBar.setVisible(false);
		}
		if(SUCCESS == 1)
		{
			try
			{
				FileUtils.moveFile(versionJar_backup, versionJar);
				FileUtils.deleteDirectory(dirJarExtraction);
				FileUtils.deleteDirectory(dirTempModExtraction);
				versionJarAsZip.delete();
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(contentPane,
						"There was an error trying to restore the Jar backup!\n\nError Message: " + e.getMessage(),
						"Minecraft Mod Installer",
						JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	private void addModButtonClick()
	{
		JFileChooser chooser = new JFileChooser();
		JList modZipList = (JList)getComponentByName("modZipList");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Zip Files", "zip");
		chooser.setDialogTitle("Select Mod Zips");
		chooser.setFileFilter(filter);
		chooser.setDialogType(JFileChooser.CUSTOM_DIALOG);
		chooser.setApproveButtonText("Add");
		chooser.setMultiSelectionEnabled(true);
		int result = chooser.showOpenDialog(contentPane);
		if(result == 0) //0 means yes hopefully
		{
			DefaultListModel addedZip = new DefaultListModel();
			File[] selectedFiles = chooser.getSelectedFiles();
			if(selectedFiles.length == 1)
			{
				addedZip.addElement(selectedFiles[0]);
			}
			else //iterate through
			{
				for(File i : selectedFiles)
				{
					addedZip.addElement(i.getAbsolutePath());
				}
			}
			modZipList.setModel(addedZip);
		}
		
	}
	
	private void createComponentMap() 
	{
        componentMap = new HashMap<String,Component>();
        Component[] components = contentPane.getRootPane().getComponents();
        for (int i=0; i < components.length; i++) {
                componentMap.put(components[i].getName(), components[i]);
        }
	}
	private Component getComponentByName(String name) 
	{ 
		return getComponentByName(contentPane.getRootPane(), name); 
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
	
	abstract class ZipFileFilter extends javax.swing.filechooser.FileFilter 
	{
	    public boolean accept(File f) 
	    {
	      return f.isFile() && f.getName().toLowerCase().endsWith(".zip");
	    }

	    public String getDescription() 
	    {
	      return "*.zip";
	    }
	}
}

