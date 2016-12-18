package lyh.com.AStar;

import java.util.ArrayList;
import java.util.List;

public class Node {
	//一个节点中包含的信息
	public int x,y;//该节点的坐标
	private int G;//从起点 A沿着到达该方格而生成的路径移动本方格的代价
	private int H;//从指定的方格移动到终点 B 的估算成本，只能横竖移动，不可斜角移动
	private int F;//F=G+H
	private Node father;//一个指向父节点的引用
	//采用枚举类型来表示通路、起点、终点和障碍物
	public Tag nodeTag;
	
	/**
	 * 带参的构造函数
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
	 *带参构造函数2 
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
	 * 默认构造函数
	 */
	public Node() {
		super();
	}

	/**
	 * 1. 查找自己的子节点，输入父节点对象、坐标地图，返回一个子节点的集合
	 * @param Node father, int[][] map
	 * @return List<Node>
	 */
	List<Node> findChildrenNodes(Node mySelf, Map map){
		
		//（1）获取到father点的信息，以及地图的大致信息
		int myX = mySelf.x;//获取父点的横坐标(i)，对应length
		int myY = mySelf.y;//获取父点的纵坐标(j)，对应width
		int mapLength = map.length;//获取地图的长度
		int mapWidth = map.width;//获取地图的宽度
		
		//（2）如果当前点是墙，或者father点的坐标越界，则说明程序有错误
		if(Node.isNodeIllegal(mySelf, map)){
			System.err.println("当前点非法！");
			return null;
		}
		
		//（3）找到可能的邻接点
		List<Node> possibleAdjPoints = new ArrayList<Node>();//这个list存储可能的邻接点
		//获取到father周围的8个点
		Node adj1 = map.map[myX][myY+1];
		Node adj2 = map.map[myX+1][myY+1];
		Node adj3 = map.map[myX+1][myY];
		Node adj4 = map.map[myX+1][myY-1];
		Node adj5 = map.map[myX][myY-1];
		Node adj6 = map.map[myX-1][myY-1];
		Node adj7 = map.map[myX-1][myY];
		Node adj8 = map.map[myX-1][myY+1];
		//先找特殊点：
		if(((myX - 1) < 0) && ((myY - 1) < 0)){
			//左上角；判断规则是：如果该点的坐标为（i，j），那么可能的邻接点为{（i，j+1）（i+1，j+1）（i+1，j）}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX - 1) < 0) && ((myY + 1) > (mapWidth - 1))){
			//右上角；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i,j-1)(i+1,j-1)(i+1,j)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX - 1) < 0) && ((myX + 1) <= (mapLength - 1)) && ((myY - 1) >= 0) && ((myY + 1) <= (mapWidth - 1))){
			//上边；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i,j-1)(i+1,j-1)(i+1,j)(i+1,j+1)(i,j+1)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myY + 1) > (mapWidth - 1))){
			//右下角；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i-1,j-i)(i-1,j)(i,j-1)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myY + 1) > (mapWidth - 1)) && ((myY - 1) >= 0) && ((myX - 1) >= 0) && ((myX + 1) <= (mapLength - 1))){
			//右边；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i-1,j-1)(i-1,j)(i,j-1)(i+1,j-1)(i+1,j)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myY - 1) < 0)){
			//左下角；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i,j+1)(i-1,j+1)(i-1,j)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myX - 1) >= 0) && ((myY + 1) <= (mapWidth - 1)) && ((myY - 1) >= 0)){
			//下边；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i-1,j-1)(i-1,j)(i-1,j+1)(i,j-1)(i,j+1)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else if(((myY - 1) < 0) && ((myY + 1) <= (mapWidth - 1)) && ((myX + 1) <= (mapLength - 1)) && ((myX - 1) >= 0)){
			//左边；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i-1,j)(i-1,j+1)(i,j+1)(i+1,j)(i+1,j+1)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}else{
			//其他正常点
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//对本节点可能的邻接节点集合进行处理
			handlePossibleAdjPoints(mySelf,possibleAdjPoints,map);
		}
		
		return possibleAdjPoints;//其实是mySelf的son节点们
	}
	
