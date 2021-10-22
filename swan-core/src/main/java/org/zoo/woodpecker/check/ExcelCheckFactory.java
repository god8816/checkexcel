package org.zoo.woodpecker.check;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zoo.woodpecker.write.ExcelBuilder;

/**
 * 
 * @author dzc
 */
public class ExcelCheckFactory {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelCheckFactory.class);
    
    public static ExcelBuilder list(List data, Class head) {
    	ExcelBuilder excelBuilder = new ExcelBuilder();
        return excelBuilder.list(data,head);
    }
}
