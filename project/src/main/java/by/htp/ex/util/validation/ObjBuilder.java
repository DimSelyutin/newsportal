package by.htp.ex.util.validation;

public interface ObjBuilder<T> {
    T build() throws ValidException;
}
