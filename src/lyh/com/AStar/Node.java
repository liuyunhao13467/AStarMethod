package lyh.com.AStar;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Node {
	//һ���ڵ��а�������Ϣ
	public int x,y;//�ýڵ������
	private int G;//����� A���ŵ���÷�������ɵ�·���ƶ�������Ĵ���
	private int H;//��ָ���ķ����ƶ����յ� B �Ĺ���ɱ���ֻ�ܺ����ƶ�������б���ƶ�
	private int F;//F=G+H
	private Node father;//һ��ָ�򸸽ڵ������
	//����ö����������ʾͨ·����㡢�յ���ϰ���
	private Tag nodeTag;
	
	//�����ķ���
	/*
	 * 1. �����Լ����ӽڵ㣬���븸�ڵ���������ͼ������һ���ӽڵ�ļ���
	 * @param Node father, int[][] map
	 * @return List<Node>
	 */
	List<Node> findChildrenNodes(Node father, Map map){
		
		//��1����ȡ��father�����Ϣ���Լ���ͼ�Ĵ�����Ϣ
		int fatherX = father.x;//��ȡ����ĺ�����(i)����Ӧlength
		int fatherY = father.y;//��ȡ�����������(j)����Ӧwidth
		int mapLength = map.length;//��ȡ��ͼ�ĳ���
		int mapWidth = map.width;//��ȡ��ͼ�Ŀ��
		
		//��2�������ǰ����ǽ������father�������Խ�磬��˵�������д���
		if(father.getNodeTag() == Tag.WALL){
			System.err.println("��ǰ����WALL���д���\n");
			return null;
		}else if((fatherX < 0) || (fatherX > mapLength-1)){
			System.err.println("��ǰ���Ѿ�Խ�磬�д���\n");
			return null;
		}else if((fatherY < 0) || (fatherY > mapWidth-1)){
			System.err.println("��ǰ���Ѿ�Խ�磬�д���\n");
			return null;
		}else{
			System.out.println("��ǰ��������\n");
		}
		
		//��3���ҵ����ܵ��ӵ�
		List<Node> possibleSons = new ArrayList<Node>();//���list�洢���ܵ�sons
		//��������㣺
		if(((fatherX - 1) < 0) && ((fatherY - 1) < 0)){
			//���Ͻǣ��жϹ����ǣ�����õ������Ϊ��i��j������ô���ܵ�children��Ϊ{��i��j+1����i+1��j+1����i+1��j��}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son1);
			possibleSons.add(son2);
			possibleSons.add(son3);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}else if(((fatherX - 1) < 0) && ((fatherY + 1) > (mapWidth - 1))){
			//���Ͻǣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ�children��Ϊ��{(i,j-1)(i+1,j+1)(i+1,j)}
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son5);
			possibleSons.add(son2);
			possibleSons.add(son3);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}else if(((fatherX + 1) > (mapLength - 1)) && ((fatherY + 1) > (mapWidth - 1))){
			//���½ǣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ�children��Ϊ��{(i-1,j-i)(i-1,j)(i,j-1)}
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			Node son6 = map.map[fatherX-1][fatherY-1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son5);
			possibleSons.add(son6);
			possibleSons.add(son7);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}else if(((fatherX + 1) > (mapLength - 1)) && ((fatherY - 1) < 0)){
			//���½ǣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ�children��Ϊ��{(i,j+1)(i-1,j+1)(i-1,j)}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			Node son8 = map.map[fatherX-1][fatherY+1].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son1);
			possibleSons.add(son7);
			possibleSons.add(son8);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}else if((fatherX - 1) < 0){
			//�ϱߣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ�children��Ϊ��{(i,j-1)(i+1,j-1)(i+1,j)(i+1,j+1)(i,j+1)}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son4 = map.map[fatherX+1][fatherY-1].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son1);
			possibleSons.add(son2);
			possibleSons.add(son3);
			possibleSons.add(son4);
			possibleSons.add(son5);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}else if((fatherY + 1) > (mapWidth - 1)){
			//�ұߣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ�children��Ϊ��{(i-1,j-1)(i-1,j)(i,j-1)(i+1,j-1)(i+1,j)}
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son4 = map.map[fatherX+1][fatherY-1].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			Node son6 = map.map[fatherX-1][fatherY-1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son6);
			possibleSons.add(son7);
			possibleSons.add(son3);
			possibleSons.add(son4);
			possibleSons.add(son5);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}else if((fatherX + 1) > (mapLength - 1)){
			//�±ߣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ�children��Ϊ��{(i-1,j-1)(i-1,j)(i-1,j+1)(i,j-1)(i,j+1)}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			Node son6 = map.map[fatherX-1][fatherY-1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			Node son8 = map.map[fatherX-1][fatherY+1].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son1);
			possibleSons.add(son6);
			possibleSons.add(son7);
			possibleSons.add(son8);
			possibleSons.add(son5);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}else if((fatherY + 1) < 0){
			//��ߣ��жϹ����ǣ����������Ϊ(i,j)����ô���ܵ�children��Ϊ��{(i-1,j)(i-1,j+1)(i,j+1)(i+1,j)(i+1,j+1)}
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			Node son8 = map.map[fatherX-1][fatherY+1].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son1);
			possibleSons.add(son2);
			possibleSons.add(son3);
			possibleSons.add(son7);
			possibleSons.add(son8);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}else{
			//����������
			Node son1 = map.map[fatherX][fatherY+1].father = father;
			Node son2 = map.map[fatherX+1][fatherY+1].father = father;
			Node son3 = map.map[fatherX+1][fatherY].father = father;
			Node son4 = map.map[fatherX+1][fatherY-1].father = father;
			Node son5 = map.map[fatherX][fatherY-1].father = father;
			Node son6 = map.map[fatherX-1][fatherY-1].father = father;
			Node son7 = map.map[fatherX-1][fatherY].father = father;
			Node son8 = map.map[fatherX-1][fatherY+1].father = father;
			//�ȼ�����ܵ��ӵ㼯����
			possibleSons.add(son1);
			possibleSons.add(son2);
			possibleSons.add(son3);
			possibleSons.add(son4);
			possibleSons.add(son5);
			possibleSons.add(son6);
			possibleSons.add(son7);
			possibleSons.add(son8);
			//��Ҫע����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ�������Ѿ�������closelist�еĵ�
			List<Node> tmpCloseList = map.closeList;//�Ȼ�ȡmap�е�closelist��������ʱ֮��
			//���possibleSons�е�Ԫ���Ƿ������closelist�У�������ھͽ����޳���possibleSons
			for(Node tmpCloseNode:tmpCloseList){
				for(Node tmpPossibleSon:possibleSons){
					if((tmpCloseNode.x == tmpPossibleSon.x) && (tmpCloseNode.y == tmpPossibleSon.y)){
						possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
						tmpPossibleSon.father = null;//�Ƴ���ϵ
					}
				}
			}
			//�ټ����Щ���ܵĶ��ӵ��Ƿ�Ϊǽ������ǣ������޳���possibleSons
			for(Node tmpPossibleSon:possibleSons){
				if(tmpPossibleSon.getNodeTag() == Tag.WALL){
					possibleSons.remove(tmpPossibleSon);//��possibleSons���Ƴ�
					tmpPossibleSon.father = null;//�Ƴ���ϵ
				}
			}
		}
		
		return possibleSons;
	}
	
	/*
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
	
	/*
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
	
	/*
	 * 4. ��closelist�����Ԫ��
	 */

	/*
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
