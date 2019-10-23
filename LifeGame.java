import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

public class LifeGame {
    public TreeSet<Coordinate> set;

    public LifeGame() {
        this.set = new TreeSet<Coordinate>();
        for (int i = 1; i < 500; i++) {
            set.add(new Coordinate(50, 50 + i));
        }
    }

    private int countAround(Coordinate c) {
        int cnt = 0;
        if (set.contains(new Coordinate(c.x - 1, c.y - 1))) {
            cnt++;
        }
        if (set.contains(new Coordinate(c.x - 1, c.y))) {
            cnt++;
        }
        if (set.contains(new Coordinate(c.x - 1, c.y + 1))) {
            cnt++;
        }
        if (set.contains(new Coordinate(c.x, c.y - 1))) {
            cnt++;
        }
        if (set.contains(new Coordinate(c.x, c.y + 1))) {
            cnt++;
        }
        if (set.contains(new Coordinate(c.x + 1, c.y - 1))) {
            cnt++;
        }
        if (set.contains(new Coordinate(c.x + 1, c.y))) {
            cnt++;
        }
        if (set.contains(new Coordinate(c.x + 1, c.y + 1))) {
            cnt++;
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
        TreeSet<Coordinate> maybeNewSet = new TreeSet<Coordinate>();
        for (Coordinate c : set) {

            Coordinate new_c = new Coordinate(c.x - 1, c.y - 1);
            if (!set.contains(new_c)) {
                maybeNewSet.add(new_c);
            }
            new_c = new Coordinate(c.x - 1, c.y);
            if (!set.contains(new_c)) {
                maybeNewSet.add(new_c);
            }
            new_c = new Coordinate(c.x - 1, c.y + 1);
            if (!set.contains(new_c)) {
                maybeNewSet.add(new_c);
            }
            new_c = new Coordinate(c.x, c.y - 1);
            if (!set.contains(new_c)) {
                maybeNewSet.add(new_c);
            }
            new_c = new Coordinate(c.x, c.y + 1);
            if (!set.contains(new_c)) {
                maybeNewSet.add(new_c);
            }
            new_c = new Coordinate(c.x + 1, c.y - 1);
            if (!set.contains(new_c)) {
                maybeNewSet.add(new_c);
            }
            new_c = new Coordinate(c.x + 1, c.y);
            if (!set.contains(new_c)) {
                maybeNewSet.add(new_c);
            }
            new_c = new Coordinate(c.x + 1, c.y + 1);
            if (!set.contains(new_c)) {
                maybeNewSet.add(new_c);
            }
        }
        for (Coordinate c : maybeNewSet) {
            int cnt = countAround(c);
            if (cnt == 3) {
                newset.add(c);
            }
        }
        set = newset;
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
