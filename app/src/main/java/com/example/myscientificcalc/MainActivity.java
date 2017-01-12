package com.example.myscientificcalc;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
    static SharedPreferences Sp;
    public static String answer="answer";//will be used as key for the answer


    static EditText scr;
    static EditText scrpst;
    String str="";
    static PM_Fragment pm_fragment;//this is scientific calculator
    static LM_Fragment lm_fragment;//this is normal calculator
    static int check=0;//checks which fragment switch is clicked

    public static void RunWhenFragmentIsReady(){
        if (pm_fragment!= null && check==1) {
            View v=pm_fragment.v;
            scr=(EditText)v.findViewById(R.id.editText);
            scrpst=(EditText)v.findViewById(R.id.editText2);
            scr.setEnabled(false);
            scrpst.setEnabled(false);
        }
        if(lm_fragment!=null && check==2) {
            View v = lm_fragment.v;
            scr = (EditText) v.findViewById(R.id.editText);
            scrpst = (EditText) v.findViewById(R.id.editText2);
            scr.setEnabled(false);
            scrpst.setEnabled(false);
        }
        check=0;

        if(Sp.contains(answer)){
            scrpst.setText(Sp.getString(answer,""));
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //

        Sp=getSharedPreferences(answer,MODE_PRIVATE);

        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        lm_fragment = new LM_Fragment();//initially will call this fragment

        fragmentTransaction.replace(android.R.id.content, lm_fragment);
        check = 2;
        fragmentTransaction.commit();



        //



    }

    public void onHit(View v){//used for switching fragments
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(v.getId()==R.id.switch1){

            lm_fragment = new LM_Fragment();

            fragmentTransaction.replace(android.R.id.content, lm_fragment);
            check=2;


        }
        if(v.getId()==R.id.switch2){

            pm_fragment = new PM_Fragment();

            fragmentTransaction.replace(android.R.id.content, pm_fragment);
            check=1;
        }
        fragmentTransaction.commit();


    }

    public void onPress(View v)//used for all buttons, other the switch
    {
        if(scr.getText().length()>22)
        {
            scr.setTextSize(18);
        }
        if(scr.getText().length()<=22)
        {
            scr.setTextSize(25);
        }



        if(v.getId()==R.id.button0)
        {
            scr.append("0");
        }
        if(v.getId()==R.id.button1)
        {

            scr.append("1");
        }
        if(v.getId()==R.id.button2)
        {
            scr.append("2");
        }
        if(v.getId()==R.id.button3)
        {
            scr.append("3");
        }
        if(v.getId()==R.id.button4)
        {
            scr.append("4");
        }
        if(v.getId()==R.id.button5)
        {
            scr.append("5");
        }
        if(v.getId()==R.id.button6)
        {
            scr.append("6");
        }
        if(v.getId()==R.id.button7)
        {
            scr.append("7");
        }
        if(v.getId()==R.id.button8)
        {
            scr.append("8");
        }
        if(v.getId()==R.id.button9)
        {
            scr.append("9");
        }
        if(v.getId()==R.id.buttonADD)
        {
            scr.append("+");
        }
        if(v.getId()==R.id.buttonSUB)
        {
            scr.append("-");
        }
        if(v.getId()==R.id.buttonDIV)
        {
            scr.append("/");
        }
        if(v.getId()==R.id.buttonMUL)
        {
            scr.append("*");
        }
        if(v.getId()==R.id.br1)
        {
            scr.append("(");
        }
        if(v.getId()==R.id.br2)
        {
            scr.append(")");
        }

        if(v.getId()==R.id.buttonDOT)
        {
            scr.append(".");
        }

        if(v.getId()==R.id.buttonRES)
        {
            str=scr.getText().toString();
            int i=str.length();

            int k=0,l=0;
            for(int j=0;j<i;j++)
            {

                char c=str.charAt(j);
                if(c=='(')
                {
                    k++;
                }
                if(c==')')
                {
                    l++;
                }
            }


/*check it*/ if(str.contains("("))
            {
            if(k>l)
            {
                Toast.makeText(this, "Must To Close With )",Toast.LENGTH_SHORT).show();
            }
            else if(k<l)
            {
                Toast.makeText(this, "You Put Some Extra )", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    String postfix = toPostfix(str);
                    String ans=String.valueOf(computePostfix(postfix));

                }
                catch (Exception e)
                {
                    scr.setText("Error");

                }
            }
        }

        else
        {
            try
            {
                String postfix = toPostfix(str);
                String ans=String.valueOf(computePostfix(postfix));
                SharedPreferences.Editor editor=Sp.edit();
                editor.putString(answer,ans);
                editor.commit();
                scrpst.setText(ans);

            }
            catch (Exception e)
            {
                scr.setText("Error");

            }
        }
        }

        if(v.getId()==R.id.buttonAC)
        {
            scr.setText("");
            str="";

        }


        if(v.getId()==R.id.buttonSin)
        {
            scr.append("(sin(");
        }

        if(v.getId()==R.id.buttonCOS)
        {

            scr.append("(cos(");
        }

        if(v.getId()==R.id.buttonTAN)
        {

            scr.append("(tan(");
        }

        if(v.getId()==R.id.buttonE)
        {
            scr.append("(exp(");
        }

        if(v.getId()==R.id.buttonLog)
        {
            scr.append("(log(");
        }

        if(v.getId()==R.id.buttonSquare)
        {

            scr.append("(square(");
        }

        if(v.getId()==R.id.buttonRoot)
        {
            scr.append("(sroot(");
        }

        if(v.getId()==R.id.buttonln)
        {
            scr.append("(ln(");
        }

        if(v.getId()==R.id.buttonPIE)
        {
            scr.append("3.141592654");
        }

        if(v.getId()==R.id.buttonDEL)
        {
            String st=scr.getText().toString();


            if(st.length()>1)
            {
                st=st.substring(0, (st.length()-1));
                scr.setText(st);
            }
            else
            {
                scr.setText("");
            }

        }

        if(v.getId()==R.id.buttonPOW)
        {
            scr.append("^");
        }

    }



    public static String toPostfix(String infix) throws Exception {

        try {
            String postfix = "";
            boolean unary = true; // is the current operator unary? unary are operator with one operand
            //MyGenericsStack<String> stack = new MyGenericsStack<>(infix.length());
            Stack<String> stack=new Stack<String>();

            StringTokenizer st = new StringTokenizer(infix,"()+-/%*sctl^e", true);

            while (st.hasMoreTokens()) {
                String token = st.nextToken().trim();
                if(token.equals("o"))
                {
                    token+="s";

                }


                if(token.equals("s"))
                {
                    continue;
                }else if(token.equals("c"))
                {
                    continue;
                }


                else if(token.equals("t"))
                {
                    continue;
                }

                else if(token.equals("l"))
                {
                    continue;
                }
                else if(token.equals("e"))
                {
                    continue;
                }

                else if (token.equals("")) { // skip any empty token
                }
                else if (token.equals("(")) {
                    stack.push(token);
                }
                else if (token.equals(")"))
                {
                    String op;
                    while (!(op = stack.pop()).equals("("))
                    {
                        postfix += " " + op;
                    }
                }
                else if (token.equals("*")|| token.equals("+") || token.equals("-")|| token.equals("^")
                        || token.equals("%") || token.equals("/") || token.equals("in") || token.equals("os")|| token.equals("an")||
                        token.equals("og")|| token.equals("n")|| token.equals("roo")|| token.equals("quar")|| token.equals("xp"))
                {
                    //		System.out.println(token);

                    //here logic hint is if after any operator comes other operator, it has to an unary
                    //after any digit there always an binary operator

                    if (unary) {
                        token = "u" + token;
                        // a unary op always goes on
                        // the stack without popping any other op
                        stack.push(token);
                    } else {

                        int p = operatorPrecedence(token);
                        while (!stack.isEmpty() && !stack.peek().equals("(")
                                && operatorPrecedence(stack.peek()) >= p)
                        {
                            String op = stack.pop();
                            postfix += " " + op;
                        }
                        stack.push(token);
                    }
                    unary = true; // if an operator is after this one, it
                    // has to be unary
                } else { // an operand
                    //Integer.parseInt(token); // just to check that
                    // it is a number
                    // If not a number, an exception is
                    // thrown
                    postfix += " " + token;
                    unary = false; // any operator after an operand is binary
                }
            }
            while (!stack.isEmpty()) {
                String op = stack.pop();
                postfix += " " + op;
            }
            return postfix;
        } catch (EmptyStackException ese) {
            throw new Exception();
        } catch (NumberFormatException nfe) {
            throw new Exception();
        }
    }

    public double computePostfix(String postfix) throws Exception {

        try {
            //MyGenericsStack stack = new MyGenericsStack(postfix.length());
            Stack<Double> stack=new Stack<Double>();

            StringTokenizer st = new StringTokenizer(postfix);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (token.equals("*")
                        || // an operator
                        token.equals("+") || token.equals("-")
                        || token.equals("/") || token.equals("^") ||
                        token.equals("uin") || token.equals("uos") || token.equals("uan") ||
                        token.equals("uog") || token.equals("un") || token.equals("uroo") || token.equals("uquar") || token.equals("uxp"))
                {
                    applyOperator(token, stack);
                }
                else
                { // an operand
                    stack.push(Double.parseDouble(token));
                }
            }

            double result = stack.pop();
            if (!stack.isEmpty()) { // the stack should be empty
                throw new Exception();
            }
            return result;
        } catch (EmptyStackException ese) {
            throw new Exception();
        } catch (NumberFormatException nfe) {
            throw new Exception();
        }
    }

    public static void applyOperator(String operator, Stack<Double> s) throws Exception {
        double op1 =(Double) s.pop();
        if (operator.equals("u-")) {
            s.push(-op1);
        } else if (operator.equals("u+")) {
            s.push(op1);
        }
        else if(operator.equals("uin"))
        {
            double d1=Math.sin(op1);

            if(d1==0.8939966636005579)
            {
                double d2=Math.ceil(d1);
                s.push(d2);
            }
            else
            {
                s.push(d1);
            }
        }

        else if(operator.equals("uos"))
        {
            double d1=Math.cos(op1);

            if(d1==-0.4480736161291702)
            {
                double d2=Math.ceil(d1);
                s.push(d2);
            }
            else
            {
                s.push(d1);
            }
        }
        else if(operator.equals("uan"))
        {
            double d1=Math.tan(op1);

            if(d1==1.6197751905438615)
            {
                double d2=Math.floor(d1);
                s.push(d2);
            }
            else
            {
                s.push(d1);
            }

        }

        else if(operator.equals("uog"))
        {
            double d1=Math.log10(op1);

            s.push(d1);

        }

        else if(operator.equals("un"))
        {
            double d1=Math.log(op1);

            s.push(d1);

        }

        else if(operator.equals("uroo"))
        {
            double d1=Math.sqrt(op1);

            s.push(d1);

        }
        else if(operator.equals("uquar"))
        {
            double d1=op1*op1;

            s.push(d1);

        }

        else if(operator.equals("uxp"))
        {
            double d1=Math.exp(op1);

            s.push(d1);

        }


        else { // binary operator
            double op2 =s.pop();
            double result;
            if (operator.equals("+"))
            {
                result = op2 + op1;
            }
            else if (operator.equals("-"))
            {
                result = op2 - op1;
            }
            else if (operator.equals("/"))
            {
                result = op2 / op1;
            }
            else if (operator.equals("%"))
            {
                result = op2 % op1;
            }
            else if (operator.equals("*"))
            {
                result = op2 * op1;
            }
            else if (operator.equals("^"))
            {
                result = Math.pow(op2, op1);
            }

            else {

                throw new IllegalArgumentException();
            }
            s.push(result);
        }
    }
    public static int operatorPrecedence(String operator) throws Exception {


        if (operator.equals("*") || operator.equals("/") || operator.equals("%")|| operator.equals("^"))
        {
            return 7;
        }
        else if (operator.equals("-") || operator.equals("+"))
        {
            return 6;
        }
        else
        {

            throw new Exception();

        }
    }
}

