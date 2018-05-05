package Model;

public class Block extends Immobile {

    public Block(int X, int Y) {
        super(X, Y, 0);
    }

    @Override
    public boolean isObstacle() {
        return true;
    }
}
