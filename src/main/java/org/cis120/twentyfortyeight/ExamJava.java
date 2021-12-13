package org.cis120.twentyfortyeight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class ExamJava {
    public static class sp19 {
        static class ExnA extends RuntimeException {
        }

        static class ExnB extends RuntimeException {
        }

        public static void m1() {
            System.out.println("begin m1");
            try {
                System.out.println("calling m2");
                m2();
                System.out.println("returned from m2");
            } catch (ExnA e) {
                System.out.println("m1 caught ExnA");
            } catch (ExnB e) {
                System.out.println("m1 caught ExnB");
            }
            System.out.println("end m1");
        }

        static void m2() {
            System.out.println("begin m2");
            try {
                System.out.println("calling m3");
                m3();
                System.out.println("returned from m3");
            } catch (ExnA e) {
                System.out.println("m2 caught ExnA");
                System.out.println("about to throw ExnB");
                throw new ExnB();
            } catch (ExnB e) {
                System.out.println("m2 caught ExnB");
            }
            System.out.println("end m2");
        }

        static void m3() {
            System.out.println("begin m3");
            try {
                System.out.println("about to throw ExnA");
                throw new ExnA();
            } catch (ExnB e) {
                System.out.println("m3 caught ExnB");
            }
            System.out.println("end m3");
        }

        interface Predicate<A> {
            boolean test(A arg);
        }

        static <A> boolean iforall(Iterator<A> i, Predicate<A> pred) {
            while (i.hasNext()) {
                if (!pred.test(i.next())) {
                    return false;
                }
            }
            return true;
        }

        static class FilterIterator<A> implements Iterator<A> {
            // Field declarations here:
            private Iterator<A> b;
            private Predicate<A> p;
            private A current;

            private void findNext() {
                current = null;
                while (b.hasNext()) {
                    A item = b.next();
                    if (p.test(item)) {
                        current = item;
                        break;
                    }
                }
            }

            public FilterIterator(Iterator<A> b, Predicate<A> p) {
                this.b = b;
                this.p = p;
                current = null;
            }

            public boolean hasNext() {
                findNext();
                return current != null;
            }

            public A next() {
                return current;
            }
        }

        public static class TestPredicate implements Predicate<Integer> {

            @Override
            public boolean test(Integer num) {
                return num > 0;
            }
        }

        public static void testIforAll() {
            List<Integer> testList1 = Arrays.asList(1, 2, -1, 0, 1);
            boolean result = iforall(testList1.listIterator(), new TestPredicate());
            System.out.println("1,2,-1,0,1 should predicate false: " + result);

            List<Integer> testList2 = Arrays.asList(1, 2, 21, 10, 1);
            boolean result2 = iforall(testList2.listIterator(), new TestPredicate());
            System.out.println("1,2,-1,0,1 should predicate false: " + result2);
        }

        public static void testFilterIterator() {
            List<Integer> testList1 = Arrays.asList(1, 2, -1, 0, 1);
            FilterIterator<Integer> filterIterator = new FilterIterator<>(testList1.listIterator(), new TestPredicate());
            System.out.println("Filter iterator should be : 1,2,1");
            while (filterIterator.hasNext()) {
                System.out.println(filterIterator.next());
            }
        }

        public static class Student implements Comparable<Student> {
            public static int code;
            public int id;
            private String name;
            private String major;

            public Student(int id, String name, String major) {
                this.id = id;
                this.name = name;
                this.major = major;
                code = 20;
            }

            @Override
            public int compareTo(Student o) {
                return id - o.id;
            }

            @Override
            public boolean equals(Object o) {
                Student other = (Student) o;
                return o == this || other.id == id && other.name.equals(name) && other.major.equals(major);
            }

            public int getId(Student student) {
                return student.id;
            }
        }

        public static class StudentA extends Student {
            private int passwd;

            public StudentA(int id, String name, String major) {
                super(id, name, major);
            }

            @Override
            public int getId(Student student) {
                return student.id;
            }
        }

        public static void test1() {
            LinkedList<Student> l = new LinkedList<Student>();
            l.add(new Student(1610, "Yakkety Yak", "CSCI"));
            boolean result = l.contains(new Student(1610, "Yakkety Yak", "CSCI"));
            System.out.println("l.contains : " + result);
        }

        public static void test2() {
            TreeSet<Student> s = new TreeSet<>();
            s.add(new Student(606, "Dapper Drake", "MSE"));
            Student a = new Student(1, "1", "1");
            StudentA b = new StudentA(2, "2", "1");
            //System.out.println("case 1: " + a.code + ":" + b.code);
            //b.code = 30;
            //System.out.println("case 2: " + a.code + ":" + b.code);

            System.out.println(((Student) b).id);
            System.out.println(((StudentA) a).id);
            String a1 = "test22433";
            String b2 = "eheejeje";
            a1.hashCode();
            // a1 -> 200;
            // b1 -> 300;
            // c1 = "tewsttknenneneneene"; -> 200;
            // 200 -> LinkList -> (a1, c1, d1)
            Map<String, String> map = new HashMap<>();
            map.put("a1", "b2");

        }
    }
}
