package com.xenosnowfox.lwjglengine;

/**
 * Represents an instance that is capable of working with the event/game loop.
 */
public interface GameLogic {

	/**
	 * Initializes the game instance.
	 *
	 * @throws Exception
	 * 		initialization exception.
	 */
	void initialize() throws Exception;

	/**
	 * Called during the input phase.
	 */
	default void input() {
	}

	/**
	 * Called during the update phase.
	 *
	 * @param interval time since the previous update call.
	 */
	void update(float interval);

	/**
	 * Called at the start of the render phase.
	 */
	default void preRender() {
	}

	/**
	 * Called during the render phase.
	 */
	void render();

	/**
	 * Called at the end of the render phase.
	 */
	default void postRender() {
	}

	/**
	 * Call when the instance is being destroyed, allowing it the chance to clean up any used resources.
	 */
	void destroy();
}
