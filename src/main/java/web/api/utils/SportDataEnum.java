package web.api.utils;

public enum SportDataEnum {
    FOOTBALL(1, "Football"),
    BASKETBALL(2, "Basketball"),
    CRICKET(3, "Cricket"),
    TENNIS(4, "Tennis"),
    OTHER(5, "Other");

    private final int id;
    private final String name;

    SportDataEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getNameById(int id) {
        for (SportDataEnum sport : values()) {
            if (sport.getId() == id) {
                return sport.getName();
            }
        }
        return SportDataEnum.OTHER.getName();
    }

    public static int getIdByName(String name) {
        for (SportDataEnum sport : values()) {
            if (sport.getName().equals(name)) {
                return sport.getId();
            }
        }
        return  SportDataEnum.OTHER.getId();
    }
}
