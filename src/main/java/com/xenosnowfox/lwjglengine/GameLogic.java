package com.xenosnowfox.lwjglengine;

/**
 * Represents an instance that is capable of working with the event/game loop.
 *
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
	 * Called exactly once during the input phase of the game loop.
	 */
	default void input() {
	}

	/**
	 * Called multiple times during the update phase.
	 *
	 * @param interval time since the previous update call.
	 */
	void update(float interval);

	/**
	 * Called exactly once at the start of the render phase.
	 */
	default void preRender() {
	}

	/**
	 * Called exactly once during the render phase.
	 */
	void render();

	/**
	 * Called exactly once at the end of the render phase.
	 */
	default void postRender() {
	}

	/**
	 * Call when the instance is being destroyed, allowing it the chance to clean up any used resources.
	 */
	void destroy();
}
