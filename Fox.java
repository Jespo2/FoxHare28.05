import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Fox extends Organism{


    int daysDying = 0;
    Fox(int x, int y){
        super(x,y);
        daysAlive = 0;
        breedCountdown = 3;
        int daysDying = 0;
    }
    @Override
    public void move() throws NoSuchAlgorithmException {
        Organism nearbyHare = findNearbyHare();

        if (nearbyHare==null){

            Random rando = SecureRandom.getInstance("SHA1PRNG");
            int randomInt = rando.nextInt(4);
            World.setAt(x, y, null);

            if (randomInt == 0 && y - 1 >= 0 && isFreeSpace(x, y - 1)) {
                y = y - 1;
            } else if (randomInt == 1 && x + 1 <= 3 && isFreeSpace(x + 1, y)) {
                x = x + 1;
            } else if (randomInt == 2 && y + 1 <= 3 && isFreeSpace(x, y + 1)) {
                y = y + 1;
            } else if (randomInt == 3 && x - 1 >= 0 && isFreeSpace(x - 1, y)) {
                x = x - 1;
            }
            daysDying++;

        }else if (nearbyHare instanceof Hare){
            World.setAt(x,y,null);
            int newX = nearbyHare.getX();
            int newY = nearbyHare.getY();
            World.hareChildren.remove(nearbyHare);
            x=newX;
            y=newY;
            daysDying=0;

        }
        World.setAt(x, y, this);

        daysAlive++;;

    }



    @Override
    public void breed() {
        if (daysAlive == 8 ) {
            int newX = x;
            int newY = y;


            if (y - 1 >= 0 && isFreeSpace(x, y - 1)) {
                newY = y - 1;
            }

            else if (y + 1 < World.getHeight() && isFreeSpace(x, y + 1)) {
                newY = y + 1;
            }

            else if (x - 1 >= 0 && isFreeSpace(x - 1, y)) {
                newX = x - 1;
            }

            else if (x + 1 < World.getWidth() && isFreeSpace(x + 1, y)) {
                newX = x + 1;
            }


            if (newX != x || newY != y) {
                Fox newFox = new Fox(newX, newY);
                World.foxChildren.add(newFox);
                World.setAt(newX, newY, newFox);

                daysAlive=0;
            }

        }
    }

    @Override
    public void starve() {
        if(daysDying==4){

            World.setAt(x, y, null);
            World.foxChildren.remove(this);
        }

    }

    @Override
    public char getPrintableChar() {
        return 'F';
    }

    @Override
    public int getX() {
        return this.x;
    }

    public boolean isFreeSpace(int x, int y) {
        // Check oben
        if (y  >= 0 && World.getAt(x, y ) == null) {
            return true;
        }

        // Check unten
        if (y < World.getHeight() && World.getAt(x, y) == null) {
            return true;
        }

        // Check links
        if (x >= 0 && World.getAt(x , y) == null) {
            return true;
        }

        // Check rechts
        if (x < World.getWidth() && World.getAt(x , y) == null) {
            return true;
        }

        return false; // Kein freies Feld in den vier Richtungen gefunden
    }
    public Organism findNearbyHare() {
        // Check oben
        if (y - 1 >= 0 && World.getAt(x, y - 1) instanceof Hare) {
            return  World.getAt(x,y-1);
        }

        // Check unten
        if (y + 1 < World.getHeight() && World.getAt(x, y + 1) instanceof Hare) {
            return World.getAt(x,y+1);
        }

        // Check links
        if (x - 1 >= 0 && World.getAt(x - 1, y) instanceof Hare) {
            return World.getAt(x-1,y);
        }

        // Check rechts
        if (x + 1 < World.getWidth() && World.getAt(x + 1, y) instanceof Hare) {
            return World.getAt(x+1,y);
        }

        return null;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }



}
