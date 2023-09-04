package mu.oth.PriceService.utils;

public class RateException extends RuntimeException {
    public RateException(String reason) {
        super(reason);
    }
    public RateException(Throwable exc) {
        super(exc);
    }
    public RateException(String reason, Throwable exc) {
        super(reason, exc);
    }
}
