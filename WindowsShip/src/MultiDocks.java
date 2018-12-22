package lab7;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class MultiDocks {
	ArrayList<Dock<ITransport>> dockNumbers;
	private final int countPlaces = 10;
	private int pictureWidth;

	private int pictureHeight;
	int countDocks = 5;
	
	public MultiDocks(int countDocks, int pictureWidth, int pictureHeight)
    {
		dockNumbers = new ArrayList<Dock<ITransport>>(countDocks);
		for (int i = 0; i < countDocks; ++i) {
			dockNumbers.add(new Dock<ITransport>(countPlaces, pictureWidth, pictureHeight));
		}
    }
	
	public Dock<ITransport> getDock(int ind) { 

		if ((ind > -1) && (ind < dockNumbers.size())) { 

			return dockNumbers.get(ind); 
		} 
		return null; 
	}
	
	public void saveData(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            writeToFile("CountDocks:" + dockNumbers.size() + System.lineSeparator(), bw);
            for (Dock<ITransport> level : dockNumbers) {
                writeToFile("Dock" + System.lineSeparator(), bw);
                for (int i = 0; i < countPlaces; i++) {       
                    try {
                    	ITransport ship = level.getShip(i);
                        if (ship.getClass().getSimpleName().equals("Cruiser")) {
                            writeToFile(i + ":Cruiser:" + ship.getInfo(), bw);
                        }
                        if (ship.getClass().getSimpleName().equals("WarShip")) {
                            writeToFile(i + ":WarShip:" + ship.getInfo(), bw);
                        }
                        writeToFile(System.lineSeparator(), bw);
                    } catch (Exception ex) { }
                    finally { }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
	}
 
	private void writeToFile(String text, BufferedWriter writer) {
        try {
            char[] info = text.toCharArray();
            writer.write(info, 0, info.length);
        } catch (Exception e) {
            System.out.println(e);
        }
  }
	
	
	public void loadData(String filename) throws Exception {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        String bufferTextFromFile = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                bufferTextFromFile += line + "\n";
            }
            String[] strs = bufferTextFromFile.split("\n");
            if (strs[0].contains("CountDocks")) {
                int count = Integer.parseInt(strs[0].split(":")[1]);
                if (dockNumbers != null) {
                    dockNumbers.clear();
                }
                dockNumbers = new ArrayList<Dock<ITransport>>(count);
            } else {
                throw new Exception("Неверный формат файла");
            }
            int counter = -1;
            ITransport ship = null;
            for (int i = 1; i < strs.length; ++i) {
                if (strs[i].equals("Dock")) {
                    counter++;
                    dockNumbers.add(new Dock<ITransport>(countPlaces, pictureWidth, pictureHeight));
                    continue;
                }
                if (strs[i].isEmpty() || strs[i] == null) {
                    continue;
                }
                if (strs[i].split(":")[1].equals("Cruiser")) {
                    ship = new Cruiser(strs[i].split(":")[2]);
                } else if (strs[i].split(":")[1].equals("WarShip")) {
                    ship = new WarShip(strs[i].split(":")[2]);
                }
                dockNumbers.get(counter).setShip(Integer.parseInt(strs[i].split(":")[0]), ship);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
