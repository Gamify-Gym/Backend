package org.gamify.gym.app.dieta.controller;

import java.util.List;

import org.gamify.gym.app.dieta.dto.AlimentoDto;
import org.gamify.gym.app.dieta.model.Alimento;
import org.gamify.gym.app.dieta.model.Dieta;
import org.gamify.gym.app.dieta.service.DietaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/diet")
public class DietaController {
    private final DietaService dietaService;

    public DietaController(DietaService dietaService){
        this.dietaService = dietaService;
    }

    @GetMapping(value = "/plan", produces = "application/json")
    public ResponseEntity<?> getDiet(Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            List<Dieta> diets = dietaService.getDietas(email);
            return ResponseEntity.ok(diets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
     }
     
     @PostMapping(value = "/plan", produces = "application/json")
    public ResponseEntity<?> insertDieta(@RequestBody String nomeDieta, Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Dieta dieta = dietaService.insertDieta(email, nomeDieta);
                return ResponseEntity.ok(dieta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/food")
    public ResponseEntity<?> deleteFood(Authentication authentication,Long idAlimento) {
        try {   
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaimAsString("sub");
        dietaService.deleteAlimento(email, idAlimento);
        return ResponseEntity.ok("Succesfully deleted food");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
     }


    @PostMapping(value = "/food", produces = "application/json")
    public ResponseEntity<?> insertFood(@RequestBody AlimentoDto dto,Authentication authentication) {
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String email = jwt.getClaimAsString("sub");
            Alimento food = dietaService.insertAlimentos(email,dto.idDieta() ,dto);
            return ResponseEntity.ok(food);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    }
            
        



