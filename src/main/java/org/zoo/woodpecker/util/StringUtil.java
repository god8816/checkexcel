package org.zoo.woodpecker.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具
 * @author dzc
 */
public class StringUtil {

    public static final String HKM = "^[HMhm]{1}([0-9]{10}|[0-9]{8})$";
    public static final String PASSPORT = "^[a-zA-Z][0-9]{5,17}$";
    public static final String DIGITAL_NUMBER = "^[A-Za-z0-9]+$";
    public static final String TAIWAN1 = "^[0-9]{8}$";
    public static final String TAIWAN2 = "^[0-9]{10}$";

//    public static void main(String[] args) {
//        System.out.println("sH12345678".matches(HKM));
//        System.out.println("e21332222222222222".matches(PASSPORT));
//    }

    /**
     * 将对象转为int类型
     *
     * @param obj
     * @return
     */

    public static final int toInt(String obj, int def) {
        if (obj == null) {
            return def;
        } else {
            try {
                return Integer.parseInt(obj);
            } catch (Exception e) {
                return def;
            }
        }
    }

    public static final int toInt(String obj) {
        return toInt(obj, -1);
    }
    
    /**
     * 把字符串转换成正则表达式
     *
     * @author yujinjun
     * @param regexString
     * @return
     * @exception
     * @since JDK 1.7
     */
    public static String toRegex(String regexString){
    	if(isNullOrEmpty(regexString)){
    		return "";
    	}
    	if(!regexString.startsWith("^")){
    		regexString = "^" + regexString;
    	}
    	if(!regexString.endsWith("$")){
    		regexString = regexString + "$";
    	}
    	return regexString;
    }

    /**
     * 将对象转为double类型
     *
     * @param obj
     * @return
     */
    public static final double toDouble(String obj, int def) {
        if (obj == null) {
            return def;
        } else {
            try {
                return Double.parseDouble(obj);
            } catch (Exception e) {
                return def;
            }
        }
    }

    public static final double toDouble(String obj) {
        return toInt(obj, -1);
    }

    /**
     * 检查字符串是否为空 或 null
     *
     * @param s
     * @return
     */
    public static final boolean checkEmptyNull(Object s) {
        return s == null || s.equals("");
    }

