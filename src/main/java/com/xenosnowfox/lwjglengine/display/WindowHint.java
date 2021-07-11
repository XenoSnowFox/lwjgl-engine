package com.xenosnowfox.lwjglengine.display;

import org.lwjgl.glfw.GLFW;

/**
 * Supported window hints that can be applied to window creation.
 */
public enum WindowHint {

	/**
	 * Flag to indicate if a window is resizable.
	 */
	RESIZABLE(GLFW.GLFW_RESIZABLE),

	/**
	 * Flag to indicate if a window should be visible upon creation.
	 */
	VISIBLE(GLFW.GLFW_VISIBLE),

	/**
	 * Flag to indicate that the cursor should be center on a newly created screen.
	 */
	CENTER_CURSOR(GLFW.GLFW_CENTER_CURSOR);

	/**
	 * GLFW id for the window hint.
	 */
	private final int hintId;

	/**
	 * Instantiates a new instance.
	 *
	 * @param hintId
	 * 		GLFW id.
	 */
	WindowHint(final int hintId) {
		this.hintId = hintId;
	}

	/**
	 * Applies the given value to the window hint.
	 *
	 * @param withValue
	 * 		Hint value.
	 */
	public void applyHintValue(final int withValue) {
		GLFW.glfwWindowHint(this.hintId, withValue);
	}
}
