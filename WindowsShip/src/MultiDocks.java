import java.util.ArrayList;

public class MultiDocks {
	ArrayList<Dock<ITransport>> dockNumbers;
	private final int countPlaces = 10;
	
	public MultiDocks(int countDocks, int pictureWidth, int pictureHeight)
    {
        dockNumbers = new ArrayList<Dock<ITransport>>();
        for (int i = 0; i < countDocks; ++i)
        {
            dockNumbers.add(new Dock<ITransport>(countPlaces, pictureWidth, pictureHeight));
        }
    }
	
	public Dock<ITransport> getDock(int ind) { 

		if ((ind > -1) && (ind < dockNumbers.size())) { 

			return dockNumbers.get(ind); 
		} 
		return null; 
	}
}
