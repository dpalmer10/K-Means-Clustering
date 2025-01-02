/*
    Daniel Palmer
    Record.java
    8/9/2023
 */
public record Record(double[] arr) {

    @Override
    public String toString() {
        String str = "";
        for (double v : arr) {
            str += v + ", ";
        }
        str = str.substring(0, str.length() - 2);
        return str;
    }
}
