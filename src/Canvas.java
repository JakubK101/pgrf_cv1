import objectdata.Point2D;
import objectdata.Polygon2D;
import rasterdata.Presentable;
import rasterdata.RasterImage;
import rasterdata.RasterImageBI;
import rasterops.DashedTrivialLiner;
import rasterops.Liner;
import rasterops.Polygoner;
import rasterops.TrivialLiner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu
 * 
 * @author PGRF FIM UHK
 * @version 2020
 */

public class Canvas {

	private JFrame frame;
	private JPanel panel;
	private final RasterImage<Integer> img;

	private final Presentable<Graphics> presenter;
	private final Liner<Integer> liner;
	private Polygoner<Integer> polygoner;

	private Polygon2D polygon;
	private int c1,r1,c2,r2;

	public Canvas(int width, int height) {
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		polygon = new Polygon2D();
		polygoner = new Polygoner<Integer>();

        //img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final RasterImageBI auxRasterImage = new RasterImageBI(width, height);
        img = auxRasterImage;
        presenter = auxRasterImage;
        liner = new TrivialLiner<>();
        //liner= new DashedTrivialLiner<>();

		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};


		panel.setPreferredSize(new Dimension(width, height));


        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //clear();
                //liner.drawLine(img, c1, r1, e.getX(), e.getY(), 0xff0000);
                //present();
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                c1 = e.getX();
                r1 = e.getY();

                if (triangleMode) {
                    frame.setTitle("Triangle mode");
                    if (polygon.getPoints().size() < 3){
                        polygon.addPoint2D(new Point2D(c1, r1));


                    }
                    else if (polygon.getPoints().size() ==2){
                        //Střed mezi 2 body
                        Point2D point1 = polygon.getPoint(0);
                        Point2D point2 = polygon.getPoint(1);

                        float v1 = point2.getX()- point1.getX();
                        float v2 = point2.getY()-point1.getY();

                        float v3 = -v2; //směr
                        float v4 = v1;

                        Point2D centerPoint = new Point2D((point1.getX()-point2.getX())/2, (point1.getY()-point2.getY()) /2);

                        Point2D mousePoint = new Point2D(c1,r1);

                        float u1 = centerPoint.getX()- mousePoint.getX();
                        float u2 = centerPoint.getY()-mousePoint.getY();

                        double vectorSize = Math.sqrt(Math.pow(u1, 2.0) + Math.pow(u2, 2.0)); // velikost




                        clear();
                        polygoner.drawPolygon(polygon, img, 0xff0000, liner);
                        present();
                    }
                } else {
                  // polygon.addPoint2D(new Point2D(c1, r1));
                  //
                  // clear();
                  // polygoner.drawPolygon(polygon, img, 0xff0000, liner);
                  // present();
                }
            }
        });

		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_C){
				clear();
				present();}
			}
		});

		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_T){

				}
			}
		});

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

		panel.grabFocus();
	}

	public void clear() {
		img.clear(0x2f2f2f);
	}

	public void present(Graphics graphics) {
		presenter.present(graphics);
	}

	public void present(){
		final Graphics g = panel.getGraphics();
		if(g!=null){

		presenter.present(g);}
	}
	public void draw() {



	}

	public void start() {
		draw();
		panel.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->
				new Canvas(800, 600).start());
	}

}