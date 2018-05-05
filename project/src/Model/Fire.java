package Model;

public class Fire extends Immobile {

	public Fire(int X, int Y) {
		super(X, Y, 5);
	}

	@Override
	public boolean isObstacle() {

		return false;
	}

}
