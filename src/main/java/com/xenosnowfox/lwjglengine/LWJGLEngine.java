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

	private Consumer<LWJGLEngine> inputConsumer = NO_ACTION_CONSUMER;

	private Consumer<LWJGLEngine> updateConsumer = NO_ACTION_CONSUMER;

	private Consumer<LWJGLEngine> renderConsumer = NO_ACTION_CONSUMER;

	private Consumer<LWJGLEngine> destroyConsumer = NO_ACTION_CONSUMER;

	private boolean running;

	private boolean exiting;

	/**
	 * Hidden constructor.
	 */
	private LWJGLEngine() {
		this.running = false;
		this.exiting = false;
	}

	@Override
	public void run() {
		LOGGER.entering(this.getClass()
				.getName(), "run");
		if (this.running) {
			return;
		}
		this.running = true;
		this.exiting = false;

		try {
			LOGGER.fine("LWJGLEngine: Initializing.");
			this.initConsumer.accept(this);

			LOGGER.fine("LWJGLEngine: Starting Game Loop.");
			while (!this.exiting) {
				this.inputConsumer.accept(this);
				this.updateConsumer.accept(this);
				this.renderConsumer.accept(this);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			LOGGER.fine("LWJGLEngine: Destroying.");
			this.destroyConsumer.accept(this);
			this.running = false;
			this.exiting = false;
		}
	}

	public LWJGLEngine exit() {
		if (this.running) {
			this.exiting = true;
		}
		return this;
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

	public LWJGLEngine onInput(final Consumer<LWJGLEngine> withConsumer) {
		this.inputConsumer = Objects.requireNonNull(withConsumer);
		return this;
	}

	public LWJGLEngine onInput(final Runnable withRunnable) {
		this.inputConsumer = lwjglEngine -> Objects.requireNonNull(withRunnable)
				.run();
		return this;
	}

	public LWJGLEngine onUpdate(final Consumer<LWJGLEngine> withConsumer) {
		this.updateConsumer = Objects.requireNonNull(withConsumer);
		return this;
	}

	public LWJGLEngine onUpdate(final Runnable withRunnable) {
		this.updateConsumer = lwjglEngine -> Objects.requireNonNull(withRunnable)
				.run();
		return this;
	}

	public LWJGLEngine onRender(final Consumer<LWJGLEngine> withConsumer) {
		this.renderConsumer = Objects.requireNonNull(withConsumer);
		return this;
	}

	public LWJGLEngine onRender(final Runnable withRunnable) {
		this.renderConsumer = lwjglEngine -> Objects.requireNonNull(withRunnable)
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
