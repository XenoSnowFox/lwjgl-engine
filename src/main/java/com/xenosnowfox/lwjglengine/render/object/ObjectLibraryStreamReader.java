package com.xenosnowfox.lwjglengine.render.object;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectLibraryStreamReader {

	private final InputStream inputStream;

	private boolean finished = false;

	private Queue<Float> vertex = new ArrayDeque<>();

	public ObjectLibraryStreamReader(final InputStream withInputStream) {
		this.inputStream = Objects.requireNonNull(withInputStream);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "{" + System.lineSeparator()
				+ "\tvertex=" + vertex.toString() + System.lineSeparator()
				+ "}";
	}

	public ObjectLibrary read() throws IOException {
		String line = this.readLine();
		while (line != null) {
			System.out.println(line);
			line = this.readLine();
		}
		System.out.println(this.toString());
		return null;
	}

	private String readLine() throws IOException {
		if (this.finished) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		int newline = '\n';
		int b = this.inputStream.read();
		while (b >= 0 && b != newline) {
			sb.append((char) b);
			b = this.inputStream.read();
		}
		this.finished = b < 0;
		return sb.toString().trim();
	}


	private void parseLine(String withLine) throws IOException  {
		String[] parts = withLine.trim()
				.split(" ", 2);

		if (parts[0].equalsIgnoreCase("v")) {
			this.parseVertex(parts[1].split(" "));
		}
	}

	private void parseVertex(String... values) throws IOException {
		var parts = Stream.of(values)
				.filter(s -> !s.isBlank())
				.collect(Collectors.toList());

		if (parts.size() != 3) {
			throw new IOException("Vertex must contain exactly 3 parts.");
		}
	}
}
