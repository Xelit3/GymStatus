package com.xelit3.gymstatus.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.xelit3.gymstatus.control.settings.AppSettings;
import com.xelit3.gymstatus.view.exercises.ExerciseManagementPanel;
import com.xelit3.gymstatus.view.exercises.ExerciseStatusListPanel;
import com.xelit3.gymstatus.view.exercises.ExerciseStatusManagementPanel;
import com.xelit3.gymstatus.view.routines.RoutineCreationPanel;
import com.xelit3.gymstatus.view.routines.TableRoutines;
import com.xelit3.gymstatus.view.routines.TableRoutinesModel;
import com.xelit3.gymstatus.view.routines.TableRoutinesPanel;
import com.xelit3.gymstatus.view.settings.ConfigurationPanel;

/**
 * MainWindow is main JFRame for the application.
 * Is initialized from the Controller
 * @see com.xelit3.gymstatus.control.Controller
 */
public class MainWindow extends JFrame implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/**
	 * Instantiates a new main window.
	 *
	 * @param aController the a controller
	 */
	public MainWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 640, 480);
		setResizable(false);
//		setUndecorated(true);
		setRootPane(this.createRootPane());
		setTitle("ChustaSoft GymStatus App - " + AppSettings.getInstance().getUsername());
		setFramePositionSettings();
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				setFramePositionSettings();				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
				
		buildMenu();
		
	    setMovementKeys();
	}

	/**
	 * Sets the frame position settings into the singleton AppSettings
	 */
	private void setFramePositionSettings() {
		AppSettings.getInstance().setMainWindowPosX(this.getX());
		AppSettings.getInstance().setMainWindowPosY(this.getY());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		ExerciseManagementPanel exercisePanel;
		switch(event.getActionCommand()){
		
			case "exit":
				System.exit(0);
				break;
				
			case "aboutUs":
				this.showMsg("Xavi Rueda\nChustaSoft\nGymStatus App\n2014-2015");
				break;
				
			case "appConfig":
				ConfigurationPanel configPanel = new ConfigurationPanel();
				setContentPane(configPanel);
				configPanel.updateUI();
				break;
				
			case "listExercises":
				ExerciseStatusListPanel listPanel = new ExerciseStatusListPanel();
				setContentPane(listPanel);
				listPanel.updateUI();
				break;
				
			case "manageExercises":
				exercisePanel = new ExerciseManagementPanel();	
				setContentPane(exercisePanel);
				exercisePanel.updateUI();		
				break;
				
			case "manageExercisesStatus":
				ExerciseStatusManagementPanel exerciseStatPanel = new ExerciseStatusManagementPanel();
				setContentPane(exerciseStatPanel);
				this.refreshWindow(exerciseStatPanel);
				break;
				
			case "listRoutines":
				TableRoutinesPanel routinesPanel = new TableRoutinesPanel(new TableRoutines(new TableRoutinesModel()));
				setContentPane(routinesPanel);
				routinesPanel.updateUI();
				this.refreshWindow(routinesPanel);
				break;
				
			case "createRoutine":
				RoutineCreationPanel routinePanel = new RoutineCreationPanel();
				setContentPane(routinePanel);
				routinePanel.updateUI();
				break;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#getX()
	 */
	@Override
	public int getX() {
		return super.getX();
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#getY()
	 */
	@Override
	public int getY() {
		return super.getY();
	}
	
	/**
	 * Refresh window.
	 *
	 * @param aComponent the a component
	 */
	private void refreshWindow(Component aComponent){
		aComponent.repaint();
		aComponent.revalidate();
		
		this.getRootPane().repaint();
		this.getRootPane().revalidate();		
	}
	
	/**
	 * This method builds the Top bar menu
	 */
	private void buildMenu(){
		JMenuBar menubar = new JMenuBar();
		JMenu menu;
		JMenuItem mitem;
		
		//Application menu
		menu = new JMenu("Application");
		
		mitem = new JMenuItem("Exit");
		mitem.setActionCommand("exit");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("Configuration");
		mitem.setActionCommand("appConfig");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("Abount us");
		mitem.setActionCommand("aboutUs");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		menubar.add(menu);
		
		//Exercise menu
		menu = new JMenu("Exercise");
		
		mitem = new JMenuItem("Manage Exercises");
		mitem.setActionCommand("manageExercises");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("Manage Exercise status");
		mitem.setActionCommand("manageExercisesStatus");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("List Exercise Status");
		mitem.setActionCommand("listExercises");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		menubar.add(menu);
		
		//Routine menu
		menu = new JMenu("Routine");
				
		mitem = new JMenuItem("Create Routine");
		mitem.setActionCommand("createRoutine");
		mitem.addActionListener(this);
		menu.add(mitem);
				
		mitem = new JMenuItem("List Routines");
		mitem.setActionCommand("listRoutines");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		menubar.add(menu);
		
		this.setJMenuBar(menubar);
	}
	
	/**
	 * This mehod allow this Window to be moved by the keyboard cursors
	 */
	private void setMovementKeys() {
		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED), "moveRight");
	    this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED), "moveLeft");
	    this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.VK_UNDEFINED), "moveDown");
	    this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.VK_UNDEFINED), "moveUp");
	    this.getRootPane().getActionMap().put("moveRight", new AbstractAction() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public void actionPerformed(ActionEvent e) {
	        	setLocation(getX() + 5, getY());
	        }
	    });
	    this.getRootPane().getActionMap().put("moveLeft", new AbstractAction() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public void actionPerformed(ActionEvent e) {
	        	setLocation(getX() - 5, getY());
	        }
	    });
	    this.getRootPane().getActionMap().put("moveDown", new AbstractAction() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public void actionPerformed(ActionEvent e) {
	        	setLocation(getX(), getY() + 5);
	        }
	    });
	    this.getRootPane().getActionMap().put("moveUp", new AbstractAction() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public void actionPerformed(ActionEvent e) {
	        	setLocation(getX(), getY() - 5);
	        }
	    });
	}	
	
	/**
	 * Just to show a JOptionPane with a message
	 *
	 * @param msg the msg
	 */
	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);	
	}
	

}
