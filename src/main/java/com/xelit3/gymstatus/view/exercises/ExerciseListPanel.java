package com.xelit3.gymstatus.view.exercises;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExerciseStatus;

public class ExerciseListPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane mainSplitPane;
	private Controller mainController = new Controller();
	private JTable exercisesTable;
	
	public ExerciseListPanel() {
		super(new BorderLayout());
		mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		mainSplitPane.setEnabled(false);
				
		JSplitPane buttonsExerciseTypePanel = new JSplitPane();
		buttonsExerciseTypePanel.setResizeWeight(0.5);
		
		JButton btn_listCardioEx = new JButton("Cardio Exercises");
		btn_listCardioEx.setActionCommand("btnListCardio");
		btn_listCardioEx.addActionListener(this);
		buttonsExerciseTypePanel.setLeftComponent(btn_listCardioEx);
		JButton btn_listFitnessEx = new JButton("Fitness Exercises");
		btn_listFitnessEx.setActionCommand("btnListFitness");
		btn_listFitnessEx.addActionListener(this);
		buttonsExerciseTypePanel.setRightComponent(btn_listFitnessEx);
		buttonsExerciseTypePanel.setEnabled(false);
		
		mainSplitPane.setLeftComponent(buttonsExerciseTypePanel);
		
		showExercises(CardioExerciseStatus.class);
						
		add(mainSplitPane, BorderLayout.CENTER);
       
	}	

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()){
			case "btnListCardio":
				showExercises(CardioExerciseStatus.class);
				break;
				
			case "btnListFitness":
				showExercises(FitnessExerciseStatus.class);
				break;	
		
		}		
	}
	
	public void showExercises(Class<?> exerciseClass){
		List<Exercise> exercisesList = mainController.getExercises(exerciseClass);
		//We show a different table depending of the class we recive, expecting for a CardioExerciseStatus or FitnessExerciseStatus
		switch(exerciseClass.getSimpleName()){
			case "CardioExerciseStatus":
				exercisesTable = new TableCardioExercise(new CardioExerciseTableModel(exercisesList));
				break;
				
			case "FitnessExerciseStatus":				
				exercisesTable = new TableFitnessExercise(new FitnessExerciseTableModel(exercisesList));
				break;
		}
		JScrollPane scrollPane = new JScrollPane(exercisesTable);
		exercisesTable.setFillsViewportHeight(true);
		
		mainSplitPane.setRightComponent(scrollPane);
	}	

}
