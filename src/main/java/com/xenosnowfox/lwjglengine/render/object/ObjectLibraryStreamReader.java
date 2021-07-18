package com.xenosnowfox.lwjglengine.render.object;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ObjectLibraryStreamReader {

	private final InputStream inputStream;

	private boolean finished = false;

	public ObjectLibraryStreamReader(final InputStream withInputStream) {
		this.inputStream = Objects.requireNonNull(withInputStream);
	}

	public ObjectLibrary read() throws IOException {
		String line;
		do {
			line = this.readLine();
			System.out.println(line);
		} while (line != null);
		return null;
	}

	private String readLine() throws IOException {
		if (this.finished) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		int newline = '\n';
		while (true) {
			int b = this.inputStream.read();
			if (b == 0) {
				this.finished = true;
				break;
			}

			if (b == newline) {
				break;
			}

			sb.append((char) b);
		}
		return sb.toString();
	}

}
