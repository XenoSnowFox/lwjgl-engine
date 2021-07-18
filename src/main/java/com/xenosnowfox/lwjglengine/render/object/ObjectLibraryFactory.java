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
		while (withInputStream.available() > 0) {
			System.out.println(this.readLine(withInputStream));
		}
		return null;
	}

	private String readLine(final InputStream withInputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		int b = withInputStream.read();
		int newline = '\n';
		while (b >= 0 && b != newline) {
			sb.append((char) b);
			b = withInputStream.read();
		}
		return sb.toString();
	}
}
