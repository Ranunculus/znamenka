package ru.znamenka.api.converter;


import org.springframework.core.convert.converter.Converter;
import ru.znamenka.api.BaseApi;
import ru.znamenka.jpa.model.BaseModel;

/**
 * <p>
 * Интерфейс конвертера для преобразования моделей {@link BaseModel}
 * в представления {@link BaseApi}. Служит для связи представления
 * и модели.
 * <p>
 * Связан с классом представления как один-ко-одному,
 * т.е. на каждое представление строго один конвертер модели в это представление
 * и обратно.
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface ApiConverter<E extends BaseModel, A extends BaseApi> extends Converter<E, A> {

    /**
     * Возвращает тип модели
     *
     * @return тип модели
     */
    Class<E> getEntityType();

    /**
     * Возвращает тип представления
     * @return тип представления
     */
    Class<A> getApiType();

    /**
     * Конвертирует в обратную сторону
     * {@inheritDoc}
     * @param source
     * @return
     */
    E convertTo(A source);

}
