package org.zoo.woodpecker.util;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.exception.WoodpeckerRuntimeException;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 动态代理
 * @author dzc
 */
public class WoodpeckerReflector {

	private static final Logger log = LoggerFactory.getLogger(WoodpeckerReflector.class);
	
    public static boolean execute(Object o,FieldCache fieldCache){
        if (Objects.isNull(fieldCache)) {
            return true;
        }
        
	
		try {
			Woodpecker woodpecker = fieldCache.getField().getAnnotation(Woodpecker.class);	
			if(Objects.nonNull(woodpecker)) {
				String  className = woodpecker.selfCheckClassName().getCanonicalName();
				String methodName = woodpecker.selfCheckMethodName();
				Class checkClass =Class.forName(className);
				if(Objects.nonNull(className) && Objects.nonNull(methodName) ) 
				{
				   Object status = MethodUtils.invokeStaticMethod(checkClass, methodName, o,fieldCache.getValue());
				   if(status.equals(true)) {
					   return true;
				   }if(status.equals(false)) {
					   return false;
				   }else {
					   throw new WoodpeckerRuntimeException("校验方法入参错误");
				   }
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			log.error("未找到selfCheckMethodName的自定义方法");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error("代码没有执行权限");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			log.error("未找到selfCheckClassName的自定义类");
		}
		
		
		return false;
    }
}
