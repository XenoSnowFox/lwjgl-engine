package com.xenosnowfox.lwjglengine.display;

import org.lwjgl.glfw.GLFW;

/**
 * Represents a factory capable of producing instances of supported monitors.
 */
public class MonitorFactory {

	/**
	 * Singleton instance of the monitor factory.
	 */
	private static final MonitorFactory SINGLETON = new MonitorFactory();

	/**
	 * Returns the singleton instance of the monitor factory.
	 *
	 * @return Monitor factory.
	 */
	public static MonitorFactory getSingleton() {
		return MonitorFactory.SINGLETON;
	}

	/**
	 * Reference to the primary display monitor.
	 */
	private Monitor primaryMonitor;

	/**
	 * Hidden constructor.
	 */
	public MonitorFactory() {
	}

	/**
	 * Returns an instance on the user's primary display monitor.
	 *
	 * @return Primary display monitor.
	 */
	public Monitor getPrimaryMonitor() {
		if (this.primaryMonitor == null) {
			this.primaryMonitor = new Monitor(GLFW.glfwGetPrimaryMonitor());
		}

		return this.primaryMonitor;
	}
}
