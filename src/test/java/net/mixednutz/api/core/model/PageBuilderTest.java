package net.mixednutz.api.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.mixednutz.api.model.IPageRequest.Direction;

public class PageBuilderTest {
	
	List<MockElement> elementsAsc = new ArrayList<MockElement>();
	List<MockElement> elementsDesc = new ArrayList<MockElement>();
	List<MockElement> oddElements = new ArrayList<MockElement>();
	List<MockElement> evenElements = new ArrayList<MockElement>();
	
	@Before
	public void setup() {
		for (int i = 1; i<=10; i++) {
			MockElement e = new MockElement(i);
			elementsAsc.add(e);
			if (i%2==0) {
				evenElements.add(e);
			} else {
				oddElements.add(e);
			}
		}
		elementsDesc.addAll(elementsAsc);
		Collections.reverse(elementsDesc);
		Collections.reverse(oddElements);
		Collections.reverse(evenElements);
	}

	@Test
	public void testDescending() {
		PageRequest<Integer> firstQuery = PageRequest.first(4, Direction.LESS_THAN, Integer.class);
		
		List<MockElement> mockResults = elementsDesc.subList(0, 4);
		
		Page<MockElement, Integer> results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setDescending()
			.setPageRequest(firstQuery)
			.setTokenCallback(MockElement::getId)
			.build(); 
		
		//Expect 10,9,8,7
		assertEquals(10, results.getItems().get(0).id);
		assertEquals(4, results.getItems().size());
		assertEquals(7, results.getItems().get(3).id);
		assertNull(results.getPageRequest().getStart());
		//Next page starts at 7
		assertEquals((Integer)7, results.getNextPage().getStart());
		assertEquals(Direction.LESS_THAN, results.getNextPage().getDirection());
		//Reverse page starts at 10
		assertEquals((Integer)10, results.getReversePage().getStart());
		assertEquals(Direction.GREATER_THAN, results.getReversePage().getDirection());
		
		//Second to Last page
		mockResults = elementsDesc.subList(8, 10);
		
		results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setDescending()
			.setPageRequest(firstQuery)
			.setTokenCallback(MockElement::getId)
			.build(); 
		
		//Expect 2, 1
		assertEquals(2, results.getItems().get(0).id);
		assertEquals(2, results.getItems().size());
		assertEquals(1, results.getItems().get(1).id);
		assertNull(results.getPageRequest().getStart());
		//Next page starts at 1
		assertEquals((Integer)1, results.getNextPage().getStart());
		assertEquals(Direction.LESS_THAN, results.getNextPage().getDirection());
		//Reverse page starts at 2
		assertEquals((Integer)2, results.getReversePage().getStart());
		assertEquals(Direction.GREATER_THAN, results.getReversePage().getDirection());
		
		//Last page
		mockResults = Collections.emptyList();
		
		results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setDescending()
			.setPageRequest(firstQuery)
			.setTokenCallback(MockElement::getId)
			.build(); 
		
		//Expect Empty
		assertNull(results.getPageRequest().getStart());
		assertFalse(results.hasNext());
		assertFalse(results.hasReverse());
	}
	
	@Test
	public void testAscending() {
		PageRequest<Integer> firstQuery = PageRequest.first(4, Direction.LESS_THAN, Integer.class);
		
		List<MockElement> mockResults = elementsAsc.subList(0, 4);
		
		Page<MockElement, Integer> results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setAscending()
			.setPageRequest(firstQuery)
			.setTokenCallback(MockElement::getId)
			.build(); 
		
		//Expect 1,2,3,4
		assertEquals(1, results.getItems().get(0).id);
		assertEquals(4, results.getItems().size());
		assertEquals(4, results.getItems().get(3).id);
		assertNull(results.getPageRequest().getStart());
		//Next page starts at 4
		assertEquals((Integer)1, results.getNextPage().getStart());
		assertEquals(Direction.LESS_THAN, results.getNextPage().getDirection());
		//Reverse page starts at 1
		assertEquals((Integer)4, results.getReversePage().getStart());
		
		//Second to Last page
		mockResults = elementsAsc.subList(8, 10);
		
		results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setAscending()
			.setPageRequest(firstQuery)
			.setTokenCallback(MockElement::getId)
			.build(); 
		
		//Expect 9,10
		assertEquals(9, results.getItems().get(0).id);
		assertEquals(2, results.getItems().size());
		assertEquals(10, results.getItems().get(1).id);
		assertNull(results.getPageRequest().getStart());
		//Next page starts at 10
		assertEquals((Integer)9, results.getNextPage().getStart());
		assertEquals(Direction.LESS_THAN, results.getNextPage().getDirection());
		//Reverse page starts at 9
		assertEquals((Integer)10, results.getReversePage().getStart());
		assertEquals(Direction.GREATER_THAN, results.getReversePage().getDirection());
		
		//Last page
		mockResults = Collections.emptyList();
		
		results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setAscending()
			.setPageRequest(firstQuery)
			.setTokenCallback(MockElement::getId)
			.build(); 
		
		//Expect Empty
		assertNull(results.getPageRequest().getStart());
		assertFalse(results.hasNext());
		assertFalse(results.hasReverse());
	}
	
	@Test
	public void testReSortAndTrim() {
		PageRequest<Integer> firstQuery = PageRequest.first(4, Direction.LESS_THAN, Integer.class);
		
		//Unsorted list of 8 elements
		List<MockElement> mockResults = new ArrayList<>(oddElements.subList(0, 4));
		mockResults.addAll(evenElements.subList(0, 4));
		
		Comparator<MockElement> comparator = new Comparator<MockElement>(){

			@Override
			public int compare(MockElement o1, MockElement o2) {
				return -Integer.compare(o1.id, o2.id);
			}};
		
		Page<MockElement, Integer> results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setDescending()
			.setPageRequest(firstQuery)
			.setTrimToPageSize(true)
			.setReSortComparator(comparator)
			.setTokenCallback(MockElement::getId)
			.build(); 
		
		//Expect 10,9,8,7
		assertEquals(10, results.getItems().get(0).id);
		assertEquals(4, results.getItems().size());
		assertEquals(7, results.getItems().get(3).id);
		assertNull(results.getPageRequest().getStart());
	}
	
	
	static class MockElement {
		int id;

		public MockElement(int id) {
			super();
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
	}

}
