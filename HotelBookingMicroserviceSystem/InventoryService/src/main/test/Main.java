import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var from = LocalDate.of(2023, 9, 1);
        var to = LocalDate.of(2023, 9, 15);

        var enterDate1 = LocalDate.of(2023, 9, 1);
        var enterDate2 = LocalDate.of(2023, 9, 15);

        System.out.println(isDateRangeWithin(from,to,enterDate1,enterDate2));

    }

    public static boolean isDateRangeWithin(LocalDate from, LocalDate to, LocalDate from1, LocalDate to1) {

        return !(from1.isBefore(from) && to1.isBefore(to)) && !(from1.isAfter(from) && to1.isAfter(to));

    }
}
