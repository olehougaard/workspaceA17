package dk.via.trees;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class HeapTest {
	private static Random random = new Random();

	private static Set<Integer> randomTestData() {
		Set<Integer> data = new HashSet<>();
		int size = random.nextInt(50) + 1;
		while(data.size() < size) 
			data.add(random.nextInt());
		return data;
	}
}
