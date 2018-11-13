package calculators;

/**
 *
 * @author caspe
 */
public class RafterCalculator {

    public static void main(String[] args) {
        RafterCalculator rc = new RafterCalculator();

        int x = rc.RaftCalc(600, 780);

        int y = rc.SpecialRaftCalc(600, 780);

        System.out.println(y);

    }

    /**
     *
     * calculates rafts for flat roof
     *
     * @return
     */
    public int RaftCalc(double a, double b) {
        int rafts = 1;

        if (a > b) {

            for (int i = 55; i < a; i += 55) {
                rafts++;

            }
        } else if (a < b) {
            for (int i = 55; i < b; i += 55) {
                rafts++;
            }
        }
        return rafts;
    }

    /**
     * Calculates rafts for roof with angle
     *
     *
     * @return
     */
    public int SpecialRaftCalc(double a, double b) {
        int rafts = 1;

        if (a > b) {

            for (int i = 89; i < a; i += 89) {
                rafts++;

            }
        } else if (a < b) {
            for (int i = 89; i < b; i += 89) {
                rafts++;
            }
        }
        return rafts * 2;

    }

}
