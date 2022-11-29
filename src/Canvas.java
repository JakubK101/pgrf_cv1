import objectdata.Cube;
import objectdata.Point2D;
import objectdata.Polygon2D;
import objectdata.Scene;
import objectops.RenderLineList;
import rasterdata.Presentable;
import rasterdata.RasterImage;
import rasterdata.RasterImageBI;
import rasterops.*;
import transforms.Camera;
import transforms.Mat4PerspRH;
import transforms.Vec2D;
import transforms.Vec3D;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Objects;
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

	private SeedFill seedFill4;
	private SeedFill seedFill8;

	private final ScanLine<Integer> scanLine;

	private RenderLineList renderer;
	private Scene scene;
	private int c1,r1;

	private int color;



	public Canvas(int width, int height) {
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		polygon = new Polygon2D();
		polygoner = new Polygoner<Integer>();
		seedFill4= new SeedFill4();
		seedFill8= new SeedFill8();
		scanLine = new ScanLineImpl<>();
		liner = new TrivialLiner<>();
		final RasterImageBI auxRasterImage = new RasterImageBI(width, height);
		img = auxRasterImage;

		scene = new Scene();
		scene.addSolid(new Cube());
		final Vec3D pos = new Vec3D(2,4,3);
		renderer = new RenderLineList<>(new Camera()
				.withPosition(pos)
				.withAzimuth(azimuthOrigin(pos))
				.withZenith(zenithOrigin(pos)),
				new Mat4PerspRH(Math.PI/4,1,0.1,200),
				0xff0000,liner,img);




        //img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        presenter = auxRasterImage;

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

				   img.getPixel(c1,r1).ifPresent(p->{
					   seedFill4.fill(img,c1,r1,color, new Predicate<Integer>() {
						   @Override
						   public boolean test(Integer pixelValue) {
							   return Objects.equals(p,pixelValue);
						   }
					   });
				   });
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

				if(e.getKeyCode()==KeyEvent.VK_1){

				}
			}
		});

		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_S){

					scanLine.fill(img,polygon,color,polygoner,liner,0xff0000);
					present();
				}
			}
		});
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_R){
					color = 0xff0000;
				}
				if(e.getKeyCode() == KeyEvent.VK_G){
					color = 0x00ff00;
				}
				if(e.getKeyCode() == KeyEvent.VK_B){
					color = 0x0000ff;
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
		renderer.renderScene(scene);
		//draw();
		panel.repaint();
	}
	private double azimuthOrigin(final Vec3D pos){
		final Vec3D v = pos.opposite();
		final double a = v.ignoreZ().normalized()
				.map(vNorm->vNorm.dot(new Vec2D(1,0)))
				.orElse(0.0);
		return v.getY() > 0 ? a :2 *Math.PI - a;


	}
	private double zenithOrigin(final Vec3D pos){
		final Vec3D v = pos.opposite();
		final double  alpha = v.normalized()
				.map(vNorm ->Math.acos(vNorm.dot(new Vec3D(0,0,1))))
				.orElse(0.0);
		return (Math.PI/2) - alpha;
	}
	//pos.dot(v); skalární součin vekorů

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->
				new Canvas(800, 600).start());
	}

}