	/**
	 * 2. 计算G的值
	 * @param Node node 要计算G值的节点
	 * @return int 返回G值，同时，也将G值保存在了本节点的G字段中
	 */
	int calculateTheValueOfG(Node node){
		int myValueOfG = 0;//本节点的G值，初始为0
		//先获取本节点的father
		Node fatherNode = node.getFather();
		if(fatherNode == null){
			System.err.println("该节点是起点。");
		}
		//再获取father节点的G值
		int GValueOfFather = fatherNode.getG();
		//计算自己的G值
		int x = node.x;
		int y = node.y;
		int fatherX = fatherNode.x;
		int fatherY = fatherNode.y;
		//如果node位于fatherNode的上下左右四个位置，那么G值加10
		if(((x == (fatherX - 1)) && (y == fatherY)) || ((x == fatherX) && (y == (fatherY + 1))) || ((x == (fatherX + 1)) && (y == fatherY)) || ((x == fatherX) && (y == (fatherY - 1)))){
			myValueOfG = GValueOfFather + 10;
			node.setG(myValueOfG);
		}
		//如果node位于fatherNode的左上、右上、左下、右下四个位置，那么G值加14
		if(((x == (fatherX - 1)) && (y == (fatherY - 1))) || ((x == (fatherX - 1)) && (y == (fatherY + 1))) || ((x == (fatherX + 1)) && (y == (fatherY + 1))) || ((x == (fatherX + 1)) && (y == (fatherY - 1)))){
			myValueOfG = GValueOfFather + 14;
			node.setG(myValueOfG);
		}
		return myValueOfG;
	}
	
	/**
	 * 3. 计算H值
	 * @param Node mySelf, Node endPoint 本节点和终点
	 * @return int 返回H值，同时将H值保存至本节点的H字段中
	 */
	int calculateTheValueOfH(Node mySelf, Node endPoint){
		//先获取本节点的坐标和终点坐标
		int x = mySelf.x;
		int y = mySelf.y;
		int endX = endPoint.x;
		int endY = endPoint.y;
		int myH = -1;//默认为-1，如果返回-1说明出现了什么错误
		//计算本点到终点的开销
		myH = (Math.abs(x - endX) + Math.abs(y - endY))*10 ;
		return myH;
	}
	
	/**
	 * 4. 计算F值
	 * @param Node mySelf 本节点
	 * @return int 返回F值，同时将F值保存至本节点的F字段中
	 */
	int calculateTheValueOfF(Node mySelf){
		int G = mySelf.getG();
		int H = mySelf.getH();
		int F = G + H;
		mySelf.setF(F);
		return F;
	}

	/**
	 * 5.判断当前点的合法性
	 * @param Node mySelf, Map map
	 * @return boolean 返回布尔值，true表示合法，false表示非法
	 */
	public static boolean isNodeIllegal(Node mySelf, Map map){
		//获取本节点的横纵坐标
		int myX = mySelf.x;
		int myY = mySelf.y;
		//获取map的长和宽（边界）
		int mapLength = map.length;//length对应x
		int mapWidth = map.width;//width对应y
		//判断合法性
		if(mySelf.getNodeTag() == Tag.WALL){
			System.err.println("当前点是WALL，有错误！\n");
			return false;
		}else if((myX < 0) || (myX > mapLength-1)){
			System.err.println("当前点已经越界，有错误！\n");
			return false;
		}else if((myY < 0) || (myY > mapWidth-1)){
			System.err.println("当前点已经越界，有错误！\n");
			return false;
		}else{
			System.out.println("当前点正常。\n");
		}
		return true;
	}
	
	/**
	 * 6.去掉AdjPoints中存在于closelist的点或是非法点
	 * @param List<Node> AdjPoints, Map myMap 邻接点list，所在的map
	 * @return 返回值为空，本函数主要为操作函数，不做返回
	 */
	public void washIllegalPointsInAdjPoints(List<Node> AdjPoints, Map myMap){
		System.out.println("washIllegalPointsInAdjPoints: start!\n");
		for(Node tmpCloseNode:myMap.getCloseList()){
			for(Node tmpAdjPoint:AdjPoints){
				//检查AdjPoints中的元素是否存在于closelist中，如果存在就将其剔除出AdjPoints
				if((tmpCloseNode.x == tmpAdjPoint.x) && (tmpCloseNode.y == tmpAdjPoint.y)){
					AdjPoints.remove(tmpAdjPoint);//从AdjPoints中移除
				}
				//再检查这些可能的邻接点是否合法，如果是，将其剔除出AdjPoints
				if(Node.isNodeIllegal(tmpAdjPoint, myMap)){
					AdjPoints.remove(tmpAdjPoint);//从AdjPoints中移除
				}
			}
		}
		System.out.println("washIllegalPointsInAdjPoints: over!\n");
		return ;
	}
	
