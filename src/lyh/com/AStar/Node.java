package lyh.com.AStar;

import java.util.List;

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
	List<Node> findChildrenNodes(Node father, int[][] map){
		//�ҵ��ӽڵ�
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
