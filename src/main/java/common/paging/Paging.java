package common.paging;

public class Paging {
	// 현재페이지, 게시글시작, 게시글끝, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, start, end, prev, next, 블록갯수
	private int nowPage, startBoard, endBoard, total, cntPerPage, lastPage, startPage, endPage, prev, next;
	private int blockCnt = 5;
	private String url;

	public Paging() {
		}

	public Paging(int total, int nowPage, int cntPerPage, String url) {
			setNowPage(nowPage);
			setCntPerPage(cntPerPage);
			setTotal(total);
			setUrl(url);
			calcLastPage(getTotal(), getCntPerPage());
			calcStartBoardEndBoard(getNowPage(), getCntPerPage());
			calcStartPageEndPage(getNowPage(), getLastPage());
			calcPrevAndNext(getStartPage(), getEndPage(), getLastPage());
		}

	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int cntPerPage) {
		int lastPage = (int) Math.ceil((double) total / cntPerPage);
		if (lastPage == 0) {
			lastPage = 1;
		}
		setLastPage(lastPage);
	}

	// 현재 페이지 기준 시작, 끝 게시글 계산
	public void calcStartBoardEndBoard(int nowPage, int cntPerPage) {
		this.startBoard = nowPage == 1 ? 1 : (nowPage - 1) * cntPerPage + 1;
		int end = startBoard + cntPerPage - 1;
		this.endBoard = end > total ? total : end;
		System.out.println("startBoard:" + startBoard + ", endBoard:" + endBoard);
	}

	// 현재 페이지가 있는 블록의 처음, 끝
	public void calcStartPageEndPage(int nowPage, int lastPage) {
		this.startPage = Math.abs(nowPage / blockCnt) * blockCnt + 1;
		int endBlock = startPage + blockCnt - 1;
		this.endPage = endBlock > lastPage ? lastPage : endBlock;
		System.out.println("StartPage:" + startPage + ", endPage:" + endPage);
	}

	// 이전 버튼 클릭 시 이동할 페이지 / 다음 버튼 클릭 시 이동할 페이지
	public void calcPrevAndNext(int startPage, int endPage, int lastPage) {
		this.prev = startPage - 1 > 0 ? startPage - 1 : 1;
		int end = endPage + 1;
		this.next = end > lastPage ? lastPage : end;
		System.out.println("prev:" + prev + ", next:" + next);
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartBoard() {
		return startBoard;
	}

	public void setStartBoard(int startBoard) {
		this.startBoard = startBoard;
	}

	public int getEndBoard() {
		return endBoard;
	}

	public void setEndBoard(int endBoard) {
		this.endBoard = endBoard;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getBlockCnt() {
		return blockCnt;
	}

	public void setBlockCnt(int blockCnt) {
		this.blockCnt = blockCnt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Paging [nowPage=" + nowPage + ", startBoard=" + startBoard + ", endBoard=" + endBoard + ", total="
				+ total + ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", prev=" + prev + ", next=" + next + ", blockCnt=" + blockCnt + ", url=" + url + "]";
	}

}
