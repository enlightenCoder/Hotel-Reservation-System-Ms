package mu.oth.InventoryService.utils;

public class InventoryException extends RuntimeException {

    public InventoryException(String reason) {
        super(reason);
    }
    public InventoryException(Throwable exc) {
        super(exc);
    }
    public InventoryException(String reason, Throwable exc) {
        super(reason, exc);
    }

}
