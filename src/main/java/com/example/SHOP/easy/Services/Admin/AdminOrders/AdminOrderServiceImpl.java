package com.example.SHOP.easy.Services.Admin.AdminOrders;

import com.example.SHOP.easy.DTO.AnalyticsResponseDTO;
import com.example.SHOP.easy.DTO.OrderDTO;
import com.example.SHOP.easy.Entity.Orders;
import com.example.SHOP.easy.Enums.OrderStatus;
import com.example.SHOP.easy.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderRepository orderRepository;

    public List<OrderDTO> getAllPlacedOrders(){
        List<Orders> orders = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered));

        return orders.stream().map(Orders::getOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO changeOrderStatus(Long orderId, String status){
        Optional<Orders> optionalOrders = orderRepository.findById(orderId);

        if(optionalOrders.isPresent()){
            Orders orders = optionalOrders.get();

            if(Objects.equals(status, "Shipped")){
                orders.setOrderStatus(OrderStatus.Shipped);
            }else if(Objects.equals(status, "Deliverde")){
                orders.setOrderStatus(OrderStatus.Delivered);
            }

            return orderRepository.save(orders).getOrderDTO();
        }
        return null;
    }

    public AnalyticsResponseDTO calculateAnalytics(){

        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);

        Long currentMonthOrders = getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthOrders = getTotalOrdersForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long currentMonthEarnings = getTotalEarningsForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthEarnings = getTotalEarningsForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long placed = orderRepository.countByOrderStatus(OrderStatus.Placed);
        Long shipped = orderRepository.countByOrderStatus(OrderStatus.Shipped);
        Long delivered = orderRepository.countByOrderStatus(OrderStatus.Delivered);

        return new AnalyticsResponseDTO(
                placed, shipped, delivered,
                currentMonthOrders, previousMonthOrders,
                currentMonthEarnings, previousMonthEarnings
        );

    }
    public Long getTotalOrdersForMonth(int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);


        Date endOfMonth = calendar.getTime();

        List<Orders> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

        return (long) orders.size();
    }
    public Long getTotalEarningsForMonth(int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);


        Date endOfMonth = calendar.getTime();

        List<Orders> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

        Long sum = 0L;

        for(Orders order : orders){
            sum += order.getAmount();
        }
        return sum;
    }
}
