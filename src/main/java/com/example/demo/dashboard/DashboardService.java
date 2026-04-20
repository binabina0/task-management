package com.example.demo.dashboard;

import com.example.demo.payment.PaymentShare;
import com.example.demo.payment.PaymentShareRepository;
import com.example.demo.payment.PaymentStatus;
import com.example.demo.task.Task;
import com.example.demo.task.TaskRepository;
import com.example.demo.task.TaskStatus;
import com.example.demo.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.example.demo.common.SecurityUtil.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardService {
    private final TaskRepository taskRepository;
    private final PaymentShareRepository paymentShareRepository;
    private final DashboardMapper dashboardMapper;

    public DashboardResponse getDashboard() {
        UserEntity user =getCurrentUser();
        if (user == null) {
            throw new IllegalStateException("No authenticated user found");
        }
        log.info("Fetching dashboard for user {}", user.getEmail());
        UUID userId = user.getId();
        log.info("Dashboard userId={}", userId);
        long total = taskRepository.countByAssignedToId(userId);
        long todo = taskRepository.countByAssignedToIdAndStatus(userId, TaskStatus.TODO);
        long inProgress = taskRepository.countByAssignedToIdAndStatus(userId, TaskStatus.IN_PROGRESS);
        long done = taskRepository.countByAssignedToIdAndStatus(userId, TaskStatus.DONE);
        log.info("Task count={}", taskRepository.countByAssignedToId(userId));
        log.info("Deadlines={}", taskRepository.findByAssignedToIdAndDeadlineBefore(userId, LocalDateTime.now().plusDays(3)));
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

        return dashboardMapper.toResponse(taskSummary, deadlines, paymentSummary);
    }
}
