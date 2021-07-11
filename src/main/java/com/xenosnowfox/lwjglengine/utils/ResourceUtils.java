package com.xenosnowfox.lwjglengine.utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * Utility class for working with resource files.
 */
public class ResourceUtils {

	/**
	 * Returns a string containing the contents of the given resource file.
	 *
	 * @param fileName
	 * 		resource file name.
	 * @return string of the resource file content
	 * @throws Exception
	 * 		if the resource cannot be read
	 */
	public static String loadResource(final String fileName) throws Exception {
		String result;
		try (
				InputStream in = ResourceUtils.class.getResourceAsStream(fileName);
				Scanner scanner = new Scanner(Objects.requireNonNull(in), StandardCharsets.UTF_8.name())
		) {
			result = scanner.useDelimiter("\\A")
					.next();
		}
		return result;
	}

	/**
	 * Hidden constructor.
	 */
	private ResourceUtils() {
	}
}
