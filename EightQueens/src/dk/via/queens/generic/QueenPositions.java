package dk.via.queens.generic;

import java.io.PrintWriter;
import java.io.StringWriter;

public class QueenPositions implements SolutionSpace {
	private int[] positions;
	private int size;
	private int rows;
	
	public QueenPositions(int size, int rows) {
		this.size = size;
		this.rows = rows;
		positions = new int[size];
	}
	
	public QueenPositions(int rows) {
		this(0, rows);
	}
	
	private boolean collides(int i, int j) {
		return positions[i] == positions[j] || Math.abs(i - j) == Math.abs(positions[i] - positions[j]);
	}
	
	/* (non-Javadoc)
	 * @see dk.via.queens.generic.SolutionSpace#reject()
	 */
	@Override
	public boolean reject() {
		for(int i = 0; i < this.size; i++) {
			for(int j = i + 1; j < this.size; j++) {
				if (collides(i, j)) return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see dk.via.queens.generic.SolutionSpace#accept()
	 */
	@Override
	public boolean accept() {
		return size == rows && !reject();
	}
	
	/* (non-Javadoc)
	 * @see dk.via.queens.generic.SolutionSpace#hasNext()
	 */
	@Override
	public boolean hasMore() {
		return size > 0 && positions[size-1] < rows;
	}
	
	/* (non-Javadoc)
	 * @see dk.via.queens.generic.SolutionSpace#next()
	 */
	@Override
	public void next() {
		positions[size-1]++;
	}
	
	/* (non-Javadoc)
	 * @see dk.via.queens.generic.SolutionSpace#extend()
	 */
	@Override
	public SolutionSpace extend() {
		QueenPositions ext = new QueenPositions(size + 1, rows);
		System.arraycopy(positions, 0, ext.positions, 0, positions.length);
		return ext;
	}
	
	@Override
	public SolutionSpace copy() {
		QueenPositions ext = new QueenPositions(size, rows);
		System.arraycopy(positions, 0, ext.positions, 0, positions.length);
		return ext;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < positions.length; i++) {
			if (i > 0) sb.append('+');
			sb.append('-');
		}
		String divider = sb.toString();
		StringWriter stringWriter = new StringWriter();
		PrintWriter printer = new PrintWriter(stringWriter);
		printer.println();
		for (int i = 0; i < size; i++) {
			for(int j = 0; j < positions.length; j++) {
				if (j > 0) printer.print('|');
				printer.print(positions[j] == i ? 'Q' : ' ');
			}
			if (i < size - 1) {
				printer.println();
				printer.println(divider);
			}
		}
		return stringWriter.toString();
	}
}
