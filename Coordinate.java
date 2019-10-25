import java.io.Serializable;
import java.util.Objects;

public class Coordinate implements Comparable<Coordinate>, Serializable {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Coordinate coordinate) {
        if (x < coordinate.x) {
            return -1;
        } else if (x > coordinate.x) {
            return 1;
        } else {
            if (y < coordinate.y) {
                return -1;
            } else if (y > coordinate.y) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate c = (Coordinate) obj;
        return x == c.x && y == c.y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(")");
        return sb.toString();
    }

}
