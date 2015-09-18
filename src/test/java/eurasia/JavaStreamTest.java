package eurasia;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.threem.eurasia.sample.Employee;
import com.threem.eurasia.sample.Person;

public class JavaStreamTest {

	@Test
	public void sampleStreamBefore() {
		
		int sum = 0;
		int count = 0;
		
		Collection<Employee> emps = new ArrayList<Employee>();
		
		Employee emp1 = new Employee("David", 100);
		Employee emp2 = new Employee("Bob", 200);
		
		emps.add(emp1);
		emps.add(emp2);
		
		for (Employee employee : emps) {
			if (employee.getSalary() > 90) {
				sum += employee.getSalary();
				count++;
			}
		}

		double avg = (double)sum/count;
		System.out.println(avg);
		
	}
	
	@Test
	public void sampleStreamAfter() {
		
		Collection<Employee> emps = new ArrayList<Employee>();
		
		Employee emp1 = new Employee("David", 100);
		Employee emp2 = new Employee("Bob", 200);
		emps.add(emp1);
		emps.add(emp2);
		
		OptionalDouble avgOpt = emps.stream()
					.filter(x -> x.getSalary() > 90)
					.mapToInt(x -> x.getSalary())
					.average();
		
		double avg = avgOpt.getAsDouble();
		
		System.out.println(avg);
		
	}
	
	@Test
	public void makeStreamTest() {

		int[] aaa = {1,2,3};
		
		int count = (int) Arrays.stream(aaa)
				.filter(x-> x > 2)
				.count();
		
		System.out.println(count);
		
	}
	
	@Test
	public void makeStreamTest2() {

		Random random = new Random();
		int count = (int) random.ints().filter(x -> x > 10)
		.count();
		
		
		System.out.println(count);
		
		
	}
	
	
	@Test
	public void makeStreamTest3() {

		int[] aaa = {1,2,3,3,4};
		
		int count = (int) Arrays.stream(aaa)
				.skip(1)
				.distinct()
				.filter(x-> x > 0)
				.count();
		
		System.out.println(count);
		
	}
	
	@Test
	public void makeStreamTest4() {

		int[] exampleArray = {1,2,3};
		
		int count = (int) Arrays.stream(exampleArray)
				.limit(1)
				.filter(x-> x > 1)
				.count();
		
		System.out.println(count);
	
	}

	@Test
	public void makeStreamTest5() {

		Path path = Paths.get("src/test/resources/apache.log");
		
	
	}
	
	@Test
	public void makeStreamTest6() {

		int[] numberArray = {1,1,3};
		
		int count = (int) Arrays.stream(numberArray)
				.filter(x-> x > 0)
				.distinct()
				.count();
		
		System.out.println(count);
	
	}
	
	@Test
	public void makeStreamTest7() {

		int[] aaa = {3,2,1};
		
		int [] bbb = Arrays.stream(aaa)
				.sorted()
				.filter(x-> x > 0)
				.toArray();
		

		for (int i : bbb) {
		System.out.println(i);
		}
		
		
	}

	@Test
	public void makeStreamTest_toSet() {
		
		Collection<Employee> emps = new ArrayList<Employee>();
		
		Employee emp1 = new Employee("Bob", 100);
		Employee emp2 = new Employee("Tom", 200);
		
		emps.add(emp1);
		emps.add(emp2);

		Set<Employee> setEmlpSet = emps.stream()
				.filter(x -> x.getSalary() > 90)
				.collect(Collectors.toSet());
		
		for (Employee employee : setEmlpSet) {
			System.out.println(employee.getSalary());
		}
		
	}


	@Test
	public void makeStreamTest_filterAndMap() {

		Stream<Integer> filteredStream = Arrays.asList(1,2,3,4,5)
				.stream()
				.filter(x -> x>2);
	
		Stream<Integer> doubledStream = filteredStream.map(x -> x*2);
		
		System.out.println(doubledStream.count());
		
		List<Integer> doubledList = doubledStream.collect(Collectors.toList());
		
		for (Integer integer : doubledList) {
			
			System.out.println(integer);
			
		}
		
	}
	
