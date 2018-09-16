package com.romanenko.lew.birthdayremaider.Model.DTO;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.DateEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;

import java.util.ArrayList;
import java.util.List;

public  class  CelebrationMapper {

    public CelebrationMapper() {

    }

    public static List<CelebrationVO> getVOObjects(List<DataCelebrationForListDTO> daoObjects) {

        List<CelebrationVO> celebrationVOS = new ArrayList<CelebrationVO>();

        for (DataCelebrationForListDTO daoObject : daoObjects
                ) {
            celebrationVOS.add(constructCelebrationVO(daoObject));
        }
        return celebrationVOS;
    }

    private static CelebrationVO constructCelebrationVO(DataCelebrationForListDTO dataCelebrationForListsDTO) {
        CelebrationVO celebrationVO = new CelebrationVO();
        celebrationVO.setFirstName(dataCelebrationForListsDTO.firstName);
        celebrationVO.setLastName(dataCelebrationForListsDTO.lastName);
        celebrationVO.setDate(dataCelebrationForListsDTO.date);
        celebrationVO.setFotoPath(dataCelebrationForListsDTO.fotoPath);
        celebrationVO.setIdUser(dataCelebrationForListsDTO.userId);

        return celebrationVO;
    }

    public static List<DataCelebrationForListDTO> getDTOObjects(List<CelebrationVO> celebrationVOS) {

        List<DataCelebrationForListDTO> celebrationForListDTOS = new ArrayList<DataCelebrationForListDTO>();

        for (CelebrationVO celebrationVO : celebrationVOS
                ) {
            celebrationForListDTOS.add(constructCelebrationDTO(celebrationVO));
        }
        return celebrationForListDTOS;
    }

    private  static DataCelebrationForListDTO constructCelebrationDTO(CelebrationVO celebrationVO) {

        DataCelebrationForListDTO dataCelebrationForListDTO = new DataCelebrationForListDTO();
        dataCelebrationForListDTO.firstName = celebrationVO.getFirstName();
        dataCelebrationForListDTO.lastName = celebrationVO.getLastName();
        dataCelebrationForListDTO.date = celebrationVO.getDate();
        dataCelebrationForListDTO.fotoPath = celebrationVO.getFotoPath();
        return dataCelebrationForListDTO;
    }

    public static List<CelebrationPersonEntity> getEntity(List<DataCelebrationForListDTO> daoObjects) {

        List<CelebrationPersonEntity> celebrationPersonEntities = new ArrayList<CelebrationPersonEntity>();

        for (DataCelebrationForListDTO daoObject : daoObjects) {
            celebrationPersonEntities.add(constructEntity(daoObject));
        }
        return celebrationPersonEntities;
    }

    public static CelebrationPersonEntity constructEntity(DataCelebrationForListDTO dataCelebrationForListsDTO) {

        CelebrationPersonEntity celebrationPersonEntity = new CelebrationPersonEntity();
        celebrationPersonEntity.firstName = dataCelebrationForListsDTO.firstName;
        celebrationPersonEntity.lastName = dataCelebrationForListsDTO.lastName;
        celebrationPersonEntity.date = dataCelebrationForListsDTO.date;
        celebrationPersonEntity.fotoPath = dataCelebrationForListsDTO.fotoPath;
        return celebrationPersonEntity;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public static DateEntity constructDateEntity(DateCelebrationVO dateCelebrationVO) {

        DateEntity celebrationDate = new DateEntity();
        celebrationDate.year = dateCelebrationVO.getYear();
        celebrationDate.month = dateCelebrationVO.getMonth();
        celebrationDate.day = dateCelebrationVO.getDay();

        return celebrationDate;
    }

    public static CelebrationPersonEntity constructDateEntity(CelebrationVO celebrationVO) {

        CelebrationPersonEntity celebrationPersonEntity = new  CelebrationPersonEntity();

        celebrationPersonEntity.firstName = celebrationVO.getFirstName();
        celebrationPersonEntity.lastName = celebrationVO.getLastName();
        celebrationPersonEntity.fotoPath = celebrationVO.getFotoPath();
        celebrationPersonEntity.comment = celebrationVO.getComment();
        celebrationPersonEntity.typeCelebration = celebrationVO.getTypeCelebration();

        return celebrationPersonEntity;
    }
}
