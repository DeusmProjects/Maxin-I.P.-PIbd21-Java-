package lab8;

public class DockNotFoundException extends Exception {
	public DockNotFoundException(int i) {
		super("Не найден корабль по месту " + i); 
	}
}
