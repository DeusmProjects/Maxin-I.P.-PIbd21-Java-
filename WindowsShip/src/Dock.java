package lab8;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Dock<T extends ITransport> implements Iterable<T>, Comparable<Dock<T>>{
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
    
    public int addShip(T ship) throws Exception {
    	if(places.size() == maxCount)
        {
            throw new DockOverflowException();
        }
    	if(places.containsValue(ship)) {
    		throw new DockAlreadyHaveException();
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
    
    public T removeShip(int index) throws DockNotFoundException {
    	if (index < 0 || index > maxCount) {
            return null;
        }
        if (!checkFreePlace(index)) {
            T ship = places.get(index);
            places.remove(index);
            return ship;
        }
        throw new DockNotFoundException(index);
    }
    
    private boolean checkFreePlace(int index) {
        return !places.containsKey(index);
    }
    
    public void draw(Graphics g) {
    	drawMarking(g);
        for(T ship : places.values()) {
        	ship.DrawShip(g);
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
    
    public T getShip(int index) throws DockNotFoundException {
    	if (places.get(index) != null) {
			return places.get(index);
		}
    	throw new DockNotFoundException(index);
    }
    
    public void setShip(int index, T ship) throws DockOccupiedPlaceException {
    	if(checkFreePlace(index)) {
    		places.put(index, ship);
    		places.get(index).SetPosition(5 + index / 5 * placeSizeWidth + 5, index % 5 * placeSizeHeight + 15, PictureWidth, PictureHeight);
    	} 
    	else {
    		throw new DockOccupiedPlaceException(index);
    	}
    }
    
    @Override
    public Iterator<T> iterator(){
    	return places.values().iterator();
    }
    
    @Override
    public int compareTo(Dock<T> other) {
    	if(places.size() > other.places.size()){
            return -1;
        } else if(places.size() < other.places.size()){
            return 1;
        } else if(places.size() > 0){
            Object[] thisKey = places.keySet().toArray();
            Object[] otherKey = other.places.keySet().toArray();
            for(int i = 0; i < places.size(); ++i){
                if(places.get(thisKey[i]) instanceof Cruiser && other.places.get(otherKey[i]) instanceof WarShip){
                    return 1;
                }
                if(places.get(thisKey[i]) instanceof WarShip && other.places.get(otherKey[i]) instanceof Cruiser){
                    return -1;
                }
                if(places.get(thisKey[i]) instanceof Cruiser && other.places.get(otherKey[i]) instanceof Cruiser){
                    return (((Cruiser) places.get(thisKey[i])).compareTo((Cruiser) other.places.get(thisKey[i])));
                }
                if(places.get(thisKey[i]) instanceof WarShip && other.places.get(otherKey[i]) instanceof WarShip){
                    return (((WarShip) places.get(thisKey[i])).compareTo((WarShip) other.places.get(thisKey[i])));
                }
            }
        }
        return 0;
    }
}
