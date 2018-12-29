package lab8;
import java.awt.*;

public class Cruiser extends Ship implements Comparable<Cruiser>{
	protected int shipWidth = 100;
	protected int shipHeight = 60;
	
	public Cruiser(int maxSpeed, float weight, Color mainColor)
    {
		setMaxSpeed(maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
    }
	
	public Cruiser(String info) {
		String[] str = info.split(";");
		if(str.length == 5) {
			MaxSpeed = Integer.parseInt(str[0]);
			Weight = Float.parseFloat(str[1]);
			MainColor = new Color(Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]));
		}
	}
	
	@Override
	public void DrawShip(Graphics g) {      
      //корпус
      		g.setColor(MainColor);
      		Color blackPen = new Color(0,0,0);
              
            int[] arrayX = { (int) _startPosX, (int) _startPosX + 170, (int) _startPosX + 100, (int) _startPosX + 30 };
      		int[] arrayY = { (int) _startPosY + 50, (int) _startPosY + 50, (int) _startPosY + 90, (int) _startPosY + 90 };
      		
      		Polygon poly = new Polygon(arrayX, arrayY, 4);
              g.fillPolygon(poly);
              g.drawPolygon(poly);

              //линии на корпусе
              g.setColor(blackPen);
              g.drawLine(_startPosX + 6, _startPosY + 57, _startPosX + 154, _startPosY + 57);
              g.drawLine(_startPosX + 26, _startPosY + 83, _startPosX + 110, _startPosY + 83);

              //мачта   
              g.setColor(blackPen);
              g.drawLine( _startPosX + 70, _startPosY + 50, _startPosX + 70, _startPosY);
              g.drawLine( _startPosX + 65, _startPosY + 5, _startPosX + 75, _startPosY + 5);
              g.drawLine( _startPosX + 55, _startPosY + 15, _startPosX + 85, _startPosY + 15);
              g.drawLine( _startPosX + 45, _startPosY + 25, _startPosX + 95, _startPosY + 25);

              //иллюминаторы
              Color c = new Color(94,190,235);
              g.setColor(c);
              g.fillOval(_startPosX + 40, _startPosY + 62, 15, 15);
              g.fillOval( _startPosX + 65, _startPosY + 62, 15, 15);
              g.fillOval(_startPosX + 90, _startPosY + 62, 15, 15);
	}

	@Override
	public void MoveTransport(Direction direction) {
		// TODO Auto-generated method stub
		float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            // вправо
            case Right:
                if (_startPosX + step < _pictureWidth - shipWidth)
                {
                    _startPosX += step;
                }
                break;
            //влево
            case Left:
                if (_startPosX - step > 0)
                {
                    _startPosX -= step;
                }
                break;
            //вверх
            case Up:
                if (_startPosY - step > 50)
                {
                    _startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - shipHeight)
                {
                    _startPosY += step;
                }
                break;
        }
	}
	
	@Override
	public String getInfo() {
		return MaxSpeed + ";" + Weight + ";" + MainColor.getRed() + ";" 
				+ MainColor.getGreen() + ";" + MainColor.getBlue();
	}
	
	@Override
	public int compareTo(Cruiser other) {
		if(other == null) {
			return 1;
		}
		if(MaxSpeed != other.MaxSpeed) {
			return Integer.compare(MaxSpeed, other.MaxSpeed);
		}
		if(Weight != other.Weight) {
			return Float.compare(Weight, other.Weight);
		}
		if(MainColor != other.MainColor) {
			return Integer.compare(MainColor.getRGB(), other.MainColor.getRGB());
		}
		return 0;
	}
	
	public boolean equals(Cruiser other) {
		if(other == null) {
			return false;
		}
		if(MaxSpeed != other.MaxSpeed) {
			return false;
		}
		if(Weight != other.Weight) {
			return false;
		}
		if(MainColor != other.MainColor) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(!(other instanceof Cruiser)) {
			return false;
		}
		Cruiser shipObj = (Cruiser) other;
		return equals(shipObj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
