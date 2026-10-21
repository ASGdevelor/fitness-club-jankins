package repository;

import model.Training;

public interface TrainingRepository {
    void save(Training training);
    Training findById(int id);
}