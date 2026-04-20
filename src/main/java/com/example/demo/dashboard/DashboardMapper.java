package com.example.demo.dashboard;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardMapper {
    public DashboardResponse toResponse(TaskSummary taskSummary, List<DeadlineTask> deadlines, PaymentSummary paymentSummary) {
        return DashboardResponse.builder()
                .taskSummary(taskSummary)
                .upcomingDeadlines(deadlines)
                .paymentSummary(paymentSummary)
                .build();
    }
}
