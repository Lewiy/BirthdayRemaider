package com.romanenko.lew.birthdayremaider.Model.DataLocalRepository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.NotifyDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageRequirementDataDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables.CelebrationPersonEntity;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.Tables.DateEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public abstract class CelebrationPersonDao {

    @Query("SELECT _id, first_name,last_name," +
            "year,month,day,foto_path,type_celebration " +
            "FROM celebration_person, date_entity WHERE year = :year " +
            "and month = :month " +
            "and day = :day " +
            "and celebration_person._id == date_entity.dateId")
    public abstract Flowable<List<NotifyDTO>> getCelebrsDate(int year, int month, int day);

    @Query("SELECT COUNT(_id) FROM celebration_person")
    public abstract Flowable<Integer> getNumberOfRows();

    @Query("SELECT celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.foto_path,celebration_person.type_celebration," +
            "celebration_person._id, date_entity.year, date_entity.month, date_entity.day " +
            "FROM celebration_person " +
            "INNER JOIN  date_entity ON celebration_person._id == date_entity.dateId ")
    public abstract Flowable<List<DataCelebrationForListDTO>> getListCelebration();


    @Query("SELECT celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.foto_path,celebration_person.type_celebration," +
            "celebration_person._id, date_entity.year, date_entity.month, date_entity.day " +
            "FROM celebration_person " +
            "INNER JOIN  date_entity ON celebration_person._id == date_entity.dateId " +
            "WHERE first_name LIKE :pattern OR last_name LIKE :pattern")
    public abstract Flowable<List<DataCelebrationForListDTO>> getListCelebrationSearch(String pattern);

    @Query("SELECT  celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.foto_path," +
            "celebration_person._id, date_entity.year, date_entity.day, date_entity.month " +
            "FROM celebration_person " +
            "INNER JOIN  date_entity ON celebration_person._id == date_entity.dateId " +
            "WHERE  date_entity.month = strftime('%m','now')  AND date_entity.day >= strftime('%d','now')" +
            "OR date_entity.month = (strftime('%m','now') + 1)" +
            "OR date_entity.month = (strftime('%m','now') + 2)" +
            "ORDER BY date_entity.month ASC, date_entity.day ASC LIMIT 6")
    public abstract Flowable<List<CelebrListNameDateFotoDTO>> getListCelebrDateNameFoto();


    @Query("SELECT comment,type_celebration from celebration_person WHERE _id = :userId ")
    public abstract Flowable<PersonalPageRequirementDataDTO> getPersonalPage(String userId);

    @Query("SELECT celebration_person.first_name,celebration_person.last_name," +
            "celebration_person.foto_path,celebration_person.comment," +
            "celebration_person._id, celebration_person.idTemporary, " +
            "date_entity.year, date_entity.month, date_entity.day," +
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
    public abstract void birthdayDateUpdate(DateEntity dateEntity);

    @Query("DELETE FROM celebration_person WHERE _id = :userId")
    public abstract void birthdayPersonDelete(int userId);

    @Query("DELETE FROM date_entity WHERE dateId = :dateId")
    public abstract void birthdayDateDelete(int dateId);

    @Transaction
    public void updateCelebrationAndDate(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity) {
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
    public Flowable<PersonalPageAllInformation> updateAndShowCelebrationAndDate(CelebrationPersonEntity celebrationPersonEntity, DateEntity dateEntity) {
        birthdayDateUpdate(dateEntity);
        birthdayPersonUpdete(celebrationPersonEntity);
        return getPersonalPageAll(String.valueOf(celebrationPersonEntity._id));
    }

}
