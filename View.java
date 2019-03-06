import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
    Model model;
    Image background;
    View(Controller c, Model m)
    {
        model = m;
        c.setView(this);
        try {
            background = ImageIO.read(new File("super_mario_backgrounds_edit.png"));

        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    public void paintComponent(Graphics g)
    {
        //Clear the screen
        g.setColor(new Color(128, 255, 255));
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        // To be moved to model later (Drawing the ground)
        g.setColor(new Color(139,69,19));
        g.fillRect(0, 595, 900,700);
        // Draw the background
        g.drawImage(background,(model.scrollPos/-3) - 80, 0, null);
        // cycles through the array of sprites
        for(int i = 0; i < model.sprites.size(); i++)
        {
            // Draws Sprites
            Sprite s = model.sprites.get(i);
            s.draw(g);
        }
    }
}
