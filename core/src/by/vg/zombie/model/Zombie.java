package by.vg.zombie.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends GameObject {

	private Animation<TextureRegion> current;
	private Animation<TextureRegion> stand;
	private Animation<TextureRegion> wake_up;
	private Animation<TextureRegion> walking_up_right;
	private Animation<TextureRegion> walking_down_right;
	private Animation<TextureRegion> walking_up_left;
	private Animation<TextureRegion> walking_down_left;
	private float elapsedTime = 0;
	private float speed = 50f;
	private Vector2 target = new Vector2(bounds.x, bounds.y);

	public Zombie(Animation<TextureRegion> animation, float x, float y,
			float width, float height) {
		super(x, y, width, height);
		this.stand = animation;
		this.current = animation;
	}

	public Vector2 getTarget() {
		return target;
	}

	public void setTarget(Vector2 target) {
		this.target = target;
	}

	public Animation<TextureRegion> getStand() {
		return stand;
	}

	public void setStand(Animation<TextureRegion> stand) {
		this.stand = stand;
	}

	public Animation<TextureRegion> getWalking_up_right() {
		return walking_up_right;
	}

	public Animation<TextureRegion> getWalking_down_right() {
		return walking_down_right;
	}

	public Animation<TextureRegion> getWalking_up_left() {
		return walking_up_left;
	}

	public Animation<TextureRegion> getWalking_down_left() {
		return walking_down_left;
	}
	
	public void setWalking_up_right(Animation<TextureRegion> walking_up_right) {
		this.walking_up_right = walking_up_right;
	}

	public void setWalking_down_right(Animation<TextureRegion> walking_down_right) {
		this.walking_down_right = walking_down_right;
	}

	public void setWalking_up_left(Animation<TextureRegion> walking_up_left) {
		this.walking_up_left = walking_up_left;
	}

	public void setWalking_down_left(Animation<TextureRegion> walking_down_left) {
		this.walking_down_left = walking_down_left;
	}

	public Animation<TextureRegion> getWake_up() {
		return wake_up;
	}

	public void setWake_up(Animation<TextureRegion> wake_up) {
		this.wake_up = wake_up;
	}

	public void draw(SpriteBatch batch) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.begin();
		move();
		batch.draw(current.getKeyFrame(elapsedTime, true), bounds.x,
				bounds.y);
		batch.end();
	}

	public void move() {
		float deltaT = Gdx.graphics.getDeltaTime();
		double alpha = Math.PI / 6;
		Vector2 o1 = new Vector2(1, (float) Math.tan(alpha));
		Vector2 o2 = new Vector2(1, (float) -Math.tan(alpha));
		Vector2 vec = new Vector2(target.x - bounds.x, target.y - bounds.y);
		float offset = (float) (deltaT * speed);
		float x = target.x - bounds.x;
		float y = target.y - bounds.y;
		float tan = (x != 0 ? y / x : 1);
		if (vec.len() > 10) {

			if (x > 0 && Math.abs(tan) <= Math.tan(alpha)) {
				if (Math.abs(vec.angle(o1)) < 1) {
					float tmpY = (float) (vec.len());
					if (tmpY >= offset) {
						bounds.x += offset * Math.cos(alpha);
						bounds.y += offset * Math.sin(alpha);
					} else {
						bounds.x += tmpY * Math.cos(alpha);
						bounds.y += tmpY * Math.sin(alpha);
					}
				}
				float tmpX = (float) (Math.cos(o2.angleRad(vec)) * vec.len());
				if (tmpX >= offset) {
					bounds.x += offset * Math.cos(alpha);
					bounds.y -= offset * Math.sin(alpha);
				} else {
					bounds.x += tmpX * Math.cos(alpha);
					bounds.y -= tmpX * Math.sin(alpha);
				}
			}

			if (x < 0 && Math.abs(tan) < Math.tan(alpha)) {
				if (Math.abs(vec.angle(o1)) < 1) {
					float tmpY = (float) (vec.len());
					if (tmpY >= offset) {
						bounds.x -= offset * Math.cos(alpha);
						bounds.y -= offset * Math.sin(alpha);
					} else {
						bounds.x -= tmpY * Math.cos(alpha);
						bounds.y -= tmpY * Math.sin(alpha);
					}
				}
				float tmpX = (float) (Math.cos(o2.angleRad(vec)) * vec.len());
				if (Math.abs(tmpX) >= offset) {
					bounds.x -= offset * Math.cos(alpha);
					bounds.y += offset * Math.sin(alpha);
				} else {
					bounds.x -= tmpX * Math.cos(alpha);
					bounds.y += tmpX * Math.sin(alpha);
				}
			}

			if (y > 0 && Math.abs(tan) > Math.tan(alpha)) {
				o2.scl(-1);
				if (Math.abs(vec.angle(o2)) < 1) {
					float tmpX = (float) (vec.len());
					if (tmpX >= offset) {
						bounds.x -= offset * Math.cos(alpha);
						bounds.y += offset * Math.sin(alpha);
					} else {
						bounds.x -= tmpX * Math.cos(alpha);
						bounds.y += tmpX * Math.sin(alpha);
					}
					return;
				}
				float a = o1.angleRad(vec);
				float tmpY = (float) (Math.sin(4 * alpha - a) * vec.len()
						/ Math.sin(2 * alpha));
				if (Math.abs(tmpY) >= offset) {
					bounds.x += offset * Math.cos(alpha);
					bounds.y += offset * Math.sin(alpha);
				} else {
					bounds.x += tmpY * Math.cos(alpha);
					bounds.y += tmpY * Math.sin(alpha);
				}
			}

			if (y < 0 && Math.abs(tan) > Math.tan(alpha)) {
				o1.scl(-1);
				if (Math.abs(vec.angle(o1)) < 1) {
					float tmpX = (float) (vec.len());
					if (tmpX >= offset) {
						bounds.x += offset * Math.cos(alpha);
						bounds.y -= offset * Math.sin(alpha);
					} else {
						bounds.x += tmpX * Math.cos(alpha);
						bounds.y -= tmpX * Math.sin(alpha);
					}
					return;
				}
				float a = o1.angleRad(vec);
				float tmpY = (float) (Math.sin(4 * alpha - a) * vec.len()
						/ Math.sin(2 * alpha));
				if (Math.abs(tmpY) >= offset) {
					bounds.x -= offset * Math.cos(alpha);
					bounds.y -= offset * Math.sin(alpha);
				} else {
					bounds.x -= tmpY * Math.cos(alpha);
					bounds.y -= tmpY * Math.sin(alpha);
				}
			}
		}
	}
}
