package com.example.progressify.service;

import com.example.progressify.dao.UserRepository;
import com.example.progressify.dto.request.muscle.AddMuscleRequestDTO;
import com.example.progressify.dto.request.muscle.UpdateMuscleRequestDTO;
import com.example.progressify.model.Muscle;
import com.example.progressify.exception.DuplicateEntityException;
import com.example.progressify.dao.MuscleRepository;
import com.example.progressify.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MuscleService {

    private final MuscleRepository muscleRepository;
    private final UserRepository userRepository;
    ;

    @Autowired
    public MuscleService(MuscleRepository muscleRepository, UserRepository userRepository){

        this.muscleRepository = muscleRepository;
        this.userRepository = userRepository;
    }

    public Muscle addMuscle(AddMuscleRequestDTO addMuscleRequestDTO){
        if(muscleRepository.findByName(addMuscleRequestDTO.getName()) != null){
            throw new DuplicateEntityException("muscle already exists " + addMuscleRequestDTO.getName());
        }
        Muscle muscle = new Muscle();
        muscle.setName(addMuscleRequestDTO.getName());
        Optional<User> optionalUser = userRepository.findById(addMuscleRequestDTO.getUser_id());
        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException("User not found with ID: " + addMuscleRequestDTO.getUser_id());
        }
        muscle.setUser(optionalUser.get());
        return muscleRepository.save(muscle);
    }

    public Muscle updateMuscle(UpdateMuscleRequestDTO updateMuscleRequestDTO){
        Muscle muscleToUpdate = muscleRepository.findById(updateMuscleRequestDTO.getMuscleId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "muscle does not exists with id " + updateMuscleRequestDTO.getMuscleId()));
        if(updateMuscleRequestDTO.getName() != null){
            muscleToUpdate.setName(updateMuscleRequestDTO.getName());
        }
         return muscleRepository.save(muscleToUpdate);
    }

    public List<Muscle> muscleList(){
        return muscleRepository.findAll();
    }

    public Muscle deleteMuscle(Long id){
        Muscle muscle = muscleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Muscle cannot be found with id " + id));

         muscleRepository.delete(muscle);
         return muscle;
    }
}
