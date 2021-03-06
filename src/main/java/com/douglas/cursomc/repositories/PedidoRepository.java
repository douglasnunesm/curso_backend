package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglas.cursomc.domains.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
