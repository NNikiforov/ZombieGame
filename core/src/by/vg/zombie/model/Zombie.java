package by.vg.zombie.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import by.vg.zombie.model.state.StandState;
import by.vg.zombie.model.state.State;
import by.vg.zombie.model.state.WalkUpLeftState;
import by.vg.zombie.model.state.WalkUpRightState;
import by.vg.zombie.model.state.WallkDownLeftState;
import by.vg.zombie.model.state.WallkDownRightState;

public class Zombie extends GameObject {

	private State state;

	private float elapsedTime = 0;
	private float speed = 50f;
	private Vector2 target = new Vector2(bounds.x, bounds.y);

	public Zombie(State initialState, float x, float y,
			float width, float height) {
		super(x, y, width, height);
		state = initialState;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Vector2 getTarget() {
		return target;
	}

	public void setTarget(Vector2 target) {
		this.target = target;
	}

	public void draw(SpriteBatch batch) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.begin();
		move();
		batch.draw(state.getAnimation().getKeyFrame(elapsedTime, true), bounds.x,
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
					state = new WalkUpRightState();
					return;
				}
				float tmpX = (float) (Math.cos(o2.angleRad(vec)) * vec.len());
				if (tmpX >= offset) {
					bounds.x += offset * Math.cos(alpha);
					bounds.y -= offset * Math.sin(alpha);
				} else {
					bounds.x += tmpX * Math.cos(alpha);
					bounds.y -= tmpX * Math.sin(alpha);
				}
				state = new WallkDownRightState();
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
					state = new WallkDownLeftState();
					return;
				}
				float tmpX = (float) (Math.cos(o2.angleRad(vec)) * vec.len());
				if (Math.abs(tmpX) >= offset) {
					bounds.x -= offset * Math.cos(alpha);
					bounds.y += offset * Math.sin(alpha);
				} else {
					bounds.x -= tmpX * Math.cos(alpha);
					bounds.y += tmpX * Math.sin(alpha);
				}
				state = new WalkUpLeftState();
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
					state = new WalkUpLeftState();
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
				state = new WalkUpRightState();
			}

			if (y < 0 && Math.abs(tan) > Math.tan(alpha)) {
				o1.scl(-1);
				if (Math.abs(vec.angle(o2)) < 1) {
					float tmpX = (float) (vec.len());
					if (tmpX >= offset) {
						bounds.x += offset * Math.cos(alpha);
						bounds.y -= offset * Math.sin(alpha);
					} else {
						bounds.x += tmpX * Math.cos(alpha);
						bounds.y -= tmpX * Math.sin(alpha);
					}
					state = new WallkDownRightState();
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
				state = new WallkDownLeftState();
			}
		} else {
			state = new StandState();
		}
	}
}
