package com.ifms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.ifms.dto.AbastecimentoDTO;
import com.ifms.entities.Abastecimento;
import com.ifms.repository.AbastecimentoRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class AbastecimentoService {

	@Autowired
	private AbastecimentoRepository repository;

	@Transactional(readOnly = true)
	public List<AbastecimentoDTO> findAll() {
		List<Abastecimento> list = repository.findAll();
		return list.stream().map(Abastecimento -> new AbastecimentoDTO(Abastecimento)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public AbastecimentoDTO findById(Long id) {
		Optional<Abastecimento> obj = repository.findById(id);
		Abastecimento Abastecimento = obj
				.orElseThrow(() -> new ResourceNotFoundException("Abastecimento SOLICITADA NAO PODE SER ENCONTRADA!!"));
		return new AbastecimentoDTO(Abastecimento);
	}

	@Transactional
	public AbastecimentoDTO insert(AbastecimentoDTO dto) {
		Abastecimento entity = new Abastecimento();
		entity.setCpfMotorista(dto.getCpfMotorista());
		entity.setDataDoAbastecimento(dto.getDataDoAbastecimento());
		entity.setQuilometragem(dto.getQuilometragem());
		entity.setCombustivel(dto.getCombustivel());
		entity.setQuantidadeEmLitros(dto.getQuantidadeEmLitros());
		entity.setValorPorLitro(dto.getValorPorLitro());
		entity.setAutoPosto(dto.getAutoposto());
		entity.setVeiculo(dto.getVeiculo());
		entity = repository.save(entity);
		return new AbastecimentoDTO(entity);
	}

	@Transactional
	public AbastecimentoDTO update(Long id, AbastecimentoDTO dto) {

		try {
			Abastecimento entity = repository.getOne(id);
			entity.setCpfMotorista(dto.getCpfMotorista());
			entity.setDataDoAbastecimento(dto.getDataDoAbastecimento());
			entity.setQuilometragem(dto.getQuilometragem());
			entity.setCombustivel(dto.getCombustivel());
			entity.setQuantidadeEmLitros(dto.getQuantidadeEmLitros());
			entity.setValorPorLitro(dto.getValorPorLitro());
			entity.setAutoPosto(dto.getAutoposto());
			entity.setVeiculo(dto.getVeiculo());
			entity = repository.save(entity);
			return new AbastecimentoDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Abastecimento nao Encontrado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Abastecimento nao Encontrado");
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Nao foi possivel Deletar o Abastecimento.");
		}
	}

}
