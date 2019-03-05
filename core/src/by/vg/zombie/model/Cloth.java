package by.vg.zombie.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import by.vg.zombie.model.state.ClothState;
import by.vg.zombie.model.state.OutfitType;
import by.vg.zombie.model.state.ZombieState;

public class Cloth extends GameObject {

	private ClothState state;
	private OutfitType type;

	public Cloth(float x, float y) {
		super(x, y, 0, 0);
		state = ClothState.NO;
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
			state = ClothState.NO;
			return;
		}
		switch (zombieState) {
		case STAND:
			state = (type == OutfitType.FIRST ? ClothState.STAND :  ClothState.STAND_D);
			break;
		case WAKE_UP:
			state = ClothState.NO;
			break;
		case WALK_DOWN_LEFT:
			state = (type == OutfitType.FIRST ? ClothState.WALK_DOWN_LEFT :  ClothState.WALK_DOWN_LEFT_D);
			break;
		case WALK_DOWN_RIGHT:
			state = (type == OutfitType.FIRST ? ClothState.WALK_DOWN_RIGHT :  ClothState.WALK_DOWN_RIGHT_D);
			break;
		case WALK_UP_LEFT:
			state = (type == OutfitType.FIRST ? ClothState.WALK_UP_LEFT :  ClothState.WALK_UP_LEFT_D);
			break;
		case WALK_UP_RIGHT:
			state = (type == OutfitType.FIRST ? ClothState.WALK_UP_RIGHT :  ClothState.WALK_UP_RIGHT_D);
			break;
		default:
			break;
		}
	}
}
