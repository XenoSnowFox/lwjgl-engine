package com.xenosnowfox.lwjglengine.render.object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ObjectLibraryFactory {

	public ObjectLibrary fromFile(final String withFilename) throws IOException {
		ObjectLibrary objectLibrary;
		try (InputStream inputStream = new FileInputStream(withFilename)) {
			objectLibrary = this.fromInputStream(inputStream);
		}
		return objectLibrary;
	}

	public ObjectLibrary fromInputStream(final InputStream withInputStream) throws IOException {
		return new ObjectLibraryStreamReader(withInputStream).read();
	}
}
