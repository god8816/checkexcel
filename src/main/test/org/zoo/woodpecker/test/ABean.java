package org.zoo.woodpecker.test;

import java.util.Date;

import org.zoo.woodpecker.annotation.BusinessCheckType;
import org.zoo.woodpecker.annotation.EmptyCheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.bean.ExcelPrentBean;

import lombok.Data;

@Data
public class ABean extends ExcelPrentBean{

  /**/
  private static final long serialVersionUID = 1L;
	

  @Woodpecker(commonCheck = BusinessCheckType.cn,errorMsg="名字不是小明",selfCheckClassName= MyCheck.class,selfCheckMethodName="checkName")
  private String name;
  
  @Woodpecker(commonCheck = BusinessCheckType.idcard,errorMsg="身份证格式异常")
  private String idcard;
  
  @Woodpecker(commonCheck = BusinessCheckType.number, errorMsg="不是数字")
  private String number;
  
  @Woodpecker(commonCheck = BusinessCheckType.phone, errorMsg="手机号格式异常1")
  private String phone1;
  
  @Woodpecker(commonCheck = BusinessCheckType.phone, emptyCheckType=EmptyCheckType.notEmpty,errorMsg="手机号格式异常2")
  private String phone2;
  
  @Woodpecker(commonCheck = BusinessCheckType.number, errorMsg="年纪不是数字")
  private Integer age;
  
  @Woodpecker(commonCheck = BusinessCheckType.timeFormat, errorMsg="时间格式异常")
  private Date dataTime;

}
