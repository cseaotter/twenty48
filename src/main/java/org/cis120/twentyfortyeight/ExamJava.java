package org.cis120.twentyfortyeight;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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

    public static class sp16 {
        interface I {
            public int getField();
        }

        static class J implements I {
            private int field;

            static public int frob(I oi, J oj) {
                oj.field = 17;
                return oi.getField();
            }

            public int getField() {
                return field;
            }
        }

        public static void testIJ() {
            I i = new J();
            System.out.println("expect 17:" + J.frob(i, (J) i));
        }
    }

    public static class fa19 {

        interface Driveable {
            public String getName();

            public void drive();

            public void fly();
        }

        interface Flyable {
            public void fly();
        }

        static class Car implements Driveable {

            public String getName() {
                return "Car";
            }

            public void drive() {
                System.out.println(getName() + " says let’s go for a drive");
            }

            @Override
            public void fly() {

            }
        }

        static class WeasleyFamilyCar extends Car implements Flyable {

            public String getName() {
                return "WeasleyFamilyCar";
            }

            public void fly() {
                System.out.println(getName() + " says let’s avoid the Whomping Willow");

            }
        }

        static class HagridsMotorcycle implements Flyable {

            public void fly() {
                System.out.println("Hagrid says I was borrowed from Sirius Black");
            }
        }

        public static class GrepResult {
            public final int lineNumber;
            public final String line;

            public GrepResult(int lineNumber, String line) {
                this.lineNumber = lineNumber;
                this.line = line;
            }

            @Override
            public String toString() {
                return (String.valueOf(lineNumber) + ":" + line);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                GrepResult other = (GrepResult) obj;
                if (line == null) {
                    if (other.line != null)
                        return false;
                } else if (!line.equals(other.line))
                    return false;
                if (lineNumber != other.lineNumber)
                    return false;
                return true;
            }
        }

        public static class GrepIterator implements Iterator<GrepResult> {
            private final BufferedReader br;
            private final String keyword;
            private int lineNumber;
            private String line;

            public GrepIterator(Reader r, String keyword) {
                br = new BufferedReader(r);
                this.keyword = keyword;
                lineNumber = 0;
            }

            @Override
            public boolean hasNext() {
                advance();
                return line != null;
            }

            @Override
            public GrepResult next() {
                return new GrepResult(lineNumber, line);
            }

            private void advance() {
                line = null;
                try {
                    String line = null;
                    do {
                        line = br.readLine();
                        lineNumber++;
                        if (line != null && line.contains(keyword)) {
                            this.line = line;
                            break;
                        }
                    } while (line != null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void test() {
            Car d = new WeasleyFamilyCar();
            WeasleyFamilyCar motorcycle = (WeasleyFamilyCar) d;
            motorcycle.fly();

            String fileName = "fileName";
            FileReader f = null;
            try {
                f = new FileReader(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            GrepIterator g = new GrepIterator(f, "keyword");
            while (g.hasNext()) {
                GrepResult gr = g.next();
                System.out.println(gr);
            }
        }
    }

    static class LightBulb extends JComponent {
        private boolean isOn = false;

        public void flip() {
            isOn = !isOn;
        }

        @Override
        public void paintComponent(Graphics gc) {
            if (isOn) {
                gc.setColor(Color.YELLOW);
            } else {
                gc.setColor(Color.BLACK);
            }
            gc.fillRect(0, 0, 100, 100);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 100);
        }
    }

    public static class OnOff implements Runnable {
        public void run() {
            JFrame frame = new JFrame("On/Off Switch");

            JPanel panel = new JPanel();
            frame.getContentPane().add(panel);

            JButton button = new JButton("DoIt!");
            panel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LightBulb bulb = new LightBulb();
                    JButton button = new JButton("On/Off");
                    panel.add(bulb);
                    panel.add(button);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            bulb.flip();
                            bulb.repaint();
                        }
                    });
                    frame.pack();
                    frame.repaint();
                }
            });
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }

        public static void test() {
            SwingUtilities.invokeLater(new OnOff());
        }
    }

    public static class sp20 {
        public static abstract class GameObject {
            private int x;
            private int y;

            public GameObject(int x0, int y0) {
                x = x0;
                y = y0;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

            abstract void draw(Graphics g);

            abstract public int getWidth();

            abstract public int getHeight();

            public int getPoints(int x0, int y0) {
                boolean inBounds = (x <= x0 && x0 <= x + getWidth()
                        && y <= y0 && y0 <= y + getHeight());
                if (inBounds) {
                    return 10;
                } else {
                    return 0;
                }
            }

        }

        public static class Circle extends GameObject {
            private int radius;

            public Circle(int x0, int y0, int r0) {
                super(x0, y0);
                this.radius = r0;
            }

            @Override
            public void draw(Graphics g) {
                g.fillOval(this.getX(), this.getY(), this.radius, this.radius);
            }

            public static Circle random() {
                int r = 5 + new Random().nextInt(10);
                int x = new Random().nextInt(GameCourt.COURT_WIDTH - r);

                int y = new Random().nextInt(GameCourt.COURT_HEIGHT - r);
                return new Circle(x, y, r);
            }

            @Override
            public int getWidth() {
                return radius;
            }

            @Override
            public int getHeight() {
                return radius;
            }
        }

        public static void test() {
            List<List<Circle>> allGames = new LinkedList<List<Circle>>();
            // c1, c2, c3, c4, c5, c6
            Circle[] circles = new Circle[6];
            List<Circle> l1 = Arrays.asList(circles[0], circles[4], circles[5]);
            List<Circle> l2 = Arrays.asList(circles[1], circles[4]);
            List<Circle> l3 = Arrays.asList(circles[4], circles[5]);
            allGames.add(l1);
            allGames.add(l2);


            Iterator<List<Circle>> iter = allGames.iterator();
            while (iter.hasNext()) {
                List<Circle> list = iter.next();
                Iterator<Circle> it = list.iterator();
            }
            
        }
    }
}
