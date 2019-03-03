package by.vg.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import by.vg.zombie.control.CameraController;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class GameScreen implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	@Override
	public void show() {
		XmlReader xmlReader = new XmlReader();
		FileHandle file = Gdx.files.internal("maps/map.xml");
		XmlReader.Element root = xmlReader.parse(file);
		XmlReader.Element offset = root.getChildByName("offset")
				.getChildByName("Point");

		Float defaultScale = Float.valueOf(root.getAttribute("defaultScale"));
		Float maxScale = Float.valueOf(root.getAttribute("maxScale"));
		Float minScale = Float.valueOf(root.getAttribute("minScale"));
		Integer width = Integer.parseInt(root.getAttribute("tileMapWidth"));
		Integer height = Integer.parseInt(root.getAttribute("tileMapHeight"));
		Integer tileWidth = Integer.parseInt(root.getAttribute("tileWidth"));
		Integer tileHeight = Integer.parseInt(root.getAttribute("tileHeight"));
		Integer tileBorderSize = Integer.parseInt(root.getAttribute("tileBorderSize"));
		Integer tilesPerAtlasColumn = Integer.parseInt(root.getAttribute("tilesPerAtlasColumn"));
		Integer tilesPerAtlasRow = Integer.parseInt(root.getAttribute("tilesPerAtlasRow"));
		Integer tilesAmount = tilesPerAtlasColumn * tilesPerAtlasRow;
		Integer offsetX = Integer.parseInt(offset.getAttribute("x"));
		Integer offsetY = Integer.parseInt(offset.getAttribute("y"));

		map = new TiledMap();
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height + 1000,
				tileWidth, tileHeight);

		layer.setName("ground");
		layer.setOffsetX(-width / 2 - offsetX);
		layer.setOffsetY(-offsetY);
		map.getLayers().add(layer);

		TiledMapTileSet mapTileSet = new TiledMapTileSet();
		mapTileSet.setName("tile");
		Texture mapTexture = new Texture("maps/" + root.getAttribute("image"));
		TextureRegion textureRegion;
		TiledMapTile tile;
		for (int i = 0; i < tilesAmount;) {
			textureRegion = new TextureRegion(mapTexture,
					tileBorderSize +(tileWidth + tileBorderSize * 2) * (i % tilesPerAtlasRow),
					tileBorderSize + (tileHeight + tileBorderSize * 2)
							* (i / tilesPerAtlasColumn),
					tileWidth, tileHeight);
			tile = new StaticTiledMapTile(textureRegion);

			mapTileSet.putTile(++i, tile);
		}

		map.getTileSets().addTileSet(mapTileSet);
		renderer = new OrthogonalTiledMapRenderer(map);

		Array<Element> array = root.getChildByName("items")
				.getChildByName("list").getChildrenByName("Tile");

		Integer index, x, y;
		Boolean flipHorizontal, flipVertical;

		for (Element element : array) {
			index = Integer.parseInt(element.getAttribute("index"));
			x = Integer.parseInt(element.getAttribute("x"));
			y = Integer.parseInt(element.getAttribute("y"));
			flipHorizontal = Boolean
					.valueOf(element.getAttribute("flipHorizontal"));
			flipVertical = Boolean
					.valueOf(element.getAttribute("flipVertical"));

			Cell cell = new Cell();
			cell.setFlipHorizontally(flipHorizontal);
			cell.setFlipVertically(flipVertical);
			cell.setTile(mapTileSet.getTile(index));
			layer.setCell((x + offsetX) / 100, -(y + offsetY) / 100, cell);
		}

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.zoom = defaultScale;
		CameraController cameraController = new CameraController(camera, minScale, maxScale);
		Gdx.input.setInputProcessor(cameraController);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		renderer.setView(camera);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

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
