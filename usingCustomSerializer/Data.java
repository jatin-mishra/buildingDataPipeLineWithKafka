import java.util.Date;

public class Data{
	private int id;
	private String name;
	private int age;
	private Date date;

	public Data(int id,String name,int age,Date date){
		this.id = id;
		this.name=name;
		this.age=age;
		this.date = date;
	}

	public int getId(){
		return this.id;
	}

	public String getName(){
		return this.name;
	}

	public int getAge(){
		return this.age;
	}

	public Date getDate(){
		return this.date;
	}
}