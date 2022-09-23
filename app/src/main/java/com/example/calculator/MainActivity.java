package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,equation;
    MaterialButton AC,C,rightbracket,leftbracket,dot,divide,multiplication,subtraction,add,equal,number0,number1,number2,number3,number4,number5,number6,number7,number8,number9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        equation = findViewById(R.id.equation);
        assignId(AC,R.id.AC);
        assignId(C,R.id.C);
        assignId(rightbracket,R.id.rightbracket);
        assignId(leftbracket,R.id.leftbracket);
        assignId(dot,R.id.dot);
        assignId(divide,R.id.divide);
        assignId(multiplication,R.id.multiplication);
        assignId(subtraction,R.id.subtraction);
        assignId(add,R.id.add);
        assignId(equal,R.id.equal);
        assignId(number0,R.id.number0);
        assignId(number1,R.id.number1);
        assignId(number2,R.id.number2);
        assignId(number3,R.id.number3);
        assignId(number4,R.id.number4);
        assignId(number5,R.id.number5);
        assignId(number6,R.id.number6);
        assignId(number7,R.id.number7);
        assignId(number8,R.id.number8);
        assignId(number9,R.id.number9);



    }
    
    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttontext = button.getText().toString();
        String tocalculate = equation.getText().toString();

        if(buttontext.equals("AC")){
            equation.setText("");
            result.setText("");
            return;
        }
        if(buttontext.equals("=")){
            String finalresult = getResult(tocalculate);

            if(!finalresult.equals("Error")){
                result.setText(finalresult);
            }
            return;
        }
        if(buttontext.equals("C")){
            tocalculate = tocalculate.substring(0,tocalculate.length()-1);
        }
        else{
            tocalculate = tocalculate+buttontext;
        }

        equation.setText(tocalculate);

        String finalresult = getResult(tocalculate);

        if(!finalresult.equals("Error")){
            result.setText(finalresult);
        }

    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalresult.endsWith(".0")){
                finalresult = finalresult.replace(".0","");
            }
            return finalresult;
        }catch (Exception e){
            return "Error";
        }
    }
}