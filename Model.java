import java.util.*;


class Model
{
    int scrollPos;
    ArrayList<Sprite> sprites;
    Mario mario;
    Sprite sprite;

    Model()
    {
    }

    void prev_locations()
    {
        mario.prev_location();

    }


    void update()
    {
        for(int i = 0; i <sprites.size(); i++) {
            // Draws Sprites
            Sprite s = sprites.get(i);
            s.update();
        }
    }

    void unmarshall(Json ob)
    {
        sprites = new ArrayList<Sprite>();
        sprites.clear();
        Json json_sprites = ob.get("sprites");
        for(int i = 0; i < json_sprites.size(); i++)
        {
            Json j = json_sprites.get(i);
            if(json_sprites.get(i).getString("Type").equals("Brick"))
            {
                sprites.add(new Brick(json_sprites.get(i),this));
            }
            if(json_sprites.get(i).getString("Type").equals("Mario"))
            {
                mario = new Mario(this, (int)j.getLong("x"),(int)j.getLong("y"));
                sprites.add(mario);
            }
            if(json_sprites.get(i).getString("Type").equals("Coin_Block"))
            {
                sprites.add(new Coin_Block(json_sprites.get(i), this));
            }
        }
    }

    Json marshall()
    {
        Json ob = Json.newObject();
        Json json_sprites = Json.newList();
        ob.add("sprites", json_sprites);
        for(int i = 0; i< sprites.size(); i++)
        {
            Sprite s = sprites.get(i);
            Json j = s.marshall();
            json_sprites.add(j);
        }
        return ob;
    }
    void save(String filename)
    {
        //Allows us to save to a file
        Json ob = marshall();
        ob.save(filename);
    }

    void load(String filename)
    {
        //Allows us to load our saved file
        Json ob = Json.load(filename);
        unmarshall(ob);
    }

}

