package com.xenosnowfox.lwjglengine.shader;

import com.xenosnowfox.lwjglengine.utils.ResourceUtils;

import java.util.Objects;

/**
 * Builder capable of producing a new shader instance.
 */
public class ShaderBuilder {

	/**
	 * Type of shader being built.
	 */
	private ShaderType type;

	/**
	 * Shader code.
	 */
	private String code;

	/**
	 * Defines the type of shader being built.
	 *
	 * @param withType
	 * 		Shader Type
	 * @return this builder instance to allow for method chaining.
	 */
	public ShaderBuilder type(final ShaderType withType) {
		this.type = Objects.requireNonNull(withType);
		return this;
	}

	/**
	 * Defines the code to execute as the shader.
	 *
	 * @param withCode
	 * 		Shader code
	 * @return this builder instance to allow for method chaining.
	 */
	public ShaderBuilder code(final String withCode) {
		this.code = withCode;
		return this;
	}

	/**
	 * Defines the resource file, which contains the shader code to be loaded in.
	 *
	 * @param withResourceFile
	 * 		Filename of the shader resource file.
	 * @return this builder instance to allow for method chaining.
	 * @throws Exception
	 * 		if the resource file cannot be read into memory
	 */
	public ShaderBuilder fromResourceFile(final String withResourceFile) throws Exception {
		return this.code(ResourceUtils.loadResource(Objects.requireNonNull(withResourceFile)));
	}

	/**
	 * Returns a new shader instance compiled with the settings provided by this builder.
	 *
	 * @return New Shader instance
	 * @throws Exception
	 * 		if the shader code cannot be compiled.
	 */
	public Shader build() throws Exception {
		return new Shader(this.type, this.code);
	}
}
