package test;

import model.Client;
import model.Trainer;
import model.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.InMemoryTrainingRepository;
import repository.TrainingRepository;
import service.FitnessCenter;

import static org.junit.jupiter.api.Assertions.*;

class FitnessCenterTest {

    private FitnessCenter fitnessCenter;
    private TrainingRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTrainingRepository();
        fitnessCenter = new FitnessCenter(repository);
    }


    @Test
    void testRegisterClientSuccess() {
        Trainer trainer = new Trainer(2, "Anna");
        Training training = new Training(1, "Yoga", trainer, 5);
        repository.save(training);
        Client client = new Client(1, "Ivan");

        boolean result = fitnessCenter.registerClient(1, client);

        assertTrue(result);
        assertEquals(1, training.getParticipants().size());
        assertEquals("Ivan", training.getParticipants().get(0).getName());
    }


    @Test
    void testRegisterClientDuplicate() {
        Trainer trainer = new Trainer(1, "Anna");
        Training training = new Training(2, "Boxing", trainer, 5);
        repository.save(training);

        Client client = new Client(3, "Ivan");

        assertTrue(fitnessCenter.registerClient(2, client));
        assertFalse(fitnessCenter.registerClient(2, client),
                "Повторная запись того же клиента запрещена");
        assertEquals(1, training.getParticipants().size());
    }


    @Test
    void testRegisterClientWhenFull() {
        Trainer trainer = new Trainer(1, "Anna");
        Training training = new Training(3, "Pilates", trainer, 2);
        repository.save(training);

        Client client1 = new Client(1, "Ivan");
        Client client2 = new Client(2, "Petr");
        Client client3 = new Client(3, "Alex");



        assertTrue(fitnessCenter.registerClient(3, client1));
        //assertFalse(fitnessCenter.registerClient(3, client1));
        assertTrue(fitnessCenter.registerClient(3, client2));




        assertFalse(fitnessCenter.registerClient(3, client3),
                "Запись должна быть отклонена: мест нет");

        //assertEquals(2, training.getParticipants().size());
        assertEquals(10, training.getParticipants().size());
    }
    @Test
    void testRegisterClientToNonExistentTraining() {
        Client client = new Client(5, "Dmitry");
        boolean result = fitnessCenter.registerClient(999, client);
        assertFalse(result, "Запись должна быть отклонена: тренировки нет в расписании");
    }
}