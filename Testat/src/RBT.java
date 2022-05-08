import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
	int key;
	Node left;
	Node right;
	Node parent;
	boolean color; // true = black, false = red
	int height;
	String name; 
}

public class RBT {
	private Node origin;
	private Node root;
	public static int nullCount = 1;

	public static int count = 1;
	public static String redResult = "";

	

	Queue<Node> que = new LinkedList<Node>();
	Queue<Node> allNodes = new LinkedList<Node>();

	static int bheight;

	public RBT() {
		origin = new Node();
		origin.color = true;
		origin.left = null;
		origin.right = null;
		origin.name = "n0";
		origin.key = 0;
		root = origin;
	}

	public void RB_Print() {

		this.addLeaves(this.getRoot());
		System.out.println("digraph G {\r\n" + "	graph [ratio=.48];\r\n"
				+ "	node [style=filled, color=black, shape=circle, width=.6 \r\n"
				+ "		fontname=Helvetica, fontweight=bold, fontcolor=white, \r\n"
				+ "		fontsize=24, fixedsize=true];");
		System.out.print("{rank = same; ");
		Queue<Node> que = this.getHeight(this.getRoot());

		while (!que.isEmpty()) {
			Node actual = que.remove();
			if (actual == null) {
				break;
			}
			if (actual.key != 0) {
				System.out.print(actual.key);
			} else {
				System.out.print(actual.name);
			}
			if (que.size() > 0) {
				System.out.print(";");
			}

		}
		redResult = "";
		System.out.print("};\n");
		System.out.println();
		System.out.print(findRed(root));
		System.out.println("\n[fillcolor=red];\n");

		RB_Printhelp(this.root);
		System.out.println();
		System.out.println(RB_PrintLeaves());
		System.out.println("[label=\"NIL\", shape=record, width=.4,height=.25, fontsize=16];" + "\n" + "}");

	}

	public static String findRed(Node root) {
		if (root == null) {
            
        }
 
        
        Stack<Node> nodeStack = new Stack<Node>();
        nodeStack.push(root);
 
        
        while (nodeStack.empty() == false) {
 
        	
            Node mynode = nodeStack.peek();
            if(mynode.color == false) {
            redResult = redResult + mynode.key + ", ";
            }
            nodeStack.pop();
            
 

            if (mynode.right != null) {
                nodeStack.push(mynode.right);
            }
            if (mynode.left != null) {
                nodeStack.push(mynode.left);
            }
        }
        if(redResult.length() < 2) {
        	return redResult;
        }
        return redResult.substring(0, redResult.length() - 2);
	}

	public Node root(Node t) {
		if (t.parent == origin) {
			return t;
		} else {
			bheight++;

			return root(t.parent);

		}

	}

	public Node getRoot() {
		return this.root;
	}

	public Queue<Node> addNodesToQueue(Node root) {
		if (root == null) {
			return allNodes;
		}

		allNodes.add(root);

		if (root.left != origin) {

			addNodesToQueue(root.left);
		}
		if (root.right != origin) {

			addNodesToQueue(root.right);
		}
		return allNodes;
	}

	public Queue<Node> getHeight(Node b) {
		b = root(b);
		int maxrank = 0;

		que.clear();
		b.height = 0;
		que.add(b);
		while (!que.isEmpty()) {
			Node node = que.remove();
			if (node.left != origin) {
				if (node.left == null) {
					break;
				}
				node.left.height = (node.left.parent.height) + 1;
				if (maxrank < node.left.height) {
					maxrank = node.left.height;
				}
				
				que.add(node.left);
			}
			if (node.right != origin) {
				if (node.right == null) {
					break;
				}
				node.right.height = (node.right.parent.height) + 1;
				if (maxrank < node.right.height) {
					maxrank = node.right.height;
				}
				
				que.add(node.right);
			}

			if (que.peek() == null) {
				break;
			}

		}
		addNodesToQueue(getRoot());
		que = sameRank(maxrank);

		return que;
	}

	public Queue<Node> sameRank(int maxrank) {
		Queue<Node> maxRank_1 = new LinkedList<Node>();
		Queue<Node> sameRank = new LinkedList<Node>();

		while (!allNodes.isEmpty()) {
			Node actual = allNodes.remove();
			if (actual.height == maxrank - 1) {

				maxRank_1.add(actual);

			}
		}

		while (!maxRank_1.isEmpty()) {
			Node actual = maxRank_1.remove();

			if (actual.key != 0) {
				sameRank.add(actual.left);
				sameRank.add(actual.right);
			}
		}
		return sameRank;
	}

	

	public String RB_PrintLeaves() {
		String result = "";

		for (int j = 1; j < nullCount; j++) {
			result += "n" + j + ",";

		}
		if (result.length() > 2) {
			result = result.substring(0, result.length() - 1);
		}

		return result;

	}

