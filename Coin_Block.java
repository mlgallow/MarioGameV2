import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

class Coin_Block extends Sprite{

        static Image[] Coin_Block_image = null;
        static Image[] Empty_Coin_Block_image = null;
        int coins_remaining = 5;
        static Random r = new Random();
        int coin_frame_count;

        Coin_Block(int block_x,int block_y,int block_w,int block_h, Model m)
        {
            super(m);
            x = block_x;
            y = block_y;
            w = block_w;
            h = block_h;
        }
        boolean update()
        {
            r = new Random();
            return true;
        }
        boolean isCoin(){return false;}
        boolean isBrick()
        {
            return false;
        }
        boolean isCoinBlock()
        {
            return true;
        }
        boolean doesCollide(Sprite s)
        {
            return false;
        }

        void pop_out_coin(int coin_frame_count)
        {
            double horiz_velocity = r.nextDouble();
            horiz_velocity *=16;
            horiz_velocity -= 8;
            if(coins_remaining > 0) {
                Coin c = new Coin(x, y, model, horiz_velocity);
                model.sprites.add(c);
                coins_remaining--;
                System.out.println(coins_remaining);
            }
        }

        void draw(Graphics g)
        {
            if ((Coin_Block_image == null) && Empty_Coin_Block_image == null)
            {
                Coin_Block_image = new Image[1];
                Empty_Coin_Block_image = new Image[1];
                try
                {
                    Coin_Block_image[0] = ImageIO.read(new File("Coin_Block.png"));
                    Empty_Coin_Block_image[0] = ImageIO.read(new File("Empty_Coin_Block.png"));
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.err);
                    System.exit(1);
                }
            }
            if(coins_remaining > 0)
            {
                g.drawImage(Coin_Block_image[0], this.x - model.scrollPos, y,w,h, null);
            }
            else
                g.drawImage(Empty_Coin_Block_image[0], this.x - model.scrollPos, y,w,h, null);
        }
        Coin_Block(Json ob, Model m)
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
            ob.add("Type", "Coin_Block");
            ob.add("x", x);
            ob.add("y", y);
            ob.add("w", w);
            ob.add("h", h);
            return ob;
        }

    }
