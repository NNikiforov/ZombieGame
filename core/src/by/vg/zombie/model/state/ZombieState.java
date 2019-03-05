package by.vg.zombie.model.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import by.vg.zombie.utils.ZombieParser;

public enum ZombieState {
	STAND("zombie/anim_woodcutter_stand/anim_woodcutter_stand.xml", false),
	WALK_UP_RIGHT("zombie/anim_woodcutter_walk_up/anim_woodcutter_walk_up.xml", true),
	WALK_UP_LEFT("zombie/anim_woodcutter_walk_up/anim_woodcutter_walk_up.xml", false),
	WALK_DOWN_RIGHT("zombie/anim_woodcutter_walk_down/anim_woodcutter_walk_down.xml", true),
	WALK_DOWN_LEFT("zombie/anim_woodcutter_walk_down/anim_woodcutter_walk_down.xml", false),
	WAKE_UP("zombie/anim_woodcutter_wakeup/anim_woodcutter_wakeup.xml", false),
	WALK_WOOD_UP_RIGHT("zombie/anim_woodcutter_walkwood_up/anim_woodcutter_walkwood_up.xml", true),
	WALK_WOOD_UP_LEFT("zombie/anim_woodcutter_walkwood_up/anim_woodcutter_walkwood_up.xml", false),
	WALK_WOOD_DOWN_RIGHT("zombie/anim_woodcutter_walkwood_down/anim_woodcutter_walkwood_down.xml", true),
	WALK_WOOD_DOWN_LEFT("zombie/anim_woodcutter_walkwood_down/anim_woodcutter_walkwood_down.xml", false),
	WOODCUT("zombie/anim_woodcutter_woodcut/anim_woodcutter_woodcut.xml", false);

	ZombieState(String config, boolean isFlipped) {
		if (isFlipped) {
			animation = new ZombieParser(config).parseToFlippedAnimation();
		} else {
			animation = new ZombieParser(config).parseToAnimation();
		}
	}
	
	private Animation<TextureRegion> animation;
	private float elapsedTime = 0;

	public Animation<TextureRegion> getAnimation(){
		return animation;
	}

	public void setAnimation(Animation<TextureRegion> animation) {
		this.animation = animation;
	};

	public void draw(SpriteBatch batch, float x, float y) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw(animation.getKeyFrame(elapsedTime, !(this == ZombieState.WAKE_UP)), x, y);
		batch.end();
	}
}
