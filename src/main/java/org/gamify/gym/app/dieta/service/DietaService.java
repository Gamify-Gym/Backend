package org.gamify.gym.app.dieta.service;

import java.util.List;

import org.gamify.gym.app.dieta.dto.AlimentoDto;
import org.gamify.gym.app.dieta.model.Acucars;
import org.gamify.gym.app.dieta.model.Alimento;
import org.gamify.gym.app.dieta.model.Dieta;
import org.gamify.gym.app.dieta.model.Gorduras;
import org.gamify.gym.app.dieta.repository.AlimentoRepository;
import org.gamify.gym.app.dieta.repository.DietaRepository;
import org.gamify.gym.app.user.model.Player;
import org.gamify.gym.app.user.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DietaService {
    private DietaRepository dietaRepository;
    private PlayerRepository playerRepository;
    private AlimentoRepository alimentoRepository;

    public DietaService(DietaRepository dietaRepository, PlayerRepository playerRepository,
            AlimentoRepository alimentoRepository) {
        this.dietaRepository = dietaRepository;
        this.playerRepository = playerRepository;
        this.alimentoRepository = alimentoRepository;
    }

    @Transactional
    public Dieta insertDieta(String email, String nomeDieta) {
        Player player = playerRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Dieta dieta = new Dieta();
        dieta.setName(nomeDieta);
        dieta.setPlayer(player);
        return dietaRepository.save(dieta);
    }

    @Transactional
    public Alimento insertAlimentos(String email, Long idDieta, AlimentoDto dto) {
        Dieta dieta = dietaRepository.findById(idDieta)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Alimento alimento = new Alimento();
        Gorduras gorduras = new Gorduras();
        Acucars acucars = new Acucars();

        alimento.setName(dto.nome());
        alimento.setCarbs(dto.carbs());
        alimento.setFats(dto.fats());
        alimento.setFibers(dto.fibers());
        alimento.setProteins(dto.proteins());
        alimento.setCalories(dto.calories());

        gorduras.setGorduraTrans(dto.gorduraTrans());
        gorduras.setGordurasMonosaturadas(dto.gordurasMonosaturadas());
        gorduras.setGordurasPoliinsaturadas(dto.gordurasPoliinsaturadas());
        gorduras.setGordurasSaturadas(dto.gordurasSaturadas());

        acucars.setAcucarAdicionado(dto.acucarAdicionado());
        acucars.setAcucarTotal(dto.acucarTotal());

        alimento.setAcucars(acucars);
        alimento.setGorduras(gorduras);
        alimento.setDieta(dieta);

        if (!alimento.getDieta().getPlayer().getUser().getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return alimentoRepository.save(alimento);
    }

    @Transactional
    public void deleteAlimento(String email, Long idAlimento) {
        Alimento alimento = alimentoRepository.findById(idAlimento)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!alimento.getDieta().getPlayer().getUser().getEmail().equals(email))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        alimentoRepository.delete(alimento);
    }

    @Transactional
    public List<Dieta> getDietas(String email) {
        List<Dieta> dietas = dietaRepository.findDietasWithAlimentosByPlayerEmail(email);
        return dietas;
    }

}
