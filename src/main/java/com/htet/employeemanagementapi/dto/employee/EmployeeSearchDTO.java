package com.htet.employeemanagementapi.dto.employee;

import com.htet.employeemanagementapi.dto.searchData.DataTableSearchDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchDTO extends DataTableSearchDTO {

    private String employeeName;
    private Long departmentId;
    private String departmentName;

    private List<EmployeeDTO> data;


    @Builder
    public EmployeeSearchDTO(Integer draw, Integer pageNo, int start, Integer length, Long recordsTotal, Long recordsFiltered, Integer column, String dir, String columnName, LocalDateTime lastRefreshAt, String employeeName, String departmentName) {
        super(draw, pageNo, start, length, recordsTotal, recordsFiltered, column, dir, columnName, lastRefreshAt);
        this.employeeName = employeeName;
        this.departmentName = departmentName;
    }

}
