package com.mcaronics.common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

//실제데이터를 처리하는 클래스, 이미 만들어진 테이블 대신 데이터를 인수로 넘겨 표시...
//데이터(테이블)을 프린트할 수 있는 상태로 변환하여 저장하고있다.
public class TablePrinter implements Printable, Pageable {

	// 실제 출력할 데이터 처리 클래스, Printable인터페이스와 Pageable인터페이스 구현
	protected JTable table; // 테이블 처리할 인스턴스 변수 선언
	protected PageFormat pageFormat; // 페이지포멧 인스턴스변수 선언
	protected int headerStatus = ALL_PAGES;

	public final static int ALL_PAGES = 0;
	public final static int FIRST_PAGE_ONLY = 1;
	public final static int NO_PAGES = 2;

	public TablePrinter(JTable tbl, PageFormat pf) {

		// 생성자, 테이블개체와 페이지포멧 개체를 인수로 받아 각각의 변수에 저장
		table = tbl;
		pageFormat = pf;
	}

	public int print(Graphics g, PageFormat pf, int index) {

		// Printable인터페이스의 추상메소드 구현
		Dimension size = null;
		if ((table.getWidth() == 0) || (table.getHeight() == 0)) { // 테이블의 넓이나
																	// 높이가 0이면,
			table.setSize(table.getPreferredSize()); // 컴포넌트에 의한 사이즈값으로 세팅
		}
		int tableWidth = table.getWidth(); // 테이블 너비 가져와 변수에 세팅
		int tableHeight = table.getHeight(); // 테이블 높이 가져와 변수에 세팅
		int positionX = 0; // X위치로 사용할 변수 0으로 세팅
		int positionY = 0;// Y위치로 사용할 변수 0으로 세팅

		int pageIndex = 0; // 페이지 번호로 사용할 변수 0으로 세팅
		while (positionY < tableHeight) { // 반복문... positionY변수값이 테이블 높이보다 작다면..
			positionX = 0; // positionX변수 0으로 세팅
			while (positionX < tableWidth) {
				size = getPrintSize(positionX, positionY);

				// getPinrtSize메소드에 positionX, positionY변수 인수로 전달하여 리턴된 size 값
				// 저장
				if (pageIndex == index) {

					// 루프를 돌며 인수로 온 index값이 PageIndex와 같은지 비교하고 같다면,
					paintTable(g, positionX, positionY, size);// 테이블을 표시한고,
					return Printable.PAGE_EXISTS;

					// PAGE_EXISTS 상수를 리턴하여 Printable에 랜더링된 페이지가 있음을 알린다.
				}
				pageIndex++; // pageIndex를 1증가 시킴
				positionX += size.width; // positionX에 size 너비값을 더해 저장.
			}
			positionY += size.height; // positionY에 size 높이값을 더해 저장.
		}
		return Printable.NO_SUCH_PAGE;

		// NO_SUCH_PAGE 상수 리턴하여 Printable에 랜더링된 페이지가 더이상 없음을 알린다.
	}

	protected Dimension getPrintSize(int positionX, int positionY) { // 프린트가능한
																		// 사이즈를
																		// 리턴
		Rectangle rect;
		int printWidth; // 프린트가능한 너비를 저장할 변수
		int printHeight; // 프린트가능한 높이를 저장할 변수
		int firstCol = table.columnAtPoint(new Point(positionX, positionY));

		// 입력된 좌표에 대해 테이블 컬럼의 인덱스를 리턴
		int firstRow = table.rowAtPoint(new Point(positionX, positionY));

		// 입력된 좌표에 대해 테이블열의 인텍스를 리턴
		int maxWidth = (int) (pageFormat.getImageableWidth());

		// 페이지포멧으로부터 이미지출력 가능한 너비를 가져와 maxWidth변수에 저장
		int maxHeight = (int) (pageFormat.getImageableHeight());

		// 페이지포멧으로부터 이미지출력 가능한 높이를 가져와 maxHeight변수에 저장
		if (displayHeaderOnPage(positionY)) {

			// positionY변수를 인자로 넘겨받아 테이블헤더 표시여부를 결정.
			maxHeight -= table.getTableHeader().getHeight();

			// 조건에 맞다면 최대높이에서 테이블헤더 만큼의 높이를 뺀다.
		}

		int lastCol = table.columnAtPoint(new Point(positionX + maxWidth, positionY));// 최대
																						// 너비에
																						// 대한
																						// 좌표에
																						// 대해
																						// 마지막
																						// 컬럼의
																						// 인덱스를
																						// 구해
																						// 리턴
		if (lastCol == -1) { // 만약 해당 좌표에 컬럼이 없다면,
			printWidth = table.getWidth() - positionX;

			// 테이블 너비에서 positionX만큼 제한겂을 printWidth에 저장
		} else { // 컬럼이 존재한다면,
			rect = table.getCellRect(0, lastCol - 1, true); // 마지막컬럼의 위 컬럼의 크기를
															// 저장
			printWidth = rect.x + rect.width - positionX;

			// 위의 마지막 컬럼의 상자크기값에서 x좌표값과 너비를 더하고 positionX값을 뺀 값을 변수에저장
		}

		int lastRow = table.rowAtPoint(new Point(positionX, positionY + maxHeight));

		// 위와 동일한 작업
		if (lastRow == -1) {
			printHeight = table.getHeight() - positionY;
		} else {
			rect = table.getCellRect(lastRow - 1, 0, true);
			printHeight = rect.y + rect.height - positionY;
		}
		return new Dimension(printWidth, printHeight); // 프린트 작업영역 리턴..
	}

