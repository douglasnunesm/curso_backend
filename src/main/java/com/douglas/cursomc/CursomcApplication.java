package com.douglas.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.douglas.cursomc.domains.Categoria;
import com.douglas.cursomc.domains.Cidade;
import com.douglas.cursomc.domains.Cliente;
import com.douglas.cursomc.domains.Endereco;
import com.douglas.cursomc.domains.Estado;
import com.douglas.cursomc.domains.Produto;
import com.douglas.cursomc.domains.enums.TipoCliente;
import com.douglas.cursomc.repositories.CategoriaRepository;
import com.douglas.cursomc.repositories.CidadeRepository;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.repositories.EnderecoRepository;
import com.douglas.cursomc.repositories.EstadoRepository;
import com.douglas.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	transient CategoriaRepository categoriaRepository;

	@Autowired
	transient ProdutoRepository produtoRepository;

	@Autowired
	transient EstadoRepository estadoRepository;

	@Autowired
	transient CidadeRepository cidadeRepository;

	@Autowired
	transient ClienteRepository clienteRepository;

	@Autowired
	transient EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "Jardim", "300", "Apto 203", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "Centro", "105", "Sala 800", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		// categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		// produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	}
}
