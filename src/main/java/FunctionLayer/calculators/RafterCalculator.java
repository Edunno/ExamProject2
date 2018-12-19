package FunctionLayer.calculators;

/**
 *
 * @author caspe
 */
public class RafterCalculator {

    //   public static void main(String[] args) {
//        RafterCalculator rc = new RafterCalculator();
//
//        int x = rc.RaftCalc(600, 780);
//
//        int y = rc.SpecialRaftCalc(600, 780);
//
//        System.out.println(y);
//    }
    /**
     *
     * calculates rafts for flat roof
     *
     * @param dimensionA length of carport
     * @param dimensionB width of carport
     * @return amount of rafters
     */
    public int RaftCalc(double dimensionA, double dimensionB) {
        int rafts = 1;

        if (dimensionA > dimensionB) {

            for (double i = 0.55; i < dimensionA; i += 0.55) {
                rafts++;

            }
        } else if (dimensionA < dimensionB) {
            for (double i = 0.55; i < dimensionB; i += 0.55) {
                rafts++;
            }
        } else {
            for (double i = 0.55; i < dimensionA; i += 0.55) {
                rafts++;
            }
        }

        return rafts;
    }

    /**Finds the length of the rafters
     *
     * @param dimensionA length of carport
     * @param dimensionB width of carport
     * @return length of a single rafter
     */
    public double RaftLength(double dimensionA, double dimensionB) {
        double rl;
        if (dimensionA > dimensionB) {
            rl = dimensionB;
        } else if (dimensionA < dimensionB) {
            rl = dimensionA;
        } else {
            rl = dimensionA;
        }

        return rl;
    }

    /**
     * Calculates rafts for roof with angle
     *
     *
     * @param dimensionA length of carport
     * @param dimensionB width of carport
     * @return amount of rafters
     */
    public int SpecialRaftCalc(double dimensionA, double dimensionB) {
        int rafts = 1;
        if (dimensionA > dimensionB) {

            for (double i = 0.89; i < dimensionA; i += 0.89) {
                rafts++;

            }
        } else if (dimensionA < dimensionB) {
            for (double i = 0.89; i < dimensionB; i += 0.89) {
                rafts++;
            }
        } else {
            for (double i = 0.89; i < dimensionA; i += 0.89) {
                rafts++;
            }
        }
        return rafts * 2;

    }

}
