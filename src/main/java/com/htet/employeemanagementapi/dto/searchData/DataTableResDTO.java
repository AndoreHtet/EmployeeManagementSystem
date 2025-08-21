package com.htet.employeemanagementapi.dto.searchData;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataTableResDTO <T>{

    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data = new ArrayList<>();

    public DataTableResDTO(List<T> data, DataTableSearchDTO searchDTO) {
        if (searchDTO.getDraw() != null){
            this.draw = searchDTO.getDraw();
        }
        this.recordsTotal = searchDTO.getRecordsTotal();
        this.recordsFiltered = searchDTO.getRecordsFiltered();
        this.data = data;
    }
}
