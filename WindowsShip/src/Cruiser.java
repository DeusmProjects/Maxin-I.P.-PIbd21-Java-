import java.awt.*;

public class Cruiser extends Ship{
	protected int shipWidth = 100;
	protected int shipHeight = 60;
	
	public Cruiser(int maxSpeed, float weight, Color mainColor)
    {
		setMaxSpeed(maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
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
	
}
