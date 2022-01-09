package common.paging;

public class Paging {
	
	// 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, start, end, prev, next, 블록 갯수
	private int nowPage, startBoard, endBoard, total, cntPerPage, lastPage, start, end, prev, next;
	private int blockCnt = 5;
	private String url;
	
	public Paging() {
	}
	public Paging(Builder builder) {
		this.nowPage = builder.nowPage;
		this.cntPerPage = builder.cntPerPage;
		this.total = builder.total;
		this.url = builder.url;
		calcLastPage(getTotal(), getCntPerPage());
		calcStartBoardEndBoard(getNowPage(), getCntPerPage());
		calcStartEnd(getNowPage(), getLastPage());
		calcPrevAndNext(getStart(), getEnd(), getLastPage());
	}
	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int cntPerPage) {
		int lastPage = (int)Math.ceil((double)total/cntPerPage);
		if(lastPage == 0) {
			lastPage = 1;
		}
		this.lastPage = lastPage;
	}
	// 현재 페이지 기준 시작, 끝 게시글 계산
	public void calcStartBoardEndBoard(int nowPage, int cntPerPage) {
		this.startBoard = nowPage == 1 ? 1 : (nowPage-1)*cntPerPage+1;
		int end = startBoard + cntPerPage -1;
		this.endBoard = end > total ? total : end;
		System.out.println("startBoard:" + startBoard + ", endBoard:" + endBoard);
	}
	
	// 현재 페이지가 있는 블록의 처음, 끝
	public void calcStartEnd(int nowPage, int lastPage) {
		this.start = Math.abs(nowPage/blockCnt)*blockCnt+1;
		int endBlock = start + blockCnt -1;
		this.end = endBlock > lastPage ? lastPage : endBlock;
		System.out.println("start:" + start + ", end:" + end);
	}
	
	// 이전 버튼 클릭 시 이동할 페이지 / 다음 버튼 클릭 시 이동할 페이지
	public void calcPrevAndNext(int start, int end, int lastPage) {
		this.prev = start-1 > 0 ? start-1 : 1;
		int endPage = end + 1;
		this.next = endPage > lastPage ? lastPage : endPage;
		System.out.println("prev:" + prev + ", next:" + next);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String url; //페이징에서 링크할 url
		private int nowPage ;//현재 페이지
		private int total;//전체 게시물 수
		private int cntPerPage;  //페이지 당 뿌릴 게시물 수
		
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		public Builder nowPage(int nowPage) {
			this.nowPage = nowPage;
			return this;
		}
		
		public Builder total(int total) {
			this.total = total;
			return this;
		}
		
		public Builder cntPerPage(int cntPerPage) {
			this.cntPerPage = cntPerPage;
			return this;
		}
		
		public Paging build() {
			return new Paging(this);
		}
	}
	
	public int getNowPage() {
		return nowPage;
	}
	public int getStartBoard() {
		return startBoard;
	}
	public int getEndBoard() {
		return endBoard;
	}
	public int getTotal() {
		return total;
	}
	public int getCntPerPage() {
		return cntPerPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public int getPrev() {
		return prev;
	}
	public int getNext() {
		return next;
	}
	public int getBlockCnt() {
		return blockCnt;
	}
	public String getUrl() {
		return url;
	}
	
	@Override
	public String toString() {
		return "Paging [nowPage=" + nowPage + ", startBoard=" + startBoard + ", endBoard=" + endBoard + ", total="
				+ total + ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end="
				+ end + ", prev=" + prev + ", next=" + next + ", blockCnt=" + blockCnt + ", url=" + url + "]";
	}
}