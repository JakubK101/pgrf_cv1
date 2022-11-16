import objectdata.Point2D;
import objectdata.Polygon2D;
import rasterdata.Presentable;
import rasterdata.RasterImage;
import rasterdata.RasterImageBI;
import rasterops.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu
 * 
 * @author PGRF FIM UHK
 * @version 2022
 */

public class Canvas {

	private JFrame frame;
	private JPanel panel;
	private final RasterImage<Integer> img;

	private final Presentable<Graphics> presenter;
	private final Liner<Integer> liner;
	private Polygoner<Integer> polygoner;

	private Polygon2D polygon;

	private SeedFill4<Integer> seedFill4;
	private SeedFill seedFill8;

	private ScanLine<Integer> scanLine;
	private int c1,r1;



	public Canvas(int width, int height) {
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




		polygon = new Polygon2D();
		polygoner = new Polygoner<Integer>();
		seedFill4= new SeedFill4<Integer>();
		seedFill8= new SeedFill8();
		scanLine = new ScanLineImpl<Integer>();

        //img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final RasterImageBI auxRasterImage = new RasterImageBI(width, height);
        img = auxRasterImage;
        presenter = auxRasterImage;
        liner = new TrivialLiner<>();      //přiřazení do lineru - TrivialLiner(plná čára) / DashedTrivialLiner(přerušovaná)

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
                /*clear();
                liner.drawLine(img, c1, r1, e.getX(), e.getY(), 0xff0000);
                present();*/
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               if (e.getButton()==MouseEvent.BUTTON1) {
				   c1 = e.getX();
				   r1 = e.getY();

				   polygon.addPoint2D(new Point2D(c1, r1));

				   clear();
				   polygoner.drawPolygon(polygon, img, 0xff0000, liner);
				   present();
			   } else if (e.getButton()==MouseEvent.BUTTON3) {
				   c1 = e.getX();
				   r1 = e.getY();
				   //seedFill4.fill(img,c1,r1,0xff0000);

				   present();


			   }

			}

        });

		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_C){
				clear();
				polygon.getPoints().clear();
				present();}
			}
		});

		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_S){
					scanLine.fill(img,polygon,0xff0000,polygoner,liner,0xff0000);
					present();
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