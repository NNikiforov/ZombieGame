package by.vg.zombie.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import by.vg.zombie.model.state.OutfitType;
import by.vg.zombie.model.state.ZombieState;

public class Zombie extends GameObject {

	private ZombieState state;
	private Hat hat;
	private Cloth cloth;
	private float speed = 50f;
	private Vector2 target = new Vector2(bounds.x, bounds.y);

	public Zombie(ZombieState initialState, float x, float y, float width,
			float height) {
		super(x, y, width, height);
		state = initialState;
		hat = new Hat(x, y);
		cloth = new Cloth(x, y);
	}

	public ZombieState getState() {
		return state;
	}

	public void setState(ZombieState state) {
		this.state = state;
	}

	public Vector2 getTarget() {
		return target;
	}

	public void setTarget(Vector2 target) {
		this.target = target;
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		hat.setPosition(x, y);
		cloth.setPosition(x, y);
	}

	public void draw(SpriteBatch batch) {
		update();
		state.draw(batch, bounds.x, bounds.y);
		hat.draw(batch);
		cloth.draw(batch);
	}

	public void changeState(ZombieState state) {
		this.state = state;
		hat.changeState(state);
		cloth.changeState(state);
	}

	public OutfitType getOutfit() {
		return cloth.getType();
	}
	public void changeOutfit(OutfitType type) {
		hat.setType(type);
		cloth.setType(type);
	}

	public void update() {
		Vector2 vec = new Vector2(target.x - bounds.x, target.y - bounds.y);

		if (vec.len() > 10) {
			float deltaT = Gdx.graphics.getDeltaTime();
			float offset = (float) (deltaT * speed);
			float x = target.x - bounds.x;
			float y = target.y - bounds.y;
			float tan = (x != 0 ? y / x : 1);
			double alpha = Math.PI / 6;
			Vector2 OX = new Vector2(1, (float) Math.tan(alpha));
			Vector2 OY = new Vector2(1, (float) -Math.tan(alpha));
			

			if (x > 0 && Math.abs(tan) <= Math.tan(alpha)) {
				if (Math.abs(vec.angle(OX)) < 1) {
					float tmpY = (float) (vec.len());
					if (tmpY >= offset) {
						bounds.x += offset * Math.cos(alpha);
						bounds.y += offset * Math.sin(alpha);
					} else {
						bounds.x += tmpY * Math.cos(alpha);
						bounds.y += tmpY * Math.sin(alpha);
					}
					changeState(ZombieState.WALK_UP_RIGHT);
					setPosition(bounds.x, bounds.y);
					return;
				}
				float tmpX = (float) (Math.cos(OY.angleRad(vec)) * vec.len());
				if (tmpX >= offset) {
					bounds.x += offset * Math.cos(alpha);
					bounds.y -= offset * Math.sin(alpha);
				} else {
					bounds.x += tmpX * Math.cos(alpha);
					bounds.y -= tmpX * Math.sin(alpha);
				}
				changeState(ZombieState.WALK_DOWN_RIGHT);
				setPosition(bounds.x, bounds.y);
				return;
			}

			if (x < 0 && Math.abs(tan) < Math.tan(alpha)) {
				if (Math.abs(vec.angle(OX)) < 1) {
					float tmpY = (float) (vec.len());
					if (tmpY >= offset) {
						bounds.x -= offset * Math.cos(alpha);
						bounds.y -= offset * Math.sin(alpha);
					} else {
						bounds.x -= tmpY * Math.cos(alpha);
						bounds.y -= tmpY * Math.sin(alpha);
					}
					changeState(ZombieState.WALK_DOWN_LEFT);
					setPosition(bounds.x, bounds.y);
					return;
				}
				float tmpX = (float) (Math.cos(OY.angleRad(vec)) * vec.len());
				if (Math.abs(tmpX) >= offset) {
					bounds.x -= offset * Math.cos(alpha);
					bounds.y += offset * Math.sin(alpha);
				} else {
					bounds.x -= tmpX * Math.cos(alpha);
					bounds.y += tmpX * Math.sin(alpha);
				}
				changeState(ZombieState.WALK_UP_LEFT);
				setPosition(bounds.x, bounds.y);
				return;
			}

			if (y > 0 && Math.abs(tan) > Math.tan(alpha)) {
				OY.scl(-1);
				if (Math.abs(vec.angle(OY)) < 1) {
					float tmpX = (float) (vec.len());
					if (tmpX >= offset) {
						bounds.x -= offset * Math.cos(alpha);
						bounds.y += offset * Math.sin(alpha);
					} else {
						bounds.x -= tmpX * Math.cos(alpha);
						bounds.y += tmpX * Math.sin(alpha);
					}
					changeState(ZombieState.WALK_UP_LEFT);
					setPosition(bounds.x, bounds.y);
					return;
				}
				float a = OX.angleRad(vec);
				float tmpY = (float) (Math.sin(4 * alpha - a) * vec.len()
						/ Math.sin(2 * alpha));
				if (Math.abs(tmpY) >= offset) {
					bounds.x += offset * Math.cos(alpha);
					bounds.y += offset * Math.sin(alpha);
				} else {
					bounds.x += tmpY * Math.cos(alpha);
					bounds.y += tmpY * Math.sin(alpha);
				}
				changeState(ZombieState.WALK_UP_RIGHT);
				setPosition(bounds.x, bounds.y);
				return;
			}

			if (y < 0 && Math.abs(tan) > Math.tan(alpha)) {
				OX.scl(-1);
				if (Math.abs(vec.angle(OY)) < 1) {
					float tmpX = (float) (vec.len());
					if (tmpX >= offset) {
						bounds.x += offset * Math.cos(alpha);
						bounds.y -= offset * Math.sin(alpha);
					} else {
						bounds.x += tmpX * Math.cos(alpha);
						bounds.y -= tmpX * Math.sin(alpha);
					}
					changeState(ZombieState.WALK_DOWN_RIGHT);
					setPosition(bounds.x, bounds.y);
					return;
				}
				float a = OX.angleRad(vec);
				float tmpY = (float) (Math.sin(4 * alpha - a) * vec.len()
						/ Math.sin(2 * alpha));
				if (Math.abs(tmpY) >= offset) {
					bounds.x -= offset * Math.cos(alpha);
					bounds.y -= offset * Math.sin(alpha);
				} else {
					bounds.x -= tmpY * Math.cos(alpha);
					bounds.y -= tmpY * Math.sin(alpha);
				}
				changeState(ZombieState.WALK_DOWN_LEFT);
				setPosition(bounds.x, bounds.y);
				return;
			}
		} else {
			if (state == ZombieState.WAKE_UP) {
				return;
			} else {
				changeState(ZombieState.STAND);
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}
}
