package utilities;

import java.util.ArrayList;

public class ArrayTree<T> {
	T data;
	ArrayList<ArrayTree> nodes;
	ArrayTree parent;
	
	public ArrayTree() {
		nodes = new ArrayList<ArrayTree>();
	}
	
	public ArrayTree(T data) {
		this();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ArrayList<ArrayTree> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<ArrayTree> nodes) {
		this.nodes = nodes;
	}
	
	public void addNode(ArrayTree node) {
		this.nodes.add(node);
	}

	public ArrayTree getParent() {
		return parent;
	}

	public void setParent(ArrayTree parent) {
		this.parent = parent;
	}
}