	/**
	 *7.对possibleAdjPoints的处理，经过这个函数处理后就可以得到最后的sonPoints
	 *@param Node me, List<Node> myPossibleAdjPoints, Map myMap 参数有本节点me，本节点的可能的邻接节点的集合，还有本节点所在的map
	 *@return void
	 */
	void handlePossibleAdjPoints(Node me, List<Node> myPossibleAdjPoints, Map myMap){
		//清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
		washIllegalPointsInAdjPoints(myPossibleAdjPoints,myMap);
		//将这些myPossibleAdjPoints加入到openlist中，注意，深拷贝
		for(Node tmpNode:myPossibleAdjPoints){
			Node adjPoint = new Node(tmpNode.x, 
					tmpNode.y, 
					tmpNode.getG(), 
					tmpNode.getH(), 
					tmpNode.getF(), 
					tmpNode.getFather(), 
					tmpNode.nodeTag);
			//将这些邻接点的父节点设置为me
			adjPoint.setFather(me);
			//将该点放入openlist中
			myMap.getOpenList().add(adjPoint);
		}
		//将me置于closelist中
		myMap.getCloseList().add(me);
	}
	
	/**
	 * 8.继续搜索keepSearching
	 * @param Map map
	 * @return Node
	 */
	void keepSearching(Map map){
		/*第一步，在openlist中找到F值最小的方格（节点）*/
		Node minimumFNode = map.findTheNodeWithMinmumValueOfF(map);
		
		/*第二步，将其从openlist中移除，添加到closelist中*/
		map.getOpenList().remove(minimumFNode);
		map.getCloseList().add(minimumFNode);
		
		/*第三步， 检查所有与它相邻的方格，忽略其中在closelist中或是不可走(unwalkable)的方格(比如墙，水，或是其他非法地形)，
		 *如果方格不在openlsit中，则把它们加入到openlist中。
		 *把我们选定的方格设置为这些新加入的方格的父亲。*/
		//(1)先找到合法的邻接结点
		List<Node> adjNodes = getAdjNodes(minimumFNode, map);
		//(2)检查相邻节点是否存在于openlist中，得到不存在于openlist中的结点集和存在于openlist中的点集
		List<Node> nodesNotInOpenlist = getNodesNotInOpenlistFromAdjnodes(adjNodes, map.getOpenList());
		List<Node> nodesInOpenlist = getNodesInOpenlistFromAdjnodes(adjNodes, map.getOpenList());
		//(3)将这些节点加入openlist中
		addNodesToOpenlist(map, nodesNotInOpenlist);
		//(4)将这些新加入openlist的节点（nodesNotInOpenlist）的父节点设置为本节点
		setNodesFather(minimumFNode, nodesNotInOpenlist);
		
		/*第四步，如果某个相邻的方格已经在openlist中，则检查这条路径是否更优，
		 *也就是说经由当前方格(我们选中的方格)到达那个方格是否具有更小的G值。
		 *如果没有，不做任何操作。相反，如果G值更小，则把那个方格的父亲设为当前方格(我们选中的方格)，
		 *然后重新计算那个方格的F值和G值。*/
		//(1)找到那些已经存在于openlist中的相邻节点（相对于上一步中那些一开始不存在于openlist中的adjNodes来说的）
		//在上一步中已找到
		//(2)遍历nodesInOpenlist，对其中的每一个节点进行检查
		int cost = 0;
		for(Node tmpNode:nodesInOpenlist){
			//先算出由当前点到被检测点的代价
			cost = calculateTheCostBetweenTwoAdjNodes(tmpNode, minimumFNode);
			//再计算通过当前点到待检测点的G值
			int newG = minimumFNode.getG() + cost;
			//对比新的G值和待检测点的G值
			if(newG < tmpNode.getG()){
				/*如果新的G值比原来的G值小:
				 *则将被检测的点（tmpNode）的father设置为当前选中的方格（minimumFNode），
				 *并且重新计算tmpNode的F&G*/
				//(1)tmpNode的father设置为minimumFNode
				tmpNode.setFather(minimumFNode);
				//(2)重新计算tmpNode的F&G
				tmpNode.setG(newG);
				int newF = newG + tmpNode.getH();
				tmpNode.setH(newF);
			}else{
				//不做任何操作
				continue;
			}
		}
		
		
	}
	
	/**
	 * 9.将一些点加入openlist中
	 * @param Map map, List<Node> nodeSet
	 * @return null
	 */
	void addNodesToOpenlist(Map map, List<Node> nodeSet){
		if(nodeSet.isEmpty()){
			return;
		}
		for(Node tmpNode : nodeSet){
			map.getOpenList().add(tmpNode);
		}
	}
	
