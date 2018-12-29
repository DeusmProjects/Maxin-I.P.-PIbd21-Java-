package lab8;
import java.awt.*;
import java.util.Set;

class WarShip extends Cruiser {
	
	private Color DopColor;
	public Color getDopColor(){
		return DopColor;
	}
	
	public void setDopColor(Color dc){
		DopColor = dc;
	}	
	
	private boolean Flag;
	public boolean getFlag() {
		return Flag;
	}
	
	public void setFlag(boolean f){
		Flag = f;
	}	
	
	private boolean Gun;
	public boolean getGun() {
		return Gun;
	}
	
	public void setGun(boolean g){
		Gun = g;
	}
	
	public void setDopColor(String colorName) {
        switch (colorName) {
            case "yellow":
            	DopColor = Color.YELLOW;
                break;
            case "blue":
            	DopColor = Color.BLUE;
                break;
            case "pink":
            	DopColor = Color.PINK;
                break;
            case "green":
            	DopColor = Color.GREEN;
                break;
            case "black":
            	DopColor = Color.BLACK;
                break;
            case "orange":
            	DopColor = Color.ORANGE;
                break;
            case "cyan":
            	DopColor = Color.CYAN;
                break;
            case "magenta":
            	DopColor = Color.MAGENTA;
                break;
        }

    }

	public WarShip(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean flag, boolean gun) {
		super(maxSpeed, weight, mainColor);
		setDopColor(dopColor);
		Flag = flag;
		Gun = gun;
	}
	
	public WarShip(String info) {
		super(info);
		String[] str = info.split(";");
		if (str.length == 10) {
			MaxSpeed = Integer.parseInt(str[0]);
			Weight = Float.parseFloat(str[1]);
			MainColor = new Color(Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]));
			DopColor = new Color(Integer.parseInt(str[5]), Integer.parseInt(str[6]), Integer.parseInt(str[7]));
			Flag = Boolean.parseBoolean(str[8]);
			Gun = Boolean.parseBoolean(str[9]);

		}
	}

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}

	public void MoveTransport(Direction direction) {
		float step = MaxSpeed * 100 / Weight;
		switch (direction) {
		case Right:
			if (_startPosX + step < _pictureWidth - shipWidth) {
				_startPosX += step;
			}
			break;
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		case Up:
			if (_startPosY - step - 10 > 0) {
				_startPosY -= step;
			}
			break;
		case Down:
			if (_startPosY + step < _pictureHeight - shipHeight) {
				_startPosY += step;
			}
			break;
		}
	}

	public void DrawShip(Graphics g) {
		super.DrawShip(g);
		Color blacPen = new Color(0,0,0);
        
        //флаг
        if (Flag)
        {
        	g.setColor(blacPen);
            g.drawLine(_startPosX + 20, _startPosY + 50, _startPosX + 20, _startPosY);
            g.drawRect( _startPosX, _startPosY, 20, 15);
            g.setColor(DopColor);
            g.fillRect(_startPosX+1, _startPosY+1, 19, 14);          
        }

        //пушка
        if (Gun)
        {   g.setColor(blacPen);
            int[] arrayX2 = { (int) _startPosX + 123, (int) _startPosX + 193, (int) _startPosX + 201, (int) _startPosX + 131 };
    		int[] arrayY2 = { (int) _startPosY + 50, (int) _startPosY + 30, (int) _startPosY + 30, (int) _startPosY + 50 };  		
    		Polygon poly2 = new Polygon(arrayX2, arrayY2, 4);
            g.fillPolygon(poly2);
            g.drawPolygon(poly2);
        }
	}
	
	@Override
	public String getInfo() {
		return MaxSpeed + ";" + Weight + ";" + MainColor.getRed() + ";" + MainColor.getGreen() + ";"
				+ MainColor.getBlue() + ";" + DopColor.getRed() + ";" + DopColor.getGreen() + ";"
				+ DopColor.getBlue() + ";" + Flag + ";" + Gun;
	}
	
	public int compareTo(WarShip other) {
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
		if(DopColor != other.DopColor) {
			return Integer.compare(DopColor.getRGB(), other.DopColor.getRGB());
		}
		if(Flag != other.Flag) {
			return Boolean.compare(Flag, other.Flag);
		}
		if(Gun != other.Gun) {
			return Boolean.compare(Gun, other.Gun);
		}
		return 0;
	}
	
	public boolean equals(WarShip other) {
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
		if(DopColor != other.DopColor) {
			return false;
		}
		if(Flag != other.Flag) {
			return false;
		}
		if(Gun != other.Gun) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(!(other instanceof WarShip)) {
			return false;
		}
		WarShip shipObj = (WarShip) other;
		return equals(shipObj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
