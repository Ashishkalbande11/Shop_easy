package com.example.SHOP.easy.Services.Admin.AdminOrders;

import com.example.SHOP.easy.DTO.AnalyticsResponseDTO;
import com.example.SHOP.easy.DTO.OrderDTO;

import java.util.List;

public interface AdminOrderService {

    List<OrderDTO> getAllPlacedOrders();

    OrderDTO changeOrderStatus(Long orderId, String status);

    AnalyticsResponseDTO calculateAnalytics();
}
