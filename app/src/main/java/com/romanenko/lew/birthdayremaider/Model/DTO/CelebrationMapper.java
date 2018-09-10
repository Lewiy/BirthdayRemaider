package com.romanenko.lew.birthdayremaider.Model.DTO;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;

import java.util.ArrayList;
import java.util.List;

public class CelebrationMapper {

    public CelebrationMapper() {

    }

    public List<CelebrationVO> getVOObjects(List<DataCelebrationForListDTO> daoObjects) {

        List<CelebrationVO> celebrationVOS = new ArrayList<CelebrationVO>();

        for (DataCelebrationForListDTO daoObject : daoObjects
                ) {
            celebrationVOS.add(constructCelebrationVO(daoObject));
        }
        return celebrationVOS;
    }

    private CelebrationVO constructCelebrationVO(DataCelebrationForListDTO dataCelebrationForListsDTO) {
        CelebrationVO celebrationVO = new CelebrationVO();
        celebrationVO.setFirstName(dataCelebrationForListsDTO.firstName);
        celebrationVO.setLastName(dataCelebrationForListsDTO.lastName);
        celebrationVO.setDate(dataCelebrationForListsDTO.date);
        celebrationVO.setFotoPath(dataCelebrationForListsDTO.fotoPath);
        return celebrationVO;
    }

    public List<DataCelebrationForListDTO> getDTOObjects(List<CelebrationVO> celebrationVOS) {

        List<DataCelebrationForListDTO> celebrationForListDTOS = new ArrayList<DataCelebrationForListDTO>();

        for (CelebrationVO celebrationVO : celebrationVOS
                ) {
            celebrationForListDTOS.add(constructCelebrationDTO(celebrationVO));
        }
        return celebrationForListDTOS;
    }

    private DataCelebrationForListDTO constructCelebrationDTO(CelebrationVO celebrationVO) {

        DataCelebrationForListDTO dataCelebrationForListDTO = new DataCelebrationForListDTO();
        dataCelebrationForListDTO.firstName = celebrationVO.getFirstName();
        dataCelebrationForListDTO.lastName = celebrationVO.getLastName();
        dataCelebrationForListDTO.date = celebrationVO.getDate();
        dataCelebrationForListDTO.fotoPath = celebrationVO.getFotoPath();
        return dataCelebrationForListDTO;
    }

    public List<CelebrationPersonEntity> getEntity(List<DataCelebrationForListDTO> daoObjects) {

        List<CelebrationPersonEntity> celebrationPersonEntities = new ArrayList<CelebrationPersonEntity>();

        for (DataCelebrationForListDTO daoObject : daoObjects) {
            celebrationPersonEntities.add(constructEntity(daoObject));
        }
        return celebrationPersonEntities;
    }

    public CelebrationPersonEntity constructEntity(DataCelebrationForListDTO dataCelebrationForListsDTO) {

        CelebrationPersonEntity celebrationPersonEntity = new CelebrationPersonEntity();
        celebrationPersonEntity.firstName = dataCelebrationForListsDTO.firstName;
        celebrationPersonEntity.lastName = dataCelebrationForListsDTO.lastName;
        celebrationPersonEntity.date = dataCelebrationForListsDTO.date;
        celebrationPersonEntity.fotoPath = dataCelebrationForListsDTO.fotoPath;
        return celebrationPersonEntity;
    }

}