	/**
	 * 10.判定一个结点是否在openlist中
	 * @param List<Node> openlist, Node node
	 * @return int
	 */
	int isInOpenlist(List<Node> openlist, Node node){
		//在openlist中检查输入node的出现次数
		int matchTimes = 0;
		for(Node tmpNode:openlist){
			if(isSameNode(tmpNode, node))
				matchTimes++;
		}
		//返回matchTimes,如果这个值为0，那么说明传入的节点不在openlist中；
		//如果为1，则说明已存在与openlist中；
		//如果大于1，则说明openlist的维护出现错误；
		//如果小于1，则说明本函数计算出错。
		return matchTimes;
	}
	
	/**
	 * 11.判断两个Node是否为同一个
	 * @param Node node1, Node node2
	 * @return boolean
	 */
	boolean isSameNode(Node node1, Node node2){
		//可能还要有一个合法性判断？
		if((node1.x == node2.x) && (node1.y == node2.y)){
			return true;
		}
		return false;
	}
	
	/**
	 * 12.获取一个结点的合法的相邻节点
	 * @param Node mySelf, Map map
	 * @return List<Node>
	 */
	List<Node> getAdjNodes(Node mySelf, Map map){
		//（1）获取到本节点点的信息，以及地图的大致信息
		int myX = mySelf.x;//获取父点的横坐标(i)，对应length
		int myY = mySelf.y;//获取父点的纵坐标(j)，对应width
		int mapLength = map.length;//获取地图的长度
		int mapWidth = map.width;//获取地图的宽度
		//（2）如果当前点是墙，或者本节点的坐标越界，则说明程序有错误
		if(Node.isNodeIllegal(mySelf, map)){
			System.err.println("当前点非法！");
			return null;
		}
		//（3）找到可能的邻接点
		List<Node> possibleAdjPoints = new ArrayList<Node>();//这个list存储可能的邻接点
		//获取到father周围的8个点
		Node adj1 = map.map[myX][myY+1];
		Node adj2 = map.map[myX+1][myY+1];
		Node adj3 = map.map[myX+1][myY];
		Node adj4 = map.map[myX+1][myY-1];
		Node adj5 = map.map[myX][myY-1];
		Node adj6 = map.map[myX-1][myY-1];
		Node adj7 = map.map[myX-1][myY];
		Node adj8 = map.map[myX-1][myY+1];
		//先找特殊点：
		if(((myX - 1) < 0) && ((myY - 1) < 0)){
			//左上角；判断规则是：如果该点的坐标为（i，j），那么可能的邻接点为{（i，j+1）（i+1，j+1）（i+1，j）}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}else if(((myX - 1) < 0) && ((myY + 1) > (mapWidth - 1))){
			//右上角；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i,j-1)(i+1,j-1)(i+1,j)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}else if(((myX - 1) < 0) && ((myX + 1) <= (mapLength - 1)) && ((myY - 1) >= 0) && ((myY + 1) <= (mapWidth - 1))){
			//上边；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i,j-1)(i+1,j-1)(i+1,j)(i+1,j+1)(i,j+1)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myY + 1) > (mapWidth - 1))){
			//右下角；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i-1,j-i)(i-1,j)(i,j-1)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}else if(((myY + 1) > (mapWidth - 1)) && ((myY - 1) >= 0) && ((myX - 1) >= 0) && ((myX + 1) <= (mapLength - 1))){
			//右边；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i-1,j-1)(i-1,j)(i,j-1)(i+1,j-1)(i+1,j)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myY - 1) < 0)){
			//左下角；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i,j+1)(i-1,j+1)(i-1,j)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}else if(((myX + 1) > (mapLength - 1)) && ((myX - 1) >= 0) && ((myY + 1) <= (mapWidth - 1)) && ((myY - 1) >= 0)){
			//下边；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i-1,j-1)(i-1,j)(i-1,j+1)(i,j-1)(i,j+1)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}else if(((myY - 1) < 0) && ((myY + 1) <= (mapWidth - 1)) && ((myX + 1) <= (mapLength - 1)) && ((myX - 1) >= 0)){
			//左边；判断规则是：如果该坐标为(i,j)，那么可能的邻接点为：{(i-1,j)(i-1,j+1)(i,j+1)(i+1,j)(i+1,j+1)}
			//先加入可能的邻接点集合中
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}else{
			//其他正常点
			possibleAdjPoints.add(adj1);
			possibleAdjPoints.add(adj2);
			possibleAdjPoints.add(adj3);
			possibleAdjPoints.add(adj4);
			possibleAdjPoints.add(adj5);
			possibleAdjPoints.add(adj6);
			possibleAdjPoints.add(adj7);
			possibleAdjPoints.add(adj8);
			//对本节点可能的邻接节点集合进行处理：清洗myPossibleAdjPoints中存在于closelist的点或是非法的点
			washIllegalPointsInAdjPoints(possibleAdjPoints,map);
		}
		return possibleAdjPoints;
	}
	
