package by.vg.zombie.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import by.vg.zombie.model.state.HatState;
import by.vg.zombie.model.state.OutfitType;
import by.vg.zombie.model.state.ZombieState;

public class Hat extends GameObject {

	private HatState state;
	private OutfitType type;

	public Hat(float x, float y) {
		super(x, y, 0, 0);
		state = HatState.NO;
		type = OutfitType.NO;
	}

	public OutfitType getType() {
		return type;
	}

	public void setType(OutfitType type) {
		this.type = type;
	}

	@Override
	public void draw(SpriteBatch batch) {
		state.draw(batch, bounds.x, bounds.y);
	}

	@Override
	public boolean isActive() {
		return true;
	}

	public void changeState(ZombieState zombieState) {
		if (type == OutfitType.NO) {
			state = HatState.NO;
			return;
		}
		switch (zombieState) {
		case STAND:
			state = (type == OutfitType.FIRST ? HatState.STAND :  HatState.STAND_D);
			break;
		case WAKE_UP:
			state = HatState.NO;
			break;
		case WALK_DOWN_LEFT:
			state = (type == OutfitType.FIRST ? HatState.WALK_DOWN_LEFT :  HatState.WALK_DOWN_LEFT_D);
			break;
		case WALK_DOWN_RIGHT:
			state = (type == OutfitType.FIRST ? HatState.WALK_DOWN_RIGHT :  HatState.WALK_DOWN_RIGHT_D);
			break;
		case WALK_UP_LEFT:
			state = (type == OutfitType.FIRST ? HatState.WALK_UP_LEFT :  HatState.WALK_UP_LEFT_D);
			break;
		case WALK_UP_RIGHT:
			state = (type == OutfitType.FIRST ? HatState.WALK_UP_RIGHT :  HatState.WALK_UP_RIGHT_D);
			break;
		default:
			break;
		}
	}
}
