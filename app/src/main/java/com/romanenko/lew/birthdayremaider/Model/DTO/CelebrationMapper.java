package com.romanenko.lew.birthdayremaider.Model.DTO;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables.DateEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;

import java.util.ArrayList;
import java.util.List;

public class CelebrationMapper {

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

    public static CelebrationVO constructCelebrationVO(DataCelebrationForListDTO dataCelebrationForListsDTO) {

        CelebrationVO celebrationVO = new CelebrationVO();
        celebrationVO.setFirstName(dataCelebrationForListsDTO.firstName);
        celebrationVO.setLastName(dataCelebrationForListsDTO.lastName);
        celebrationVO.setDay(dataCelebrationForListsDTO.day);
        celebrationVO.setMonth(dataCelebrationForListsDTO.month);
        celebrationVO.setYear(dataCelebrationForListsDTO.year);
        celebrationVO.setFotoPath(dataCelebrationForListsDTO.fotoPath);
        celebrationVO.setIdUser(dataCelebrationForListsDTO.userId);
        celebrationVO.setTypeCelebration(dataCelebrationForListsDTO.typeCelebration);

        if (dataCelebrationForListsDTO.fotoPath != null) {
            Bitmap scaled = scaleImage(dataCelebrationForListsDTO.fotoPath);
            celebrationVO.setImage(scaled);
        }

        return celebrationVO;
    }

    private static Bitmap scaleImage(String imgPath) {

        Bitmap bitmapImage = BitmapFactory.decodeFile(imgPath);
        int nh = (int) (bitmapImage.getHeight() * (512.0 / bitmapImage.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true);
        return scaled;
    }


    public static List<DataCelebrationForListDTO> getDTOObjects(List<CelebrationVO> celebrationVOS) {

        List<DataCelebrationForListDTO> celebrationForListDTOS = new ArrayList<DataCelebrationForListDTO>();

        for (CelebrationVO celebrationVO : celebrationVOS
                ) {
            celebrationForListDTOS.add(constructCelebrationDTO(celebrationVO));
        }
        return celebrationForListDTOS;
    }

    private static DataCelebrationForListDTO constructCelebrationDTO(CelebrationVO celebrationVO) {

        DataCelebrationForListDTO dataCelebrationForListDTO = new DataCelebrationForListDTO();
        dataCelebrationForListDTO.firstName = celebrationVO.getFirstName();
        dataCelebrationForListDTO.lastName = celebrationVO.getLastName();
        dataCelebrationForListDTO.day = celebrationVO.getDay();
        dataCelebrationForListDTO.month = celebrationVO.getMonth();
        dataCelebrationForListDTO.year = celebrationVO.getYear();
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
        celebrationPersonEntity.fotoPath = dataCelebrationForListsDTO.fotoPath;
        return celebrationPersonEntity;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public static DateEntity constructDateEntity(DateCelebrationVO dateCelebrationVO) {

        DateEntity celebrationDate = new DateEntity();
        celebrationDate.year = dateCelebrationVO.getYear();
        celebrationDate.month = dateCelebrationVO.getMonth();
        celebrationDate.day = dateCelebrationVO.getDay();
        celebrationDate.dateId = dateCelebrationVO.getDateId();

        return celebrationDate;
    }

    public static CelebrationPersonEntity constructDateEntity(CelebrationVO celebrationVO) {

        CelebrationPersonEntity celebrationPersonEntity = new CelebrationPersonEntity();

        celebrationPersonEntity.firstName = celebrationVO.getFirstName();
        celebrationPersonEntity.lastName = celebrationVO.getLastName();
        celebrationPersonEntity.fotoPath = celebrationVO.getFotoPath();
        celebrationPersonEntity.comment = celebrationVO.getComment();
        celebrationPersonEntity.typeCelebration = celebrationVO.getTypeCelebration();
        celebrationPersonEntity._id = celebrationVO.getIdUser();
        celebrationPersonEntity.idTemporary = celebrationVO.getIdTemporary();

        return celebrationPersonEntity;
    }

    public static HomeCelebrationVO constructHomeCelebrVO(CelebrListNameDateFotoDTO celebrListNameDateFotoDTO) {
        HomeCelebrationVO homeCelebrationVO = new HomeCelebrationVO();

        homeCelebrationVO.setFirstName(celebrListNameDateFotoDTO.firstName);
        homeCelebrationVO.setLastName(celebrListNameDateFotoDTO.lastName);
        homeCelebrationVO.setYear(celebrListNameDateFotoDTO.year);
        homeCelebrationVO.setMonth(celebrListNameDateFotoDTO.month);
        homeCelebrationVO.setDay(celebrListNameDateFotoDTO.day);
        homeCelebrationVO.setFotoPath(celebrListNameDateFotoDTO.fotoPath);

        if (homeCelebrationVO.getFotoPath() != null) {
            Bitmap bitmap = scaleImage(celebrListNameDateFotoDTO.fotoPath);
            Bitmap dst = cropToSquare(bitmap);
            homeCelebrationVO.setBitmap(dst);
        }

        return homeCelebrationVO;
    }


    private static Bitmap cropToSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = (height > width) ? width : height;
        int newHeight = (height > width) ? height - (height - width) : height;
        int cropW = (width - height) / 2;
        cropW = (cropW < 0) ? 0 : cropW;
        int cropH = (height - width) / 2;
        cropH = (cropH < 0) ? 0 : cropH;
        Bitmap cropImg = Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight);

        return cropImg;
    }

}
