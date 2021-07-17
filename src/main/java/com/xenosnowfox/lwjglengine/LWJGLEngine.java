package com.xenosnowfox.lwjglengine;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class LWJGLEngine implements Runnable {

	public static LWJGLEngine newEngine() {
		return new LWJGLEngine();
	}

	private static final Consumer<LWJGLEngine> NO_ACTION_CONSUMER = lwjglEngine -> {};

	private static final Logger LOGGER = Logger.getLogger(LWJGLEngine.class.getName());

	private Consumer<LWJGLEngine> initConsumer = NO_ACTION_CONSUMER;

	private Consumer<LWJGLEngine> destroyConsumer = NO_ACTION_CONSUMER;

	private boolean running;

	/**
	 * Hidden constructor.
	 */
	private LWJGLEngine() {
		this.running = false;
	}

	@Override
	public void run() {
		LOGGER.entering(this.getClass().getName(), "run");
		if (this.running) {
			return;
		}
		this.running = true;

		try {
			LOGGER.fine("LWJGLEngine: Initializing.");
			this.initConsumer.accept(this);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			LOGGER.fine("LWJGLEngine: Destroying.");
			this.destroyConsumer.accept(this);
			this.running = false;
		}
	}

	public LWJGLEngine onInitialization(final Consumer<LWJGLEngine> withConsumer) {
		this.initConsumer = Objects.requireNonNull(withConsumer);
		return this;
	}

	public LWJGLEngine onInitialization(final Runnable withRunnable) {
		this.initConsumer = lwjglEngine -> Objects.requireNonNull(withRunnable)
				.run();
		return this;
	}

	public LWJGLEngine onDestroy(final Consumer<LWJGLEngine> withConsumer) {
		this.destroyConsumer = Objects.requireNonNull(withConsumer);
		return this;
	}

	public LWJGLEngine onDestroy(final Runnable withRunnable) {
		this.destroyConsumer = lwjglEngine -> Objects.requireNonNull(withRunnable)
				.run();
		return this;
	}
}
