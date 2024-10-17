package com.sakib.io.socket;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SessionData {
    private List<String> visitedUrls;
    private long lastVisitedTime;
}
