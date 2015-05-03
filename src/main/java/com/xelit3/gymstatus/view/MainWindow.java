package com.xelit3.gymstatus.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.view.exercises.ExerciseManagementPanel;
import com.xelit3.gymstatus.view.exercises.ExerciseListPanel;
import com.xelit3.gymstatus.view.exercises.ExerciseStatusPanel;
import com.xelit3.gymstatus.view.routines.RoutineCreationPanel;
import com.xelit3.gymstatus.view.routines.TableRoutines;
import com.xelit3.gymstatus.view.routines.TableRoutinesModel;

public class MainWindow extends JFrame implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	public MainWindow(Controller controller){
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setResizable(false);
		setUndecorated(true);
		setRootPane(this.createRootPane());
				
		buildMenu();
		
	    setMovementKeys();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//TODO Aprovechar metodo update
	}
	
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
				
			case "listExercises":
				ExerciseListPanel listPanel = new ExerciseListPanel();
				setContentPane(listPanel);
				listPanel.updateUI();
				break;
				
			case "manageExercises":
				exercisePanel = new ExerciseManagementPanel();	
				setContentPane(exercisePanel);
				exercisePanel.updateUI();		
				break;
				
			case "manageExercisesStatus":
				ExerciseStatusPanel exerciseStatPanel = new ExerciseStatusPanel();
				setContentPane(exerciseStatPanel);
				this.refreshWindow(exerciseStatPanel);
				break;
				
			case "listRoutines":
				TableRoutinesModel model = new TableRoutinesModel();
				TableRoutines tableRoutines = new TableRoutines(model);
				JScrollPane scrollPane = new JScrollPane(tableRoutines);
//				scrollPane.setViewportView(tableRoutines);
				setContentPane(scrollPane);
				this.refreshWindow(scrollPane);
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
		// TODO Auto-generated method stub
		return super.getX();
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#getY()
	 */
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return super.getY();
	}
	
	private void refreshWindow(Component aComponent){
		aComponent.repaint();
		aComponent.revalidate();
		
		this.getRootPane().repaint();
		this.getRootPane().revalidate();		
	}
	
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
		
		mitem = new JMenuItem("Abount us");
		mitem.setActionCommand("aboutUs");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		menubar.add(menu);
		
		//Exercise menu
		menu = new JMenu("Exercise");
		
		mitem = new JMenuItem("List Exercise");
		mitem.setActionCommand("listExercises");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("Manage Exercises");
		mitem.setActionCommand("manageExercises");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("Manage Exercise status");
		mitem.setActionCommand("manageExercisesStatus");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		menubar.add(menu);
		
		//Routine menu
		menu = new JMenu("Routine");
				
		mitem = new JMenuItem("List Routines");
		mitem.setActionCommand("listRoutines");
		mitem.addActionListener(this);
		menu.add(mitem);
				
		mitem = new JMenuItem("Create Routine");
		mitem.setActionCommand("createRoutine");
		mitem.addActionListener(this);
		menu.add(mitem);
				
		menubar.add(menu);
		
		this.setJMenuBar(menubar);
	}
	
	private void setMovementKeys() {
		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED), "moveRight");
	    this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED), "moveLeft");
	    this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.VK_UNDEFINED), "moveDown");
	    this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.VK_UNDEFINED), "moveUp");
	    this.getRootPane().getActionMap().put("moveRight", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	setLocation(getX() + 5, getY());
	        }
	    });
	    this.getRootPane().getActionMap().put("moveLeft", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	setLocation(getX() - 5, getY());
	        }
	    });
	    this.getRootPane().getActionMap().put("moveDown", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	setLocation(getX(), getY() + 5);
	        }
	    });
	    this.getRootPane().getActionMap().put("moveUp", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	setLocation(getX(), getY() - 5);
	        }
	    });
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);	
	}
	

}
