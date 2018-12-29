package lab8;

public class DockAlreadyHaveException extends Exception{
	public DockAlreadyHaveException() {
		super("В доке уже есть такой корабль");
	}
}
