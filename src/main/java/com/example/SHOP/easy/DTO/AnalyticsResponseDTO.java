package com.example.SHOP.easy.DTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnalyticsResponseDTO {

    Long placed;

    Long shipped;

    Long delivered;

    Long currentMonthOrders;

    Long previousMonthOrders;

    Long currentMonthEarnings;

    Long previousMonthEarnings;
}
