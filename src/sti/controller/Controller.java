/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sti.controller;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import sti.menu.Menu;
import sti.model.Aluno;

/**
 *
 * @author wilker
 */
public class Controller {

    String filePath;
    CSVReader reader;
    Map<Integer, Aluno> alunos;

    public Controller(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.reader = new CSVReader(new FileReader(filePath));
    }

    private Map<Integer, Aluno> loadFile() throws IOException {
        Map<Integer, Aluno> alunos = new HashMap<>();
        String[] nextLine = reader.readNext();
        while ((nextLine = reader.readNext()) != null) {
            Aluno aluno = new Aluno(nextLine[0], Integer.parseInt(nextLine[1]), nextLine[2], nextLine[3], nextLine[4], nextLine[5]);
            alunos.put(Integer.parseInt(nextLine[1]), aluno);
        }
        this.alunos = alunos;
        return alunos;
    }

    private boolean isActive(int mat) {
        return this.alunos.get(mat).getStatus().equals("Ativo");
    }

    private boolean matExists(int mat) {
        return alunos.containsKey(mat);
    }

    private boolean checkUffmail(int mat) {
        return this.alunos.get(mat).getUffmail().equals("")
                || !this.alunos.get(mat).getUffmail().contains("@id.uff.br");
    }

    private void updateFile(Aluno aluno) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            try (Formatter out = new Formatter("tmp")) {
                String nextLine = scanner.nextLine();
                out.format(nextLine + "\n");
                while (scanner.hasNextLine()) {
                    nextLine = scanner.nextLine();
                    String[] tmp = nextLine.split(",");
                    if (Integer.parseInt(tmp[1]) == (aluno.getMatricula())) {
                        out.format("%s,%s,%s,%s,%s,%s\n", aluno.getNome(),
                                aluno.getMatricula(),
                                aluno.getTelefone(),
                                aluno.getEmail(),
                                aluno.getUffmail(),
                                aluno.getStatus());
                    } else {
                        out.format(nextLine + "\n");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
        }
        File file = new File("tmp");
        File file2 = new File(filePath);
        file.renameTo(file2);
    }

    public void start() throws IOException {
        loadFile();
        int mat = Menu.inicial();

        if (!matExists(mat)) {
            Menu.notExists();
            return;
        }

        if (!isActive(mat)) {
            Menu.userInative();
            return;
        }
        if (!checkUffmail(mat)) {
            Menu.emailExist();
            return;
        }
        Aluno aluno = alunos.get(mat);
        String email = Menu.optionsEmail(aluno.getNome(), aluno.generateEmail());
        aluno.setUffmail(email);
        Menu.emailCreated(aluno);
        updateFile(aluno);
    }
}
