import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    public ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private Point startPoint;
    private Point endPoint;
    private Timer timer;
    Thread t;
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Draw rectangles");
        setLocationRelativeTo(null);
        setSize(500, 400);
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Rectangle rect : rectangles) {
                    g.drawRect(rect.x, rect.y, rect.width, rect.height);
                    rect.fall(rect);
                }
                if (startPoint != null && endPoint != null) {
                    int x = Math.min(startPoint.x, endPoint.x);
                    int y = Math.min(startPoint.y, endPoint.y);
                    int width = Math.abs(startPoint.x - endPoint.x);
                    int height = Math.abs(startPoint.y - endPoint.y);
                    g.drawRect(x, y, width, height);
                }

                }

//            Timer timer= new Timer(50, e -> {
//                for (Rectangle rect : rectangles) {
//                    rect.fall(rect);
//                    //rect.y += 5;
//                    repaint();
//                }
//            });
        };

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                rectangles.add(new Rectangle(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y), Math.abs(startPoint.x - endPoint.x),
                        Math.abs(startPoint.y - endPoint.y)));
                startPoint = null;
                endPoint = null;
                mainPanel.repaint();
            }

//            @Override
//            public void mouseDragged(MouseEvent e) {
//                endPoint = e.getPoint();
//                mainPanel.repaint();
//            }
        });


        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);


    }
}
