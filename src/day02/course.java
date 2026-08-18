package day02;


import java.util.*;

/**
 * 现有两门选修课，每门选修课都有一部分学生选修，每个学生都有选修课的成绩，需要你找出同时选修了两门选修课的学生，
 * 先按照班级进行划分，班级编号小的先输出，每个班级按照两门选修课成绩和的降序排序，成绩相同时按照学生的学号升序排序。
 *
 *输入
 *
 * 第一行为第一门选修课学生的成绩，第二行为第二门选修课学生的成绩，每行数据中学生之间以英文分号分隔，每个学生的学号和成绩以英文逗号分隔，
 * 学生学号的格式为 8 位数字(2 位院系编号+入学年份后 2 位+院系内部 1 位专业编号+所在班级 3 位学号)，
 * 学生成绩的取值范围为 [0,100] 之间的整数，两门选修课选修学生数的取值范围为 [1-2000] 之间的整数。
 *
 *
 * 输出
 *
 * 同时选修了两门选修课的学生的学号，如果没有同时选修两门选修课的学生输出 NULL，
 * 否则，先按照班级划分，班级编号小的先输出，每个班级先输出班级编号(学号前五位)，
 * 然后另起一行输出这个班级同时选修两门选修课的学生学号，
 * 学号按照要求排序(按照两门选修课成绩和的降序，成绩和相同时按照学号升序)，学生之间以英文分号分隔。
 */
public class course {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] one = sc.nextLine().split(";");
            String[] two = sc.nextLine().split(";");
            Map<String, Integer> tIds = new HashMap<>();
            for (String t : two) {
                String[] tStu = t.split(",");
                String tId = tStu[0];
                int tScore = Integer.parseInt(tStu[1]);
                tIds.put(tId, tScore);
            }
            Comparator<Student> stuComparator = Comparator.comparingInt(Student::getScore).thenComparing(Student::getId);
            TreeMap<String, TreeSet<Student>> map = new TreeMap<>();
            for (String s : one) {
                String[] sStu = s.split(",");
                String sId = sStu[0];
                if (tIds.containsKey(sId)) {
                    int sScore = Integer.parseInt(sStu[1]);
                    int tScore = tIds.get(sId);
                    int totalScore = sScore + tScore;
                    String cls = sId.substring(0, 5);
                    Student student = new Student(sId, totalScore);
                    map.computeIfAbsent(cls, k -> new TreeSet<>(stuComparator)).add(student);
                }
            }
            if (map.isEmpty()) {
                System.out.println("NULL");
            } else {
                map.forEach((key, value) -> {
                    System.out.println(key);
                    String res = String.join(";", value.stream().map(Student::getId).toArray(String[]::new));
                    System.out.println(res);
                });
            }
        }
    }

    static class Student {
        String id;
        int score;

        public Student(String id, int score) {
            this.id = id;
            this.score = score;
        }

        public String getId() {
            return id;
        }

        public int getScore() {
            return score;
        }
    }
}
