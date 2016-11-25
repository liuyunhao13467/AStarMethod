package lyh.com.AStar;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
	List<Node> findChildrenNodes(Node father, Map map){
		
		//（1）获取到father点的信息，以及地图的大致信息
		int fatherX = father.x;//获取父点的横坐标(i)，对应length
		int fatherY = father.y;//获取父点的纵坐标(j)，对应width
		int mapLength = map.length;//获取地图的长度
		int mapWidth = map.width;//获取地图的宽度
		
		//（2）如果当前点是墙，或者father点的坐标越界，则说明程序有错误
		if(father.getNodeTag() == Tag.WALL){
			System.err.println("当前点是WALL，有错误！\n");
			return null;
		}else if((fatherX < 0) || (fatherX > mapLength-1)){
			System.err.println("当前点已经越界，有错误！\n");
			return null;
		}else if((fatherY < 0) || (fatherY > mapWidth-1)){
			System.err.println("当前点已经越界，有错误！\n");
			return null;
		}else{
			System.out.println("当前点正常。\n");
		}
		
		//（3）如果是正常点，先找到可能的子点
		List<Node> possibleSons = new ArrayList<Node>();//这个list存储可能的sons
		//先找特殊点：
		if(((fatherX - 1) < 0) && ((fatherY - 1) < 0)){
			//左上角
		}else if((fatherX - 1) < 0){
			//上边
		}else if(((fatherX - 1) < 0) && ((fatherY + 1) > (mapWidth - 1))){
			//右上角
		}else if((fatherY + 1) > (mapWidth - 1)){
			//右边
		}else if(((fatherX + 1) > (mapLength - 1)) && ((fatherY + 1) > (mapWidth - 1))){
			//右下角
		}else if((fatherX + 1) > (mapLength - 1)){
			//下边
		}else if(((fatherX + 1) > (mapLength - 1)) && ((fatherY - 1) < 0)){
			//左下角
		}else if((fatherY + 1) < 0){
			//左边
		}else{
			//其他正常点
		}
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

	/*
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
