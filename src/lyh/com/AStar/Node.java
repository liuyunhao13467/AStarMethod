package lyh.com.AStar;

import java.util.ArrayList;
import java.util.List;

public class Node {
	//һ���ڵ��а�������Ϣ
	public int x,y;//�ýڵ������
	private int G;//����� A���ŵ���÷�������ɵ�·���ƶ�������Ĵ���
	private int H;//��ָ���ķ����ƶ����յ� B �Ĺ���ɱ���ֻ�ܺ����ƶ�������б���ƶ�
	private int F;//F=G+H
	private Node father;//һ��ָ�򸸽ڵ������
	//����ö����������ʾͨ·����㡢�յ���ϰ���
	public Tag nodeTag;
	
	/**
	 * ���εĹ��캯��
	 */
	public Node(int x, int y, int g, int h, int f, Node father, Tag nodeTag) {
		super();
		this.x = x;
		this.y = y;
		G = g;
		H = h;
		F = f;
		this.father = father;
		this.nodeTag = nodeTag;
	}

	/**
	 *���ι��캯��2 
	 */
	public Node(Node node){
		this.x = node.x;
		this.y = node.y;
		G = node.getG();
		H = node.getH();
		F = node.getF();
		this.father = node.getFather();
		this.nodeTag = node.nodeTag;
	}
	
	/**
	 * Ĭ�Ϲ��캯��
	 */
	public Node() {
		super();
	}

