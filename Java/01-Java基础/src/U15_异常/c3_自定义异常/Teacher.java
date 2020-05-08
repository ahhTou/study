package U15_异常.c3_自定义异常;

public class Teacher {
    public void checkScore(int score) throws ScoreException {
        if (score < 0 || score > 100) {
            // 需要手动抛出异常
            throw new ScoreException("你给的分数有误，应该在0-100之间");
        } else {
            System.out.println("分数正常");
        }
    }
}
