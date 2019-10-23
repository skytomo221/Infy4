import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class LifeGame {
    public TreeSet<Coordinate> set;
    protected int generation = 1;

    public int GetGeneration() {
        return generation;
    }

    public LifeGame() {
        this.set = new TreeSet<Coordinate>();
        for (int i = 1; i < 500; i++) {
            set.add(new Coordinate(50, 50 + i));
        }
    }

    private int countAround(Coordinate c) {
        int cnt = 0;
        List<Coordinate> neighbor = Arrays.asList(new Coordinate(c.x - 1, c.y - 1), new Coordinate(c.x - 1, c.y),
                new Coordinate(c.x - 1, c.y + 1), new Coordinate(c.x, c.y - 1), new Coordinate(c.x, c.y + 1),
                new Coordinate(c.x + 1, c.y - 1), new Coordinate(c.x + 1, c.y), new Coordinate(c.x + 1, c.y + 1));
        for (Coordinate new_c : neighbor) {
            if (set.contains(new_c)) {
                cnt++;
            }
        }
        return cnt;
    }

    public void calc() {
        TreeSet<Coordinate> newset = new TreeSet<Coordinate>();
        for (Coordinate c : set) {
            int cnt = countAround(c);
            if (cnt == 2 || cnt == 3) {
                newset.add(c);
            }
        }
        TreeSet<Coordinate> checkBirthSet = new TreeSet<Coordinate>();
        for (Coordinate c : set) {
            List<Coordinate> neighbor = Arrays.asList(new Coordinate(c.x - 1, c.y - 1), new Coordinate(c.x - 1, c.y),
                    new Coordinate(c.x - 1, c.y + 1), new Coordinate(c.x, c.y - 1), new Coordinate(c.x, c.y + 1),
                    new Coordinate(c.x + 1, c.y - 1), new Coordinate(c.x + 1, c.y), new Coordinate(c.x + 1, c.y + 1));
            for (Coordinate new_c : neighbor) {
                if (!set.contains(new_c)) {
                    checkBirthSet.add(new_c);
                }
            }
        }
        for (Coordinate c : checkBirthSet) {
            int cnt = countAround(c);
            if (cnt == 3) {
                newset.add(c);
            }
        }
        set = newset;
        generation++;
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<String>();
        for (Coordinate c : set) {
            list.add(c.toString());
        }
        return String.join(", ", list);
    }

}