    /**
     * 检查字符串是否为空 或 null
     *
     * @param s
     * @return
     */
    public static final boolean isNullOrEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    /***
     * @date 2015-6-4
     * @author shm
     * @returnType boolean
     */
    public static final boolean IsEqual(String str1, String str2) {
        boolean isEqual = false;
        if (str1 == null && str2 == null) {
            isEqual = true;
        } else {
            if (str1 != null && str1.equals(str2)) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    /**
     * 检查字符串是否不为空
     *
     * @param s
     * @return
     */
    public static final boolean notNullOrEmpty(String s) {
        return s != null && !"".equals(s);
    }

    /**
     * 检查字符串是否为数字
     *
     * @param s
     * @return
     */
    public static final boolean isDigital(String s) {
        if (notNullOrEmpty(s)) {
            int index = s.indexOf(".");
            if (index < 0) {
                return StringUtils.isNumeric(s);
            } else {
                String num1 = s.substring(0, index);
                String num2 = s.substring(index + 1);
                return StringUtils.isNumeric(num1) && StringUtils.isNumeric(num2);
            }
        } else {
            return false;
        }
    }

    /**
     * 将对象转成String字符串
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj, String def) {
        if (obj == null) {
            return def;
        } else {
            return obj.toString();
        }
    }

    public static String toString(Object obj) {
        return toString(obj, "");
    }

    public static String join(Iterator<String> iter, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        if (iter.hasNext()) {
            buffer.append(iter.next());
            while (iter.hasNext()) {
                buffer.append(delimiter);
                buffer.append(iter.next());
            }
        }
        return buffer.toString();
    }
    
    public static String join(List<String> iter, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<iter.size();i++) {
          	buffer.append(iter.get(i));
        	    if(i<iter.size()-1) {
        	     	buffer.append(delimiter);
        	    }
        }
        return buffer.toString();
    }

    public static String join(String delimiter, Object... args) {
        if (args == null)
            return null;
        StringBuffer buffer = new StringBuffer();
        for (Object obj : args) {
            buffer.append(obj == null ? "" : obj);
            buffer.append(delimiter);
        }
        return buffer.toString();
    }

    /**
     * 正则表达式数组分割(过滤重复的值)
     *
     * @param str
     * @param regular
     * @return "aaaaaaaaaaa#{aa}aaaa#{aa}aaaaaa#{bbb}aaaa" aa,bb
     */
    public static List<String> regularSplit(String str) {
        List<String> strList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("#\\{\\w+\\}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String string = matcher.group();
            // 仅读取结果集
            string = string.substring(2, string.length() - 1);
            if (!strList.contains(string)) {
                strList.add(string);
            }
        }
        return strList;
    }

    /**
     * s 左补 p，按size大小
     */
    public static String leftPad(String s, int size, char p) {
        if (s == null || s.length() > size) {
            return s;
        } else {
            int ps = size - s.length();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ps; i++) {
                sb.append(p);
            }
            sb.append(s);
            return sb.toString();
        }
    }

    /**
     */
    public static String replace(String src, String s, String t) {
        if (src == null || src.length() == 0) {
            return src;
        } else {
            return src.replace(s, t);
        }
    }

    /**
     * double 转 string 防止出现科学计数法
     *
     * @param dob
     * @return
     */
    public static String doubleToString(double dob) {
        BigDecimal bd = new BigDecimal(dob);
        return bd.toPlainString();
    }

    /**
     * 正则替换，替换template中${}的参数
     *
     * @param template
     * @param tokens
     * @return
     */
    public static String replaceMapToString(String template, HashMap<String, Object> tokens) {
        // 生成匹配模式的正则表达式
        String patternString = "\\$\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, (String) tokens.get(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 转义keyword like字句中需要调用
     *
     * @param keyword
     * @return
     */
    public static final String transferLikeCaulse(String keyword) {
        if (keyword == null)
            return keyword;
        if (keyword.contains("%") || keyword.contains("_")) {
            keyword = keyword.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\%", "\\\\%").replaceAll("\\_", "\\\\_");
        }
        return "%" + keyword + "%";
    }

    /**
     * String,String[] =-->String,String
     *
     * @throws UnsupportedEncodingException
     */
    /*
	 * public static final Map<String, String> formatMapString( Map<String,
	 * String[]> map, String delim) throws UnsupportedEncodingException {
	 * Isz.require(map != null, "参数map为空"); Map<String, String> ret = new
	 * HashMap<String, String>(); for (Entry<String, String[]> entry :
	 * map.entrySet()) { ret.put(entry.getKey(), URLDecoder.decode(
	 * StringUtils.arrayToDelimitedString(entry.getValue(), delim), "UTF-8")); }
	 * return ret; }
	 */
	/* *//**
     * 计算表达式的值，一般用于多个变量字符串连接
     *
     * @param express
     * @param param
     * @return
     *//*
			 * public static final String calcExpression(String express,
			 * Map<String, Object> param) { if (checkEmptyNull(express)) {
			 * return ""; } List<Variable> variables = new
			 * ArrayList<Variable>(); Iterator<Entry<String, Object>> iter =
			 * param.entrySet().iterator(); while (iter.hasNext()) {
			 * Entry<String, Object> entry = iter.next(); String value =
			 * entry.getValue() == null ? "" : "" + entry.getValue();
			 * variables.add(Variable.createVariable(entry.getKey(), value)); }
			 * // 执行表达式 return ExpressionEvaluator.evaluate(express,
			 * variables).toString(); }
			 */

    /**
     * 先匹配 第一个参数，如果为空，则取b
     *
     * @param a
     * @param b
     * @return
     */
    public static final String firstMatch(String a, String b) {
        return StringUtil.checkEmptyNull(a) ? b : a;
    }

    /**
     * @ClassName: MoneyToCNY
     */
    public static class MoneyToCNY {
        /**
         * 汉语中数字大写
         */
        private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        /**
         * 汉语中货币单位大写，这样的设计类似于占位符
         */
        private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿",
                "拾", "佰", "仟", "兆", "拾", "佰", "仟"};
        /**
         * 特殊字符：整
         */
        private static final String CN_FULL = "整";
        /**
         * 特殊字符：负
         */
        private static final String CN_NEGATIVE = "负";
        /**
         * 金额的精度，默认值为2
         */
        private static final int MONEY_PRECISION = 2;
        /**
         * 特殊字符：零元整
         */
        private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

        /**
         * 把输入的金额转换为汉语中人民币的大写
         *
         * @param numberOfMoney 输入的金额
         * @return 对应的汉语大写
         */
        public static String number2CNMontrayUnit(double numberOfMoney) {
            return number2CNMontrayUnit(new BigDecimal(numberOfMoney));
        }

        /**
         * 把输入的金额转换为汉语中人民币的大写
         *
         * @param numberOfMoney 输入的金额
         * @return 对应的汉语大写
         */
        public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
            StringBuffer sb = new StringBuffer();
            // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
            // positive.
            int signum = numberOfMoney.signum();
            // 零元整的情况
            if (signum == 0) {
                return CN_ZEOR_FULL;
            }
            // 这里会进行金额的四舍五入
            long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
            // 得到小数点后两位值
            long scale = number % 100;
            int numUnit = 0;
            int numIndex = 0;
            boolean getZero = false;
            // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
            if (!(scale > 0)) {
                numIndex = 2;
                number = number / 100;
                getZero = true;
            }
            if ((scale > 0) && (!(scale % 10 > 0))) {
                numIndex = 1;
                number = number / 10;
                getZero = true;
            }
            int zeroSize = 0;
            while (true) {
                if (number <= 0) {
                    break;
                }
                // 每次获取到最后一个数
                numUnit = (int) (number % 10);
                if (numUnit > 0) {
                    if ((numIndex == 9) && (zeroSize >= 3)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                    }
                    if ((numIndex == 13) && (zeroSize >= 3)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                    }
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    getZero = false;
                    zeroSize = 0;
                } else {
                    ++zeroSize;
                    if (!(getZero)) {
                        sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    }
                    if (numIndex == 2) {
                        if (number > 0) {
                            sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                        }
                    } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                    getZero = true;
                }
                // 让number每次都去掉最后一个数
                number = number / 10;
                ++numIndex;
            }
            // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
            if (signum == -1) {
                sb.insert(0, CN_NEGATIVE);
            }
            // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
            if (!(scale > 0)) {
                sb.append(CN_FULL);
            }
            return sb.toString();
        }
    }

