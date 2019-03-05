package by.vg.zombie.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import by.vg.zombie.utils.ZombieParser;

public class WhiteWave extends GameObject {

	private static Animation<TextureRegion> animation;
	private float elapsedTime = 0;

	static {
		animation = new ZombieParser("white_wave/white_wave.xml").parseToAnimation();
		animation.setFrameDuration(1/5f);
	}
	
	public WhiteWave(float x, float y) {
		super(x, y, animation.getKeyFrame(0).getRegionWidth(), animation.getKeyFrame(0).getRegionHeight());
	}

	public void draw(SpriteBatch batch) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw(animation.getKeyFrame(elapsedTime, false), bounds.x,
				bounds.y);
		batch.end();
	}

	@Override
	public boolean isActive() {
		return !animation.isAnimationFinished(elapsedTime);
	}
}
