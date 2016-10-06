package ru.znamenka.represent.converter.domain;

import lombok.val;
import org.springframework.stereotype.Component;
import ru.znamenka.jpa.model.Training;
import ru.znamenka.represent.converter.UpdatableApiConverter;
import ru.znamenka.represent.domain.TrainingApi;

/**
 * Created by Сережа on 10.08.2016.
 */
@Component
public class TrainingApiConverter implements UpdatableApiConverter<Training, TrainingApi, Long> {

    @Override
    public Class<TrainingApi> getApiType() {
        return TrainingApi.class;
    }

    @Override
    public Class<Training> getEntityType() {
        return Training.class;
    }

    @Override
    public Training convertTo(TrainingApi source) {
        Training training = new Training();
        training.setClientId(source.getClientId());
        training.setTrainerId(source.getTrainerId());
        training.setPurchaseId(source.getPurchaseId());
        training.setStart(source.getStart());
        training.setStatusId(source.getStatusId());
        training.setComment(source.getComment());
        return training;
    }

    @Override
    public TrainingApi convert(Training training) {
        TrainingApi api = new TrainingApi();
        api.setClientId(training.getClientId());
        api.setClientName(training.getClient().getName() + " " + training.getClient().getSurname());
        api.setTrainerId(training.getTrainerId());
        api.setTrainerName(training.getTrainer().getName());
        api.setId(training.getId());
        api.setPurchaseId(training.getPurchase().getId());
        api.setStatusId(training.getStatus().getId());
        api.setStatusName(training.getStatus().getName());
        api.setStart(training.getStart());
        api.setComment(training.getComment());
        return api;
    }


    @Override
    public Training convertTo(TrainingApi source, Training entity) {
        val statusId = source.getStatusId();
        if (statusId != null) {
            entity.setStatusId(statusId);
        }
        val start = source.getStart();
        if (start != null) {
            entity.setStart(start);
        }
        val trainerId = source.getTrainerId();
        if (trainerId != null) {
            entity.setTrainerId(trainerId);
        }
        val comment = source.getComment();
        if (comment != null) {
            entity.setComment(comment);
        }

        return entity;
    }
}
