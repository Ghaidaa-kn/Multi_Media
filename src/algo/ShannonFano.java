//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package algo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

public class ShannonFano extends Compression {
    private static final String SEPARATOR = "$";
    private static final String START_ENCODING_FLAG = "(";
    private HashMap<Integer, String> encoding;

    public ShannonFano() {
    }

    public void compressSingleFile(String filePath, String destFilePath) throws IOException {
        int[] bytes = this.compression(filePath, (new File(filePath)).getParent());
        BitByte outputStream = null;

        try {
            outputStream = new BitByte(new FileOutputStream(destFilePath));
        } catch (FileNotFoundException var6) {
            var6.printStackTrace();
        }

        outputStream.writeBytes(bytes);
        outputStream.close();
    }

    public void DirectoryCompression(String dirPath, String newFilePath) {
        File dir = new File(dirPath);
        List<String> list = new ArrayList();
        list.add(dir.getAbsolutePath());
        BitByte outputStream = null;

        try {
            outputStream = new BitByte(new FileOutputStream(newFilePath));
        } catch (FileNotFoundException var16) {
            var16.printStackTrace();
        }

        String baseDir = dir.getParent();

        for(int i = 0; i < list.size(); ++i) {
            String path = (String)list.get(i);
            File tmp = new File(path);
            int[] bytes = this.compression(path, baseDir);
            outputStream.writeBytes(bytes);
            if (tmp.isDirectory()) {
                File[] files = tmp.listFiles();
                if (files != null) {
                    File[] var12 = files;
                    int var13 = files.length;

                    for(int var14 = 0; var14 < var13; ++var14) {
                        File file = var12[var14];
                        list.add(file.getAbsolutePath());
                    }
                }
            }
        }

        outputStream.close();
    }

    public int[] compression(String path, String baseDir) {
        int[] result = new int[0];
        String compressed = "";
        if (Files.isDirectory(Paths.get(path), new LinkOption[0])) {
            String newDirPath = path.replace(baseDir, "");
            int[] bytes = merge(new int[][]{getBytes(9223372036854775807L), getBytes(newDirPath.length()), getBytes(newDirPath)});
            return bytes;
        } else {
            try {
                File file = new File(path);
                byte[] bytes = Files.readAllBytes(Paths.get(path));
                int[] coding = this.getShannonCode(bytes);
                String newFilePath = file.getPath().replace(baseDir, "");
                result = merge(new int[][]{coding, getBytes(newFilePath.length()), getBytes(newFilePath)});
            } catch (IOException var9) {
                var9.printStackTrace();
            }

            return result;
        }
    }

    public void decompression(String filePath, String destDir) {

        try {
            BitInputStream inputStream = new BitInputStream(new FileInputStream(filePath));

            while(true) {
                while(true) {
                    Map<Integer, Long> frequencies = new HashMap();
                    long encodingSize = inputStream.readLong();
                    if (encodingSize == -9223372036854775808L) {
                        return;
                    }

                    int frequencyPairsCount;
                    StringBuilder stringBuilder;
                    if (encodingSize == 9223372036854775807L) {
                        frequencyPairsCount = inputStream.readInt();
                        stringBuilder = new StringBuilder();

                        for(int i = 0; i < frequencyPairsCount; ++i) {
                            int b = inputStream.readByte();
                            stringBuilder.append((char)b);
                        }

                        String newPath = destDir + stringBuilder.toString();
                        (new File(newPath)).mkdirs();
                    } else {
                        frequencyPairsCount = inputStream.readInt();

                        long i;
                        int j;
                        for(int i3 = 0; i3 < frequencyPairsCount; ++i3) {
                            i = inputStream.readLong();
                            j = inputStream.readInt();
                            frequencies.put(j, i);
                        }

                        this.buildShannonTree(frequencies);
                        stringBuilder = new StringBuilder();

                        for(i = 0L; i < encodingSize; ++i) {
                            j = inputStream.readBit();
                            stringBuilder.append((char)(j + 48));
                        }

                        List<Integer> bytesList = new ArrayList();

                        for(long i2 = encodingSize; i2 % 8L != 0L; ++i2) {
                            inputStream.readBit();
                        }

                        String s = stringBuilder.toString();
                        j = 0;

                        while(true) {
                            String encoding;
                            while(j < s.length()) {
                                Iterator var12 = this.encoding.entrySet().iterator();

                                while(var12.hasNext()) {
                                    Entry<Integer, String> entry = (Entry)var12.next();
                                    encoding = (String)entry.getValue();
                                    if (j + encoding.length() <= s.length() && s.substring(j, j + encoding.length()).equals(encoding)) {
                                        bytesList.add(entry.getKey());
                                        j += encoding.length();
                                        break;
                                    }
                                }
                            }

                            int pathLen = inputStream.readInt();
                            StringBuilder path = new StringBuilder();

                            for(int i1 = 0; i1 < pathLen; ++i1) {
                                path.append((char)inputStream.readByte());
                            }

                            encoding = destDir + path;
                            Files.write(Paths.get(encoding), this.toArray(bytesList), new OpenOption[0]);
                            break;
                        }
                    }
                }
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }
    }

    private int[] getShannonCode(byte[] bytes) {
        int[] intBytes = new int[bytes.length];

        for(int i = 0; i < intBytes.length; ++i) {
            intBytes[i] = bytes[i];
        }

        this.buildShannonTree(this.getProbabilities(intBytes));
        int[] old = new int[0];
        Map<Integer, Long> probs = this.getProbabilities(intBytes);

        Entry entry;
        for(Iterator var5 = probs.entrySet().iterator(); var5.hasNext(); old = merge(new int[][]{old, getBytes((Long)entry.getValue()), getBytes((Integer)entry.getKey())})) {
            entry = (Entry)var5.next();
        }

        old = merge(new int[][]{getBytes(probs.size()), old});
        StringBuilder s = new StringBuilder();
        byte[] var12 = bytes;
        int var7 = bytes.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            int b = var12[var8];
            s.append((String)this.encoding.get(Integer.valueOf(b)));
        }

        int[] coding = bitsStringToBytes(s.toString());
        int[] result = merge(new int[][]{getBytes((long)s.length()), old, coding});
        return result;
    }

