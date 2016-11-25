package lyh.com.AStar;

import java.util.List;

public class Node {
	//一个节点中包含的信息
	public int x,y;//该节点的坐标
	private int G;//从起点 A沿着到达该方格而生成的路径移动本方格的代价
	private int H;//从指定的方格移动到终点 B 的估算成本，只能横竖移动，不可斜角移动
	private int F;//F=G+H
	private Node father;//一个指向父节点的引用
	//采用枚举类型来表示通路、起点、终点和障碍物
	private Tag nodeTag;
	
	//包含的方法
	/*
	 * 1. 查找自己的子节点，输入父节点对象、坐标地图，返回一个子节点的集合
	 * @param Node father, int[][] map
	 * @return List<Node>
	 */
	List<Node> findChildrenNodes(Node father, int[][] map){
		//找到子节点
		//建立关系
		return null;
	}
	
	/*
	 * 2. 向openlist中添加元素
	 */
	
	/*
	 * 3. 从openlist中删除元素
	 */
	
	/*
	 * 4. 向closelist中添加元素
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
