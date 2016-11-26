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
		
		//（3）找到可能的子点
		List<Node> possibleSons = new ArrayList<Node>();//这个list存储可能的sons
		//先找特殊点：
		if(((fatherX - 1) < 0) && ((fatherY - 1) < 0)){
			//左上角；判断规则是：如果该点的坐标为（i，j），那么可能的children点为{（i，j+1）（i+1，j+1）（i+1，j）}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son1);
			possibleSons.add(son2);
			possibleSons.add(son3);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}else if(((fatherX - 1) < 0) && ((fatherY + 1) > (mapWidth - 1))){
			//右上角；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i,j-1)(i+1,j+1)(i+1,j)}
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son5);
			possibleSons.add(son2);
			possibleSons.add(son3);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}else if(((fatherX + 1) > (mapLength - 1)) && ((fatherY + 1) > (mapWidth - 1))){
			//右下角；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i-1,j-i)(i-1,j)(i,j-1)}
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			Node son6 = map.map[fatherX-1][fatherY-1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son5);
			possibleSons.add(son6);
			possibleSons.add(son7);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}else if(((fatherX + 1) > (mapLength - 1)) && ((fatherY - 1) < 0)){
			//左下角；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i,j+1)(i-1,j+1)(i-1,j)}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			Node son8 = map.map[fatherX-1][fatherY+1].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son1);
			possibleSons.add(son7);
			possibleSons.add(son8);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}else if((fatherX - 1) < 0){
			//上边；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i,j-1)(i+1,j-1)(i+1,j)(i+1,j+1)(i,j+1)}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son4 = map.map[fatherX+1][fatherY-1].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son1);
			possibleSons.add(son2);
			possibleSons.add(son3);
			possibleSons.add(son4);
			possibleSons.add(son5);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}else if((fatherY + 1) > (mapWidth - 1)){
			//右边；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i-1,j-1)(i-1,j)(i,j-1)(i+1,j-1)(i+1,j)}
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son4 = map.map[fatherX+1][fatherY-1].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			Node son6 = map.map[fatherX-1][fatherY-1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son6);
			possibleSons.add(son7);
			possibleSons.add(son3);
			possibleSons.add(son4);
			possibleSons.add(son5);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}else if((fatherX + 1) > (mapLength - 1)){
			//下边；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i-1,j-1)(i-1,j)(i-1,j+1)(i,j-1)(i,j+1)}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			Node son6 = map.map[fatherX-1][fatherY-1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			Node son8 = map.map[fatherX-1][fatherY+1].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son1);
			possibleSons.add(son6);
			possibleSons.add(son7);
			possibleSons.add(son8);
			possibleSons.add(son5);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}else if((fatherY + 1) < 0){
			//左边；判断规则是：如果该坐标为(i,j)，那么可能的children点为：{(i-1,j)(i-1,j+1)(i,j+1)(i+1,j)(i+1,j+1)}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			Node son8 = map.map[fatherX-1][fatherY+1].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son1);
			possibleSons.add(son2);
			possibleSons.add(son3);
			possibleSons.add(son7);
			possibleSons.add(son8);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}else{
			//其他正常点
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son4 = map.map[fatherX+1][fatherY-1].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			Node son6 = map.map[fatherX-1][fatherY-1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			Node son8 = map.map[fatherX-1][fatherY+1].father = father;
			//先加入可能的子点集合中
			possibleSons.add(son1);
			possibleSons.add(son2);
			possibleSons.add(son3);
			possibleSons.add(son4);
			possibleSons.add(son5);
			possibleSons.add(son6);
			possibleSons.add(son7);
			possibleSons.add(son8);
			//还要注意这些可能的儿子点是否为墙或者是已经存在于closelist中的点
			List<Node> tmpCloseList = map.closeList;//先获取map中的closelist，以作临时之用
			//检查possibleSons中的元素是否存在于closelist中，如果存在就将其剔除出possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
						tmpPossibleSon.father = null;//移除关系
					}
				}
			}
			//再检查这些可能的儿子点是否为墙，如果是，将其剔除出possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//从possibleSons中移除
					tmpPossibleSon.father = null;//移除关系
				}
			}
		}
		
		return possibleSons;
	}
	
	/*
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
	
	/*
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
