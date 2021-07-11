package com.xenosnowfox.lwjglengine.shader;

import org.lwjgl.opengl.GL46;

/**
 * Represents the available shader types.
 */
public enum ShaderType {

	/**
	 * Vertex shader.
	 */
	VERTEX(GL46.GL_VERTEX_SHADER),

	/**
	 * Compute shader.
	 */
	COMPUTE(GL46.GL_COMPUTE_SHADER),

	/**
	 * Fragment shader.
	 */
	FRAGMENT(GL46.GL_FRAGMENT_SHADER),

	/**
	 * Geometry shader.
	 */
	GEOMETRY(GL46.GL_GEOMETRY_SHADER);

	/**
	 * Type id for the shader.
	 */
	private final int type;

	/**
	 * Instantiates a new shader type.
	 *
	 * @param withType
	 * 		GL type id.
	 */
	ShaderType(final int withType) {
		this.type = withType;
	}

	/**
	 * Returns the GL id for the shader type.
	 *
	 * @return Type id.
	 */
	public int getType() {
		return this.type;
	}
}
