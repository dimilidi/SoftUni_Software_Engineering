package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.repositories.interfaces;

import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics.Magic;

import java.util.*;

import static ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.common.ExceptionMessages.INVALID_MAGIC_REPOSITORY;

public class MagicRepositoryImpl implements MagicRepository{
    private Collection <Magic> data;


    public MagicRepositoryImpl() {
        this.data = new ArrayList<>();
    }

    @Override
    public Collection getData() {
        return data;
    }

    @Override
    public void addMagic(Magic model) {
        if (model == null){
            throw new NullPointerException(INVALID_MAGIC_REPOSITORY);
        }
        data.add(model);
    }

    @Override
    public boolean removeMagic(Magic model) {
        return data.remove(model);
    }

    @Override
    public Magic findByName(String name) {
        return data.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
    }
}