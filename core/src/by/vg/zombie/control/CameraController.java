package by.vg.zombie.control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController implements InputProcessor {

	private OrthographicCamera camera;
	private Float minScale, maxScale;
	private Integer lastPosX, lastPosY;
	
	public CameraController(OrthographicCamera camera, Float minScale,
			Float maxScale) {
		this.camera = camera;
		this.minScale = minScale;
		this.maxScale = maxScale;
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
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer,
			int button) {
		lastPosX = screenX;
		lastPosY = -screenY;
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.translate(lastPosX - screenX , lastPosY + screenY );
		lastPosX = screenX ;
		lastPosY = -screenY;
		camera.update();
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
