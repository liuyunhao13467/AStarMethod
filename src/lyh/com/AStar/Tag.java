package lyh.com.AStar;

/*
 * �����������ʾ����������
 */
public enum Tag {
	WAY("way",0), 
	START("start",1), 
	END("end",2), 
	WALL("wall",3);
	
	private String name ;
    private int index ;
     
    private Tag( String name , int index ){
        this.name = name ;
        this.index = index ;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
