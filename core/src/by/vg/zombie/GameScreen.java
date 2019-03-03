package by.vg.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class GameScreen implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
//	private StaticTiledMapTile

	@Override
	public void show() {
		XmlReader xmlReader = new XmlReader();
		FileHandle file = Gdx.files.internal("maps/map.xml");
		XmlReader.Element root = xmlReader.parse(file);
		XmlReader.Element offset = root.getChildByName("offset")
				.getChildByName("Point");

		Integer width = Integer.parseInt(root.getAttribute("tileMapWidth"));
		Integer height = Integer.parseInt(root.getAttribute("tileMapHeight"));
		Integer tileWidth = Integer.parseInt(root.getAttribute("tileWidth"));
		Integer tileHeight = Integer.parseInt(root.getAttribute("tileHeight"));
		Integer tileBorderSize = Integer
				.parseInt(root.getAttribute("tileBorderSize"));
		Integer tilesPerAtlasColumn = Integer
				.parseInt(root.getAttribute("tilesPerAtlasColumn"));
		Integer tilesPerAtlasRow = Integer
				.parseInt(root.getAttribute("tilesPerAtlasRow"));
		Integer tilesAmount = tilesPerAtlasColumn * tilesPerAtlasRow;
		Integer offsetX = Integer.parseInt(offset.getAttribute("x"));
		Integer offsetY = Integer.parseInt(offset.getAttribute("y"));

		map = new TiledMap();
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height,
				tileWidth, tileHeight);

		layer.setName("ground");
//		layer.setOffsetX(-offsetX);
//		layer.setOffsetY(-offsetY);
		map.getLayers().add(layer);

		TiledMapTileSet mapTileSet = new TiledMapTileSet();
		mapTileSet.setName("tile");
		Texture mapTexture = new Texture("maps/" + root.getAttribute("image"));
		TextureRegion textureRegion;
		TiledMapTile tile;
		for (int i = 0; i < tilesAmount;) {
			textureRegion = new TextureRegion(mapTexture,
					(tileWidth + tileBorderSize * 2) * (i % tilesPerAtlasRow),
					(tileHeight + tileBorderSize * 2)
							* (i / tilesPerAtlasColumn),
					tileWidth, tileHeight);
			tile = new StaticTiledMapTile(textureRegion);

			mapTileSet.putTile(++i, tile);
		}

		map.getTileSets().addTileSet(mapTileSet);
		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();

		Array<Element> array = root.getChildByName("items")
				.getChildByName("list").getChildrenByName("Tile");

		Integer index, x, y;
		Boolean flipHorizontal, flipVertical;

		for (Element element : array) {
			index = Integer.parseInt(element.getAttribute("index"));
			x = Integer.parseInt(element.getAttribute("x")) - offsetX;
			y = Integer.parseInt(element.getAttribute("y")) - offsetY;
			flipHorizontal = Boolean
					.valueOf(element.getAttribute("flipHorizontal"));
			flipVertical = Boolean
					.valueOf(element.getAttribute("flipVertical"));

			Cell cell = new Cell();
			cell.setFlipHorizontally(flipHorizontal);
			cell.setFlipVertically(flipVertical);
			cell.setTile(mapTileSet.getTile(index));
			layer.setCell(x / 100, -y / 100, cell);
		}
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
