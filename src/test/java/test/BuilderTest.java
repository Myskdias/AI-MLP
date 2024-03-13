package test;

public class BuilderTest {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("gdegedge");
        builder.delete(builder.length()-2, builder.length());
        System.out.println(builder.toString());
    }

}
