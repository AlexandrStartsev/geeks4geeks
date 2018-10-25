package org.alex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class MaxDiffBetweenNodes {

	public static class Node implements Serializable {
		private static final long serialVersionUID = 7080047147359062466L;
		
		int data;
		Node left, right;

		Node(int item) {
			data = item;
			left = right = null;
		}
	}

	void calcDiff(Node cur, int max, Consumer<Integer> report) {
		if (cur != null) {
			report.accept(max - cur.data);
			calcDiff(cur.left, Math.max(max, cur.data), report);
			calcDiff(cur.right, Math.max(max, cur.data), report);
		}
	}
	
	public String stringify(Node root) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oo = new ObjectOutputStream(new GZIPOutputStream(bos, true));
			oo.writeObject(root);
			oo.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return Hex.encodeHexString(bos.toByteArray());
	}
	
	public Node parse(String str) {
		try {
			return (Node) new ObjectInputStream(new GZIPInputStream(new ByteArrayInputStream(Hex.decodeHex(str.toCharArray())))).readObject();
		} catch (ClassNotFoundException | IOException | DecoderException e) {
			throw new RuntimeException(e);
		}
	}

	public int maxDiff(String data) throws IOException {
		return maxDiff(parse(data));
	}
	
	public int maxDiff(Node root) throws IOException {
		AtomicInteger maxDiff = new AtomicInteger(Integer.MIN_VALUE);
		Consumer<Integer> report = diff -> maxDiff.set(Math.max(diff, maxDiff.get()));
		if (root != null) {
			calcDiff(root.left, root.data, report);
			calcDiff(root.right, root.data, report);
		}
		return maxDiff.get();
	}

	public static void main(String[] args) throws IOException {
		String testData = "1f8b080000000000000074cbcd4a42511405e06ddd0b8189a4a509fe903f697f0a854e044169125c7b8006c2917b6e89a247ef11efa827ea299cf930bd83ddc9624dda83cdc7da7bfdfc8a1b6ee446fbdbb65ae8a83d56d1eb2c0846daeeb45ebeaf7c1dd6e33d1daac9fce3b03f91d337717c659527ce4207d64acdfb6b77e276e7bf76df137733fbfcb26bf9964464442415c68ee70aea420da8029d1b03e7c92e24940ec84de802baa4bb434e43f79466c80f50162a427543af3d72092a50fa4c4e404fd02df4489f67e42454a33447be83aea13eddcbe416f44269d59823000000ffff";

		System.out.println(new MaxDiffBetweenNodes().maxDiff(testData));
		
		/*try (Scanner sc = new Scanner(System.in)) {
			int t = sc.nextInt();

			while (t-- > 0) {
				int n = sc.nextInt();
				if (n == 0) {
					System.out.println(0);
					continue;
				}
				Node root = null;
				for (int i = 0; i < n; i++) {
					int a = sc.nextInt();
					int a1 = sc.nextInt();
					char lr = sc.next().charAt(0);
					if (i == 0) {
						root = new Node(a);
						switch (lr) {
						case 'L':
							root.left = new Node(a1);
							break;
						case 'R':
							root.right = new Node(a1);
							break;
						}
					} else {
						insert(root, a, a1, lr);
					}
				}

				MaxDiffBetweenNodes g = new MaxDiffBetweenNodes();
				System.out.println(g.maxDiff(root));

			}
		}*/
	}

	/*public static void insert(Node root, int a, int a1, char lr) {
		if (root == null) {
			return;
		}
		if (root.data == a) {
			switch (lr) {
			case 'L':
				root.left = new Node(a1);
				break;
			case 'R':
				root.right = new Node(a1);
				break;
			}
			return;
		}
		insert(root.left, a, a1, lr);
		insert(root.right, a, a1, lr);
	}*/
}
