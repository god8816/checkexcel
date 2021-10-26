package org.zoo.woodpecker;
import java.util.List;
import org.zoo.woodpecker.builder.ExcelBuilder;

/**
 * 
 * @author dzc
 */
public class ExcelCheckFactory {

    public static ExcelBuilder list(List data, Class head) {
        return ExcelBuilder.list(data,head);
    }
}
