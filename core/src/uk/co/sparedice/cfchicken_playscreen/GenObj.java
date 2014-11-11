/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.sparedice.cfchicken_playscreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Michael
 */
public interface GenObj {
    
    public void render(SpriteBatch batch, ShapeRenderer renderer);
    
    public void update(float delta);
    
    public Rectangle getWorldCollisionRectangle();

    public void onChickenCollide(Chicken c);
    
}
