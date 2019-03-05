package by.vg.zombie.model.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import by.vg.zombie.utils.ZombieParser;

public class WallkDownLeftState extends State {

	private static final String CONFIG = "zombie/anim_woodcutter_walk_down/anim_woodcutter_walk_down.xml";

	protected static Animation<TextureRegion> animation;
	
	{
		animation = new ZombieParser(CONFIG).parseToAnimation();
	}

	public Animation<TextureRegion> getAnimation() {
		return animation;
	}

	public void setAnimation(Animation<TextureRegion> animation) {
		WallkDownLeftState.animation = animation;
	}
}
