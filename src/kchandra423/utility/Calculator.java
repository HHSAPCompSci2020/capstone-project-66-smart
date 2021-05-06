package kchandra423.utility;

public class Calculator {
    public static double calculateAngle(double x1, double y1, double x2, double y2) {
        double answer = Math.atan2(y2-y1,x2-x1);
//        if(answer<0){
//            answer+=Math.PI*2;
//        }
        return answer;
    }
}
