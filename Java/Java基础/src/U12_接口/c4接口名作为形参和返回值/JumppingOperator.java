package U12_接口.c4接口名作为形参和返回值;

public class JumppingOperator {
    void useJumpping(Jumpping j) {
        j.jump();
    }

    public Jumpping getJumpping() {
        Jumpping j = new Cat();
        return j;
    }
}
