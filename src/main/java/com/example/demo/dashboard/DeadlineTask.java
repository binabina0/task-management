package com.example.demo.dashboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeadlineTask {
    private String title;
    private String deadline;
}
