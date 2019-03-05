package by.vg.zombie.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
	
	Rectangle bounds;

	public GameObject(float x, float y, float width, float height) {
		bounds = new Rectangle(x, y, width, height);
	}
	
	public void setPosition(float x, float y) {
		bounds.x = x;
		bounds.y = y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public abstract void draw(SpriteBatch batch);

	public abstract boolean isActive();
}
