package Model;

import View.Window;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    private Window window;
    private int size = 20;
    // private int bombTimer = 3000;
    private int numberOfBlocks = 40;
    private int numberOfFires = 20;
    private int numberOfHerbs = 20;
    public Game(Window window) {
        this.window = window;

        // Creating one Player at position (1,1)
        objects.add(new Player(10, 10, 3, 5));

        // Map building
        for (int i = 0; i < size; i++) { // Frames the map with blocks
            objects.add(new Block(i, 0));
            objects.add(new Block(0, i));
            objects.add(new Block(i, size - 1));
            objects.add(new Block(size - 1, i));
        }
        Random rand = new Random();
        for (int i = 0; i < numberOfBlocks; i++) { // Adds random blocks
            int x = rand.nextInt(16) + 2;
            int y = rand.nextInt(16) + 2;
            Block block = new Block(x, y);
            objects.add(block);
        }

        for (int i = 0; i < numberOfFires; i++) { // Adds random fires
            int x = rand.nextInt(16) + 2;
            int y = rand.nextInt(16) + 2;
            
            for (GameObject object : objects) {
            	if (!(object.getPosX()==x && object.getPosY()==y)) {
            		Fire fire = new Fire(x, y);
            		objects.add(fire);
            	}
            }
        }
        
        for (int i = 0; i < numberOfHerbs; i++) { // Adds random herbs
            int x = rand.nextInt(16) + 2;
            int y = rand.nextInt(16) + 2;
            Herbs Herbs = new Herbs(x, y);
            objects.add(Herbs);
        }
        
        window.setGameObjects(this.getGameObjects());
        notifyView();
    }


    public void movePlayer(int x, int y, int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        int nextX = player.getPosX() + x;
        int nextY = player.getPosY() + y;

        boolean obstacle = false;
        for (GameObject object : objects) {
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
            }
            if (obstacle == true) {
                break;
            }
        }
        player.rotate(x, y);
        if (obstacle == false) {
            player.move(x, y);
        }
        notifyView();
    }

    public void action(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        Activable aimedObject = null;
		for(GameObject object : objects){
			if(object.isAtPosition(player.getFrontX(),player.getFrontY())){
			    if(object instanceof Activable){
			        aimedObject = (Activable) object;
			    }
			}
		}
		if(aimedObject != null){
		    aimedObject.activate();
            notifyView();
		}
        
    }

    private void notifyView() {
        window.update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
        notifyView();
    }


    public void playerPos(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        System.out.println(player.getPosX() + ":" + player.getPosY());
        
    }

}