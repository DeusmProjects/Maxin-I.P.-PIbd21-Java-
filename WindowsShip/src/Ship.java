package lab8;
	import java.awt.*;

public abstract class Ship implements ITransport{
	protected int _startPosX;
	protected int _startPosY;
	protected int _pictureWidth;
	protected int _pictureHeight;
	
	protected int MaxSpeed;
	public int getMaxSpeed(){
		return MaxSpeed;
	}
	public void setMaxSpeed(int s){
		MaxSpeed = s;
	}	
	
	protected float Weight;
	public float getWeight(){
		return Weight;
	}	
	public void setWeight(float w){
		Weight = w;
	}	
	
	protected Color MainColor;
	public Color getMainColor(){
		return MainColor;
	}
	public void setMainColor(Color mc){
		MainColor = mc;
	}	
	
	@Override
	public void setMainColor(String colorName){
		switch (colorName) {
        case "yellow":
            MainColor = Color.YELLOW;
            break;
        case "blue":
        	MainColor = Color.BLUE;
            break;
        case "pink":
        	MainColor = Color.PINK;
            break;
        case "green":
        	MainColor = Color.GREEN;
            break;
        case "black":
        	MainColor = Color.BLACK;
            break;
        case "orange":
        	MainColor = Color.ORANGE;
            break;
        case "cyan":
        	MainColor = Color.CYAN;
            break;
        case "magenta":
        	MainColor = Color.MAGENTA;
            break;
		}

	}
	
	public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public abstract void DrawShip(Graphics g);

    public abstract void MoveTransport(Direction direction);
    
    public abstract String getInfo();
}
