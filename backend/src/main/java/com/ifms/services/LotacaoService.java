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
import com.ifms.dto.LotacaoDTO;
import com.ifms.entities.Lotacao;
import com.ifms.repository.LotacaoRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class LotacaoService {

	@Autowired
	private LotacaoRepository repository;

	@Transactional(readOnly = true)
	public List<LotacaoDTO> findAll() {
		List<Lotacao> list = repository.findAll();
		return list.stream().map(Lotacao -> new LotacaoDTO(Lotacao)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public LotacaoDTO findById(Long id) {
		Optional<Lotacao> obj = repository.findById(id);
		Lotacao Lotacao = obj
				.orElseThrow(() -> new ResourceNotFoundException("Lotacao nao foi encontrado!!"));
		return new LotacaoDTO(Lotacao);
	}

	@Transactional
	public LotacaoDTO insert(LotacaoDTO dto) {
		Lotacao entity = new Lotacao();
		entity.setDescricao(dto.getDescricao());
		entity.setEmail(dto.getEmail());
		entity.setSite(dto.getSite());
		entity.setTelefone(dto.getTelefone());
		entity.setEndereco(dto.getEndereco());
		entity.setCidade(dto.getCidade());
		entity = repository.save(entity);
		return new LotacaoDTO(entity);
	}

	@Transactional
	public LotacaoDTO update(Long id, LotacaoDTO dto) {

		try {
			Lotacao entity = repository.getOne(id);
			entity.setDescricao(dto.getDescricao());
			entity.setEmail(dto.getEmail());
			entity.setSite(dto.getSite());
			entity.setTelefone(dto.getTelefone());
			entity.setEndereco(dto.getEndereco());
			entity.setCidade(dto.getCidade());
			entity = repository.save(entity);
			return new LotacaoDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Lotacao nao Encontrada");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Lotacao nao Encontrada");
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Nao foi possivel Deletar a Lotacao.");
		}
	}

}
