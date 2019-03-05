package by.vg.zombie.model.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import by.vg.zombie.utils.ZombieParser;

public class WalkUpRightState extends State {

	private static final String CONFIG = "zombie/anim_woodcutter_walk_up/anim_woodcutter_walk_up.xml";

	protected static Animation<TextureRegion> animation;

	{
		animation = new ZombieParser(CONFIG).parseToFlippedAnimation();
	}

	public Animation<TextureRegion> getAnimation() {
		return animation;
	}

	public void setAnimation(Animation<TextureRegion> animation) {
		WalkUpRightState.animation = animation;
	}
}
