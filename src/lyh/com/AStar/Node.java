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
		
		//��3������������㣬���ҵ����ܵ��ӵ�
		List<Node> possibleSons = new ArrayList<Node>();//���list�洢���ܵ�sons
		//��������㣺
		if(((fatherX - 1) < 0) && ((fatherY - 1) < 0)){
			//���Ͻ�
		}else if((fatherX - 1) < 0){
			//�ϱ�
		}else if(((fatherX - 1) < 0) && ((fatherY + 1) > (mapWidth - 1))){
			//���Ͻ�
		}else if((fatherY + 1) > (mapWidth - 1)){
			//�ұ�
		}else if(((fatherX + 1) > (mapLength - 1)) && ((fatherY + 1) > (mapWidth - 1))){
			//���½�
		}else if((fatherX + 1) > (mapLength - 1)){
			//�±�
		}else if(((fatherX + 1) > (mapLength - 1)) && ((fatherY - 1) < 0)){
			//���½�
		}else if((fatherY + 1) < 0){
			//���
		}else{
			//����������
		}
		//������ϵ
		return null;
	}
	
	/*
	 * 2. ��openlist�����Ԫ��
	 */
	
	/*
	 * 3. ��openlist��ɾ��Ԫ��
	 */
	
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
