package com.example.demo.dashboard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardResponse {
    private TaskSummary taskSummary;
    private List<DeadlineTask> upcomingDeadlines;
    private PaymentSummary paymentSummary;
}
