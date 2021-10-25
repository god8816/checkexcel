package aaaaaaa;

import org.zoo.woodpecker.annotation.CheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.bean.ExcelPrentBean;

import lombok.Data;

@Data
public class AaBean extends ExcelPrentBean{

  /**/
  private static final long serialVersionUID = 1L;
	

  @Woodpecker(commonCheck = CheckType.phone, errorMsg="名字")
  private String name;
  
  @Woodpecker(commonCheck = CheckType.phone, errorMsg="年纪")
  private String age;
  
  @Woodpecker(commonCheck = CheckType.phone, errorMsg="性格")
  private String xg;

}
