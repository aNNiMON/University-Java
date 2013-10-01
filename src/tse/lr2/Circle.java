package tse.lr2;

/**
 *
 * @author aNNiMON
 */
public class Circle extends Ellipse {

    public boolean isConsist() {
        return false;
    }
    
    @Override
    public String toString() {
        return "This is circle!\n" + super.toString();
    }
    
}
