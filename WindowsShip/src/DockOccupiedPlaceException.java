package lab7;

public class DockOccupiedPlaceException extends Exception{
	public DockOccupiedPlaceException(int i) {
		super("�� ����� " + i + " ��� ����� �������");
	}
}
