package com.example.admin.bmicalculator;

/**
 * Created by admin on 2015/06/25.
 */
public class bmiCalculator {
    private double myWeight;
    private double myHeight;
    private double myBmi;

    public void setHeight(double height) {
        myHeight = height;
    }

    public void setWeight(double weight) {
        myWeight = weight;
    }

    //BMI計算部分
    public double Caluclator() {
        myBmi = myWeight / ((myHeight / 100) * (myHeight / 100));

        //小数点第二位まで表示
        myBmi = Math.round(myBmi * 100);
        myBmi = myBmi / 100;

        return myBmi;
    }

    public double StandardWeight() {
        double sWeight;
        sWeight = 22 * (myHeight / 100) * (myHeight / 100);
        sWeight = Math.round(sWeight * 100);
        sWeight = sWeight / 100;

        return sWeight;
    }

    public String bmiJudg(double bmi){
        if (bmi < 17) {
            //痩せ過ぎ
            String strJudge = String.valueOf("痩せ過ぎ");
            return strJudge;
        } else if (bmi <= 17 || bmi < 18.5) {
            //"痩せ気味"
            String strJudge = String.valueOf("痩せ気味");
            return strJudge;
        } else if (bmi <= 18.5 || bmi < 25) {
            //"普通"
            String strJudge = String.valueOf("普通");
            return strJudge;
        } else if (bmi <= 25 || bmi < 30) {
            //"太り気味"
            String strJudge = String.valueOf("太り気味");
            return strJudge;
        } else{
            //"太り過ぎ"
            String strJudge = String.valueOf("太り過ぎ");
            return strJudge;
        }
    }
}