	protected void paintTable(Graphics g, int positionX, int positionY, Dimension size) {// 테이블을
																							// 그려내기위한
																							// 메소드
		int offsetX = (int) (pageFormat.getImageableX());// 페이지포멧에서 이미지X좌표 얻어오기
		int offsetY = (int) (pageFormat.getImageableY());// 페이지포멧에서 이미지Y좌표 얻어오기
		if (displayHeaderOnPage(positionY)) { // 헤더 표시유무 확인, True면
			JTableHeader header = table.getTableHeader();// table에서 테이블헤더를 가져와
															// 변수에 저장,
			if ((header.getWidth() == 0) || (header.getHeight() == 0)) {// 만약
																		// 헤더너비나
																		// 높이가
																		// 0이라면,
				header.setSize(header.getPreferredSize());// 기본적인 size값을 세팅
			}
			int headerHeight = header.getHeight();// 헤더에서 높이가져와 변수에 저장
			g.translate(offsetX - positionX, offsetY);// 인수로 얻은 값 만큼 펜 이동
			g.clipRect(positionX, 0, size.width, size.height + headerHeight);

			// 지정 좌표를 클립(처음 clipRect가 호출되었으므로 새로운클립이됨)한 후,
			header.paint(g);// 헤더 그리기..
			g.translate(0, headerHeight - positionY);// 원점으로 되돌리기..
			g.clipRect(positionX, positionY, size.width, size.height);// 지정좌표를
																		// 클립
		} else {
			g.translate(offsetX - positionX, offsetY - positionY);// 좌표이동
			g.clipRect(positionX, positionY, size.width, size.height);// 클립
		}
		table.paint(g);// 테이블 그리기...
	}

	protected boolean displayHeaderOnPage(int positionY) { // 헤더 표시여부를 검사
		return ((headerStatus == ALL_PAGES) || ((headerStatus == FIRST_PAGE_ONLY) && positionY == 0));
	}// headerStatus가 ALL_PAGES(0) 이거나 headerStatus가 FIRST_PAGE_ONLY(1) 이고
		// positionY값이 0이면 true, 아니면 false리턴.

	public int getNumberOfPages() {

		// Pageable 인터페이스의 추상메소드 구현, 페이지 번호를 리턴하는 메소드
		Dimension size = null;
		int tableWidth = table.getWidth(); // 테이블의 너비와 높이를 저장
		int tableHeight = table.getHeight();
		int positionX = 0;
		int positionY = 0;

		int pageIndex = 0;
		while (positionY < tableHeight) {// 테이블높이가 positionY보다 크면,
			positionX = 0; // positionX를 0으로 초기화
			while (positionX < tableWidth) { // 테이블너비가 positionX보다 크면,
				size = getPrintSize(positionX, positionY);

				// getPrintSize메소드에 인수를 넘겨주어 인쇄가능 사이즈를 얻어온다.
				positionX += size.width;// positionX에 size의 너비값을 더해주고,
				pageIndex++;// 페이지 인덱스를 1증가, while문 조건으로 이동
			}
			positionY += size.height;// positionY에 size의 높이값을 더한다.
		} // while문 조건으로 이동
		return pageIndex;
	}

	public Printable getPrintable(int index) {

		// Pageable 인터페이스의 추상메소드 구현, 해당 페이지 번호의 Printable 리턴
		return this;
	}

	public PageFormat getPageFormat(int index) {

		// Pageable 인터페이스의 추상메소드 구현, 해당 페이지번호의 페이지 포멧을 리턴
		return pageFormat;
	}
}
