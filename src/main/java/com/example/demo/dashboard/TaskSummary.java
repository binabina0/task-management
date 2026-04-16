package com.example.demo.dashboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskSummary {
    private long total;
    private long todo;
    private long inProgress;
    private long done;
}