	/**
	 *13.检查相邻节点是否存在于openlist中，返回那些不存在于openlist中的结点们
	 *@param List<Node> adjNodes, List<Node> openlist
	 *@return List<Node>
	 */
	List<Node> getNodesNotInOpenlistFromAdjnodes(List<Node> adjNodes, List<Node> openlist){
		//返回值的数据结构
		List<Node> nodesNotInOpenlist = new ArrayList<Node>();
		//查找
		for(Node tmpAdjNode:adjNodes){
			//这里还要加上合法性判断？
			//若该点不存在于openlist中，则加入nodesNotInOpenlist
			if(isInOpenlist(openlist, tmpAdjNode) == 0){
				nodesNotInOpenlist.add(tmpAdjNode);
			}
		}
		return nodesNotInOpenlist;
	}
	
	/**
	 *14.将一些节点的父节点设置为某个节点
	 *@param Node father, List<Node> nodes
	 *@return void
	 */
	void setNodesFather(Node father, List<Node> nodes){
		if(nodes.isEmpty())
			return;
		for(Node tmpNode:nodes){
			tmpNode.setFather(father);
		}
	}
	
	
	/**
	 *15. 检查相邻节点是否存在于openlist中，返回那些存在于openlist中的结点们
	 *@param List<Node> adjNodes, List<Node> openlist
	 *@return List<Node>
	 */
	List<Node> getNodesInOpenlistFromAdjnodes(List<Node> adjNodes, List<Node> openlist){
		//返回值的数据结构
		List<Node> nodesInOpenlist = new ArrayList<Node>();
		//查找
		for(Node tmpAdjNode:adjNodes){
			//这里还要加上合法性判断？
			//若该点存在于openlist中，则加入nodesInOpenlist
			if(isInOpenlist(openlist, tmpAdjNode) == 1){
				nodesInOpenlist.add(tmpAdjNode);
			}
		}
		return nodesInOpenlist;
	}
	
	/**
	 * 16. 计算相邻两点之间的代价
	 */
	int calculateTheCostBetweenTwoAdjNodes(Node node1, Node node2){
		int cost = 0;
		//1.获取相邻两点的的坐标
		int x1 = node1.x;
		int y1 = node1.y;
		int x2 = node2.x;
		int y2 = node2.y;
		//2.判定几种情况
		if((x1 == x2) && ((y1-y2) == 1)){
			//node1在node2右边
			cost = 10;
		}else if((x1 == x2) && ((y1-y2) == -1)){
			//node1在node2左边
			cost = 10;
		}else if(((x1-x2) == 1) && (y1 == y2)){
			//node1在node2下边
			cost = 10;
		}else if(((x1-x2) == -1) && (y1 == y2)){
			//node1在node2上边
			cost = 10;
		}else if(((x1-x2) == 1) && ((y1-y2) == 1)){
			//node1在node2右下方
			cost = 14;
		}else if(((x1-x2) == -1) && ((y1-y2) == 1)){
			//node1在node2右上方
			cost = 14;
		}else if(((x1-x2) == 1) && ((y1-y2) == -1)){
			//node1在node2左下方
			cost = 14;
		}else if(((x1-x2) == -1) && ((y1-y2) == -1)){
			//node1在node2左上方
			cost = 14;
		}else{
			cost = 0;
		}
		return cost;
	}
	
	/**
	 * 17. 检查终点是否进入openlist中
	 * @param List<Node> openlist
	 * @return boolean
	 */
	boolean checkEndPointInOpenlist(List<Node> openlist){
		for(Node tmpNode:openlist){
			if(tmpNode.nodeTag == Tag.END){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 18. 从endPoint回溯查找路径
	 */
	void findBestWay(Node endPoint){
		DoubleLinkList<Node> bestWay = new DoubleLinkList<Node>();
		if(endPoint.getFather() != null){
			//若存在前一个结点就回溯
			
		}else{
			//若不存在说明不存在路径
		}
	}
	
	/**
	 * 元素的getter和setter
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
