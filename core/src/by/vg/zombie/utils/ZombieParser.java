package by.vg.zombie.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

import by.vg.zombie.model.Zombie;

public class ZombieParser extends XMLParser {

	public ZombieParser(String fileName) {
		super(fileName);
	}

	public Zombie parseToZombie() {
		Texture texture = new Texture(root.getAttribute("animationTexture"));
		Array<Element> elements = root.getChildByName("frames").getChildByName("list").getChildrenByName("Frame");
		Array<TextureRegion> keyFrames = new Array<TextureRegion>();
		
		Integer height = 100, ticks = 0, width = 50;
		for (Element element : elements) {
			Integer flippedXOffset = Integer.valueOf(element.getAttribute("flippedXOffset"));
			Integer flippedYOffset = Integer.valueOf(element.getAttribute("flippedYOffset"));
			height = Integer.valueOf(element.getAttribute("height"));
			ticks += Integer.valueOf(element.getAttribute("ticks"));
			width = Integer.valueOf(element.getAttribute("width"));
			Integer x = Integer.valueOf(element.getAttribute("x"));
			Integer xOffset = Integer.valueOf(element.getAttribute("xOffset"));
			Integer y = Integer.valueOf(element.getAttribute("y"));
			Integer yOffset = Integer.valueOf(element.getAttribute("yOffset"));

			TextureRegion region = new TextureRegion(texture, x, y, width, height);
			keyFrames.add(region);
		}
		Animation<TextureRegion> animation = new Animation<TextureRegion>(1f/ticks, keyFrames);
		Zombie zombie = new Zombie(animation, 0, 0, width, height);
		return zombie;
	}
	
	public Animation<TextureRegion> parseToAnimation() {
		Texture texture = new Texture(root.getAttribute("animationTexture"));
		Array<Element> elements = root.getChildByName("frames").getChildByName("list").getChildrenByName("Frame");
		Array<TextureRegion> keyFrames = new Array<TextureRegion>();
		
		Integer height = 100, tick = 1, width = 50, count = 0;
		for (Element element : elements) {
			height = Integer.valueOf(element.getAttribute("height"));
			tick = Integer.valueOf(element.getAttribute("ticks"));
			width = Integer.valueOf(element.getAttribute("width"));
			Integer x = Integer.valueOf(element.getAttribute("x"));
			Integer y = Integer.valueOf(element.getAttribute("y"));

			TextureRegion region = new TextureRegion(texture, x, y, width, height);
			keyFrames.add(region);
			count++;
		}
		Animation<TextureRegion> animation = new Animation<TextureRegion>((float)tick/count, keyFrames);
		
		return animation;
	}
	
	public Animation<TextureRegion> parseToFlippedAnimation() {
		Texture texture = new Texture(root.getAttribute("animationTexture"));
		Array<Element> elements = root.getChildByName("frames").getChildByName("list").getChildrenByName("Frame");
		Array<TextureRegion> keyFrames = new Array<TextureRegion>();
		
		Integer height = 100, tick = 1, width = 50, count = 0;
		for (Element element : elements) {
			height = Integer.valueOf(element.getAttribute("height"));
			tick = Integer.valueOf(element.getAttribute("ticks"));
			width = Integer.valueOf(element.getAttribute("width"));
			Integer x = Integer.valueOf(element.getAttribute("x"));
			Integer y = Integer.valueOf(element.getAttribute("y"));

			TextureRegion region = new TextureRegion(texture, x, y, width, height);
			region.flip(true, false);
			keyFrames.add(region);
			count++;
		}
		Animation<TextureRegion> animation = new Animation<TextureRegion>((float)tick/count, keyFrames);
		
		return animation;
	}
}