	@Test
	public void makeStreamTest_filterAndMap2() {

		Stream<Integer> filteredStream = Arrays.asList(1,2)
				.stream()
				;
	
		Stream<Integer> doubledStream = filteredStream.map(x -> x*2);

		int count = doubledStream.reduce(0, Integer::sum);
		
		System.out.println(count);
		
		
	}
	
	@Test
	public void makeStreamTest_filterAndMap3() {

		Stream<Integer> filteredStream = Arrays.asList(1,2)
				.stream()
				;
	
		Stream<Integer> doubledStream = filteredStream.map(x -> x*2);

		List<Integer> list = doubledStream.collect(Collectors.toList());

		for (Integer integer : list) {
			System.out.println(integer);
		}
		
		
	}
	
	@Test
	public void makeStreamTest_IntStream_Range() {

		
		IntStream stream = IntStream.range(1, 100)
			.filter(x -> x % 11 == 0)
			.map(x -> x*2)
			.skip(5)
			.limit(2)
			;
		
		int[] aaa = stream.toArray();
		
		for (int i : aaa) {
			System.out.println(i);
		}
		
	}
	
	@Test
	public void makeStreamTest_IntStream_reduce() {

		IntStream filteredStream = IntStream.range(1, 100)
		.filter(x -> x % 10 == 0)
		.map(x -> x);
		
		int [] aaa = filteredStream.toArray();
		
		for (int i : aaa) {
			System.out.println(i);
			
		}
		
		OptionalInt sum = IntStream.range(1, 100)
			.filter(x -> x % 10 == 0)
			.map(x -> x)
			.reduce(Integer::sum)
			;

		System.out.println(sum.getAsInt());
		
	}

	@Test
	public void makeStreamTest_기존_foreach() {

		List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara");
	
		 friends.forEach(new Consumer<String>() {
			   public void accept(final String name) {
			     System.out.println(name);
			   }
			 });
	
	}
	
	@Test
	public void makeStreamTest_람다를이용한_foreach() {

		//람다로 변경 
		List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara");
		friends.forEach((final String x) -> System.out.println(x));
	
		//축약형 
		friends.forEach(x -> System.out.println(x));
		
	}
	
	@Test
	public void makeStreamTest_기존_대문자변경() {

		List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara");

		List<String> uppercaseNames = new ArrayList<String>();
		
		for (String name : friends) {
		   uppercaseNames.add(name.toUpperCase());  
		}

		for (String string : uppercaseNames) {
			System.out.println(string);
		}
		
	}
	
	@Test
	public void makeStreamTest_람다를이용한_대문자변경() {
		List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara");
		friends.stream().map(name -> name.toUpperCase()).forEach(name -> System.out.println(name));;
		
	}
	
	@Test
	public void makeStreamTest_람다를이용한_대문자변경_메서드레퍼런() {

		List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara");

		//메서도 레퍼런스 이용
		friends.stream().map(String::toUpperCase).forEach(name -> System.out.println(name));
		
	}
	
	@Test
	public void makeStreamTest_람다를이용한_K나Y로시작하는이름선택() {

		List<String> friends = Arrays.asList("Kim", "Jang", "Choi", "Yun", "Lee", "Kity");

		final Predicate<String> startsWithK = name -> name.startsWith("K");
		final Predicate<String> startsWithY = name -> name.startsWith("Y");

		final long countFriendsStartK = friends.stream().filter(startsWithK).count();
		final long countFriendsStartY = friends.stream().filter(startsWithY).count();
		
		System.out.println(countFriendsStartK);
		System.out.println(countFriendsStartY);
		
	}
	
