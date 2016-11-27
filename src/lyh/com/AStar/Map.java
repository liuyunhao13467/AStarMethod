package lyh.com.AStar;

import java.util.ArrayList;
import java.util.List;

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
	//openlist
	List<Node> openList;
	//closelist
	List<Node> closeList;
	
	/**
	 * ��openlist��Ѱ��Fֵ����Node
	 * @param Map map 
	 * @return Node nodeWithLargestValueOfF
	 */
	Node findTheNodeWithLargestValueOfF(Map map){
		Node nodeWithLargestValueOfF = new Node();
		nodeWithLargestValueOfF.setF(-1);
		for(Node tmpNode:map.getOpenList()){
			if(tmpNode.getF() > nodeWithLargestValueOfF.getF()){
				nodeWithLargestValueOfF = tmpNode;
			}
		}
		Node largestNode = new Node(nodeWithLargestValueOfF);
		return largestNode;
	}
	
	/**
	 * Ĭ�Ϲ��캯��
	 */
	public Map() {
		//Ĭ������£���ͼ�ĳ����Ϊ10
		this.length = 10;
		this.width = 10;
		//��ʼ��open��close����list
		openList = new ArrayList<Node>();
		closeList = new ArrayList<Node>();
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
	
	/**
	 * getter & setter
	 */
	public List<Node> getOpenList() {
		return openList;
	}

	public void setOpenList(List<Node> openList) {
		this.openList = openList;
	}

	public List<Node> getCloseList() {
		return closeList;
	}

	public void setCloseList(List<Node> closeList) {
		this.closeList = closeList;
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
