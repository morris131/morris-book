package jvm;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 
 * @author lian.chen
 * @createTime 2016年9月8日 下午1:31:12
 */
public class Test {
	
	
	public static <T> T test(T t, Map<String,Object> map) throws Exception {
		
		
		Field[] fields = t.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			Object value = map.get(field.getName());
			
			field.set(t, value);
			
		}
		
		
		
		return t;
	}
	
	public static void main(String[] args) throws Exception {
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "aaa");
		map.put("age", 12);
		Person person = test(new Person(), map );
		
		
		System.out.println(person.getName());
		System.out.println(person.getAge());*/
		
		Byte byte1 = -128;
	}

}


class Person {
	
	String name;
	
	Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
	
}
