package Model;

public class Herbs extends Immobile{

	public Herbs(int X, int Y) {
		super(X, Y, 5);
	}

	@Override
	public boolean isObstacle() {

		return false;
	}

}
