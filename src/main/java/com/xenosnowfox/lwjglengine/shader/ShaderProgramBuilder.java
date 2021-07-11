package com.xenosnowfox.lwjglengine.shader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Builder capable of producing a Shader Program.
 */
public class ShaderProgramBuilder {

	/**
	 * Collection of uniform names to register with the shader program.
	 */
	private final Set<String> uniforms = new HashSet<>();

	/**
	 * Collection of Shaders to link to the shader program.
	 */
	private final List<Shader> shaders = new ArrayList<>();

	/**
	 * Clears all the shaders and uniforms already set on the builder.
	 *
	 * @return this builder instance to allow method chaining.
	 */
	public ShaderProgramBuilder clear() {
		return this.clearUniforms()
				.clearShaders();
	}

	/**
	 * Clears all the previous defined uniforms from the builder.
	 *
	 * @return this builder instance to allow method chaining.
	 */
	public ShaderProgramBuilder clearUniforms() {
		this.uniforms.clear();
		return this;
	}

	/**
	 * Clears all the previous defined shaders from the builder.
	 *
	 * @return this builder instance to allow method chaining.
	 */
	public ShaderProgramBuilder clearShaders() {
		this.shaders.clear();
		return this;
	}

	/**
	 * Appends the given uniform to the builder.
	 *
	 * @param withName
	 * 		Name of the uniform
	 * @return this builder instance to allow method chaining.
	 */
	public ShaderProgramBuilder uniform(final String withName) {
		this.uniforms.add(Objects.requireNonNull(withName));
		return this;
	}

	/**
	 * Appends the given Shader to the builder.
	 *
	 * @param withShader
	 * 		Shader
	 * @return this builder instance to allow method chaining.
	 */
	public ShaderProgramBuilder shader(final Shader withShader) {
		this.shaders.add(Objects.requireNonNull(withShader));
		return this;
	}

	/**
	 * Appends a new shader based upon a builder create based upon a given consumer.
	 *
	 * @param withConsumer
	 * 		Consumer that accepts a new Shader builder.
	 * @return this builder instance to allow method chaining.
	 * @throws Exception
	 * 		if the shader cannot be compiled.
	 */
	public ShaderProgramBuilder shader(final Consumer<ShaderBuilder> withConsumer) throws Exception {
		final ShaderBuilder builder = Shader.builder();
		Objects.requireNonNull(withConsumer)
				.accept(builder);
		return this.shader(builder.build());
	}

	/**
	 * Returns a newly created Shader program based upon the data defined within this builder instance.
	 *
	 * @return newly created shader program
	 * @throws Exception
	 * 		if the shader program cannot be created.
	 */
	public ShaderProgram build() throws Exception {
		return new ShaderProgram(this.shaders, this.uniforms);
	}
}
