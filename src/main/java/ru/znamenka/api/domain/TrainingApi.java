package ru.znamenka.api.domain;

import lombok.Getter;
import lombok.Setter;
import ru.znamenka.api.BaseApi;

import java.sql.Timestamp;

/**
 * <p>
 * <p>
 * Создан 01.08.2016
 * <p>
 * Изменения:
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */

public class TrainingApi implements BaseApi {



    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long trainerId;

    @Getter @Setter
    private Timestamp start;


    @Getter @Setter
    private Long clientId;


    @Getter @Setter
    private Long purchaseId;
}
