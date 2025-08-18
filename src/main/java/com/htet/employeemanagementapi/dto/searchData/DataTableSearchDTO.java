package com.htet.employeemanagementapi.dto.searchData;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataTableSearchDTO {

    private Integer draw;
    private Integer pageNo;

    private int start;
    private Integer length;
    private Long recordsTotal;
    private Long recordsFiltered;

    private Integer column;
    private String dir;
    private String columnName;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastRefreshAt;


}
