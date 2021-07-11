package com.xenosnowfox.lwjglengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameLoop implements Runnable {

	public static final int TARGET_FPS = 75;

	public static final int TARGET_UPS = 30;

	private boolean running = false;

	private final Timer timer;

	private final List<Viewport> viewports;

	public GameLoop() {
		this.timer = new Timer();
		this.viewports = new ArrayList<>();
	}

	public boolean addViewport(final Viewport withViewport) {
		return this.viewports.add(Objects.requireNonNull(withViewport));
	}

	public boolean removeViewport(final Viewport withViewport) {
		return this.viewports.remove(withViewport);
	}

	@Override
	public void run() {
		if (this.running) {
			return;
		}
		this.running = true;

		try {
			System.out.println("GAME LOOP: init");
			this.initialize();

			System.out.println("GAME LOOP: run");
			float elapsedTime;
			float accumulator = 0f;
			float interval = 1f / TARGET_UPS;

			while (this.isRunning()) {
				elapsedTime = timer.getElapsedTime();
				accumulator += elapsedTime;

				this.input();

				while (accumulator >= interval) {
					this.update(interval);
					accumulator -= interval;
				}

				this.preRender();
				this.render();
				this.postRender();
			}
		} catch (Exception ex) {
			System.out.println("GAME LOOP: exception");
			ex.printStackTrace();
		} finally {
			System.out.println("GAME LOOP: destroy");
			this.destroy();
		}
	}

	public boolean isRunning() {
		return this.running;
	}

	public void stop() {
		this.running = false;
	}

	private void sync() {
		float loopSlot = 1f / TARGET_FPS;
		double endTime = timer.getLastLoopTime() + loopSlot;
		long pauseDuration = (long) (endTime - timer.getTime());
		if (pauseDuration > 0) {
			try {
				Thread.sleep(pauseDuration);
			} catch (InterruptedException ignored) {
			}
		}
	}

	private void initialize() throws Exception {
		for (Viewport viewport : this.viewports) {
			viewport.initialize();
		}
	}

	private void input() {
		for (Viewport viewport : this.viewports) {
			viewport.input();
		}
	}

	private void update(final float interval) {
		for (Viewport viewport : this.viewports) {
			viewport.update(interval);
		}
	}

	private void preRender() {
		for (Viewport viewport : this.viewports) {
			viewport.preRender();
		}
	}

	private void render() {
		for (Viewport viewport : this.viewports) {
			viewport.render();
		}
	}

	private void postRender() {
		for (Viewport viewport : this.viewports) {
			viewport.postRender();
		}
	}

	private void destroy() {
		for (Viewport viewport : this.viewports) {
			viewport.destroy();
		}
	}
}
