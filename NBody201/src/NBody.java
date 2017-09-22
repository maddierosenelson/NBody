import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import princeton.StdAudio;
import princeton.StdDraw;

public class NBody{
	
	public static final double G = 6.67E-11;
	/**
	 * returns Euclidean distance between (x1, y1) and (x2, y2)
	 * @param x1
	 *            x-coordinate of point 1
	 * @param y1
	 *            y-coordinate of point 1
	 * @param x2
	 *            x-coordinate of point 2
	 * @param y2
	 *            y-coordinate of point 2
	 * @return Euclidean distance between (x1, y1) and (x2, y2)
	 */
	public double distance(double x1, double y1, double x2, double y2) {
		//TODO: Complete distance
		double dist=0;
		dist=Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
		return dist;
	}
	/**
	 * return the magnitude of the gravitational force between two bodies of
	 * mass m1 and m2 that are distance r apart
	 * @param m1
	 *            mass of body 1 in kg
	 * @param m2
	 *            mass of body 2 in kg
	 * @param r
	 *            distance in m
	 * @return force between m1 and m2 that are distance r apart
	 */
	public double force(double m1, double m2, double r) {
		//TODO: Complete force
		double force=0;
		//does this r use the distance method I calculated above??
		if (r!=0){
			force=(G*m1*m2)/(r*r);
		}
		if(r==0){
			force=0;
		}
		return force;
	}
	/**
	 * Returns the x positions and y positions of bodies
	 * @param totalTime The total amount of universe time to run for
	 * @param timeStep The value of delta t in the equations to calculate position
	 * @param info The scanner with info about the initial conditions of the
	 * bodies
	 * @return an array whose first element is the x and y position of the first body, and so on
	 * t = totalTime
	 */
	public double[][] positions(Scanner info, int totalTime, int timeStep) {
		//TODO: Complete positions. asking for final position after entire program runs. 
		int numbodies=0;
		numbodies= info.nextInt();
		double R=0;
		R= info.nextDouble();
		StdDraw.setXscale(-R,R);
		StdDraw.setYscale(-R,R);
		double[] px= new double[numbodies];
		double[] py= new double[numbodies];
		double[] vx= new double[numbodies];
		double[] vy= new double[numbodies];
		double[] mass= new double[numbodies];
		String[] image= new String[numbodies];
		double[][] output = new double[numbodies][2]; //Replace 0 with the number of bodies, read from the file
		for (int i=0; i<numbodies; i++){
			px[i]=info.nextDouble();
			py[i]=info.nextDouble();
			vx[i]=info.nextDouble();
			vy[i]=info.nextDouble();
			mass[i]=info.nextDouble();
			image[i]=info.next();
		}
		//		System.out.println(px.length);
		//				for(double a : py){
		//					System.out.println(a);
		//				}
		for (int t=0; t<totalTime; t=t+timeStep){
				double F;
				double[] sumFx= new double[numbodies];
				double[] sumFy=new double[numbodies];
				double[] ax=new double[numbodies];
				double[] ay=new double[numbodies];
				StdDraw.picture(0, 0, "data/starfield.jpg");

				for (int i=0;i<numbodies;i++){
				StdDraw.picture(px[i],py[i], "data/"+image[i]);
				}
			for(int i=0; i<numbodies; i++){
				for(int j=0; j<numbodies; j++){
					if (i!=j){
					F=force(mass[i],mass[j], distance(px[i],py[i],px[j],py[j]));
					sumFx[i]= F*((px[j]-px[i])/distance(px[i],py[i],px[j],py[j]))+sumFx[i];
					sumFy[i]= F*((py[j]-py[i])/distance(px[i],py[i],px[j],py[j]))+sumFy[i];		
					}
					}
			}
				for(int k=0; k<numbodies; k++){
					ax[k]=sumFx[k]/mass[k];
					ay[k]=sumFy[k]/mass[k];
					vx[k]=vx[k]+ax[k]*timeStep;
					vy[k]=vy[k]+ay[k]*timeStep;					
				px[k]=px[k]+timeStep*vx[k];
				py[k]=py[k]+timeStep*vy[k];
				}
			}
		
		for(int m=0; m<numbodies; m++){
			output[m][0]=px[m];
			output[m][1]=py[m];
		}
		return output;
	
    }

    public static void main(String[] args) {
        Scanner info = openFile();
        int time = 10000000;
        int dt = 25000;
        
        if (info != null) {
            //StdAudio.play("data/2001.mid");
            NBody myNBody = new NBody();
            double[][] results = myNBody.positions(info, time, dt);
            for(int i = 0; i < results.length; i++) {
                for(int j = 0; j < results[i].length; j++) {
                    System.out.print(results[i][j]+" ");
                }
                System.out.println();
            }
            StdAudio.close();
        }
    }
    /**
     * Displays file chooser for browsing in the project directory. and opens
     * the selected file
     *
     * @return a new Scanner that produces values scanned from the selected
     *         file. null if file could not be opened or was not selected
     */
    
    public static Scanner openFile() {
        Scanner scan = null;
        System.out.println("Opening file dialog.");
        JFileChooser openChooser = new JFileChooser(System.getProperties()
                                                    .getProperty("user.dir"));
        
        int retval = openChooser.showOpenDialog(null);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = openChooser.getSelectedFile();
            System.out.println(file.getAbsolutePath());
            try {
                scan = new Scanner(file);
                System.out.println("Opening: " + file.getName() + ".");
            } catch (FileNotFoundException e) {
                System.out.println("Could not open selected file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File open canceled.");
            System.exit(0);
        }
        
        return scan;
    }
}
//wilson zhang
