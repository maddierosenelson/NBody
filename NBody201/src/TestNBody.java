import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;


public class TestNBody {
	
	private static final double[][] planets = new double[][] { { 1.4956294976553436E11, 2.9798154903465266E9 },
															   { 2.2788403506932733E11, 2.409957795285363E9 },
															   { 5.765266792231076E10, 4.784879361438483E9 },
															   { 330.8671434437699, 2.820200096518896 },
															   { 1.0812917648253027E11, 3.499427153160241E9 }};
															   
	private static final double[][] threeBody = new double[][] { { 4.977356489506579E7, 22736.008698348087 },
																 { 2.998293541487213E9, 4.489764826524645E10 },
																 { -2.998293540807111E9, -4.4897648265314735E10 }};
																 
	private static final double[][] binaryStars = new double[][] { { -8.997440884402254E10, -1.9998578235097435E9 },
																   { 8.997440884399228E10, 1.9998578235105014E9 },
																   { -1.294648430211896E11, -7.980330229532566E9 },
																   { 1.294648430210403E11, 7.98033022954817E9 },
																   { -1.5981776268499698E11, 5.495433931690655E9 }};

	private static final double[] forces = new double[] { 6.67E-11, 8.308650519031142E-22, 3.062755102040816E-32,
			2.1396302071730896E-42, 1.2125741699280394E-52, 1.8898333333333335E-5, 2.3541176470588237E-16,
			8.677806122448979E-27, 6.06228558699042E-37, 3.4356268147961116E-47, 3.1126666666666667,
			3.877370242214533E-11, 1.4292857142857144E-21, 9.984940966807753E-32, 5.6586794596641836E-42,
			372408.3333333334, 4.638996539792388E-6, 1.7100382653061224E-16, 1.1946268656716417E-26,
			6.7702057820982194E-37, 4.946916666666667E10, 0.6162249134948097, 2.2715433673469388E-11,
			1.586892403653375E-21, 8.993258426966292E-32, 1.889833333333333E-5, 2.354117647058823E-16,
			8.677806122448979E-27, 6.0622855869904195E-37, 3.435626814796111E-47, 5.354527777777778, 6.67E-11,
			2.4587117346938774E-21, 1.7176475829806192E-31, 9.73427597525565E-42, 881922.2222222222,
			1.0985882352941177E-5, 4.0496428571428574E-16, 2.829066607262196E-26, 1.6032925135715186E-36,
			1.0551569444444444E11, 1.3143823529411764, 4.845108418367347E-11, 3.3847761194029847E-21,
			1.9182249715944957E-31, 1.401626388888889E16, 174597.0588235294, 6.436039540816327E-6,
			4.496195143684562E-16, 2.5480898876404492E-26, 3.1126666666666667, 3.877370242214533E-11,
			1.4292857142857144E-21, 9.984940966807753E-32, 5.6586794596641836E-42, 881922.2222222222,
			1.0985882352941177E-5, 4.0496428571428574E-16, 2.829066607262196E-26, 1.6032925135715186E-36,
			1.4525777777777777E11, 1.809439446366782, 6.67E-11, 4.6596391178436174E-21, 2.640717081176619E-31,
			1.7379055555555556E16, 216486.5051903114, 7.980178571428572E-6, 5.574925373134328E-16,
			3.1594293649791693E-26, 2.308561111111111E21, 2.8757162629757782E10, 1.0600535714285713,
			7.405497883715748E-11, 4.1968539325842695E-21, 372408.3333333334, 4.638996539792388E-6,
			1.7100382653061224E-16, 1.1946268656716417E-26, 6.7702057820982194E-37, 1.0551569444444444E11,
			1.3143823529411764, 4.845108418367347E-11, 3.3847761194029847E-21, 1.9182249715944957E-31,
			1.7379055555555556E16, 216486.5051903114, 7.980178571428572E-6, 5.574925373134328E-16,
			3.1594293649791693E-26, 2.079279861111111E21, 2.5901064013840828E10, 0.9547713647959183,
			6.669999999999999E-11, 3.7800315616715056E-21, 2.7620284722222225E26, 3.440589100346021E15,
			126827.8380102041, 8.860149253731344E-6, 5.02123595505618E-16, 4.946916666666667E10, 0.6162249134948097,
			2.2715433673469388E-11, 1.586892403653375E-21, 8.993258426966292E-32, 1.401626388888889E16,
			174597.0588235294, 6.436039540816327E-6, 4.496195143684562E-16, 2.5480898876404492E-26,
			2.308561111111111E21, 2.8757162629757782E10, 1.0600535714285713, 7.405497883715748E-11,
			4.1968539325842695E-21, 2.7620284722222225E26, 3.440589100346021E15, 126827.8380102041,
			8.860149253731344E-6, 5.02123595505618E-16, 3.6689631944444445E31, 4.570334775086505E20,
			1.6847279974489796E10, 1.176945199376253, 6.67E-11 };
	
