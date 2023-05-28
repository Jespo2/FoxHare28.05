import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
public class Hare extends Organism{



    public Hare(int x, int y) {
        super(x, y);

        daysAlive = 0;
        breedCountdown = 3;

    }

    @Override
    public void move() throws NoSuchAlgorithmException {
        Random rando = SecureRandom.getInstance("SHA1PRNG");
        int randomInt = rando.nextInt(4);
        World.setAt(x, y, null);
        if(randomInt==0){
            if(y-1>=0&&isFreeSpace(x,y-1)){
                y=y-1;
            }
        }
        if(randomInt==1){
            if(x+1<=24&&isFreeSpace(x+1,y)){
                x=x+1;
            }
        }
        if(randomInt==2){
            if(y+1<=24&&isFreeSpace(x,y+1)){
                y=y+1;
            }
        }
        if(randomInt==3){
            if(x-1>=0&&isFreeSpace(x-1,y)){
                x=x-1;
            }
        }
        setX(x);
        setY(y);

        World.setAt(x, y, this);
        daysAlive++;

    }

    @Override
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void breed() {
        if (daysAlive  == 3) {
            int newX = x;
            int newY = y;


            if (y - 1 >= 0 && isFreeSpace(x, y - 1)) {
                newY = y - 1;
            }

             if (y + 1 < World.getHeight() && isFreeSpace(x, y + 1)) {
                newY = y + 1;
            }

             if (x - 1 >= 0 && isFreeSpace(x - 1, y)) {
                newX = x - 1;
            }

             if (x + 1 < World.getWidth() && isFreeSpace(x + 1, y)) {
                newX = x + 1;
            }


            if (newX != x || newY != y) {
                Hare newHare = new Hare(newX, newY);

                World.setAt(newX, newY, newHare);
                World.hareChildren.add(newHare);
            }
            daysAlive=0;
        }
    }

    @Override
    public void starve() {

    }

    public boolean isFreeSpace(int x, int y) {

        if (y  >= 0 && World.getAt(x, y ) == null) {
            return true;
        }


        if (y < World.getHeight() && World.getAt(x, y) == null) {
            return true;
        }


        if (x >= 0 && World.getAt(x , y) == null) {
            return true;
        }


        if (x < World.getWidth() && World.getAt(x , y) == null) {
            return true;
        }else
        return false;
    }




    public char getPrintableChar(){
        return 'H';
    }
    public int getX() {
        return x;
    }
    public int getY(){return y;}


}