	public void RB_Printhelp(Node treeNode) {

		if (treeNode.key != 0 && treeNode.left != null && treeNode.right != null) {

			if (treeNode.left.key == 0) {

				System.out.print(treeNode.key + " -> " + treeNode.left.name + " ;" + "\n");

				nullCount++;
			} else {
				System.out.print(treeNode.key + " -> " + treeNode.left.key + " ;" + "\n");
			}
			if (treeNode.right.key == 0) {

				System.out.print(treeNode.key + " -> " + treeNode.right.name + " ;" + "\n");
				nullCount++;
				
			} else {

				System.out.print(treeNode.key + " -> " + treeNode.right.key + " ;" + "\n");
			}

			RB_Printhelp(treeNode.left);
			RB_Printhelp(treeNode.right);
		}

	}

	public void addLeaves(Node root) {
		count = 1;
		if (root == null)
			return;

		Stack<Node> stack = new Stack<Node>();
		Node curr = root;

		while (curr.key != 0 || stack.size() > 0) {

			while (curr.key != 0) {
				stack.push(curr);
				curr = curr.left;
			}

			curr = stack.pop();
			if (curr.left == origin) {
				curr.left = new Node();
				curr.left.color = true;
				curr.left.left = null;
				curr.left.right = null;
				curr.left.parent = curr;
				curr.left.key = 0;
				curr.left.name = "n" + count;
				count++;
			}
			if (curr.right == origin) {
				curr.right = new Node();
				curr.right.color = true;
				curr.right.left = null;
				curr.right.right = null;
				curr.right.parent = curr;
				curr.right.key = 0;
				curr.right.name = "n" + count;
				count++;
			}
			curr = curr.right;

		}

	}
	
	public void removeLeaves(Node root) {
		//count = 1;
		if (root == null)
			return;

		Stack<Node> stack = new Stack<Node>();
		Node curr = root;

		while (curr.key != 0 || stack.size() > 0) {

			while (curr.key != 0) {
				stack.push(curr);
				curr = curr.left;
			}

			curr = stack.pop();
			if (curr.left.key == 0) {
				curr.left.parent = null;
				curr.left = origin;
				
			}
			if (curr.right.key == 0) {
				
				curr.right.parent = null;
				curr.right = origin;
				
			}
			curr = curr.right;

		}

	}

	public void RB_Insert(int z) {
		nullCount = 1;

		Node treeNode = new Node();
		treeNode.key = z;
		treeNode.left = origin;
		treeNode.right = origin;
		treeNode.parent = origin;
		treeNode.color = false;

		Node y = origin;
		Node x = getRoot();

		while (x != origin) {
			y = x;
			if (treeNode.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}

		}
		treeNode.parent = y;

		if (y == origin) {
			root = treeNode;

		} else if (treeNode.key < y.key) {
			y.left = treeNode;
		} else {
			y.right = treeNode;
		}

		treeNode.left = origin;
		treeNode.right = origin;
		treeNode.color = false;

		RB_Insert_Fixup(treeNode);

	}

	public void RB_Insert_Fixup(Node treeNode) {
		Node y;

		while (treeNode.parent.color == false) {
			if (treeNode.parent == treeNode.parent.parent.left) {
				y = treeNode.parent.parent.right;
				if (y.color == false) {
					treeNode.parent.color = true;
					y.color = true;
					treeNode.parent.parent.color = false;
					treeNode = treeNode.parent.parent;
				} else {

					if (treeNode == treeNode.parent.right) {
						treeNode = treeNode.parent;
						Left_Rotate(treeNode);
					}
					treeNode.parent.color = true;
					treeNode.parent.parent.color = false;
					Right_Rotate(treeNode.parent.parent);
				}
			} else {
				y = treeNode.parent.parent.left;
				if (y.color == false) {
					treeNode.parent.color = true;
					y.color = true;
					treeNode.parent.parent.color = false;
					treeNode = treeNode.parent.parent;
				} else {

					if (treeNode == treeNode.parent.left) {
						treeNode = treeNode.parent;
						Right_Rotate(treeNode);
					}
					treeNode.parent.color = true;
					treeNode.parent.parent.color = false;
					Left_Rotate(treeNode.parent.parent);
				}
			}
		}
		this.root.color = true;
	}

	public void Left_Rotate(Node treeNode) {
		Node y = treeNode.right;
		treeNode.right = y.left;
		if (y.left != origin) {
			y.left.parent = treeNode;
		}
		y.parent = treeNode.parent;
		if (treeNode.parent == origin) {
			this.root = y;
		} else if (treeNode == treeNode.parent.left) {
			treeNode.parent.left = y;
		} else {
			treeNode.parent.right = y;
		}
		y.left = treeNode;
		treeNode.parent = y;

	}

	public void Right_Rotate(Node treeNode) {
		Node y = treeNode.left;
		treeNode.left = y.right;
		if (y.right != origin) {
			y.right.parent = treeNode;
		}
		y.parent = treeNode.parent;
		if (treeNode.parent == origin) {
			this.root = y;
		} else if (treeNode == treeNode.parent.right) {
			treeNode.parent.right = y;
		} else {
			treeNode.parent.left = y;
		}
		y.right = treeNode;
		treeNode.parent = y;
	}

}
