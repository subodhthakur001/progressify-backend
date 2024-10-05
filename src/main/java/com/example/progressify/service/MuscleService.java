package com.example.progressify.service;

import com.example.progressify.dto.request.muscle.AddMuscleRequestDTO;
import com.example.progressify.dto.request.muscle.UpdateMuscleRequestDTO;
import com.example.progressify.model.Muscle;
import com.example.progressify.exception.DuplicateEntityException;
import com.example.progressify.dao.MuscleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MuscleService {

    private final MuscleRepository muscleRepository;

    @Autowired
    public MuscleService(MuscleRepository muscleRepository){
        this.muscleRepository = muscleRepository;
    }

    public Muscle addMuscle(AddMuscleRequestDTO addMuscleRequestDTO){
        if(muscleRepository.findByName(addMuscleRequestDTO.getName()) != null){
            throw new DuplicateEntityException("muscle already exists " + addMuscleRequestDTO.getName());
        }
        Muscle muscle = new Muscle();
        muscle.setName(addMuscleRequestDTO.getName());
        muscle.setUser(addMuscleRequestDTO.getUser_id());
        return muscleRepository.save(muscle);
    }

    public Muscle updateMuscle(UpdateMuscleRequestDTO updateMuscleRequestDTO){
        Muscle muscleToUpdate = muscleRepository.findById(updateMuscleRequestDTO.getMuscle_id())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "muscle does not exists with id " + updateMuscleRequestDTO.getMuscle_id()));
        if(updateMuscleRequestDTO.getName() != null){
            muscleToUpdate.setName(updateMuscleRequestDTO.getName());
        }
         return muscleRepository.save(muscleToUpdate);
    }

    public List<Muscle> muscleList(){
        return muscleRepository.findAll();
    }
}
