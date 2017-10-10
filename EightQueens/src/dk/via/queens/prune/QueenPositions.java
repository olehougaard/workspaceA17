package dk.via.queens.prune;

import java.io.PrintWriter;
import java.io.StringWriter;

public class QueenPositions {
	private int[] positions;
	private int size;
	private int rows;
	
	public QueenPositions(int size, int rows) {
		this.size = size;
		this.rows = rows;
		positions = new int[size];
	}
	
	public boolean hasMore() {
		return size > 0 && positions[size-1] < rows;
	}
	
	public void next() {
		positions[size-1]++;
	}
	
	public QueenPositions extend() {
		QueenPositions ext = new QueenPositions(size + 1, rows);
		System.arraycopy(positions, 0, ext.positions, 0, positions.length);
		return ext;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getPosition(int i) {
		return positions[i];
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
