package by.vg.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import by.vg.zombie.control.Controller;
import by.vg.zombie.model.Zombie;
import by.vg.zombie.utils.MapParser;
import by.vg.zombie.utils.ZombieParser;


public class GameScreen implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Zombie zombie;
	

	@Override
	public void show() {
		MapParser mParser = new MapParser("maps/map.xml");

		map = mParser.parseToTiledMap();
		renderer = new OrthogonalTiledMapRenderer(map);

		Controller controller = new Controller();
		camera = mParser.parseToCamera(controller);
		Gdx.input.setInputProcessor(controller);

		zombie = new ZombieParser("zombie/anim_woodcutter_stand/anim_woodcutter_stand.xml").parseToZombie();
		
		ZombieParser zParser = new ZombieParser("zombie/anim_woodcutter_walk_down/anim_woodcutter_walk_down.xml");
		Animation<TextureRegion> animation = zParser.parseToAnimation();
		zombie.setWalking_down_left(animation);
		
		animation = zParser.parseToFlippedAnimation();
		zombie.setWalking_down_right(animation);
		
		zParser = new ZombieParser("zombie/anim_woodcutter_walk_up/anim_woodcutter_walk_up.xml");
		animation = zParser.parseToAnimation();
		zombie.setWalking_up_left(animation);
	
		animation = zParser.parseToFlippedAnimation();
		zombie.setWalking_up_right(animation);
		
		animation = new ZombieParser("zombie/anim_woodcutter_wakeup/anim_woodcutter_wakeup.xml").parseToAnimation();
		animation.setFrameDuration(1/15f);
		zombie.setWake_up(animation);
		
		controller.setZombie(zombie);
		
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		renderer.setView(camera);
		
		renderer.render();
		SpriteBatch batch = (SpriteBatch) renderer.getBatch();
		zombie.draw(batch);
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