	@Test
	public void makeStreamTest_람다를이용한_K나Y로시작하는이름선택_리팩토링1() {

		List<String> friends = Arrays.asList("Kim", "Jang", "Choi", "Yun", "Lee", "Kity");

	
		
		 // 렉시컬 스코프를 이용한 중복 제거
		final long countFriendsStartK = friends.stream().filter(checkIfStartsWith("K")).count();
		final long countFriendsStartY = friends.stream().filter(checkIfStartsWith("Y")).count();
		
		System.out.println(countFriendsStartK);
		System.out.println(countFriendsStartY);
		
	}
	
	final Predicate<String> checkIfStartsWith(final String letter) {
		return name -> name.startsWith(letter);
	}
	
	@Test
	public void makeStreamTest_람다를이용한_K나Y로시작하는이름선택_리팩토링2() {

		List<String> friends = Arrays.asList("Kim", "Jang", "Choi", "Yun", "Lee", "Kity");

		final Function<String, Predicate<String>> startsWithLetter = 
				letter -> name -> name.startsWith(letter);

		final long countFriendsStartK = friends.stream().filter(startsWithLetter.apply("K")).count();
		final long countFriendsStartY = friends.stream().filter(startsWithLetter.apply("Y")).count();
		
		System.out.println(countFriendsStartK);
		System.out.println(countFriendsStartY);
		
	}
	
	@Test
	public void 주어진_문자로시작하는첫번째이름찾기() {
		// before
		List<String> friends = Arrays.asList("Kim", "Jang", "Choi", "Yun", "Lee", "Kity");
		
		String startingLetter = "L";
	    String foundName = null;

	    for (String name : friends) {
	    	if (name.startsWith(startingLetter)) {
	    		foundName = name;
	    	System.out.println(foundName);
	    	break;
	    	}
		}
	    
	 }
		

	@Test
	public void 주어진_문자로시작하는첫번째이름찾기2() {
		// before
		List<String> friends = Arrays.asList("Kim", "Jang", "Choi", "Yun", "Lee", "Kity");
		
		String startingLetter = "L";
	
		// 람다 표현식과 Optional 클래스를 이용
	    final Optional<String> foundName = friends.stream()
	    			.filter(name -> name.startsWith(startingLetter))
	    			.findFirst();
	
	    if (foundName.isPresent()) {
	    	System.out.println(foundName.get());
	    }
	
	}
	
	@Test
	public void mapToInt_APi() {
		// before
		List<String> friends = Arrays.asList("Kim", "Jang", "Choi", "Yun", "Lee", "Kity");
		
		int [] lengthArray = friends.stream().mapToInt(name -> name.length()).toArray();
		
		for (int i : lengthArray) {
			System.out.println(i);
		}
		
		int totalNameLength = friends.stream().mapToInt(name -> name.length()).sum();
	
	}
	
	@Test
	public void 전체문자열길이_구하기() {
		// before
		List<String> friends = Arrays.asList("Kim", "Jang", "Choi", "Yun", "Lee", "Kity");
		
		System.out.println(friends.stream().mapToInt(name -> name.length()).sum());
	
	}

	@Test
	public void 나이별로_grouping() {
		
		final List<Person> people = Arrays.asList(
				 new Person("John", 20),
				 new Person("Sara", 21),
				 new Person("Jane", 21), 
				 new Person("Greg", 35));

		 Map<Integer, List<Person>> peopleByAge = people.stream().collect(Collectors.groupingBy(Person::getAge));
		 // Grouped by age: [35=[Greg - 35], 20=[John - 20], 21=[Sara - 21, Jane - 21]] 


		 List<Person> person21 = peopleByAge.get(21);
		 
		 for (Person person : person21) {
			System.out.println("name : " + person.getName() + " age :" + person.getAge());
		 }
			
		 
	}
	
	@Test
	public void range_Stream() {
		IntStream stream = IntStream.range(1, 100);
		System.out.println(stream.reduce(Integer::sum).getAsInt());
	}
	
	@Test
	public void rangeClose_Stream() {
		IntStream stream = IntStream.rangeClosed(1, 101);
		System.out.println(stream.reduce(Integer::sum).getAsInt());
		
		BiConsumer<String, String> bigConsumer = (t, u) -> System.out.println(t + u);
	}
	

}
