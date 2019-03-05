package by.vg.zombie.model.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import by.vg.zombie.utils.ZombieParser;

public enum ClothState {
	NO("", false),
	STAND("cloth/anim_woodcutter_cloth_stand/anim_woodcutter_cloth_stand.xml", false),
	WALK_DOWN_LEFT("cloth/anim_woodcutter_cloth_walk_down/anim_woodcutter_cloth_walk_down.xml", false),
	WALK_DOWN_RIGHT("cloth/anim_woodcutter_cloth_walk_down/anim_woodcutter_cloth_walk_down.xml", true),
	WALK_UP_LEFT("cloth/anim_woodcutter_cloth_walk_up/anim_woodcutter_cloth_walk_up.xml", false),
	WALK_UP_RIGHT("cloth/anim_woodcutter_cloth_walk_up/anim_woodcutter_cloth_walk_up.xml", true),
	WALK_WOOD_DOWN_LEFT("cloth/anim_woodcutter_cloth_walkwood_down/anim_woodcutter_cloth_walkwood_down.xml", false),
	WALK_WOOD_DOWN_RIGHT("cloth/anim_woodcutter_cloth_walkwood_down/anim_woodcutter_cloth_walkwood_down.xml", true),
	WALK_WOOD_UP_LEFT("cloth/anim_woodcutter_cloth_walkwood_up/anim_woodcutter_cloth_walkwood_up.xml", false),
	WALK_WOOD_UP_RIGHT("cloth/anim_woodcutter_cloth_walkwood_up/anim_woodcutter_cloth_walkwood_up.xml", true),
	WOODCUT("cloth/anim_woodcutter_cloth_woodcut/anim_woodcutter_cloth_woodcut.xml", false),
	STAND_D("cloth/anim_woodcutter_double_cloth_stand/anim_woodcutter_double_cloth_stand.xml", false),
	WALK_DOWN_LEFT_D("cloth/anim_woodcutter_double_cloth_walk_down/anim_woodcutter_double_cloth_walk_down.xml", false),
	WALK_DOWN_RIGHT_D("cloth/anim_woodcutter_double_cloth_walk_down/anim_woodcutter_double_cloth_walk_down.xml", true),
	WALK_UP_LEFT_D("cloth/anim_woodcutter_double_cloth_walk_up/anim_woodcutter_double_cloth_walk_up.xml", false),
	WALK_UP_RIGHT_D("cloth/anim_woodcutter_double_cloth_walk_up/anim_woodcutter_double_cloth_walk_up.xml", true),
	WALK_WOOD_DOWN_LEFT_D("cloth/anim_woodcutter_double_cloth_walkwood_down/anim_woodcutter_double_cloth_walkwood_down.xml", false),
	WALK_WOOD_DOWN_RIGHT_D("cloth/anim_woodcutter_double_cloth_walkwood_down/anim_woodcutter_double_cloth_walkwood_down.xml", true),
	WALK_WOOD_UP_LEFT_D("cloth/anim_woodcutter_double_cloth_walkwood_up/anim_woodcutter_double_cloth_walkwood_up.xml", false),
	WALK_WOOD_UP_RIGHT_D("cloth/anim_woodcutter_double_cloth_walkwood_up/anim_woodcutter_double_cloth_walkwood_up.xml", true),
	WOODCUT_D("cloth/anim_woodcutter_double_cloth_woodcut/anim_woodcutter_double_cloth_woodcut.xml", false);

	ClothState(String config, boolean isFlipped) {
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
