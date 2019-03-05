package by.vg.zombie.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import by.vg.zombie.control.Controller;
import by.vg.zombie.model.GameObject;
import by.vg.zombie.model.Zombie;
import by.vg.zombie.utils.MapParser;
import by.vg.zombie.utils.ZombieParser;


public class GameScreen implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private static List<GameObject> items = new ArrayList<GameObject>();

	
	public static void removeGameObject(GameObject object) {
		items.remove(object);
	}

	public static void addGameObject(GameObject object) {
		items.add(object);
	}

	@Override
	public void show() {
		MapParser mParser = new MapParser("maps/map.xml");

		map = mParser.parseToTiledMap();
		renderer = new OrthogonalTiledMapRenderer(map);

		Controller controller = new Controller();
		camera = mParser.parseToCamera(controller);
		Gdx.input.setInputProcessor(controller);

		Zombie zombie = new ZombieParser("zombie/anim_woodcutter_stand/anim_woodcutter_stand.xml").parseToZombie();
		controller.setZombie(zombie);
		
		items.add(zombie);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.setView(camera);
		renderer.render();
		
		List<GameObject> itemsToRemove = new ArrayList<GameObject>();
		
		for (GameObject gameObject : items) {
			if (!gameObject.isActive()) {
				itemsToRemove.add(gameObject);
			}
		}
		
		for (GameObject gameObject : itemsToRemove) {
			items.remove(gameObject);
		}
		
		SpriteBatch batch = (SpriteBatch) renderer.getBatch();
		
		for (GameObject gameObject : items) {
			gameObject.draw(batch);
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
	}
}
