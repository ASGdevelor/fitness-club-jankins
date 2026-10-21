package service;

import model.Client;
import model.Training;
import repository.TrainingRepository;

public class FitnessCenter {

    private final TrainingRepository repository;


    public FitnessCenter(TrainingRepository repository) {
        this.repository = repository;
    }

    public boolean registerClient(int trainingId, Client client) {
        Training training = repository.findById(trainingId);

        if (training == null) {
            return false;
        }

        if (training.getParticipants().size() >= training.getMaxParticipants()) {
            return false;
        }


        boolean alreadyEnrolled = training.getParticipants().stream()
                .anyMatch(c -> c.getId() == client.getId());
        if (alreadyEnrolled) {
            return false;
        }

        training.getParticipants().add(client);
        return true;
    }
}