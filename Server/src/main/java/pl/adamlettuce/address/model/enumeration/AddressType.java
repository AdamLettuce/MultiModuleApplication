package pl.adamlettuce.address.model.enumeration;

public enum AddressType {
    REGISTRATION("REGISTRATION"), PRIMARY("PRIMARY"), CORRESPONDENCE("CORRESPONDENCE");

    private String value;

    private AddressType(String value) {
        this.value = value;
    }
}
