package lyh.com.AStar;

public class Map {
	//采用二维数组表示地图坐标，初始为默认地图
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
	//地图实例
	Node[][] map;
	//地图的长宽
	int length,width;
	
	public Map() {
		//默认情况下，地图的长宽均为10
		this.length = 10;
		this.width = 10;
		//在默认构造函数中，完成对地图的实例化（使用默认的abstractMap）
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
