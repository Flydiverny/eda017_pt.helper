package se.markusmaga.lth.pt.helper.options;

public class Option<T> {
	private String key;
	private String desc;
	private T value;
	
	public Option(String key, String desc, T value) {
		this.key = key;
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
}