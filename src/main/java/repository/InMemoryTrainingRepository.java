package repository;

import model.Training;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTrainingRepository implements TrainingRepository {

    private final Map<Integer, Training> trainings = new HashMap<>();

    @Override
    public void save(Training training) {
        trainings.put(training.getId(), training);
    }

    @Override
    public Training findById(int id) {
        return trainings.get(id);
    }
}