import java.awt.Graphics;
abstract class Sprite {
    int x;
    int y;
    int w;
    int h;
    Model model;
    Sprite(Model m)
    {
        model = m;
    }

    abstract boolean update();
    {
    }

    abstract void draw(Graphics g);
    {
    }

    abstract Json marshall();
    {
    }

    abstract boolean isBrick();
    {
    }
    abstract boolean isCoinBlock();
    {
    }
    abstract boolean isCoin();
    {
    }
    boolean doesCollide(Sprite object1, Sprite object2)
    {
        if (object1.x + object1.w <= object2.x) {
            return false;
        }
        if (object1.x >= object2.x + object2.w) {
            return false;
        }
        if (object1.y + object1.h <= object2.y) {
            return false;
        }
        if (object1.y >= object2.y + object2.h) {
            return false;
        }
        else
            return true;
    }


}
