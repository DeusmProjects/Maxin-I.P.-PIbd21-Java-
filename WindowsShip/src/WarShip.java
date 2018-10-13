import java.awt.*;
import java.util.Set;

class WarShip {
	private int _startPosX;
	private int _startPosY;
	private int _pictureWidth;
	private int _pictureHeight;
	private int shipWidth = 100;
	private int shipHeight = 60;
	private int MaxSpeed;
	public int getMaxSpeed(){
		return MaxSpeed;
	}
	public void setWeight(int s){
		MaxSpeed = s;
	}	
	private float Weight;
	public float getWeight(){
		return Weight;
	}	
	public void setWeight(float w){
		Weight = w;
	}	
	
	private Color MainColor;
	public Color getMainColor(){
		return MainColor;
	}
	public void setMainColor(Color mc){
		MainColor = mc;
	}	
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

	public WarShip(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean flag, boolean gun) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		DopColor = dopColor;
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
        //корпус
		g.setColor(MainColor);
		Color blacPen = new Color(0,0,0);
        
        int[] arrayX = { (int) _startPosX, (int) _startPosX + 170, (int) _startPosX + 100, (int) _startPosX + 30 };
		int[] arrayY = { (int) _startPosY + 50, (int) _startPosY + 50, (int) _startPosY + 90, (int) _startPosY + 90 };
		
		Polygon poly = new Polygon(arrayX, arrayY, 4);
        g.fillPolygon(poly);
        g.drawPolygon(poly);

        //линии на корпусе
        g.setColor(blacPen);
        g.drawLine(_startPosX + 6, _startPosY + 57, _startPosX + 154, _startPosY + 57);
        g.drawLine(_startPosX + 26, _startPosY + 83, _startPosX + 110, _startPosY + 83);

        //мачта   
        g.setColor(blacPen);
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
	
}