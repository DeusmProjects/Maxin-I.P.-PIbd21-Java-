import java.awt.*;
import java.util.ArrayList;
public class Dock<T extends ITransport> {
	private ArrayList<T> places;
	
	private int PictureWidth;
	
	public int getPictureWidth(){
		return PictureWidth;
	}
	
	public void setPictureWidth(int pw){
		PictureWidth = pw;
	}	
	
	private int PictureHeight;
	
	public int getPictureHeight(){
		return PictureHeight;
	}
	
	public void setPictureHeight(int ph){
		PictureHeight = ph;
	}	
	
	private int placeSizeWidth = 310;
    private int placeSizeHeight = 120;
    
    public Dock(int sizes, int pictureWidth, int pictureHeight)
    {
    	places = new ArrayList<T>();
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
        for (int i = 0; i < sizes; i++) {
            places.add(null);
        }
    }
    
    public int addShip(T ship) {
    	for (int i = 0; i < places.size(); i++) {
            if (checkFreePlace(i)) {
                places.add(i, ship);
                if (i < places.size() / 2)
                {
                    places.get(i).SetPosition(3 + i / 5 * placeSizeWidth + 5, i % 5 * placeSizeHeight + 15, PictureWidth, PictureHeight);
                } else
                {
                    places.get(i).SetPosition(63 + i / 5 * placeSizeWidth + 5, i % 5 * placeSizeHeight + 15, PictureWidth, PictureHeight);
                }
                return i;
            }
        }
        return -1;
    }
    
    public T removeShip(int index) {
    	if (index < 0 || index > places.size()) {
            return null;
        }
        if (!checkFreePlace(index)) {
            T ship = places.get(index);
            places.set(index, null);
            return ship;
        }
        return null;
    }
    
    private boolean checkFreePlace(int index) {
        return places.get(index) == null;
    }
    
    public void draw(Graphics g) {
    	drawMarking(g);
        for (int i = 0; i < places.size(); i++) {
            if (!checkFreePlace(i)) {
                places.get(i).DrawShip(g);
            }
        }
    }
    
    private void drawMarking(Graphics g) {
    	Color black = new Color(0,0,0);
    	g.setColor(black);
    	g.drawRect(0, 0, (places.size() / 4) * placeSizeWidth, 600);
    	for (int i = 0; i < 1; i++) {
    		for (int j = 0; j < 5; ++j) {
    			g.drawLine(i * placeSizeWidth, j * placeSizeHeight, i * placeSizeWidth + 210, j * placeSizeHeight);		
    		}
    		g.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth, 600);
    	}
    	for (int i = 0; i < 1; i++) {
    		for (int j = 0; j < 5; ++j) {
    			g.drawLine(i * placeSizeWidth + 368, j * placeSizeHeight, i * placeSizeWidth + 600, j * placeSizeHeight);
    		}
    		g.drawLine(i * placeSizeWidth + 600, 0, i * placeSizeWidth + 600, 600);
    	}
    }
}
