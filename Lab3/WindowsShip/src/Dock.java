import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;
public class Dock<T extends ITransport> {
	private HashMap<Integer, T> places;
	
	private int PictureWidth;
	private int maxCount;
	
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
    	maxCount = sizes;
    	places = new HashMap<Integer,T>();
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
    }
    
    public int addShip(T ship) {
    	if(places.size() == maxCount)
        {
            return -1;
        }
    	
    	for (int i = 0; i < maxCount; i++) {
            if (checkFreePlace(i)) {
                places.put(i, ship);
                if (i < maxCount / 2)
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
    	if (index < 0 || index > maxCount) {
            return null;
        }
        if (!checkFreePlace(index)) {
            T ship = places.get(index);
            places.remove(index);
            return ship;
        }
        return null;
    }
    
    private boolean checkFreePlace(int index) {
        return !places.containsKey(index);
    }
    
    public void draw(Graphics g) {
    	drawMarking(g);
        for (int i = 0; i < places.keySet().toArray().length; i++) {         
        	places.get(places.keySet().toArray()[i]).DrawShip(g);
        }
    }
    
    private void drawMarking(Graphics g) {
    	Color black = new Color(0,0,0);
    	g.setColor(black);
    	g.drawRect(0, 0, (maxCount / 4) * placeSizeWidth, 600);
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
