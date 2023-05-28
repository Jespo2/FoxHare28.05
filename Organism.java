import java.security.NoSuchAlgorithmException;


abstract class Organism {
    int x;
    int y;
    int daysAlive;
    int breedCountdown;
    int daysDying;
    public Organism(int x, int y) {
        this.x = x;
        this.y = y;

    }
    public abstract void move () throws NoSuchAlgorithmException;
    public abstract void breed ();
    public abstract void starve();
    public abstract char getPrintableChar();

    public abstract int getX();

    public abstract void setX(int x);

    public abstract int getY();

    public abstract void setY(int y);

}


