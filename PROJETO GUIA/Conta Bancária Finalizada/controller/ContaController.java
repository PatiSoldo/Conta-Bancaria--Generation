package conta.controller;

import java.util.ArrayList;


import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController<conta> implements ContaRepository {
	
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;
	
	

	 @Override
	    public void procurarPorNumero(int numero) {
	    	var conta = buscarNaColletion(numero);
	    	
	    	if(conta != null)
	    		conta.visualizar();
	    	else
	    		System.out.println("\nA Conta número : "+ numero + " não foi encontrada!");
	    	
	    }

	@Override
	public void listarTodas() {
		for(var conta: listaContas) {
			conta.visualizar();
	}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA conta número: "+ conta.getNumero()+ " foi criada com sucesso!");
				
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaColletion(conta.getNumero());
		
		if(buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA Conta número: "+ conta.getNumero()+  " foi atualizada com sucesso!");
		}else
			System.out.println("\nA Conta número: " + conta.getNumero() + " não foi encontrada!");
	}

	@Override
	public void deletar(int numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sacar(int numero, float valor) {
	  var conta = buscarNaColletion(numero);
	  
	  if(conta !=null) {
		  if (conta.sacar(valor)== true)
			  System.out.println("\nO Saque na Conta número: " + numero + " foi efetuado com sucesso!");
	  }else
		  System.out.println("\nA Conta Numero: " + numero + " não foi encontrada!");
		
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaColletion(numero);
		
		if(conta != null) {
			conta.depositar(valor);
			System.out.println("\nDepósito na Conta número: " + numero + " foi efetuado com sucesso!");
		}else
			System.out.println("\nA Conta numero: " + numero + " não foi encontrada ou a Conta destino não é uma Conta Corrente!");
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaColletion(numeroOrigem);
		var contaDestino = buscarNaColletion(numeroDestino);
		
		if(contaOrigem != null && contaDestino != null) {
			 
			if(contaOrigem.sacar(valor) == true ) {
				contaDestino.depositar(valor);;
				System.out.println("\nA Transferência foi efetuada com sucesso!");
			}
		}else
			System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas!");
		
		
	}
	
    public int gerarNumero() {
    	return ++ numero;
    }
    
    public Conta buscarNaColletion(int numero) {
    	for(var conta : listaContas) {
    		if(conta.getNumero() == numero) {
    			return conta;
    			
    		}
    	}
    	return null;
    }

	public int retornaTipo(int numero) {
		for (var conta: listaContas) {
			if(conta.getNumero() ==numero) {
			return conta.getTipo();
		}
	}
	return 0 ;
  }

}