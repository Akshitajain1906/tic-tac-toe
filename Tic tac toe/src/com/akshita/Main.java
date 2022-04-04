package com.akshita;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static char[][] gameBoard = {//5 x 5 ka board
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
    };
    static ArrayList<Integer> playerposlist = new ArrayList<Integer>();
    static ArrayList<Integer> compposlist = new ArrayList<Integer>();

    public static void main(String[] args) {
        printGameBoard();
        play();
    }

    public static void clearGameBoard(){
        for (int i = 0; i < gameBoard.length; i=i+2) {
            for (int j = 0; j < gameBoard[i].length; j=j+2) {
                gameBoard[i][j]=' ';
            }
        }
        playerposlist.clear();
        compposlist.clear();
    }

    public static void printGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("1 2 3" );
        System.out.println("4 5 6" );
        System.out.println("7 8 9" );

    }

    public static void getPosition(String user) {
        //u-user, c-computer
        int pos = 0;
        try {
            if (user.equals("u")) {
                Scanner sc = new Scanner(System.in);
                pos = sc.nextInt();
                while (playerposlist.contains(pos) || compposlist.contains(pos)) {
                    System.out.println("Position taken, please enter another position");
                    pos = sc.nextInt();
                }
            } else {
                computerTurn("c");
            }
        }
        catch (Exception e){
            System.out.println("enter valid place");
            play();
        }
        matchPosition(pos, user);
    }

    public static void matchPosition(int pos, String user) {
        char symbol = ' ';
        if (user == "u" && pos >= 1 && pos <= 9) {
            symbol = 'X';
            playerposlist.add(pos);
        } else if (user.equals("c") && pos >= 1 && pos <= 9) {
            symbol = 'O';
            compposlist.add(pos);
        } else
            System.out.println("recheck the position entered");


        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                System.out.println("enter position again");
                getPosition(user);
                break;

        }
        checkWinner();
        if (user.equals("u")) {
            user = "c";
        } else if (user.equals("c")) {
            user = "u";
            printGameBoard();
        }

        getPosition(user);
    }

    public static void computerTurn(String user) {
        int pos;
        Random rand = new Random();
        pos = rand.nextInt(9) + 1;
        while (playerposlist.contains(pos) || compposlist.contains(pos)) {
            pos = rand.nextInt(9) + 1; // [0-8]+1
        }
        matchPosition(pos, "c");
        printGameBoard();
    }
    public static void checkWinner() {
        List toprow = Arrays.asList(1, 2, 3);
        List midrow = Arrays.asList(4, 5, 6);
        List botrow = Arrays.asList(7, 8, 9);
        List leftcol = Arrays.asList(1, 4, 7);
        List midcol = Arrays.asList(2, 5, 8);
        List rigcol = Arrays.asList(3, 6, 9);
        List diagonal1 = Arrays.asList(1, 5, 9);
        List diagonal2 = Arrays.asList(3, 5, 7);

        List<List> winpos = new ArrayList<List>(); //new list nhi likh sakte we cant instantiate it
        winpos.add(toprow);
        winpos.add(midrow);
        winpos.add(botrow);
        winpos.add(leftcol);
        winpos.add(midcol);
        winpos.add(rigcol);
        winpos.add(diagonal1);
        winpos.add(diagonal2);


        for (List l : winpos) {
            //System.out.println(playerposlist);
            if (playerposlist.containsAll(l)) {
                printGameBoard();
                System.out.println("congo you won");
                clearGameBoard();
                play();
            } else if (compposlist.containsAll(l)) {
                printGameBoard();
                System.out.println("computer won, better luck next time ");
                clearGameBoard();
                play();
            } else if (compposlist.size() + playerposlist.size() == 9) {
                printGameBoard();
                System.out.println("DRAW");
                clearGameBoard();
                play();

            }
        }

    }

    public static void play() {

        System.out.print("Play TIC TAC TOE :) ?? y/n: ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        System.out.println("new game(n) or continue(c)");
        String gamechoice = sc.next();
        if(gamechoice=="n" || gamechoice=="N")
            clearGameBoard();
        printGameBoard();
        if (choice.contains("y") || choice.contains("Y")) {
            System.out.println("enter the position(1-9) at which you want to enter your symbol(x)");
            getPosition("u");
        }
        else{
            System.exit(0);
        }
    }
}
