package pl.adamsalata.privateperson.model.enumeration;

public enum Sex {
    MALE("MALE"),FEMALE("FEMALE");

    private String value;

    private Sex(String value) {
        this.value = value;
    }
}
