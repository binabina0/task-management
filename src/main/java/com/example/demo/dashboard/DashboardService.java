package com.example.demo.dashboard;

import com.example.demo.payment.PaymentShare;
import com.example.demo.payment.PaymentShareRepository;
import com.example.demo.payment.PaymentStatus;
import com.example.demo.task.Task;
import com.example.demo.task.TaskRepository;
import com.example.demo.task.TaskStatus;
import com.example.demo.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.example.demo.common.SecurityUtil.getCurrentUser;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final TaskRepository taskRepository;
    private final PaymentShareRepository paymentShareRepository;

    public DashboardResponse getDashboard() {
        UserEntity user =getCurrentUser();
        UUID userId = user.getId();
        long total = taskRepository.countByAssignedToId(userId);
        long todo = taskRepository.countByAssignedToIdAndStatus(userId, TaskStatus.TODO);
        long inProgress = taskRepository.countByAssignedToIdAndStatus(userId, TaskStatus.IN_PROGRESS);
        long done = taskRepository.countByAssignedToIdAndStatus(userId, TaskStatus.DONE);
        TaskSummary taskSummary = TaskSummary.builder()
                .total(total)
                .todo(todo)
                .inProgress(inProgress)
                .done(done)
                .build();

        List<Task> tasks =taskRepository.findByAssignedToIdAndDeadlineBefore(userId, LocalDateTime.now().plusDays(3));
        List<DeadlineTask> deadlines = tasks.stream()
                .map(t -> DeadlineTask.builder()
                        .title(t.getTitle())
                        .deadline(t.getDeadline() != null ? t.getDeadline().toString() : null)
                        .build())
                .toList();

        List<PaymentShare> shares = paymentShareRepository.findByUserId(userId);
        BigDecimal totalOwed = shares.stream()
                .filter(s -> s.getStatus() == PaymentStatus.UNPAID)
                .map(PaymentShare::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPaid = shares.stream()
                .filter(s -> s.getStatus() == PaymentStatus.PAID)
                .map(PaymentShare::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        PaymentSummary paymentSummary = PaymentSummary.builder()
                .totalOwed(totalOwed)
                .totalPaid(totalPaid)
                .build();

        return DashboardResponse.builder()
                .taskSummary(taskSummary)
                .upcomingDeadlines(deadlines)
                .paymentSummary(paymentSummary)
                .build();
    }
}
