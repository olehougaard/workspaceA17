package dk.via.queens;

import java.io.PrintWriter;
import java.io.StringWriter;

public class QueenPositions {
	private int[] positions;
	private int size;
	
	public QueenPositions(int size) {
		this.size = size;
		positions = new int[size];
	}
	
	public boolean hasMore() {
		return positions[0] < size - 1;
	}
	
	public void next() {
		int i = positions.length - 1;
		while(i > 0 && positions[i] == size - 1)
			i--;
		positions[i]++;
		i++;
		while(i < positions.length) {
			positions[i] = 0;
			i++;
		}
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
