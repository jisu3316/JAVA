package board.md.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardVo2 {
	private String catgo;
	private String keyword;
	private int cp =1; //페이지 번호
	private int ps =5; //페이지 사이즈
	//{ cp=1; ps =5; } 
	
	
	
	public int getStartRow() {
		return (cp-1)*ps;	//첫번째 페이지 0 두번째 페이지 1*5=5
	}
	public int getEndRow() {
		return cp*ps; //ex) 1*5 = 5,2*5=10
	}
}