    /**
     * 英文逗号隔开的单引号字符串拼接
     *
     * @param source
     * @param mark
     * @return String
     */
    public static String searchParamsByMarks(String source, String mark) {
        if (StringUtil.isNullOrEmpty(source)) {
            return source;
        }
//        if (source.contains("'")) {
//            source.replace("'", "");
//        }
        return mark + source.replace(",", "','") + mark;
    }

    /**
     * 下拉框多选拼成'test1','test2'形式
     *
     * @param pageData
     * @param paraList
     * @return
     */
    public static Map<String, Object> readAndWritePageData(Map<String, Object> pageData, List<String> paraList) {
        for (String para : paraList) {
            Iterator<Entry<String, Object>> iterator = pageData.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
                String key = entry.getKey();
                Object vaule = entry.getValue();
                if (para.equals(key)) {
                    if (!StringUtil.isNullOrEmpty((String) vaule)) {
                        pageData.put(para, StringUtil.searchParamsByMarks((String) vaule, "'"));
                    }
                }
            }
        }
        return pageData;
    }

    
    public static List<String> buildStrs(String str) {
		if(StringUtils.isNotBlank(str)) {
			String[] idsArray = str.split(",");
			List<String> idInts = new ArrayList<String>(idsArray.length);
			for (String idStr : idsArray) {
				if(StringUtils.isNotBlank(idStr)) {
					idInts.add(idStr);
				}
			}
			return idInts;
		}
		return new ArrayList<String>();
	}
    
    
    /**
     * 时间戳转化成标准时间
     * @param s
     * @return
     */
    public static final String UnixTimeToString(Object time) {
		if (!StringUtil.checkEmptyNull(time) && !"0".equals(String.valueOf(time))) {
			String res = null;
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str_time = time.toString();
			long long_time =Long.parseLong(str_time) ;
			if(str_time.length()>10){
		        long lt = new Long(long_time);
		        Date date = new Date(lt);
		        res = simpleDateFormat.format(date);
		        return res;
			}else{
		        long lt = new Long(long_time*1000);
		        Date date = new Date(lt);
		        res = simpleDateFormat.format(date);
		        return res;
			}
		}else{
			return null;
		}
    }
    
    public static String genRandomNum(){  
	      int  maxNum = 36;  
	      int i;  
	      int count = 0;  
	      char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
	        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
	        'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
	      StringBuffer pwd = new StringBuffer("");  
	      Random r = new Random();  
	      while(count < 8){  
	       i = Math.abs(r.nextInt(maxNum));     
	       if (i >= 0 && i < str.length) {  
	       pwd.append(str[i]);  
	       count ++;  
	     }  
	  }  
	  return pwd.toString();  
	}  
    
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		return !StringUtil.isEmpty(str);
	}
	
	public static boolean isNotBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }
	
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * @return 
     * 
    * @Title: ifNullReturnEmptyString 
    * @Description:如果传进来的是空则返回空字符串
    * @param     设定文件 
    * @throws
     */
    public static String ifNullReturnEmptyString(String value){
    	if(value == null){
    		return "";
    	}else{
    		return value;
    	}
    }
	
	
	/**判断是否是手机号*/ 
    public static boolean isMobileNO(String mobiles){  
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(mobiles);  
        b = m.matches();   
        return b; 
	}  
	
	    
    /** 手机号中间4位替换成*号 */ 
    public static String replaceMobileNO(String mobile){
	    	if(isNotEmpty(mobile)){
	            mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	    	}
	    	return mobile;
	} 
    
    
    /** String集合转化int集合 */ 
    public static List<Integer> strListTointList(List<String> strList){
    	    List<Integer> numList = new ArrayList<Integer>();
	    	if(strList!=null && strList.size()>0){
	            for (String str : strList) {
	              	numList.add(Integer.valueOf(str));
				}
	    	}
	    	return numList;
	} 
    
    /** Integer集合转化String集合 */ 
    public static List<String> intListTostringList(List<Integer> intList){
    	    List<String> numList = new ArrayList<String>();
	    	if(intList!=null && intList.size()>0){
	            for (Integer str : intList) {
	              	numList.add(str.toString());
				}
	    	}
	    	return numList;
	} 
    
    /** Integer集合转化Long */ 
    public static Long intToLong(Integer intObj){
    	    if(intObj!=null) {
    	    	    return intObj.longValue();
    	    }
	    	return null;
	} 
    
    /** Integer集合转化Long */ 
    public static Long stringToLong(String stringObj){
    	    if(stringObj!=null) {
    	    	    return Long.valueOf(stringObj);
    	    }
	    	return null;
	} 
}
