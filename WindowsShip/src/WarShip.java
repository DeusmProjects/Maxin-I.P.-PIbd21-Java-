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
            case "red":
            	DopColor = Color.RED;
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
            case "grey":
            	DopColor = Color.GRAY;
                break;
            case "white":
            	DopColor = Color.WHITE;
                break;
        }

    }

	public WarShip(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean flag, boolean gun) {
		super(maxSpeed, weight, mainColor);
		setDopColor(dopColor);
		Flag = flag;
		Gun = gun;
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
        
        //����
        if (Flag)
        {
        	g.setColor(blacPen);
            g.drawLine(_startPosX + 20, _startPosY + 50, _startPosX + 20, _startPosY);
            g.drawRect( _startPosX, _startPosY, 20, 15);
            g.setColor(DopColor);
            g.fillRect(_startPosX+1, _startPosY+1, 19, 14);          
        }

        //�����
        if (Gun)
        {   g.setColor(blacPen);
            int[] arrayX2 = { (int) _startPosX + 123, (int) _startPosX + 193, (int) _startPosX + 201, (int) _startPosX + 131 };
    		int[] arrayY2 = { (int) _startPosY + 50, (int) _startPosY + 30, (int) _startPosY + 30, (int) _startPosY + 50 };  		
    		Polygon poly2 = new Polygon(arrayX2, arrayY2, 4);
            g.fillPolygon(poly2);
            g.drawPolygon(poly2);
        }
	}
	
}
