package com.xenosnowfox.lwjglengine.render.object;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectLibraryStreamReader {

	private final InputStream inputStream;

	private boolean finished = false;

	private Queue<Float> vertex = new ArrayDeque<>();

	private Queue<Float> vertexNormal = new ArrayDeque<>();

	private Queue<Float> vertexTexture = new ArrayDeque<>();

	public ObjectLibraryStreamReader(final InputStream withInputStream) {
		this.inputStream = Objects.requireNonNull(withInputStream);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "{" + System.lineSeparator()
				+ "\tvertex=" + vertex.toString() + System.lineSeparator()
				+ "\tvertexNormal=" + vertexNormal.toString() + System.lineSeparator()
				+ "\tvertexTexture=" + vertexTexture.toString() + System.lineSeparator()
				+ "}";
	}

	public ObjectLibrary read() throws IOException {
		String line = this.readLine();
		while (line != null) {
			this.parseLine(line);
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

		switch (parts[0].toLowerCase(Locale.ROOT)) {
			case "v":
				this.parseVertex(parts[1].split(" "));
				break;

			case "vn":
				this.parseVertexNormal(parts[1].split(" "));
				break;

			case "vt":
				this.parseVertexTexture(parts[1].split(" "));
				break;

			default: break;
		}
	}

	private void parseVertex(String... values) throws IOException {
		var parts = Stream.of(values)
				.filter(s -> !s.isBlank())
				.collect(Collectors.toList());

		if (parts.size() == 3) {
			parts.add("1.0");
		}

		if (parts.size() != 4) {
			throw new IOException("Vertex must contain either 3 or 4 parts.");
		}

		parts.stream()
				.map(Float::parseFloat)
				.forEach(this.vertex::add);
	}

	private void parseVertexNormal(String... values) throws IOException {
		var parts = Stream.of(values)
				.filter(s -> !s.isBlank())
				.collect(Collectors.toList());

		if (parts.size() != 3) {
			throw new IOException("Vertex Normal must contain exactly 3 parts.");
		}

		parts.stream()
				.map(Float::parseFloat)
				.forEach(this.vertexNormal::add);
	}

	private void parseVertexTexture(String... values) throws IOException {
		var parts = Stream.of(values)
				.filter(s -> !s.isBlank())
				.collect(Collectors.toList());

		while (parts.size() < 3) {
			parts.add("0.0");
		}

		if (parts.size() != 3) {
			throw new IOException("Vertex Texture must contain exactly 3 parts.");
		}

		parts.stream()
				.map(Float::parseFloat)
				.forEach(this.vertexTexture::add);
	}
}
