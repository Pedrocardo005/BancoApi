package javaLambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestingLambda {

	public static void main(String[] args) {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		alunos.add(new Aluno("Rafael", 80L));
		alunos.add(new Aluno("Juliano", 1240L));
		alunos.add(new Aluno("Fernando", 825L));
		alunos.add(new Aluno("Quercia", 70L));
		alunos.add(new Aluno("Roberto", 102L));
		alunos.add(new Aluno("Carlos", 90L));
		
		List<Aluno> alunosComNomeR = new ArrayList<Aluno>();
		for(Aluno aluno : alunos) {
			if(aluno.getNome().startsWith("R")) {
				alunosComNomeR.add(aluno);
			}
		}
		
		List<Aluno> alunosComNomeR2 = alunos.stream().filter(aluno -> aluno.getNome().startsWith("R")
												&& aluno.getMatricula() > 100L).collect(Collectors.toList());
		
		System.out.println(alunosComNomeR2);
	}
}
