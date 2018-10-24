package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.romanenko.lew.birthdayremaider.AlarmingSystem.MyDate;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrListNameDateFotoVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageRequirementDataDTO;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public abstract class CelebrationPersonDao {


    @Query("SELECT COUNT(_id) FROM celebration_person")
    public abstract Flowable<Integer> getNumberOfRows();


    @Query("SELECT celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.date,celebration_person.foto_path," +
            "celebration_person._id, date_entity.year " +
            "FROM celebration_person,date_entity " +
            "where celebration_person._id == date_entity.dateId")
    public abstract Flowable<List<DataCelebrationForListDTO>> getListCelebration();



    @Query("SELECT celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.foto_path," +
            "celebration_person._id, date_entity.year, date_entity.day, date_entity.month " +
            "FROM celebration_person,date_entity " +
            "where celebration_person._id == date_entity.dateId")
    public abstract Flowable<List<CelebrListNameDateFotoDTO>> getListCelebrDateNameFoto();



    @Query("SELECT comment,type_celebration from celebration_person WHERE _id = :userId ")
    public abstract Flowable<PersonalPageRequirementDataDTO> getPersonalPage(String userId);

    @Query("SELECT celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.date,celebration_person.foto_path,celebration_person.comment," +
            "celebration_person._id, date_entity.year, date_entity.month, date_entity.day," +
            "celebration_person.type_celebration,date_entity.dateId " +
            "FROM celebration_person,date_entity " +
            "where celebration_person._id == date_entity.dateId and  _id = :userId")

    public abstract Flowable<PersonalPageAllInformation> getPersonalPageAll(String userId);

    @Insert
    public abstract void birthdayPersonInsert(CelebrationPersonEntity celebrationPersonEntity);

    @Insert
    public abstract void birthdayPersonDateInsert(DateEntity DateEntity);

    @Update
    public abstract void birthdayPersonUpdete(CelebrationPersonEntity celebrationPersonEntity);
    @Update
    public  abstract void birthdayDateUpdate(DateEntity dateEntity);

    @Query("DELETE FROM celebration_person WHERE _id = :userId")
    public abstract void birthdayPersonDelete(int userId);

    @Query("DELETE FROM date_entity WHERE dateId = :dateId")
    public abstract void birthdayDateDelete(int dateId);

    @Transaction
    public void updateCelebrationAndDate(CelebrationPersonEntity celebrationPersonEntity,DateEntity dateEntity){
        birthdayDateUpdate(dateEntity);
        birthdayPersonUpdete(celebrationPersonEntity);
    }

    @Transaction
    public void deleteCelebrationAndDate(int userId, int dateId) {
        birthdayDateDelete(userId);
        birthdayPersonDelete(dateId);
    }

    @Transaction
    public void insertPersonAndDate(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity) {
        birthdayPersonInsert(celebrationPersonEntity);
        birthdayPersonDateInsert(dateEntity);
    }

    @Transaction
    public Flowable<PersonalPageAllInformation> updateAndShowCelebrationAndDate(CelebrationPersonEntity celebrationPersonEntity,DateEntity dateEntity){
        birthdayDateUpdate(dateEntity);
        birthdayPersonUpdete(celebrationPersonEntity);
        return  getPersonalPageAll(String.valueOf(celebrationPersonEntity._id));
    }

}
