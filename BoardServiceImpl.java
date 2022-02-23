package board.md.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import board.md.domain.Board;
import board.md.domain.BoardListResult;
import board.md.domain.BoardVo;
import board.md.domain.BoardVo2;
import board.md.domain.Criteria;
import board.md.fileset.Path;
import board.md.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
	
	@Override
	public BoardListResult getBoardListResult(String catgo,String keyword,int cp, int ps) {
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp,ps);
		List<Board> list = boardMapper.selectPerPage(boardVo);
		return new BoardListResult(cp,totalCount,ps,list);
	}
	@Override
	public BoardListResult getBoardListResult2(String catgo,String keyword,int cp, int ps) {
		
		BoardVo2 boardVo2 = new BoardVo2(catgo,keyword,cp,ps);
		System.out.println("서비스안 :"+keyword+catgo);
		List<Board> list = boardMapper.selectSearchPerPage(boardVo2);
		System.out.println("서비스안 리스트  사이즈 :"+list.size());
		String totalCountstr = boardMapper.selectsearchCount(boardVo2);
		long totalCount=0;
		if(totalCountstr==null) {
			totalCount=0;
		}else {
			totalCount = Long.parseLong(totalCountstr);
		}
		return new BoardListResult(cp,totalCount,ps,list);
	}
	/*@Override
	public  SearchList getSerchList(int cp ,int ps,String subject) {
		log.info("들어옴");
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp,ps);
		Board board = new Board(subject);
		List<Board> list = boardMapper.searchSubject(cp,ps,board);
		System.out.println("boardvo startRow: "+boardVo.getStartRow());
		System.out.println("boardvo : "+boardVo.getEndRow());
		
		return new SearchList(cp,totalCount,ps,list);
		
	}*/
	
	@Override
	public List<Board> listS() {
		
		return boardMapper.list();
	}
	
	@Override
	public List<Board> listPageS(Criteria cri) {
		return boardMapper.listPage(cri);
	}

	@Override
	public void write(Board board,MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");	
		String ofheader = ofname.substring(0, idx);		
		String ext = ofname.substring(idx);		
		long ms = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);		
		sb.append(ext);
		String saveFileName = sb.toString();
		long fsize = file.getSize();
	
			
		board.setOfname(ofname);
		board.setFname(saveFileName);
		board.setFsize(fsize);
			
		
		boolean flag = writeFile(file, saveFileName);
		if(flag) {
			System.out.println("#업로드 성공");
		}else {
			System.out.println("#업로드 실패");
		}
		boardMapper.insert(board);//여기서 데이터베이스에 들어감
	}
	private boolean writeFile(MultipartFile file,String saveFileName) {
		File dir = new File(Path.FILE_STORE);
		if(!dir.exists()) dir.mkdirs();
		
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE+saveFileName);
			fos.write(data);
			fos.flush();
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
	}
	
	@Override
	public void remove(long seq) {
		boardMapper.delete(seq);

	}
	
	@Override
	public Board contentS(long seq) {
		return boardMapper.content(seq);

	}
	@Override
	public void updateS(Board board) {
		boardMapper.update(board);

	}


	@Override
	public long selectCount() {
		long Count = boardMapper.selectCount();
		return Count;
	}
	@Override
	public String selectsearchCount(BoardVo2 boardVo2) {
		String totalCountstr = boardMapper.selectsearchCount(boardVo2);
		long totalCount = Long.parseLong(totalCountstr);
		return "totalCountstr";
	}

	


	

}