	/**
	 * 1. �����Լ����ӽڵ㣬���븸�ڵ���������ͼ������һ���ӽڵ�ļ���
	 * @param Node father, int[][] map
	 * @return List<Node>
	 */
	List<Node> findChildrenNodes(Node mySelf, Map map){
		
		//��1����ȡ��father�����Ϣ���Լ���ͼ�Ĵ�����Ϣ
		int myX = mySelf.x;//��ȡ����ĺ�����(i)����Ӧlength
		int myY = mySelf.y;//��ȡ�����������(j)����Ӧwidth
		int mapLength = map.length;//��ȡ��ͼ�ĳ���
		int mapWidth = map.width;//��ȡ��ͼ�Ŀ��
		
		//��2�������ǰ����ǽ������father�������Խ�磬��˵�������д���
		if(Node.isNodeIllegal(mySelf, map)){
			System.err.println("��ǰ��Ƿ���");
			return null;
		}
		
		//��3���ҵ����ܵ��ڽӵ�
		List<Node> possibleAdjPoints = new ArrayList<Node>();//���list�洢���ܵ��ڽӵ�
		//��ȡ��father��Χ��8����
		Node adj1 = map.map[myX][myY+1];
		Node adj2 = map.map[myX+1][myY+1];
		Node adj3 = map.map[myX+1][myY];
		Node adj4 = map.map[myX+1][myY-1];
		Node adj5 = map.map[myX][myY-1];
		Node adj6 = map.map[myX-1][myY-1];
		Node adj7 = map.map[myX-1][myY];
		Node adj8 = map.map[myX-1][myY+1];
		//��������㣺
		if(((myX - 1) < 0) && ((myY - 1) < 0)){
			//���Ͻǣ��жϹ����ǣ�����õ������Ϊ��i��j������ô���ܵ��ڽӵ�Ϊ{��i��j+1����i+1��j+1����i+1��j��}
			//�ȼ�����ܵ��ڽӵ㼯����
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX - 1) < 0) && ((myY + 1) > (mapWidth - 1))){
			//���Ͻǣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ��ڽӵ�Ϊ��{(i,j-1)(i+1,j-1)(i+1,j)}
			//�ȼ�����ܵ��ڽӵ㼯����
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX - 1) < 0) && ((myX + 1) <= (mapLength - 1)) && ((myY - 1) >= 0) && ((myY + 1) <= (mapWidth - 1))){
			//�ϱߣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ��ڽӵ�Ϊ��{(i,j-1)(i+1,j-1)(i+1,j)(i+1,j+1)(i,j+1)}
			//�ȼ�����ܵ��ڽӵ㼯����
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myY + 1) > (mapWidth - 1))){
			//���½ǣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ��ڽӵ�Ϊ��{(i-1,j-i)(i-1,j)(i,j-1)}
			//�ȼ�����ܵ��ڽӵ㼯����
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myY + 1) > (mapWidth - 1)) && ((myY - 1) >= 0) && ((myX - 1) >= 0) && ((myX + 1) <= (mapLength - 1))){
			//�ұߣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ��ڽӵ�Ϊ��{(i-1,j-1)(i-1,j)(i,j-1)(i+1,j-1)(i+1,j)}
			//�ȼ�����ܵ��ڽӵ㼯����
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myY - 1) < 0)){
			//���½ǣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ��ڽӵ�Ϊ��{(i,j+1)(i-1,j+1)(i-1,j)}
			//�ȼ�����ܵ��ڽӵ㼯����
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myX - 1) >= 0) && ((myY + 1) <= (mapWidth - 1)) && ((myY - 1) >= 0)){
			//�±ߣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ�children��Ϊ��{(i-1,j-1)(i-1,j)(i-1,j+1)(i,j-1)(i,j+1)}
			//�ȼ�����ܵ��ڽӵ㼯����
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myY - 1) < 0) && ((myY + 1) <= (mapWidth - 1)) && ((myX + 1) <= (mapLength - 1)) && ((myX - 1) >= 0)){
			//��ߣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ��ڽӵ�Ϊ��{(i-1,j)(i-1,j+1)(i,j+1)(i+1,j)(i+1,j+1)}
			//�ȼ�����ܵ��ڽӵ㼯����
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else{
			//����������
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//�Ա��ڵ���ܵ��ڽӽڵ㼯�Ͻ��д���
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}
		
		return possibleAdjPoints;//��ʵ��mySelf��son�ڵ���
	}
	
	/**
	 * 2. ����G��ֵ
	 * @param Node node Ҫ����Gֵ�Ľڵ�
	 * @return int ����Gֵ��ͬʱ��Ҳ��Gֵ�������˱��ڵ��G�ֶ���
	 */
	int calculateTheValueOfG(Node node){
		int myValueOfG = 0;//���ڵ��Gֵ����ʼΪ0
		//�Ȼ�ȡ���ڵ��father
		Node fatherNode = node.getFather();
		if(fatherNode == null){
			System.err.println("�ýڵ�����㡣");
		}
		//�ٻ�ȡfather�ڵ��Gֵ
		int GValueOfFather = fatherNode.getG();
		//�����Լ���Gֵ
		int x = node.x;
		int y = node.y;
		int fatherX = fatherNode.x;
		int fatherY = fatherNode.y;
		//���nodeλ��fatherNode�����������ĸ�λ�ã���ôGֵ��10
		if(((x == (fatherX - 1)) && (y == fatherY)) || ((x == fatherX) && (y == (fatherY + 1))) || ((x == (fatherX + 1)) && (y == fatherY)) || ((x == fatherX) && (y == (fatherY - 1)))){
			myValueOfG = GValueOfFather + 10;
			node.setG(myValueOfG);
		}
		//���nodeλ��fatherNode�����ϡ����ϡ����¡������ĸ�λ�ã���ôGֵ��14
		if(((x == (fatherX - 1)) && (y == (fatherY - 1))) || ((x == (fatherX - 1)) && (y == (fatherY + 1))) || ((x == (fatherX + 1)) && (y == (fatherY + 1))) || ((x == (fatherX + 1)) && (y == (fatherY - 1)))){
			myValueOfG = GValueOfFather + 14;
			node.setG(myValueOfG);
		}
		return myValueOfG;
	}
	
	/**
	 * 3. ����Hֵ
	 * @param Node mySelf, Node endPoint ���ڵ���յ�
	 * @return int ����Hֵ��ͬʱ��Hֵ���������ڵ��H�ֶ���
	 */
	int calculateTheValueOfH(Node mySelf, Node endPoint){
		//�Ȼ�ȡ���ڵ��������յ�����
		int x = mySelf.x;
		int y = mySelf.y;
		int endX = endPoint.x;
		int endY = endPoint.y;
		int myH = -1;//Ĭ��Ϊ-1���������-1˵��������ʲô����
		//���㱾�㵽�յ�Ŀ���
		myH = (Math.abs(x - endX) + Math.abs(y - endY))*10 ;
		return myH;
	}
	
	/**
	 * 4. ����Fֵ
	 * @param Node mySelf ���ڵ�
	 * @return int ����Fֵ��ͬʱ��Fֵ���������ڵ��F�ֶ���
	 */
	int calculateTheValueOfF(Node mySelf){
		int G = mySelf.getG();
		int H = mySelf.getH();
		int F = G + H;
		mySelf.setF(F);
		return F;
	}

	/**
	 * 5.�жϵ�ǰ��ĺϷ���
	 * @param Node mySelf, Map map
	 * @return boolean ���ز���ֵ��true��ʾ�Ϸ���false��ʾ�Ƿ�
	 */
	public static boolean isNodeIllegal(Node mySelf, Map map){
		//��ȡ���ڵ�ĺ�������
		int myX = mySelf.x;
		int myY = mySelf.y;
		//��ȡmap�ĳ��Ϳ��߽磩
		int mapLength = map.length;//length��Ӧx
		int mapWidth = map.width;//width��Ӧy
		//�жϺϷ���
		if(mySelf.getNodeTag() == Tag.WALL){
			System.err.println("��ǰ����WALL���д���\n");
			return false;
		}else if((myX < 0) || (myX > mapLength-1)){
			System.err.println("��ǰ���Ѿ�Խ�磬�д���\n");
			return false;
		}else if((myY < 0) || (myY > mapWidth-1)){
			System.err.println("��ǰ���Ѿ�Խ�磬�д���\n");
			return false;
		}else{
			System.out.println("��ǰ��������\n");
		}
		return true;
	}
	
	/**
	 * 6.ȥ��AdjPoints�д�����closelist�ĵ���ǷǷ���
	 * @param List<Node> AdjPoints, Map myMap �ڽӵ�list�����ڵ�map
	 * @return ����ֵΪ�գ���������ҪΪ������������������
	 */
	public void washIllegalPointsInAdjPoints(List<Node> AdjPoints, Map myMap){
		System.out.println("washIllegalPointsInAdjPoints: start!\n");
		for(Node tmpCloseNode:myMap.getCloseList()){
			for(Node tmpAdjPoint:AdjPoints){
				//���AdjPoints�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���AdjPoints
				if((tmpCloseNode.x == tmpAdjPoint.x) && (tmpCloseNode.y == tmpAdjPoint.y)){
					AdjPoints.remove(tmpAdjPoint);//��AdjPoints���Ƴ�
				}
				//�ټ����Щ���ܵ��ڽӵ��Ƿ�Ϸ�������ǣ������޳���AdjPoints
				if(Node.isNodeIllegal(tmpAdjPoint, myMap)){
					AdjPoints.remove(tmpAdjPoint);//��AdjPoints���Ƴ�
				}
			}
		}
		System.out.println("washIllegalPointsInAdjPoints: over!\n");
		return ;
	}
	
	/**
	 *7.��possibleAdjPoints�Ĵ�������������������Ϳ��Եõ�����sonPoints
	 *@param Node me, List<Node> myPossibleAdjPoints, Map myMap �����б��ڵ�me�����ڵ�Ŀ��ܵ��ڽӽڵ�ļ��ϣ����б��ڵ����ڵ�map
	 *@return void
	 */
	void handlePossibleAdjPoints(Node me, List<Node> myPossibleAdjPoints, Map myMap){
		//��ϴmyPossibleAdjPoints�д�����closelist�ĵ���ǷǷ��ĵ�
		washIllegalPointsInAdjPoints(myPossibleAdjPoints,myMap);
		//����ЩmyPossibleAdjPoints���뵽openlist�У�ע�⣬���
		for(Node tmpNode:myPossibleAdjPoints){
			Node adjPoint = new Node(tmpNode.x, 
					tmpNode.y, 
					tmpNode.getG(), 
					tmpNode.getH(), 
					tmpNode.getF(), 
					tmpNode.getFather(), 
					tmpNode.nodeTag);
			//����Щ�ڽӵ�ĸ��ڵ�����Ϊme
			adjPoint.setFather(me);
			//���õ����openlist��
			myMap.getOpenList().add(adjPoint);
		}
		//��me����closelist��
		myMap.getCloseList().add(me);
	}
	
	/**
	 * 8.��������keepSearching
	 * @param Map map
	 * @return Node
	 */
	void keepSearching(Map map){
		//��һ������openlist���ҵ�Fֵ��С�ķ��񣨽ڵ㣩
		Node minimumFNode = map.findTheNodeWithMinmumValueOfF(map);
		//�ڶ����������openlist���Ƴ�����ӵ�closelist��
		map.getOpenList().remove(minimumFNode);
		map.getCloseList().add(minimumFNode);
		//������������������ڵĸ��ӣ��ų������зǷ����ӣ����Ϸ��ĸ��Ӽ���openlist�У�����������Ϊ���ڸ��ӵĸ��ڵ�
		List<Node> childrenNodes = findChildrenNodes(minimumFNode, map);
		//���Ĳ������ĳ�����ڽڵ��Ѿ���openlist�У����һ�����ڵ�·���Ƿ����
	}
	
	/**
	 * 9.��һЩ�����openlist��
	 * @param Map map, List<Node> nodeSet
	 * @return null
	 */
	void addNodesToOpenlist(Map map, List<Node> nodeSet){
		for(Node tmpNode : nodeSet){
			map.getOpenList().add(tmpNode);
		}
	}
	
	/**
	 * Ԫ�ص�getter��setter
	 */
	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public Tag getNodeTag() {
		return nodeTag;
	}

	public void setNodeTag(Tag nodeTag) {
		this.nodeTag = nodeTag;
	}

}
