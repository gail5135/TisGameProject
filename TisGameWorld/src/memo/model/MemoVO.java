package memo.model;
import java.io.Serializable;
/* sw아키텍쳐 중
 * - Domain계층에 해당한다.
 * - 데이터베이스나 화면 계층에서 받아온 값을 Object화 하여
 * 담고 있는 객체
 * - VO(Value Object) 또는 DTO(Data Transfer Object)객체 라고 함
 * - 보통 VO객체들은 직렬화를 구현함
 * 직렬화는 객체를 일려로 나열할 수 있음을 쵸시하는 것
 * 네트워크로 객체를 전송하고자 할 때 직렬화가 되어있지 않으면 전송할 수 없음
 * */
import java.sql.Date;

public class MemoVO implements Serializable{
	
	private int idx;
	private String name;
	private String msg;
	private java.sql.Date wdate;
	
	//생성자 오버로드
	public MemoVO() {//-기본생성자는 필수
		this(0,null,null,null);//-초기화
	}//default 생성자---

	public MemoVO(int idx, String name, String msg, Date wdate) {//-기본생성자 오버로드
		super();
		this.idx = idx;
		this.name = name;
		this.msg = msg;
		this.wdate = wdate;
	}//인자 생성자----

	//setter, getter
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public java.sql.Date getWdate() {
		return wdate;
	}

	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}

	//toString() 오버라이드, 재정의하지 않으면 MemoVO@abd123
	@Override
	public String toString() {
		return "MemoVO [idx=" + idx + ", name=" + name + ", msg=" + msg + ", wdate=" + wdate + "]";
	}//-테스트 용도로 사용
	
	
	
	
	
	

}
