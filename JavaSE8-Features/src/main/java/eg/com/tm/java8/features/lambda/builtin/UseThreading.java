/*
 * Copyright (C) 2014 mohamed_taman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eg.com.tm.java8.features.lambda.builtin;

import static java.lang.System.out;

/**
 *
 * @author mohamed_taman
 */
public class UseThreading {

    public static void main(String[] args) {
        //Old version
//        Runnable thrd1 = new Runnable(){
//
//            @Override
//            public void run() {
//                out.println("Hello Thread 1.");
//            }  
//        };

        /* 
         *****************************************
         * Using lambda expression inner classes *
         *****************************************
         */
        Runnable thrd1 = () -> out.println("Hello Thread 1.");

        new Thread(thrd1).start();

         // Old Version
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                out.println("Hello Thread 2.");
//            }
//        }).start();
        
        /* 
         ******************************************
         * Using lambda exprssion anonymous class *
         ******************************************
         */
        new Thread(() -> out.println("Hello Thread 2.")).start();

    }
}
