package pl.adamlettuce.authentication.model.domain;


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