    private void buildShannonTree(Map<Integer, Long> probs) {
        if (probs.size() != 0) {
            List<ShannonFano.Node> nodes = new ArrayList();
            Queue<ShannonFano.Node> queue = new LinkedList();
            int total = 0;

            Entry entry;
            for(Iterator var5 = probs.entrySet().iterator(); var5.hasNext(); total = (int)((long)total + (Long)entry.getValue())) {
                entry = (Entry)var5.next();
                nodes.add(new ShannonFano.Node((Integer)entry.getKey(), (Long)entry.getValue()));
            }

            ShannonFano.Node root = new ShannonFano.Node(-1, (long)total);
            root.nodes = nodes;
            queue.add(root);
            if (nodes.size() == 1) {
                root = (ShannonFano.Node)nodes.get(0);
            }

            while(true) {
                ShannonFano.Node curr;
                do {
                    do {
                        if (queue.isEmpty()) {
                            this.encoding = new HashMap();
                            this.traverse(root, root.left == null ? "0" : "");
                            return;
                        }

                        curr = (ShannonFano.Node)queue.poll();
                        //List<ShannonFano.Node> nodes = curr.nodes;
                    } while(curr.nodes == null);
                } while(curr.nodes.size() == 1);

                int sum = 0;
                int twoParts = 0;

                for(int i = 0; i < curr.nodes.size(); ++i) {
                    if ((double)((long)sum + ((ShannonFano.Node)curr.nodes.get(i)).prob) > (double)curr.prob / 2.0D) {
                        twoParts = i;
                        break;
                    }

                    sum = (int)((long)sum + ((ShannonFano.Node)curr.nodes.get(i)).prob);
                }

                if (twoParts == 0) {
                    twoParts = 1;
                }

                List<ShannonFano.Node> onePart = new ArrayList();
                List<ShannonFano.Node> twoPart = new ArrayList();
                int oneProbilty = 0;
                int twoProbilty = 0;

                int i;
                for(i = 0; i < twoParts; ++i) {
                    onePart.add(curr.nodes.get(i));
                    oneProbilty = (int)((long)oneProbilty + ((ShannonFano.Node)curr.nodes.get(i)).prob);
                }

                for(i = twoParts; i < curr.nodes.size(); ++i) {
                    twoPart.add(curr.nodes.get(i));
                    twoProbilty = (int)((long)twoProbilty + ((ShannonFano.Node)curr.nodes.get(i)).prob);
                }

                ShannonFano.Node left = new ShannonFano.Node(-1, (long)oneProbilty);
                left.nodes = onePart;
                if (onePart.size() == 1) {
                    left = (ShannonFano.Node)onePart.get(0);
                }

                queue.add(left);
                curr.left = left;
                ShannonFano.Node right = new ShannonFano.Node(-1, (long)twoProbilty);
                right.nodes = twoPart;
                if (twoPart.size() == 1) {
                    right = (ShannonFano.Node)twoPart.get(0);
                }

                queue.add(right);
                curr.right = right;
            }
        }
    }

    private Map<Integer, Long> getProbabilities(int[] bytes) {
        int total = bytes.length;
        Map<Integer, Long> frequencies = new HashMap();
        new HashMap();
        int[] var5 = bytes;
        int var6 = bytes.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            int b = var5[var7];
            frequencies.put(b, (Long)frequencies.getOrDefault(b, 0L) + 1L);
        }

        return frequencies;
    }

    private void traverse(ShannonFano.Node node, String pathCoding) {
        if (node != null) {
            this.traverse(node.left, pathCoding + "0");
            this.traverse(node.right, pathCoding + "1");
            if (node.nodes == null) {
                this.encoding.put(node.value, pathCoding);
            }

        }
    }



    private byte[] toArray(List<Integer> list) {
        byte[] arr = new byte[list.size()];

        for(int i = 0; i < arr.length; ++i) {
            arr[i] = Byte.parseByte(list.get(i) + "");
        }

        return arr;
    }

    class Node implements Comparable {
        ShannonFano.Node left;
        ShannonFano.Node right;
        List<ShannonFano.Node> nodes;
        int value;
        long prob;

        Node(int value, long prob) {
            this.value = value;
            this.prob = prob;
        }

        public int compareTo(Object o) {
            ShannonFano.Node n = (ShannonFano.Node)o;
            if (this.prob < n.prob) {
                return -1;
            } else {
                return this.prob > n.prob ? 1 : 0;
            }
        }
    }
}
