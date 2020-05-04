final class Protocol {   //상속할 수 없음. extends불가 
	public static final String ENTER ="100";
	public static final String EXIT ="200";
	public static final String SEND_MESSAGE="300";
}
//split으로 100:1,1로 보내서 ":"으로 나눠준다.
//이런식으로 앞에 100이라는 좌표 프로토콜을 정해주면 1,1이 좌표임을 알 수 있고, 
//
