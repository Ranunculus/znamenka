package ru.znamenka.api.domain;

import lombok.Getter;
import lombok.Setter;
import ru.znamenka.api.BaseApi;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class PaymentApi implements BaseApi {

    @Getter
    @NotNull
    @Setter
    private Long amount;

    @Getter
    @NotNull
    @Setter
    private Long purchaseId;

    @Getter @Setter
    private Timestamp paymentDate;
}
