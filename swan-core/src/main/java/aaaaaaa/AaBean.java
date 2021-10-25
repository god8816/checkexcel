package aaaaaaa;

import java.util.Date;

import org.zoo.woodpecker.annotation.CheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.bean.ExcelPrentBean;

import lombok.Data;

@Data
public class AaBean extends ExcelPrentBean{

  /**/
  private static final long serialVersionUID = 1L;
	

  @Woodpecker(commonCheck = CheckType.cn, errorMsg="名字")
  private String name;
  
  @Woodpecker(commonCheck = CheckType.phone, errorMsg="手机号格式异常")
  private String phone;
  
  @Woodpecker(commonCheck = CheckType.number, errorMsg="年纪")
  private Integer age;
  
  @Woodpecker(commonCheck = CheckType.timeFormat, errorMsg="性格")
  private Date dataTime;

}
