package Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Overworld implements MapHandler{
    int[][] map;

    public Overworld() {
        try {
            LinkedList<LinkedList<Integer>> tempNestList = new LinkedList<>();
            Scanner fileReader = new Scanner(new File("Resource/mapLayouts/overworld.txt"));
            for(int i = 0; fileReader.hasNextLine(); i++) {
                tempNestList.add(new LinkedList<Integer>());
                Scanner lineScanner = new Scanner(fileReader.nextLine());
                while(lineScanner.hasNextInt()) {
                    tempNestList.get(i).add(lineScanner.nextInt());
                }
                lineScanner.close();
            }

            map = new int[tempNestList.size()][tempNestList.get(0).size()];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = tempNestList.get(i).get(j);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int[][] getMap() {
        return this.map;
    }

    @Override
    public int getMapHeight() {
        return this.map.length;
    }

    @Override
    public int getMapLength() {
        return this.map[0].length;
    }

    @Override
    public int getSpot(int x, int y) {
        return this.map[y][x];
    }
}
