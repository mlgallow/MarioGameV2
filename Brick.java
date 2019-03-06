import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

class Brick extends Sprite
{
    static Image[] Brick_image = null;

    Brick(int brick_x,int brick_y,int brick_w,int brick_h, Model m)
    {
        super(m);
        model = m;
        x = brick_x;
        y = brick_y;
        w = brick_w;
        h = brick_h;
    }
    boolean update()
    {
        return true;
    }
    boolean isCoin(){return false;}
    boolean isBrick()
    {
        return true;
    }
    boolean isCoinBlock()
    {
        return false;
    }
    boolean doesCollide(Sprite s)
    {
        return false;
    }
    void draw(Graphics g)
    {
        if (Brick_image == null)
        {
            Brick_image = new Image[1];
            try
            {
                Brick_image[0] = ImageIO.read(new File("Brick_1.png"));

            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        g.drawImage(Brick_image[0], this.x - model.scrollPos, y,w,h, null);
    }
    Brick(Json ob, Model m)
    {
        super(m);
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        model = m;
    }

    Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("Type", "Brick");
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }

    //void addBrick(int x, int y, int w, int h)
    //{
        // Array of bricks and adding new bricks to the array
    //    Brick b = new Brick(x, y, w, h);
    //    brick.add(b);
    //}
}

