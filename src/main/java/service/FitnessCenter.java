package service;

import model.Client;
import model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.TrainingRepository;
//import java.util.*;

public class FitnessCenter {

    private static final Logger logger =
            LoggerFactory.getLogger(FitnessCenter.class);

    private final TrainingRepository repository;

    public FitnessCenter(TrainingRepository repository) {
        this.repository = repository;
    }

    public boolean registerClient(int trainingId, Client client) {

        logger.info("Попытка записи клиента {} на тренировку {}",
                client.getName(), trainingId);

        Training training = repository.findById(trainingId);

        if (training == null) {
            logger.warn("Тренировка с ID {} не найдена", trainingId);
            return false;
        }

        if (training.getParticipants().size() >= training.getMaxParticipants()) {
            logger.warn("Нет свободных мест на тренировке ID {}", trainingId);
            return false;
        }

        boolean alreadyEnrolled = training.getParticipants().stream()
                .anyMatch(c -> c.getId() == client.getId());

        if (alreadyEnrolled) {
            logger.warn("Клиент {} уже записан на тренировку {}",
                    client.getName(), trainingId);
            return false;
        }

        training.getParticipants().add(client);

        logger.info("Клиент {} успешно записан на тренировку {}",
                client.getName(), trainingId);

        return true;
    }
}
