package board.md.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public Criteria getCri() {
		return cri;
	}
	 
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);//1/10 >0.1 math 하면 1 *display(10) 하면 10
		startPage = (endPage - displayPageNum) + 1;//10-10+1=1
	  
		int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));//총갯수에서 /10 한다 206/10 =20>21
		if (endPage > tempEndPage) {//엔드페이지가 더 커지면 21이된다
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;//스타트페이지가 1이면 false 아니면 true
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true; //엔드페이지 (10)*(cri )10 >=206 크면 false 아니면 true
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents =
		UriComponentsBuilder.newInstance()
						    .queryParam("page", page)
							.queryParam("perPageNum", cri.getPerPageNum())
							.build();
		   
		return uriComponents.toUriString();
	}
}
