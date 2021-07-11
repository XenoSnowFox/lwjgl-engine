package com.xenosnowfox.lwjglengine.shader;

import org.lwjgl.opengl.GL46;

import java.util.Objects;

/**
 * Represents a shader instance.
 */
public class Shader {

	/**
	 * Returns a new Shader builder instance.
	 *
	 * @return Shader Builder.
	 */
	public static ShaderBuilder builder() {
		return new ShaderBuilder();
	}

	/**
	 * ID of the shader.
	 */
	private final int id;

	/**
	 * Type of shader.
	 */
	private final ShaderType type;

	/**
	 * Instantiates a new shader instance.
	 *
	 * @param withType
	 * 		Type of shader
	 * @param withCode
	 * 		Code to run as this shader.
	 * @throws Exception
	 * 		when a shader cannot be compiled.
	 */
	public Shader(final ShaderType withType, final String withCode) throws Exception {
		this.type = Objects.requireNonNull(withType);
		this.id = GL46.glCreateShader(this.type.getType());
		if (this.id == 0) {
			throw new Exception("Error creating shader. Type: " + this.type);
		}
		GL46.glShaderSource(this.id, Objects.requireNonNull(withCode));
		GL46.glCompileShader(this.id);
		if (GL46.glGetShaderi(this.id, GL46.GL_COMPILE_STATUS) == 0) {
			throw new Exception("Error compiling Shader code: " + GL46.glGetShaderInfoLog(this.id, 1024));
		}
	}

	/**
	 * Returns the id of the shader.
	 *
	 * @return Shader ID
	 */
	public int id() {
		return this.id;
	}

	/**
	 * Returns the type of the shader.
	 *
	 * @return Shader type.
	 */
	public ShaderType type() {
		return this.type;
	}

	/**
	 * Destroys the shader.
	 */
	public void destroy() {
		GL46.glDeleteShader(this.id);
	}
}
