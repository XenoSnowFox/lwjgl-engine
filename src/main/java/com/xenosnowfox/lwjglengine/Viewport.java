package com.xenosnowfox.lwjglengine;

import com.xenosnowfox.lwjglengine.display.Window;
import org.joml.Vector2i;
import org.lwjgl.opengl.GL46;

import java.util.Objects;

/**
 * Represents a viewport displayed on a window.
 */
public class Viewport implements GameLogic {

	/**
	 * Window the viewport is attached to.
	 */
	private Window window;

	/**
	 * Camera.
	 */
	private Object camera;

	/**
	 * Game logic to execute
	 */
	private GameLogic gameLogic;

	/**
	 * Coordinate of the top-left point of the viewport within the window.
	 */
	private final Vector2i position;

	/**
	 * Width and height of the viewport within the window.
	 */
	private final Vector2i size;

	/**
	 * Instantiates a new instance.
	 *
	 * @param withWindow Window to assign to this viewport.
	 * @param withCamera Camera to use with this Viewport.
	 * @param withGameLogic Game logic.
	 */
	public Viewport(final Window withWindow, final Object withCamera, final GameLogic withGameLogic) {
		this.window = Objects.requireNonNull(withWindow);
		this.camera = Objects.requireNonNull(withCamera);
		this.gameLogic = Objects.requireNonNull(withGameLogic);
		this.position = new Vector2i();
		this.size = new Vector2i(this.window.getWidth(), this.window.getHeight());
	}

	@Override
	public void initialize() throws Exception {
		this.gameLogic.initialize();
	}

	@Override
	public void input() {
		this.gameLogic.input();
	}

	@Override
	public void update(final float interval) {
		this.gameLogic.update(interval);
	}

	@Override
	public void preRender() {
		this.gameLogic.preRender();
	}

	@Override
	public void render() {
		// focus on the window
		this.window.setAsCurrentRenderingContext();

		// set the viewport
		GL46.glViewport(this.position.x, this.position.y, this.size.x, this.size.y);

		this.gameLogic.render();
	}

	@Override
	public void postRender() {
		this.gameLogic.postRender();
	}

	@Override
	public void destroy() {
		this.gameLogic.destroy();
	}
}
