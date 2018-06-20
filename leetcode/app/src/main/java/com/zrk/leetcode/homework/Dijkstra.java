package com.zrk.leetcode.homework;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By zhurongkun
 *
 * @author zhurongkun
 * @version 2018/6/20 10:21 1.0
 * @time 2018/6/20 10:21
 * @project leetcode com.zrk.leetcode.homework
 * @description
 * @updateVersion 1.0
 * @updateTime 2018/6/20 10:21
 */

public class Dijkstra {
    /**
     * @param map map[i,j] means distance of vertex[i] to vertex[j],-1 means no direct path
     */
    public int[] minimumDistance(int[][] map) {
        final int NO_PATH = -1;

        int vertexCount = map[0].length;
        int[] distance = new int[map[0].length];
        for (int i = 0; i < map[0].length; i++) {
            distance[i] = map[0][i];
        }

        List<Integer> S = new ArrayList<>();
        List<Integer> U = new ArrayList<>();
        S.add(0);
        for (int i = 1; i < vertexCount; i++) {
            U.add(i);
        }

        while (!U.isEmpty()) {
            int min = NO_PATH;
            int v = -1;
            for (int u : U) {
                if (min == NO_PATH) {
                    min = distance[u];
                    v = u;
                } else {
                    if (distance[u] != NO_PATH && min > distance[u]) {
                        min = distance[u];
                        v = u;
                    }
                }
            }

            S.add(v);
            U.remove((Integer) v);

            for (int vInU : U) {
                int dis = NO_PATH;
                if (distance[v] != NO_PATH && map[v][vInU] != NO_PATH) {
                    dis = distance[v] + map[v][vInU];
                }

                if (dis != NO_PATH) {
                    if (distance[vInU] == NO_PATH) {
                        distance[vInU] = dis;
                    } else {
                        distance[vInU] = Math.min(dis, distance[vInU]);
                    }
                }
            }

        }
        return distance;
    }


    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        int[][] map = {
                {0, 2, 12, -1, 3, -1},
                {-1, 0, 3, 6, -1, -1},
                {-1, -1, 0, -1, 5, -1},
                {-1, -1, 4, 0, -1, 15},
                {-1, -1, -1, 2, 0, 4},
                {-1, -1, -1, -1, -1, 0}
        };

        int[] distance = dijkstra.minimumDistance(map);

        for (int i = 0; i < distance.length; i++) {
            System.out.println("min path:0-->" + i + " = " + distance[i]);
        }

    }
}
