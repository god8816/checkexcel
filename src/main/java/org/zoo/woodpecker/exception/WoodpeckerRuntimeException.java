package org.zoo.woodpecker.exception;

/**
 * 公共异常
 * @author dzc
 */
public class WoodpeckerRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -1949770547060521702L;


    public WoodpeckerRuntimeException() {
    }

    /**
     *
     * @param message the message
     */
    public WoodpeckerRuntimeException(final String message) {
        super(message);
    }

    /**
     *
     * @param message the message
     * @param cause   the cause
     */
    public WoodpeckerRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause the cause
     */
    public WoodpeckerRuntimeException(final Throwable cause) {
        super(cause);
    }
}
