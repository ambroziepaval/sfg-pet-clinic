package com.ambroziepaval.sfgpetclinic.services.map;

import com.ambroziepaval.sfgpetclinic.model.Owner;
import com.ambroziepaval.sfgpetclinic.model.Pet;
import com.ambroziepaval.sfgpetclinic.services.OwnerService;
import com.ambroziepaval.sfgpetclinic.services.PetService;
import com.ambroziepaval.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * Created by Ambrozie on 09/10/2018
 */
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {

        if (Objects.isNull(owner)) {
            return null;
        }

        if (owner.getPets() != null) {
            owner.getPets().forEach(pet -> {
                if (pet.getPetType() != null) {
                    if (pet.getPetType().getId() == null) {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                } else {
                    throw new RuntimeException("PetType is required.");
                }

                if (pet.getId() == null) {
                    Pet savedPet = petService.save(pet);
                    pet.setId(savedPet.getId());
                }
            });
        }

        return super.save(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
