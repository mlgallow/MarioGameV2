import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements MouseListener, KeyListener
{
    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;
    boolean keySpace;
    int mouseDownX;
    int mouseDownY;

    View view;
    Model model;
   // Mario mario;

    void setView(View v)
    {
        view = v;
    }
    Controller(Model m)
    {
        model = m;
    }



    public void mousePressed(MouseEvent e)
    {
        mouseDownX = e.getX();
        mouseDownY = e.getY();
    }
    //Allows you to draw the boxs by dragging your mouse
    public void mouseReleased(MouseEvent e)
    {
        int x1 = mouseDownX;
        int x2 = e.getX();
        int y1 = mouseDownY;
        int y2 = e.getY();
        int left = Math.min(x1, x2);
        int right = Math.max(x1, x2);
        int top = Math.min(y1, y2);
        int bottom = Math.max(y1, y2);

        //adds the bricks
        //model.addBrick(left + model.scrollPos, top, right - left, bottom - top);
    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
    public void mouseClicked(MouseEvent e) {    }
    //Allows the use of the keyboard to control the turtles movements
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT: keyRight = true; break;
            case KeyEvent.VK_LEFT: keyLeft = true; break;
            case KeyEvent.VK_UP: keyUp = true; break;
            case KeyEvent.VK_DOWN: keyDown = true; break;
            // Sets S as our save button and calls the function in the model
            case KeyEvent.VK_S: model.save("map.json"); break;
            // Sets L as our load button and calls the function in the model
            case KeyEvent.VK_L: model.load("map.json"); break;
            // Sets Spacebar to jump button
            case KeyEvent.VK_SPACE: keySpace = true; break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT: keyRight = false; break;
            case KeyEvent.VK_LEFT: keyLeft = false; break;
            case KeyEvent.VK_UP: keyUp = false; break;
            case KeyEvent.VK_DOWN: keyDown = false; break;
            case KeyEvent.VK_SPACE: keySpace = false; break;
        }
    }

    public void keyTyped(KeyEvent e)
    {

    }

    void update()
    {
        model.prev_locations();

        if (keyRight)
        {
            model.mario.x += 10;
        }
        if (keyLeft)
        {
            model.mario.x -= 10;
        }
        if (keySpace)
        {
             if(model.mario.frame_count < 5)
                model.mario.vert_vel = -21;




        }
    }
}

