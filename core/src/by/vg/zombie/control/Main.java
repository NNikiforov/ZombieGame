package by.vg.zombie.control;

import com.badlogic.gdx.Game;

import by.vg.zombie.view.GameScreen;

public class Main extends Game {
		
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
