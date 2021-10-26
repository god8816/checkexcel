package org.zoo.woodpecker.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * SwanException.
 *
 * @author dzc
 */
@Getter
@Setter
public class WoodpeckerException  {

    private static final long serialVersionUID = -948934144333391208L;
    
    private Integer code = 200;
    
    private String msg;

 
    public WoodpeckerException() {
    }

    public WoodpeckerException(final String msg) {
        this.msg = msg;
    }
 
    public WoodpeckerException(final Integer code ,final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
