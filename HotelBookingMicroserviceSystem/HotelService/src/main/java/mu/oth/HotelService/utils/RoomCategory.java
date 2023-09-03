package mu.oth.HotelService.utils;

public enum RoomCategory {

    SUJ("Junior Suite"),
    SUS("Senior Suite"),
    SUL("Senior Suite Beach"),
    XSF("2-Bedroom Family suite"),
    STS("Standard Sea facing"),

    FDS("Family Duplex Sea facing");

    private String type;

    public String getType() {
        return this.type;
    }

    private RoomCategory(String type) {
        this.type = type;
    }


}
