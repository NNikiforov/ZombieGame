package by.vg.zombie.model.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import by.vg.zombie.utils.ZombieParser;

public class StandState extends State {

	private static final String CONFIG = "zombie/anim_woodcutter_stand/anim_woodcutter_stand.xml";
	
	protected static Animation<TextureRegion> animation;

	static {
		animation = new ZombieParser(CONFIG).parseToAnimation();
		//animation.setFrameDuration(1/15f);
	}
	
	public Animation<TextureRegion> getAnimation() {
		return animation;
	}

	public void setAnimation(Animation<TextureRegion> animation) {
		WakeUpState.animation = animation;
	}
}
