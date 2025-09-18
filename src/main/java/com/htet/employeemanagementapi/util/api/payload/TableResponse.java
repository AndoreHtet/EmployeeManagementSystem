package com.htet.employeemanagementapi.util.api.payload;

import java.util.List;

public record TableResponse<T>(
        long totalCount,
        long filterCount,
        int totalPages,
        List<T> contents
) {
}
