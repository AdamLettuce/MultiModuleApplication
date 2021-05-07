package pl.adamsalata.authentication.model.domain;


import java.util.Optional;

public class ValueObject<T> {
    private T value;

    public ValueObject(){
    }

    public ValueObject(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
