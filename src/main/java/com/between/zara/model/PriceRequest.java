package com.between.zara.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    private Date applicationDate;

    @PositiveOrZero(message = "Product Id must be positive value")
    @NotNull(message = "Product Id cannot be null")
    private Long productId;

    @PositiveOrZero(message = "Brand Id must be positive value")
    @NotNull(message = "Brand Id cannot be null")
    private Long brandId;
}
