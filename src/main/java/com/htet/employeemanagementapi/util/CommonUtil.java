package com.htet.employeemanagementapi.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Component
public class CommonUtil {

    public static boolean validList(List value) {
        return value != null && !value.isEmpty();
    }

    public static boolean validString(String val) {
        return val != null && ! val.isBlank();
    }

    public static boolean validLong(Long val) {
        return val != null && val > 0;
    }

    public static boolean validInt(Integer val) {
        return val != null && val > 0;
    }

    public static boolean validBool(Boolean val) {
        return val != null && val;
    }

    public static DateTimeFormatter formatDate(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

}
