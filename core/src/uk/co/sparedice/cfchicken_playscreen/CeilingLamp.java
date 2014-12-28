/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.sparedice.cfchicken_playscreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import uk.co.sparedice.cfchicken1.AssetLoader;

/**
 *
 * @author Michael
 */
public class CeilingLamp implements GenObj {

    private float x, y;

    public CeilingLamp(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void render(SpriteBatch batch, ShapeRenderer renderer) {
        batch.begin();
            Texture tex = AssetLoader.ceiling_lamp_on;
            float ratio = tex.getWidth() / tex.getHeight();
            batch.draw(tex, x, y, 300, 300);
        batch.end();
    }

    @Override
    public void update(float delta) {
        //
    }

    @Override
    public Rectangle getWorldCollisionRectangle() {
        return null;
    }

    @Override
    public void onChickenCollide(Chicken c) {
        //
    }
    
}
