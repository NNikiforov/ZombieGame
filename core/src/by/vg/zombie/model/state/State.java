package by.vg.zombie.model.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class State {
	public abstract Animation<TextureRegion> getAnimation();

	public abstract void setAnimation(Animation<TextureRegion> animation);
}
