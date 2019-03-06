import java.awt.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.util.Random;

class Coin extends Sprite {
    static Image[] Coin_image = null;
    double ver_velocity;
    double hori_velocity;
    int prev_x;
    int prev_y;
    Coin(int x_start, int y_start, Model m, double hv)
    {
        super(m);
        model = m;
        x = x_start;
        y = y_start;
        hori_velocity = hv;
    }
    void prev_location() {
        prev_x = x;
        prev_y = y;
    }
    boolean keep_living()
    {
        if(y > 700)
            return false;
        return true;
    }
    boolean update()
    {
        ver_velocity = 3.14159;
        y += ver_velocity;
        x -= hori_velocity;

        for(int i = 0; i <model.sprites.size(); i++) {
            // Draws Sprites
            Sprite s = model.sprites.get(i);

            if(!keep_living())
            {
                model.sprites.remove(this);
            }
        }

        return true;
    }
    boolean isCoin(){return true;}
    boolean isBrick()
    {
        return false;
    }
    boolean isCoinBlock()
    {
        return false;
    }

    void draw(Graphics g)
    {
        if (Coin_image == null)
        {
            Coin_image = new Image[1];
            try
            {
                Coin_image[0] = ImageIO.read(new File("Coin.png"));
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        g.drawImage(Coin_image[0],x - model.scrollPos, y - 70  ,40,40, null);

    }
    Coin(Json ob, Model m)
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
        ob.add("Type", "Coin");
         ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }


}
