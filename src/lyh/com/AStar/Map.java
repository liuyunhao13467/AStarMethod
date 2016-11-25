package lyh.com.AStar;

public class Map {
	//���ö�ά�����ʾ��ͼ���꣬��ʼΪĬ�ϵ�ͼ
	int[][] abstractMap = {
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,3,0,0,0,0,0},
			{0,0,1,0,3,0,2,0,0,0},
			{0,0,0,0,3,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}
			};
	//��ͼʵ��
	Node[][] map;
	//��ͼ�ĳ���
	int length,width;
	
	public Map() {
		//Ĭ������£���ͼ�ĳ����Ϊ10
		this.length = 10;
		this.width = 10;
		//��Ĭ�Ϲ��캯���У���ɶԵ�ͼ��ʵ������ʹ��Ĭ�ϵ�abstractMap��
		for(int i = 0; i < 10; ++i){
			for(int j = 0; j < 10; ++j){
				Node tmpNode = new Node();
				
				switch(abstractMap[i][j]){
					case 0:
						tmpNode.setNodeTag(Tag.WAY);
						break;
					case 1:
						tmpNode.setNodeTag(Tag.START);
						break;
					case 2:
						tmpNode.setNodeTag(Tag.END);
						break;
					case 3:
						tmpNode.setNodeTag(Tag.WALL);
						break;
					default:
						break;
				}
				tmpNode.x = i;
				tmpNode.y = j;
				this.map[i][j] = tmpNode;
			}
		}
	}

	public int[][] getAbstractMap() {
		return abstractMap;
	}

	public void setAbstractMap(int[][] abstractMap) {
		this.abstractMap = abstractMap;
	}

	public Node[][] getMap() {
		return map;
	}

	public void setMap(Node[][] map) {
		this.map = map;
	}
	
}
