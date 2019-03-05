package by.vg.zombie.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;

public abstract class XMLParser {
	protected XmlReader.Element root;
	private String fileName;

	public XMLParser(String fileName) {
		this.fileName = fileName;
		XmlReader xmlReader = new XmlReader();
		FileHandle file = Gdx.files.internal(fileName);
		root = xmlReader.parse(file);
	}
}
