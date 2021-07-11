package com.xenosnowfox.lwjglengine.display;

import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Helper class for building a new window.
 */
public class WindowBuilder {

	/**
	 * Requested width to assign to the newly created window.
	 */
	private int width;

	/**
	 * Requested height to assign to the newly created window.
	 */
	private int height;

	/**
	 * Text to display in the title bar of the new;y created window.
	 */
	private String title;

	/**
	 * Monitor to assign the window to, making it fullscreen.
	 */
	private Monitor monitor;

	/**
	 * Window hints to apply to the newly created window.
	 */
	private final Map<WindowHint, Integer> windowHints = new HashMap<>();

	/**
	 * Sets a widow hint.
	 *
	 * @param withHint
	 * 		Window hint to apply.
	 * @param withBoolean
	 * 		Value to apply to a hint.
	 * @return this builder instance to allow for method chaining.
	 */
	private WindowBuilder windowHint(final WindowHint withHint, final boolean withBoolean) {
		return this.windowHint(withHint, withBoolean ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
	}

	/**
	 * Sets a widow hint.
	 *
	 * @param withHint
	 * 		Window hint to apply.
	 * @param withValue
	 * 		Value to apply to a hint.
	 * @return this builder instance to allow for method chaining.
	 */
	private WindowBuilder windowHint(final WindowHint withHint, final int withValue) {
		this.windowHints.put(withHint, withValue);
		return this;
	}

	/**
	 * Defines the monitor to display the window on.
	 *
	 * @param withMonitor
	 * 		Monitor.
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder monitor(final Monitor withMonitor) {
		this.monitor = withMonitor;
		return this;
	}

	/**
	 * Indicates that the newly created window should be assigned to the user's primary display window.
	 *
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder primaryMonitor() {
		return this.monitor(Monitor.factory()
				.getPrimaryMonitor());
	}

	/**
	 * Defines the width to assign to the new window.
	 *
	 * @param withWidth
	 * 		Window width
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder width(final int withWidth) {
		if (withWidth <= 0) {
			throw new IllegalArgumentException("Window width must be greater then zero.");
		}
		this.width = withWidth;
		return this;
	}

	/**
	 * Defines the height to assign to the new window.
	 *
	 * @param withHeight
	 * 		Window height
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder height(final int withHeight) {
		if (withHeight <= 0) {
			throw new IllegalArgumentException("Window height must be greater then zero.");
		}
		this.height = withHeight;
		return this;
	}

	/**
	 * Defines the title to display in the title bar of the newly created window.
	 *
	 * @param withTitle
	 * 		Window title
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder title(final String withTitle) {
		this.title = Objects.requireNonNull(withTitle);
		return this;
	}

	/**
	 * Indicates that the newly created window should be resizable.
	 *
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder resizable() {
		return this.resizable(true);
	}

	/**
	 * Defines whether the newly created window should be resizable.
	 *
	 * @param isResizable
	 *        {@code true} to indicate that the newly created window should be resizeable, or {@code false otherwise}.
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder resizable(final boolean isResizable) {
		return this.windowHint(WindowHint.RESIZABLE, isResizable);
	}

	/**
	 * Indicates that the newly created window should be visible upon creation.
	 *
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder visible() {
		return this.visible(true);
	}

	/**
	 * Defines whether the newly created window should be visible upon creation.
	 *
	 * @param isVisible
	 *        {@code true} to indicate that the newly created window should be visible upon creation, or {@code false}
	 * 		otherwise.
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder visible(final boolean isVisible) {
		return this.windowHint(WindowHint.VISIBLE, isVisible);
	}

	/**
	 * Indicates that the cursor should be centered on the newly created window.
	 *
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder centerCursor() {
		return this.centerCursor(true);
	}

	/**
	 * Defines whether the cursor should be centers on the newly created window.
	 *
	 * @param isCursorCentered
	 *        {@code true} to indicate that the cursor should be centered on the newly created window, or {@code false}
	 * 		otherwise.
	 * @return this builder instance to allow for method chaining.
	 */
	public WindowBuilder centerCursor(final boolean isCursorCentered) {
		return this.windowHint(WindowHint.CENTER_CURSOR, isCursorCentered);
	}

	/**
	 * Applies all the requested settings and attempted to create a new window instance.
	 *
	 * @return newly created window.
	 */
	public Window build() {
		// apply all window hints
		GLFW.glfwDefaultWindowHints();
		this.windowHints.forEach(WindowHint::applyHintValue);

		return new Window(this.title, this.width, this.height, true);
	}
}
