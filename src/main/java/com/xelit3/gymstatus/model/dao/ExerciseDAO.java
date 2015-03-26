package com.xelit3.gymstatus.model.dao;

import java.util.List;

import com.xelit3.gymstatus.model.dto.Exercise;

public abstract class ExerciseDAO extends GenericGymStatDAO {
	
	public abstract boolean saveExercise(Exercise e);
	public abstract boolean updateExercise(int id, Exercise newExercise);
	public abstract Exercise getExercise(int id);
	public abstract List<Exercise> getExercises();
	public abstract boolean deleteExercise(int id);
		
	
}
