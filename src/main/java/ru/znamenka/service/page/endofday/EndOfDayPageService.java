package ru.znamenka.service.page.endofday;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.znamenka.api.domain.TrainingApi;
import ru.znamenka.service.ApiStore;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.util.Assert.notNull;
import static ru.znamenka.jpa.model.QTraining.training;
import static ru.znamenka.util.Utils.fromLocalDate;

/**
 * <p>
 * <p>
 * Создан 26.08.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class EndOfDayPageService {

    private final ApiStore apiStore;

    @Autowired
    public EndOfDayPageService(@Qualifier("dataService") ApiStore apiStore) {
        notNull(apiStore);
        this.apiStore = apiStore;
    }

    public List<TrainingApi> getTrainings(LocalDate date, Long trainerId) {
        Predicate predicate = training.status.id.eq(1L)
                .and(training.start.eq(fromLocalDate(date)))
                .and(training.trainer.id.eq(trainerId));
        return apiStore.findAll(TrainingApi.class, predicate);
    }
}
