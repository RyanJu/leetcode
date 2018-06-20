package com.zrk.leetcode.homework;

import android.support.annotation.NonNull;
import android.util.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/11 11:07 1.0
 * @time 2018/6/11 11:07
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/11 11:07
 */

public class Huffman {
    public static void main(String[] args) {
        String text = "My house is perfect. By great good fortune I have found a housekeeper no less to my mind, a low-voiced, light-footed woman of discreet age, strong and deft enough to render me all the service I require, and not afraid of loneliness. She rises very early. By my breakfast-time there remains little to be done under the roof save dressing of meals. Very rarely do I hear even a clink of crockery; never the closing of a door or window. Oh, blessed silence! My house is perfect. Just large enough to allow the grace of order in domestic circumstance; just that superfluity of inner space, to lack which is to be less than at one's ease. The fabric is sound; the work in wood and plaster tells of a more leisurely and a more honest age than ours. The stairs do not creak under my step; I am attacked by no unkindly draught; I can open or close a window without muscle-ache. As to such trifles as the color and device of wall-paper, I confess my indifference; be the walls only plain, and I am satisfied. The first thing in one's home is comfort; let beauty of detail be added if one has the means, the patience, the eye. To me, this little book-room is beautiful, and chiefly because it is home. Through the greater part of life I was homeless. Many places have I lived, some which my soul disliked, and some which pleased me well; but never till now with that sense of security which makes a home. At any moment I might have been driven forth by evil accident, by disturbing necessity. For all that time did I say within myself: Some day, perchance, I shall have a home; yet the \"perchance\" had more and more of emphasis as life went on, and at the moment when fate was secretly smiling on me, I had all but abandoned hope. I have my home at last. This house is mine on a lease of a score of years. So long I certainly shall not live; but, if I did, even so long should I have the money to pay my rent and buy my food. I am no cosmopolite. Were I to think that I should die away from England, the thought would be dreadful to me. And in England, this is the place of my choice; this is my home.";
        Huffman huffman = new Huffman();
        Map<Character, String> huffManCode = huffman.encode(text);

        for (char c : huffManCode.keySet()) {
            System.out.println(c + " : " + huffManCode.get(c));
        }

        System.out.println("primary text size = " + text.length() * Character.SIZE / Byte.SIZE + " bytes");
        System.out.println("haffman encode size = " + Huffman.calculateEncodedCharSize(huffManCode) / Byte.SIZE + " bytes");

//        Map<Character, String> hellooo = huffman.encode("hellooo");
//        for (char c : hellooo.keySet()) {
//            System.out.println(c + " : " + hellooo.get(c));
//        }
        List<Byte> bytes = huffman.encodeToBytes(text);
        for (byte a : bytes) {
            System.out.printf(" " + a);
        }
    }

    public static int calculateEncodedCharSize(Map<Character, String> encodedMap) {
        if (encodedMap == null || encodedMap.isEmpty()) {
            return 0;
        }
        int result = 0;
        for (char c : encodedMap.keySet()) {
            result += encodedMap.get(c).length();
        }
        return result;
    }

    public Map<Character, String> encode(String text) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = text.toCharArray();
        for (char c : chars) {
            Integer times = map.get(c);
            times = times == null ? 0 : times;
            map.put(c, times + 1);
        }
        char[] letters = new char[map.size()];
        int[] frequency = new int[letters.length];
        int i = 0;
        for (char c : map.keySet()) {
            letters[i] = c;
            frequency[i++] = map.get(c);

        }
        return encode(letters, frequency);
    }

    public Map<Character, String> encode(char[] letters, int[] frequency) {
        if (letters == null || frequency == null || letters.length != frequency.length) {
            return null;
        }
        Map<Character, String> result = new HashMap<>();
        Heap<Node> heap = new Heap<>();
        for (int i = 0; i < letters.length; i++) {
            Node node = new Node();
            node.letter = letters[i];
            node.frequency = frequency[i];
            heap.insert(node);
        }
//        Heap.printHeap(heap);

        Node top = null;
        while (!heap.empty()) {
            Node node1 = heap.takeTop();
            if (node1 == null) {
                break;
            }
            top = node1;

            Node node2 = heap.takeTop();
            if (node2 == null) {
                break;
            }

            Node newNode = new Node();
            newNode.frequency = node1.frequency + node2.frequency;
            newNode.left = node1;
            newNode.right = node2;
            node1.parent = newNode;
            node2.parent = newNode;
            heap.insert(newNode);
//            Heap.printHeap(heap);
        }

        if (top != null) {
            deepTravel(top, "", result);
        }

        return result;
    }

    public List<Byte> encodeToBytes(String text) {
        List<Byte> result = new ArrayList<>();
        Map<Character, String> map = encode(text);

        StringBuilder bits = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String s = map.get(c);
            if (s == null) {
                throw new RuntimeException("encode error");
            }
            bits.append(s);
            if (bits.length() % 8 == 0) {
                for (int j = 0; j < bits.length(); j += 8) {
                    int end = j + 8;
                    if (j + 8 > bits.length()) {
                        end = bits.length();
                    }
                    Byte b = ByteValueOf(bits.substring(j, end));
                    result.add(b);
                }
                bits = new StringBuilder();
            }
        }
        for (int j = 0; j < bits.length(); j += 8) {
            int end = j + 8;
            if (j + 8 > bits.length()) {
                end = bits.length();
            }
            Byte b = ByteValueOf(bits.substring(j, end));
            result.add(b);
        }

        return result;
    }

    private Byte ByteValueOf(String s) {
        byte b = 0;
        int end = Math.min(s.length(), 8);
        for (int i = 0; i < end; i++) {
            b = setBit(b, 7 - i, s.charAt(i) - '0');
        }
        return b;
    }

    /**
     * @param b
     * @param position
     * @param value    1 or 0
     */
    private byte setBit(byte b, int position, int value) {
        return b |= (value << position);
    }

    private void deepTravel(Node node, String s, Map<Character, String> result) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            deepTravel(node.left, s + "0", result);
        }
        if (node.right != null) {
            deepTravel(node.right, s + "1", result);
        }
        if (node.left == null && node.right == null) {
            result.put(node.letter, s);
        }
    }

    private static class Node implements Comparable {
        char letter;
        int frequency;

        Node parent;
        Node left;
        Node right;

        @Override
        public int compareTo(@NonNull Object o) {
            if (o instanceof Node) {
                return frequency - ((Node) o).frequency;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "letter=" + letter +
                    ", frequency=" + frequency +
                    '}';
        }
    }


}
