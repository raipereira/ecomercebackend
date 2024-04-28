package raiper.miu.cs489.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer id) {
        super("Could not find address with id " + id + " :(");
    }
}
