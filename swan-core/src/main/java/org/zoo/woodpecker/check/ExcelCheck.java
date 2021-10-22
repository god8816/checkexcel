package org.zoo.woodpecker.check;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author dzc
 */
public class ExcelCheck<T> {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelCheck.class);
    

    public ExcelCheck list(List data, Class head) {
         
        return this;
    }

    /**
     * 获取正常的导出记录
     * */
    public <T> List<T> doRightRecord() {
         
        return null;
    }
    
    
    /**
     * 获取异常的导出记录
     * */
    public <T> List<T> doErrorRecord() {
         
        return null;
    }
   
}
