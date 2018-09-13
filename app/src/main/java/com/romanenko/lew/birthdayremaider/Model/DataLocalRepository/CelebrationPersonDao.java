package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.romanenko.lew.birthdayremaider.AlarmingSystem.MyDate;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageRequirementDataDTO;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public abstract class CelebrationPersonDao {


    @Query("SELECT celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.date,celebration_person.foto_path," +
            "celebration_person._id, date_entity.year " +
            "FROM celebration_person,date_entity " +
            "where celebration_person._id == date_entity.dateId")
   abstract Flowable<List<DataCelebrationForListDTO>> getListCelebration();

    @Query("SELECT comment,type_celebration from celebration_person WHERE _id = :userId ")
    abstract Flowable<PersonalPageRequirementDataDTO> getPersonalPage(String userId);

    @Query("SELECT celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.date,celebration_person.foto_path," +
            "celebration_person._id, date_entity.year, date_entity.month, date_entity.day " +
            "FROM celebration_person,date_entity " +
            "where celebration_person._id == date_entity.dateId and  _id = :userId")

    abstract Flowable<PersonalPageAllInformation> getPersonalPageAll(String userId);

    @Insert
    abstract void birthdayPersonInsert(CelebrationPersonEntity celebrationPersonEntity);

    @Insert
    abstract  void birthdayPersonDateInsert(MyDate myDate);

    @Update
    abstract void birthdayPersonUpdete(CelebrationPersonEntity celebrationPersonEntity);

    @Delete
    abstract  void birthdayPersonDelete(CelebrationPersonEntity celebrationPersonEntity);

    @Transaction
     void insertPersonAndDate(CelebrationPersonEntity celebrationPersonEntity, MyDate myDate) {
        birthdayPersonInsert(celebrationPersonEntity);
        birthdayPersonDateInsert(myDate);
    }

}
