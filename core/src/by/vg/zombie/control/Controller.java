package by.vg.zombie.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import by.vg.zombie.model.WhiteWave;
import by.vg.zombie.model.Zombie;
import by.vg.zombie.model.state.OutfitType;
import by.vg.zombie.view.GameScreen;

public class Controller extends Stage implements InputProcessor {
	private TiledMap map;
	private OrthographicCamera camera;
	private Zombie zombie;
	private Float minScale, maxScale;
	private Integer lastPosX, lastPosY;
	private Boolean isMapMoving = false;
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public Float getMinScale() {
		return minScale;
	}

	public void setMinScale(Float minScale) {
		this.minScale = minScale;
	}

	public Float getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(Float maxScale) {
		this.maxScale = maxScale;
	}

	public Zombie getZombie() {
		return zombie;
	}

	public void setZombie(Zombie zombie) {
		this.zombie = zombie;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		switch (character) {
		case '1':
			zombie.changeOutfit(OutfitType.NO);
			break;
		case '2':
			zombie.changeOutfit(OutfitType.FIRST);
			break;
		case '3':
			zombie.changeOutfit(OutfitType.SECOND);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer,
			int button) {
		if (button == 0) {
			lastPosX = screenX;
			lastPosY = -screenY;
			isMapMoving = true;
		}
		if (button == 1 && zombie != null) {
			int x1 = Gdx.input.getX();
			int y1 = Gdx.input.getY();
			Vector3 input = new Vector3(x1, y1, 0);
			camera.unproject(input);
			zombie.setTarget(new Vector2(input.x - 150, input.y - 80));
			GameScreen.addGameObject(new WhiteWave(input.x - 35, input.y - 15));
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == 0) {
			isMapMoving = false;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (isMapMoving) {
		camera.translate(lastPosX - screenX , lastPosY + screenY );
		lastPosX = screenX ;
		lastPosY = -screenY;
		camera.update();
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		float newZoom = camera.zoom + amount * 0.2f;
		camera.zoom = (newZoom > maxScale ? maxScale : (newZoom < minScale ? minScale : newZoom));
		camera.update();
		return false;
	}
}
