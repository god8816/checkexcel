/*
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.zoo.swan.common.utils;

import java.util.List;

/**
 * The type String utils.
 *
 * @author dzc
 */
public class StringUtils {


    /**
     * Is none blank boolean.
     *
     * @param css the css
     * @return the boolean
     */
    public static boolean isNoneBlank(CharSequence... css) {
        return !isAnyBlank(css);
    }

    /**
     * Is any blank boolean.
     *
     * @param css the css
     * @return the boolean
     */
    private static boolean isAnyBlank(CharSequence... css) {
        if (isEmpty(css)) {
            return true;
        }
        for (CharSequence cs : css) {
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is empty boolean.
     *
     * @param array the array
     * @return the boolean
     */
    public static boolean isEmpty(final Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Is blank boolean.
     *
     * @param cs the cs
     * @return the boolean
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * redis redisson 
     * @param lists lists
     * @return String
     */
    public static String redissonJoin(final List<String> lists) {
    	    StringBuilder builder = new StringBuilder();
        for (String str : lists) {
         	 builder.append("\"").append(str).append("\"").append(",");
		}
        builder.delete(builder.length()-1, builder.length());
        return builder.toString();
    }
}
