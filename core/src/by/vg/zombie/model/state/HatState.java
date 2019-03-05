package by.vg.zombie.model.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import by.vg.zombie.utils.ZombieParser;

public enum HatState {
	NO("", false),
	STAND("hat/anim_woodcutter_hat_stand/anim_woodcutter_hat_stand.xml", false),
	WALK_DOWN_LEFT("hat/anim_woodcutter_hat_walk_down/anim_woodcutter_hat_walk_down.xml", false),
	WALK_DOWN_RIGHT("hat/anim_woodcutter_hat_walk_down/anim_woodcutter_hat_walk_down.xml", true),
	WALK_UP_LEFT("hat/anim_woodcutter_hat_walk_up/anim_woodcutter_hat_walk_up.xml", false),
	WALK_UP_RIGHT("hat/anim_woodcutter_hat_walk_up/anim_woodcutter_hat_walk_up.xml", true),
	WALK_WOOD_DOWN_LEFT("hat/anim_woodcutter_hat_walkwood_down/anim_woodcutter_hat_walkwood_down.xml", false),
	WALK_WOOD_DOWN_RIGHT("hat/anim_woodcutter_hat_walkwood_down/anim_woodcutter_hat_walkwood_down.xml", true),
	WALK_WOOD_UP_LEFT("hat/anim_woodcutter_hat_walkwood_up/anim_woodcutter_hat_walkwood_up.xml", false),
	WALK_WOOD_UP_RIGHT("hat/anim_woodcutter_hat_walkwood_up/anim_woodcutter_hat_walkwood_up.xml", true),
	WOODCUT("hat/anim_woodcutter_hat_woodcut/anim_woodcutter_hat_woodcut.xml", false),
	STAND_D("hat/anim_woodcutter_double_hat_stand/anim_woodcutter_double_hat_stand.xml", false),
	WALK_DOWN_LEFT_D("hat/anim_woodcutter_double_hat_walk_down/anim_woodcutter_double_hat_walk_down.xml", false),
	WALK_DOWN_RIGHT_D("hat/anim_woodcutter_double_hat_walk_down/anim_woodcutter_double_hat_walk_down.xml", true),
	WALK_UP_LEFT_D("hat/anim_woodcutter_double_hat_walk_up/anim_woodcutter_double_hat_walk_up.xml", false),
	WALK_UP_RIGHT_D("hat/anim_woodcutter_double_hat_walk_up/anim_woodcutter_double_hat_walk_up.xml", true),
	WALK_WOOD_DOWN_LEFT_D("hat/anim_woodcutter_double_hat_walkwood_down/anim_woodcutter_double_hat_walkwood_down.xml", false),
	WALK_WOOD_DOWN_RIGHT_D("hat/anim_woodcutter_double_hat_walkwood_down/anim_woodcutter_double_hat_walkwood_down.xml", true),
	WALK_WOOD_UP_LEFT_D("hat/anim_woodcutter_double_hat_walkwood_up/anim_woodcutter_double_hat_walkwood_up.xml", false),
	WALK_WOOD_UP_RIGHT_D("hat/anim_woodcutter_double_hat_walkwood_up/anim_woodcutter_double_hat_walkwood_up.xml", true),
	WOODCUT_D("hat/anim_woodcutter_double_hat_woodcut/anim_woodcutter_double_hat_woodcut.xml", false);

	HatState(String config, boolean isFlipped) {
		if (!config.equals("")) {
			if (isFlipped) {
				animation = new ZombieParser(config).parseToFlippedAnimation();
			} else {
				animation = new ZombieParser(config).parseToAnimation();
			}
		}
	}

	private Animation<TextureRegion> animation;
	private float elapsedTime = 0;

	public Animation<TextureRegion> getAnimation() {
		return animation;
	}

	public void setAnimation(Animation<TextureRegion> animation) {
		this.animation = animation;
	};

	public void draw(SpriteBatch batch, float x, float y) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		if (this != NO) {
			batch.begin();
			batch.draw(animation.getKeyFrame(elapsedTime, true), x, y);
			batch.end();
		}
	}
}
