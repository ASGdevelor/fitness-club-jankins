package model;

import java.util.ArrayList;
import java.util.List;

public class Training {
    private final int id;
    private final String name;
    private final Trainer trainer;
    private final int maxParticipants;

    private final List<Client> participants = new ArrayList<>();

    public Training(int id, String name, Trainer trainer, int maxParticipants) {
        this.id = id;
        this.name = name;
        this.trainer = trainer;
        this.maxParticipants = maxParticipants;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public List<Client> getParticipants() {
        return participants;
    }
}