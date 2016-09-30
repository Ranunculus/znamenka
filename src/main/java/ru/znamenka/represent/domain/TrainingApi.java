package ru.znamenka.represent.domain;

import lombok.Getter;
import lombok.Setter;
import ru.znamenka.represent.DomainApi;
import ru.znamenka.represent.UpdatableApi;

import java.sql.Timestamp;

/**
 * <p>Представление для бизнес-модели Тренировка
 * <p>
 * Создан 01.08.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */

public class TrainingApi implements DomainApi, UpdatableApi<Long> {

    public static TrainingApi empty() {
        return new TrainingApi();
    }

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long trainerId;

    @Getter @Setter
    private String trainerName;

    @Getter @Setter
    private Timestamp start;

    @Getter @Setter
    private Timestamp end;

    @Getter @Setter
    private Long clientId;

    @Getter @Setter
    private String clientName;

    @Getter @Setter
    private Long purchaseId;

    @Getter @Setter
    private Long statusId;

    @Getter @Setter
    private String statusName;
}