	private static final double[] distances = new double[] { 0.0, 1.0, 2.0, 1.0, 1.4142135623730951, 2.23606797749979,
			2.0, 2.23606797749979, 2.8284271247461903, 4.949747468305833, 1.4142134209517388E7, 1.0, 0.0, 1.0,
			1.4142135623730951, 1.0, 1.4142135623730951, 2.23606797749979, 2.0, 2.23606797749979, 4.301162633521313,
			1.4142133502410624E7, 2.0, 1.0, 0.0, 2.23606797749979, 1.4142135623730951, 1.0, 2.8284271247461903,
			2.23606797749979, 2.0, 3.8078865529319543, 1.4142132795303896E7, 1.0, 1.4142135623730951, 2.23606797749979,
			0.0, 1.0, 2.0, 1.0, 1.4142135623730951, 2.23606797749979, 4.301162633521313, 1.4142133502410624E7,
			1.4142135623730951, 1.0, 1.4142135623730951, 1.0, 0.0, 1.0, 1.4142135623730951, 1.0, 1.4142135623730951,
			3.5355339059327378, 1.4142132795303825E7, 2.23606797749979, 1.4142135623730951, 1.0, 2.0, 1.0, 0.0,
			2.23606797749979, 1.4142135623730951, 1.0, 2.9154759474226504, 1.4142132088197062E7, 2.0, 2.23606797749979,
			2.8284271247461903, 1.0, 1.4142135623730951, 2.23606797749979, 0.0, 1.0, 2.0, 3.8078865529319543,
			1.4142132795303896E7, 2.23606797749979, 2.0, 2.23606797749979, 1.4142135623730951, 1.0, 1.4142135623730951,
			1.0, 0.0, 1.0, 2.9154759474226504, 1.4142132088197062E7, 2.8284271247461903, 2.23606797749979, 2.0,
			2.23606797749979, 1.4142135623730951, 1.0, 2.0, 1.0, 0.0, 2.1213203435596424, 1.4142131381090263E7,
			4.949747468305833, 4.301162633521313, 3.8078865529319543, 4.301162633521313, 3.5355339059327378,
			2.9154759474226504, 3.8078865529319543, 2.9154759474226504, 2.1213203435596424, 0.0, 1.414212925976992E7,
			1.4142134209517388E7, 1.4142133502410624E7, 1.4142132795303896E7, 1.4142133502410624E7,
			1.4142132795303825E7, 1.4142132088197062E7, 1.4142132795303896E7, 1.4142132088197062E7,
			1.4142131381090263E7, 1.414212925976992E7, 0.0 };

	@Test
	public void testForce() {
		NBody submission = new NBody();
		int count = 0;
		double[] values = new double[] { 1.2, 3.4E5, 5.6E10, 6.7E15, 8.9E20 };
		for(int x = 0; x < values.length; x++) {
			for(int y = 0; y < values.length; y++) {
				for(int r = 0; r < values.length; r++) {
					assertEquals(forces[count], submission.force(values[x], values[y], values[r]), Math.abs(forces[count++]) * .0001);
				}
			}
		}
	}

	@Test
	public void testDistance() {
		NBody submission = new NBody();
		int count = 0;
		double[] x = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4.5, 1E7};
		double[] y = {1, 2, 3, 1, 2, 3, 1, 2, 3, 4.5, 1E7};
		for(int case1 = 0; case1 < x.length; case1++)
			for(int case2 = 0; case2 < y.length; case2++){
				assertEquals(distances[count],
						submission.distance(x[case1], y[case1], x[case2], y[case2]), Math.abs(distances[count++]) * .0001);
			}
	}

	@Test (timeout = 10000)
	public void testNBodyPositions(){
		fileTest("planets.txt", 100000, 25000, planets);
		fileTest("3body.txt", 100000, 25000, threeBody);
		fileTest("binaryStars.txt", 100000, 25000, binaryStars);
	}

	private void fileTest(String filename, int duration, int step, double[][] answer){
		NBody submission = new NBody();
		try {
			double[][] subResults = submission.positions(
					new Scanner(new File("data/"+filename)), duration, step);
			assertEquals(subResults.length, answer.length);
			for(int i = 0; i < answer.length; i++){
				assertEquals(subResults[i].length, answer[i].length);
				for (int j = 0; j < answer[i].length; j++) {
					assertEquals(subResults[i][j], answer[i][j], Math.abs(answer[i][j]) * .0001);
				}
			}
		}
		catch (FileNotFoundException e) {
			assertTrue(false);
		}
	}
}
