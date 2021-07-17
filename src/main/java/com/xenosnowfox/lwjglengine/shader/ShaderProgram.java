package com.xenosnowfox.lwjglengine.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL46;
import org.lwjgl.system.MemoryStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents a shader program.
 */
public class ShaderProgram {

	/**
	 * Returns a new builder instance.
	 *
	 * @return Shader Program builder.
	 */
	public static ShaderProgramBuilder builder() {
		return new ShaderProgramBuilder();
	}

	/**
	 * Identifier for the shader program.
	 */
	private final int id;

	/**
	 * Collection of uniforms.
	 */
	private final Map<String, Integer> uniforms = new HashMap<>();

	/**
	 * Instantiates a new program.
	 *
	 * @param withShaders
	 * 		Collection of shaders to link to the program.
	 * @throws Exception
	 * 		if the program cannot be created.
	 */
	public ShaderProgram(final Collection<Shader> withShaders, final Collection<String> withUniforms) throws Exception {
		// create the program
		this.id = GL46.glCreateProgram();
		if (this.id == 0) {
			throw new Exception("Could not create Shader Program");
		}

		// attach shaders
		for (Shader shader : withShaders) {
			GL46.glAttachShader(this.id, shader.id());
		}

		// link the program together
		GL46.glLinkProgram(this.id);
		if (GL46.glGetProgrami(this.id, GL46.GL_LINK_STATUS) == 0) {
			throw new Exception("Error linking Shader code: " + GL46.glGetProgramInfoLog(this.id, 1024));
		}

		// detach shaders
		for (Shader shader : withShaders) {
			GL46.glDetachShader(this.id, shader.id());
		}

		// validate the program
		GL46.glValidateProgram(this.id);
		if (GL46.glGetProgrami(this.id, GL46.GL_VALIDATE_STATUS) == 0) {
			System.err.println("Warning validating Shader code: " + GL46.glGetProgramInfoLog(this.id, 1024));
		}

		// locate all the uniforms.
		for (String uniform : withUniforms) {
			int uniformLocation = GL46.glGetUniformLocation(this.id, uniform);
			if (uniformLocation < 0) {
				throw new Exception("Could not find uniform:" + uniform);
			}
			uniforms.put(uniform, uniformLocation);
		}
	}

	/**
	 * Returns the program's identifier.
	 *
	 * @return Program's ID.
	 */
	public int id() {
		return this.id;
	}

	/**
	 * Binds the program for use.
	 */
	public void bind() {
		GL46.glUseProgram(this.id);
	}

	/**
	 * Unbinds the program from use.
	 */
	public void unbind() {
		GL46.glUseProgram(0);
	}

	/**
	 * Destroys the program.
	 */
	public void destroy() {
		unbind();
		if (this.id != 0) {
			GL46.glDeleteProgram(this.id);
		}
	}

	public void uniform(String uniformName, Matrix4f value) {
		// Dump the matrix into a float buffer
		try (MemoryStack stack = MemoryStack.stackPush()) {
			GL46.glUniformMatrix4fv(uniforms.get(uniformName), false,
					value.get(stack.mallocFloat(16))
			);
		}
	}

	public void uniform(String uniformName, int value) {
		GL46.glUniform1i(uniforms.get(uniformName), value);
	}

	public void uniform(String uniformName, float value) {
		GL46.glUniform1f(uniforms.get(uniformName), value);
	}

	public void uniform(String uniformName, Vector3f value) {
		GL46.glUniform3f(uniforms.get(uniformName), value.x, value.y, value.z);
	}

	public void uniform(String uniformName, Vector4f value) {
		GL46.glUniform4f(uniforms.get(uniformName), value.x, value.y, value.z, value.w);
	}

	public void bindAndApply(final Consumer<ShaderProgram> withConsumer) {
		this.bind();
		Objects.requireNonNull(withConsumer)
				.accept(this);
		this.unbind();
	}
}
