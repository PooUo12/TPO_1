package prog;

public class SecTaylor {
    public static Double CastSecTaylor(double x){
        int sign;
        double scale = Math.pow(10, 3);
        x = Math.round(x * scale / Math.PI) / scale; // 3pi -> 3
        x = Math.abs(x - 2 * Math.round(x / 2)); // 7,5 -> 0,5(функция четная и периодическая)
        if (x > 0.5){
            sign = -1;
            x = 1 - x;
        } else {
            sign = 1;
        }
        if (x == 0.5){
            return Double.NaN;
        } else {
            return sign * Taylor(x * Math.PI);
        }
    }
    public static Double Taylor(double x){
        return 1 + Math.pow(x,2) / 2 +
                5 * Math.pow(x, 4) / 24 +
                61 * Math.pow(x, 6)/ 720 +
                277 * Math.pow(x, 8)/ 8064 +
                50521 * Math.pow(x, 16) / 3628800;
    }
}
