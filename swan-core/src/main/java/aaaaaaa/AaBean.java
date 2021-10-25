package aaaaaaa;

import org.zoo.woodpecker.annotation.Woodpecker;

import lombok.Data;

@Data
public class AaBean {

  @Woodpecker(errorMsg="名字")
  private String name;

}
