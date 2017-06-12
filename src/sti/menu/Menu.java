/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sti.menu;

import sti.model.Aluno;
import java.util.Scanner;

/**
 *
 * @author wilker
 */
public class Menu {

    public static int inicial() {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite sua matrícula:");
        return in.nextInt();
    }

    public static String optionsEmail(String name, String[] options) {
        Scanner in = new Scanner(System.in);
        System.out.println(name + ", por favor escolha uma das opções abaixo para o seu UFFMail");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + 1 + "-" + options[i]);
        }
        return options[in.nextInt() - 1];
    }

    public static void userInative() {
        System.out.println("Somente alunos ativos podem criar uffmail");
    }

    public static void emailExist() {
        System.out.println("Usuário já possui uffmail");
    }

    public static void emailCreated(Aluno aluno) {
        System.out.println("A criação de seu e-mail " + aluno.getUffmail() + " será feita nos próximos minutos.\n"
                + "Um SMS foi enviado para " + aluno.getTelefone() + " com a sua senha de acesso.");
    }

    public static void notExists() {
        System.out.println(
                "Não existe aluno com esta matrícula!");
    }
}
