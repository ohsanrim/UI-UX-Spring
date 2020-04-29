import java.io.*;
class ObjectMain 
{
	public static void main(String[] args) throws ClassNotFoundException
	{	
		PersonDTO dto =new PersonDTO("홍길동", 25, 185.4);
		/*
		try{
			ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("Result2.txt"));
			oos.writeObject(dto);
			oos.close();
		}catch(IOException e){ }
		*/
		try{
		ObjectInputStream ois= new ObjectInputStream(new FileInputStream("Result2.txt"));
		PersonDTO dto2= (PersonDTO)ois.readObject();
		System.out.println("이름= "+dto2.getName());
		System.out.println("나이= "+dto2.getAge());
		System.out.println("키= "+dto2.getHeight());
		ois.close();
		
		} catch(IOException e){}
	}
}
