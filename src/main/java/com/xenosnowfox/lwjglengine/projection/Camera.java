package com.xenosnowfox.lwjglengine.projection;

import org.joml.Vector3f;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Camera instance.
 */
public class Camera {

    /**
     * Position of the camera within the game world.
     */
    private final Vector3f position = new Vector3f();

    /**
     * Rotation of the camera.
     */
    private final Vector3f rotation = new Vector3f();

    /**
     * Instantiates a new camera instance, with an initial position located at the world origin.
     */
    public Camera() {
        this(new Vector3f(), new Vector3f());
    }

    /**
     * Instantiates a new camera instance with the given position within the game world.
     *
     * @param withPosition Position.
     */
    public Camera(Vector3f withPosition) {
        this(withPosition, new Vector3f());
    }

    /**
     * Instantiates a new camera instance with the given position within the game world and given rotation direction.
     *
     * @param withPosition Position.
     * @param withRotation Rotation.
     */
    public Camera(Vector3f withPosition, Vector3f withRotation) {
        this.position(withPosition);
        this.rotation(withRotation);
    }

    /**
     * Returns the position vector of the camera.
     *
     * @return Position.
     */
    public Vector3f position() {
        return position;
    }

    /**
     * Defines the position of the camera.
     *
     * @param withVector new camera position.
     * @return this camera instance to allow for method chaining.
     */
    public Camera position(final Vector3f withVector) {
        return this.position(withVector.x, withVector.y, withVector.z);
    }

    /**
     * Defines the position of the camera.
     *
     * @param x new x position of the camera.
     * @param y new y position of the camera.
     * @param z new z position of the camera.
     * @return this camera instance to allow for method chaining.
     */
    public Camera position(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
        return this;
    }

    /**
     * Defines a consumer that accepts the position vector, allowing the caller to directly update the camera's position.
     *
     * @param withConsumer Vector consumer.
     * @return this camera instance to allow for method chaining.
     */
    public Camera position(final Consumer<Vector3f> withConsumer) {
        Objects.requireNonNull(withConsumer).accept(this.position());
        return this;
    }

    /**
     * Returns the rotation vector of the camera.
     *
     * @return Rotation.
     */
    public Vector3f rotation() {
        return rotation;
    }

    /**
     * Defines the rotation of the camera.
     *
     * @param withVector new camera rotation vector.
     * @return this camera instance to allow for method chaining.
     */
    public Camera rotation(final Vector3f withVector) {
        return this.rotation(withVector.x, withVector.y, withVector.z);
    }

    /**
     * Defines the rotation of the camera.
     *
     * @param x new x rotation of the camera.
     * @param y new y rotation of the camera.
     * @param z new z rotation of the camera.
     * @return this camera instance to allow for method chaining.
     */
    public Camera rotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
        return this;
    }

    /**
     * Defines a consumer that accepts the rotation vector, allowing the caller to directly update the camera's rotation.
     *
     * @param withConsumer Vector consumer.
     * @return this camera instance to allow for method chaining.
     */
    public Camera rotation(final Consumer<Vector3f> withConsumer) {
        Objects.requireNonNull(withConsumer).accept(this.rotation());
        return this;
    }
}