
public class MyList<T> {
	private int capacity = 10;
	private T[] array;
	private T[] tempArray;
	
	public MyList() {
		array = (T[]) new Object[capacity];
	} 
	
	public MyList(int capacity) {
		this.capacity = capacity;
		array = (T[]) new Object[capacity];
	}
	
	public int size() {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null)
				return i;
		}
		return capacity;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public void add(T data) {
		if(array[array.length-1] != null) {
			capacity += 10;
			tempArray = (T[]) new Object[capacity];
			for(int i = 0; i < array.length; i++) {
				tempArray[i] = array[i];
			}
			array = tempArray;
			tempArray = null;
		}
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				array[i] = data;
				break;
			}
		}
	}
	
	public T get(int index) {
		if(index > capacity || index < 1) {
			return null;
		}
		return (T) array[index - 1];
	}
	
	public void remove(int index) {
		if(index > capacity || index < 1) {
			System.out.println("Invalid index!");
			return;
		}
		T temp = array[array.length - 1]; 
		for(int i = 0; i < array.length - 1; i++) {
			if(i >= index-1) {
				array[i] = array[i+1];
			}
		}
		array[array.length - 2] = temp;
		array[array.length - 1] = null;
	}
	
	public void set(int index, T data) {
		if(index > capacity || index < 1) {
			System.out.println("Invalid index!");
			return;
		}
		
		for(int i = 0; i < array.length; i++) {
			if(i == index-1) {
				array[i] = data;
			}
		}
	}
	
	public String toString() {
		String str = "[";
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				str += array[i];
				str += ",";	
			}
			
		}
		str = str.substring(0, str.length()-1);
		str += "]";
		return str;
	}
	
	public int indexOf(T data) {
		for(int i = 0; i < array.length; i++) {
			if(data == array[i]) {
				return i+1;
			}
		}
		return -1;
	}
	
	public int lastIndexOf(T data) {
		int index = 0;
		for(int i = array.length-1; i > -1; i--) {
			index++;
			if(data == array[i]) {
				return index;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null)
				return false;
		}
		return true;
	}
	
	public T[] toArray() {
		return array;
	}
	
	public void clear() {
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null)
				array[i] = null;
		}
	}
	
	public MyList<T> sublist(int start, int finish) {
		MyList<T> newArray = new MyList<>();
		for(int i = start; i <= finish; i++) {
			newArray.add(array[i]);
		}
		return newArray;
	}
	
	public boolean contains(T data) {
		for(T i: array) {
			if(i == data)
				return true;
		}
		return false;
	}
}
