import java.awt.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;


class Mario extends Sprite {
    int prev_x;
    int prev_y;
    double vert_vel;
    int frame_count;
    int coin_counter = 0;
    static Image[] mario_images = null;
    Coin_Block coin_block;
    Coin coin;

    Mario(Model m, int xx, int yy) {
        super(m);
        frame_count = 0;
        w = 60;
        h = 95;
        x = xx;
        y = yy;
        model = m;
    }

    void prev_location() {
        prev_x = x;
        prev_y = y;
    }

    Mario(Json ob, Model m) {
        super(m);
        x = (int) ob.getLong("x");
        y = (int) ob.getLong("y");
        w = (int) ob.getLong("w");
        h = (int) ob.getLong("h");
        model = m;
    }

    Json marshall() {
        Json ob = Json.newObject();
        ob.add("Type", "Mario");
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }

    boolean isBrick() {
        return false;
    }
    boolean isCoin(){return false;}
    boolean isCoinBlock() {
        return false;
    }

    void get_out(Sprite s) {
        if (this.x + this.w > s.x && prev_x + w <= s.x) // If mario enters from the left
        {
            this.x = s.x - this.w;
        } else if (this.x < s.x + s.w && prev_x >= s.x + s.w) // If mario enters form the right
        {
            this.x = s.x + s.w;
        }
        if (this.y + this.h > s.y && prev_y + h <= s.y) // If mario enters form the top
        {
            this.y = s.y - this.h;
            vert_vel = 0;
            frame_count = 0;
        } else if (y < s.y + s.h && prev_y >= s.y + s.h) // If mario enters form the bottom
        {
            y = s.y + s.h;
            vert_vel = 2;
            if(s.isCoinBlock())
            {
                Coin_Block cb = (Coin_Block) s; //casting
                if (coin_counter < 6)
                {
                    coin_counter++;
                }

                cb.pop_out_coin(coin_counter);
            }
        }
    }

    boolean update() {
        // Locks the scrolling to mario's x position
        model.scrollPos = x - 200;
        // Setting mario's velocity to a specific number
        vert_vel += 3.14159;
        y += vert_vel;

        // Stop when mario hits the ground
        if (y > 515) {
            y = 515;
            vert_vel = 0;
            frame_count = 0;
        }
        //Check for collisions with all the bricks and coin blocks
        for (int i = 0; i < model.sprites.size(); i++)
        {
            // Draws Sprites
            Sprite s = model.sprites.get(i);
                if (s.isBrick())
                {
                    if (s.doesCollide(this, s))
                    {
                        get_out(s);
                    }
                }
                if (s.isCoinBlock())
                {
                    if (s.doesCollide(this, s))
                    {
                        get_out(s);
                    }
                }
                if(s.isCoin())
                {
                    if(s.doesCollide(this, s))
                    {
                        model.sprites.remove(s);
                    }
                }
        }
        frame_count++;
        return true;
    }

        void draw(Graphics g)
        {
            if (mario_images == null) {
                mario_images = new Image[5];

                try {
                    mario_images[0] = ImageIO.read(new File("mario1.png"));
                    mario_images[1] = ImageIO.read(new File("mario2.png"));
                    mario_images[2] = ImageIO.read(new File("mario3.png"));
                    mario_images[3] = ImageIO.read(new File("mario4.png"));
                    mario_images[4] = ImageIO.read(new File("mario5.png"));
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                    System.exit(1);
                }
            }

            //20 slows down how fast he runs and 5 is the number of frames we have to work with
            int marioFrame = (Math.abs(model.mario.x / 20)) % 5;
            //Draw/animate mario
                 g.drawImage(mario_images[marioFrame], this.x - model.scrollPos, this.y, null);

        }
    }


