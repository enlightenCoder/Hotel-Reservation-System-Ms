package mu.oth.InventoryService.utils;

import java.time.LocalDate;

public class DateUtils {


    public static boolean isDateRangeWithin(LocalDate from, LocalDate to, LocalDate from1, LocalDate to1) {

        return !(from1.isBefore(from) && to1.isBefore(to)) && !(from1.isAfter(from) && to1.isAfter(to));

    }
}
